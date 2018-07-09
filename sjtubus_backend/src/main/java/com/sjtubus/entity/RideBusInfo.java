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
    String ishoilday ;  //enum('true', 'false'),
    String isweekday;   //enum('true', 'shiftfalse'),
    int student_num ;  //check (student_num >= 0),
    int teacher_num;     //check (teacher_num >= 0),
    int appoint_num;     //check (appoint_num >= 0),
    int appoint_break ;  //check (appoint_break >= 0),
    Date ride_date;
}
