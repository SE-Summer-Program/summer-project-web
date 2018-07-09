package com.sjtubus.dao;

import com.sjtubus.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorDao extends JpaRepository<Administrator,String> {
    //public Administrator findByA_username(String username);
    public Administrator findByAdminId(int id);
}
