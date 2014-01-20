begin;

drop table if exists profiles;

create table profiles (
  id bigserial primary key,
  login text not null unique,
  pass text not null,
  email text not null unique
);

insert into profiles(login, pass, email) values ('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'root@localhost');
insert into profiles(login, pass, email) values ('guest', '35675e68f4b5af7b995d9205ad0fc43842f16450', 'guest@localhost');

commit;