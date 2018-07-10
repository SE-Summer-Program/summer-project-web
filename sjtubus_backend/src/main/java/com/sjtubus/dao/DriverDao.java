package com.sjtubus.dao;

import com.sjtubus.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDao extends JpaRepository<Driver,Integer> {
}
