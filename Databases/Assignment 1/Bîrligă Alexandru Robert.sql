 
CREATE DATABASE TaskManagerApp

GO 
USE TaskManagerApp;
GO

DROP TABLE dbo.tblUser;


CREATE TABLE Users(
	ID int identity,
	FirstName varchar(50),
	LastName varchar(50),
	Email varchar(120),
	Username varchar(50),
	Password varchar(120),
	CONSTRAINT Pk_Users PRIMARY KEY(ID)
);

CREATE TABLE Friendships(
	User1ID int not null FOREIGN KEY REFERENCES Users(ID),
	User2ID int not null FOREIGN KEY REFERENCES Users(ID),
);

ALTER TABLE Friendships
	ADD CONSTRAINT Pk_Friendships PRIMARY KEY(User1ID, User2ID)

CREATE TABLE Groups (
	ID int identity,
	AdminID int,
	NumberOfMembers int,
	CONSTRAINT Pk_UsersGroups PRIMARY KEY(ID)
);

CREATE TABLE GroupMemberships (
	JoinDateTime datetime,
	UserID int not null FOREIGN KEY REFERENCES Users(ID),
	GroupID int not null FOREIGN KEY REFERENCES Groups(ID),
);

ALTER TABLE GroupMemberships
	ADD CONSTRAINT Pk_GroupMemberships PRIMARY KEY(UserID, GroupID)

EXEC sp_rename 'UsersGroups', 'GroupMemberships';

CREATE TABLE GroupProjects (
	ID int identity,
	NumberOfComponents int not null,
	GroupID int not null FOREIGN KEY REFERENCES Groups(ID),
	CONSTRAINT Pk_GroupProjects PRIMARY KEY(ID)
);

CREATE TABLE GroupProjectsComponents (
	ID int identity,
	Title varchar(40) not null,
	Summary text not null,
	ManagerID int not null FOREIGN KEY REFERENCES Users(ID),
	GroupProjectsID int not null FOREIGN KEY REFERENCES GroupProjects(ID)
)

CREATE TABLE PersonalTasks (
	ID int identity,
	Title varchar(150) default 'Untitled Task',
	Summary text default 'No summary',
	CONSTRAINT Pk_PersonalTasks PRIMARY KEY(ID),
	UserID int not null FOREIGN KEY REFERENCES Users(ID),
);

CREATE TABLE GroupTasks (
	ID int identity,
	Title varchar(150) not null,
	Summary text default 'No summary',
	UserAssignedByID int not null FOREIGN KEY REFERENCES Users(ID),
	UserAssignedToID int not null FOREIGN KEY REFERENCES Users(ID),
	GroupProjectsID int not null FOREIGN KEY REFERENCES GroupProjects(ID),
	CONSTRAINT Pk_GroupTasks PRIMARY KEY(ID)
);

CREATE TABLE UserStatistics (
	NumberOfCompletedPersonalTasks int default 0,
	NumberOfCompletedGroupTasks int default 0,
	NumberOfUncompletedPersonalTasks int default 0,
	NumberOfUncompletedGroupTasks int default 0,
	UserID int not null FOREIGN KEY REFERENCES Users(ID),
	CONSTRAINT Pk_UserStatistics PRIMARY KEY(UserID)
);

CREATE TABLE GroupStatistics (
	NumberOfCompletedTasks int default 0,
	NumberOfUncompletedTasks int default 0,
	AverageNumberOfActiveMembersPerDay int default 0,
	GroupID int not null FOREIGN KEY REFERENCES Groups(ID),
	CONSTRAINT Pk_GroupStatistics PRIMARY KEY(GroupID)
);

CREATE TABLE ChatRooms (
	ID int identity,
	NumberOfMembers int default 0,
	CONSTRAINT Pk_ChatRooms PRIMARY KEY(ID)
)

DROP TABLE ChatRooms

CREATE TABLE ChatRoomMemberships (
	DateOfJoin datetime not null,
	ChatRoomID int not null FOREIGN KEY REFERENCES ChatRooms(ID),
	UserID int not null FOREIGN KEY REFERENCES Users(ID),
	CONSTRAINT Pk_ChatRoomMemberships PRIMARY KEY(ChatRoomID, UserID)
)

ALTER TABLE ChatRoomMemberships
	ADD IsAdmin bit not null

ALTER TABLE ChatRoomMemberships
	DROP CONSTRAINT Pk_ChatMessages

ALTER TABLE ChatRoomMemberships
	ADD CONSTRAINT Pk_ChatRoomMemberships PRIMARY KEY(ChatRoomID, UserID)

CREATE TABLE ChatMessages (
	ChatRoomID int not null FOREIGN KEY REFERENCES ChatRooms(ID),
	UserID int not null FOREIGN KEY REFERENCES Users(ID),
	CONSTRAINT Pk_ChatMessages PRIMARY KEY(ChatRoomID, UserID)
)

DROP TABLE ChatRoomMemberships
DROP TABLE ChatMessages
