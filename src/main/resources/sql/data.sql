INSERT INTO `company` (`id`, `ceo_name`, `biz_reg_num`, `address`, `zip_code`, `establish_date`) VALUES
	(1, '김CC', '12345631', '', '034342', '2021-06-03');
	
INSERT INTO `user` (`id`, `username`, `company_id`, `password`, `name`, `email`, `profile_Img`, `user_role`, `user_state`, `retired_date`, `reg_date`, `modi_date`, `modi_password_date`, `phone_number`) VALUES
	(1, 'Admin', NULL, '$2a$10$Izrc7nyTyH38JVE/uD37qui1bqy7/gr22UpgSHqi/W4eQKVdPV41q', '관리자', NULL, 'img/proflie_img.jpg', 'ROLE_ADMIN', 'AUTH', NULL, '2021-06-03', NULL, NULL, '02-3554-0901'),
	(2, 'Manager', NULL, '$2a$10$bGruS8.UvG9FYOFV6zfS3OoKlOEto/YvNIqu6N3NKTx5rkTJie1sO', '운영자', NULL, 'img/proflie_img.jpg', 'ROLE_MANAGER', 'AUTH', NULL, '2021-06-03', NULL, NULL, '02-3456-2034'),
	(3, 'Partner', 1, '$2a$10$nKqCj682/pL3B1QlYVMeluSxr8xB/JySi6aO1SU8IRfsEgFIlrnO6', '파트너사', NULL, 'img/proflie_img.jpg', 'ROLE_PARTNER', 'AUTH', NULL, '2021-06-03', NULL, NULL, '02-3456-1245'),
	(4, 'Staff01', NULL, '$2a$10$TypW6qPn2o7441NNoSS85e5NauidkmxYzKpOPyora.8i6DMnAgDIm', '스탭1', NULL, 'img/proflie_img.jpg', 'ROLE_STAFF', 'AUTH', NULL, '2021-06-03', NULL, NULL, '010-3434-3941'),
	(5, 'Staff02', NULL, '$2a$10$IVXqd80UCAtoWNiQWyqPm.A.Aoeai7oRD2KFJPCPa11Kec163Pjsu', '스탭2', NULL, 'img/proflie_img.jpg', 'ROLE_USER', 'AUTH', NULL, '2021-06-03', NULL, NULL, NULL),
	(6, 'User01', NULL, '$2a$10$si4DIExdyVrzHFJfwU/G7e9hW/DhvnsInThaKOK0vc/PowEVt1Wtu', '김OO', NULL, 'img/proflie_img.jpg', 'ROLE_USER', 'UN_AUTH', NULL, '2021-06-03', NULL, NULL, NULL),
	(7, 'User02', NULL, '$2a$10$zTt24aA0Gtx7xWV97Ffe4Of0w8D5baZzUvGLHE5mT8MRz64j0H7YW', '이OO', NULL, 'img/proflie_img.jpg', 'ROLE_USER', 'UN_AUTH', NULL, '2021-06-03', NULL, NULL, NULL),
	(8, 'User03', NULL, '$2a$10$IdzE20lwxNazrf8WrNMOdeIRyQMuOVxHly9v1DBtVwIon.B7JEid6', '박OO', NULL, 'img/proflie_img.jpg', 'ROLE_USER', 'UN_AUTH', NULL, '2021-06-03', NULL, NULL, NULL);



INSERT INTO `account` (`id`, `user_id`, `description`, `account`, `bank_name`, `account_state`, `use_date`, `reg_date`, `modi_date`) VALUES
	(1, 6, NULL, '110-3345-4533', '신한은행', 'ACTIVE', '2021-06-03', '2021-06-03', NULL),
	(2, 6, NULL, '110-4352-453', '신한은행', 'ACTIVE', '2021-08-01', '2019-06-12', NULL),
	(3, 7, NULL, '70122-434-12', '국민은행', 'ACTIVE', '2019-06-03', '2018-06-03', NULL),
	(4, 3, '대표계좌', '3412-5124-125', '우리은행', 'ACTIVE', '2021-06-04', '2021-06-04', NULL);



INSERT INTO `associate` (`id`, `name`, `associate_state`, `operate_fee_ratio`, `end_expect_date`, `end_real_date`, `city`, `state`, `address`) VALUES
	(1, '강남 조합', 'CREATE', 0.2, '2025-08-03', NULL, '서울', '강남구', '도산대로');



INSERT INTO `claim` (`id`, `sale_prop_id`, `payment`, `round`, `reg_date`, `modi_date`) VALUES
	(1, 1, 30000, 1, '2021-06-03', NULL),
	(2, 1, 50000, 2, '2021-06-03', NULL),
	(3, 1, 50000, 3, '2021-06-03', NULL);



INSERT INTO `owned_history` (`id`, `reg_date`, `modi_date`, `owned_state`, `user_id`, `sale_prop_id`) VALUES
	(1, '2021-06-03', NULL, 'OWNED', 6, 1);

INSERT INTO `purchase_prop` (`id`, `associate_id`, `city`, `state`, `zip_code`, `address`, `address_detail`, `price`, `purchase_date`, `prop_type`) VALUES
	(1, 1, '서울', '강남구', NULL, NULL, NULL, 30000, NULL, 'REQUIRED'),
	(2, 1, '서울', '강남구', NULL, NULL, NULL, 50000, NULL, 'REQUIRED'),
	(3, 1, '서울', '강남구', NULL, NULL, NULL, 300, NULL, 'PURCHASED'),
	(4, 1, '서울', '강남구', NULL, NULL, NULL, 34500, NULL, 'REQUIRED');

INSERT INTO `sale_prop` (`id`, `associate_id`, `address_detail`, `sale_round`, `reg_date`, `modi_date`) VALUES
	(1, 1, 'A101동 101호', 1, '2021-06-03', NULL),
	(2, 1, 'A101동 102호', 1, '2021-06-03', NULL),
	(3, 1, 'A101동 103호', 1, '2021-06-03', NULL),
	(4, 1, 'A101동 104호', 1, '2021-06-03', NULL),
	(5, 1, 'A101동 105호', 1, '2021-06-03', NULL);

INSERT INTO `staff` (`id`, `staff_role`, `reg_date`, `modi_date`, `associate_id`, `user_id`) VALUES
	(1, 'MANAGER', '2021-06-03', NULL, 1, 4),
	(2, 'STAFF', '2021-06-03', NULL, 1, 5);


