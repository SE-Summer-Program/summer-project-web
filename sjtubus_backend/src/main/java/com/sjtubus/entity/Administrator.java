package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Administrator")
@Data
public class Administrator {
    String id ;
    String a_username;
    String a_password;
}
