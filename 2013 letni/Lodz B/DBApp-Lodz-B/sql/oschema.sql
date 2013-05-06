drop table if exists ovalues;
drop table if exists oobjects;
drop table if exists oprops;
drop table if exists otypes;

create table otypes (
  id serial,
  name varchar(128) unique,
  
  primary key(id)
);

create table oprops (
  id serial,
  name varchar(128) unique,
  
  primary key(id)
);


create table oobjects (
  id bigserial,
  otype int not null references otypes(id),
  
  primary key(id)
);

create table ovalues (
  oobject bigint not null references oobjects(id),
  oprop int not null references oprops(id),
  val varchar(256),

  primary key(oobject, oprop)
);

