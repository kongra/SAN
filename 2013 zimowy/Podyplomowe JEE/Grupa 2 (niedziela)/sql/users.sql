begin;

drop table if exists users;

create table users (
  id bigserial primary key,
  login varchar(255) unique not null,
  passwd varchar(32) not null
);

insert into users(login, passwd) values ('janek', md5('jan'));
insert into users(login, passwd) values ('adam', md5('aaa'));
insert into users(login, passwd) values ('ania', md5('1234'));

commit;
