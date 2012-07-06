-- Start of user code DB Name
-- TODO specify the name of database used
USE sample;
-- End of user code

-- Tables for Entity Interface 

DROP TABLE IF EXISTS INTERFACE;
CREATE TABLE INTERFACE
(
	ID CHAR(32) NOT NULL,
	PRIMARY KEY (ID)
);

-- Tables for Entity Class 

DROP TABLE IF EXISTS CLASS;
CREATE TABLE CLASS
(
	ID CHAR(32) NOT NULL,
	PRIMARY KEY (ID)
);

-- Tables for Entity Bad name 

DROP TABLE IF EXISTS BADNAME;
CREATE TABLE BADNAME
(
	ID CHAR(32) NOT NULL,
	PRIMARY KEY (ID)
);

-- Tables for Entity Another.name 

DROP TABLE IF EXISTS ANOTHERNAME;
CREATE TABLE ANOTHERNAME
(
	ID CHAR(32) NOT NULL,
	PRIMARY KEY (ID)
);

-- Tables for Entity entityWithSp�cialChars 

DROP TABLE IF EXISTS ENTITY_WITH_SPECIAL_CHARS;
CREATE TABLE ENTITY_WITH_SPECIAL_CHARS
(
	ID CHAR(32) NOT NULL,
	PRIMARY KEY (ID)
);

-- Tables for Entity public 

DROP TABLE IF EXISTS PUBLIC;
CREATE TABLE PUBLIC
(
	ID CHAR(32) NOT NULL,
	PRIMARY KEY (ID)
);

-- Tables for Entity private 

DROP TABLE IF EXISTS PRIVATE;
CREATE TABLE PRIVATE
(
	ID CHAR(32) NOT NULL,
	PRIMARY KEY (ID)
);

-- Tables for Entity protected 

DROP TABLE IF EXISTS PROTECTED;
CREATE TABLE PROTECTED
(
	ID CHAR(32) NOT NULL,
	PRIMARY KEY (ID)
);

-- Tables for Entity null 

DROP TABLE IF EXISTS NULL1;
CREATE TABLE NULL1
(
	ID CHAR(32) NOT NULL,
	PRIMARY KEY (ID)
);

-- Tables for Entity myEntity 

DROP TABLE IF EXISTS MY_ENTITY;
CREATE TABLE MY_ENTITY
(
	ID CHAR(32) NOT NULL,
	ATTR1 INT ,
	PRIVATE INT ,
	PUBLIC INT ,
	PROTECTED VARCHAR(255) ,
	NULL1 VARCHAR(255) ,
	STRING VARCHAR(255) ,
	A DOUBLE ,
	PRIMARY KEY (ID)
);

-- Tables for Entity TABLE 

DROP TABLE IF EXISTS TABLE;
CREATE TABLE TABLE
(
	ID CHAR(32) NOT NULL,
	COLUMN INT ,
	PRIMARYKEY INT ,
	PRIMARY KEY (ID)
);

-- Tables for Entity return 

DROP TABLE IF EXISTS RETURN;
CREATE TABLE RETURN
(
	ID CHAR(32) NOT NULL,
	-- |*|-->1
	FK_CLASS_CLASS_ID CHAR(32) UNIQUE,
	PRIMARY KEY (ID)
);

