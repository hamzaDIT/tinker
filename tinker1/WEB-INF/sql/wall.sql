

CREATE TABLE wall(
   wallid mediumint(9) not null auto_increment primary key,
   comment varchar(255) not null,
   fromuserid int(11) not null,
   touserid varchar(255) not null,
   userid mediumint(9) not null,
   FOREIGN KEY fk_userid(userid)
   REFERENCES users(userid)
   ON UPDATE CASCADE
   ON DELETE RESTRICT
);