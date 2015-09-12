use sgpd;

SET SQL_SAFE_UPDATES = 0;

alter table BILL add column OVERDUE_DAYS_FLAG DATETIME;


alter table SUPERVISOR_PAYROLL_ITEM add column SUBTOTAL_BONUS decimal(34,18) not null; 
alter table SUPERVISOR_PAYROLL_ITEM add column SUBTOTAL_DEV decimal(34,18) not null;
alter table SUPERVISOR_PAYROLL_ITEM add column SUBTOTAL_REDUCTION decimal(34,18) not null;

create table COLLECTOR (ID bigint not null auto_increment, CREATED_BY varchar(40), CREATED_DATE DATETIME not null, UPDATED_BY varchar(40), UPDATED_DATE DATETIME, DESCRIPTION varchar(140), ZONE bigint, primary key (ID)) ENGINE=InnoDB;
create table COLLECTOR_AUD (ID bigint not null, REV integer not null, REVTYPE tinyint, DESCRIPTION varchar(140), ZONE bigint, primary key (ID, REV)) ENGINE=InnoDB;

alter table COLLECTOR_AUD add constraint FK_nsmf10jro4qv0w8yukqvdw6fk foreign key (REV) references REVINFO (REV);