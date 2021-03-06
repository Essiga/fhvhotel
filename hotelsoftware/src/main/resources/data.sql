insert into TBL_BOOKING(ID, BOOKING_ID, CUSTOMER, CHECK_IN_DATE, CHECK_OUT_DATE, CANCELLATION_DEADLINE, SINGLE_ROOM, DOUBLE_ROOM, SUPERIOR_ROOM, VOUCHER_CODE, BOOKING_STATUS)
values
(1, 'f0c7aa4e1b8348b2a7460c4e47517255', 'ac46c25965994a9094e6d9d91fcb0385', CURRENT_DATE, CURRENT_DATE + 3, CURRENT_DATE - 10,2,1,1,'Winter', 'PENDING'),
(2, 'f0c7aa4e1b8348b2a7460c4e47517256', 'ac46c25965994a9094e6d9d91fcb0386', CURRENT_DATE, CURRENT_DATE + 2, CURRENT_DATE - 10,1,0,0,' ', 'CONFIRMED'),
(3, 'f0c7aa4e1b8348b2a7460c4e47517257', 'ac46c25965994a9094e6d9d91fcb0387', CURRENT_DATE, CURRENT_DATE + 1, CURRENT_DATE - 10,5,0,0,' ', 'CONFIRMED'),
(4, 'f0c7aa4e1b8348b2a7460c4e47517258', 'ac46c25965994a9094e6d9d91fcb0388', CURRENT_DATE - 2, CURRENT_DATE, CURRENT_DATE - 10,0,2,0,' ', 'CHECKEDIN');



insert into TBL_GUEST(ID, GUEST_ID, FIRST_NAME, LAST_NAME, STREET_ADDRESS, ZIP, CITY, COUNTRY, PHONE_NUMBER, EMAIL, GUEST_TYPE, COMPANY_NAME, AGENCY_NAME)
values
(1,	'ac46c25965994a9094e6d9d91fcb0385','Adrian','Essig','Jahngasse 1', '6800','Feldkirch','Austria','06608371982','adrian.essig@students.fhv.at','REGULAR',NULL,NULL),
(2,	'ac46c25965994a9094e6d9d91fcb0386','Alp','Arslan','Jahngasse 3', '6800','Feldkirch','Austria','0660837585','alp.arslan@students.fhv.at','COMPANY','IT Service GmbH',NULL),
(3,	'ac46c25965994a9094e6d9d91fcb0387','Achim','Unterkofler','Jahngasse 5', '6800','Feldkirch','Austria','066012939785','achim.unterkofler@students.fhv.at','TRAVEL_AGENCY',NULL,'Unterkofler Reisen GmbH'),
(4,	'ac46c25965994a9094e6d9d91fcb0388','Tobias','Kurz','Jahngasse 6', '6800','Feldkirch','Austria','066012939787','tobias.kurz@students.fhv.at','REGULAR',NULL,NULL);

insert into TBL_ROOM(ID,ROOM_CATEGORY,ROOM_NUMBER,ROOM_STATUS,BOOKING_ID)
values
(1, 'SINGLE',100,'FREE',NULL),
(2, 'SINGLE',101,'FREE',NULL),
(3, 'SINGLE',102,'FREE',NULL),
(4, 'SINGLE',103,'FREE',NULL),
(5, 'SINGLE',104,'FREE',NULL),
(6, 'SINGLE',105,'FREE',NULL),
(7, 'SINGLE',106,'FREE',NULL),
(8, 'SINGLE',107,'FREE',NULL),
(9, 'SINGLE',108,'FREE',NULL),
(10, 'SINGLE',109,'FREE',NULL),

(11,'DOUBLE',200,'OCCUPIED','f0c7aa4e1b8348b2a7460c4e47517258'),
(12,'DOUBLE',201,'OCCUPIED','f0c7aa4e1b8348b2a7460c4e47517258'),
(13,'DOUBLE',202,'FREE',NULL),
(14,'DOUBLE',203,'FREE',NULL),
(15,'DOUBLE',204,'FREE',NULL),
(16,'DOUBLE',205,'FREE',NULL),
(17,'DOUBLE',206,'FREE',NULL),
(18,'DOUBLE',207,'FREE',NULL),
(19,'DOUBLE',208,'FREE',NULL),
(20,'DOUBLE',209,'FREE',NULL),

(21,'SUPERIOR',300,'FREE',NULL),
(22,'SUPERIOR',301,'FREE',NULL),
(23,'SUPERIOR',302,'FREE',NULL),
(24,'SUPERIOR',303,'FREE',NULL),
(25,'SUPERIOR',304,'FREE',NULL),
(26,'SUPERIOR',305,'FREE',NULL),
(27,'SUPERIOR',306,'FREE',NULL),
(28,'SUPERIOR',307,'FREE',NULL),
(29,'SUPERIOR',308,'FREE',NULL),
(30,'SUPERIOR',309,'FREE',NULL);





