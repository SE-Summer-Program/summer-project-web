package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Appointment")
@Data
public class Appointment {
    @Id
    int appoinmentId ;
    int userId ;
    int shiftId ;
    Date appointDate ;
    String lineName ;
    boolean isNormal ;

    @Id
    @Column(name = "appointment_id")
    public int getAppoinmentId() { return appoinmentId; }

    public void setAppoinmentId(int appoinmentId) { this.appoinmentId = appoinmentId; }
}
