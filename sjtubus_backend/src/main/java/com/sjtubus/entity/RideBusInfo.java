package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="RideBusInfoService")
@Data
public class RideBusInfo {
    @Id
    @Column(name = "ride_id")
    int rideId ;
    @Column(name = "shift_id")
    int shiftId ;
    @Column(name = "bus_id")
    int busId;
    @Column(name = "ride_date")
    Date rideDate;
    @Column(name = "ishoilday")
    boolean isHoilday ;  //enum('true', 'false')
    @Column(name = "isweekday")
    boolean isWeekday;   //enum('true', 'shiftfalse')
    @Column(name = "student_num")
    int studentNum ;  //check (student_num >= 0)
    @Column(name = "teacher_num")
    int teacherNum;     //check (teacher_num >= 0)
    @Column(name = "appoint_num")
    int appointNum;     //check (appoint_num >= 0)
    @Column(name = "appoint_break")
    int appointBreak ;  //check (appoint_break >= 0)
    @Column(name = "reserve_seat")
    int reserveSeat;
}
