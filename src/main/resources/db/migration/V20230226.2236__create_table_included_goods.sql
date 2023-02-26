create table goods.included_goods
(
    id              uuid primary key,
    folder_id       uuid      not null references goods.folders (id),
    good_id         uuid      not null references goods.goods (id),
    order_in_folder int,
    created_at      timestamp not null default now()
)