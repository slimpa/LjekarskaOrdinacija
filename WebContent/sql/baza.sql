-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pacijent
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pacijent
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pacijent` DEFAULT CHARACTER SET utf8 ;
USE `pacijent` ;

-- -----------------------------------------------------
-- Table `pacijent`.`Pacijent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pacijent`.`Pacijent` (
  `idPacijent` INT NOT NULL AUTO_INCREMENT,
  `ime` VARCHAR(45) NOT NULL,
  `prezime` VARCHAR(45) NOT NULL,
  `datumRodjenja` DATETIME NULL,
  `adresa` VARCHAR(155) NULL,
  `telefon` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPacijent`))

-- -----------------------------------------------------
-- Table `pacijent`.`doktor`
-- -----------------------------------------------------
CREATE TABLE `pacijent`.`doktor` (
  `id` INT NOT NULL,
  `ime` VARCHAR(45) NOT NULL,
  `prezime` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `passowrd` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));  
  
CREATE TABLE `pacijent`.`pregled` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datumPregleda` DATE NOT NULL,
  `idPacijenta` INT NOT NULL,
  `idDoktora` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idPacijenta_idx` (`idPacijenta` ASC) VISIBLE,
  INDEX `idDoktora_idx` (`idDoktora` ASC) VISIBLE,
  CONSTRAINT `idPacijenta`
    FOREIGN KEY (`idPacijenta`)
    REFERENCES `pacijent`.`pacijent` (`idPacijent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idDoktora`
    FOREIGN KEY (`idDoktora`)
    REFERENCES `pacijent`.`doktor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
 
  
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

