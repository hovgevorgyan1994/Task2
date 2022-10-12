alter table processing_history
add column failed_at timestamp;

alter table processing_history
add column failed_reason varchar(255)


