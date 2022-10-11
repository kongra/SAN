begin;

alter table profile
add column lastName text not null;

commit;
