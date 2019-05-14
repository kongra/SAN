BEGIN;

CREATE TABLE profiles(
  id               BIGSERIAL NOT NULL,
  email            TEXT      NOT NULL UNIQUE,
  passwd           TEXT      NOT NULL,

  first_name       TEXT,
  last_name        TEXT,

  creation_stamp   TIMESTAMPTZ NOT NULL DEFAULT now(),
  last_login_stamp TIMESTAMPTZ,

  PRIMARY KEY(id)
);

INSERT INTO profiles(email, passwd)
VALUES('kongra@gmail.com', crypt('kon12345', gen_salt('bf')));

INSERT INTO profiles(email, passwd)
VALUES('john.doe@gmail.com', crypt('jon12345', gen_salt('bf')));

INSERT INTO profiles(email, passwd)
VALUES('kgrzanek@san.edu.pl', crypt('kon12345', gen_salt('bf')));

COMMIT;

-- REVOKE:
--BEGIN;
--DELETE FROM profiles;
--DROP  TABLE profiles;
--COMMIT;