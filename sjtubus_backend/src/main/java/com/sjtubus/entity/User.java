package com.sjtubus.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Table(name="Users")
@Data
public class User {
    @Id
    String user_id ;
    String username ;
    String password ;
    int credit ;
    boolean isTeacher;   //enum('true', 'false'),

    @Id
    //@GeneratedValue(generator = "increment")
    @Column(name = "user_id")
    public String getUser_id() { return user_id; }

    public void setUser_id(String shift_id) { this.user_id = user_id; }

    @Basic
    @Column(name = "username")
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    @Basic
    @Column(name = "password")
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Basic
    @Column(name = "credit")
    public int getCredit() { return credit; }

    public void setCredit(int credit) { this.credit = credit; }

    @Basic
    @Column(name = "isteacher")
    public boolean getIsteacher() { return isTeacher; }

    public void setIsteacher(boolean isTeacher) { this.isTeacher = isTeacher; }
}
