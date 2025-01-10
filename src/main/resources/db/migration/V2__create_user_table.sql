CREATE TABLE `user` (
    email varchar(64) NOT NULL,
    passwordHash varbinary(128) NOT NULL,
    salt varbinary(16) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (email)
);