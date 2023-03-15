create table goods.folders
(
    id           uuid primary key,
    name         text      not null,
    size_rows    int       not null,
    size_columns int       not null,
    color        int       not null,
    deleted      bool      not null,
    created_at   timestamp not null default now()
)
