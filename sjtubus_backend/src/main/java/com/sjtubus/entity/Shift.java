package com.sjtubus.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Table(name="Shift")
public class Shift {
    private String shiftId ;
    private String lineName;     //enum('LoopLineClockwise', 'LoopLineAntiClockwise',
                                  // 'MinToXu', 'XuToMin', 'MinToQi', 'QiToMin'),
    private String lineNameCn;
    private String lineType;
    private Time departureTime ;
    private int reserveSeat;       //check (reserve_seat > 0),
    private String comment;

    @Id
    @Column(name = "shift_id")
    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    @Basic
    @Column(name = "line_name")
    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Basic
    @Column(name = "line_name_cn")
    public String getLineNameCn() {
        return lineNameCn;
    }

    public void setLineNameCn(String lineNameCn) {
        this.lineNameCn = lineNameCn;
    }

    @Basic
    @Column(name = "line_type")
    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "reserve_seat")
    public int getReserveSeat() {

        return reserveSeat;
    }

    public void setReserveSeat(int reserveSeat) {
        this.reserveSeat = reserveSeat;
    }

    @Basic
    @Column(name = "departure_time")
    public Time getDepartureTime() {

        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }
}
