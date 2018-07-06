package com.sjtubus.service;

import com.sjtubus.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentDao appointmentDao;
}
