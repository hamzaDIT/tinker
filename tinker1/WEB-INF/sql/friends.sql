SHOW ENGINE INNODB STATUS;
CREATE TABLE friends(
   friendid int(11) not null auto_increment primary key,
   username varchar(255) not null,
   userid mediumint(9) not null,
   FOREIGN KEY useridfk(userid)
   REFERENCES users(userid)
   ON UPDATE CASCADE
   ON DELETE RESTRICT
);