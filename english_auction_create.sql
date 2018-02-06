-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema english_auction
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema english_auction
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `english_auction` DEFAULT CHARACTER SET utf8 ;
USE `english_auction` ;

-- -----------------------------------------------------
-- Table `english_auction`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `english_auction`.`clients` (
  `client_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Client identifier',
  `first_name` VARCHAR(45) NOT NULL COMMENT 'First name of client',
  `last_name` VARCHAR(45) NOT NULL COMMENT 'Last name of client',
  `c_login` VARCHAR(20) NOT NULL COMMENT 'Login to sign in',
  `c_password` VARCHAR(64) NOT NULL COMMENT 'Password hash code for user to sign in',
  `c_email` VARCHAR(45) NOT NULL COMMENT 'Contact email of user',
  `c_phone_number` VARCHAR(25) NULL COMMENT 'Contact phone number of user',
  `user_type` ENUM('CLIENT', 'ADMIN') NOT NULL COMMENT 'Type of user',
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `c_login_UNIQUE` (`c_login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `english_auction`.`auctions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `english_auction`.`auctions` (
  `auction_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Auction identifier',
  `lot_name` VARCHAR(80) NOT NULL COMMENT 'Lot name (name of some product)',
  `lot_description` VARCHAR(255) NOT NULL COMMENT 'Description of a lot',
  `start_date` DATETIME NOT NULL COMMENT 'Start date and time of the auction',
  `end_date` DATETIME NOT NULL COMMENT 'End date and time of the auction',
  `initial_price` DOUBLE NOT NULL COMMENT 'Initial price of a product',
  `selling_price` DOUBLE NULL COMMENT 'Selling price of product or null if it is not sold',
  `client_id` INT NULL COMMENT 'Buyer identifier or null if not sold',
  `owner_id` INT NOT NULL COMMENT 'Identifier of product owner',
  `auction_type` ENUM('STRAIGHT', 'REVERSE') NOT NULL COMMENT 'Type of auction',
  PRIMARY KEY (`auction_id`),
  INDEX `fk_lots_clients_idx` (`client_id` ASC),
  INDEX `name_idx` (`lot_name` ASC)  COMMENT 'Index for queries with name column',
  INDEX `name_initial_price_idx` (`lot_name` ASC, `initial_price` ASC)  COMMENT 'Index for queries with name and initial price columns',
  INDEX `name_selling_price_idx` (`lot_name` ASC, `selling_price` ASC)  COMMENT 'Index for queries with name and initial price columns',
  INDEX `fk_lots_clients1_idx` (`owner_id` ASC),
  CONSTRAINT `fk_lots_clients`
    FOREIGN KEY (`client_id`)
    REFERENCES `english_auction`.`clients` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lots_clients1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `english_auction`.`clients` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
