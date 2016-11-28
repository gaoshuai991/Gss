CREATE DATABASE restaurant charset='utf8';
USE restaurant;

drop table if exists combosales;
drop table if exists salesrecord;
drop table if exists combo;
drop table if exists food;
drop table if exists user;
drop table if exists admin;

create table admin
(
   ano                  varchar(10) primary key,
   name                 varchar(10) not null,
   password             varchar(10) not null
)engine=InnoDB;
insert into admin values('1408090236','gss','236');

create table user
(
   uno                  varchar(10) primary key,
   name                 varchar(10) not null,
   password             varchar(15) not null,
   regdate              datetime 
)engine=InnoDB;

create table food
(
   fid                  varchar(10) primary key,
   name                 varchar(20) not null,
   category             varchar(10),
   price                double,
   amount               int
)engine=InnoDB;

create table combo
(
   cid                  varchar(10) primary key,
   staple               int,
   vegetables           int,
   meat                 int,
   gruel                int,
   price                double
)engine=InnoDB;

create table salesrecord
(
   uno                  varchar(10) ,
   fid                  varchar(10),
   amount               int,
   total                double,
   saledate             datetime,
   constraint fk_funo foreign key(uno) references user(uno),
   constraint fk_ffid foreign key(fid) references food(fid)
)engine=InnoDB;


create table combosales
(
   csid					int primary key auto_increment,
   cid                  varchar(10),
   uno                  varchar(10),
   amount               int,
   total				double,
   saledate             datetime,
   constraint fk_cuno foreign key(uno) references user(uno),
   constraint fk_cfid foreign key(cid) references combo(cid) on delete cascade
)engine=InnoDB;
