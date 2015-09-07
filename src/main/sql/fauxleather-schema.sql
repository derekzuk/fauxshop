CREATE TABLE `account` (
   `ACCOUNT_ID` int(11) NOT NULL AUTO_INCREMENT,
   `USER_LOGIN` varchar(45) NOT NULL DEFAULT '',
   `EMAIL` varchar(100) NOT NULL DEFAULT '',
   `PASSWORD` varchar(45) NOT NULL DEFAULT '',
   `FIRST_NAME` varchar(100) DEFAULT NULL,
   `LAST_NAME` varchar(100) DEFAULT NULL,
   `CITY` varchar(45) DEFAULT NULL,
   `STATE` varchar(45) DEFAULT NULL,
   `ZIP` varchar(45) DEFAULT NULL,
   `PHONE_NUMBER` varchar(45) DEFAULT NULL,
   `COUNTRY` varchar(45) DEFAULT NULL,
   `ADDRESS` varchar(1000) DEFAULT NULL,
   `ADDRESS2` varchar(1000) DEFAULT NULL,
   `ENABLED` tinyint(1) DEFAULT NULL,
   PRIMARY KEY (`ACCOUNT_ID`)
 ) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
 
CREATE TABLE `cart` (
   `CART_ID` int(11) NOT NULL AUTO_INCREMENT,
   `ACCOUNT_ID` int(11) NOT NULL DEFAULT 0,
   `INVENTORY_DETAIL_ID` int(11) NOT NULL DEFAULT 0,
   `QUANTITY` int(11) NOT NULL DEFAULT 1,
   `PRICE_PER_ITEM` DECIMAL(6,2) NOT NULL DEFAULT 0.00,
   `SHIPPING_COST` DECIMAL(6,2) NOT NULL DEFAULT 0.00,
   `TAX` DECIMAL(6,2) NOT NULL DEFAULT 0.00,
   PRIMARY KEY (`CART_ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `employees` (
   `username` varchar(20) NOT NULL DEFAULT '',
   `password` varchar(20) NOT NULL DEFAULT '',
   `enabled` tinyint(1) NOT NULL DEFAULT '1',
   PRIMARY KEY (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `inventory` (
   `INVENTORY_ID` int(11) NOT NULL,
   `INVENTORY_TYPE_CD` INT NOT NULL,   
   `INVENTORY_TXT` varchar(45) DEFAULT NULL,
   `INVENTORY_DESCRIPTION` VARCHAR(5000) NOT NULL,
   `INVENTORY_CARE` VARCHAR(5000) NULL,
   `INVENTORY_SIZE_DESC` VARCHAR(5000) NULL,
   `BRAND` VARCHAR(45) NOT NULL,   
   `PRICE_USD` DECIMAL(6,2) DEFAULT NULL,
   `IN_STOCK` tinyint(1) DEFAULT NULL,
   `IMG` VARCHAR(100) NULL,
   PRIMARY KEY (`INVENTORY_ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 CREATE TABLE `inventory_detail` (
  `INVENTORY_DETAIL_ID` INT NOT NULL AUTO_INCREMENT,
  `INVENTORY_ID` INT NOT NULL,
  `PRODUCT_CODE` VARCHAR(45) NOT NULL,
  `STOCK_QUANTITY` INT NOT NULL,
  `SIZE` VARCHAR(45) NOT NULL,
  `COLOR` VARCHAR(45) NOT NULL,
  `THUMBNAIL1` VARCHAR(100) NULL,
  `THUMBNAIL2` VARCHAR(100) NULL,
  `THUMBNAIL3` VARCHAR(100) NULL,
  `THUMBNAIL4` VARCHAR(100) NULL,
  `THUMBNAIL5` VARCHAR(100) NULL,
  PRIMARY KEY (`INVENTORY_DETAIL_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `inventory_type_code` (
   `inventory_type_cd` int(11) NOT NULL,
   `inventory_type_txt` varchar(45) DEFAULT NULL,
   `inventory_type_description` varchar(5000) DEFAULT NULL,
   PRIMARY KEY (`inventory_type_cd`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE `person` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(20) NOT NULL DEFAULT '',
   `country` varchar(20) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
   `USER_LOGIN` varchar(20) NOT NULL DEFAULT '',
   `ROLE` varchar(20) NOT NULL DEFAULT '',
   PRIMARY KEY (`USER_LOGIN`,`ROLE`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sizing` (
   `SIZING_ID` int(11) NOT NULL,
   `INVENTORY_ID` int(11) DEFAULT NULL,
   `BUST` int(11) DEFAULT NULL,
   `WAIST` int(11) DEFAULT NULL,
   `HIP` int(11) DEFAULT NULL,
   `SHOULDER_WIDTH` int(11) DEFAULT NULL,
   `FRONT_BODY_LENGTH` int(11) DEFAULT NULL,
   PRIMARY KEY (`SIZING_ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transaction` (
   `TRANSACTION_ID` int(11) NOT NULL AUTO_INCREMENT,
   `ACCOUNT_ID` int(11) DEFAULT NULL,
   `ORDER_QUANTITY` varchar(45) DEFAULT NULL,
   `SHIP_NAME` varchar(200) DEFAULT NULL,
   `SHIP_ADDRESS` varchar(1000) DEFAULT NULL,
   `SHIP_ADDRESS2` varchar(1000) DEFAULT NULL,
   `CITY` varchar(45) DEFAULT NULL,
   `STATE` varchar(45) DEFAULT NULL,
   `ZIP` varchar(20) DEFAULT NULL,
   `COUNTRY` varchar(45) DEFAULT NULL,
   `PHONE` varchar(20) DEFAULT NULL,
   `SHIPPING_COST` varchar(45) DEFAULT NULL,
   `TAX` varchar(45) DEFAULT NULL,
   `ORDER_EMAIL` varchar(45) DEFAULT NULL,
   `DATE` timestamp NULL DEFAULT NULL,
   `SHIPPED` tinyint(4) DEFAULT NULL,
   `TRACKING_NUMBER` varchar(100) DEFAULT NULL,
   `INVENTORY_ID` int(11) DEFAULT NULL,
   `ORDER_COST` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`TRANSACTION_ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 