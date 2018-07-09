package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus")
@Data
public class Bus {
    @Id
    String bus_id ;
    String driver_id ;
    String shift_id ;
    int seat_num ;
    String plate_num ;
}
