create table goods.included_folders
(
    id                 uuid primary key,
    folder_id          uuid      not null references goods.folders (id),
    included_folder_id uuid      not null references goods.folders (id),
    row_in_folder      int       not null,
    column_in_folder   int       not null,
    created_at         timestamp not null default now()
)
