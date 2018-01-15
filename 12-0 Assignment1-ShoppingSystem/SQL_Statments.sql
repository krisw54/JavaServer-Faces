Alter Table tblOrder
DROP CONSTRAINT USER_ID;

Alter Table tblOrderLine
DROP CONSTRAINT ITEM_ID;

Alter Table tblOrderLine
DROP CONSTRAINT ORDER_ID;

Alter Table tblStoreStock
DROP CONSTRAINT ITEM_ID;

Alter Table tblStoreStock
DROP CONSTRAINT STORE_ID;

DELETE FROM tblorder;
DELETE FROM tbluser;
DELETE FROM tblorderline;
DELETE FROM tblitem;
DELETE FROM tblstore;
DELETE FROM tblstoreStock;

drop table tblorder;
drop table tblorderline;
drop table tblUser;
drop table tblitem;
drop table tblstore;
drop table tblstoreStock;


create table tblUSER
(
	USER_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	FIRSTNAME VARCHAR(30) not null,
	LASTNAME VARCHAR(30) not null,
	ADDRESS1 VARCHAR(30),
	ADDRESS2 VARCHAR(30),
	ADDRESS3 VARCHAR(30),
	POSTCODE VARCHAR(16),
	MOBILE VARCHAR(15),
	LANDLINE VARCHAR(20),
	EMAIL VARCHAR(40) not null,
	UNAME VARCHAR(20) not null,
	PWD VARCHAR(60) not null,
    userType VARCHAR(30) not null,
    dateCreated Date not null,
    timeCreated time not null,
    store Int
);

INSERT into tblUser (firstName, lastName, email, uname, pwd, userType, dateCreated, timeCreated) VALUES ('Chris','Wood','email@email.com','cw','V8DEVdg4fZjByRGyUI+Ii4afpU30wG4cIgfbZZJLVUY=','Area Manager','2017-12-04','14:37:58');

INSERT into tblUser (firstName, lastName, email, uname, pwd, userType, dateCreated, timeCreated, store) VALUES ('Chris','Wood','email@email.com','cw1','V8DEVdg4fZjByRGyUI+Ii4afpU30wG4cIgfbZZJLVUY=','Branch Manager','2017-12-04','14:37:58',1);

INSERT into tblUser (firstName, lastName, email, uname, pwd, userType, dateCreated, timeCreated) VALUES ('Chris','Wood','email@email.com','cw2','V8DEVdg4fZjByRGyUI+Ii4afpU30wG4cIgfbZZJLVUY=','User','2017-12-04','14:37:58');

create table tblItem
(
    ITEM_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    itemName VARCHAR(30) not null,
    itemDesc VARCHAR(60),
    itemBarcode VARCHAR(30) not null,
    itemPrice REAL,
    itemPhoto VARCHAR(100)
);

create table tblOrder
(
    ORDER_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    USER_ID INT,
    dateOfOrder Date,
    timeOfOrder Time
    
);

create table tblOrderLine
(
    ORDERLINE_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ITEM_ID INT,
    ORDER_ID INT,
    qty INT
    
);

create table tblStore
(
    STORE_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    STORENAME VARCHAR(30) not null,
    STOREADDRESS VARCHAR(60) not null,
    STORETEL VARCHAR(16) not null,
    STOREMANID INT
);

create table tblStoreStock
(
    STOCK_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ITEM_ID INT,
    STORE_ID INT,
    QTY INT
);


INSERT into tblitem (itemName, itemDesc, itemBarcode, itemPrice, itemPhoto) Values ('Apple','A crisp red apple','0123232',0.50,'resources/images/apple.jpg');
INSERT into tblitem (itemName, itemDesc, itemBarcode, itemPrice, itemPhoto) Values ('Pear', 'A lovely green pear','121232',0.99,'resources/images/pear.jpg');
INSERT into tblitem (itemName, itemDesc, itemBarcode, itemPrice, itemPhoto) Values ('Clock', 'Clock with batteries', '12332',10.99,'resources/images/clock.jpg');
INSERT into tblitem (itemName, itemDesc, itemBarcode, itemPrice, itemPhoto) Values ('iPhone', 'iPhone 6s, brand new bargin price', '2345332',300.29,'resources/images/iphone.jpg');
INSERT into tblitem (itemName, itemDesc, itemBarcode, itemPrice, itemPhoto) Values ('Banana', 'Lovely bunch of bananas', '2333232',0.67,'resources/images/ban.jpg');


Alter Table tblOrder
Add FOREIGN KEY (USER_ID)
References tblUser (USER_ID);

Alter Table tblOrderLine
Add FOREIGN KEY (ITEM_ID)
References tblItem (ITEM_ID);

Alter Table tblOrderLine
Add FOREIGN KEY (ORDER_ID)
References tblOrder (ORDER_ID);

Alter Table tblStoreStock
Add FOREIGN KEY (ITEM_ID)
References tblItem (ITEM_ID);

Alter Table tblStoreStock
Add FOREIGN KEY (STORE_ID)
References tblStore (STORE_ID);

INSERT into tblStore (storeName, storeAddress, storeTel, storeManID) VALUES ('Stoke','1212 Stoke Street, Stoke-On-Trent','012782 5654656',1);
INSERT into tblStore (storeName, storeAddress, storeTel, storeManID) VALUES ('Stafford','44 Staffs Street, Stoke-On-Trent','012782 588888',1);
INSERT into tblStore (storeName, storeAddress, storeTel, storeManID) VALUES ('Stone','44 database Street, Stone','01256 5554466',1);

INSERT into tblStoreStock (item_id, store_id, qty) VALUES (1,1,10);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (1,2,1);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (1,3,8);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (2,1,0);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (2,2,15);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (2,3,100);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (3,1,100);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (3,2,0);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (3,3,2);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (4,1,1);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (4,2,3);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (4,3,5);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (5,1,100);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (5,2,50);
INSERT into tblStoreStock (item_id, store_id, qty) VALUES (5,3,70);

INSERT into tblOrder (USER_ID, dateOfOrder, timeOfOrder) VALUES (1,'2017-12-04','14:44:33');
INSERT into tblOrderLine (ITEM_ID, ORDER_ID, qty) VALUES (1,1,2);
INSERT into tblOrderLine (ITEM_ID, ORDER_ID, qty) VALUES (2,1,1);
INSERT into tblOrderLine (ITEM_ID, ORDER_ID, qty) VALUES (3,1,10);
INSERT into tblOrder (USER_ID, dateOfOrder, timeOfOrder) VALUES (3,'2017-12-05','11:34:33');
INSERT into tblOrderLine (ITEM_ID, ORDER_ID, qty) VALUES (1,2,2);
INSERT into tblOrderLine (ITEM_ID, ORDER_ID, qty) VALUES (3,2,10);

/* SQL Queries */

Select * from tblUser;
Select * from tblitem;
Select * from tblstore;

/* Stock at one store */
SELECT storestock.item_id,qty,itemname,itemdesc,itembarcode,itemprice
FROM storestock
left join items ON items.item_id = storestock.item_id where store_id=1;

/* Stock at all stores */
SELECT store_id,storestock.item_id,qty,itemname,itemdesc,itembarcode,itemprice
FROM storestock
left join items ON items.item_id = storestock.item_id order by store_id;

/* All Users Previous Order */
SELECT order_id, users.user_id, dateoforder, timeoforder
FROM orders
left join users ON users.USER_ID = orders.USER_ID;

/* List items Ordered from order ID */
SELECT orderline.item_id, qty, itemname, itemdesc, itembarcode, itemprice
FROM orderline
left join items ON orderline.ITEM_ID = items.ITEM_ID where order_id=1;
