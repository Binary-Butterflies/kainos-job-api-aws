CREATE TABLE status (
    statusId INT auto_increment,
    statusName VARCHAR(128) NOT NULL,
    CONSTRAINT pk_status PRIMARY KEY (statusId)
);

INSERT INTO status(statusId, statusName) VALUES (1, "Open");
INSERT INTO status(statusId, statusName) VALUES (2, "Closed");

ALTER TABLE job_roles
ADD description VARCHAR(512) NOT NULL,
ADD responsibilities VARCHAR(512) NOT NULL,
ADD sharepointUrl VARCHAR(512) NOT NULL,
ADD statusId INT NOT NULL,
ADD numberOfOpenPositions INT NOT NULL;

-- Disable safe updates temporarily in order to set fields for existing jobs
SET SQL_SAFE_UPDATES = 0;
UPDATE `job_roles` SET description = "";
UPDATE `job_roles` SET responsibilities = "";
UPDATE `job_roles` SET sharepointUrl = "";
UPDATE `job_roles` SET statusId = 1;
UPDATE `job_roles` SET numberOfOpenPositions = 1;
SET SQL_SAFE_UPDATES = 1;

ALTER TABLE job_roles
ADD CONSTRAINT fk_statusId FOREIGN KEY (statusId) REFERENCES status(statusId);