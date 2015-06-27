CREATE SCHEMA SAMPLE_SCHEMA;

CREATE TABLE SAMPLE (
  	`id` BIGINT NOT NULL AUTO_INCREMENT ,
  	`description` VARCHAR(100) NULL ,
	PRIMARY KEY (`id`) 
);

CREATE TABLE CLIENT (
	id bigint not null auto_increment,
	created_by  varchar(40) null,
	created_date timestamp not null,
	updated_by varchar(40) null,
	updated_date timestamp null,
	gateway_id	varchar(50) not null,
	provider_id  INTEGER not null,
	supplier_id INTEGER not null,
	country_id varchar(20) not null,
	tax_payer_id INTEGER null,
	ADDRESS_CODE INTEGER,
	INDEX IDX_CLIENT_GATEWAY (GATEWAY_ID, PROVIDER_ID, SUPPLIER_ID),
	INDEX IDX_CLIENT_COUNTRY (COUNTRY_ID, TAX_PAYER_ID)
);
