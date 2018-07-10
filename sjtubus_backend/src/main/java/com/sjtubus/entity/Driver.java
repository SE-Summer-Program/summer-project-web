package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="driver")
@Data
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

//    @Id
//    @Column(name = "id")
//    public String getId() { return id; }
//
//    public void setId(String id) { this.id = id; }
//
//    @Basic
//    @Column(name = "username")
//    public String getUsername() { return username; }
//
//    public void setUsername(String username) { this.username = username; }
//
//    @Basic
//    @Column(name = "password")
//    public String getPassword() { return password; }
//
//    public void setPassword(String password) { this.password = password; }
//
//    @Basic
//    @Column(name = "phone")
//    public String getPhone() { return phone; }
//
//    public void setPhone(String phone) { this.phone = phone; }
}
