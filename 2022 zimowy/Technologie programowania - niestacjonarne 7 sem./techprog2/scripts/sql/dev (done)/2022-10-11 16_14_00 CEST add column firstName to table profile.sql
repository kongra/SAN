begin;

alter table profile
drop column if exists lastName;

alter table profile
add column lastName text not null;

commit;
