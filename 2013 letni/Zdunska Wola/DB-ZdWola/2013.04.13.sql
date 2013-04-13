drop table if exists points;

create table points (
  x real,
  y real,
  colour varchar(16),
  
  primary key(x, y)
);

-- create unique index points_x_idx on points(x);

insert into points values(1, 2, 'BLACK');
insert into points values(3, 4, 'WHITE');
insert into points values(1, 3, 'WHITE');
