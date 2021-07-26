
-- MariaDB에서 사용
-- DROP DATABASE IF EXISTS `proptech`;
-- CREATE DATABASE IF NOT EXISTS `proptech`
-- USE `proptech`;



DROP TABLE IF EXISTS account;
CREATE TABLE account (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) DEFAULT NULL,
  description varchar(50) DEFAULT NULL,
  account varchar(50) DEFAULT NULL,
  fail_login_count int(11) DEFAULT 0,
  bank_name varchar(50) DEFAULT NULL,
  account_state varchar(50) DEFAULT 'ACTIVE',
  use_date date DEFAULT NULL,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS alert;
CREATE TABLE alert (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  message varchar(1024) DEFAULT NULL,
  receive_user_id bigint(20) DEFAULT NULL,
  sand_date date DEFAULT NULL,
  read_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS associate;
CREATE TABLE associate (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  associate_round varchar(50) DEFAULT 'CREATE',
  operate_fee_ratio double DEFAULT NULL,
  end_expect_date date DEFAULT NULL,
  end_real_date date DEFAULT NULL,
  city varchar(50) DEFAULT NULL,
  state varchar(50) DEFAULT NULL,
  address varchar(50) DEFAULT NULL,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS claim_prop;
CREATE TABLE claim_prop (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  sale_prop_id bigint(20) DEFAULT NULL,
  payment bigint(20) DEFAULT NULL,
  round int(11) DEFAULT NULL,
  description varchar(1024) DEFAULT NULL,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS company;
CREATE TABLE company (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  ceo_name varchar(50) DEFAULT NULL,
  biz_reg_num varchar(50) DEFAULT NULL,
  address varchar(50) DEFAULT NULL,
  zip_code varchar(50) DEFAULT NULL,
  establish_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS located_pos;
CREATE TABLE located_pos (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  purchase_prop_id bigint(20) DEFAULT NULL,
  x_pos double DEFAULT NULL,
  y_pos double DEFAULT NULL,
  z_pos double DEFAULT NULL,
  order_count int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS login_history;
CREATE TABLE login_history (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) DEFAULT NULL,
  login_date date DEFAULT NULL,
  login_ip varchar(50) DEFAULT NULL,
  is_login tinyint(4) DEFAULT NULL,
  ip_checked varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS message;
CREATE TABLE message (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  message varchar(1024) DEFAULT NULL,
  sand_user_id bigint(20) DEFAULT NULL,
  receive_user_id bigint(20) DEFAULT NULL,
  sand_date date DEFAULT NULL,
  read_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS owned_history;
CREATE TABLE owned_history (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  owned_state varchar(50) DEFAULT 'OWNED',
  user_id bigint(20) DEFAULT NULL,
  sale_prop_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS prop_price;
CREATE TABLE prop_price (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  price bigint(20) DEFAULT NULL,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  reg_ip varchar(50) DEFAULT NULL,
  description varchar(50) DEFAULT NULL,
  purchase_prop_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS purchase_prop;
CREATE TABLE purchase_prop (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  associate_id bigint(20) DEFAULT NULL,
  city varchar(50) DEFAULT NULL,
  state varchar(50) DEFAULT NULL,
  zip_code varchar(50) DEFAULT NULL,
  address varchar(50) DEFAULT NULL,
  address_detail varchar(50) DEFAULT NULL,
  price bigint(20) DEFAULT NULL,
  purchase_date date DEFAULT NULL,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  prop_type varchar(50) DEFAULT 'REQUIRED',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS receipt;
CREATE TABLE receipt (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  claim_id bigint(20) DEFAULT NULL,
  payment bigint(20) DEFAULT NULL,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  buyer_ip varchar(50) DEFAULT NULL,
  user_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS sale_prop;
CREATE TABLE sale_prop (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  associate_id bigint(20) DEFAULT NULL,
  address_detail varchar(50) DEFAULT NULL,
  sale_round int(11) DEFAULT NULL,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS staff;
CREATE TABLE staff (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  staff_role varchar(50) DEFAULT 'STAFF',
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  associate_id bigint(20) DEFAULT NULL,
  user_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  company_id bigint(20) DEFAULT NULL,
  password varchar(70) NOT NULL,
  name varchar(50) DEFAULT NULL,
  email varchar(50) DEFAULT NULL,
  profile_Img varchar(50) DEFAULT '/img/proflie_img.jpg',
  user_role varchar(50) DEFAULT 'ROLE_USER',
  user_state varchar(50) DEFAULT 'UN_AUTH',
  retired_date date DEFAULT NULL,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  modi_password_date date DEFAULT NULL,
  phone_number varchar(50) DEFAULT NULL,
  provider varchar(50) DEFAULT NULL,
  provider_id varchar(50) DEFAULT NULL,
  fail_login_count int(11) DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY username (username)
);

DROP TABLE IF EXISTS web_menu;
CREATE TABLE web_menu (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_role varchar(50) DEFAULT 'USER',
  name varchar(50) DEFAULT NULL,
  url varchar(50) DEFAULT NULL,
  parent_id bigint(20) DEFAULT NULL,
  order_seq int(20) DEFAULT 0,
  reg_date date DEFAULT NULL,
  modi_date date DEFAULT NULL,
  PRIMARY KEY (id)
);


-- N+1 문제를 해결하는 방법으로 VIEW를 생성하는 것을 고려함
--
CREATE OR REPLACE VIEW V_ASSOCIATE_SUMMARY AS
SELECT *
FROM   (SELECT *
        FROM   associate
               LEFT JOIN (SELECT associate_id,
                                 Sum(claimpay)  totalClaimPay,
                                 Sum(receiptpay)TotalReceiptPay
                          FROM   sale_prop
                                 LEFT JOIN (SELECT sale_prop_id,
																	Sum(claim_prop.payment)claimpay,
																	Sum(receipt.payment)   receiptPay
															FROM   claim_prop
																		LEFT JOIN receipt
      																ON claim_prop.id =  receipt.claim_id
															GROUP  BY sale_prop_id)A
											ON sale_prop.id = A.sale_prop_id)sale
					ON associate.id = sale.associate_id) info
LEFT JOIN (SELECT A.associate_id,
						A.totalpurchaseneedpaid,
						B.totalpurchasepaid
				FROM
				 (SELECT associate_id,
					Sum(pay)totalPurchaseNeedPaid
					FROM   (SELECT purchase_prop.associate_id,
										Max(prop_price.price)pay
								FROM  purchase_prop
					LEFT JOIN prop_price ON purchase_prop.id = prop_price.purchase_prop_id
                GROUP  BY purchase_prop.id)A
            GROUP  BY A.associate_id)A
JOIN (SELECT associate_id,
        Sum(pay)totalPurchasePaid
      FROM   (SELECT purchase_prop.associate_id,
                Max(prop_price.price)pay
        FROM   purchase_prop
        LEFT JOIN prop_price ON purchase_prop.id =
     prop_price.purchase_prop_id
WHERE  purchase_prop.prop_type = 'PURCHASED'
        GROUP  BY purchase_prop.id)B
        GROUP  BY B.associate_id)B
ON A.associate_id = b.associate_id)paid
ON info.id = paid.associate_id
