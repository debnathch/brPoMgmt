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
      `customer_id`  INT NOT NULL,
      `customer_name` varchar(255) NOT NULL,
      `customer_GST` varchar(255) ,
      `customer_LiscNum` varchar(255) ,
      `customer_EMail` varchar(255) ,
      `customer_Phone1` varchar(255) NOT NULL,
      `customer_Phone2` varchar(255) ,
      `customer_address` varchar(255) NOT NULL,
      `customer_type` varchar(255) NOT NULL,

      PRIMARY KEY (`customer_id`));

select * from `purchase_order`.`customer`;

    INSERT INTO `purchase_order`.`customer`
        (`customer_id`,`customer_name`,`customer_GST`,`customer_LiscNum`, `customer_EMail`,`customer_Phone1` ,`customer_Phone2`, `customer_address`,`customer_type`)
        values (10,  'SUMITA ENTERPRISE','GST-123456','Lsc-67895','debnathch@gmail.com','9838773388','9988776655', 'KOLKATA', 'Distributor');




-- -----------------------------------------------------
-- Schema purchase_order
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `purchase_order` ;

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
  `po_id`  INT(1) NOT NULL AUTO_INCREMENT,
 -- `po_line_item_id`  INT NOT NULL,
  `customer_id` INT NOT NULL,
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

  `po_line_item_id`  INT(1) NOT NULL AUTO_INCREMENT,
  `po_id`  INT NOT NULL,
  `prod_id` INT NOT NULL,
  `product_quantity` INT NOT NULL,

  PRIMARY KEY (`po_line_item_id`),
  foreign key  (`prod_id`) REFERENCES `purchase_order`.`br_product_list`(`prod_id`),
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
  `company_address` varchar(255) NOT NULL,
  `company_desc` varchar(255) NOT NULL,


  PRIMARY KEY (`company_id`));




INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`company_address`, `company_desc`)
    values (1,  'BENGAL REMEDIES', 1, 'KOLKATA', 'Parent Company');

INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`company_address`, `company_desc`)
    values (2,  'DWARKA_PHERMA', 1,'KOLKATA', 'BENGAL REMEDIES');

INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`company_address`, `company_desc`)
    values (3,  'JM_REMEDIES', 3, 'KOLKATA', 'Parent Company');

INSERT INTO `purchase_order`.`company`
	(`company_id`,`company_name`, `parent_company_id`,`company_address`, `company_desc`)
    values (4,  'KANT_BIOTECH', 3, 'KOLKATA', 'Parent Company');



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
-- product list
-- -----------------------------------------------------
DROP TABLE IF EXISTS `purchase_order`.`br_product_list` ;
CREATE TABLE IF NOT EXISTS `purchase_order`.`br_product_list` (
  `prod_id`  INT NOT NULL,

  `prod_type_id` INT NOT NULL,
  `company_id` INT NOT NULL,
  `prod_name` varchar(255) NOT NULL,

  `prod_description` varchar(255) NOT NULL,
  `prod_pack_size` varchar(255) NOT NULL,
  `prod_trade_price` FLOAT NOT NULL,
  `prod_mrp_incl_vat` FLOAT NOT NULL,
  `prod_net_exclude_vat` FLOAT NOT NULL,

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





   -- -----------------------------------------------------
-- insert statement .. master data
-- -----------------------------------------------------

INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (1,  'TABLET', 'TABLET');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (2,  'LIQID', 'TABLET');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (3,  'CAPSULES', 'CAPSULES');

     INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (4,  'DRY SYRUP', 'DRY SYRUP');

    INSERT INTO `purchase_order`.`br_product_type`
	(`prod_type_id`,`prod_type_name`, `prod_type_drug_comb`)
    values (5,  'INJECTION', 'INJECTION');


-- TABLETS
INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (20, 1, 1,'AZIBRA-500(DPCO)', 'Azithromycin 500mg', '10x3 tab', '449.20', '561.50', '219.00');

INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (21, 1, 1, 'B-FLU','Fluconazole-150mg' ,'20X1 Tab BLISTER',	'184.00'	,'230.00',	'70.00');

INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (22, 1, 1, 'BRACEF-200LB',	'Cefixime  200 mg + +Lactic Acid Bacillus-60million spo',	'10 Tab ALU-ALU',	'1001.00',	'1300.00',	'40.00');

  INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (23, 1,1, 'BRACEF-CV',	'Cefixime  200 mg +Clavulanate Potassium 125 mg'	,'10 Tab ALU-ALU',	'154.00',	'200.00',	'80.00');

    INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (24, 1, 1, 'BRACEF-O(DPCO)',	'Cefixime 200mg+Ofloxacin 200mg',	'10 Tab ALU-ALU',	'89.60',	'112.00',	'49.28');

   INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (25, 1,1,  'BRAMOX-625-LB',	'Amoxy 500mg+ Clavulanate125mg+Lactic Acid Bacillus-60million spo',	'10 Tab ALU-ALU',	'184.80',	'240.00',	'80.00');

     INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (26, 1,1,  'BRAMOX-KID',	'Amoxycillin 200 mg + Clavulanic Acid 28.5 mg',	'10 Tab ALU-ALU',	'77.00',	'100.00',	'34.44');

     INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (27, 1, 1, 'BRAZOL-400',	'Albendazole 400 mg', 	'20X1 BLISTER',	'163.20',	'204.00',	'60.00');

     INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (28, 1, 1, 'BROND-4',	'Ondansetron-4 mg(MOUTH DESOLVING)'	 , '1X10 BLISTER',	'34.65',	'45.00',	'7.00');

     INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (29, 1, 1, 'CHYMOBEN FORTE',	'Trypsin-Chymotrypsin, 100000 Armour units of enzymatic activity',	'1X10',	'98.00',	'127.50',	'35.00');

     INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (30, 1, 1, 'DKORT-6',	'Deflazacort - 6 mg tab (Alu Alu)',	'1X10',	'55.44',	'72.00',	'17.00');

      INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (31, 1,1,  'ESO-DSR',	'Esomeprazole Magnesium Trihydrate 40mg Domperidone I.P. 30mg (ALU ALU)',	'1X10',	'61.60',	'80.00',	'17.50');


  INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (32, 1,1, 'FENTA-S',	'Aceclofenac 100 mg + Paracetamol 325 mg + serratiopeptidase 15 mg',	'1X10 BLISTER',	'50.05',	'65.00',	'16.50');


  INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (33, 1,1, 'FEVER X Tablet',	'Paracetamol 500mg + Phenylephrine 5mg + Cetirizine - 5mg','20X10 BLISTER',	'616.00',	'800.00',	'170.00');


  INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (34, 1,1,'HYPEN(DPCO)','Aceclofenac 100 mg + Paracetamol 325 mg',  	'20X10 BLISTER',	'464.00',	'580.00',	'180.00');


  INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (35, 1,1, 'LEEV-5'	,'Levocetirizine Dihydrochloride 5mg',	'1X10 BLISTER',	'15.40',	'20.00',	'5.90');


  INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (36, 1,1, 'LEEV-M',	'Montelukast 10mg + Levocetirizine 5 mg  ALU-ALU',	'1X10 BLISTER',	'80.85',	'105.00',	'28.00');


  INSERT INTO `purchase_order`.`br_product_list`
	( `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (37, 1,1, 'SUMIRON XT',	'Ferrous Ascorbate 100mg + Folic Acid 1.5mg + B12 15mcg + Zinc Sulphate',	'1X10'	,'69.30',	'90.00',	'20.12');


  INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (38, 1,1, 'POTON-40',	'Pantoprazole 40mg',	 '1X10 ALU-ALU'	,'36.96',	'48.00',	'12.70');







-- LIQUIDS
INSERT INTO `purchase_order`.`br_product_list`
	(  `prod_id`, `prod_type_id`,`company_id`,`prod_name`,`prod_description`, `prod_pack_size`, `prod_trade_price`,  `prod_mrp_incl_vat`, `prod_net_exclude_vat`)
    values (1, 2,1, 'ALLZYME Syrup', 'Alpha Amylase 60mg+ Cinnamon Oil 0.125mg + Cardamom Oil 0.25mg', '200 ml', '65.45', '85', '30');


