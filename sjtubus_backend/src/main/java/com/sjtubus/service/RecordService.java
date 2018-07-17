package com.sjtubus.service;

import com.sjtubus.dao.AppointmentDao;
import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Appointment;
import com.sjtubus.entity.Shift;
import com.sjtubus.model.RecordInfo;
import com.sjtubus.utils.StringCalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private ShiftDao shiftDao;

    /**
     * @description: 找出跟username对的上的所有预约条目
     * @date: 2018/07/18 14:09
     * @params:
     * @return:
     */
    public List<RecordInfo> getRecordInfo(String username, String current_time){
        List<Appointment> appointments = appointmentDao.findByUserName(username);
        List<RecordInfo> recordInfos = new ArrayList<>();

        if (appointments == null || appointments.size()==0){
            return null;
        }
        for (Appointment appointment : appointments){
            Shift shift = shiftDao.findByShiftId(appointment.getShiftId());

            RecordInfo info = new RecordInfo();
            String departuredate = StringCalendarUtils.DateToString(appointment.getAppointDate());
            String departuretime = StringCalendarUtils.TimeToString(shift.getDepartureTime());
            info.setLineName(shift.getLineName());
            info.setDepartureDate(departuredate);
            info.setDepartureTime(departuretime);
            info.setShiftid(shift.getShiftId());

            // 预约成功 时间未到，normal
            // 预约失败 时间未到，unnormal
            // 已出行  时间已到
            String departure = departuredate + " " + departuretime;
            if (! StringCalendarUtils.isBeforeCurrentTime(departure))
                info.setStatus("已出行");
            else if (! appointment.isNormal()){
                info.setStatus("预约失败");
            }
            else if (appointment.isNormal()){
                info.setStatus("预约成功");
            }
            else{
                info.setStatus("系统错误");
            }

            recordInfos.add(info);
        }

        return recordInfos;
    }

}
