DROP DATABASE bug_tracker_db;
DROP USER 'bug_track_admin'@'localhost';
CREATE DATABASE bug_tracker_db CHARACTER SET utf8 COLLATE utf8_general_ci;
FLUSH PRIVILEGES;
CREATE USER 'bug_track_admin'@'localhost' IDENTIFIED BY PASSWORD '*D0B4CA4B17B859FCB3417D189EEA403E8212C60C';
GRANT ALL ON bug_tracker_db.* TO 'bug_track_admin'@'localhost';
USE bug_tracker_db;
#SELECT PASSWORD('bugapp2017');

CREATE TABLE Users (
	user_id INT(11) AUTO_INCREMENT NOT NULL ,
	username VARCHAR(45) NOT NULL,
	password VARCHAR(45) NOT NULL,
	first_name VARCHAR(40),
	last_name VARCHAR(40),
	email varchar(60) NOT NULL,
	PRIMARY KEY (`user_id`),
	UNIQUE KEY `username_UNIQUE` (`username`)
);
INSERT INTO Users (`user_id`,`username`,`password`,`first_name`,`last_name`,`email`) VALUES (100101,'admin','admin','kiruba','lakshmi','admin@le.ac.uk');
INSERT INTO Users (`username`,`password`,`first_name`,`last_name`,`email`) VALUES ('YasinJassat','tester','Yasin','Jassat','Yasin@le.ac.uk');
INSERT INTO Users (`username`,`password`,`first_name`,`last_name`,`email`) VALUES ('ElliotAlderson','developer','Elliot','Alderson','Elliot@le.ac.uk');
INSERT INTO Users (`username`,`password`,`first_name`,`last_name`,`email`) VALUES ('AngelaMoss','developer','Angela','Moss','Angela@le.ac.uk');
INSERT INTO Users (`username`,`password`,`first_name`,`last_name`,`email`) VALUES ('test','123456','test','test','test@le.ac.uk');


CREATE TABLE Project (
	project_id INT(6) AUTO_INCREMENT PRIMARY KEY NOT NULL,
	project_name VARCHAR(50) NOT NULL,
	description VARCHAR(255),
	created_by INT(11) NOT NULL,
	FOREIGN KEY (created_by) REFERENCES Users(user_id)
);
INSERT INTO Project (`project_id`,`project_name`,`description`,`created_by`) VALUES ('111','blackboard','Blackboard Learn, is a virtual learning environment and course management system developed by Blackboard Inc. ','100103');
INSERT INTO Project (`project_id`,`project_name`,`description`,`created_by`) VALUES ('222','MYUOL','Mobile app for university student and staff','100104');

CREATE TABLE Bug (
	bug_id VARCHAR(8) PRIMARY KEY NOT NULL,
	location VARCHAR(50) NOT NULL,
	status ENUM('Open', 'Close', 'Hold') NOT NULL,
	bug_type VARCHAR(20) NOT NULL,
	serverity varchar(10) NOT NULL,
	bug_description VARCHAR(1000),
	found_by INT(11) NOT NULL,
    version_id varchar(6) DEFAULT NULL,
	date_found TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	assigned_to INT(11) NOT NULL,
	project INT(6) NOT NULL,
	FOREIGN KEY (found_by) REFERENCES Users(user_id),
	FOREIGN KEY (assigned_to) REFERENCES Users(user_id),
	FOREIGN KEY (project) REFERENCES Project(project_id)
);

INSERT INTO  Bug (`bug_id`,`location`,`status`,`bug_type`,`serverity`,`bug_description`,`found_by`,`version_id`,`date_found`,`assigned_to`,`project`)
VALUES ('BUG004','Admin','Close','Performance','Low','There may need to be a maximum limit set on the file size upload of images.','100102','v1.0.0',DEFAULT,'100103','111');
INSERT INTO  Bug (`bug_id`,`location`,`status`,`bug_type`,`serverity`,`bug_description`,`found_by`,`version_id`,`date_found`,`assigned_to`,`project`)
VALUES ('BUG005','Homepage','Hold','Content','high','Links on the front page do not link anywhere - all JavaScript void','100102','v1.0.2',DEFAULT,'100104','222');
INSERT INTO  Bug (`bug_id`,`location`,`status`,`bug_type`,`serverity`,`bug_description`,`found_by`,`version_id`,`date_found`,`assigned_to`,`project`)
VALUES ('BUG012','Map','Open','Usability','medium','The hotspots on the map are clickable, but seemed to be binded to the drag event also - dragging causes a pop up. Hot spots do not change the mouse cursor to a pointer also?','100102','v1.0.2',DEFAULT,'100104','222');
INSERT INTO  Bug (`bug_id`,`location`,`status`,`bug_type`,`serverity`,`bug_description`,`found_by`,`version_id`,`date_found`,`assigned_to`,`project`)
VALUES ('BUG0016','General','Open','Security','Medium','Try to encrypt the database connection string if possibe - regiis it on deployment','100102','v1.0.0',DEFAULT,'100103','111');


 
CREATE TABLE BugComments (
    id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Notes text NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	created_by INT(11) NOT NULL,
    bug_id VARCHAR(8) NOT NULL,
	FOREIGN KEY (bug_id) REFERENCES Bug(bug_id),
	FOREIGN KEY (created_by) REFERENCES Users(user_id)
);

INSERT INTO BugComments (`id`,`Notes`,`created`,`created_by`,`bug_id`) VALUES (01,'finished',DEFAULT,'100102','BUG004');
INSERT INTO BugComments (`Notes`,`created`,`created_by`,`bug_id`) VALUES ('waiting for approval',DEFAULT,'100102','BUG005');
INSERT INTO BugComments (`Notes`,`created`,`created_by`,`bug_id`) VALUES ('in rpogress',DEFAULT,'100102','BUG012');

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  user_id int(11) DEFAULT NULL,
  role_id int(11) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user_roles (`user_role_id`,`username`,`role`,`user_id`,`role_id`) VALUES (1,'admin','ROLE_ADMIN','100101','101');
INSERT INTO user_roles (`user_role_id`,`username`,`role`,`user_id`,`role_id`) VALUES (2,'YasinJassat','ROLE_USER','100102','102');
INSERT INTO user_roles (`user_role_id`,`username`,`role`,`user_id`,`role_id`) VALUES (3,'ElliotAlderson','ROLE_USER','100103','103');
INSERT INTO user_roles (`user_role_id`,`username`,`role`,`user_id`,`role_id`) VALUES (4,'AngelaMoss','ROLE_USER','100104','104');
INSERT INTO user_roles (`user_role_id`,`username`,`role`,`user_id`,`role_id`) VALUES (5,'test','ROLE_USER','100105','105');
