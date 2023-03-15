alter table goods.included_products rename column order_in_folder to row_in_folder;
alter table goods.included_products add column_in_folder int;
alter table goods.included_folders rename column order_in_folder to row_in_folder;
alter table goods.included_folders add column_in_folder int;
