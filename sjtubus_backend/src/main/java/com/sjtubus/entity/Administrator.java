package com.sjtubus.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Administrator")
@Data
public class Administrator {
    @Id
    int administratorId ;
    String username;
    String password;

    @Id
    @Column(name = "administrator_id")
    public int getAdministratorId() { return administratorId; }

    public void setAdministratorId(int administratorId) { this.administratorId = administratorId; }
}
