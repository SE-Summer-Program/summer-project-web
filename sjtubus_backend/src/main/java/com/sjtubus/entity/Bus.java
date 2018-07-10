package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Bus")
@Data
public class Bus {
    @Id
    int busId ;
    int driverId ;
    int shiftId ;
    int seatNum ;
    String plate_num ;

}
