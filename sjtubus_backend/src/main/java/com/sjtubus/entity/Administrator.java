package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Administrator")
@Data
public class Administrator {
    @Id
    @Column(name = "id")
    int administratorId ;
    @Column(name="username")
    String username;
    @Column(name="password")
    String password;
}
