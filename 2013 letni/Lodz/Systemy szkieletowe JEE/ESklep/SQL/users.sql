drop table if exists users;

create table users (
  id bigserial,
  login  varchar(16) not null unique,
  passwd varchar(32) not null,
  
  primary key(id)
);

insert into users(login, passwd) values ('user', md5('12345'));
insert into users(login, passwd) values ('admin', md5('ueee'));
