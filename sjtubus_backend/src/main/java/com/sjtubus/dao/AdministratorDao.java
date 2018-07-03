package com.sjtubus.dao;

import com.sjtubus.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorDao extends JpaRepository<Administrator,String> {
}
