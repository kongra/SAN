begin;

create table profile (
  id        bigserial not null,
  email     text      not null,
  firstName text      not null,
  
  primary key (id)
);

commit;
