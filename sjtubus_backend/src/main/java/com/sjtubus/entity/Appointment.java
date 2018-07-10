package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Appointment")
@Data
public class Appointment {
    @Id
    @Column(name = "appoinment_id")
    int appointmentId ;
    @Column(name = "user_id")
    int userId ;
    @Column(name = "shift_id")
    int shiftId ;
    @Column(name = "appoint_date")
    Date appointDate ;
    @Column(name = "line_name")
    String lineName ;
    @Column(name = "isnormal")
    boolean isNormal ;
}
