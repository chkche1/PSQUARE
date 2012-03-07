# Note, we have to drop tables in the reverse order of dependencies

#first clear out all the photo stuff
drop table if exists UserPrivacy;
drop table if exists CirclePrivacy;
drop table if exists Rating;
drop table if exists Tag;
drop table if exists Photo;
drop table if exists PrivacyType;

#now users
drop table if exists AcademicInstitution;
drop table if exists Interest;
drop table if exists FriendRelation;
drop table if exists Circle;
drop table if exists Student;
drop table if exists Professor;
drop table if exists Users;

CREATE TABLE `Users`(
	`userid` int(11) NOT NULL auto_increment,
	`first_name` varchar(20),
	`last_name` varchar(20),
	`dob` date, 
	`email` varchar(40),
	`gender` char(1),
	`address` varchar(100),
	`password` varchar(20),
	PRIMARY KEY (userid) 
) ENGINE=InnoDB;

insert into Users(userid, first_name, last_name, email) values (0, 'No', 'Name', 'No.Name@example.org');


CREATE TABLE Interest(
	`userid` int(11) NOT NULL,
	`interest` varchar(40) NOT NULL,
	FOREIGN KEY (userid) REFERENCES Users(userid)
		ON DELETE CASCADE,
        PRIMARY KEY (userid, interest)
) ENGINE=InnoDB;

CREATE TABLE AcademicInstitution(
	`userid` int(11) NOT NULL,
	`institution` varchar(50) NOT NULL,
	FOREIGN KEY (userid) REFERENCES Users(userid)
		ON DELETE CASCADE,
        PRIMARY KEY (userid, institution)
) ENGINE=InnoDB;

CREATE TABLE Professor(
	`userid` int(11) NOT NULL,
	`research_area` varchar(20),
	`room_id` varchar(20),
	FOREIGN KEY (userid) REFERENCES Users(userid)
		ON DELETE CASCADE,
	PRIMARY KEY (userid) 	
) ENGINE=InnoDB;

CREATE TABLE Student(
	`userid` int(11) NOT NULL,
	`advisorid` int(11),
	`year` int(11),
	FOREIGN KEY (userid) REFERENCES Users(userid)
		ON DELETE CASCADE,
	FOREIGN KEY (advisorid) REFERENCES Professor(userid)
		ON DELETE CASCADE,
	PRIMARY KEY (userid)
) ENGINE=InnoDB;

CREATE TABLE Circle(
	`cid` int(11) NOT NULL,
	`userid` int(11) NOT NULL,
	`name` varchar(20) NOT NULL,
	FOREIGN KEY (userid) REFERENCES Users(userid)
		ON DELETE CASCADE,
	PRIMARY KEY (cid)
) ENGINE=InnoDB;

CREATE TABLE FriendRelation(
	`friendid` int(11) NOT NULL,
	`cid` int(11) NOT NULL,
	FOREIGN KEY (friendid) REFERENCES Users(userid)
		ON DELETE CASCADE,
        FOREIGN KEY (cid) REFERENCES Circle(cid)
                ON DELETE CASCADE,
      	PRIMARY KEY (friendid, cid)
) ENGINE=InnoDB;

CREATE TABLE PrivacyType(
       `privacy` varchar(20) NOT NULL,
       PRIMARY KEY (privacy)
) ENGINE=InnoDB;

insert into PrivacyType(privacy) VALUES ('ALL');
insert into PrivacyType(privacy) VALUES ('CUSTOM');

CREATE TABLE Photo( 
        `pid` int(11) NOT NULL,
	`userid` int(11) NOT NULL,
	`url` varchar(500) NOT NULL,
	`privacy` varchar(20) NOT NULL DEFAULT 'ALL',
	FOREIGN KEY (userid) REFERENCES Users(userid)
		ON DELETE CASCADE,
	FOREIGN KEY (privacy) REFERENCES PrivacyType(privacy),
	PRIMARY KEY (pid) 	
) ENGINE=InnoDB;

CREATE TABLE Rating( 
        `pid` int(11) NOT NULL,
	`userid` int(11) NOT NULL,
	`score` int(11) NOT NULL, 
	FOREIGN KEY (pid) REFERENCES Photo(pid)
		ON DELETE CASCADE,
	FOREIGN KEY (userid) REFERENCES Users(userid)
		ON DELETE CASCADE,
      	PRIMARY KEY (pid, userid, score)
) ENGINE=InnoDB;

CREATE TABLE Tag(
	`pid` int(11) NOT NULL,
	`userid` int(11) NOT NULL,
	`comments` varchar(250) NOT NULL,
	FOREIGN KEY (pid) REFERENCES Photo(pid)
		ON DELETE CASCADE,
	FOREIGN KEY (userid) REFERENCES Users(userid)
		ON DELETE CASCADE,
      	PRIMARY KEY (pid, userid, comments)

) ENGINE=InnoDB;


CREATE TABLE UserPrivacy(
        `pid` int(11) NOT NULL,
	`userid` int(11) NOT NULL,
	FOREIGN KEY (pid) REFERENCES Photo(pid)
		ON DELETE CASCADE,
	FOREIGN KEY (userid) REFERENCES Users(userid)
		ON DELETE CASCADE,
      	PRIMARY KEY (pid, userid)
) ENGINE=InnoDB;

CREATE TABLE CirclePrivacy(
        `pid` int(11) NOT NULL,
	`cid` int(11) NOT NULL,
	FOREIGN KEY (pid) REFERENCES Photo(pid)
		ON DELETE CASCADE,
	FOREIGN KEY (cid) REFERENCES Circle(cid)
		ON DELETE CASCADE,
      	PRIMARY KEY (pid, cid)
) ENGINE=InnoDB;

