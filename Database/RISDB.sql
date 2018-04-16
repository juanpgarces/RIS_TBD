CREATE DATABASE  IF NOT EXISTS `risdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `risdb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: ris2.cjvk36afmzyl.us-east-2.rds.amazonaws.com    Database: risdb
-- ------------------------------------------------------
-- Server version	5.6.39-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` varchar(9) NOT NULL,
  `patientID` varchar(9) NOT NULL,
  `modalityID` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `startTme` time DEFAULT NULL,
  `stopTime` time DEFAULT NULL,
  `notes` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`appID`,`userID`,`patientID`,`modalityID`),
  UNIQUE KEY `appID_UNIQUE` (`appID`),
  KEY `physicianID_idx` (`userID`),
  KEY `patientID_idx` (`patientID`),
  KEY `mod1_idx` (`modalityID`),
  CONSTRAINT `mod1` FOREIGN KEY (`modalityID`) REFERENCES `modality` (`modID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `patientID` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `physicianID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bills` (
  `billID` int(11) NOT NULL AUTO_INCREMENT,
  `Cost` double DEFAULT '0',
  `appID` int(11) NOT NULL,
  `userID` varchar(9) NOT NULL,
  `patientID` varchar(9) NOT NULL,
  `modalityID` int(11) NOT NULL,
  PRIMARY KEY (`billID`,`appID`,`userID`,`patientID`,`modalityID`),
  KEY `fk_BILLLING_APPOINTMENT1_idx` (`appID`,`userID`,`patientID`,`modalityID`),
  CONSTRAINT `fk_BILLLING_APPOINTMENT1` FOREIGN KEY (`appID`, `userID`, `patientID`, `modalityID`) REFERENCES `appointment` (`appID`, `userID`, `patientID`, `modalityID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modality`
--

DROP TABLE IF EXISTS `modality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modality` (
  `modID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `cost` double NOT NULL,
  `duration` int(11) NOT NULL,
  PRIMARY KEY (`modID`),
  UNIQUE KEY `modID_UNIQUE` (`modID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modality`
--

LOCK TABLES `modality` WRITE;
/*!40000 ALTER TABLE `modality` DISABLE KEYS */;
INSERT INTO `modality` VALUES (1,'MRI',2000,0),(2,'CT Scan',1500,0),(3,'Ultrasound',400,0),(4,'Nuclear Machine',1100,0),(5,'Anesthesia Imaging',300,0),(6,'Radiography',450,0),(7,'Flouroscopy',800,0),(8,'Interventional Radiology',1000,0);
/*!40000 ALTER TABLE `modality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `emergencyLevel` varchar(45) DEFAULT NULL,
  `userID` varchar(9) NOT NULL,
  `patientID` varchar(9) NOT NULL,
  `modality` varchar(45) DEFAULT NULL,
  `notes` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`orderID`,`userID`,`patientID`),
  UNIQUE KEY `orderID_UNIQUE` (`orderID`),
  KEY `patientSSN_idx` (`patientID`),
  KEY `physicianSSN_idx` (`userID`),
  CONSTRAINT `patientSSN` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `physicianSSN` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2','987654321','324651782',NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacs`
--

DROP TABLE IF EXISTS `pacs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacs` (
  `imageID` int(11) NOT NULL AUTO_INCREMENT,
  `appointmentID` int(11) NOT NULL,
  `image` varchar(100) NOT NULL,
  PRIMARY KEY (`imageID`,`appointmentID`),
  UNIQUE KEY `imageID_UNIQUE` (`imageID`),
  UNIQUE KEY `image_UNIQUE` (`image`),
  KEY `appID_idx` (`appointmentID`),
  CONSTRAINT `appID` FOREIGN KEY (`appointmentID`) REFERENCES `appointment` (`appID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacs`
--

LOCK TABLES `pacs` WRITE;
/*!40000 ALTER TABLE `pacs` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `patientID` varchar(9) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `DOB` date NOT NULL,
  `insurance` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `notes` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`patientID`),
  UNIQUE KEY `ssn_UNIQUE` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('324651782','Juan','ThePatient','M','0000-00-00','State','534 Butler','235428234','juanp32423@gmail.com','He is a good person');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` varchar(9) NOT NULL,
  `passwd` varchar(45) NOT NULL,
  `userType` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `ssn_UNIQUE` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('123456789','987654321','Administrator','Test','Testing',NULL,'M','Test@ung.edu'),('543876148','0123456789','Referring','John','TheReferrer','2423423','M','adfd@ung.edu'),('987654321','123456789','Receptionist','Jua','TheReceptionist','565161868','M','asdsd@sadfdf.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-15 22:12:38
