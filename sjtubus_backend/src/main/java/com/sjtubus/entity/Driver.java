package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="driver")

public class Driver {
    @Id
    @Column(name = "driver_id")
    int driverId ;
    @Column(name = "username")
    String username ;
    @Column(name = "password")
    String password ;
    @Column(name = "phone")
    String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDriverId() {

        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}
