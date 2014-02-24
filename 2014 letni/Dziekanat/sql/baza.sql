-- $ su postgres
-- $ createdb -E utf8 SAN
-- $ psql SAN

create role san with encrypted password 'san' nocreatedb nocreateuser login;
create schema san authorization san;

-- $ exit
-- $ psql SAN -U san
