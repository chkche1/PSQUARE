

insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5001, 'Kent', 'Cheng', '1992-01-01', 'kcheng@google.com', 'm','3925 Chestnut', 'salt-secrete');

insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5002, 'Sarin', 'Morgan', '1990-10-02', 'morgan@example.com', 'f', 'Center City', 'ring-xrp');
insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5003, 'Allen', 'Li', '1982-03-03', 'li@microsoft.com','m', '3925 Walnut, PA', 'billtogates');
insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5004, 'Joe', 'Smith', '1979-09-28', 'copycat@baidu.com.cn','m', 'Shanghai', 'lollipop');
insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5005, 'Eun', 'Kim', '2000-05-05', 'kimmy@example.com', 'm','Seoul, Korea', 'socccer');


insert into Professor(userid, research_area, room_id) values (5005, 'DB', '6003');
insert into Student(userid, advisorid, year) values (5001, 5005, 2013);
insert into Student(userid, advisorid, year) values (5002, 5005, 2013);
insert into Student(userid, advisorid, year) values (5003, 5005, 2013);
insert into Student(userid, advisorid, year) values (5004, 5005, 2013);

insert into AcademicInstitution(userid, institution) values (5001, 'UPenn');
insert into AcademicInstitution(userid, institution) values (5002, 'UPenn');
insert into AcademicInstitution(userid, institution) values (5003, 'UPenn');
insert into AcademicInstitution(userid, institution) values (5004, 'UPenn');
insert into AcademicInstitution(userid, institution) values (5005, 'UPenn');


insert into Circle(cid, userid, name) values (5501, 5001, 'Research buddies');
insert into Circle(cid, userid, name) values (5502, 5001, 'SIGGMOD group');
insert into Circle(cid, userid, name) values (5503, 5002, 'STOC');
insert into Circle(cid, userid, name) values (5504, 5003, 'SIGGCOMM');
insert into Circle(cid, userid, name) values (5505, 5004, 'CIDR');

insert into FriendRelation(friendid, cid) values(5002, 5501);
insert into FriendRelation(friendid, cid) values(5001, 5502);
-- insert into FriendRelation(friendid, cid) values(5003, 5504);
insert into FriendRelation(friendid, cid) values(5004, 5503);
insert into FriendRelation(friendid, cid) values(5002, 5505);
insert into FriendRelation(friendid, cid) values(5002, 5504);
insert into FriendRelation(friendid, cid) values(5005, 5503);

insert into Interest(userid, interest) values (5001, 'Star Craft');
insert into Interest(userid, interest) values (5001, 'Warcraft');
insert into Interest(userid, interest) values (5001, 'Counter Strike');
insert into Interest(userid, interest) values (5001, 'Diablo');

insert into Interest(userid, interest) values (5002, 'K-pop');
insert into Interest(userid, interest) values (5002, 'J-pop');

insert into Interest(userid, interest) values (5003, 'Baseball');
insert into Interest(userid, interest) values (5003, 'Tennis');
insert into Interest(userid, interest) values (5003, 'Karaoke');

insert into Interest(userid, interest) values (5004, 'Swimming');
insert into Interest(userid, interest) values (5004, 'RPG');
insert into Interest(userid, interest) values (5004, 'Biking');

insert into Interest(userid, interest) values (5005, 'Database Systems');
insert into Interest(userid, interest) values (5005, 'AI');
insert into Interest(userid, interest) values (5005, 'networking');

insert into Users(userid, first_name, last_name, dob, email, gender,address, password) values (5011, 'Sauron', 'Morgoth', '1000-01-01', 'sauron@mordor.gov', 'f','Cirith Ungol', 'ring');

insert into Users(userid, first_name, last_name, dob, email, gender,address, password) values (5012, 'Elrond', 'Half-Elven', '1200-01-01', 'elrond@rivendell.org','m', 'Rivendell', 'neo');

insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5013, 'Gandalf', 'Gray', '1000-01-01', 'slave@isengard.com', 'm', '3925 Chestnut','eaaagglees');

insert into Users(userid, first_name,  dob, email, gender, address, password) values (5014, 'Aragorn', '2931-03-01', 'highking@gondor.gov', 'm','Minas Tirith', 'rofl');

insert into Users(userid, first_name, last_name, dob, address, password) values (5015, 'Frodo', 'Baggins', '2989-09-22', 'Bag End', 'sam');

insert into Interest(userid, interest) values (5011, 'Death Metal');
insert into Interest(userid, interest) values (5011, 'Rings');
insert into Interest(userid, interest) values (5011, 'Towers');
insert into Interest(userid, interest) values (5011, 'Black');

insert into Interest(userid, interest) values (5012, 'Elven Culture');
insert into Interest(userid, interest) values (5012, 'Shelter');
insert into Interest(userid, interest) values (5012, 'Orphans');

insert into Interest(userid, interest) values (5013, 'Fireworks');
insert into Interest(userid, interest) values (5013, 'Hobbits');
insert into Interest(userid, interest) values (5013, 'Wizardry');

insert into Interest(userid, interest) values (5014, 'Swords');


insert into AcademicInstitution(userid, institution) values (5012, 'zion');
insert into AcademicInstitution(userid, institution) values (5012, 'oracle');
insert into AcademicInstitution(userid, institution) values (5013, 'isengard');
insert into AcademicInstitution(userid, institution) values (5014, 'Kingship School');
insert into AcademicInstitution(userid, institution) values (5014, 'Rangersss');
insert into AcademicInstitution(userid, institution) values (5014, 'Winning');

insert into Professor(userid, research_area, room_id) values (5011, 'death', 'EYE');

insert into Professor(userid, research_area) values (5012, 'The Matrix');

insert into Professor(userid, research_area) values (5013, 'magic/fireworks');

insert into Student(userid, advisorid, year) values(5014, 5012, 20);

insert into Student(userid, advisorid, year) values(5015, 5013, 1);

insert into Circle(cid, userid, name) values (5001, 5011, 'minions');

insert into Circle(cid, userid, name) values (5002, 5011, 'enemies');
insert into FriendRelation(friendid, cid) values (5012, 5002);
insert into FriendRelation(friendid, cid) values (5013, 5002);
insert into FriendRelation(friendid, cid) values (5014, 5002);
insert into FriendRelation(friendid, cid) values (5015, 5002);

insert into Circle(cid, userid, name) values (5003, 5011, 'equals');
insert into FriendRelation(friendid, cid) values (5013, 5003);

insert into Circle(cid, userid, name) values (5004, 5012, 'elves');

insert into Circle(cid, userid, name) values (5005, 5012, 'family');
insert into FriendRelation(friendid, cid) values (5014, 5005);

insert into Circle(cid, userid, name) values (5006, 5012, 'guests');
insert into FriendRelation(friendid, cid) values (5015, 5006);
insert into FriendRelation(friendid, cid) values (5014, 5006);
insert into FriendRelation(friendid, cid) values (5013, 5006);

insert into Photo(pid, userid, url, privacy) values(50001, 5011, 'http://images.wikia.com/lotr/images/b/bb/Eye-o-sauron-03.jpg', 'ALL');

insert into Photo(pid, userid, url) values(50002, 5011, 'http://upload.wikimedia.org/wikipedia/en/3/3f/Minasmorgulpj.jpg');

insert into Photo(pid, userid, url, privacy) values(50003, 5015, 'http://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/Unico_Anello.png/200px-Unico_Anello.png', 'CUSTOM');

insert into UserPrivacy(pid, userid) values (50003, 5012), (50003, 5013), (50003, 5014);

insert into Photo(pid, userid, url, privacy) values(50004, 5012, 'http://www.lotrolife.com/wp-content/uploads/serge_gallery/_18/rivendell_map_m.jpg', 'CUSTOM');

insert into CirclePrivacy(pid,cid) values (50004, 5006);

insert into Users (userid, first_name, last_name,dob,email,gender,address,password) values (5016,'jintao','hu','1940-01-01','jhu@communism.com','M','No.1 road','hujintao');
insert into Users (userid, first_name, last_name,dob,email,gender,address,password) values (5017,'jiabao','wen','1940-02-02','jwen@communism.com','M','No.1 road','wenjiabao');
insert into Users (userid, first_name, last_name,dob,email,gender,address,password) values (5018,'qinglin','jia','1940-03-03','qjia@communism.com','M','No.2 road','jiaqinglin');

insert into Users (userid, first_name, last_name,dob,email,gender,address,password) values (5019,'changchun','li','1940-04-04','cli@communism.com','M','No.2 road','lichangchun');
insert into Users (userid, first_name, last_name,dob,email,gender,address,password) values (5020,'xilai','bo','1940-05-05','xbo@communism.com','M','No.3 road','boxilai');

insert into AcademicInstitution (userid, institution) values (5016,'Tsinghua'),(5017,'Tsinghu'), (5018,'Unknown'),(5019,'HIT'),(5020,'Cambridge');

insert into Professor(userid,research_area,room_id) values (5016,'Communism','room1'),(5017,'Communism','room2');

insert into Student (userid, advisorid,year) values (5018,5016,4),(5019,5017,3),(5020,5016,5);

insert into Circle(cid, userid, name) values (5116, 5016, 'Buddies');
insert into Circle(cid, userid, name) values (5117, 5017, 'Friend');
insert into Circle(cid, userid, name) values (5118, 5018, 'Pengyou');
insert into Circle(cid, userid, name) values (5119, 5019, 'Family');
insert into Circle(cid, userid, name) values (5120, 5020, 'Roommate');

insert into FriendRelation(friendid, cid) values (5018, 5116);
insert into FriendRelation(friendid, cid) values (5016, 5117);
insert into FriendRelation(friendid, cid) values (5017, 5118);
insert into FriendRelation(friendid, cid) values (5019, 5120);
insert into FriendRelation(friendid, cid) values (5020, 5119);


insert into Interest(userid, interest) values (5016, 'Sleep');
insert into Interest(userid, interest) values (5016, 'Run');
insert into Interest(userid, interest) values (5016, 'Walk');
insert into Interest(userid, interest) values (5017, 'Eat');
insert into Interest(userid, interest) values (5017, 'Cry');
insert into Interest(userid, interest) values (5018, 'Jump');
insert into Interest(userid, interest) values (5018, 'Speak');
insert into Interest(userid, interest) values (5018, 'Stand');
insert into Interest(userid, interest) values (5019, 'Ski');
insert into Interest(userid, interest) values (5020, 'Swim');

insert into Photo (pid,userid,url,privacy) values (50161,5016,'https://encrypted-tbn3.google.com/images?q=tbn:ANd9GcS34xx_4foauqDJ9qtueboluwZpoxuabgXRtUijzEgpw-mrzofo','ALL');
insert into Photo (pid,userid,url,privacy) values (50162,5016,'https://encrypted-tbn1.google.com/images?q=tbn:ANd9GcQ1VaWg1Rc6oyA_59CC4cTyTLFsZQb7Wo1B_bSwtKT4iLKUvr7y','CUSTOM');
insert into Photo (pid,userid,url,privacy) values (50171,5017,'https://encrypted-tbn0.google.com/images?q=tbn:ANd9GcSJFRnFKsDuRonb77Z2Yz6wrzop1iGkb4Sp6VqIPUsG0nH13xs-sQ','ALL');
insert into Photo (pid,userid,url,privacy) values (50181,5018,'https://encrypted-tbn3.google.com/images?q=tbn:ANd9GcRQ4Imoakg7snTyrWB9wx72grdRUOcJuTerprxIYQRlnkHHdhct','CUSTOM');
insert into Photo (pid,userid,url,privacy) values (50182,5018,'https://encrypted-tbn1.google.com/images?q=tbn:ANd9GcRY7pFDVWH7mc4Uoq2_sshNjiZqDVigkOCcsfevKVG5aYBlduRWJg','CUSTOM');
insert into Photo (pid,userid,url,privacy) values (50183,5018,'https://encrypted-tbn3.google.com/images?q=tbn:ANd9GcSx5sdi76gGRHfw444NwLrGQkjHZGkUFmXl5XzQFz-0qyAXdWpm','CUSTOM');
insert into Photo (pid,userid,url,privacy) values (50191,5019,'https://encrypted-tbn0.google.com/images?q=tbn:ANd9GcR2-_hf0Vbi552VuVZt7N-DLwyRBuXr0EApn4Ud7ksUflD5Lsle5Q','ALL');
insert into Photo (pid,userid,url,privacy) values (50192,5019,'https://encrypted-tbn2.google.com/images?q=tbn:ANd9GcTKoBpAFXaujbox_WdJWD_CyH0MS_rhQTdFH-LOyzGXuqawneNZ','ALL');
insert into Photo (pid,userid,url,privacy) values (50201,5020,'https://encrypted-tbn0.google.com/images?q=tbn:ANd9GcS2YBUJD-3M2lBPNN4jkjUUszYJeN04m64NgF641V27XiRp7SiYyQ','CUSTOM');
insert into Photo (pid,userid,url,privacy) values (50202,5020,'https://encrypted-tbn0.google.com/images?q=tbn:ANd9GcTedlk-p0lwkN9S0sQoL40fFy4FpRhy1PxsjcXW0A2_7KgmNomMJA','CUSTOM');

insert into UserPrivacy(pid, userid) values (50201, 5019), (50202, 5019), (50183, 5017);
insert into CirclePrivacy(pid,cid) values (50162, 5116), (50181,5118),(50182,5118);


insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5006, 'Robert', 'Fischer', '1961-11-01', 'rob@gmail.com', 'M', '32 Wellington St', 'chess');
insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5007, 'Richard', 'Glahn', '1951-11-1', 'rich@gmail.com', 'M', '50 Wellington St', 'CalculusBC');
insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5008, 'Alen', 'Kubati', '1990-11-14', 'kubatial@gmail.com', 'M', '72 Marion Ave', 'blahblahblah');
insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5009, 'Albert', 'Einstein', '1910=11-14', 'eins@gmail.com', 'M', '25 Princeton Ave', 'Relativity');
insert into Users(userid, first_name, last_name, dob, email, gender, address, password) values (5010, 'Bob', 'Brown', '1987-11-14', 'bbro@gmail.com', 'M', '54 Hollywood Ave', 'seriously?');


insert into Professor(userid, research_area, room_id) values (5006, 'Chess', '5030');
insert into Professor(userid, research_area, room_id) values (5007, 'Mathematics', '5031');
insert into Student(userid, advisorid, year) values (5008, 5006, 2013);
insert into Student(userid, advisorid, year) values (5009, 5007, 2020);
insert into Student(userid, advisorid, year) values (5010, 5007, 2025);

insert into Circle(cid, userid, name) values (5100, 5006, 'Chess Buddies');
insert into Circle(cid, userid, name) values (5101, 5007, 'Math Buddies');
insert into Circle(cid, userid, name) values (5102, 5008, 'Buddies');
insert into Circle(cid, userid, name) values (5103, 5009, 'Friends');
insert into Circle(cid, userid, name) values (5104, 5010, 'Buddies');


insert into FriendRelation(friendid, cid) values (5007, 5100);
insert into FriendRelation(friendid, cid) values (5006, 5101);
insert into FriendRelation(friendid, cid) values (5009, 5102);
insert into FriendRelation(friendid, cid) values (5010, 5102);
insert into FriendRelation(friendid, cid) values (5008, 5103);
insert into FriendRelation(friendid, cid) values (5010, 5103);
insert into FriendRelation(friendid, cid) values (5008, 5104);
insert into FriendRelation(friendid, cid) values (5009, 5104);

insert into AcademicInstitution(userid, institution) values(5006, 'UPenn');
insert into AcademicInstitution(userid, institution) values(5007, 'Princeton');
insert into AcademicInstitution(userid, institution) values(5008, 'UPenn');
insert into AcademicInstitution(userid, institution) values(5009, 'Princeton');
insert into AcademicInstitution(userid, institution) values(5010, 'Princeton');

insert into Interest(userid, interest) values (5006, 'Computer Science');
insert into Interest(userid, interest) values (5007, 'Mathematics');
insert into Interest(userid, interest) values (5008, 'Literature');
insert into Interest(userid, interest) values (5009, 'Ping Pong');
insert into Interest(userid, interest) values (5010, 'Tennis');

insert into Tag(pid, userid, comments) values ( 50191, 5001, "Good photo");
insert into Tag(pid, userid, comments) values ( 50192, 5002, "very good");
insert into Tag(pid, userid, comments) values ( 50202, 5003, "nice");
insert into Tag(pid, userid, comments) values ( 50201, 5004, "this is a good one");
insert into Tag(pid, userid, comments) values ( 50162, 5005, "pretty good");

insert into Rating(pid,userid, score) values (50191, 5010, 3);
insert into Rating(pid,userid, score) values (50191, 5011, 3);
insert into Rating(pid,userid, score) values (50191, 5012, 3);
insert into Rating(pid,userid, score) values (50191, 5013, 3);
insert into Rating(pid,userid, score) values (50191, 5014, 3);
insert into Rating(pid,userid, score) values (50192, 5015, 4);
insert into Rating(pid,userid, score) values (50192, 5016, 4);
insert into Rating(pid,userid, score) values (50192, 5009, 4);
insert into Rating(pid,userid, score) values (50192, 5008, 4);
insert into Rating(pid,userid, score) values (50192, 5007, 4);

-- declare some users
insert into Users(userid, first_name, email, password) values (100001, 'p1', '1@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100002, 'p2', '2@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100003, 'p3', '3@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100004, 'p4', '4@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100005, 'p5', '5@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100006, 'p6', '6@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100007, 'p7', '7@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100008, 'p8', '8@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100009, 'p9', '9@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100010, 'p10', '10@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100011, 'p11', '11@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100012, 'p12', '12@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100013, 'p13', '13@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100014, 'p14', '14@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100015, 'p15', '15@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100016, 'p16', '16@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100017, 'p17', '17@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100018, 'p18', '18@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100019, 'p19', '19@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100020, 'p20', '20@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100021, 'p21', '21@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100022, 'p22', '22@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100023, 'p23', '23@me.com', 'lol');
insert into Users(userid, first_name, email, password) values (100024, 'p24', '24@me.com', 'lol');

-- each user 1000x has circle 1000x name pxs
-- in this circle of x are all multiples of x
insert into Circle(cid, userid, name) values (100001, 100001, 'p1s');
insert into Circle(cid, userid, name) values (100002, 100002, 'p2s');
insert into Circle(cid, userid, name) values (100003, 100003, 'p3s');
insert into Circle(cid, userid, name) values (100004, 100004, 'p4s');
insert into Circle(cid, userid, name) values (100005, 100005, 'p5s');
insert into Circle(cid, userid, name) values (100006, 100006, 'p6s');
insert into Circle(cid, userid, name) values (100007, 100007, 'p7s');
insert into Circle(cid, userid, name) values (100008, 100008, 'p8s');
insert into Circle(cid, userid, name) values (100009, 100009, 'p9s');
insert into Circle(cid, userid, name) values (100010, 100010, 'p10s');
insert into Circle(cid, userid, name) values (100011, 100011, 'p11s');
insert into Circle(cid, userid, name) values (100012, 100012, 'p12s');
insert into Circle(cid, userid, name) values (100013, 100013, 'p13s');
insert into Circle(cid, userid, name) values (100014, 100014, 'p14s');
insert into Circle(cid, userid, name) values (100015, 100015, 'p15s');
insert into Circle(cid, userid, name) values (100016, 100016, 'p16s');
insert into Circle(cid, userid, name) values (100017, 100017, 'p17s');
insert into Circle(cid, userid, name) values (100018, 100018, 'p18s');
insert into Circle(cid, userid, name) values (100019, 100019, 'p19s');
insert into Circle(cid, userid, name) values (100020, 100020, 'p20s');
insert into Circle(cid, userid, name) values (100021, 100021, 'p21s');
insert into Circle(cid, userid, name) values (100022, 100022, 'p22s');
insert into Circle(cid, userid, name) values (100023, 100023, 'p23s');
insert into Circle(cid, userid, name) values (100024, 100024, 'p24s');

-- each user 1000x has circle 2000x name pxt
-- in this circle of x are all factors of x
insert into Circle(cid, userid, name) values (200001, 100001, 'p1t');
insert into Circle(cid, userid, name) values (200002, 100002, 'p2t');
insert into Circle(cid, userid, name) values (200003, 100003, 'p3t');
insert into Circle(cid, userid, name) values (200004, 100004, 'p4t');
insert into Circle(cid, userid, name) values (200005, 100005, 'p5t');
insert into Circle(cid, userid, name) values (200006, 100006, 'p6t');
insert into Circle(cid, userid, name) values (200007, 100007, 'p7t');
insert into Circle(cid, userid, name) values (200008, 100008, 'p8t');
insert into Circle(cid, userid, name) values (200009, 100009, 'p9t');
insert into Circle(cid, userid, name) values (200010, 100010, 'p10t');
insert into Circle(cid, userid, name) values (200011, 100011, 'p11t');
insert into Circle(cid, userid, name) values (200012, 100012, 'p12t');
insert into Circle(cid, userid, name) values (200013, 100013, 'p13t');
insert into Circle(cid, userid, name) values (200014, 100014, 'p14t');
insert into Circle(cid, userid, name) values (200015, 100015, 'p15t');
insert into Circle(cid, userid, name) values (200016, 100016, 'p16t');

insert into Circle(cid, userid, name) values (200017, 100017, 'p17t');
insert into Circle(cid, userid, name) values (200018, 100018, 'p18t');
insert into Circle(cid, userid, name) values (200019, 100019, 'p19t');
insert into Circle(cid, userid, name) values (200020, 100020, 'p20t');
insert into Circle(cid, userid, name) values (200021, 100021, 'p21t');
insert into Circle(cid, userid, name) values (200022, 100022, 'p22t');
insert into Circle(cid, userid, name) values (200023, 100023, 'p23t');
insert into Circle(cid, userid, name) values (200024, 100024, 'p24t');

-- multiples of 1
insert into FriendRelation(friendid, cid) values (100002, 100001);
insert into FriendRelation(friendid, cid) values (100003, 100001);
insert into FriendRelation(friendid, cid) values (100004, 100001);
insert into FriendRelation(friendid, cid) values (100005, 100001);
insert into FriendRelation(friendid, cid) values (100006, 100001);
insert into FriendRelation(friendid, cid) values (100007, 100001);
insert into FriendRelation(friendid, cid) values (100008, 100001);
insert into FriendRelation(friendid, cid) values (100009, 100001);
insert into FriendRelation(friendid, cid) values (100010, 100001);
insert into FriendRelation(friendid, cid) values (100011, 100001);
insert into FriendRelation(friendid, cid) values (100012, 100001);
insert into FriendRelation(friendid, cid) values (100013, 100001);
insert into FriendRelation(friendid, cid) values (100014, 100001);
insert into FriendRelation(friendid, cid) values (100015, 100001);
insert into FriendRelation(friendid, cid) values (100016, 100001);
insert into FriendRelation(friendid, cid) values (100017, 100001);
insert into FriendRelation(friendid, cid) values (100018, 100001);
insert into FriendRelation(friendid, cid) values (100019, 100001);
insert into FriendRelation(friendid, cid) values (100020, 100001);
insert into FriendRelation(friendid, cid) values (100021, 100001);
insert into FriendRelation(friendid, cid) values (100022, 100001);
insert into FriendRelation(friendid, cid) values (100023, 100001);
insert into FriendRelation(friendid, cid) values (100024, 100001);

-- have 1 as factor
insert into FriendRelation(friendid, cid) values (100001, 200002);
insert into FriendRelation(friendid, cid) values (100001, 200003);
insert into FriendRelation(friendid, cid) values (100001, 200004);
insert into FriendRelation(friendid, cid) values (100001, 200005);
insert into FriendRelation(friendid, cid) values (100001, 200006);
insert into FriendRelation(friendid, cid) values (100001, 200007);
insert into FriendRelation(friendid, cid) values (100001, 200008);
insert into FriendRelation(friendid, cid) values (100001, 200009);
insert into FriendRelation(friendid, cid) values (100001, 200010);
insert into FriendRelation(friendid, cid) values (100001, 200011);
insert into FriendRelation(friendid, cid) values (100001, 200012);
insert into FriendRelation(friendid, cid) values (100001, 200013);
insert into FriendRelation(friendid, cid) values (100001, 200014);
insert into FriendRelation(friendid, cid) values (100001, 200015);
insert into FriendRelation(friendid, cid) values (100001, 200016);
insert into FriendRelation(friendid, cid) values (100001, 200017);
insert into FriendRelation(friendid, cid) values (100001, 200018);
insert into FriendRelation(friendid, cid) values (100001, 200019);
insert into FriendRelation(friendid, cid) values (100001, 200020);
insert into FriendRelation(friendid, cid) values (100001, 200021);
insert into FriendRelation(friendid, cid) values (100001, 200022);
insert into FriendRelation(friendid, cid) values (100001, 200023);
insert into FriendRelation(friendid, cid) values (100001, 200024);

-- forward 2's
insert into FriendRelation(friendid, cid) values (100004, 100002);
insert into FriendRelation(friendid, cid) values (100006, 100002);
insert into FriendRelation(friendid, cid) values (100008, 100002);
insert into FriendRelation(friendid, cid) values (100010, 100002);
insert into FriendRelation(friendid, cid) values (100012, 100002);
insert into FriendRelation(friendid, cid) values (100014, 100002);
insert into FriendRelation(friendid, cid) values (100016, 100002);
insert into FriendRelation(friendid, cid) values (100018, 100002);
insert into FriendRelation(friendid, cid) values (100020, 100002);
insert into FriendRelation(friendid, cid) values (100022, 100002);
insert into FriendRelation(friendid, cid) values (100024, 100002);

-- backward 2's
insert into FriendRelation(friendid, cid) values (100002, 200004);
insert into FriendRelation(friendid, cid) values (100002, 200006);
insert into FriendRelation(friendid, cid) values (100002, 200008);
insert into FriendRelation(friendid, cid) values (100002, 200010);
insert into FriendRelation(friendid, cid) values (100002, 200012);
insert into FriendRelation(friendid, cid) values (100002, 200014);
insert into FriendRelation(friendid, cid) values (100002, 200016);
insert into FriendRelation(friendid, cid) values (100002, 200018);
insert into FriendRelation(friendid, cid) values (100002, 200020);
insert into FriendRelation(friendid, cid) values (100002, 200022);
insert into FriendRelation(friendid, cid) values (100002, 200024);

-- forward 3's
insert into FriendRelation(friendid, cid) values (100006, 100003);
insert into FriendRelation(friendid, cid) values (100009, 100003);
insert into FriendRelation(friendid, cid) values (100012, 100003);
insert into FriendRelation(friendid, cid) values (100015, 100003);
insert into FriendRelation(friendid, cid) values (100018, 100003);
insert into FriendRelation(friendid, cid) values (100021, 100003);
insert into FriendRelation(friendid, cid) values (100024, 100003);

-- backward 3's
insert into FriendRelation(friendid, cid) values (100003, 200006);
insert into FriendRelation(friendid, cid) values (100003, 200009);
insert into FriendRelation(friendid, cid) values (100003, 200012);
insert into FriendRelation(friendid, cid) values (100003, 200015);
insert into FriendRelation(friendid, cid) values (100003, 200018);
insert into FriendRelation(friendid, cid) values (100003, 200021);
insert into FriendRelation(friendid, cid) values (100003, 200024);

-- forward 4's
insert into FriendRelation(friendid, cid) values (100008, 100004);
insert into FriendRelation(friendid, cid) values (100012, 100004);
insert into FriendRelation(friendid, cid) values (100016, 100004);
insert into FriendRelation(friendid, cid) values (100020, 100004);
insert into FriendRelation(friendid, cid) values (100024, 100004);

-- backward 4's
insert into FriendRelation(friendid, cid) values (100004, 200008);
insert into FriendRelation(friendid, cid) values (100004, 200012);
insert into FriendRelation(friendid, cid) values (100004, 200016);
insert into FriendRelation(friendid, cid) values (100004, 200020);
insert into FriendRelation(friendid, cid) values (100004, 200024);

-- forward 5's
insert into FriendRelation(friendid, cid) values (100010, 100005);
insert into FriendRelation(friendid, cid) values (100015, 100005);
insert into FriendRelation(friendid, cid) values (100020, 100005);

-- backward 5's
insert into FriendRelation(friendid, cid) values (100005, 200010);
insert into FriendRelation(friendid, cid) values (100005, 200015);
insert into FriendRelation(friendid, cid) values (100005, 200020);

-- forward 6's
insert into FriendRelation(friendid, cid) values (100012, 100006);
insert into FriendRelation(friendid, cid) values (100018, 100006);
insert into FriendRelation(friendid, cid) values (100024, 100006);

-- backwrad 6's
insert into FriendRelation(friendid, cid) values (100006, 200012);
insert into FriendRelation(friendid, cid) values (100006, 200018);
insert into FriendRelation(friendid, cid) values (100006, 200024);

-- forward 7's
insert into FriendRelation(friendid, cid) values (100014, 100007);
insert into FriendRelation(friendid, cid) values (100021, 100007);

-- backward 7's
insert into FriendRelation(friendid, cid) values (100007, 200014);
insert into FriendRelation(friendid, cid) values (100007, 200021);

-- forward 8's
insert into FriendRelation(friendid, cid) values (100016, 100008);
insert into FriendRelation(friendid, cid) values (100024, 100008);

-- backward 8's
insert into FriendRelation(friendid, cid) values (100008, 200016);
insert into FriendRelation(friendid, cid) values (100008, 200024);

-- forward 9's
insert into FriendRelation(friendid, cid) values (100018, 100009);

-- backward 9's
insert into FriendRelation(friendid, cid) values (100009, 200018);

-- forward 10's
insert into FriendRelation(friendid, cid) values (100020, 100010);

-- backward 10's
insert into FriendRelation(friendid, cid) values (100010, 200020);

-- forward 11's
insert into FriendRelation(friendid, cid) values (100022, 100011);

-- backward 11's
insert into FriendRelation(friendid, cid) values (100011, 200022);

-- forward 12's
insert into FriendRelation(friendid, cid) values (100024, 100012);

-- backward 12's
insert into FriendRelation(friendid, cid) values (100012, 200024);

insert into Photo(pid, userid, url, privacy) values(100001, 100001, 'http://promovieblogger.com/wp-content/uploads/2010/02/number-1-sign.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100002, 100002, 'http://blog.modernica.net/wp-content/uploads/2011/12/2.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100003, 100003, 'http://2.bp.blogspot.com/-V-m32CjCCv4/TpR7ZryD12I/AAAAAAAAGss/8s3SjB7dpRc/s1600/day-3.png', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100004, 100004, 'http://weightofaman.com/wp-content/uploads/2011/10/m4.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100005, 100005, 'http://www.gameguru.in/images/resident-evil-5-11.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100006, 100006, 'https://secure.touchnet.com/C20026_ustores/web/images/store_1/6.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100007, 100007, 'http://www.7-zip.org/logos/7z_sg01.png', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100008, 100008, 'http://www.forddoctorsdts.com/psd-8-ball/images/default/8ball_0.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100009, 100009, 'http://blog.80millionmoviesfree.com/wp-content/uploads/2009/05/9-movie.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100010, 100010, 'http://1.bp.blogspot.com/-Oj-0u8fNPIo/TeTFitmnMyI/AAAAAAABAA4/Ff4Xi5imvMM/s1600/ben10_13.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100011, 100011, 'http://www.in5d.com/images/11-11.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100012, 100012, 'http://www.spacefacts.de/iss/patches_hi/iss-12_3.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100013, 100013, 'http://s3.amazonaws.com/dogonews/pictures/7564/content/friday-the-13th.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100014, 100014, 'http://thebridescoop.com/wp-content/uploads/14-Days-Counting-Photo-from-www.robjcameron.posterous.com_.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100015, 100015, 'http://thebridescoop.com/wp-content/uploads/15-Days-Counting-Photo-from-www.weddingaccents.com_.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100016, 100016, 'http://www.lifeonpurpose.com/wp-content/uploads/sweet16.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100017, 100017, 'http://images2.fanpop.com/images/photos/5000000/Zac-17-Again-zac-efron-5012242-1280-1024.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100018, 100018, 'http://www.janbrett.com/images/number18.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100019, 100019, 'http://www.thecoloringbarn.com/wp-content/uploads/2010/07/number-19.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100020, 100020, 'http://www.cocettiwines.com/public/w3301/20-off-left-yellow.png', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100021, 100021, 'http://thecia.com.au/reviews/1/images/21-poster-1.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100022, 100022, 'http://rumiahmed.files.wordpress.com/2007/09/catch22.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100023, 100023, 'http://www.nkfu.com/resimler/23-nisan-resimleri-7.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(100024, 100024, 'http://4.bp.blogspot.com/_U-_MB3ct83I/S66D0Mzf0AI/AAAAAAAAFK8/ZkjOEQRfOao/s1600/24-season-8.jpg', 'ALL');

insert into Photo(pid, userid, url, privacy) values(200001, 100023, 'http://www.skiwithkim.com/images/slider/Kim-ski-CO.jpg', 'ALL');


insert into Rating(pid, userid, score) values (100001, 100002, 5);
insert into Rating(pid, userid, score) values (100001, 100003, 5);
insert into Tag(pid, userid, comments) values (100001, 100002, 'kw1 kw2');
insert into Tag(pid, userid, comments) values (100001, 100003, 'kw1 kw3');

insert into Rating(pid, userid, score) values (100002, 100003, 5);
insert into Rating(pid, userid, score) values (100002, 100004, 5);
insert into Tag(pid, userid, comments) values (100002, 100003, 'kw2 kw3');
insert into Tag(pid, userid, comments) values (100002, 100004, 'kw2 kw4');

insert into Rating(pid, userid, score) values (100003, 100001, 5);
insert into Rating(pid, userid, score) values (100003, 100002, 5);
insert into Tag(pid, userid, comments) values (100003, 100001, 'kw3 kw1');
insert into Tag(pid, userid, comments) values (100003, 100002, 'kw3 kw2');

insert into Rating(pid, userid, score) values (100004, 100002, 4);
insert into Rating(pid, userid, score) values (100004, 100001, 4);
insert into Tag(pid, userid, comments) values (100004, 100002, 'kw4 kw2');
insert into Tag(pid, userid, comments) values (100004, 100001, 'kw4 kw1');

insert into Rating(pid, userid, score) values (100005, 100006, 4);
insert into Rating(pid, userid, score) values (100005, 100007, 4);
insert into Tag(pid, userid, comments) values (100005, 100006, 'kw5 kw6');
insert into Tag(pid, userid, comments) values (100005, 100007, 'kw5 kw7');

insert into Rating(pid, userid, score) values (100006, 100002, 4);
insert into Rating(pid, userid, score) values (100006, 100010, 4);
insert into Tag(pid, userid, comments) values (100006, 100002, 'kw6 kw2');
insert into Tag(pid, userid, comments) values (100006, 100010, 'kw6 kw10');

insert into Rating(pid, userid, score) values (100007, 100009, 4);
insert into Rating(pid, userid, score) values (100007, 100008, 4);
insert into Tag(pid, userid, comments) values (100007, 100009, 'kw7 kw9');
insert into Tag(pid, userid, comments) values (100007, 100008, 'kw7 kw8');

insert into Rating(pid, userid, score) values (100008, 100010, 4);
insert into Rating(pid, userid, score) values (100008, 100011, 4);
insert into Tag(pid, userid, comments) values (100008, 100010, 'kw8 kw10');
insert into Tag(pid, userid, comments) values (100008, 100011, 'kw8 kw11');

insert into Rating(pid, userid, score) values (100009, 100008, 4);
insert into Rating(pid, userid, score) values (100009, 100007, 4);
insert into Tag(pid, userid, comments) values (100009, 100009, 'kw9 kw8');
insert into Tag(pid, userid, comments) values (100009, 100007, 'kw9 kw7');

insert into Rating(pid, userid, score) values (100010, 100001, 4);
insert into Rating(pid, userid, score) values (100010, 100005, 4);
insert into Tag(pid, userid, comments) values (100010, 100001, 'kw10 kw1');
insert into Tag(pid, userid, comments) values (100010, 100005, 'kw10 kw5');

insert into Rating(pid, userid, score) values (100011, 100001, 4);
insert into Rating(pid, userid, score) values (100011, 100002, 4);
insert into Tag(pid, userid, comments) values (100011, 100001, 'kw11 kw1');
insert into Tag(pid, userid, comments) values (100011, 100002, 'kw11 kw2');

insert into Rating(pid, userid, score) values (100012, 100015, 4);
insert into Rating(pid, userid, score) values (100012, 100016, 4);
insert into Tag(pid, userid, comments) values (100012, 100015, 'kw12 kw15');
insert into Tag(pid, userid, comments) values (100012, 100016, 'kw12 kw16');

insert into Rating(pid, userid, score) values (100013, 100001, 3);
insert into Rating(pid, userid, score) values (100013, 100003, 3);
insert into Tag(pid, userid, comments) values (100013, 100001, 'kw13 kw1');
insert into Tag(pid, userid, comments) values (100013, 100003, 'kw13 kw3');

insert into Rating(pid, userid, score) values (100014, 100018, 3);
insert into Rating(pid, userid, score) values (100014, 100019, 3);
insert into Tag(pid, userid, comments) values (100014, 100018, 'kw14 kw18');
insert into Tag(pid, userid, comments) values (100014, 100019, 'kw10 kw5');

insert into Rating(pid, userid, score) values (100015, 100011, 3);
insert into Rating(pid, userid, score) values (100015, 100010, 3);
insert into Tag(pid, userid, comments) values (100015, 100011, 'kw15 kw11');
insert into Tag(pid, userid, comments) values (100015, 100010, 'kw15 kw10');

insert into Rating(pid, userid, score) values (100016, 100002, 3);
insert into Rating(pid, userid, score) values (100016, 100007, 3);
insert into Tag(pid, userid, comments) values (100016, 100002, 'kw16 kw2');
insert into Tag(pid, userid, comments) values (100016, 100007, 'kw16 kw7');

insert into Rating(pid, userid, score) values (100017, 100013, 2);
insert into Rating(pid, userid, score) values (100017, 100011, 2);
insert into Tag(pid, userid, comments) values (100017, 100013, 'kw17 kw13');
insert into Tag(pid, userid, comments) values (100017, 100011, 'kw17 kw11');

insert into Rating(pid, userid, score) values (100018, 100014, 2);
insert into Rating(pid, userid, score) values (100018, 100015, 2);
insert into Tag(pid, userid, comments) values (100018, 100014, 'kw18 kw14');
insert into Tag(pid, userid, comments) values (100018, 100015, 'kw18 kw15');

insert into Rating(pid, userid, score) values (100019, 100001, 1);
insert into Rating(pid, userid, score) values (100019, 100002, 1);
insert into Tag(pid, userid, comments) values (100019, 100001, 'kw19 kw1');
insert into Tag(pid, userid, comments) values (100019, 100002, 'kw19 kw2');

insert into Rating(pid, userid, score) values (100020, 100006, 1);
insert into Rating(pid, userid, score) values (100020, 100007, 1);
insert into Tag(pid, userid, comments) values (100020, 100006, 'kw20 kw6');
insert into Tag(pid, userid, comments) values (100020, 100007, 'kw20 kw7');

insert into Rating(pid, userid, score) values (100021, 100020, 1);
insert into Rating(pid, userid, score) values (100021, 100024, 1);
insert into Tag(pid, userid, comments) values (100021, 100020, 'kw21 kw20');
insert into Tag(pid, userid, comments) values (100021, 100024, 'kw21 kw24');

insert into Rating(pid, userid, score) values (100022, 100018, 1);
insert into Rating(pid, userid, score) values (100022, 100016, 1);
insert into Tag(pid, userid, comments) values (100022, 100018, 'kw22 kw18');
insert into Tag(pid, userid, comments) values (100022, 100016, 'kw22 kw16');

insert into Rating(pid, userid, score) values (100023, 100001, 1);
insert into Rating(pid, userid, score) values (100023, 100002, 1);
insert into Tag(pid, userid, comments) values (100023, 100001, 'kw23 kw1');
insert into Tag(pid, userid, comments) values (100023, 100002, 'kw23 kw2');

insert into Rating(pid, userid, score) values (100024, 100003, 1);
insert into Rating(pid, userid, score) values (100024, 100004, 1);
insert into Tag(pid, userid, comments) values (100024, 100003, 'kw24 kw3');
insert into Tag(pid, userid, comments) values (100024, 100004, 'kw24 kw4');

insert into Tag(pid, userid, comments) values (200001, 100003, 'Looks like a skiing time!');
insert into Tag(pid, userid, comments) values (200001, 100024, 'I would love to go skiing with you. Invite me next time!!');
