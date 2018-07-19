package com.sjtubus.dao;

import com.sjtubus.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import java.util.Date;
import java.util.List;

public interface AppointmentDao extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByShiftIdAndAppointDate(String shiftId, Date appointDate);

    List<Appointment> findByUserName(String username);

    @Query(value = "select appointment from Appointment appointment where appointment.shiftId like %:shiftId% and appointment.appointDate=:appointDate")
    List<Appointment> queryAppointmentByShiftIdAndAppointDate(@Param("shiftId") String shiftId, @Param("appointDate") Date appointDate);
}
