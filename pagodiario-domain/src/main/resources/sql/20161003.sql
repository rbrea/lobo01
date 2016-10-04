use sgpd;

alter table product add column PRODUCT_TYPE varchar(100);
alter table product_aud add column PRODUCT_TYPE varchar(100);

alter table product add column STOCK_COUNT int not null default 0;
alter table product_aud add column STOCK_COUNT int;