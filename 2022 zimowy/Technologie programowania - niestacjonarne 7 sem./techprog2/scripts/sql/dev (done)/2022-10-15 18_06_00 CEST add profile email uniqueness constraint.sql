begin;

drop index if exists profile_email_index;

create unique index if not exists
profile_email_index on profile (email);

commit;
