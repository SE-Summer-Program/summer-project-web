package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Bus")
@Data
public class Bus {
    @Id
    @Column(name = "bus_id")
    int busId ;
    @Column(name = "driver_id")
    int driverId ;
    @Column(name = "shift_id")
    int shiftId ;
    @Column(name = "seat_num")
    int seatNum ;
    @Column(name = "plate_num")
    String plate_num ;

}
