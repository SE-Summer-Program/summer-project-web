package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Appointment")
@Data
public class Appointment {
    @Id
    int appoinment_id ;
    String user_id ;
    String shift_id ;
    Date appoint_date ;
    String line_name ;
    String isnormal ;
}
