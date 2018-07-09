package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name="shift")
@Data
public class Shift {
    @Id
    private String shift_id ;
    private String line_name;   //enum('LoopLineClockwise', 'LoopLineAntiClockwise','MinToXu', 'XuToMin', 'MinToQi', 'QiToMin')
    private String line_name_cn;
    private String line_type;
    private Time departure_time ;
    private int reserve_seat;   //check (reserve_seat > 0),
    private String comment;

    public String getShift_id() {
        return shift_id;
    }

    public void setShift_id(String shift_id) {
        this.shift_id = shift_id;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public String getLine_name_cn() {
        return line_name_cn;
    }

    public void setLine_name_cn(String line_name_cn) {
        this.line_name_cn = line_name_cn;
    }

    public String getLine_type() {
        return line_type;
    }

    public void setLine_type(String line_type) {
        this.line_type = line_type;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public int getReserve_seat() {
        return reserve_seat;
    }

    public void setReserve_seat(int reserve_seat) {
        this.reserve_seat = reserve_seat;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
