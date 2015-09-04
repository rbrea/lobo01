use sgpd;

alter table BILL add column OVERDUE_DAYS_FLAG DATETIME;


alter table SUPERVISOR_PAYROLL_ITEM add column SUBTOTAL_BONUS decimal(34,18) not null; 
alter table SUPERVISOR_PAYROLL_ITEM add column SUBTOTAL_DEV decimal(34,18) not null;
alter table SUPERVISOR_PAYROLL_ITEM add column SUBTOTAL_REDUCTION decimal(34,18) not null;

