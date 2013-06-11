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


import com.google.common.collect.Lists;
import dk.nsi.sdm4.core.domain.Dataset;
import dk.nsi.sdm4.core.domain.TemporalEntity;
import dk.nsi.sdm4.cpr.parser.models.*;

import java.util.Date;
import java.util.List;

public class CPRDataset
{
	@SuppressWarnings("unchecked")
	private final List<Dataset<? extends CPREntity>> datasets = Lists.newArrayList(
			new Dataset<Personoplysninger>(Personoplysninger.class),
			new Dataset<Klarskriftadresse>(Klarskriftadresse.class),
			new Dataset<NavneBeskyttelse>(NavneBeskyttelse.class),
			new Dataset<Navneoplysninger>(Navneoplysninger.class),
			new Dataset<UmyndiggoerelseVaergeRelation>(UmyndiggoerelseVaergeRelation.class),
			new Dataset<ForaeldreMyndighedRelation>(ForaeldreMyndighedRelation.class),
			new Dataset<BarnRelation>(BarnRelation.class)
	);

	private Date validFrom;
    private Date previousFileValidFrom;

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

    public Date getPreviousFileValidFrom() {
        return previousFileValidFrom;
    }

    public void setPreviousFileValidFrom(Date previousFileValidFrom) {
        this.previousFileValidFrom = previousFileValidFrom;
    }

	public <T extends CPREntity> void addEntity(T entity) {
		entity.setDataset(this);
		for (Dataset<? extends TemporalEntity> dataset : datasets)
		{
			if (dataset.getType().equals(entity.getClass()))
			{
				@SuppressWarnings("unchecked")
				Dataset<T> typedDataset = (Dataset<T>) dataset;
				typedDataset.add(entity);
			}
		}
	}

	public List<Dataset<? extends CPREntity>> getDatasets() {
		return datasets;
	}

	@SuppressWarnings("unchecked")
	public <T extends TemporalEntity> Dataset<T> getDataset(Class<T> entityClass) {
		for (Dataset<? extends TemporalEntity> dataset : datasets)
		{
			if (dataset.getType().equals(entityClass))
			{
				return (Dataset<T>) dataset;
			}
		}

		throw new IllegalArgumentException("Ukendt entitetsklasse: " + entityClass);
	}
}
