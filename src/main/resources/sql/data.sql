/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DELETE FROM `account`;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `user_id`, `description`, `account`, `bank_name`, `account_state`, `use_date`, `reg_date`, `modi_date`) VALUES
	(1, 6, NULL, '110-3345-4533', '신한은행', 'ACTIVE', '2021-06-03', '2021-06-03', NULL),
	(2, 6, NULL, '110-4352-453', '신한은행', 'ACTIVE', '2021-08-01', '2019-06-12', NULL),
	(3, 7, NULL, '70122-434-12', '국민은행', 'ACTIVE', '2019-06-03', '2018-06-03', NULL),
	(4, 3, '대표계좌', '3412-5124-125', '우리은행', 'ACTIVE', '2021-06-04', '2021-06-04', NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

DELETE FROM `alert`;
/*!40000 ALTER TABLE `alert` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert` ENABLE KEYS */;

DELETE FROM `associate`;
/*!40000 ALTER TABLE `associate` DISABLE KEYS */;
INSERT INTO `associate` (`id`, `name`, `associate_round`, `operate_fee_ratio`, `end_expect_date`, `end_real_date`, `city`, `state`, `address`, `reg_date`, `modi_date`, `logo`) VALUES
	(1, '강남 조합', 'CREATE', 0.2, '2025-08-03', '2027-10-05', '서울', '강남구', '도산대로', '2021-06-28', NULL, NULL),
	(3, '테스트 조합', 'CREATE', 0.2, '2025-08-03', '2026-06-28', '경기도', '김포', '하성면 석탄리', '2021-06-28', NULL, NULL);
/*!40000 ALTER TABLE `associate` ENABLE KEYS */;

DELETE FROM `claim_prop`;
/*!40000 ALTER TABLE `claim_prop` DISABLE KEYS */;
INSERT INTO `claim_prop` (`id`, `sale_prop_id`, `payment`, `round`, `description`, `reg_date`, `modi_date`) VALUES
	(1, 1, 30000, 1, '1차 운영 금액', '2021-06-03', NULL),
	(2, 1, 50000, 2, '2차 운영 금액', '2021-06-03', NULL),
	(3, 1, 90000, 3, '3차 운영 금액', '2021-06-03', NULL),
	(4, 2, 30000, 1, '1차 운영 금액', '2021-06-05', NULL),
	(5, 2, 50000, 2, '2차 운영 금액', '2021-06-05', NULL);
/*!40000 ALTER TABLE `claim_prop` ENABLE KEYS */;

DELETE FROM `company`;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`id`, `ceo_name`, `biz_reg_num`, `address`, `zip_code`, `establish_date`) VALUES
	(1, '김CC', '12345631', '', '034342', '2021-06-03');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;

DELETE FROM `located_pos`;
/*!40000 ALTER TABLE `located_pos` DISABLE KEYS */;
/*!40000 ALTER TABLE `located_pos` ENABLE KEYS */;

DELETE FROM `login_history`;
/*!40000 ALTER TABLE `login_history` DISABLE KEYS */;
INSERT INTO `login_history` (`id`, `user_id`, `login_date`, `is_login`) VALUES
	(1, 1, '2021-06-25', 1),
	(2, 1, '2021-06-25', 1),
	(3, 1, '2021-06-25', 1),
	(4, 1, '2021-06-25', 1),
	(5, 1, '2021-06-25', 1),
	(6, 1, '2021-06-25', 1),
	(7, 1, '2021-06-25', 1),
	(8, 1, '2021-06-25', 1),
	(9, 1, '2021-06-25', 1);

/*!40000 ALTER TABLE `login_history` ENABLE KEYS */;

DELETE FROM `login_ip`;
/*!40000 ALTER TABLE `login_ip` DISABLE KEYS */;
INSERT INTO `login_ip` (`id`, `user_id`, `ip`, `is_active`, `reg_date`, `modi_date`) VALUES
	(1, 1, '0:0:0:0:0:0:0:1', 1, '2021-08-02', '2021-08-02'),
	(2, 1, '127.0.0.1', 1, '2021-08-02', '2021-08-02');
/*!40000 ALTER TABLE `login_ip` ENABLE KEYS */;

DELETE FROM `message`;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;

DELETE FROM `owned_history`;
/*!40000 ALTER TABLE `owned_history` DISABLE KEYS */;
INSERT INTO `owned_history` (`id`, `reg_date`, `modi_date`, `owned_state`, `user_id`, `sale_prop_id`) VALUES
	(1, '2021-06-03', NULL, 'OWNED', 6, 1);
/*!40000 ALTER TABLE `owned_history` ENABLE KEYS */;

DELETE FROM `prop_price`;
/*!40000 ALTER TABLE `prop_price` DISABLE KEYS */;
INSERT INTO `prop_price` (`id`, `price`, `reg_date`, `modi_date`, `reg_ip`, `description`, `purchase_prop_id`) VALUES
	(1, 100, '2021-02-16', NULL, '127.0.0.1', '최초 거래값', 3),
	(2, 300, '2021-05-16', '2021-05-16', '127.0.0.1', '제시가 증가', 3),
	(3, 500, '2021-07-16', '2021-07-16', '127.0.0.1', '제시가 증가', 3),
	(4, 1200, '2021-07-16', '2021-07-16', '127.0.0.1', '제시가 증가', 1),
	(5, 2500, '2021-07-16', '2021-07-16', '127.0.0.1', '제시가 증가', 2),
	(6, 800, '2021-07-16', '2021-07-16', '127.0.0.1', '제시가 증가', 4);
/*!40000 ALTER TABLE `prop_price` ENABLE KEYS */;

DELETE FROM `purchase_prop`;
/*!40000 ALTER TABLE `purchase_prop` DISABLE KEYS */;
INSERT INTO `purchase_prop` (`id`, `associate_id`, `city`, `state`, `zip_code`, `address`, `address_detail`, `purchase_date`, `prop_type`, `reg_date`, `modi_date`) VALUES
	(1, 1, '서울', '강남구', NULL, '일원동', NULL, NULL, 'REQUIRED', '2021-02-18', NULL),
	(2, 1, '서울', '강남구', NULL, '반포동', NULL, NULL, 'REQUIRED', '2021-02-19', NULL),
	(3, 1, '서울', '강남구', NULL, '신사동', NULL, '2021-04-16', 'PURCHASED', '2021-02-19', NULL),
	(4, 1, '서울', '강남구', NULL, '대치동', NULL, NULL, 'REQUIRED', '2021-03-19', NULL);
/*!40000 ALTER TABLE `purchase_prop` ENABLE KEYS */;

DELETE FROM `receipt`;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` (`id`, `claim_id`, `payment`, `reg_date`, `modi_date`, `buyer_ip`, `user_id`) VALUES
	(1, 1, 1000, '2021-05-06', NULL, '127.0.0.1', 5),
	(2, 1, 2000, '2021-05-16', NULL, '127.0.0.1', 5),
	(3, 2, 1500, '2021-07-16', NULL, '127.0.0.1', 5);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;

DELETE FROM `sale_prop`;
/*!40000 ALTER TABLE `sale_prop` DISABLE KEYS */;
INSERT INTO `sale_prop` (`id`, `associate_id`, `address_detail`, `sale_round`, `reg_date`, `modi_date`) VALUES
	(1, 1, 'A101동 101호', 1, '2021-06-03', NULL),
	(2, 1, 'A101동 102호', 1, '2021-06-03', NULL),
	(3, 1, 'A101동 103호', 1, '2021-06-03', NULL),
	(4, 1, 'A101동 104호', 1, '2021-06-03', NULL),
	(5, 1, 'A101동 105호', 1, '2021-06-03', NULL);
/*!40000 ALTER TABLE `sale_prop` ENABLE KEYS */;

DELETE FROM `staff`;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` (`id`, `staff_role`, `reg_date`, `modi_date`, `associate_id`, `user_id`) VALUES
	(1, 'MANAGER', '2021-06-03', NULL, 1, 4),
	(2, 'STAFF', '2021-06-03', NULL, 1, 5);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;

DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `company_id`, `password`, `name`, `email`, `profile_Img`, `user_role`, `user_state`, `retired_date`, `reg_date`, `modi_date`, `modi_password_date`, `phone_number`, `provider`, `provider_id`, `fail_login_count`) VALUES
	(1, 'Admin', NULL, '$2a$10$Izrc7nyTyH38JVE/uD37qui1bqy7/gr22UpgSHqi/W4eQKVdPV41q', '관리자', NULL, '/img/profile_img.jpg', 'ROLE_ADMIN', 'AUTH', NULL, '2021-05-03', '2021-08-09', NULL, '02-3554-0901', NULL, NULL, 0),
	(2, 'Manager', NULL, '$2a$10$bGruS8.UvG9FYOFV6zfS3OoKlOEto/YvNIqu6N3NKTx5rkTJie1sO', '운영자', NULL, '/img/profile_img.jpg', 'ROLE_MANAGER', 'AUTH', NULL, '2021-05-03', NULL, NULL, '02-3456-2034', NULL, NULL, 0),
	(3, 'Partner', 1, '$2a$10$nKqCj682/pL3B1QlYVMeluSxr8xB/JySi6aO1SU8IRfsEgFIlrnO6', '파트너사', NULL, '/img/profile_img.jpg', 'ROLE_PARTNER', 'AUTH', NULL, '2021-06-03', NULL, NULL, '02-3456-1245', NULL, NULL, 0),
	(4, 'Staff01', NULL, '$2a$10$TypW6qPn2o7441NNoSS85e5NauidkmxYzKpOPyora.8i6DMnAgDIm', '스탭1', NULL, '/img/profile_img.jpg', 'ROLE_STAFF', 'AUTH', NULL, '2021-06-03', NULL, NULL, '010-3434-3941', NULL, NULL, 0),
	(5, 'Staff02', NULL, '$2a$10$IVXqd80UCAtoWNiQWyqPm.A.Aoeai7oRD2KFJPCPa11Kec163Pjsu', '스탭2', NULL, '/img/profile_img.jpg', 'ROLE_USER', 'AUTH', NULL, '2021-06-03', NULL, NULL, '', NULL, NULL, 0),
	(6, 'User01', NULL, '$2a$10$si4DIExdyVrzHFJfwU/G7e9hW/DhvnsInThaKOK0vc/PowEVt1Wtu', '김OO', NULL, '/img/profile_img.jpg', 'ROLE_USER', 'UN_AUTH', NULL, '2021-06-03', NULL, NULL, '', NULL, NULL, 0),
	(7, 'User02', NULL, '$2a$10$zTt24aA0Gtx7xWV97Ffe4Of0w8D5baZzUvGLHE5mT8MRz64j0H7YW', '이OO', NULL, '/img/profile_img.jpg', 'ROLE_USER', 'UN_AUTH', NULL, '2021-06-03', NULL, NULL, ' ', NULL, NULL, 0),
	(8, 'User03', NULL, '$2a$10$IdzE20lwxNazrf8WrNMOdeIRyQMuOVxHly9v1DBtVwIon.B7JEid6', '박OO', NULL, '/img/profile_img.jpg', 'ROLE_USER', 'UN_AUTH', NULL, '2021-06-03', NULL, NULL, ' ', NULL, NULL, 0),
	(10, 'User04', NULL, '$2a$10$OjlOJpRtwbabKKuJcVosouOuz67trdo4OsGJymP1MS0.BKLQM/jHS', '손', '111@111.ccc', '/img/profile_img.jpg', 'ROLE_USER', 'UN_AUTH', NULL, '2021-06-10', '2021-06-10', NULL, ' ', NULL, NULL, 0),
	(11, 'Admin1', NULL, '$2a$10$Izrc7nyTyH38JVE/uD37qui1bqy7/gr22UpgSHqi/W4eQKVdPV41q', '관리자', NULL, '/img/profile_img.jpg', 'ROLE_ADMIN', 'AUTH', NULL, '2021-06-03', NULL, NULL, '02-3554-0901', NULL, NULL, 0),
	(12, 'Admin2', NULL, '$2a$10$Izrc7nyTyH38JVE/uD37qui1bqy7/gr22UpgSHqi/W4eQKVdPV41q', '관리자', NULL, '/img/profile_img.jpg', 'ROLE_ADMIN', 'AUTH', NULL, '2021-06-03', NULL, NULL, '02-3554-0901', NULL, NULL, 0),
	(19, 'qwer', NULL, '$2a$10$/1OvaGio1XHWC6nUReC34e699nl.29wQ9pOT2U589ViZilBr9Ku2W', '야무치', 'kafei@naver.com', '/img/profile_img.jpg', 'ROLE_USER', 'UN_AUTH', NULL, '2021-07-09', '2021-07-09', NULL, NULL, NULL, NULL, 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

DELETE FROM `web_menu`;
/*!40000 ALTER TABLE `web_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `web_menu` ENABLE KEYS */;

DELETE FROM `file_info`;
/*!40000 ALTER TABLE `file_info` DISABLE KEYS */;
INSERT INTO `file_info` (`id`, `orig_filename`, `filename`, `filePath`) VALUES
	(1, 'profile_img.jpg', 'profile_img.jpg', '/img/profile_img.jpg'),
	(2, 'logo_img.jpg', 'logo_img.jpg', '/img/logo_img.jpg');

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
