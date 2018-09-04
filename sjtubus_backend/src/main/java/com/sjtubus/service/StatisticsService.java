package com.sjtubus.service;

import com.sjtubus.dao.AppointmentDao;
import com.sjtubus.entity.Appointment;
import com.sjtubus.utils.StringCalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.sql.Time;
import java.time.Month;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StatisticsService {
    @Autowired
    AppointmentDao appointmentDao;

    @Autowired
    ShiftService shiftService;


    public List<Integer> dealAppointmentData(Date startDate, Date endDate, String lineNameCn, String lineType, Time time){
        List<Integer> result = new ArrayList<>();
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
        shiftid = shiftid + timeString;
        System.out.println("shiftid:"+shiftid);
        //List<Appointment> appointmentList = appointmentDao.queryAppointmentByShiftIdAndPeriod(shiftid, StringCalendarUtils.UtilDateToSqlDate(startDate), StringCalendarUtils.UtilDateToSqlDate(endDate));
        //System.out.println("list:"+appointmentList.size());
        List<Object[]> countList = appointmentDao.queryAppointmentGroupByDate(shiftid, StringCalendarUtils.UtilDateToSqlDate(startDate), StringCalendarUtils.UtilDateToSqlDate(endDate));
        System.out.println("list:"+countList.size());
        System.out.println("list:"+countList);
        Map<String,String> map = new HashMap<String,String>();
        for (Object[] objects : countList){
            System.out.println("date:"+objects[0]);
            System.out.println("count:"+objects[1]);
            map.put(objects[0].toString(),objects[1].toString());
        }
        List<Date> dateList = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(startDate);
        while(startDate.getTime()<=endDate.getTime()){
            dateList.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            startDate = tempStart.getTime();
        }

        System.out.println(dateList.size());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Date date: dateList){

            String dateString = formatter.format(date);
            //System.out.println(dateString);
            if(map.containsKey(dateString)){
                result.add(Integer.parseInt(map.get(dateString)));
            }
            else{
                result.add(0);
            }
        }
        System.out.println(result);
        return result;
    }
}
