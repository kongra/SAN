begin;
drop table if exists profiles;
create table profiles (
  id     bigserial          not null,
  login  varchar(32) unique not null,
  passwd varchar(32)        not null,

  primary key(id)
);

insert into profiles(login, passwd) values ('john' , md5('12345'));
insert into profiles(login, passwd) values ('admin', md5('root'));
insert into profiles(login, passwd) values ('anna' , md5('qwerty'));

commit;
