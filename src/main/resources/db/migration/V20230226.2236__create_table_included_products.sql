create table goods.included_products
(
    id              uuid primary key,
    folder_id       uuid      not null references goods.folders (id),
    product_id      uuid      not null references goods.products (id),
    order_in_folder int,
    created_at      timestamp not null default now()
)