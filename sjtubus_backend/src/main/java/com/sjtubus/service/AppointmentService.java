package com.sjtubus.service;

import com.sjtubus.dao.AppointmentDao;
import com.sjtubus.entity.Appointment;
import com.sjtubus.entity.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentDao appointmentDao;
    @Autowired
    private ShiftService shiftService;

    public List<Appointment> searchAppointment(String lineNameCn, String lineType, Time departureTime, Date appointDate){
        String time = shiftService.changeTimeToStringTime(departureTime);
        String shiftid;
        if (lineNameCn.equals("闵行到七宝"))
            shiftid = "MQ";
        else if (lineNameCn.equals("闵行到徐汇"))
            shiftid = "MX";
        else if (lineNameCn.equals("七宝到闵行"))
            shiftid = "QM";
        else
            shiftid = "XM";
        shiftid = shiftid + shiftService.changeTypeToId(lineType);
        shiftid = shiftid + time;
        System.out.println("shiftid:"+shiftid);
        List<Appointment> appointmentList = appointmentDao.queryAppointmentByShiftIdAndAppointDate(shiftid, appointDate);
        return appointmentList;
    }
}
