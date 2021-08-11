use mysql;

CREATE DATABASE PROPTECH default CHARACTER SET utf8mb4;

create user 'GUNS'@localhost identified by 'GUNS';
create user 'GUNS'@'%' identified by 'GUNS';

grant all privileges on PROPTECH.* to 'GUNS'@localhost identified by 'GUNS' ;
grant all privileges on PROPTECH.* to 'GUNS'@'%' identified by 'GUNS' ;

SHOW GRANTS FOR 'GUNS'@'%';
SHOW GRANTS FOR 'GUNS'@localhost;

flush privileges;