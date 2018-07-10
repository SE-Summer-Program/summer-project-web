package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    int appoinment_id ;
    String user_id ;
    String shift_id ;
    Date appoint_date ;
    String line_name ;
    String isnormal ;
    

	/*public User() {}
	public User(String name, String password, String phone, boolean isteacher) {
		this.username = name;
		this.password = password;
		this.phone = phone;
		this.credit = 100;
		this.isteacher = isteacher;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer userId ;
    @Column(name = "username")
    String username ;
    @Column(name = "password")
    String password ;
    @Column(name = "phone")
    String phone;
    @Column(name = "credit")
    int credit ;
    @Column(name = "isteacher")
    boolean isteacher;   //enum('true', 'false'),

    public Integer getId() {
    	return this.userId;
    }
    public void setId(Integer id){
    	this.userId = id;
    }
    public String getName() {
    	return this.username;
    }
    public void setName(String name) {
    	this.username = name;
    }
    public String getPassword() {
    	return this.password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    public String getPhone() {
    	return this.phone;
    }
    public void setPhone(String phone) {
    	this.phone = phone;
    }
    public Integer getCredit() {
    	return this.credit;
    }
    public void setCredit(Integer credit) {
    	this.credit = credit;
    }
    public boolean getIsteacher() {
    	return this.isteacher;
    }
    public void setIsteacher(boolean isteacher) {
    	this.isteacher = isteacher;
    }*/
}
