package com.sjtubus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="Ridebusinfo")
public class RideBusInfo {

    @Id
    @Column(name = "ride_id")
    private int rideId ;
    @Column(name = "shift_id")
    private String shiftId ;
    @Column(name = "bus_id")
    private int busId;
    @Column(name = "ride_date")
    private Date rideDate;
    @Column(name = "ishoilday")
    private boolean isHoilday ;  //enum('true', 'false')
    @Column(name = "isweekday")
    private boolean isWeekday;   //enum('true', 'shiftfalse')
    @Column(name = "student_num")
    private int studentNum ;  //check (student_num >= 0)
    @Column(name = "teacher_num")
    private int teacherNum;     //check (teacher_num >= 0)
    @Column(name = "appoint_num")
    private int appointNum;     //check (appoint_num >= 0)
    @Column(name = "appoint_break")
    private int appointBreak ;  //check (appoint_break >= 0)
    @Column(name = "reserve_seat")
    private int reserveSeat;
    @Column(name = "seat_num")
    private int seatNum ;

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public Date getRideDate() {
        return rideDate;
    }

    public void setRideDate(Date rideDate) {
        this.rideDate = rideDate;
    }

    public boolean isHoilday() {
        return isHoilday;
    }

    public void setHoilday(boolean hoilday) {
        isHoilday = hoilday;
    }

    public boolean isWeekday() {
        return isWeekday;
    }

    public void setWeekday(boolean weekday) {
        isWeekday = weekday;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(int teacherNum) {
        this.teacherNum = teacherNum;
    }

    public int getAppointNum() {
        return appointNum;
    }

    public void setAppointNum(int appointNum) {
        this.appointNum = appointNum;
    }

    public int getAppointBreak() {
        return appointBreak;
    }

    public void setAppointBreak(int appointBreak) {
        this.appointBreak = appointBreak;
    }

    public int getReserveSeat() {
        return reserveSeat;
    }

    public void setReserveSeat(int reserveSeat) {
        this.reserveSeat = reserveSeat;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
}
