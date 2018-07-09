package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="Shift")
@Data
public class Shift {
    @Id
    String shift_id ;
    String line_name;            //enum('LoopLineClockwise', 'LoopLineAntiClockwise',
                         // 'MinToXu', 'XuToMin', 'MinToQi', 'QiToMin'),
    String line_name_cn;
    String line_type;
    Time departure_time ;
    int reserve_seat;       //check (reserve_seat > 0),
    String comment;

    @Id
    @Column(name = "shift_id")
    public String getShift_id() { return shift_id; }

    public void setShift_id(String shift_id) { this.shift_id = shift_id; }

    @Basic
    @Column(name = "line_name")
    public String getLine_name() { return line_name; }

    public void setLine_name(String line_name) { this.line_name = line_name; }

    @Basic
    @Column(name = "line_name_cn")
    public String getLine_name_cn() { return line_name_cn; }

    public void setLine_name_cn(String line_name_cn) { this.line_name_cn = line_name_cn; }

    @Basic
    @Column(name = "line_type")
    public String getLine_type() { return line_type; }

    public void setLine_type(String line_type) { this.line_type = line_type; }

    @Basic
    @Column(name = "departure_time")
    public Time getDeparture_time() { return departure_time; }

    public void setDeparture_time(Time departure_time) { this.departure_time = departure_time ; }

    @Basic
    @Column(name = "reserve_seat")
    public int getReserve_seat() { return reserve_seat; }

    public void setReserve_seat(int reserve_seat) { this.reserve_seat = reserve_seat; }

    @Basic
    @Column(name = "commnet")
    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }
}
