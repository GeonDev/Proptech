
DROP DATABASE IF EXISTS `proptech`;
CREATE DATABASE IF NOT EXISTS `proptech` 
USE `proptech`;

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `bank_name` varchar(50) DEFAULT NULL,
  `account_state` varchar(50) DEFAULT 'ACTIVE',
  `use_date` date DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `associate`;
CREATE TABLE IF NOT EXISTS `associate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `associate_state` varchar(50) DEFAULT 'CREATE',
  `operate_fee_ratio` double DEFAULT NULL,
  `end_expect_date` date DEFAULT NULL,
  `end_real_date` date DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `claim`;
CREATE TABLE IF NOT EXISTS `claim` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sale_prop_id` bigint(20) DEFAULT NULL,
  `payment` bigint(20) DEFAULT NULL,
  `round` int(11) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ceo_name` varchar(50) DEFAULT NULL,
  `biz_reg_num` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `zip_code` varchar(50) DEFAULT NULL,
  `establish_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `located_pos`;
CREATE TABLE IF NOT EXISTS `located_pos` (
  `id` bigint(20) NOT NULL DEFAULT 0,
  `purchase_prop_id` bigint(20) DEFAULT NULL,
  `x_pos` double DEFAULT NULL,
  `y_pos` double DEFAULT NULL,
  `z_pos` double DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `login_history`;
CREATE TABLE IF NOT EXISTS `login_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `login_date` date DEFAULT NULL,
  `login_ip` varchar(50) DEFAULT NULL,
  `is_login` tinyint(4) DEFAULT NULL,
  `ip_checked` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `owned_history`;
CREATE TABLE IF NOT EXISTS `owned_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  `owned_state` varchar(50) DEFAULT 'OWNED',
  `user_id` bigint(20) DEFAULT NULL,
  `sale_prop_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `prop_price`;
CREATE TABLE IF NOT EXISTS `prop_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` bigint(20) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  `reg_ip` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `purchase_prop_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `purchase_prop`;
CREATE TABLE IF NOT EXISTS `purchase_prop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `associate_id` bigint(20) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `zip_code` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `address_detail` varchar(50) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `prop_type` varchar(50) DEFAULT 'REQUIRED',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `receipt`;
CREATE TABLE IF NOT EXISTS `receipt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `claim_id` bigint(20) DEFAULT NULL,
  `payment` bigint(20) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  `buyer_ip` varchar(50) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sale_prop`;
CREATE TABLE IF NOT EXISTS `sale_prop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `associate_id` bigint(20) DEFAULT NULL,
  `address_detail` varchar(50) DEFAULT NULL,
  `sale_round` int(11) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `staff_role` varchar(50) DEFAULT 'STAFF',
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  `associate_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `profile_Img` varchar(50) DEFAULT 'img/proflie_img.jpg',
  `user_role` varchar(50) DEFAULT 'USER',
  `user_state` varchar(50) DEFAULT 'UN_AUTH',
  `retired_date` date DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  `modi_password_date` date DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
