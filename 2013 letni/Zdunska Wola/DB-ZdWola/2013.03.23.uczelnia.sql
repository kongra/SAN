drop table if exists studenci;

create table studenci(
  id bigserial,

  nr_indeksu integer not null,
  imie varchar(64) not null,
  nazwisko varchar(128) not null,
  adres varchar(256),
  data_ur date,
  pesel bigint,
  
  primary key(id)
);

create unique index studenci_nr_indeksu_idx on studenci(nr_indeksu);
create unique index studenci_pesel_idx on studenci(pesel);

insert into studenci(nr_indeksu, imie, nazwisko) 
values (61655, 'Jacek', 'Majewski');

insert into studenci(nr_indeksu, imie, nazwisko) 
values (98215, 'Konrad', 'Grzanek');

insert into studenci(nr_indeksu, imie, nazwisko) 
values (61664, 'Piotr', 'Słoczyński');



