
-- Create tables
CREATE TABLE Users (
	id int identity not null,
	fullname varchar(100) not null,
	email varchar(100) not null,
	
	CONSTRAINT Pk_Users PRIMARY KEY(id)
)

CREATE TABLE Groups (
	id int identity not null,
	name varchar(100) not null,
	admin_id int not null,

	CONSTRAINT Pk_Groups PRIMARY KEY(id),
	CONSTRAINT Fk_Groups FOREIGN KEY(admin_id) REFERENCES Users(id)
)

CREATE TABLE GroupMemberships (
	user_id int not null,
	group_id int not null,

	CONSTRAINT Fk_GroupMemberships_Users FOREIGN KEY(user_id) REFERENCES Users(id),
	CONSTRAINT Fk_GroupMemberships_Groups FOREIGN KEY(group_id) REFERENCES Groups(id),
	CONSTRAINT Pk_GroupMemberships PRIMARY KEY(user_id, group_id)
)

GO
	USE UsersAndGroups
GO

-- Create views - GOTTA TEST THEM
GO
CREATE VIEW View_1 AS 
	SELECT * FROM Users
GO

GO
CREATE VIEW View_2 AS 
	SELECT Users.fullname, Groups.id, Groups.name 
	FROM Groups
	INNER JOIN Users ON Groups.admin_id = Users.id

GO

GO
CREATE VIEW View_3 AS 
	SELECT COUNT(*) AS counter
	FROM GroupMemberships
	INNER JOIN Users ON GroupMemberships.user_id = Users.id
	GROUP BY Users.id
GO

---- Insert data into test tables

GO 
	USE UsersAndGroups
GO

-- 1. Insert into Tables the 3 tables to be tested
INSERT INTO Tables VALUES ('Users'), ('Groups'), ('GroupMemberships')

-- 2. Insert into Views the 3 views to be tested
INSERT INTO Views VALUES ('View_1'), ('View_2'), ('View_3');

-- 3. Insert into Tests delete, insert and view tests
INSERT INTO Tests VALUES ('delete_from_table'), ('insert_into_table'), ('select_from_table')

-- 4. Insert into TestTables the delete and insert tests for all tables
SELECT * FROM Tests
SELECT * FROM Tables
SELECT * FROM TestTables
INSERT INTO TestTables VALUES (1, 3, 500, 1), (1, 2, 500, 2), (1, 1, 500, 3) -- Delete tests
INSERT INTO TestTables VALUES (2, 1, 500, 1), (2, 2, 500, 2), (2, 3, 500, 3) -- Insert tests	

-- 5. Insert into TestView the select tests for all views
INSERT INTO TestViews VALUES (3, 1), (3, 2), (3, 3)

-- 6. Wrapper testing function in the Testing.sql querry file..

