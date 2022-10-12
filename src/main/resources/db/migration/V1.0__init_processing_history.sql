create table if not exists processing_history
(
    id           uuid primary key,
    file_name    varchar(50) not null,
    processed_at timestamp   not null
)