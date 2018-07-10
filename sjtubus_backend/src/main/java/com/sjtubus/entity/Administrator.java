package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
@Data
public class Administrator {
    @Id
    int administratorId ;
    String username;
    String password;
}
