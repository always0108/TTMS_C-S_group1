/*==============================================================*/
/* Create Database                                         	*/
/*==============================================================*/
drop DATABASE IF EXISTS TTMS;

CREATE DATABASE TTMS;

use TTMS;

/*==============================================================*/
/* Table: data_dict                                             */
/*==============================================================*/
create table data_dict
(
   dict_id              int not null auto_increment,
   dict_parent_id       int,
   dict_index           int,
   dict_name            varchar(200),
   dict_value           varchar(100) not null,
   primary key (dict_id)
)ENGINE=InnoDB;

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   emp_id               int not null auto_increment,
   emp_no               char(20) not null,
   emp_type             smallint not null,
   emp_name             varchar(100) binary unique not null,
   emp_passwd           char(40) not null,
   emp_tel_num          char(20),
   emp_addr             varchar(200),
   emp_email            varchar(100),
   primary key (emp_id)
)ENGINE=InnoDB;

/*==============================================================*/
/* Table: play                                                  */
/*==============================================================*/
create table play
(

   play_id              int not null auto_increment,
   play_type_id         int,
   play_lang_id         int,
   play_name            varchar(200) binary unique,
   play_introduction    varchar(2000),
   play_image           longblob,
   play_length          int,
   play_ticket_price    numeric(10,2),
   play_status          smallint comment '取值含义：
				0：待安排演出
				1：已安排演出
				-1：已下线',
   primary key (play_id)
)ENGINE=InnoDB;

/*==============================================================*/
/* Table: sale                                                  */
/*==============================================================*/
create table sale
(

   sale_ID              bigint not null auto_increment,
   emp_id               int,
   sale_time            datetime,
   sale_payment         decimal(10,2),
   sale_change          numeric(10,2),
   sale_type            smallint comment '取值含义：
				-1：退款单
				1：销售单',
   sale_status          smallint comment '取值含义：
				-1:取消订单
				0：待付款
				1：已付款',
   primary key (sale_ID)
)ENGINE=InnoDB;

/*==============================================================*/
/* Table: sale_item                                             */
/*==============================================================*/
create table sale_item
(
   sale_item_id         bigint not null auto_increment,
   ticket_id            bigint,
   sale_ID              bigint,
   sale_item_price      numeric(10,2),
   primary key (sale_item_id)
)ENGINE=InnoDB;

/*==============================================================*/
/* Table: schedule                                              */
/*==============================================================*/
create table schedule
(
   sched_id             int not null auto_increment,
   studio_id            int,
   play_id              int,
   sched_time           datetime not null,
   sched_ticket_price   numeric(10,2),
   primary key (sched_id)
)ENGINE=InnoDB;

/*==============================================================*/
/* Table: seat                                                  */
/*==============================================================*/
create table seat
(
   seat_id              int not null auto_increment,
   studio_id            int,
   seat_row             int,
   seat_column          int,
   seat_status          smallint comment '取值含义：
				0：空位，没有安排座位
				1：完好的座位，可以使用
				-1：座位损坏，不能使用',    
   primary key (seat_id)
)ENGINE=InnoDB;

/*==============================================================*/
/* Table: studio                                                */
/*==============================================================*/
Create table studio
(
    studio_id int NOT NULL AUTO_INCREMENT,
    studio_name VARCHAR(100) binary unique NOT NULL,
    studio_row_count     int,
    studio_col_count     int,
    studio_seat_count    int,
    studio_introduction  VARCHAR(2000),
    studio_flag          smallint comment '取值含义：
				0：没有初始化，可根据行列信息自动生成
				1：已经初始化过作为，不能再安排座位
				-1：影厅损坏或废弃，无法使用', 
    PRIMARY KEY(studio_id)
)ENGINE=InnoDB;

/*==============================================================*/
/* Table: ticket                                                */
/*==============================================================*/
create table ticket
(
   ticket_id            bigint not null auto_increment,
   seat_id              int,
   sched_id             int,
   ticket_price         numeric(10,2),
   ticket_status        smallint comment '取值含义：
				0：可售卖
				1：锁定
				-1：卖出', 
   ticket_locked_time   datetime,
   primary key (ticket_id)
)ENGINE=InnoDB;


create table mylog (
  log_id  int PRIMARY KEY AUTO_INCREMENT,
  log_time  varchar(100),
  log_content varchar(300)
)ENGINE=InnoDB;


alter table data_dict add constraint FK_super_child_dict foreign key (dict_parent_id)
      references data_dict (dict_id) on delete restrict on update restrict;

alter table play add constraint FK_dict_lan_play foreign key (play_lang_id)
      references data_dict (dict_id) on delete restrict on update restrict;

alter table play add constraint FK_dict_type_play foreign key (play_type_id)
      references data_dict (dict_id) on delete restrict on update restrict;

alter table sale add constraint FK_employee_sale foreign key (emp_id)
      references employee (emp_id) on delete restrict on update restrict;

alter table sale_item add constraint FK_sale_sale_item foreign key (sale_ID)
      references sale (sale_ID) on delete restrict on update restrict;

alter table sale_item add constraint FK_ticket_sale_item foreign key (ticket_id)
      references ticket (ticket_id) on delete restrict on update restrict;

alter table schedule add constraint FK_play_sched foreign key (play_id)
      references play (play_id) on delete restrict on update restrict;

alter table schedule add constraint FK_studio_sched foreign key (studio_id)
      references studio (studio_id) on delete restrict on update restrict;

alter table seat add constraint FK_studio_seat foreign key (studio_id)
      references studio (studio_id) on delete restrict on update restrict;

alter table ticket add constraint FK_sched_ticket foreign key (sched_id)
      references schedule (sched_id) on delete restrict on update restrict;

alter table ticket add constraint FK_seat_ticket foreign key (seat_id)
      references seat (seat_id) on delete restrict on update restrict;
