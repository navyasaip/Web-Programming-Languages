drop database if exists trendz1;

Create database trendz1;

CREATE TABLE `cart` (
  `product_id` varchar(5) NOT NULL DEFAULT '',
  `cust_email` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(200) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`product_id`,`cust_email`)
);

CREATE TABLE `customer` (
  `cid` int(11) NOT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `lastlogin` datetime DEFAULT NULL,
  `failedlogin` int(5) DEFAULT NULL,
  PRIMARY KEY (`cid`)
);

CREATE TABLE `orders` (
  `orderid` int(5) NOT NULL,
  `productid` varchar(5) DEFAULT NULL,
  `cid` int(5) DEFAULT NULL,
  `shippingaddress` varchar(100) DEFAULT NULL,
  `orderdate` date DEFAULT NULL,
  `quantity` int(3) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `deliverydate` date DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ;

CREATE TABLE `product` (
  `productid` varchar(5) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `category` int(2) DEFAULT NULL,
  `brand` varchar(30) DEFAULT NULL,
  `color` varchar(15) DEFAULT NULL,
  `price` float(6,2) DEFAULT NULL,
  `quantity` int(4) DEFAULT NULL,
  `discount` float(4,1) DEFAULT NULL,
  `rating` float(2,1) DEFAULT NULL,
  PRIMARY KEY (`productid`)
) ;

CREATE TABLE `reviews` (
  `orderid` int(5) NOT NULL DEFAULT '0',
  `productid` varchar(5) NOT NULL DEFAULT '',
  `cid` int(5) NOT NULL DEFAULT '0',
  `reviews` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`orderid`,`cid`,`productid`),
  KEY `productid` (`productid`),
  KEY `cid` (`cid`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`),
  CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`),
  CONSTRAINT `reviews_ibfk_3` FOREIGN KEY (`cid`) REFERENCES `customer` (`cid`)
) ;