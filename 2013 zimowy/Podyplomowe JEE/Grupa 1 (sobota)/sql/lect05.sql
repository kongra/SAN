


begin;

drop table if exists emps;

create table emps (
  id bigserial not null,
  name varchar(32) unique not null,
  surname varchar(32) not null,
  
  primary key (id)
);

insert into emps(name, surname) values ('Jan', 'Kowalski');
insert into emps(name, surname) values ('Krzysztof', 'Nowak');

commit;
