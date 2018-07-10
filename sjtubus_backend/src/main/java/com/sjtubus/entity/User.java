package com.sjtubus.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Table(name="Users")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    int userId ;
    @Column(name="username")
    String username ;
    @Column(name="password")
    String password ;
    @Column(name="credit")
    int credit ;
    @Column(name="isteacher")
    boolean isTeacher;   //enum('true', 'false')
    @Column(name="phone")
    String phone;

//    @Id
//    @Column(name = "user_id")
//    public int getUserId() { return userId; }
//
//    public void setUserId() { this.userId = userId; }
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
//    @Column(name = "credit")
//    public int getCredit() { return credit; }
//
//    public void setCredit(int credit) { this.credit = credit; }
//
//    @Basic
//    @Column(name = "isteacher")
//    public boolean getIsteacher() { return isTeacher; }
//
//    public void setIsteacher(boolean isTeacher) { this.isTeacher = isTeacher; }
//
//    @Basic
//    @Column(name = "phone")
//    public String getPhone() { return phone; }
//
//    public void setPhone(String phone) { this.phone = phone; }
}
