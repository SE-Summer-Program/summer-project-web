package com.sjtubus.service;

import com.sjtubus.dao.AppointmentDao;
import com.sjtubus.dao.BusDao;
import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Appointment;
import com.sjtubus.entity.Bus;
import com.sjtubus.entity.Shift;
import com.sjtubus.model.AppointInfo;
import com.sjtubus.utils.StringCalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AppointService {
    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private ShiftDao shiftDao;

    @Autowired
    private BusDao busDao;

    /**
     * @description: 符合当前线路和时间段类型的所有shift班次
     * @date: 2018/7/16 13:00
     * @params:
     * @return: List<AppointInfo>
     */
    public List<AppointInfo> getAppointInfo(String line_name, String type, String appoint_date){
        System.out.println("linename:"+line_name);
        System.out.println("type:"+type);
        System.out.println("appoint_date:"+appoint_date);
        List<Shift> shifts =  shiftDao.findByLineTypeAndLineNameOrderByDepartureTime(type, line_name);
        List<AppointInfo> appointInfos = new ArrayList<>();

        System.out.println("shifts:"+shifts.size());
        if(shifts == null || shifts.size()==0) {
            return null;
        }
        System.out.println("shifts:"+shifts.get(0).getShiftId());
        for (int i = 0; i < shifts.size(); i++){
            String departure_time = shifts.get(i).getDepartureTime().toString();
            //比较发车时间和当前时间的先后
            String departure = appoint_date + " " + departure_time;
            if (StringCalendarUtils.isBeforeCurrentTime(departure))
                continue;

            AppointInfo info = new AppointInfo();
            info.setShiftId(shifts.get(i).getShiftId());
            info.setDepartureTime(departure_time);
            info.setArriveTime(shifts.get(i).getArriveTime().toString());
            //获取当前班次剩余可预约座位数（减去预留座位和已经被预约的座位）
            info.setRemainSeat(getRemainSeat(shifts.get(i).getShiftId(), StringCalendarUtils.StringToDate(appoint_date)));

            appointInfos.add(info);
            System.out.println("shiftid:"+shifts.get(i).getShiftId());
        }
        System.out.println("appoint:"+appointInfos.size());
        return appointInfos;
    }

    /**
     * @description: 获取当前班次剩余可预约座位数（减去预留座位和已经被预约的座位）
     * @date: 2018/07/16 13:00
     * @params:
     * @return:
     */
    public int getRemainSeat(String shiftId, Date appoint_date){
       List<Appointment> appointments = appointmentDao.findByShiftIdAndAppointDate(shiftId, appoint_date);
       Shift shift = shiftDao.findByShiftId(shiftId);
       Bus bus = busDao.findByBusId(shift.getBusId());
       int totalSeat = bus.getSeatNum();
       int reserveSeat = shift.getReserveSeat();
       int appointedSeat = appointments.size();
       return totalSeat - reserveSeat - appointedSeat;
    }

    /**
     * @description: 比较发车时间和当前时间的先后
     * @date: 2018/07/16 13:57
     * @params:
     * @return:
     */
//    private boolean isTodayDeparturedShift(String appoint_date, String departure_time) {
//
//        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date departure = new Date();
//        Date current = new Date();
//        String time = appoint_date + " " + departure_time;
//
//        try {
//            departure = timeFormat.parse(time);//发车时间 考虑了日期和时间
//            current = timeFormat.parse(timeFormat.format(new Date()));//当前时间
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        if (departure.before(current))
//            return true;
//        else
//            return false;
//
//    }
}
