CREATE DATABASE  IF NOT EXISTS `risdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `risdb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: risdb
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.26-MariaDB

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
  `userID` int(9) NOT NULL,
  `patientID` int(9) NOT NULL,
  `modalityID` int(11) NOT NULL,
  `dateTime` datetime NOT NULL,
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
-- Table structure for table `billling`
--

DROP TABLE IF EXISTS `billling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billling` (
  `billID` int(11) NOT NULL,
  `Cost` varchar(45) DEFAULT NULL,
  `APPOINTMENT_appID` int(11) NOT NULL,
  `APPOINTMENT_userID` int(9) NOT NULL,
  `APPOINTMENT_patientID` int(9) NOT NULL,
  `APPOINTMENT_modalityID` int(11) NOT NULL,
  PRIMARY KEY (`billID`,`APPOINTMENT_appID`,`APPOINTMENT_userID`,`APPOINTMENT_patientID`,`APPOINTMENT_modalityID`),
  KEY `fk_BILLLING_APPOINTMENT1_idx` (`APPOINTMENT_appID`,`APPOINTMENT_userID`,`APPOINTMENT_patientID`,`APPOINTMENT_modalityID`),
  CONSTRAINT `fk_BILLLING_APPOINTMENT1` FOREIGN KEY (`APPOINTMENT_appID`, `APPOINTMENT_userID`, `APPOINTMENT_patientID`, `APPOINTMENT_modalityID`) REFERENCES `appointment` (`appID`, `userID`, `patientID`, `modalityID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billling`
--

LOCK TABLES `billling` WRITE;
/*!40000 ALTER TABLE `billling` DISABLE KEYS */;
/*!40000 ALTER TABLE `billling` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modality`
--

LOCK TABLES `modality` WRITE;
/*!40000 ALTER TABLE `modality` DISABLE KEYS */;
/*!40000 ALTER TABLE `modality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `emergencyLevel` varchar(45) DEFAULT NULL,
  `userID` int(9) NOT NULL,
  `patientID` int(9) NOT NULL,
  `modalityID` int(11) NOT NULL,
  PRIMARY KEY (`orderID`,`userID`,`patientID`,`modalityID`),
  UNIQUE KEY `orderID_UNIQUE` (`orderID`),
  KEY `patientSSN_idx` (`patientID`),
  KEY `physicianSSN_idx` (`userID`),
  KEY `fk_ORDER_MODALITY1_idx` (`modalityID`),
  CONSTRAINT `fk_ORDER_MODALITY1` FOREIGN KEY (`modalityID`) REFERENCES `modality` (`modID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `patientSSN` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `physicianSSN` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacs`
--

DROP TABLE IF EXISTS `pacs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacs` (
  `imageID` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(100) NOT NULL,
  `transcript` varchar(300) DEFAULT NULL,
  `apppintmentID` int(11) NOT NULL,
  PRIMARY KEY (`imageID`,`apppintmentID`),
  UNIQUE KEY `imageID_UNIQUE` (`imageID`),
  UNIQUE KEY `image_UNIQUE` (`image`),
  KEY `appID_idx` (`apppintmentID`),
  CONSTRAINT `appID` FOREIGN KEY (`apppintmentID`) REFERENCES `appointment` (`appID`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `patientID` int(9) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `DOB` date NOT NULL,
  `insurance` varchar(45) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
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
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(9) NOT NULL,
  `userType` int(11) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
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

-- Dump completed on 2018-03-30 12:29:47
