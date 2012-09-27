CREATE TABLE PakningskombinationerUdenPriser (
    PakningskombinationerUdenPriserPID BIGINT(15) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    
    VarenummerOrdineret BIGINT(12) NOT NULL, -- ID Column
    VarenummerSubstitueret BIGINT(12),
    VarenummerAlternativt BIGINT(12),
    AntalPakninger BIGINT(12),
    InformationspligtMarkering VARCHAR(1),

    CreatedDate DATETIME NOT NULL,
    ModifiedDate DATETIME NOT NULL,
    ValidFrom DATETIME NOT NULL,
    ValidTo DATETIME NOT NULL,
    
    INDEX (PakningskombinationerUdenPriserPID, ModifiedDate),
    INDEX (VarenummerOrdineret, ValidTo, ValidFrom)
) ENGINE=InnoDB COLLATE=utf8_bin;
