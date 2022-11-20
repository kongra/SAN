begin;

drop table if exists profile;

create table profile (
  id        bigint not null,
  email     text   not null,
  firstName text   not null,
  
  primary key (id)
);

commit;
