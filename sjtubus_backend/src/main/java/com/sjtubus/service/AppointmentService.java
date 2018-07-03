package com.sjtubus.service;

import com.sjtubus.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;

public class AppointmentService {
    @Autowired
    private AppointmentDao appointmentDao;
}
