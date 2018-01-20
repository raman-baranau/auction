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
-- -----------------------------------------------------
-- Schema user_access_data
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema user_access_data
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `user_access_data` ;
USE `english_auction` ;

-- -----------------------------------------------------
-- Table `english_auction`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `english_auction`.`clients` (
  `client_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Client identifier',
  `first_name` VARCHAR(45) NOT NULL COMMENT 'First name of client',
  `patronymic` VARCHAR(45) NULL COMMENT 'Patronymic of client',
  `last_name` VARCHAR(45) NOT NULL COMMENT 'Last name of client',
  `client_balance` DOUBLE NOT NULL COMMENT 'Money that client has to place bids',
  PRIMARY KEY (`client_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `english_auction`.`lots`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `english_auction`.`lots` (
  `lot_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Lot identifier',
  `lot_name` VARCHAR(255) NOT NULL COMMENT 'Lot name (name of some product)',
  `initial_price` DOUBLE NOT NULL COMMENT 'Initial price of a product',
  `selling_price` DOUBLE NULL COMMENT 'Selling price of product or null if not sold',
  `client_id` INT NULL COMMENT 'Buyer identifier or null if not sold',
  `owner_id` INT NOT NULL COMMENT 'Identifier of product owner',
  PRIMARY KEY (`lot_id`),
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


-- -----------------------------------------------------
-- Table `english_auction`.`auction_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `english_auction`.`auction_type` (
  `auction_type_id` INT NOT NULL AUTO_INCREMENT,
  `auction_type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`auction_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `english_auction`.`auctions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `english_auction`.`auctions` (
  `auction_id` INT NOT NULL AUTO_INCREMENT COMMENT 'Auction identifier',
  `start_date` DATE NOT NULL COMMENT 'Start date of an auction',
  `start_time` TIME NOT NULL COMMENT 'Start time of an auction',
  `end_date` DATE NOT NULL COMMENT 'End date of an auction',
  `end_time` TIME NOT NULL COMMENT 'End time of an auction',
  `auction_type_id` INT NOT NULL COMMENT 'Type of an auction, \'direct\', \'reverse\' or other',
  PRIMARY KEY (`auction_id`),
  INDEX `start_date_idx` (`start_date` ASC)  COMMENT 'Index for queries with start_date column',
  INDEX `fk_auctions_auction_type1_idx` (`auction_type_id` ASC),
  CONSTRAINT `fk_auctions_auction_type1`
    FOREIGN KEY (`auction_type_id`)
    REFERENCES `english_auction`.`auction_type` (`auction_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `english_auction`.`lots_has_auctions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `english_auction`.`lots_has_auctions` (
  `lots_lot_id` INT NOT NULL COMMENT 'Lot identifier. If lot is not sold it could be placed on auction another time',
  `auctions_auction_id` INT NOT NULL COMMENT 'Auction identifier',
  PRIMARY KEY (`lots_lot_id`, `auctions_auction_id`),
  INDEX `fk_lots_has_auctions_auctions1_idx` (`auctions_auction_id` ASC),
  INDEX `fk_lots_has_auctions_lots1_idx` (`lots_lot_id` ASC),
  CONSTRAINT `fk_lots_has_auctions_lots1`
    FOREIGN KEY (`lots_lot_id`)
    REFERENCES `english_auction`.`lots` (`lot_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lots_has_auctions_auctions1`
    FOREIGN KEY (`auctions_auction_id`)
    REFERENCES `english_auction`.`auctions` (`auction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `user_access_data` ;

-- -----------------------------------------------------
-- Table `user_access_data`.`passwords`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_access_data`.`passwords` (
  `p_email` VARCHAR(64) NOT NULL COMMENT 'User email and login',
  `p_password` VARCHAR(40) NOT NULL COMMENT 'Password hash code',
  PRIMARY KEY (`p_email`),
  UNIQUE INDEX `email_UNIQUE` (`p_email` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
