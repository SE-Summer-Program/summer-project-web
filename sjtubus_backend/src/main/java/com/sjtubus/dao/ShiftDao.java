package com.sjtubus.dao;

import com.sjtubus.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftDao extends JpaRepository<Shift,String> {
}
