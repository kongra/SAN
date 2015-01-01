begin;

drop table if exists reservations;
drop table if exists books;
drop table if exists users;

create table users (
  id       bigserial not null,
  login    text      not null unique,
  password text      not null,
  address  text,
  email    text      not null,
  
  primary key (id)
);

create table books (
  id     bigserial not null,
  title  text      unique not null,
  author text      not null,
  
  primary key (id)
);

create table reservations (
  id    bigserial not null,
  since timestamp not null,
  until timestamp,
  
  bookid bigint   not null references books(id),
  userid bigint   not null references users(id), 
  
  primary key (id)
);

insert into users (login, password, email) values ('janek', md5('1234'), 'jan@gmail.com');
insert into users (login, password, email) values ('anna',  md5('abcd'), 'anna@gmail.com');
insert into users (login, password, email) values ('tomek', md5('xyzv'), 'tom@wp.pl');

insert into books (title, author) values ('Mały Książę', 'Antoine de SE');
insert into books (title, author) values ('Potop', 'Henryk Sienkiewicz');
insert into books (title, author) values ('Hobbit', 'Tolkien');

insert into reservations (since, userid, bookid) 
values (
  current_timestamp,
  (select id from users where login='janek'),
  (select id from books where title='Potop')
);

select * from reservations as r, users as u, books as b where
  r.bookid = b.id and
  r.userid = u.id;

commit;
