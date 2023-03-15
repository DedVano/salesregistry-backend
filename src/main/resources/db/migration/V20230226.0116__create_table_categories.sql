create table goods.products_categories
(
    id              uuid      not null primary key,
    name            text      not null,
    order_in_report int       not null,
    deleted         bool      not null,
    created_at      timestamp not null default now()
)
