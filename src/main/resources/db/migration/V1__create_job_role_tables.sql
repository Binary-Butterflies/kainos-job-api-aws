CREATE TABLE capability (
    capabilityId INT auto_increment,
    capabilityName VARCHAR(128) NOT NULL,
    CONSTRAINT pk_capability PRIMARY KEY (capabilityId)
);

CREATE TABLE band (
    bandId INT auto_increment,
    bandName VARCHAR(128) NOT NULL,
    CONSTRAINT pk_band PRIMARY KEY (bandId)
);

CREATE TABLE job_roles (
    jobRoleId INT auto_increment,
    roleName VARCHAR(128) NOT NULL,
    location VARCHAR(128) NOT NULL,
    capabilityId INT,
    bandId INT,
    closingDate DATETIME NOT NULL,
    CONSTRAINT pk_job_roles PRIMARY KEY (jobRoleId),
    CONSTRAINT fk_capabilityId FOREIGN KEY (capabilityId) REFERENCES capability(capabilityId),
    CONSTRAINT fk_bandId FOREIGN KEY (bandId) REFERENCES band(bandId)
);