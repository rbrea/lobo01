insert into USUARIO(ID, USERNAME, PASSWORD, TOKEN, CREATED_DATE, IS_ADMIN) values (1, 'roher', 'WObWhK1hy5l9ywFauZDTJtYYg5IwSc9O', '11e3ab48-01e9-4994-b7d0-aaa31662864c', curdate(), true);

insert into CLIENT(ID, CREATED_DATE, ADDRESS, CITY, DOCUMENT_NUMBER, DOCUMENT_TYPE, EMAIL, NAME, PHONE, COMPANY_ADDRESS, COMPANY_CITY, COMPANY_PHONE, COMPANY_TYPE) values (1, curdate(), 'francia 3444', 'Castelar', 24777677, 'DNI', 'elianadel@fdsa.com', 'Eliana Delgado', '3243-4322', 'saraza 1234', 'Castelar', '15-6789-5434', 'Comercio');
insert into CLIENT (ID, CREATED_DATE, ADDRESS, CITY, DOCUMENT_NUMBER, DOCUMENT_TYPE, EMAIL, NAME, NEAR_STREETS, PHONE, COMPANY_ADDRESS, COMPANY_CITY, COMPANY_PHONE, COMPANY_TYPE) values (2, curdate(), 'francia 3445', 'Moron', 24777673, 'DNI', 'fra234@fdsa.com', 'Francisco Delgado', 'entre saraza y martin co.', '3246-4322', 'arriba 11234', 'Moron', '15-6783-5134', 'Particular');
insert into CLIENT (ID, CREATED_DATE, ADDRESS, CITY, DOCUMENT_NUMBER, DOCUMENT_TYPE, EMAIL, NAME, NEAR_STREETS, PHONE, COMPANY_ADDRESS, COMPANY_CITY, COMPANY_PHONE, COMPANY_TYPE) values (3, curdate(), 'francia 34421', 'Capital Federal', 21212121212, 'CUIT', 'empresa3@fdsa.com', 'La Serenisima', 'entre donato alvarez y gioja', '3211-4312', 'ajoba 909', 'Capital Federal', '15-6789-5434', 'Empresa');

insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (1, curdate(), 'P001', 'Toalla XL', '100.00');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (2, curdate(), 'P002', 'Producto 02', '1300.00');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (3, curdate(), 'P003', 'Producto 03', '140.00');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (4, curdate(), 'P004', 'Producto 04', '170.00');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (5, curdate(), 'P005', 'Producto 05', '180.50');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (6, curdate(), 'P006', 'Producto 06', '1900.00');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (7, curdate(), 'P007', 'Producto 07', '2100.00');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (8, curdate(), 'P008', 'Producto 08', '50.00');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (9, curdate(), 'P009', 'Producto 09', '1210.00');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (10, curdate(), 'P0010', 'Producto 10', '170.20');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (11, curdate(), 'P0011', 'Producto 11', '199.99');
insert into PRODUCT (ID, created_date, CODE, DESCRIPTION, PRICE) values (12, curdate(), 'P0012', 'Producto 12', '112.33');

insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (1, curdate(), 'Rodrigo Martinez', 'DNI', 29788987, true, null);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (2, curdate(), 'Martin Rodriguez', 'DNI', 29788999, false, 1);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (3, curdate(), 'Eliana Fernandez', 'DNI', 29778987, false, null);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (4, curdate(), 'Fernanda Delgado', 'DNI', 29748987, false, null);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (5, curdate(), 'Ramiro Gonzalez', 'DNI', 29788117, false, 1);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (6, curdate(), 'Gonzalo Ramirez', 'DNI', 29711987, false, null);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (7, curdate(), 'Tobias Hernandez', 'DNI', 29711111, false, 1);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (8, curdate(), 'Maria Ines Brea', 'DNI', 23238987, false, null);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (9, curdate(), 'Jose Maria Hernandez', 'DNI', 21788987, false, null);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (10, curdate(), 'Facundo Brea', 'DNI', 19788987, false, null);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (11, curdate(), 'Mirta Alicia Gomez', 'DNI', 12788987, false, null);
insert into TRADER(ID, CREATED_DATE, NAME, DOCUMENT_TYPE, DOCUMENT_NUMBER, SUPERVISOR, parent_ID) values (12, curdate(), 'Angel Franz', 'DNI', 11788007, false, null);

insert into TRADER_TRADER(TRADER_ID, traders_ID) values (1, 2);
insert into TRADER_TRADER(TRADER_ID, traders_ID) values (1, 2);
insert into TRADER_TRADER(TRADER_ID, traders_ID) values (1, 5);
insert into TRADER_TRADER(TRADER_ID, traders_ID) values (1, 7);

insert into BILL (ID, CREATED_DATE, COLLECTOR_ID, CREDIT_NUMBER, START_DATE, END_DATE, OVERDUE_DAYS, REMAINING_AMOUNT, STATUS, TOTAL_AMOUNT, TOTAL_DAILY_INSTALLMENT, client_ID, trader_ID) values (1, curdate(), 11, '1234', curdate(), curdate() + 20, 0, '190.00', 'ACTIVE', '200.00', '10.00', 1, 1);
insert into BILL_PRODUCT (ID, CREATED_DATE, AMOUNT, COUNT, DAILY_INSTALLMENT, bill_ID, product_ID) values (1, curdate(), '200.00', 2, '10.00', 1, 1);
insert into BILL_BILL_PRODUCT (BILL_ID, billProducts_ID) values (1, 1);
insert into PAYMENT (ID, CREATED_DATE, AMOUNT, COLLECTOR_ID, PAYMENT_DATE, bill_ID) values (1, curdate(), '10.00', 11, curdate(), 1);
insert into BILL_PAYMENT (BILL_ID, payments_ID) values (1, 1);
