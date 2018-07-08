package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="RideBusInfoService")
@Data
public class RideBusInfo {
    @Id
    String ride_id ;
    String shift_id ;
    String bus_id;
    boolean isHoilday ;  //enum('true', 'false'),
    boolean isWeekday;   //enum('true', 'shiftfalse'),
    int student_num ;  //check (student_num >= 0),
    int teacher_num;     //check (teacher_num >= 0),
    int appoint_num;     //check (appoint_num >= 0),
    int appoint_break ;  //check (appoint_break >= 0),
    int reserve_seat;
    Date ride_date;
}
