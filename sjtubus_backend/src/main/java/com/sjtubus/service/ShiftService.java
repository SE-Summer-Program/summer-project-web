package com.sjtubus.service;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.model.LineInfo;
import com.sjtubus.model.ShiftInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftDao shiftDao;

    public List<LineInfo> getShiftInfo(String type){
        List<LineInfo> results = new ArrayList<>();
        List<Shift> shifts = shiftDao.getLineNameByType(type);
        for(Shift shift:shifts){
            String line_name = shift.getLine_name();
            Shift first_shift = shiftDao.getFirstTimeByLineNameAndType(line_name,type);
            Shift last_shift = shiftDao.getLastTimeByLineNameAndType(line_name,type);
            LineInfo info = new LineInfo();
            info.setStart_time(first_shift.getDeparture_time());
            info.setEnd_time(last_shift.getDeparture_time());
            info.setLine_name(line_name);
            results.add(info);
        }
        return results;
    }

    public List<String> getLineName(String type){
        List<String> result = new ArrayList<>();
        List<Shift> shifts = shiftDao.getLineNameByType(type);
        for (Shift shift:shifts){
            String line_name = shift.getLine_name();
            result.add(line_name);
        }
        return result;
    }

    public List<ShiftInfo> getSortedShiftInfo(String type, String line_name){
        List<ShiftInfo> result = shiftDao.getShiftInfoByTypeAndLine_name(type,line_name);
        return result;
    }
}
