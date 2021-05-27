CREATE SCHEMA IF NOT EXISTS `four_lab_db` DEFAULT CHARACTER SET utf8;
USE `four_lab_db`;


DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS personal_data;
DROP TABLE IF EXISTS driver_data;
DROP TABLE IF EXISTS license;
DROP TABLE IF EXISTS auto;

CREATE TABLE auto
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    mark        VARCHAR(45) NOT NULL,
    auto_number VARCHAR(45) NOT NULL,
    auto_type   VARCHAR(45) NOT NULL,
    state       VARCHAR(45) NOT NULL
)
    ENGINE = InnoDB;


CREATE TABLE license
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_of_issue  VARCHAR(45) NOT NULL,
    place_of_issue VARCHAR(45) NOT NULL
)
    ENGINE = InnoDB;

CREATE TABLE driver_data
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    driving_experience FLOAT   NOT NULL,
    license_id         BIGINT  NOT NULL,
    auto_id            BIGINT  NOT NULL,
    is_booked          BOOLEAN NOT NULL
)
    ENGINE = InnoDB;
CREATE UNIQUE INDEX driver_data1 ON driver_data (license_id, auto_id);

ALTER TABLE driver_data
    ADD CONSTRAINT FK_driver_data_license_id
        FOREIGN KEY (license_id)
            REFERENCES license (id),

    ADD CONSTRAINT FK_driver_data_auto_id
        FOREIGN KEY (auto_id)
            REFERENCES auto (id);

CREATE TABLE personal_data
(
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_of_birth          VARCHAR(45) NOT NULL,
    date_of_registration VARCHAR(45) NOT NULL,
    user_password VARCHAR(45)      NOT NULL,
    rating               FLOAT       NOT NULL,
    driver_data_id BIGINT
)
    ENGINE = InnoDB;

CREATE INDEX personal_data1 ON personal_data (driver_data_id);

ALTER TABLE personal_data
    ADD CONSTRAINT FK_personal_data_driver_data_id
        FOREIGN KEY (driver_data_id)
            REFERENCES driver_data (id);

CREATE TABLE user
(
    id                     BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name             VARCHAR(45) NOT NULL,
    second_name            VARCHAR(45) NOT NULL,
    gender                 VARCHAR(45) NOT NULL,
    personal_data_id BIGINT      NOT NULL
)
    ENGINE = InnoDB;

CREATE UNIQUE  INDEX user1 ON user (personal_data_id);

ALTER TABLE user
    ADD CONSTRAINT FK_user_personal_data_id
        FOREIGN KEY (personal_data_id)
            REFERENCES personal_data (id);



INSERT INTO auto(id,mark,auto_number,auto_type,state)VALUES
(1,'HONDA','isekai11','ISEKAI','NORM'),
(2,'HONDA','isekai22','ISEKAI','LOW'),
(3,'HONDA','isekai12','ISEKAI','MEDIUM'),
(4,'HONDA','isekai46','ISEKAI','FAILL');
INSERT INTO license(id,date_of_issue,place_of_issue)VALUES
(1,'2020-10-12','Shafaruka123'),
(2,'2020-10-12','Shafaruka12'),
(3,'2020-10-12','Shafaruka3'),
(4,'2020-10-12','Shafaruka1');
INSERT INTO driver_data(id,driving_experience,license_id,auto_id,is_booked)VALUES
(1,10,1,1,true),
(2,10,2,2,false);

INSERT INTO personal_data(id,date_of_birth,date_of_registration,user_password,rating,driver_data_id)VALUES
(1, '2020-01-01','2020-01-01',"qwerty123",5.8,NULL),
(2, '2020-01-01','2020-01-01',"qwerty123",5.8,2),
(3, '2020-01-01','2020-01-01',"qwerty123",5.8,1),
(4, '2020-01-01','2020-01-01',"qwerty123",5.8,NULL);


INSERT INTO user(id,first_name, second_name, gender,personal_data_id)VALUES
(1,'Nazar', 'Tverdokhlib', 'male',1),
(2,'Nazar', 'Tverdokhlib', 'male',2),
(3,'Nazar', 'Tverdokhlib', 'male',3),
(4,'Nazar', 'Tverdokhlib', 'male',4);
