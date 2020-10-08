-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema HOSPITAL
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema HOSPITAL
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `HOSPITAL` DEFAULT CHARACTER SET utf8mb4 ;
USE `HOSPITAL` ;

-- -----------------------------------------------------
-- Table `HOSPITAL`.`DOCTORS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`DOCTORS` (
  `doctor_id` VARCHAR(10) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `collegiate` VARCHAR(10) NOT NULL,
  `dpi` VARCHAR(15) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `start_time` TIME NOT NULL,
  `end_time` TIME NOT NULL,
  `start_date` DATE NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`doctor_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`ADMINISTRATORS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`ADMINISTRATORS` (
  `admin_id` VARCHAR(10) NOT NULL,
  `dpi` VARCHAR(15) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`admin_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`SPECIALTIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`SPECIALTIES` (
  `specialty_id` INT NOT NULL AUTO_INCREMENT,
  `degree` VARCHAR(50) NOT NULL,
  `price_consultation` DECIMAL(7,2) NULL DEFAULT 0,
  PRIMARY KEY (`specialty_id`),
  UNIQUE INDEX `degree_UNIQUE` (`degree` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`MEDICAL_DEGREES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`MEDICAL_DEGREES` (
  `degree_id` INT NOT NULL AUTO_INCREMENT,
  `doctor_id` VARCHAR(10) NOT NULL,
  `specialty_id` INT NOT NULL,
  PRIMARY KEY (`degree_id`),
  CONSTRAINT `fk_doctors_has_specialties_doctors`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `HOSPITAL`.`DOCTORS` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctors_has_specialties_specialties1`
    FOREIGN KEY (`specialty_id`)
    REFERENCES `HOSPITAL`.`SPECIALTIES` (`specialty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`EXAMS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`EXAMS` (
  `exam_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `exam_order` TINYINT NOT NULL,
  `description` TEXT(1000) NOT NULL,
  `price` DECIMAL(7,2) NOT NULL,
  `report` TINYINT NOT NULL COMMENT '0=img\n1=pdf',
  PRIMARY KEY (`exam_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`LAB_WORKERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`LAB_WORKERS` (
  `lab_worker_id` VARCHAR(10) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `registry_number` VARCHAR(20) NOT NULL,
  `dpi` VARCHAR(15) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `start_date` DATE NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `exam_id` INT NOT NULL,
  PRIMARY KEY (`lab_worker_id`),
  CONSTRAINT `fk_lab_workers_exams1`
    FOREIGN KEY (`exam_id`)
    REFERENCES `HOSPITAL`.`EXAMS` (`exam_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`PATIENTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`PATIENTS` (
  `patient_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `gender` TINYINT NOT NULL COMMENT '1=man\n0=woman',
  `birth` DATE NOT NULL,
  `dpi` VARCHAR(15) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `weight` DECIMAL(5,2) NOT NULL COMMENT 'kg',
  `blood` VARCHAR(10) NOT NULL COMMENT 'tipo de sangre',
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`patient_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`DAYS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`DAYS` (
  `day_id` INT NOT NULL,
  `name_day` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`day_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`WORKER_DAYS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`WORKER_DAYS` (
  `worker_days_id` INT NOT NULL AUTO_INCREMENT,
  `lab_worker_id` VARCHAR(10) NOT NULL,
  `day_id` INT NOT NULL,
  PRIMARY KEY (`worker_days_id`),
  CONSTRAINT `fk_lab_workers_has_days_lab_workers1`
    FOREIGN KEY (`lab_worker_id`)
    REFERENCES `HOSPITAL`.`LAB_WORKERS` (`lab_worker_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lab_workers_has_days_days1`
    FOREIGN KEY (`day_id`)
    REFERENCES `HOSPITAL`.`DAYS` (`day_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`APPOINTMENTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`APPOINTMENTS` (
  `appointment_id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `doctor_id` VARCHAR(10) NOT NULL,
  `specialty_id` INT NULL DEFAULT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `status` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`appointment_id`),
  CONSTRAINT `fk_appointments_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `HOSPITAL`.`PATIENTS` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `HOSPITAL`.`DOCTORS` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_APPOINTMENTS_SPECIALTIES1`
    FOREIGN KEY (`specialty_id`)
    REFERENCES `HOSPITAL`.`SPECIALTIES` (`specialty_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`REPORTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`REPORTS` (
  `report_id` INT NOT NULL AUTO_INCREMENT,
  `appointment_id` INT NULL DEFAULT NULL,
  `patient_id` INT NOT NULL,
  `doctor_id` VARCHAR(10) NOT NULL,
  `report` TEXT(1000) NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  PRIMARY KEY (`report_id`),
  CONSTRAINT `fk_reports_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `HOSPITAL`.`PATIENTS` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reports_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `HOSPITAL`.`DOCTORS` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reports_appointments1`
    FOREIGN KEY (`appointment_id`)
    REFERENCES `HOSPITAL`.`APPOINTMENTS` (`appointment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`APPOINTMENTS_LAB`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`APPOINTMENTS_LAB` (
  `appointment_lab_id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `doctor_id` VARCHAR(10) NULL DEFAULT NULL,
  `exam_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `exam_order` MEDIUMBLOB NULL DEFAULT NULL,
  `status` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`appointment_lab_id`),
  CONSTRAINT `fk_appointments_lab_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `HOSPITAL`.`PATIENTS` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_lab_exams1`
    FOREIGN KEY (`exam_id`)
    REFERENCES `HOSPITAL`.`EXAMS` (`exam_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointments_lab_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `HOSPITAL`.`DOCTORS` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`RESULTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`RESULTS` (
  `result_id` INT NOT NULL AUTO_INCREMENT,
  `appointment_lab_id` INT NULL DEFAULT NULL,
  `patient_id` INT NOT NULL,
  `exam_id` INT NOT NULL,
  `lab_worker_id` VARCHAR(10) NOT NULL,
  `report` MEDIUMBLOB NULL DEFAULT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  PRIMARY KEY (`result_id`),
  CONSTRAINT `fk_results_patients1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `HOSPITAL`.`PATIENTS` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_results_exams1`
    FOREIGN KEY (`exam_id`)
    REFERENCES `HOSPITAL`.`EXAMS` (`exam_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_results_lab_workers1`
    FOREIGN KEY (`lab_worker_id`)
    REFERENCES `HOSPITAL`.`LAB_WORKERS` (`lab_worker_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_results_appointments_lab1`
    FOREIGN KEY (`appointment_lab_id`)
    REFERENCES `HOSPITAL`.`APPOINTMENTS_LAB` (`appointment_lab_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`INCOMES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`INCOMES` (
  `income_id` INT NOT NULL AUTO_INCREMENT,
  `report_id` INT NULL DEFAULT NULL,
  `result_id` INT NULL DEFAULT NULL,
  `income` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`income_id`),
  CONSTRAINT `fk_incomes_reports1`
    FOREIGN KEY (`report_id`)
    REFERENCES `HOSPITAL`.`REPORTS` (`report_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incomes_results1`
    FOREIGN KEY (`result_id`)
    REFERENCES `HOSPITAL`.`RESULTS` (`result_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `HOSPITAL`.`NOTIFICATIONS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HOSPITAL`.`NOTIFICATIONS` (
  `notification_id` INT NOT NULL AUTO_INCREMENT,
  `result_id` INT NOT NULL,
  `read_by_pacient` TINYINT NULL DEFAULT 0,
  `read_by_doctor` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`notification_id`),
  CONSTRAINT `fk_notifications_results1`
    FOREIGN KEY (`result_id`)
    REFERENCES `HOSPITAL`.`RESULTS` (`result_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
