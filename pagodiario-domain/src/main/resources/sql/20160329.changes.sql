use sgpd;

alter table PAYROLL_ITEM_COLLECT add column CARDS_COUNT_REAL int(11) not null default 0;
alter table PAYROLL_ITEM_COLLECT_AUD add column CARDS_COUNT_REAL int(11);
alter table PAYROLL_COLLECT add column TOTAL_CARDS_COUNT_REAL int(11) not null default 0;
alter table PAYROLL_COLLECT_AUD add column TOTAL_CARDS_COUNT_REAL int(11);

