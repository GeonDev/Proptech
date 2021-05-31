-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.8-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- proptech 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `proptech`;
CREATE DATABASE IF NOT EXISTS `proptech` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `proptech`;

-- 테이블 proptech.account 구조 내보내기
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `bank_name` varchar(50) DEFAULT NULL,
  `account_state` varchar(50) DEFAULT NULL,
  `use_date` date DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.associate 구조 내보내기
DROP TABLE IF EXISTS `associate`;
CREATE TABLE IF NOT EXISTS `associate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `associate_state` varchar(50) DEFAULT NULL,
  `operrate_fee_ratio` double DEFAULT NULL,
  `end_expect_date` date DEFAULT NULL,
  `end_real_date` date DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.claim 구조 내보내기
DROP TABLE IF EXISTS `claim`;
CREATE TABLE IF NOT EXISTS `claim` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sale_prop_id` bigint(20) DEFAULT NULL,
  `payment` bigint(20) DEFAULT NULL,
  `round` int(11) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.company 구조 내보내기
DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ceo_name` varchar(50) DEFAULT NULL,
  `biz_reg_num` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `zip_code` varchar(50) DEFAULT NULL,
  `establish_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.locate_pos 구조 내보내기
DROP TABLE IF EXISTS `located_pos`;
CREATE TABLE IF NOT EXISTS `locate_pos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_prop_id` int(11) DEFAULT NULL,
  `x_pos` DOUBLE DEFAULT NULL,
  `y_pos` DOUBLE DEFAULT NULL,
  `z_pos` DOUBLE DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.login_history 구조 내보내기
DROP TABLE IF EXISTS `login_history`;
CREATE TABLE IF NOT EXISTS `login_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `login_date` date DEFAULT NULL,
  `login_ip` varchar(50) DEFAULT NULL,
  `is_login` tinyint(4) DEFAULT NULL,
  `ip_cheched` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.owned_history 구조 내보내기
DROP TABLE IF EXISTS `owned_history`;
CREATE TABLE IF NOT EXISTS `owned_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  `owned_state` varchar(50) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `sale_prop_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.prop_price 구조 내보내기
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

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.purchase_prop 구조 내보내기
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
  `prop_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.receipt 구조 내보내기
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

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.sale_prop 구조 내보내기
DROP TABLE IF EXISTS `sale_prop`;
CREATE TABLE IF NOT EXISTS `sale_prop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `associate_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address_detail` varchar(50) DEFAULT NULL,
  `sale_round` int(11) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.staff 구조 내보내기
DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `staff_role` varchar(50) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  `associate_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.user 구조 내보내기
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_id` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `proflie_img` varchar(50) DEFAULT NULL,
  `user_role` varchar(50) DEFAULT NULL,
  `user_state` varchar(50) DEFAULT NULL,
  `retired_date` date DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  `modi_password_date` date DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
