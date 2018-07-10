package com.sjtubus.service;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.projection.Linename;
import com.sjtubus.entity.projection.ShiftInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftDao shiftDao;

    @Transactional
    public List<String> getAllLineName(String type){
        List<String> result = new ArrayList<>();
        Collection<Linename> names = shiftDao.getLineNameByType(type);
        for (Linename linename:names){
            String line_name = linename.getLineNameCn();
            result.add(line_name);
        }
        return result;
    }

    public List<ShiftInfo> getSortedShiftInfo(String type, String line_name){
        List<ShiftInfo> result = shiftDao.getShiftInfoByTypeAndLinename(type,line_name);
        return result;
    }

    //    public List<ShiftInfo> getShiftInfo(String type){
//        List<ShiftInfo> results = new ArrayList<>();
//        List<Shift> shifts = shiftDao.getLineNameByType(type);
//        for(Shift shift:shifts){
//            String line_name = shift.getLineName();
//            Shift first_shift = shiftDao.getFirstTimeByLineNameAndType(line_name,type);
//            Shift last_shift = shiftDao.getLastTimeByLineNameAndType(line_name,type);
//            ShiftInfo info = new ShiftInfo();
//            info.setStart_time(first_shift.getDepartureTime());
//            info.setEnd_time(last_shift.getDepartureTime());
//            info.setLine_name(line_name);
//            results.add(info);
//        }
//        return results;
//    }
}
