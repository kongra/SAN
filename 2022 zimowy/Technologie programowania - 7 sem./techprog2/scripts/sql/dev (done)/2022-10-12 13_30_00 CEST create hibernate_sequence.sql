begin;

drop sequence if exists hibernate_sequence;

create sequence if not exists
hibernate_sequence as bigint
start with 1
increment by 1
cache 10;

commit;
