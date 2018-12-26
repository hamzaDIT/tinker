
drop table users;
create table users(
    userid MEDIUMINT NOT NULL AUTO_INCREMENT,
    username CHAR(30),
    firstName varchar(30),
    lastName varchar(30),
    email varchar(30),
    password varchar(30),
    birthday varchar(30),
    gender varchar(30),
    PRIMARY KEY (userid));
