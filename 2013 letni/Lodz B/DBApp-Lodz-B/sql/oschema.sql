drop table if exists otypes;

create table otypes (
  id serial,
  name varchar(128) unique,
  
  primary key(id)
);
