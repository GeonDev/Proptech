-- 유저 정보
call next value for hibernate_sequence;
insert into user (`id`, `password`, `name`, `email`, `user_role`, `reg_date`, `login_date`, `updated_date`, `remove_date`) values (1, '1234', 'cada', 'cada@fastcampus.com','USER', now(), now(), null, null);

call next value for hibernate_sequence;
insert into user (`id`, `password`, `name`, `email`, `user_role`, `reg_date`, `login_date`, `updated_date`, `remove_date`) values (2, '2345', 'kada', 'kada@fastcampus.com','USER', now(), now(), null, null);

-- 계좌 정보
call next value for hibernate_sequence;
insert into account (`id`, `name`, `account`, user_id, `state`, `reg_date`, `updated_date`, `use_date`) values (1, '신한은행', '0834-3452-4593',1, 'ACTIVE', now(), null, now()  );

call next value for hibernate_sequence;
insert into account (`id`, `name`, `account`, user_id, `state`, `reg_date`, `updated_date`, `use_date`) values (2, '국민은행', '0834er2asdf593',1, 'ACTIVE', now(), null, now()  );

call next value for hibernate_sequence;
insert into account (id, name, account, user_id, state, reg_date, updated_date, use_date) values (3, '한국은행', '0834er2asdf593',1, 'ACTIVE', now(), null, now()  );


-- 지역(부동산) 정보
call next value for hibernate_sequence;
insert into prop (id, city, state, zip_code, address, address_detail) values (1, '서울', '서초구', '06683', '효령로 84', '효령 아파트 123');

call next value for hibernate_sequence;
insert into locate_pos (id, prop_id, x_pos, y_pos, z_pos) values (1, 1, 123.23, 123.56, 123.67);

call next value for hibernate_sequence;
insert into locate_pos (id, prop_id, x_pos, y_pos, z_pos) values (2, 1, 456.23, 456.56, 456.67);

call next value for hibernate_sequence;
insert into locate_pos (id, prop_id, x_pos, y_pos, z_pos) values (3, 1, 789.23, 789.56, 789.67);

-- 모임 정보
call next value for hibernate_sequence;
insert into associate(`id`, `name` , `state`, `total_amount`, `target_amount`, `payment_expect_count`, `payment_real_count` ,prop_id ,`reg_date`, `end_expect_date`, `end_real_date`) values (1, '구의조합', 'ACTIVE' ,1000000, 0, 10000, 10000, 1, now(), now(), null );


-- 유저/모임 정보
call next value for hibernate_sequence;
insert into associate_user (`id`, `user_id`, `associate_id`, `level` ,`reg_Date`, `update_Date`) values (1, 1, 1, 'A_MANAGER' , now(), now() );

call next value for hibernate_sequence;
insert into associate_user (`id`, `user_id`, `associate_id`, `level` ,`reg_Date`, `update_Date`) values (2, 2, 1, 'A_STAFF' , now(), now() );


