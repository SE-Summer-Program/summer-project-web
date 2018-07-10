package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {
    @Id
    int appoinmentId ;
    int userId ;
    int shiftId ;
    Date appointDate ;
    String lineName ;
    boolean isNormal ;
}
