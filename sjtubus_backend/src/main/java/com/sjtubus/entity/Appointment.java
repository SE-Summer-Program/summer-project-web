package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @Column(name = "appoinment_id")
    private int appointmentId ;
    @Column(name = "user_id")
    private int userId ;
    @Column(name = "shift_id")
    private int shiftId ;
    @Column(name = "appoint_date")
    private Date appointDate ;
    @Column(name = "line_name")
    private String lineName ;
    @Column(name = "isnormal")
    private boolean isNormal ;

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

    public int getShiftId() {

        return shiftId;
    }

    public void setShiftId(int shiftId) {
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
