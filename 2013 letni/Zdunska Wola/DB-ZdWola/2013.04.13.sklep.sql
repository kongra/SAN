drop table if exists categories;

create table categories (
  id bigserial,
  name varchar(32) not null unique,
  parent bigint,
  
  primary key(id)
);

insert into categories(name, parent) values('Sporty letnie', null);
insert into categories(name, parent) values('Sporty zimowe', null);

insert into categories(name, parent) values('Rowery', 
       (select id from categories where name='Sporty letnie'));
       
insert into categories(name, parent) values('Tenis', 
       (select id from categories where name='Sporty letnie'));

insert into categories(name, parent) values('Narciarstwo', 
       (select id from categories where name='Sporty zimowe'));
       
insert into categories(name, parent) values('Łyżwiarstwo/hokej',
       (select id from categories where name='Sporty zimowe'));
       
       
drop table if exists products;

create table products (
  id bigserial,
  name varchar(32) not null,
  description varchar(256),
  category bigint not null,
  
  primary key(id)
);

insert into products(name, category) values('Rakieta Wilson',
       (select id from categories where name='Tenis'));
       

drop table if exists prices;

create table prices (
  id bigserial,
  pricelist bigint not null,
  product bigint not null,
  
  value real not null,
  
  primary key(id)
);


drop table if exists pricelists;

create table pricelists (
  id bigserial,
  since timestamp,
  until timestamp, 
  
  primary key(id)
);

insert into pricelists(since, until) values(now(), null);

insert into prices(pricelist, product, value)
values
((select id from pricelists limit 1),
 (select id from products where name='Rakieta Wilson'),
 300);