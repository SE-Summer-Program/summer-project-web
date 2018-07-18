package com.sjtubus.controller;

import com.sjtubus.entity.Appointment;
import com.sjtubus.model.response.AppointmentResponse;
import com.sjtubus.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @RequestMapping(path = "/search")
    public AppointmentResponse searchAppointment(@RequestParam("lineNameCn") String lineNameCn,
                                                 @RequestParam("lineType") String lineType,
                                                 @RequestParam("departureTime") Time departureTime,
                                                 @RequestParam("appointDate") String appointDate) {
        AppointmentResponse response = new AppointmentResponse();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
            Date date =  formatter.parse(appointDate);
            System.out.println("date:"+date);
            List<Appointment> appointmentList = appointmentService.searchAppointment(lineNameCn, lineType, departureTime, date);
            response.setAppointmentList(appointmentList);
            response.setError(0);
        }
        catch(Exception e){
            response.setError(1);
        }
        return response;
    }
}
