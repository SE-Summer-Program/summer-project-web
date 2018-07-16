package com.sjtubus.service;

import com.sjtubus.dao.AppointmentDao;
import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.model.AppointInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private ShiftDao shiftDao;

    /**
     * @description:
     * @date:
     * @params:
     * @return:
     */
    public void getAppointment(String line_name, String type){
        List<Shift> shifts =  shiftDao.findByLineTypeAndLineNameOrderByDepartureTime(line_name, type);
        for (int i = 0; i < shifts.size(); i++) {
            AppointInfo info = new AppointInfo();
//            info.setShiftId(shifts.get(i).getShiftId());
//            info
        }
    }

//    List<Shift> shifts = shiftDao.findByLineTypeAndLineNameOrderByDepartureTime(type,line_name);
//    LineInfo info = new LineInfo();
//        info.setLineName(line_name);
//        if(shifts == null || shifts.size()==0){
//        return null;
//    }
//        info.setFirstTime(shifts.get(0).getDepartureTime().toString());
//        info.setLastTime(shifts.get(shifts.size()-1).getDepartureTime().toString());
//        info.setLineNameCN(shifts.get(0).getLineNameCn());
//        return info;
}
