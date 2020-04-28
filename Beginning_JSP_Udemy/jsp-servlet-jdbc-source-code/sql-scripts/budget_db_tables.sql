CREATE DATABASE  IF NOT EXISTS `budget_app_db`;
USE `budget_app_db`;

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `account_id` int(11) DEFAULT 0000,
  `account_pin` int(4) DEFAULT 0000,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `member` WRITE;
INSERT INTO `member` VALUES (1,'Test Name','admin','T1@grr.com', 9999, 1234);
UNLOCK TABLES;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total_amount` DOUBLE(20,2) DEFAULT 0.00,
  `account_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `pin` int(4) DEFAULT 0000,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `account` WRITE;
INSERT INTO `account` VALUES (0, 1000.00, 'TestAcct', 'ti@ger.com', 'admin', 'test guy', 1111);
UNLOCK TABLES;

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT 0,
  `member_id` int(11) DEFAULT 0,
  `date` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `transaction_amount` DOUBLE(11,2) DEFAULT 0.00,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `transaction` WRITE;
INSERT INTO `transaction` VALUES (0, 0, 0, '4/24/20', 'Groceries', 100.55);
UNLOCK TABLES;


