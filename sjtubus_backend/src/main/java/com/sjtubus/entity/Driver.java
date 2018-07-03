package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="driver")
@Data
public class Driver {
    @Id
    String driver_id ;
    String username ;
    String password ;
}
