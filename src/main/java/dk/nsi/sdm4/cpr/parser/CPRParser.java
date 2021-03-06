/**
 * The MIT License
 *
 * Original work sponsored and donated by National Board of e-Health (NSI), Denmark
 * (http://www.nsi.dk)
 *
 * Copyright (C) 2011 National Board of e-Health (NSI), Denmark (http://www.nsi.dk)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dk.nsi.sdm4.cpr.parser;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import dk.nsi.sdm4.core.domain.Dataset;
import dk.nsi.sdm4.core.domain.Entities;
import dk.nsi.sdm4.core.domain.TemporalEntity;
import dk.nsi.sdm4.core.parser.Parser;
import dk.nsi.sdm4.core.parser.ParserException;
import dk.nsi.sdm4.core.persistence.Persister;
import dk.nsi.sdm4.core.util.Dates;
import dk.nsi.sdm4.cpr.parser.models.Klarskriftadresse;
import dk.nsi.sdm4.cpr.parser.models.NavneBeskyttelse;
import dk.nsi.sdm4.cpr.parser.models.Navneoplysninger;
import dk.nsi.sdm4.cpr.parser.models.Personoplysninger;
import dk.sdsd.nsp.slalog.api.SLALogItem;
import dk.sdsd.nsp.slalog.api.SLALogger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class CPRParser implements Parser {
	private static final Logger logger = Logger.getLogger(CPRParser.class);

	@Value("${spooler.cpr.file.pattern.person}")
	private Pattern personFilePattern;

	@Value("${spooler.cpr.file.pattern.person.delta}")
	private Pattern personFileDeltaPattern;

	@Autowired
	private SLALogger slaLogger;

	@Autowired
	private CprSingleFileImporter cprSingleFileImpoter;

	@Autowired
	Persister persister;

	@Autowired
	JdbcTemplate jdbcTemplate;

    @Override
    public void process(File dataset, String identifier) throws ParserException {
	    Preconditions.checkNotNull(dataset, "dataset is null");
	    Preconditions.checkState(dataset.isDirectory(), "dataset " + dataset.getAbsolutePath() + " is not a directory");
	    Preconditions.checkState(dataset.canRead(), "dataset directory" + dataset.getAbsolutePath() + " is not readable");

	    File[] input = dataset.listFiles();
        for (File personFile : input) {
            if (!isPersonFile(personFile)) {
                throw new ParserException("File " + personFile.getAbsolutePath() + " is not a valid CPR file. Nothing will be imported from the fileset.");
            }
        }
        SLALogItem slaLogItem = slaLogger.createLogItem(getHome()+".process", "SDM4."+getHome()+".process");
        slaLogItem.setMessageId(identifier);
        slaLogItem.addCallParameter(Parser.SLA_INPUT_NAME, dataset.getAbsolutePath());
        try {
            // Make sure transaction time is reset on import
            persister.resetTransactionTime();

            // Check that the sequence is kept.

            ArrayList<String> cprWithChanges = Lists.newArrayList();
            long processed = 0;

	        for (File personFile : input) {
		        SLALogItem slaLogItemFile = slaLogger.createLogItem("CPRImport of file", personFile.getName());
		        logger.info("Started parsing CPR file. file=" + personFile.getAbsolutePath());

		        CPRDataset changes = cprSingleFileImpoter.parse(personFile);

		        if (isDeltaFile(personFile)) {
			        Date previousVersion = getLatestVersion();

			        if (previousVersion == null) {
				        logger.warn("Didn't find any previous versions of CPR. Asuming an initial import and skipping sequence checks.");
			        } else {
                        Date previousFileValidFrom = changes.getPreviousFileValidFrom();
                        if(!comparePreviousVersion(previousFileValidFrom, previousVersion)) {
                            throw new ParserException("Forrige ikrafttraedelsesdato i personregisterfilen stemmer ikke overens med forrige ikrafttraedelsesdato i databasen.");
                        }
                    }
		        }

		        // All these entities affect the person table.

		        Set<Class<?>> classesObservedByPVIT = Sets.newHashSet();
		        classesObservedByPVIT.add(Navneoplysninger.class);
		        classesObservedByPVIT.add(Klarskriftadresse.class);
		        classesObservedByPVIT.add(Personoplysninger.class);
		        classesObservedByPVIT.add(NavneBeskyttelse.class);

		        for (Dataset<? extends TemporalEntity> changesDataset : changes.getDatasets()) {
                    processed += changesDataset.size();
			        persister.persistDeltaDataset(changesDataset);

			        // The GOS/CPR component requires a table with
			        // a combination columns (CPR, ModifiedDate).
			        //
			        // The key for all CPR entities is the CPR and thus
			        // we can safely case the key value.

			        for (TemporalEntity record : changesDataset.getEntities()) {
				        if (!classesObservedByPVIT.contains(record.getClass())) continue;

				        // It does not matter that the same CPR is added
				        // multiple times. It is a set and only contains
				        // distinct entries.

				        String cpr = Entities.getEntityID(record).toString();

				        try {
					        Preconditions.checkState(cpr.length() == 10, "Invalid key used in the CPR parser. Only CPR keys are supported.");
				        } catch (Exception e) {
					        slaLogItemFile.setCallResultError("CPR Import failed importing file " + personFile.getName() + " - Cause: " + e.getMessage());
					        slaLogItemFile.store();

					        throw new RuntimeException(e);
				        }

				        cprWithChanges.add(cpr);
			        }
		        }

		        // Add latest 'version' date to database if we are not importing
		        // a full set.

		        if (isDeltaFile(personFile)) {
			        insertVersion(changes.getValidFrom());
		        }

		        slaLogItemFile.setCallResultOk();
		        slaLogItemFile.store();
	        }

	        // Update the GOS/CPR table with the current time stamp and
            // CPR numbers.

	        Date modifiedDate = new Date();
            for (String cpr : cprWithChanges) {
	            jdbcTemplate.update("REPLACE INTO ChangesToCPR (CPR, ModifiedDate) VALUES (?,?)", cpr, modifiedDate);
            }
            slaLogItem.addCallParameter(Parser.SLA_RECORDS_PROCESSED_MAME, ""+processed);
            slaLogItem.setCallResultOk();
            slaLogItem.store();

        } catch (ParserException e) {
	        markAsFailed(slaLogItem, e);
	        throw e;
        } catch (Exception e) {
	        markAsFailed(slaLogItem, e);
            throw new RuntimeException(e);
        }
    }

    private boolean comparePreviousVersion(Date previousInFile, Date previousInDb) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String prevInFile = sdf.format(previousInFile);
        String prevInDb = sdf.format(previousInDb);
        return prevInDb.equals(prevInFile);
    }

    private void markAsFailed(SLALogItem slaLogItem, Exception e) {
		slaLogItem.setCallResultError("CPR Import failed - Cause: " + e.getMessage());
		slaLogItem.store();
	}

	private boolean isPersonFile(File f) {
        return personFilePattern.matcher(f.getName()).matches();
    }

    private boolean isDeltaFile(File f) {
        return personFileDeltaPattern.matcher(f.getName()).matches();
    }

    public Date getLatestVersion() throws SQLException {
	    try {
            return jdbcTemplate.queryForObject("SELECT MAX(IkraftDato) AS Ikraft FROM PersonIkraft", Date.class);
	    } catch (EmptyResultDataAccessException ignored) {
            // We just return null if no previous version of CPR has been imported.
		    return null;
	    }
    }

    void insertVersion(Date calendar) throws SQLException {
		jdbcTemplate.update("INSERT INTO PersonIkraft (IkraftDato) VALUES ('" + Dates.toSqlDate(calendar) + "')");
    }

    @Override
    public String getHome() {
        return "cprimporter";
    }
}
