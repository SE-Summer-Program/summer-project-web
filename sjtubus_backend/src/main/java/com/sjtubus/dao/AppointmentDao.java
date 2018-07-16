package com.sjtubus.dao;

import com.sjtubus.entity.Appointment;
import com.sjtubus.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDao extends JpaRepository<Appointment,Integer> {

    Appointment findByName(String name);

}
