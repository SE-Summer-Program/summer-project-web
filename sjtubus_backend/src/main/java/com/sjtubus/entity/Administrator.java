package com.sjtubus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Administrator")
public class Administrator {
	
	public Administrator() {}
	public Administrator(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(name = "username")
    private String name;
    @Column(name = "password")
    private String password;
    
    public Integer getId() {
    	return this.id;
    }
    public void setId(Integer id){
    	this.id = id;
    }
    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public String getPassword() {
    	return this.password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
}