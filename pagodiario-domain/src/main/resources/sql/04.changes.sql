alter table SUPERVISOR_PAYROLL_ITEM modify column SUBTOTAL_BONUS decimal(34,18) null;
alter table SUPERVISOR_PAYROLL_ITEM modify column SUBTOTAL_DEV decimal(34,18) null;
alter table SUPERVISOR_PAYROLL_ITEM modify column SUBTOTAL_REDUCTION decimal(34,18) null;

alter table SUPERVISOR_PAYROLL_ITEM_AUD modify column SUBTOTAL_BONUS decimal(34,18) null;
alter table SUPERVISOR_PAYROLL_ITEM_AUD modify column SUBTOTAL_DEV decimal(34,18) null;
alter table SUPERVISOR_PAYROLL_ITEM_AUD modify column SUBTOTAL_REDUCTION decimal(34,18) null;

-- -------------------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;

alter table CLIENT add column REDUCTION_MARK datetime null;
alter table CLIENT_AUD add column REDUCTION_MARK datetime null;

update BILL
set status = 'REDUCED'
where status = 'CANCELED';

update BILL
set status = 'CANCELED'
where status = 'FINALIZED';

--------------------------------

alter table BILL add column TOTAL_AMOUNT_TO_LIQ decimal(34,18) null;
alter table BILL_AUD add column TOTAL_AMOUNT_TO_LIQ decimal(34,18) null;


update BILL
set TOTAL_AMOUNT_TO_LIQ = TOTAL_AMOUNT;

update BILL_AUD
set TOTAL_AMOUNT_TO_LIQ = TOTAL_AMOUNT;


-- ---------------------------------------------------------

alter table BILL drop column WEEK_SUNDAY;
alter table BILL drop column WEEK_MONDAY;
alter table BILL drop column WEEK_TUESDAY;
alter table BILL drop column WEEK_WEDNESDAY;
alter table BILL drop column WEEK_THURSDAY;
alter table BILL drop column WEEK_FRIDAY;
alter table BILL drop column WEEK_SATURDAY;

alter table BILL_AUD drop column WEEK_SUNDAY;
alter table BILL_AUD drop column WEEK_MONDAY;
alter table BILL_AUD drop column WEEK_TUESDAY;
alter table BILL_AUD drop column WEEK_WEDNESDAY;
alter table BILL_AUD drop column WEEK_THURSDAY;
alter table BILL_AUD drop column WEEK_FRIDAY;
alter table BILL_AUD drop column WEEK_SATURDAY;

alter table BILL add column WEEK_SUNDAY varchar(1) not null default 'S';
alter table BILL add column WEEK_MONDAY varchar(1) not null default 'S';
alter table BILL add column WEEK_TUESDAY varchar(1) not null default 'S';
alter table BILL add column WEEK_WEDNESDAY varchar(1) not null default 'S';
alter table BILL add column WEEK_THURSDAY varchar(1) not null default 'S';
alter table BILL add column WEEK_FRIDAY varchar(1) not null default 'S';
alter table BILL add column WEEK_SATURDAY varchar(1) not null default 'S';

alter table BILL_AUD add column WEEK_SUNDAY varchar(1);
alter table BILL_AUD add column WEEK_MONDAY varchar(1);
alter table BILL_AUD add column WEEK_TUESDAY varchar(1);
alter table BILL_AUD add column WEEK_WEDNESDAY varchar(1);
alter table BILL_AUD add column WEEK_THURSDAY varchar(1);
alter table BILL_AUD add column WEEK_FRIDAY varchar(1);
alter table BILL_AUD add column WEEK_SATURDAY varchar(1);



