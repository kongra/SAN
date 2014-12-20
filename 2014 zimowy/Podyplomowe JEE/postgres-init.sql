-- MOJA ZAWARTOŚĆ PLIKU pg_hba.conf

-- # DO NOT DISABLE!
-- # If you change this first entry you will need to make sure that the
-- # database superuser can access the database using some other method.
-- # Noninteractive access to all databases is required during automatic
-- # maintenance (custom daily cronjobs, replication, and similar tasks).
-- #
-- # Database administrative login by Unix domain socket
-- local   all             postgres                                peer

-- # TYPE  DATABASE        USER            ADDRESS                 METHOD

-- # "local" is for Unix domain socket connections only
-- local   all             all                                     md5
-- # IPv4 local connections:
-- host    all             all             127.0.0.1/32            md5
-- # IPv6 local connections:
-- host    all             all             ::1/128                 md5
-- # Allow replication connections from localhost, by a user with the
-- # replication privilege.
-- #local   replication     postgres                                peer
-- #host    replication     postgres        127.0.0.1/32            md5
-- #host    replication     postgres        ::1/128                 md5

-- Polecenia poniższe wykonujemy jako użytkownik bazy danych postgres.

DROP DATABASE IF EXISTS "JEE3";
DROP SCHEMA   IF EXISTS "jee3";
DROP ROLE     IF EXISTS "jee3";

CREATE DATABASE "JEE3" ENCODING 'utf-8';
CREATE ROLE "jee3" ENCRYPTED PASSWORD 'md5381f6c77d1af1238d1c2d99f6c572723' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN;
CREATE SCHEMA "jee3" AUTHORIZATION "jee3";

-- Po wykonaniu powyższych poleceń wylogowujemy się z konta postgres i łączymy się z bazą:
-- $ psql JEE3 -U jee3
