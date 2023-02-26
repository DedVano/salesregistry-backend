create table goods.included_folders
(
    id                 uuid primary key,
    folder_id          uuid      not null references goods.folders (id),
    included_folder_id uuid      not null references goods.folders (id),
    order_in_folder    int,
    created_at         timestamp not null default now()
)