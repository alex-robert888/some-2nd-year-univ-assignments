 
GO 
	USE TaskManagerApp
GO

ALTER TABLE PersonalTasks
	ADD start_time datetime;

ALTER TABLE PersonalTasks
	ADD deadline datetime;

ALTER TABLE PersonalTasks
	ADD TaskPriority varchar(30);

ALTER TABLE PersonalTasks
	DROP COLUMN TaskPriority

ALTER TABLE ChatMessages
	ADD time_sent datetime;

ALTER TABLE ChatMessages
	ADD text TEXT;

ALTER TABLE ChatMessages
	ADD ID INT NOT NULL IDENTITY;

ALTER TABLE Users
	ADD Age INT

ALTER TABLE ChatMessages
	DROP COLUMN ID;

ALTER TABLE ChatMessages
	DROP CONSTRAINT Pk_ChatMessages;

ALTER TABLE ChatMessages
	ADD CONSTRAINT Pk_ChatMessages PRIMARY KEY(ID);

SELECT * FROM Users;
SELECT * FROM PersonalTasks;


-- First INSERT statement for Users
INSERT INTO Users VALUES ('Filip', 'Oscar', 'foscar@yahoo.com', 'foscar', 'fopass444'), ('Alex', 'Robert', 'arobert@gmail.com', 'arobert', 'arpass123'), 
						 ('Ana', 'Maria', 'amaria@yahoo.com', 'amaria', 'ampass890'), ('Florin', 'Balazs', 'fbalazs@hotmail.com', 'fbalazs', 'fbpass234'),
						 ('Elena', 'Alexandra', 'ealexandra@gmail.com', 'ealexandra', 'eapass777'), ('Dan', 'Petrescu', 'dpetrescu@gmail.com', 'dpetrescu', 'dppass010');
INSERT INTO Users VALUES ('Andreea', 'Zamfir', 'azamfir@yahoo.com', 'azamfir', 'azpass876');


UPDATE Users SET Age = 19 WHERE ID = 2
UPDATE Users SET Age = 22 WHERE ID = 3
UPDATE Users SET Age = 25 WHERE ID = 4
UPDATE Users SET Age = 23 WHERE ID = 5
UPDATE Users SET Age = 27 WHERE ID = 6
UPDATE Users SET Age = 22 WHERE ID = 7


-- Second INSERT statement for PersonalTasks
INSERT INTO PersonalTasks VALUES ('Finish the essay for tomorrow', '', 1, '20201018 10:00:00 AM', '20201018 11:30:00 AM'), 
								 ('Send an email to Math teacher', '', 5, '20201018 10:00:00 AM', '20201018 11:30:00 AM'), 
								 ('Visit grandma', '', 2, '20201018 01:00:00 PM', '20201018 04:00:00 PM'),
								 ('Search for an internhip online', '', 4, '20201020 09:00:00 AM', '20201020 10:00:00 AM'), 
								 ('Do the groceries', '', 2, '20201018 04:00:00 PM', '20201018 05:00:00 PM'), 
								 ('Fix the runtime errors', '', 4, '20201020 10:30:00 AM', '20201020 11:45:00 AM'),
								 ('Finish Prolog assignment', '', 2, '20201018 05:00:00 PM', '20201018 07:30:00 PM'), 
								 ('Play the piano', '', 1, '20201018 11:30:00 AM', '20201018 12:10:00 PM'), 
								 ('Feed my golden fish', '', 6, '20201020 09:00:00 AM', '20201020 09:10:00 AM'),
								 ('Study for Computer Networks test', '', 5, '20201018 11:00:00 AM', '20201018 02:00:00 PM'), 
								 ('Watch last episode of Raised by Wolves', '', 1, '20201018 01:10:00 PM', '20201018 01:00:00 PM'), 
								 ('Go swimming', '', 3, '20201018 02:00:00 PM', '20201018 03:00:00 PM'),
								 ('Make some research on socket programming', '', 1, '20201018 01:00:00 PM', '20201018 03:00:00 PM'), 
								 ('Listen to SPI Podcast', '', 5, '20201018 02:00:00 PM', '20201018 03:00:00 PM'), 
								 ('Help my brother with his HW', '', 4, 'ChatRoomMemberships', '20201020 12:45:00 PM');

SELECT * FROM  ChatRoomMemberships;
SELECT * FROM  ChatMessages;


-- Third INSERT statement for ChatRooms
INSERT INTO ChatRooms VALUES (3), (2)

-- Fourth INSERT statement for ChatRoomMemberships
INSERT INTO ChatRoomMemberships VALUES ('20201018 02:01:24 PM', 1, 1), ('20201018 02:04:01 PM', 1, 2), ('20201018 02:04:55 PM', 1, 3),
									   ('20201023 08:22:01 PM', 2, 2), ('20201023 08:22:01 PM', 2, 4)	

-- Fifth INSERT statement for ChatRoomMemberships (with a referential integrity constraints violation, due to an invalid CharRoom ID)
INSERT INTO ChatRoomMemberships VALUES ('20201023 08:22:01 PM', 10, 2);

-- Sixth INSERT statement for ChatMessages 
INSERT INTO ChatMessages VALUES (1, 1, '20201023 09:22:01 PM', 'Welcome to the chat!'), (1, 3, '20201023 09:22:10 PM', 'Great to join this chat!'), (1, 2, '20201023 09:23:44 PM', 'Hi! ^^'), (1, 2, '20201023 09:23:50 PM', 'What is up?'),
								(2, 2, '20201024 11:57:19 PM', 'Could you help me with something?'), (2, 1, '20201024 11:59:59 PM', 'Yeah, surew');

-- First delete statement with OR
DELETE FROM PersonalTasks 
	WHERE ID=2 OR ID=3;

-- Second delete statement with >=
DELETE FROM ChatMessages
	WHERE UserID >= 3

SELECT * FROM  PersonalTasks;

-- First update statement with IS NULL, OR, BETWEEN
UPDATE PersonalTasks
	SET Summary = 'No Summary.'
	WHERE (CONVERT(VARCHAR, Summary) = '' OR CONVERT(VARCHAR, Summary) IS NULL) AND ID BETWEEN 1 AND 13

-- Second update statement with Like
UPDATE ChatMessages
	SET text = 'Hello!'
	WHERE text LIKE 'Hi%'

-- Second update statement with AND, IN
UPDATE PersonalTasks
	SET Summary = 'No Summary.'
	WHERE CONVERT(VARCHAR, Summary) = '' AND ID IN (14, 15, 16, 17, 18)



-- a. 2 queries with the union operation; use UNION [ALL] and OR
SELECT * FROM Users;

-- Select with OR
SELECT TOP 3 * FROM Users	
	WHERE FirstName LIKE 'A%' OR LastName LIKE 'A%'


-- Select with UNION
SELECT DISTINCT FirstName FROM Users
	WHERE FirstName LIKE 'A%'
UNION
SELECT DISTINCT LastName FROM Users
	WHERE LastName LIKE 'A%'

-- b. 2 queries with the intersection operation; use INTERSECT and IN;

-- INTERSECT
SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1
UNION
SELECT ID FROM Users
	WHERE LastName LIKE 'A%' OR FirstName LIKE 'A%'
INTERSECT 
SELECT UserID FROM PersonalTasks

--IN
SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1  AND UserID IN (SELECT UserID FROM PersonalTasks)

-- c. 2 queries with the difference operation; use EXCEPT and NOT IN;
-- EXCEPT
SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1
EXCEPT 
SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 2
	
-- NOT IN
SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1
	AND UserID NOT IN (
		SELECT UserID FROM ChatRoomMemberships
		WHERE ChatRoomID = 2
	)

-- d. 4 queries with INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN (one query per operator); one query will join at least 3 tables, 
-- while another one will join at least two many-to-many relationships;

SELECT * FROM Users;
SELECT * FROM ChatRoomMemberships
SELECT * FROM ChatMessages;

--SELECT Users.ID, COUNT(ChatRoomMemberships.UserID), COUNT(ChatMessages.UserID)
--FROM ChatRoomMemberships
--INNER JOIN Users ON ChatMessages.UserID = Users.ID
--GROUP BY Users.ID


-- INNER JOIN WITH 3 tables  
SELECT ChatMessages.text, ChatRooms.NumberOfMembers, Users.Username 
FROM ChatMessages
INNER JOIN ChatRooms ON ChatMessages.ChatRoomID = ChatRooms.ID
INNER JOIN Users ON ChatMessages.UserID = UserID
ORDER BY Users.Username


SELECT * FROM Users;
SELECT * FROM PersonalTasks;

--RIGHT JOIN 
SELECT Users.username, PersonalTasks.ID
FROM PersonalTasks
RIGHT JOIN Users on PersonalTasks.UserID = Users.ID
ORDER BY Users.username

-- LEFT JOIN
SELECT AVG(Users.Age), ChatRoomMemberships.ChatRoomID
	FROM Users
	LEFT JOIN ChatRoomMemberships ON Users.ID= ChatRoomMemberships.UserID 
	GROUP BY ChatRoomMemberships.ChatRoomID

-- FULL JOIN
SELECT ChatMessages.text, ChatRooms.NumberOfMembers, Users.Username 
FROM ChatMessages
FULL JOIN ChatRooms ON ChatMessages.ChatRoomID = ChatRooms.ID
FULL JOIN Users ON ChatMessages.UserID = UserID
ORDER BY Users.Username

-- e. WHERE AND IN (Querry) 
SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1  AND UserID IN (SELECT UserID FROM PersonalTasks)

-- WHERE AND IN 
SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1  AND UserID IN (1, 3, 5)

-- f. 2 queries using the EXISTS operator to introduce a subquery in the WHERE clause;

-- EXISTS 1 - Users that have a chat room memberships
SELECT username, ID FROM Users
	WHERE EXISTS(
		SELECT * FROM ChatRoomMemberships
		WHERE ID = ChatRoomMemberships.UserID AND ChatRoomMemberships.ChatRoomID IN(1, 2)
	)

-- EXISTS 2
SELECT username, ID FROM Users
	WHERE EXISTS(
		SELECT * FROM PersonalTasks
		WHERE Users.ID = PersonalTasks.UserID 
	)
	ORDER BY username

-- g. 2 queries with a subquery in the FROM clause;SELECT DISTINCT personal_tasks.Title 
	FROM (
		SELECT p.Title, Users.Username FROM PersonalTasks p
		INNER JOIN Users ON p.UserID = Users.ID
		WHERE Users.Username
		LIKE 'a%'
	) personal_tasks

SELECT users.Email
	FROM (
		SELECT *
		FROM Users
		WHERE Users.Email LIKE '%gmail.com' OR Users.Email LIKE '%hotmail.com'
	) users

--h. 4 queries with the GROUP BY clause, 3 of which also contain the HAVING clause; 2 of the latter will also have a subquery in the HAVING clause; 
 --use the aggregation operators: COUNT, SUM, AVG, MIN, MAX;

SELECT * FROM ChatRoomMemberships

-- 1st querry - AVG of ages in a chat room
SELECT AVG(Users.Age), ChatRoomMemberships.ChatRoomID
	FROM Users
	LEFT JOIN ChatRoomMemberships ON Users.ID= ChatRoomMemberships.UserID 
	GROUP BY ChatRoomMemberships.ChatRoomID

-- 2nd querry - MIN of ages in each chat room, having users.id >= 3
SELECT MIN(Users.Age), ChatRoomMemberships.ChatRoomID
	FROM Users
	FULL JOIN ChatRoomMemberships ON Users.ID= ChatRoomMemberships.UserID 
	GROUP BY ChatRoomMemberships.ChatRoomID
	HAVING MIN(Users.Age) >= 18

-- 3rd querry - MAX	
SELECT MAX(Users.Age), ChatRoomMemberships.ChatRoomID
	FROM Users
	FULL JOIN ChatRoomMemberships ON Users.ID= ChatRoomMemberships.UserID 
	GROUP BY ChatRoomMemberships.ChatRoomID
	HAVING MAX(Users.Age) > (
		SELECT AVG(Users.Age) 
		FROM Users
	)

SELECT * FROM ChatMessages

-- 4th querry - count users that have more than 2 messages
SELECT AVG(Users.Age), ChatRoomMemberships.ChatRoomID
	FROM Users
	INNER JOIN ChatRoomMemberships ON Users.ID= ChatRoomMemberships.UserID 
	GROUP BY ChatRoomMemberships.ChatRoomID
	HAVING AVG(Users.Age) > (
		SELECT AVG(Users.Age) 
		FROM Users
		WHERE Users.Email LIKE '%@yahoo.com'
	)

-- 4 queries using ANY and ALL to introduce a subquery in the WHERE clause; rewrite 2 of them with aggregation operators, and the other 2 with [NOT] IN.

SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1
	AND UserID <>ALL (
		SELECT UserID FROM ChatRoomMemberships
		WHERE ChatRoomID = 2
	)

SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1
	AND UserID NOT IN (
		SELECT UserID FROM ChatRoomMemberships
		WHERE ChatRoomID = 2
	)

SELECT SUM(Users.Age) / COUNT(Users.Age), ChatRoomMemberships.ChatRoomID
	FROM Users
	LEFT JOIN ChatRoomMemberships ON Users.ID= ChatRoomMemberships.UserID 
	GROUP BY ChatRoomMemberships.ChatRoomID
	HAVING ChatRoomMemberships.ChatRoomID <>ALL (
		SELECT ChatRoomID FROM ChatRoomMemberships
		WHERE ChatRoomID IN (1)
	)

SELECT AVG(Users.Age)  / COUNT(Users.Age), ChatRoomMemberships.ChatRoomID
	FROM Users
	LEFT JOIN ChatRoomMemberships ON Users.ID= ChatRoomMemberships.UserID 
	GROUP BY ChatRoomMemberships.ChatRoomID
	HAVING ChatRoomMemberships.ChatRoomID NOT IN (
		SELECT ChatRoomID FROM ChatRoomMemberships
		WHERE ChatRoomID IN (1, 2)
	)

SELECT TOP 2 UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1  AND UserID = ANY(SELECT UserID FROM PersonalTasks)

SELECT UserID FROM ChatRoomMemberships
	WHERE ChatRoomID = 1  AND UserID IN (SELECT UserID FROM PersonalTasks)

SELECT AVG(Users.Age), ChatRoomMemberships.ChatRoomID
	FROM Users
	LEFT JOIN ChatRoomMemberships ON Users.ID= ChatRoomMemberships.UserID 
	GROUP BY ChatRoomMemberships.ChatRoomID
	HAVING ChatRoomMemberships.ChatRoomID = ANY (
		SELECT ChatRoomID FROM ChatRoomMemberships
		WHERE ChatRoomID IN (1, 2)
	)

SELECT AVG(Users.Age), ChatRoomMemberships.ChatRoomID
	FROM Users
	LEFT JOIN ChatRoomMemberships ON Users.ID= ChatRoomMemberships.UserID 
	GROUP BY ChatRoomMemberships.ChatRoomID
	HAVING ChatRoomMemberships.ChatRoomID IN (
		SELECT ChatRoomID FROM ChatRoomMemberships
		WHERE ChatRoomID IN (1, 2)
	)



