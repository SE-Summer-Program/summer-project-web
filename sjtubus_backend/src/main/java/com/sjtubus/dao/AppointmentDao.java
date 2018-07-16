package com.sjtubus.dao;

import com.sjtubus.entity.Appointment;
import com.sjtubus.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentDao extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByShiftIdAndAppointDate(String shiftId, Date appointDate);

}
