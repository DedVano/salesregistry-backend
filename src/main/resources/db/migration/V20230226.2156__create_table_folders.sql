create table goods.folders
(
    id           uuid primary key,
    name         text      not null,
    size_rows    int,
    size_columns int,
    color        int,
    deleted      bool      not null,
    created_at   timestamp not null default now()
)