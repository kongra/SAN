-- as postgres:
-- CREATE EXTENSION pgcrypto;

BEGIN;

CREATE TYPE profile_role AS ENUM('Admin', 'Trainer');

CREATE TABLE profiles(
  id      BIGSERIAL NOT NULL,
  email        TEXT NOT NULL UNIQUE,
  passwd       TEXT NOT NULL,
  role profile_role NOT NULL,

  PRIMARY KEY(id)
);

CREATE TABLE courses(
  id          BIGSERIAL NOT NULL,
  name             TEXT NOT NULL UNIQUE,
  start_date  TIMESTAMP NOT NULL,
  end_date    TIMESTAMP NOT NULL,
  description      TEXT,
  max_participants SMALLINT NOT NULL,

  PRIMARY KEY(id)
);

CREATE TYPE  part_status AS ENUM ('Primary', 'Reserve');
CREATE TABLE participations(
  id         BIGSERIAL   NOT NULL,
  email      TEXT        NOT NULL,
  first_name TEXT        NOT NULL,
  last_name  TEXT        NOT NULL,
  phone      TEXT        NOT NULL,
  course_id  BIGINT      NOT NULL references courses(id),
  regstamp   TIMESTAMP   NOT NULL DEFAULT now(),
  confstamp  TIMESTAMP,
  status     part_status NOT NULL DEFAULT 'Primary',
  uuid       UUID        NOT NULL UNIQUE DEFAULT uuid_generate_v1(),

  PRIMARY KEY(id)
);

CREATE INDEX part_course_id_idx ON participations(course_id);

COMMIT;

-- REVOKE:
-- BEGIN;
-- DROP TABLE participations;
-- DROP TABLE courses;
-- DROP TABLE profiles;
-- DROP TYPE  profile_role;
-- DROP TYPE  part_status;
-- COMMIT;
