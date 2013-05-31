ALTER TABLE `Person`
ADD INDEX `ModifiedPID` (`ModifiedDate` ASC, `PersonPID` ASC) ;

ALTER TABLE `ForaeldreMyndighedRelation`
ADD INDEX `ModifiedPID` (`ModifiedDate` ASC, `ForaeldreMyndighedRelationPID` ASC) ;
