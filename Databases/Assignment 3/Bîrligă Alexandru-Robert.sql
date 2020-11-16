GO 
	USE TaskManagerApp
GO

CREATE TABLE Objectives (
	ID int identity,
	Title varchar(10),
	Summary varchar(200)
)

ALTER TABLE Objectives
	ADD UserID int

INSERT INTO Objectives(Title, UserID) VALUES('abc', 1)
INSERT INTO Objectives(Title, UserID) VALUES('def', 2)
INSERT INTO Objectives(Title, UserID) VALUES('ghi', 3)
INSERT INTO Objectives(Title, UserID) VALUES('jkl', 1)

INSERT INTO Objectives(Title) VALUES('abc')
DELETE FROM Objectives WHERE ID=(SELECT MAX(ID) FROM Objectives)

DELETE FROM Objectives

DROP TABLE Objectives

CREATE TABLE DBVersion (
	ID int identity,
	Version int
)

DROP TABLE DBVersion

INSERT INTO DBVersion VALUES(0)



----------- PROCEDURES
CREATE PROCEDURE do_prod_1 
	AS
	BEGIN
		ALTER TABLE Objectives
		ALTER COLUMN Title varchar(40) not null

		UPDATE DBVersion
		SET Version = 1 
	END

CREATE PROCEDURE do_prod_2
	AS
	BEGIN
		ALTER TABLE Objectives
		ADD duration int

		UPDATE DBVersion
		SET Version = 2 

	END

CREATE PROCEDURE do_prod_3
	AS
	BEGIN
		ALTER TABLE Objectives
		ADD CONSTRAINT df_Summary
		DEFAULT 'No summary..' FOR Summary;

		UPDATE DBVersion
		SET Version = 3 
	END

CREATE PROCEDURE do_prod_4
	AS
	BEGIN
		ALTER TABLE Objectives
		ADD CONSTRAINT PK_Objectives PRIMARY KEY (ID);

		UPDATE DBVersion
		SET Version = 4 
	END

CREATE PROCEDURE do_prod_5
	AS
	BEGIN
		ALTER TABLE Objectives
		ADD CONSTRAINT Uk_Objectives UNIQUE(Title)
		
		UPDATE DBVersion
		SET Version = 5 
	END

CREATE PROCEDURE do_prod_6
	AS
	BEGIN
		ALTER TABLE Objectives
		ADD CONSTRAINT Fk_Objectives 
			FOREIGN KEY (UserID) 
			REFERENCES Users(ID)

		UPDATE DBVersion
		SET Version = 6 
	END

CREATE PROCEDURE do_prod_7
	AS
	BEGIN
		CREATE TABLE TestTable (
			ID int identity,
			ABC varchar(10),
		)
		UPDATE DBVersion
		SET Version = 7 
	END

CREATE PROCEDURE undo_prod_1 
	AS
	BEGIN
		ALTER TABLE Objectives
		ALTER COLUMN Title varchar(10)

		UPDATE DBVersion
		SET Version = 0
	END

CREATE PROCEDURE undo_prod_2
	AS
	BEGIN
		ALTER TABLE Objectives
		DROP COLUMN duration

		UPDATE DBVersion
		SET Version = 1
	END

CREATE PROCEDURE undo_prod_3
	AS
	BEGIN
		ALTER TABLE Objectives
		DROP CONSTRAINT df_Summary

		UPDATE DBVersion
		SET Version = 2
	END

CREATE PROCEDURE undo_prod_4
	AS
	BEGIN
		ALTER TABLE Objectives
		DROP CONSTRAINT PK_Objectives

		UPDATE DBVersion
		SET Version = 3
	END


CREATE PROCEDURE undo_prod_5
	AS
	BEGIN
		ALTER TABLE Objectives
		DROP CONSTRAINT Uk_Objectives

		UPDATE DBVersion
		SET Version = 4
	END

CREATE PROCEDURE undo_prod_6
	AS
	BEGIN
		ALTER TABLE Objectives
		DROP CONSTRAINT Fk_Objectives

		UPDATE DBVersion
		SET Version = 5
	END

CREATE PROCEDURE undo_prod_7
	AS
	BEGIN
		DROP TABLE TestTable

		UPDATE DBVersion
		SET Version = 6
	END


DROP PROCEDURE do_prod_1
DROP PROCEDURE do_prod_2
DROP PROCEDURE do_prod_3
DROP PROCEDURE do_prod_4
DROP PROCEDURE do_prod_5
DROP PROCEDURE do_prod_6
DROP PROCEDURE do_prod_7

DROP PROCEDURE undo_prod_1
DROP PROCEDURE undo_prod_2
DROP PROCEDURE undo_prod_3
DROP PROCEDURE undo_prod_4
DROP PROCEDURE undo_prod_5
DROP PROCEDURE undo_prod_6
DROP PROCEDURE undo_prod_7

SELECT * FROM Objectives
SELECT * FROM DBVersion

EXEC do_prod_1
EXEC do_prod_2
EXEC do_prod_3
EXEC do_prod_4
EXEC do_prod_5
EXEC do_prod_6
EXEC do_prod_7

EXEC undo_prod_1
EXEC undo_prod_2
EXEC undo_prod_3
EXEC undo_prod_4
EXEC undo_prod_5
EXEC undo_prod_6
EXEC undo_prod_7


CREATE PROCEDURE main
	@newVersion int
	AS
	BEGIN
		-- Get current version
		DECLARE @oldVersion int
		SET @oldVersion = (
			SELECT TOP 1 Version from DBVersion
		)
		PRINT('old version: ')
		PRINT(@oldVersion)

		PRINT('new version: ')
		PRINT(@newVersion)

		IF @newVersion > 7
		BEGIN
			PRINT('New version must be less than or equal to 7!')
			RETURN
		END

		IF @oldVersion = @newVersion
		BEGIN
			PRINT('Same versions. Nothing to do..')
			RETURN
		END

		IF @oldVersion < @newVersion
		BEGIN
			WHILE @oldVersion < @newVersion
			BEGIN
				IF @oldVersion = 0
				BEGIN
					EXEC do_prod_1
				END

				IF @oldVersion = 1
				BEGIN
					EXEC do_prod_2
				END

				IF @oldVersion = 2
				BEGIN
					EXEC do_prod_3
				END

				IF @oldVersion = 3
				BEGIN
					EXEC do_prod_4
				END
				
				IF @oldVersion = 4
				BEGIN
					EXEC do_prod_5
				END

				IF @oldVersion = 5
				BEGIN
					EXEC do_prod_6
				END

				IF @oldVersion = 6
				BEGIN
					EXEC do_prod_7
				END

				set @oldVersion = @oldVersion + 1
			END
		END

			IF @oldVersion > @newVersion
			BEGIN
				WHILE @oldVersion > @newVersion
				BEGIN
					IF @oldVersion = 1
					BEGIN
						EXEC undo_prod_1
					END

					IF @oldVersion = 2
					BEGIN
						EXEC undo_prod_2
					END

					IF @oldVersion = 3
					BEGIN
						EXEC undo_prod_3
					END

					IF @oldVersion = 4
					BEGIN
						EXEC undo_prod_4
					END
				
					IF @oldVersion = 5
					BEGIN
						EXEC undo_prod_5
					END

					IF @oldVersion = 6
					BEGIN
						EXEC undo_prod_6
					END

					IF @oldVersion = 7
					BEGIN
						EXEC undo_prod_7
					END

					set @oldVersion = @oldVersion - 1

				END
				PRINT('Version downgraded')
			
		 END

	END

DROP PROCEDURE main

EXEC main 0

SELECT * FROM DBVersion
SELECT * FROM Objectives