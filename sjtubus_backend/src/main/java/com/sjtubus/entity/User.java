package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    String user_id ;
    String username ;
    String password ;
    int credit ;
    boolean isTeacher;   //enum('true', 'false'),

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
