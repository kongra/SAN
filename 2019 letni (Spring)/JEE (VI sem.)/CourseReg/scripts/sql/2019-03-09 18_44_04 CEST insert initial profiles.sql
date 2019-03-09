BEGIN;

INSERT INTO profiles(email, passwd, role)
VALUES
  ('kongra@gmail.com', crypt('buziaczek123', gen_salt('bf')), 'Trainer'),
  ('root@localhost',   crypt('root',         gen_salt('bf')), 'Admin'  ),
  ('pangeon@tlen.pl',  crypt('kwiatek55',    gen_salt('bf')), 'Admin'  );

COMMIT;

-- REVOKE:
-- BEGIN;
-- DELETE FROM profiles
-- WHERE email = 'kongra@gmail.com' OR
--       email = 'root@localhost'   OR
--       email = 'pangeon@tlen.pl';
-- COMMIT;
