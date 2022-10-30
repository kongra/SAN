begin;

alter table profile add column uuid text not null unique;

commit;
