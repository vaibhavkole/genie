# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.44)
# Database: payment_service
# Generation Time: 2017-02-25 21:49:46 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table deliver_shipment_request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `deliver_shipment_request`;

CREATE TABLE `deliver_shipment_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `shipment_id` int(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `pincode` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `deliver_shipment_request` WRITE;
/*!40000 ALTER TABLE `deliver_shipment_request` DISABLE KEYS */;

INSERT INTO `deliver_shipment_request` (`id`, `shipment_id`, `address`, `pincode`)
VALUES
	(1,17,'koramangala',560037),
	(2,18,'koramangala',560038);

/*!40000 ALTER TABLE `deliver_shipment_request` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table forward_shipment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `forward_shipment`;

CREATE TABLE `forward_shipment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `destination_location_id` int(11) DEFAULT NULL,
  `source_location_id` int(11) DEFAULT NULL,
  `shipment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `forward_shipment` WRITE;
/*!40000 ALTER TABLE `forward_shipment` DISABLE KEYS */;

INSERT INTO `forward_shipment` (`id`, `destination_location_id`, `source_location_id`, `shipment_id`)
VALUES
	(3,1,12,1);

/*!40000 ALTER TABLE `forward_shipment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table pickup_request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pickup_request`;

CREATE TABLE `pickup_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `shipment_id` int(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `pincode` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `pickup_request` WRITE;
/*!40000 ALTER TABLE `pickup_request` DISABLE KEYS */;

INSERT INTO `pickup_request` (`id`, `shipment_id`, `address`, `pincode`, `location_id`)
VALUES
	(1,12,'koramangala',560034,1),
	(2,13,'koramangala',560034,1),
	(3,14,'koramangala',560035,1),
	(4,15,'koramangala',560036,1);

/*!40000 ALTER TABLE `pickup_request` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table receive_shipment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `receive_shipment`;

CREATE TABLE `receive_shipment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `location_id` int(11) DEFAULT NULL,
  `shipment_id` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `receive_shipment` WRITE;
/*!40000 ALTER TABLE `receive_shipment` DISABLE KEYS */;

INSERT INTO `receive_shipment` (`id`, `location_id`, `shipment_id`, `date`)
VALUES
	(1,1,1234,'2017-02-25');

/*!40000 ALTER TABLE `receive_shipment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Task
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Task`;

CREATE TABLE `Task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `task_description` varchar(50) DEFAULT NULL,
  `entity_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;

INSERT INTO `Task` (`id`, `task_description`, `entity_id`, `location_id`, `date`, `active`)
VALUES
	(10,'create_runsheet',NULL,2,'2017-02-26',0),
	(11,'create_pickupsheet',NULL,2,'2017-02-26',1),
	(12,'create_pickupsheet',NULL,2,'2017-02-26',1),
	(13,'create_pickupsheet',NULL,2,'2017-02-26',1),
	(14,'create_runsheet',NULL,2,'2017-02-26',1),
	(15,'create_runsheet',NULL,3,'2017-02-26',1);

/*!40000 ALTER TABLE `Task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table task_id_shipment_mapping
# ------------------------------------------------------------

DROP TABLE IF EXISTS `task_id_shipment_mapping`;

CREATE TABLE `task_id_shipment_mapping` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL,
  `shipment_id` int(11) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `task_id_shipment_mapping` WRITE;
/*!40000 ALTER TABLE `task_id_shipment_mapping` DISABLE KEYS */;

INSERT INTO `task_id_shipment_mapping` (`id`, `task_id`, `shipment_id`, `active`)
VALUES
	(3,10,1,1),
	(4,10,2,1),
	(5,11,3,1),
	(6,11,4,1),
	(7,12,13,1),
	(8,13,14,1),
	(9,13,15,1),
	(10,14,17,1),
	(11,14,18,1),
	(12,15,2,1),
	(13,15,25,1);

/*!40000 ALTER TABLE `task_id_shipment_mapping` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
