
alter table BILL add column completed_date datetime null;
alter table BILL_AUD add column completed_date datetime null;

create table CONCILIATION_ITEM_COLLECT (ID bigint not null auto_increment, CREATED_BY varchar(40), CREATED_DATE DATETIME not null, UPDATED_BY varchar(40), UPDATED_DATE DATETIME, AMOUNT decimal(34,18), DESCRIPTION varchar(255), BILL_ID bigint, PAYROLL_ITEM_COLLECT_ID bigint, primary key (ID)) ENGINE=InnoDB;
create table CONCILIATION_ITEM_COLLECT_AUD (ID bigint not null, REV integer not null, REVTYPE tinyint, AMOUNT decimal(34,18), DESCRIPTION varchar(255), BILL_ID bigint, PAYROLL_ITEM_COLLECT_ID bigint, primary key (ID, REV)) ENGINE=InnoDB;

create table PAYROLL_ITEM_COLLECT (ID bigint not null auto_increment, CREATED_BY varchar(40), CREATED_DATE DATETIME not null, UPDATED_BY varchar(40), UPDATED_DATE DATETIME, AMOUNT_TO_PAY decimal(34,18), CARDS_COUNT integer not null, COMMISSION decimal(34,18), COMMISSION_PERCENTAGE decimal(34,18), TOTAL_AMOUNT decimal(34,18), TOTAL_PAYMENT decimal(34,18), COLLECTOR_ID bigint, PAYROLL_COLLECT_ID bigint, primary key (ID)) ENGINE=InnoDB;
create table PAYROLL_ITEM_COLLECT_AUD (ID bigint not null, REV integer not null, REVTYPE tinyint, AMOUNT_TO_PAY decimal(34,18), CARDS_COUNT integer, COMMISSION decimal(34,18), COMMISSION_PERCENTAGE decimal(34,18), TOTAL_AMOUNT decimal(34,18), TOTAL_PAYMENT decimal(34,18), COLLECTOR_ID bigint, PAYROLL_COLLECT_ID bigint, primary key (ID, REV)) ENGINE=InnoDB;
create table PAYROLL_ITEM_COLLECT_CONCILIATION_ITEM_COLLECT (PAYROLL_ITEM_COLLECT_ID bigint not null, conciliationItemCollectList_ID bigint not null) ENGINE=InnoDB;
create table PAYROLL_ITEM_COLLECT_CONCILIATION_ITEM_COLLECT_AUD (REV integer not null, PAYROLL_ITEM_COLLECT_ID bigint not null, conciliationItemCollectList_ID bigint not null, REVTYPE tinyint, primary key (REV, PAYROLL_ITEM_COLLECT_ID, conciliationItemCollectList_ID)) ENGINE=InnoDB;

create table PAYROLL_COLLECT (ID bigint not null auto_increment, CREATED_BY varchar(40), CREATED_DATE DATETIME not null, UPDATED_BY varchar(40), UPDATED_DATE DATETIME, PAYROLL_DATE DATETIME, STATUS varchar(255) not null, TOTAL_AMOUNT decimal(34,18), TOTAL_AMOUNT_TO_PAY decimal(34,18), TOTAL_CARDS_COUNT integer not null, TOTAL_PAYMENT decimal(34,18), primary key (ID)) ENGINE=InnoDB;
create table PAYROLL_COLLECT_AUD (ID bigint not null, REV integer not null, REVTYPE tinyint, PAYROLL_DATE DATETIME, STATUS varchar(255), TOTAL_AMOUNT decimal(34,18), TOTAL_AMOUNT_TO_PAY decimal(34,18), TOTAL_CARDS_COUNT integer, TOTAL_PAYMENT decimal(34,18), primary key (ID, REV)) ENGINE=InnoDB;
create table PAYROLL_COLLECT_PAYROLL_ITEM_COLLECT (PAYROLL_COLLECT_ID bigint not null, payrollItemCollectList_ID bigint not null) ENGINE=InnoDB;
create table PAYROLL_COLLECT_PAYROLL_ITEM_COLLECT_AUD (REV integer not null, PAYROLL_COLLECT_ID bigint not null, payrollItemCollectList_ID bigint not null, REVTYPE tinyint, primary key (REV, PAYROLL_COLLECT_ID, payrollItemCollectList_ID)) ENGINE=InnoDB;

alter table CONCILIATION_ITEM_COLLECT add constraint FK_m5fa29dpf0anxaacjhyiqu3h1 foreign key (BILL_ID) references BILL (ID);
alter table CONCILIATION_ITEM_COLLECT add constraint FK_lwee9py2teiqu9603mrhfpyng foreign key (PAYROLL_ITEM_COLLECT_ID) references PAYROLL_ITEM_COLLECT (ID);
alter table CONCILIATION_ITEM_COLLECT_AUD add constraint FK_6pycaqoke2q7o3muywqfs2dnj foreign key (REV) references REVINFO (REV);


alter table PAYROLL_COLLECT_AUD add constraint FK_rdil64nh2ma33p2b2ntgx21bp foreign key (REV) references REVINFO (REV);
alter table PAYROLL_COLLECT_PAYROLL_ITEM_COLLECT add constraint FK_j135dwyh18bbt9sf1puvdd8ko foreign key (payrollItemCollectList_ID) references PAYROLL_ITEM_COLLECT (ID);
alter table PAYROLL_COLLECT_PAYROLL_ITEM_COLLECT add constraint FK_n9gcgbw05ilfywrcggcdoxniw foreign key (PAYROLL_COLLECT_ID) references PAYROLL_COLLECT (ID);
alter table PAYROLL_COLLECT_PAYROLL_ITEM_COLLECT_AUD add constraint FK_fjo5rfm017hxy4psitqjkn1a3 foreign key (REV) references REVINFO (REV);


alter table PAYROLL_ITEM_COLLECT add constraint FK_lcsyy38attc1korgiao86pa2b foreign key (COLLECTOR_ID) references COLLECTOR (ID);
alter table PAYROLL_ITEM_COLLECT add constraint FK_m9il9b09grrlsn3x7knue02tv foreign key (PAYROLL_COLLECT_ID) references PAYROLL_COLLECT (ID);
alter table PAYROLL_ITEM_COLLECT_AUD add constraint FK_4nlrptyffufir3fgahovhbof4 foreign key (REV) references REVINFO (REV);
alter table PAYROLL_ITEM_COLLECT_CONCILIATION_ITEM_COLLECT add constraint FK_dlpnxd738vxouonig9gxpelt5 foreign key (conciliationItemCollectList_ID) references CONCILIATION_ITEM_COLLECT (ID);
alter table PAYROLL_ITEM_COLLECT_CONCILIATION_ITEM_COLLECT add constraint FK_i6m670ywdrfo2v4nqxfiwn3go foreign key (PAYROLL_ITEM_COLLECT_ID) references PAYROLL_ITEM_COLLECT (ID);
alter table PAYROLL_ITEM_COLLECT_CONCILIATION_ITEM_COLLECT_AUD add constraint FK_lfa95mtpv11v7c8btfoqvi7a9 foreign key (REV) references REVINFO (REV);


