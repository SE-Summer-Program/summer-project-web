package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="driver")
@Data
public class Driver {
    @Id
    int driverId ;
    String username ;
    String password ;
    String phone;

    @Id
    @Column(name = "driver_id")
    public int getDriverId() { return driverId; }

    public void setDriverId(int driverId) { this.driverId = driverId; }

    @Basic
    @Column(name = "username")
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    @Basic
    @Column(name = "password")
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Basic
    @Column(name = "phone")
    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }
}
