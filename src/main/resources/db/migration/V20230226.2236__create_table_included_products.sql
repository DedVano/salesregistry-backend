create table goods.included_products
(
    id               uuid primary key,
    folder_id        uuid      not null references goods.folders (id),
    product_id       uuid      not null references goods.products (id),
    row_in_folder    int       not null,
    column_in_folder int       not null,
    created_at       timestamp not null default now()
)
