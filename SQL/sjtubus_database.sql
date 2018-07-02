/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/2 15:34:17                            */
/*==============================================================*/


drop table if exists Appointment;

drop table if exists Bus;

drop table if exists Driver;

drop table if exists RideBusInfo;

drop table if exists Shift;

drop table if exists User;

/*==============================================================*/
/* Table: Appointment                                           */
/*==============================================================*/
create table Appointment
(
   appoinment_id        int not null,
   user_id              varchar(10),
   shift_id             varchar(10),
   appoint_date         date,
   line_name            enum('LoopLineClockwise', 'LoopLineAntiClockwise',
							'MinToXu', 'XuToMin', 'MinToQi', 'QiToMin'),
   isnormal             enum('true', 'false'),
   primary key (appoinment_id)
);

/*==============================================================*/
/* Table: Bus                                                   */
/*==============================================================*/
create table Bus
(
   bus_id               varchar(10) not null,
   driver_id            varchar(10),
   shift_id             varchar(10),
   seat_num             int check (seat_num >= 0),
   plate_num            varchar(50),
   primary key (bus_id)
);

/*==============================================================*/
/* Table: Driver                                                */
/*==============================================================*/
create table Driver
(
   driver_id            varchar(10) not null,
   username                varchar(50),
   password           varchar(50),
   primary key (driver_id)
);

/*==============================================================*/
/* Table: RideBusInfo                                           */
/*==============================================================*/
create table RideBusInfo
(
   ride_id              varchar(10) not null,
   shift_id             varchar(10),
   ishoilday            enum('true', 'false'),
   isweekday            enum('true', 'shiftfalse'),
   student_num          int check (student_num >= 0),
   teacher_num          int check (teacher_num >= 0),
   appoint_num          int check (appoint_num >= 0),
   appoint_break        int check (appoint_break >= 0),
   ride_date            date,
   primary key (ride_id)
);

/*==============================================================*/
/* Table: Shift                                                 */
/*==============================================================*/
create table Shift
(
   shift_id             varchar(10) not null,
   departure_time 		datetime,
   reseverd_seat        int check (reserve_seat > 0),
   line_name            enum('LoopLineClockwise', 'LoopLineAntiClockwise',
							'MinToXu', 'XuToMin', 'MinToQi', 'QiToMin'),
   comment     			varchar(1000),
   primary key (shift_id)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   user_id              varchar(10) not null,
   usrename             varchar(50),
   password             varchar(50),
   credit               int,
   isteacher            enum('true', 'false'),
   primary key (user_id)
);

alter table Appointment add constraint FK_Relationship_10 foreign key (user_id)
      references User (user_id) on delete restrict on update restrict;

alter table Appointment add constraint FK_Relationship_11 foreign key (shift_id)
      references Shift (shift_id) on delete restrict on update restrict;

alter table Bus add constraint FK_Relationship_5 foreign key (driver_id)
      references Driver (driver_id) on delete restrict on update restrict;

alter table Bus add constraint FK_Relationship_9 foreign key (shift_id)
      references Shift (shift_id) on delete restrict on update restrict;

alter table RideBusInfo add constraint FK_Relationship_7 foreign key (shift_id)
      references Shift (shift_id) on delete restrict on update restrict;

