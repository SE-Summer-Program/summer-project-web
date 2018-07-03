package com.sjtubus.dao;

import com.sjtubus.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDao extends JpaRepository<Appointment,Integer> {
}
