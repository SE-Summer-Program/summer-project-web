package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="ride_bus_info_service")
@Data
public class RideBusInfo {
    @Id
    int rideId ;
    int shiftId ;
    int busId;
    Date rideDate;
    boolean isHoilday ;  //enum('true', 'false'),
    boolean isWeekday;   //enum('true', 'shiftfalse'),
    int studentNum ;  //check (student_num >= 0),
    int teacherNum;     //check (teacher_num >= 0),
    int appointNum;     //check (appoint_num >= 0),
    int appointBreak ;  //check (appoint_break >= 0),
    int reserveSeat;
}
