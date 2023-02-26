create table goods.products
(
    id             uuid primary key,
    name           text      not null,
    price          int       not null,
    variable_price bool      not null,
    category_id    uuid      not null references goods.products_categories (id),
    color          int,
    deleted        bool      not null,
    created_at     timestamp not null default now()
)