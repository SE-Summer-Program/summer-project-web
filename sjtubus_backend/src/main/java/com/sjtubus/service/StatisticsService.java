package com.sjtubus.service;

import com.sjtubus.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Month;
import java.util.List;

@Service
public class StatisticsService {
    @Autowired
    AppointmentDao appointmentDao;

    @Autowired
    ShiftService shiftService;

    public List<Integer> dealMonthData(Month month, String lineNameCn, String lineType, Time time){
        String timeString = shiftService.changeTimeToStringTime(time);
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
        return null;
    }
}
