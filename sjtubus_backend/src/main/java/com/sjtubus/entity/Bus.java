package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Bus")
public class Bus {

    @Id
    @Column(name = "bus_id")
    private int busId ;
    @Column(name = "driver_id")
    private int driverId ;
    @Column(name = "shift_id")
    private int shiftId ;
    @Column(name = "seat_num")
    private int seatNum ;
    @Column(name = "plate_num")
    private String plate_num ;

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public String getPlate_num() {
        return plate_num;
    }

    public void setPlate_num(String plate_num) {
        this.plate_num = plate_num;
    }
}
