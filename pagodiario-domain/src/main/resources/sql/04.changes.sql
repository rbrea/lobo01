alter table SUPERVISOR_PAYROLL_ITEM modify column SUBTOTAL_BONUS decimal(34,18) null;
alter table SUPERVISOR_PAYROLL_ITEM modify column SUBTOTAL_DEV decimal(34,18) null;
alter table SUPERVISOR_PAYROLL_ITEM modify column SUBTOTAL_REDUCTION decimal(34,18) null;

alter table SUPERVISOR_PAYROLL_ITEM_AUD modify column SUBTOTAL_BONUS decimal(34,18) null;
alter table SUPERVISOR_PAYROLL_ITEM_AUD modify column SUBTOTAL_DEV decimal(34,18) null;
alter table SUPERVISOR_PAYROLL_ITEM_AUD modify column SUBTOTAL_REDUCTION decimal(34,18) null;

-- -------------------------------------------------------------------------------------------

SET SQL_SAFE_UPDATES = 0;

update BILL
set status = 'REDUCED'
where status = 'CANCELED';

update BILL
set status = 'CANCELED'
where status = 'FINALIZED';

