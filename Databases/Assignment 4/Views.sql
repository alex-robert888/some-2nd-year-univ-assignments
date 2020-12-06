
	CREATE VIEW View_1 AS
		SELECT * FROM PersonalTasks
	GO

	CREATE VIEW View_2 AS
		SELECT Count(*) as count_tasks 
		FROM PersonalTasks INNER JOIN Users ON PersonalTasks.UserID=Users.ID
		GROUP BY Users.Username
	GO

	CREATE VIEW View_3 AS
		SELECT ChatRoomMemberships.ChatRoomID, ChatRoomMemberships.UserID, Users.Username
		FROM ChatRoomMemberships INNER JOIN Users ON ChatRoomMemberships.UserID=Users.ID
	GO

	DROP VIEW View_3

	SELECT * FROM View_1
	SELECT * FROM View_2
	SELECT * FROM View_3

