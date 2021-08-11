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
  `user_id` bigint(20) DEFAULT NULL COMMENT '유저 ID',
  `description` varchar(50) DEFAULT NULL COMMENT '사용자 코멘트',
  `account` varchar(50) DEFAULT NULL COMMENT '계좌번호',
  `bank_name` varchar(50) DEFAULT NULL COMMENT '은행명',
  `account_state` varchar(50) DEFAULT 'ACTIVE' COMMENT '계좌 상태',
  `use_date` date DEFAULT NULL COMMENT '최근 사용일',
  `reg_date` date DEFAULT NULL COMMENT '등록일',
  `modi_date` date DEFAULT NULL COMMENT '수정일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.alert 구조 내보내기
DROP TABLE IF EXISTS `alert`;
CREATE TABLE IF NOT EXISTS `alert` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(1024) DEFAULT NULL COMMENT '메세지 코멘트',
  `receive_user_id` bigint(20) DEFAULT NULL COMMENT '전달받은 유저 ID',
  `sand_date` date DEFAULT NULL COMMENT '발송 시간',
  `read_date` date DEFAULT NULL COMMENT '수신 시간',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.associate 구조 내보내기
DROP TABLE IF EXISTS `associate`;
CREATE TABLE IF NOT EXISTS `associate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '조합 이름',
  `associate_round` varchar(50) DEFAULT 'CREATE' COMMENT '조합 상태(진행도)',
  `operate_fee_ratio` double DEFAULT NULL COMMENT '수수료 비율',
  `end_expect_date` date DEFAULT NULL COMMENT '예상 종료일',
  `end_real_date` date DEFAULT NULL COMMENT '확정 종료일',
  `city` varchar(50) DEFAULT NULL COMMENT '시',
  `state` varchar(50) DEFAULT NULL COMMENT '구/군',
  `address` varchar(50) DEFAULT NULL COMMENT '상세 주소',
  `reg_date` date DEFAULT NULL COMMENT '등록일',
  `modi_date` date DEFAULT NULL COMMENT '수정일',
  `logo` varchar(50) DEFAULT '/img/logo_img.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.claim_prop 구조 내보내기
DROP TABLE IF EXISTS `claim_prop`;
CREATE TABLE IF NOT EXISTS `claim_prop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sale_prop_id` bigint(20) DEFAULT NULL COMMENT '판매한 부동산 ID',
  `payment` bigint(20) DEFAULT NULL COMMENT '금액',
  `round` int(11) DEFAULT NULL COMMENT '진행 상태',
  `description` varchar(1024) DEFAULT NULL COMMENT '부가 설명',
  `reg_date` date DEFAULT NULL COMMENT '등록일',
  `modi_date` date DEFAULT NULL COMMENT '수정일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.company 구조 내보내기
DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ceo_name` varchar(50) DEFAULT NULL COMMENT '대표자 명',
  `biz_reg_num` varchar(50) DEFAULT NULL COMMENT '사업자 등록번호',
  `address` varchar(50) DEFAULT NULL COMMENT '주소',
  `zip_code` varchar(50) DEFAULT NULL COMMENT '우편번호',
  `establish_date` date DEFAULT NULL COMMENT '설립일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.located_pos 구조 내보내기
DROP TABLE IF EXISTS `located_pos`;
CREATE TABLE IF NOT EXISTS `located_pos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `purchase_prop_id` bigint(20) DEFAULT NULL COMMENT '토지 ID(구매 대상)',
  `x_pos` double DEFAULT NULL COMMENT '좌표 x',
  `y_pos` double DEFAULT NULL COMMENT '좌표 y',
  `z_pos` double DEFAULT NULL COMMENT '좌표 z',
  `order_count` int(11) DEFAULT NULL COMMENT '순서',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.login_history 구조 내보내기
DROP TABLE IF EXISTS `login_history`;
CREATE TABLE IF NOT EXISTS `login_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '유저 ID',
  `login_date` date DEFAULT NULL COMMENT '로그인 일자',
  `is_login` tinyint(4) DEFAULT NULL COMMENT '로그인 성공여부',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=363 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.login_ip 구조 내보내기
DROP TABLE IF EXISTS `login_ip`;
CREATE TABLE IF NOT EXISTS `login_ip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '로그인 ID',
  `ip` varchar(50) DEFAULT NULL COMMENT '접속 IP',
  `is_active` tinyint(4) DEFAULT NULL COMMENT 'IP 인증 여부',
  `reg_date` date DEFAULT NULL COMMENT '등록일',
  `modi_date` date DEFAULT NULL COMMENT '수정일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.message 구조 내보내기
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(1024) DEFAULT NULL COMMENT '메세지',
  `sand_user_id` bigint(20) DEFAULT NULL COMMENT '발신 유저 ID',
  `receive_user_id` bigint(20) DEFAULT NULL COMMENT '수신 유저 ID',
  `sand_date` date DEFAULT NULL COMMENT '발신 일',
  `read_date` date DEFAULT NULL COMMENT '수신 일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.owned_history 구조 내보내기
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

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
  `purchase_date` date DEFAULT NULL,
  `prop_type` varchar(50) DEFAULT 'REQUIRED',
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.sale_prop 구조 내보내기
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

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.staff 구조 내보내기
DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `staff_role` varchar(50) DEFAULT 'STAFF' COMMENT '스탭 등급',
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  `associate_id` bigint(20) DEFAULT NULL COMMENT '조합 ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '유저 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 proptech.user 구조 내보내기
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '유저 ID',
  `company_id` bigint(20) DEFAULT NULL COMMENT '회사 정보 ID',
  `password` varchar(70) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '닉네임',
  `email` varchar(50) DEFAULT NULL COMMENT '이메일 주소',
  `profile_Img` varchar(50) DEFAULT '/img/proflie_img.jpg' COMMENT '프로필',
  `user_role` varchar(50) DEFAULT 'ROLE_USER' COMMENT '유저 권한',
  `user_state` varchar(50) DEFAULT 'UN_AUTH' COMMENT '유저 상태',
  `retired_date` date DEFAULT NULL COMMENT '탈퇴일',
  `reg_date` date DEFAULT NULL COMMENT '등록일',
  `modi_date` date DEFAULT NULL,
  `modi_password_date` date DEFAULT NULL COMMENT '최근 패스워드 수정일',
  `phone_number` varchar(50) DEFAULT NULL COMMENT '핸드폰 번호',
  `provider` varchar(50) DEFAULT NULL COMMENT '소셜 로그인 공급자',
  `provider_id` varchar(50) DEFAULT NULL COMMENT '소셜 로그인 ID',
  `fail_login_count` int(11) DEFAULT 0 COMMENT '로그인 실패 횟수 - 최대 10회',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 뷰 proptech.v_associate_summary 구조 내보내기
DROP VIEW IF EXISTS `v_associate_summary`;
-- VIEW 종속성 오류를 극복하기 위해 임시 테이블을 생성합니다.
CREATE TABLE `v_associate_summary` (
	`id` BIGINT(20) NOT NULL,
	`name` VARCHAR(50) NULL COMMENT '조합 이름' COLLATE 'utf8_general_ci',
	`associate_round` VARCHAR(50) NULL COMMENT '조합 상태(진행도)' COLLATE 'utf8_general_ci',
	`operate_fee_ratio` DOUBLE NULL COMMENT '수수료 비율',
	`end_expect_date` DATE NULL COMMENT '예상 종료일',
	`end_real_date` DATE NULL COMMENT '확정 종료일',
	`city` VARCHAR(50) NULL COMMENT '시' COLLATE 'utf8_general_ci',
	`state` VARCHAR(50) NULL COMMENT '구/군' COLLATE 'utf8_general_ci',
	`address` VARCHAR(50) NULL COMMENT '상세 주소' COLLATE 'utf8_general_ci',
	`reg_date` DATE NULL COMMENT '등록일',
	`totalClaimPay` DECIMAL(63,0) NULL,
	`TotalReceiptPay` DECIMAL(63,0) NULL,
	`TotalpurchasePay` DECIMAL(41,0) NULL
) ENGINE=MyISAM;

-- 뷰 proptech.v_user_summary 구조 내보내기
DROP VIEW IF EXISTS `v_user_summary`;
-- VIEW 종속성 오류를 극복하기 위해 임시 테이블을 생성합니다.
CREATE TABLE `v_user_summary` (
	`name` VARCHAR(50) NULL COMMENT '닉네임' COLLATE 'utf8_general_ci',
	`email` VARCHAR(50) NULL COMMENT '이메일 주소' COLLATE 'utf8_general_ci',
	`phone_number` VARCHAR(50) NULL COMMENT '핸드폰 번호' COLLATE 'utf8_general_ci',
	`provider` VARCHAR(50) NULL COMMENT '소셜 로그인 공급자' COLLATE 'utf8_general_ci',
	`user_role` VARCHAR(50) NULL COMMENT '유저 권한' COLLATE 'utf8_general_ci',
	`user_state` VARCHAR(50) NULL COMMENT '유저 상태' COLLATE 'utf8_general_ci',
	`reg_date` DATE NULL COMMENT '등록일',
	`login_date` DATE NULL COMMENT '로그인 일자',
	`retired_date` DATE NULL COMMENT '탈퇴일'
) ENGINE=MyISAM;

-- 테이블 proptech.web_menu 구조 내보내기
DROP TABLE IF EXISTS `web_menu`;
CREATE TABLE IF NOT EXISTS `web_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_role` varchar(50) DEFAULT 'USER',
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `order_seq` int(20) DEFAULT 0,
  `reg_date` date DEFAULT NULL,
  `modi_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 뷰 proptech.v_associate_summary 구조 내보내기
DROP VIEW IF EXISTS `v_associate_summary`;
-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `v_associate_summary`;
CREATE ALGORITHM=UNDEFINED DEFINER=`GUNS`@`localhost` SQL SECURITY DEFINER VIEW `v_associate_summary` AS select `proptech`.`associate`.`id` AS `id`,`proptech`.`associate`.`name` AS `name`,`proptech`.`associate`.`associate_round` AS `associate_round`,`proptech`.`associate`.`operate_fee_ratio` AS `operate_fee_ratio`,`proptech`.`associate`.`end_expect_date` AS `end_expect_date`,`proptech`.`associate`.`end_real_date` AS `end_real_date`,`proptech`.`associate`.`city` AS `city`,`proptech`.`associate`.`state` AS `state`,`proptech`.`associate`.`address` AS `address`,`proptech`.`associate`.`reg_date` AS `reg_date`,`sale`.`totalClaimPay` AS `totalClaimPay`,`sale`.`TotalReceiptPay` AS `TotalReceiptPay`,`purchase`.`TotalpurchasePay` AS `TotalpurchasePay` from ((`proptech`.`associate` left join (select `proptech`.`sale_prop`.`associate_id` AS `associate_id`,sum(`a`.`claimpay`) AS `totalClaimPay`,sum(`a`.`receiptPay`) AS `TotalReceiptPay` from (`proptech`.`sale_prop` left join (select `proptech`.`claim_prop`.`sale_prop_id` AS `sale_prop_id`,sum(`proptech`.`claim_prop`.`payment`) AS `claimpay`,sum(`proptech`.`receipt`.`payment`) AS `receiptPay` from (`proptech`.`claim_prop` left join `proptech`.`receipt` on(`proptech`.`claim_prop`.`id` = `proptech`.`receipt`.`claim_id`)) group by `proptech`.`claim_prop`.`sale_prop_id`) `a` on(`proptech`.`sale_prop`.`id` = `a`.`sale_prop_id`))) `sale` on(`proptech`.`associate`.`id` = `sale`.`associate_id`)) left join (select sum(`a`.`price`) AS `TotalpurchasePay`,`proptech`.`purchase_prop`.`associate_id` AS `associate_id` from (`proptech`.`purchase_prop` left join (select `proptech`.`prop_price`.`id` AS `id`,`proptech`.`prop_price`.`price` AS `price`,`proptech`.`prop_price`.`reg_date` AS `reg_date`,`proptech`.`prop_price`.`modi_date` AS `modi_date`,`proptech`.`prop_price`.`reg_ip` AS `reg_ip`,`proptech`.`prop_price`.`description` AS `description`,`proptech`.`prop_price`.`purchase_prop_id` AS `purchase_prop_id` from `proptech`.`prop_price` where `proptech`.`prop_price`.`id` in (select max(`proptech`.`prop_price`.`id`) from `proptech`.`prop_price` group by `proptech`.`prop_price`.`purchase_prop_id`)) `a` on(`proptech`.`purchase_prop`.`id` = `a`.`purchase_prop_id`))) `purchase` on(`proptech`.`associate`.`id` = `purchase`.`associate_id`));

-- 뷰 proptech.v_user_summary 구조 내보내기
DROP VIEW IF EXISTS `v_user_summary`;
-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `v_user_summary`;
CREATE ALGORITHM=UNDEFINED DEFINER=`GUNS`@`localhost` SQL SECURITY DEFINER VIEW `v_user_summary` AS select `proptech`.`user`.`name` AS `name`,`proptech`.`user`.`email` AS `email`,`proptech`.`user`.`phone_number` AS `phone_number`,`proptech`.`user`.`provider` AS `provider`,`proptech`.`user`.`user_role` AS `user_role`,`proptech`.`user`.`user_state` AS `user_state`,`proptech`.`user`.`reg_date` AS `reg_date`,`a`.`login_date` AS `login_date`,`proptech`.`user`.`retired_date` AS `retired_date` from (`proptech`.`user` left join (select `proptech`.`login_history`.`user_id` AS `user_id`,max(`proptech`.`login_history`.`login_date`) AS `login_date` from `proptech`.`login_history` group by `proptech`.`login_history`.`user_id`) `a` on(`proptech`.`user`.`id` = `a`.`user_id`));

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
