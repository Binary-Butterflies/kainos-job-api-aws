CREATE TABLE status (
    statusId INT auto_increment,
    statusName VARCHAR(128) NOT NULL,
    CONSTRAINT pk_status PRIMARY KEY (statusId)
);

ALTER TABLE job_roles
    ADD description VARCHAR(512) NOT NULL,
    ADD responsibilities VARCHAR(512) NOT NULL,
    ADD sharepointUrl VARCHAR(512) NOT NULL,
    ADD statusId INT NOT NULL,
    ADD numberOfOpenPositions INT NOT NULL,
    ADD CONSTRAINT fk_statusId FOREIGN KEY (statusId) REFERENCES status(statusId);