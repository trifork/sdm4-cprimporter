
-- -----------------------------------------------------
-- Someone has to create the SKRS tables first time
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SKRSViewMapping` (
  `idSKRSViewMapping` BIGINT(15) NOT NULL AUTO_INCREMENT ,
  `register` VARCHAR(255) NOT NULL ,
  `datatype` VARCHAR(255) NOT NULL ,
  `version` INT NOT NULL ,
  `tableName` VARCHAR(255) NOT NULL ,
  `createdDate` TIMESTAMP NOT NULL ,
  PRIMARY KEY (`idSKRSViewMapping`) ,
  INDEX `idx` (`register` ASC, `datatype` ASC, `version` ASC) ,
  UNIQUE INDEX `unique` (`register` ASC, `datatype` ASC, `version` ASC) )
  ENGINE = InnoDB;
CREATE  TABLE IF NOT EXISTS `SKRSColumns` (
  `idSKRSColumns` BIGINT(15) NOT NULL AUTO_INCREMENT ,
  `viewMap` BIGINT(15) NOT NULL ,
  `isPID` TINYINT NOT NULL ,
  `tableColumnName` VARCHAR(255) NOT NULL ,
  `feedColumnName` VARCHAR(255) NULL ,
  `feedPosition` INT NOT NULL ,
  `dataType` INT NOT NULL ,
  `maxLength` INT NULL ,
  PRIMARY KEY (`idSKRSColumns`) ,
  INDEX `viewMap_idx` (`viewMap` ASC) ,
  UNIQUE INDEX `viewColumn` (`tableColumnName` ASC, `viewMap` ASC) ,
  CONSTRAINT `viewMap`
  FOREIGN KEY (`viewMap` )
  REFERENCES `SKRSViewMapping` (`idSKRSViewMapping` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;

-- ---------------------------------------------------------------------------------------------------------------------
-- CPR
-- ---------------------------------------------------------------------------------------------------------------------

INSERT IGNORE INTO SKRSViewMapping (register, datatype, version, tableName, createdDate)
  VALUES ('cpr', 'barnrelation', 1, 'BarnRelation', NOW());
INSERT IGNORE INTO SKRSColumns (viewMap, isPID, tableColumnName, feedColumnName, feedPosition, dataType, maxLength) VALUES
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='barnrelation' AND version=1), 1, 'BarnRelationPID',  NULL,                  0,  -5, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='barnrelation' AND version=1), 0, 'Id',               'id',                  1,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='barnrelation' AND version=1), 0, 'CPR',              'cpr',                 2,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='barnrelation' AND version=1), 0, 'BarnCPR',          'barnCPR',             3,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='barnrelation' AND version=1), 0, 'ModifiedDate',      NULL,                 0,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='barnrelation' AND version=1), 0, 'ValidFrom',        'validFrom',           4,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='barnrelation' AND version=1), 0, 'ValidTo',          'validTo',             5,  93, NULL);

INSERT IGNORE INTO SKRSViewMapping (register, datatype, version, tableName, createdDate)
  VALUES ('cpr', 'foraeldremyndighedrelation', 1, 'ForaeldreMyndighedRelation', NOW());
INSERT IGNORE INTO SKRSColumns (viewMap, isPID, tableColumnName, feedColumnName, feedPosition, dataType, maxLength) VALUES
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='foraeldremyndighedrelation' AND version=1), 1, 'ForaeldreMyndighedRelationPID', NULL,    0,  -5, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='foraeldremyndighedrelation' AND version=1), 0, 'Id',                            'id',    1,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='foraeldremyndighedrelation' AND version=1), 0, 'CPR',                          'cpr',    2,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='foraeldremyndighedrelation' AND version=1), 0, 'TypeKode',                'typeKode',    3,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='foraeldremyndighedrelation' AND version=1), 0, 'TypeTekst',              'typeTekst',    4,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='foraeldremyndighedrelation' AND version=1), 0, 'RelationCpr',          'relationCpr',    5,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='foraeldremyndighedrelation' AND version=1), 0, 'ModifiedDate',                  NULL,    0,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='foraeldremyndighedrelation' AND version=1), 0, 'ValidFrom',              'validFrom',    6,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='foraeldremyndighedrelation' AND version=1), 0, 'ValidTo',                  'validTo',    7,  93, NULL);

INSERT IGNORE INTO SKRSViewMapping (register, datatype, version, tableName, createdDate)
  VALUES ('cpr', 'person', 1, 'Person', NOW());
INSERT IGNORE INTO SKRSColumns (viewMap, isPID, tableColumnName, feedColumnName, feedPosition, dataType, maxLength) VALUES
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 1, 'PersonPID',                                         NULL,  0,  -5, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'CPR',                                              'cpr',  1,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Koen',                                            'koen',  2,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Fornavn',                                      'fornavn',  3,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Mellemnavn',                                'mellemnavn',  4,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Efternavn',                                  'efternavn',  5,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'CoNavn',                                        'coNavn',  6,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Lokalitet',                                  'lokalitet',  7,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Vejnavn',                                      'vejnavn',  8,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Bygningsnummer',                        'bygningsnummer',  9,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Husnummer',                                  'husnummer', 10,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Etage',                                          'etage', 11,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'SideDoerNummer',                        'sideDoerNummer', 12,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Bynavn',                                        'bynavn', 13,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Postnummer',                                'postnummer', 14,  4,  NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'PostDistrikt',                            'postdistrikt', 15,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Status',                                        'status', 16,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'GaeldendeCPR',                            'gaeldendeCPR', 17,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'foedselsdato',                            'foedselsdato', 18,  91, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'Stilling',                                    'stilling', 19,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'VejKode',                                      'vejKode', 20,  4,  NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'KommuneKode',                              'kommuneKode', 21,  4,  NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'NavneBeskyttelseSletteDato','navnebeskyttelseslettedato', 22,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'NavneBeskyttelseStartDato',  'navnebeskyttelsestartdato', 23,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'ModifiedDate',                                      NULL,  0,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'ValidFrom',                                  'validFrom', 24,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='person' AND version=1), 0, 'ValidTo',                                      'validTo', 25,  93, NULL);

INSERT IGNORE INTO SKRSViewMapping (register, datatype, version, tableName, createdDate)
  VALUES ('cpr', 'umyndiggoerelsevaergerelation', 1, 'UmyndiggoerelseVaergeRelation', NOW());
INSERT IGNORE INTO SKRSColumns (viewMap, isPID, tableColumnName, feedColumnName, feedPosition, dataType, maxLength) VALUES
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 1, 'UmyndiggoerelseVaergeRelationPID',  NULL, 0,  -5, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'Id',                                    'id', 1,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'CPR',                                  'cpr', 2,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'TypeKode',                        'typeKode', 3,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'TypeTekst',                      'typeTekst', 4,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'RelationCpr',                  'relationCpr', 5,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'RelationCprStartDato','relationCprStartDato', 6,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'VaergesNavn',                  'vaergesNavn', 7,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'VaergesNavnStartDato','vaergesNavnStartDato', 8,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'RelationsTekst1',          'relationsTekst1', 9,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'RelationsTekst2',          'relationsTekst2',10,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'RelationsTekst3',          'relationsTekst3',11,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'RelationsTekst4',          'relationsTekst4',12,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'RelationsTekst5',          'relationsTekst5',13,  12, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'ModifiedDate',                          NULL, 0,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'ValidFrom',                      'validFrom',14,  93, NULL),
((SELECT idSKRSViewMapping FROM SKRSViewMapping WHERE register='cpr' AND datatype='umyndiggoerelsevaergerelation' AND version=1), 0, 'ValidTo',                          'validTo',15,  93, NULL);
