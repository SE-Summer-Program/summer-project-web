package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
@Data
public class User {
    @Id
    String user_id ;
    String username ;
    String password ;
    int credit ;
    boolean isTeacher;   //enum('true', 'false'),
}
