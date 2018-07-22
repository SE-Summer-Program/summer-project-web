package com.sjtubus.service;

import com.sjtubus.dao.AppointmentDao;
import com.sjtubus.dao.BusDao;
import com.sjtubus.dao.ShiftDao;
import com.sjtubus.dao.UserDao;
import com.sjtubus.entity.Appointment;
import com.sjtubus.entity.Bus;
import com.sjtubus.entity.Shift;
import com.sjtubus.entity.User;
import com.sjtubus.model.AppointInfo;
import com.sjtubus.utils.ShiftUtils;
import com.sjtubus.utils.StringCalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointService {
    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private ShiftDao shiftDao;

    @Autowired
    private BusDao busDao;

    @Autowired
    private UserDao userDao;
    
    /**
     * @description: 添加预约信息
     * @date: 2018/7/18 8:17
     * @params:  预约所需的用户名，预约日期，班次id，线路名
     * @return: 添加结果
    */
    public boolean addAppointment(String username,
                                  String appoint_date,
                                  String shift_id,
                                  String line_name,
                                  String submit_time){
        Appointment appointment = new Appointment();
        User user = userDao.findByUsername(username);
       // Date date = Date.valueOf(appoint_date);

        java.sql.Date date = StringCalendarUtils.UtilDateToSqlDate(StringCalendarUtils.StringToDate(appoint_date));

        appointment.setAppointDate(date);
        appointment.setLineName(line_name);
        appointment.setLineNameCn(ShiftUtils.getChiLineName(line_name));
        appointment.setNormal(false);
        appointment.setShiftId(shift_id);
        appointment.setUserName(username);
        appointment.setSubmitTimeString(submit_time);
        appointment.setUserId(user.getUserId());
        appointment.setRealName("");
        appointment.setUserCode("");

        System.out.println("remain: " + getRemainSeat(shift_id,date));
        System.out.println("shiftid: " + shift_id);
        if(getRemainSeat(shift_id,date) > 0){
            appointmentDao.save(appointment);
            return true;
        }else return false;
    }

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
            return new ArrayList<>();
        }
        System.out.println("shifts:"+shifts.get(0).getShiftId());
        for (Shift shift : shifts) {
            String departure_time = shift.getDepartureTime().toString();
            //比较发车时间和当前时间的先后
            String departure = appoint_date + " " + departure_time;
            if (StringCalendarUtils.isBeforeCurrentTime(departure))
                continue;

            AppointInfo info = new AppointInfo();
            info.setShiftId(shift.getShiftId());
            info.setDepartureTime(departure_time);
            info.setArriveTime(shift.getArriveTime().toString());
            //获取当前班次剩余可预约座位数（减去预留座位和已经被预约的座位）
            info.setRemainSeat(getRemainSeat(shift.getShiftId(), Date.valueOf(appoint_date)));

            appointInfos.add(info);
            System.out.println("shiftid:" + shift.getShiftId());
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
    private int getRemainSeat(String shiftId, Date appoint_date){
       List<Appointment> appointments = appointmentDao.findByShiftIdAndAppointDate(shiftId, appoint_date);
       Shift shift = shiftDao.findByShiftId(shiftId);
       Bus bus = busDao.findByBusId(shift.getBusId());
       int totalSeat = bus.getSeatNum();
       int reserveSeat = shift.getReserveSeat();
       int appointedSeat = appointments.size();
       return totalSeat - reserveSeat - appointedSeat;
    }

    /**
    * @description: 验证用户已上车
    * @date: 2018/07/16 13:00
    * @params:
    * @return:
    */
    public String verifyAppointment(String username,String departure_date,String shift_id){
        Appointment appointment = appointmentDao.findDistinctByShiftIdAndAppointDateAndUserName(shift_id,Date.valueOf(departure_date),username);
        if(appointment == null){
            return "您没有预约该班次~";
        }else{
            appointment.setNormal(true);
            appointmentDao.save(appointment);
            return "验证成功~";
        }
    }

    /*
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
