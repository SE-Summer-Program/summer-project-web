package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "appointment")

public class Appointment {
    @Id
    @Column(name = "appoinment_id")
    int appointmentId ;
    @Column(name = "user_id")
    int userId ;
    @Column(name = "shift_id")
    String shiftId ;
    @Column(name = "appoint_date")
    Date appointDate ;
    @Column(name = "line_name")
    String lineName ;
    @Column(name = "isnormal")
    boolean isNormal ;

    public boolean isNormal() {
        return isNormal;
    }

    public void setNormal(boolean normal) {
        isNormal = normal;
    }

    public String getLineName() {

        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Date getAppointDate() {

        return appointDate;
    }

    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }

    public String getShiftId() {

        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAppointmentId() {

        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
}
