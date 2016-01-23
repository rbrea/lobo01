alter table USUARIO add column RESET_PASSWORD_VERIFICATION_CODE varchar(50);
alter table USUARIO add column RESET_PASSWORD_EXPIRATION datetime;

alter table USUARIO_AUD add column RESET_PASSWORD_VERIFICATION_CODE varchar(50);
alter table USUARIO_AUD add column RESET_PASSWORD_EXPIRATION datetime;

update DEV
set product_count = 0
where product_count is null;

alter table DEV modify column PRODUCT_COUNT int not null;