package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
}
