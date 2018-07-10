package com.sjtubus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	public User() {}
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
    private Integer userId ;
    @Column(name = "username")
    private String username ;
    @Column(name = "password")
    private String password ;
    @Column(name = "phone")
    private String phone;
    @Column(name = "credit")
    private int credit ;
    @Column(name = "isteacher")
    private boolean isteacher; 

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
    }
}
