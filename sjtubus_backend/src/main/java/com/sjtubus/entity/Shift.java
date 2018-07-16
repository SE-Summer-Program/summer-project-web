package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="Shift")

public class Shift {
    @Id
    @Column(name = "shift_id")
    String shiftId ;

    @Column(name = "bus_id")
    int bus_id;

    @Column(name = "line_name")
    String lineName;     //enum('LoopLineClockwise', 'LoopLineAntiClockwise',
                            // 'MinToXu', 'XuToMin', 'MinToQi', 'QiToMin'),
    @Column(name = "line_name_cn")
    String lineNameCn;

    @Column(name = "line_type")
    String lineType;

    @Column(name = "departure_time")
    Time departureTime ;

    @Column(name = "arrive_time")
    Time arriveTime;

    @Column(name = "reserve_seat")
    int reserveSeat;       //check (reserve_seat > 0),

    @Column(name = "comment")
    String comment;

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineNameCn() {
        return lineNameCn;
    }

    public void setLineNameCn(String lineNameCn) {
        this.lineNameCn = lineNameCn;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getReserveSeat() {

        return reserveSeat;
    }

    public void setReserveSeat(int reserveSeat) {
        this.reserveSeat = reserveSeat;
    }

    public Time getDepartureTime() {

        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }


    public int getBus_id() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }


    public Time getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
    }
}
