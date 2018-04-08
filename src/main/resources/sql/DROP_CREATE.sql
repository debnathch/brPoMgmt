-- MySQL Script generated by MySQL Workbench
-- Fri Sep 30 13:30:37 2016
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `purchase_order` ;

CREATE SCHEMA `purchase_order` ;
-------------------------------
-- Customer Table
-------------------------------

     DROP TABLE IF EXISTS `purchase_order`.`customer` ;

     CREATE TABLE IF NOT EXISTS `purchase_order`.`customer` (
       `customer_id`  BIGINT NOT NULL ,
       `customer_name` varchar(255) NOT NULL,
       `customer_gst` varchar(255) ,
       `customer_lisc_num` varchar(255) ,
       `customer_email` varchar(255) ,
       `customer_Phone1` varchar(255) NOT NULL,
       `customer_Phone2` varchar(255) ,
       `customer_address` varchar(255) ,
       `customer_type` varchar(255) ,

       PRIMARY KEY (`customer_id`));



-- -----------------------------------------------------
-- Schema purchase_order
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `purchase_order` DEFAULT CHARACTER SET utf8 ;
USE `purchase_order` ;

-- -----------------------------------------------------
-- Table `purchase_order`.`purchase_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `purchase_order`.`br_purchase_order` ;

CREATE TABLE IF NOT EXISTS `purchase_order`.`br_purchase_order` (
  `po_id`  BIGINT(1) NOT NULL AUTO_INCREMENT,
 -- `po_line_item_id`  INT NOT NULL,
  `customer_id` BIGINT NOT NULL,
  `is_cart`  varchar(5) NOT NULL,
  `is_active`  varchar(5) NOT NULL,
  `order_date` TIMESTAMP NULL,
  PRIMARY KEY (`po_id`)
--  foreign key  (`po_line_item_id`) REFERENCES `purchase_order`.`br_purchase_order_line`(`po_line_item_id`)
  );


-- -----------------------------------------------------
-- purchase order line item
-- -----------------------------------------------------

DROP TABLE IF EXISTS `purchase_order`.`br_purchase_order_line` ;

CREATE TABLE IF NOT EXISTS `purchase_order`.`br_purchase_order_line` (

  `po_line_item_id`  BIGINT(1) NOT NULL AUTO_INCREMENT,
  `po_id`  BIGINT NOT NULL,
  `prod_name` varchar(255) NOT NULL,
    `prod_id`  INT NOT NULL,
    `prod_description` varchar(255) NOT NULL,
    `prod_pack_size` varchar(255) NOT NULL,
    `prod_trade_price` FLOAT ,
    `prod_hsn_code` varchar(100) ,

   `product_quantity` INT NOT NULL,

  PRIMARY KEY (`po_line_item_id`),

  foreign key  (`po_id`) REFERENCES `purchase_order`.`br_purchase_order`(`po_id`)
  );




-- -----------------------------------------------------
-- order cart
-- -----------------------------------------------------

-- DROP TABLE IF EXISTS `purchase_order`.`order_cart` ;

-- CREATE TABLE IF NOT EXISTS `purchase_order`.`order_cart` (
--  `customer_id` DOUBLE NOT NULL,
--  `prod_id`  INT NOT NULL,
--  `product_quantity`  INT NOT NULL,

--  PRIMARY KEY (`customer_id`),

--   foreign key  (`prod_id`) REFERENCES `purchase_order`.`br_product_list`(`prod_id`));




SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- INSERT INTO `purchase_order`.`br_purchase_order` (`po_id`, `order_from`, `order_date`, `po_details_id`) VALUES ('2', '1','1','20' ,'2016-10-03 00:00:00', 'Y');


-- COMPANY DETAILS

DROP TABLE IF EXISTS `purchase_order`.`company` ;
CREATE TABLE IF NOT EXISTS `purchase_order`.`company` (
  `company_id`  INT NOT NULL,

  `company_name` varchar(255) NOT NULL,
  `parent_company_id` INT NOT NULL,
   `parent_company_name` varchar(255) NOT NULL,

  `company_address` varchar(255) NOT NULL,
  `company_desc` varchar(255)  ,


  PRIMARY KEY (`company_id`));




INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`parent_company_name`,`company_address`, `company_desc`)
    values (1,  'BENGAL REMEDIES', 1, 'BENGAL REMEDIES' ,'KOLKATA', 'Parent Company');

INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`parent_company_name`,`company_address`, `company_desc`)
    values (2,  'DWARKA PHERMA', 1,'BENGAL REMEDIES' ,'KOLKATA', 'BENGAL REMEDIES');

INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`parent_company_name`,`company_address`, `company_desc`)
    values (3,  'BENGAL PHERMA', 1,'BENGAL REMEDIES' ,'KOLKATA', 'BENGAL REMEDIES');

INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`parent_company_name`,`company_address`, `company_desc`)
    values (4,  'JM HEALTHCARE', 2, 'JM_REMEDIES','CHANDIGARH', 'Parent Company');

INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`parent_company_name`,`company_address`, `company_desc`)
    values (5,  'JM REMEDIES', 2, 'JM_REMEDIES' ,'CHANDIGARH', 'child Company');

INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`parent_company_name`,`company_address`, `company_desc`)
    values (6,  'ALPIC', 2, 'JM_REMEDIES' ,'CHANDIGARH', 'child Company');

INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`parent_company_name`,`company_address`, `company_desc`)
    values (7,  'KAN BIOTECH', 2, 'JM_REMEDIES' ,'CHANDIGARH', 'child Company');


-- DRUG DETAILS
 DROP TABLE IF EXISTS `purchase_order`.`br_drug_details` ;

CREATE TABLE IF NOT EXISTS `purchase_order`.`br_drug_details` (
  `drug_id`  INT NOT NULL,
  `drug_name` varchar(255) NOT NULL,
  `drug_power` INT ,
  `drug_details` varchar(255) NOT NULL,
  PRIMARY KEY (`drug_id`));

-- PRODUCT TYPE

DROP TABLE IF EXISTS `purchase_order`.`br_product_type` ;

CREATE TABLE IF NOT EXISTS `purchase_order`.`br_product_type` (
  `prod_type_id`  INT NOT NULL,
  `prod_type_name` varchar(255) NOT NULL,
   `prod_type_drug_comb` varchar(255) NOT NULL,
  PRIMARY KEY (`prod_type_id`));

   -- -----------------------------------------------------
-- insert statement .. product type data
-- -----------------------------------------------------
INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (1,  'TABLET', 'TABLET');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (2,  'LIQUID', 'LIQUID');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (3,  'CAPSULES', 'CAPSULES');

     INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (4,  'DRY SYRUP', 'DRY SYRUP');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (5,  'INJECTION', 'INJECTION');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (6,  'OINMENTS', 'OINMENTS');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (7,  'OPTHA', 'OPTHA');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (8,  'HERBAL', 'HERBAL');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (9,  'CARDIAC', 'CARDIAC');

     INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (10,  'DIABATIC', 'DIABATIC');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (11,  'TOOTHPASTE', 'TOOTH PASTE');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (12,  'POWDER', 'Powder');

    INSERT INTO `purchase_order`.`br_product_type`
        (`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
        values (13,  'SOFTGEL CAPSULES', 'CAPSULES');

-- -----------------------------------------------------
-- product list
-- -----------------------------------------------------
DROP TABLE IF EXISTS `purchase_order`.`br_product_list` ;
CREATE TABLE IF NOT EXISTS `purchase_order`.`br_product_list` (
  `prod_id`  INT NOT NULL AUTO_INCREMENT,

  `prod_type_id` INT NOT NULL,
  `company_id` INT NOT NULL,
  `prod_name` varchar(255) NOT NULL,

  `prod_description` varchar(1000) NOT NULL,
  `prod_pack_size` varchar(255) NOT NULL,
  `prod_trade_price` FLOAT ,
  `prod_hsn_code` varchar(100) ,

	foreign key  (`prod_type_id`) REFERENCES `purchase_order`.`br_product_type`(`prod_type_id`),

	PRIMARY KEY (`prod_id`));


-- -----------------------------------------------------
-- drug associated with product
-- -----------------------------------------------------

  DROP TABLE IF EXISTS `purchase_order`.`br_drug_prod_associate` ;

CREATE TABLE IF NOT EXISTS `purchase_order`.`br_drug_prod_associate` (
  `drug_prod_assoc_id`  INT NOT NULL,
  `drug_id`  INT NOT NULL,
  `prod_id` INT NOT NULL,
  `drug_prod_details` varchar(255) NOT NULL,
  foreign key  (`drug_id`) REFERENCES `purchase_order`.`br_drug_details`(`drug_id`),
   foreign key  (`prod_id`) REFERENCES `purchase_order`.`br_product_list`(`prod_id`),
  PRIMARY KEY (`drug_prod_assoc_id`));






