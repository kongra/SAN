drop schema public;

create schema cots;

create user cots_owner with password 'cots_owner';

grant connect                        on                database cots to cots_owner;
grant usage, create                  on                  schema cots to cots_owner;
grant select, insert, update, delete on all tables    in schema cots to cots_owner;
grant usage, select                  on all sequences in schema cots to cots_owner;

grant connect                        on                database cots to cots_user;
grant usage                          on                  schema cots to cots_user;
grant select, insert, update, delete on all tables    in schema cots to cots_user;
grant usage, select                  on all sequences in schema cots to cots_user;

alter default privileges for user cots_owner in schema cots grant select, insert, update, delete on tables    to cots_user;
alter default privileges for user cots_owner in schema cots grant usage, select                  on sequences to cots_user;
