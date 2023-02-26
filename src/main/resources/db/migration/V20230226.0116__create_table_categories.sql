create table goods.goods_categories
(
    id              uuid      not null primary key,
    name            text      not null,
    order_in_report int,
    deleted         bool      not null,
    created_at      timestamp not null default now()
)