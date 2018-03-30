-- MySQL Script generated by MySQL Workbench
-- Thu Mar 29 18:57:28 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema RISDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema RISDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RISDB` DEFAULT CHARACTER SET utf8 ;
USE `RISDB` ;

-- -----------------------------------------------------
-- Table `RISDB`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISDB`.`USER` (
  `userID` INT(9) NOT NULL,
  `userType` INT NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `ssn_UNIQUE` (`userID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RISDB`.`PATIENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISDB`.`PATIENT` (
  `patientID` INT(9) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `DOB` DATE NOT NULL,
  `insurance` VARCHAR(45) NULL,
  `address` VARCHAR(100) NULL,
  `email` VARCHAR(45) NULL,
  `notes` VARCHAR(300) NULL,
  PRIMARY KEY (`patientID`),
  UNIQUE INDEX `ssn_UNIQUE` (`patientID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RISDB`.`MODALITY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISDB`.`MODALITY` (
  `modID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cost` DOUBLE NOT NULL,
  `duration` INT NOT NULL,
  PRIMARY KEY (`modID`),
  UNIQUE INDEX `modID_UNIQUE` (`modID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RISDB`.`APPOINTMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISDB`.`APPOINTMENT` (
  `appID` INT NOT NULL AUTO_INCREMENT,
  `userID` INT(9) NOT NULL,
  `patientID` INT(9) NOT NULL,
  `modalityID` INT NOT NULL,
  `dateTime` DATETIME NOT NULL,
  PRIMARY KEY (`appID`, `userID`, `patientID`, `modalityID`),
  UNIQUE INDEX `appID_UNIQUE` (`appID` ASC),
  INDEX `physicianID_idx` (`userID` ASC),
  INDEX `patientID_idx` (`patientID` ASC),
  INDEX `mod1_idx` (`modalityID` ASC),
  CONSTRAINT `physicianID`
    FOREIGN KEY (`userID`)
    REFERENCES `RISDB`.`USER` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `patientID`
    FOREIGN KEY (`patientID`)
    REFERENCES `RISDB`.`PATIENT` (`patientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `mod1`
    FOREIGN KEY (`modalityID`)
    REFERENCES `RISDB`.`MODALITY` (`modID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RISDB`.`PACS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISDB`.`PACS` (
  `imageID` INT NOT NULL AUTO_INCREMENT,
  `image` VARCHAR(100) NOT NULL,
  `transcript` VARCHAR(300) NULL,
  `apppintmentID` INT NOT NULL,
  `patientID` INT(9) NOT NULL,
  PRIMARY KEY (`imageID`, `apppintmentID`, `patientID`),
  UNIQUE INDEX `imageID_UNIQUE` (`imageID` ASC),
  UNIQUE INDEX `image_UNIQUE` (`image` ASC),
  INDEX `appID_idx` (`apppintmentID` ASC),
  INDEX `fk_PACS_PATIENT1_idx` (`patientID` ASC),
  CONSTRAINT `appID`
    FOREIGN KEY (`apppintmentID`)
    REFERENCES `RISDB`.`APPOINTMENT` (`appID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PACS_PATIENT1`
    FOREIGN KEY (`patientID`)
    REFERENCES `RISDB`.`PATIENT` (`patientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RISDB`.`ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISDB`.`ORDER` (
  `orderID` INT NOT NULL AUTO_INCREMENT,
  `emergencyLevel` VARCHAR(45) NULL,
  `userID` INT(9) NOT NULL,
  `patientID` INT(9) NOT NULL,
  `modalityID` INT NOT NULL,
  PRIMARY KEY (`orderID`, `userID`, `patientID`, `modalityID`),
  UNIQUE INDEX `orderID_UNIQUE` (`orderID` ASC),
  INDEX `patientSSN_idx` (`patientID` ASC),
  INDEX `physicianSSN_idx` (`userID` ASC),
  INDEX `fk_ORDER_MODALITY1_idx` (`modalityID` ASC),
  CONSTRAINT `physicianSSN`
    FOREIGN KEY (`userID`)
    REFERENCES `RISDB`.`USER` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `patientSSN`
    FOREIGN KEY (`patientID`)
    REFERENCES `RISDB`.`PATIENT` (`patientID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ORDER_MODALITY1`
    FOREIGN KEY (`modalityID`)
    REFERENCES `RISDB`.`MODALITY` (`modID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RISDB`.`BILLLING`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RISDB`.`BILLLING` (
  `billID` INT NOT NULL,
  `Cost` VARCHAR(45) NULL,
  `APPOINTMENT_appID` INT NOT NULL,
  `APPOINTMENT_userID` INT(9) NOT NULL,
  `APPOINTMENT_patientID` INT(9) NOT NULL,
  `APPOINTMENT_modalityID` INT NOT NULL,
  PRIMARY KEY (`billID`, `APPOINTMENT_appID`, `APPOINTMENT_userID`, `APPOINTMENT_patientID`, `APPOINTMENT_modalityID`),
  INDEX `fk_BILLLING_APPOINTMENT1_idx` (`APPOINTMENT_appID` ASC, `APPOINTMENT_userID` ASC, `APPOINTMENT_patientID` ASC, `APPOINTMENT_modalityID` ASC),
  CONSTRAINT `fk_BILLLING_APPOINTMENT1`
    FOREIGN KEY (`APPOINTMENT_appID` , `APPOINTMENT_userID` , `APPOINTMENT_patientID` , `APPOINTMENT_modalityID`)
    REFERENCES `RISDB`.`APPOINTMENT` (`appID` , `userID` , `patientID` , `modalityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;