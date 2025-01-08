CREATE TABLE `role` (
    roleId INT NOT NULL DEFAULT 2,
    roleName VARCHAR(32) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (roleId)
);

INSERT INTO role(roleId, roleName) VALUES (1, 'Admin');
INSERT INTO role(roleId, roleName) VALUES (2, 'User');

ALTER TABLE `user`
ADD roleId INT NOT NULL;

-- Disable safe updates temporarily in order to set roleId for all users
SET SQL_SAFE_UPDATES = 0;
UPDATE `user` SET roleId = 2;
SET SQL_SAFE_UPDATES = 1;

ALTER TABLE `user`
ADD CONSTRAINT fk_role FOREIGN KEY (roleId) REFERENCES role(roleId);