-- Start of user code DB Name
-- TODO specify the name of database used
USE sample;
-- End of user code

-- Tables for Entity Types 

DROP TABLE IF EXISTS TYPES;
CREATE TABLE TYPES
(
	ID CHAR(32) NOT NULL,
	MY_BOOLEAN INT(1) NOT NULL,
	MY_DATE DATETIME NOT NULL,
	MY_DOUBLE  NOT NULL,
	MY_MAIL VARCHAR(255) NOT NULL,
	MY_FLOAT DOUBLE NOT NULL,
	MY_INTEGER INT NOT NULL,
	MY_LONG  NOT NULL,
	MY_STRING VARCHAR(255) NOT NULL,
	PRIMARY KEY (ID)
);

