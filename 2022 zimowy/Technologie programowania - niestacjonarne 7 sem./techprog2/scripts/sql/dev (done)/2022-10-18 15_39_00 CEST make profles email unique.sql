begin;

drop index if exists profile_email_index;
create unique index profile_email_index on profile(email);

commit;
