alter table BILL add column WEEK_OF_YEAR int null;
alter table BILL add column MONTH int null;
alter table BILL add column YEAR int null;

alter table BILL_AUD add column WEEK_OF_YEAR int null;
alter table BILL_AUD add column MONTH int null;
alter table BILL_AUD add column YEAR int null;

alter table DEV add column product_ID bigint;
alter table DEV_AUD add column product_ID bigint;

alter table DEV add column PRODUCT_COUNT integer;
alter table DEV_AUD add column PRODUCT_COUNT integer;