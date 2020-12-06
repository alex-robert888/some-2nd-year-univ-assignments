
GO
	USE UsersAndGroups
GO

-- Delete operation procedure
GO
ALTER PROCEDURE delete_from_table
@table_id int,
@no_of_rows int,
@position int
AS
BEGIN
	-- Extract table name given the table id
	DECLARE @table_name nvarchar(MAX);
	SET @table_name = (
		SELECT Name 
		FROM Tables
		WHERE TableID = @table_id
	)

	-- Execute delte from @table_name
	PRINT 'Delete from table with name ' + @table_name;
	DECLARE @sqlDeleteStatement nvarchar(MAX);
	SET @sqlDeleteStatement = 'DELETE FROM ' + @table_name;
	EXECUTE sp_executesql @sqlDeleteStatement;
END

exec test_everything 

-- Insert operation into table with 2 primary keys
GO
ALTER PROCEDURE insert_into_2_pks_table
@table_name varchar(100),
@no_of_rows int
AS
BEGIN
	DECLARE @iterator int;
	DECLARE @sqlInsertStatement nvarchar(MAX);
	DECLARE @user_id int;
	SET @user_id = (
		SELECT TOP 1 id from Users
	)

	DECLARE @group_id int;
	SET @group_id = (
		SELECT TOP 1 id from Groups
	)

	SET @iterator = 1;

	WHILE @iterator <= @no_of_rows
	BEGIN
		SET @sqlInsertStatement = 'INSERT INTO ' + @table_name + ' VALUES(' + CAST(@user_id AS VARCHAR) + ', ' + CAST(@group_id AS VARCHAR) + ')';
		EXEC sp_executesql @sqlInsertStatement;
		SET @iterator = @iterator + 1;
		SET @user_id = @user_id + 1;
	END
END
GO


-- Insert operation into table with 1 primary key and 1 foreign key
GO
ALTER PROCEDURE insert_into_1_pk_and_1_fk_table
@table_name varchar(100),
@no_of_rows int
AS
BEGIN
	DECLARE @iterator int;
	DECLARE @sqlInsertStatement nvarchar(MAX);
	DECLARE @user_id int;
	SET @user_id = (
		SELECT TOP 1 id from Users
	)
	SET @iterator = 1;
	DECLARE @group_name nvarchar(MAX);
	WHILE @iterator <= @no_of_rows
	BEGIN
		SET @group_name = 'group' + CAST(@iterator AS VARCHAR);
		SET @sqlInsertStatement = 'INSERT INTO ' + @table_name + ' VALUES(''' + @group_name + ''', ' + CAST(@user_id AS VARCHAR) + ')';
		EXEC sp_executesql @sqlInsertStatement;
		SET @iterator = @iterator + 1;
	END
END
GO

-- Insert operation into table with 1 primary key
GO
ALTER PROCEDURE insert_into_1_pk_table
@table_name varchar(100),
@no_of_rows int
AS
BEGIN
	DECLARE @iterator int;
	DECLARE @sqlInsertStatement nvarchar(MAX);
	DECLARE @fullName nvarchar(MAX);
	DECLARE @email nvarchar(MAX);
	SET @iterator = 1;
	WHILE @iterator <= @no_of_rows
	BEGIN
		SET @fullName = N'FullName' + CAST(@iterator AS nvarchar(MAX));
		SET @email = N'Email' + CAST(@iterator AS nvarchar(MAX));
		SET @sqlInsertStatement = 'INSERT INTO ' + @table_name + ' VALUES(''' + @fullName + ''', ''' + @email + ''')';
		EXEC sp_executesql @sqlInsertStatement;
		SET @iterator = @iterator + 1;
	END
END
GO

exec test_everything

-- Insert operation procedure
GO
ALTER PROCEDURE insert_into_table
@table_id int,
@no_of_rows int,
@position int
AS
BEGIN
	-- Extract table name given the table id
	DECLARE @table_name nvarchar(MAX)
	SET @table_name = (
		SELECT Name 
		FROM Tables
		WHERE TableID = @table_id
	)

	-- Execute insert into @table_name
	PRINT 'Insert into table with name ' + @table_name;
	DECLARE @sqlDeleteStatement nvarchar(MAX)
	IF @table_name = 'Users'
	BEGIN
		exec insert_into_1_pk_table @table_name, @no_of_rows
		RETURN
	END
	IF @table_name = 'Groups'
	BEGIN
		exec insert_into_1_pk_and_1_fk_table @table_name, @no_of_rows
		RETURN
	END
	IF @table_name = 'GroupMemberships'
	BEGIN
		exec insert_into_2_pks_table @table_name, @no_of_rows
		RETURN
	END

	PRINT('Incorrect table name')
END

-- Select	operation procedure
GO
ALTER PROCEDURE select_from_view
@view_id int
AS
BEGIN
	-- Extract table name given the table id
	DECLARE @view_name nvarchar(MAX);
	SET @view_name = (
		SELECT Name 
		FROM Views
		WHERE ViewID = @view_id
	)

	-- Execute insert into @table_name
	PRINT 'Select from view with name ' + @view_name;
	DECLARE @sqlSelectStatement nvarchar(MAX)
	SET @sqlSelectStatement = 'SELECT * FROM ' + @view_name;
	EXEC sp_executesql @sqlSelectStatement;
END

-- Wrapper procedure for all tests
GO
ALTER PROCEDURE test_everything 
AS
BEGIN
	DECLARE @datetime_start_di DATETIME
	DECLARE @datetime_finish_di_and_start_s DATETIME
	DECLARE @datetime_finish_s DATETIME
	-- Step 1: Perform delete operations on tables

	SET @datetime_start_di = GETDATE();
	-- Declare a cursor that selects all the delete tests ordered by the position field
	DECLARE CursorDeleteTests CURSOR LOCAL FOR 
	SELECT TableID, NoOfRows, Position
	FROM TestTables
	WHERE TestID = 1
	ORDER BY Position;

	-- Fetch all records the cursor selected
	OPEN CursorDeleteTests;
	WHILE 1 = 1
	BEGIN
		-- Fetch next record
		DECLARE @table_id INT;
		DECLARE @no_of_rows INT;
		DECLARE @position INT;
		FETCH NEXT FROM CursorDeleteTests
		INTO @table_id, @no_of_rows, @position;

		PRINT @table_id;
		-- If no more records, break the while loop
		IF @@FETCH_STATUS != 0
		BEGIN
			BREAK;
		END

		-- Perform the delete operation on the current table
		PRINT 'Perform delete test on table with id: ' + CAST(@table_id AS VARCHAR) + ', number of rows: ' + CAST(@no_of_rows AS VARCHAR) + ', position: ' + CAST(@position AS VARCHAR);
		EXEC delete_from_table @table_id, @no_of_rows, @position;
	END

	------ Step 2: Perform insert operations on tables
	DECLARE CursorInsertTests CURSOR LOCAL FOR 
	SELECT TableID, NoOfRows, Position
	FROM TestTables
	WHERE TestID = 2
	ORDER BY Position;

	-- Fetch all records the cursor selected
	OPEN CursorInsertTests;
	WHILE 1 = 1
	BEGIN
		-- Fetch next record
		FETCH NEXT FROM CursorInsertTests
		INTO @table_id, @no_of_rows, @position;

		-- If no more records, break the while loop
		IF @@FETCH_STATUS != 0
		BEGIN
			BREAK;
		END

		-- Perform the delete operation on the current table
		PRINT 'Perform insert test on table with id: ' + CAST(@table_id AS VARCHAR) + ', number of rows: ' + CAST(@no_of_rows AS VARCHAR) + ', position: ' + CAST(@position AS VARCHAR);
		EXEC insert_into_table @table_id, @no_of_rows, @position;
	END

	SET @datetime_finish_di_and_start_s = GETDATE();
	-- Step 3: Perform select operations on views
	DECLARE CursorSelectTests CURSOR LOCAL FOR 
	SELECT ViewID
	FROM TestViews
	WHERE TestID = 3
	
	-- Fetch all records the cursor selected
	DECLARE @view_id int;
	OPEN CursorSelectTests;
	WHILE 1 = 1
	BEGIN
		-- Fetch next record
		FETCH NEXT FROM CursorSelectTests
		INTO @view_id;

		-- If no more records, break the while loop
		IF @@FETCH_STATUS != 0
		BEGIN
			BREAK;
		END

		-- Perform the delete operation on the current table
		PRINT 'Perform select test on view with id: ' + CAST(@view_id AS VARCHAR);
		EXEC select_from_view @view_id;
	END

	SET @datetime_finish_s = GETDATE();

	-- Step 4: Register time results in the according tables
	INSERT INTO TestRuns VALUES('', @datetime_start_di, @datetime_finish_s);

	DECLARE @test_runs_max_id int;
	SET @test_runs_max_id = (	
		SELECT Max(TestRunID) 
		FROM TestRuns
	)

	INSERT INTO TestRunTables VALUES (@test_runs_max_id, 1, @datetime_start_di, @datetime_finish_di_and_start_s);
	INSERT INTO TestRunViews VALUES (@test_runs_max_id, 1, @datetime_finish_di_and_start_s, @datetime_finish_s);
END


exec test_everything 

INSERT INTO Users VALUES ('asd', 'asd')

SELECT * FROM Users
SELECT * FROM Groups
SELECT * FROM GroupMemberships
SELECT * FROM TestRuns
SELECT * FROM TestTables
SELECT * FROM TestRunTables
SELECT * FROM TestRunViews

SELECT * FROM View_3

DELETE FROM TestRuns;
DELETE FROM TestRunTables;
DELETE FROM TestRunViews; 

GO	
	USE UsersAndGroups
GO