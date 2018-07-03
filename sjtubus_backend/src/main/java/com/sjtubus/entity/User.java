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
    String usrename ;
    String password ;
    int credit ;
    String isteacher;   //enum('true', 'false'),
}
