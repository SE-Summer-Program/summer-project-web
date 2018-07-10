package com.sjtubus.service;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.model.LineInfo;
import com.sjtubus.model.Schedule;

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

    public List<Shift> getSortedShiftInfo(String type, String line_name){
        List<Shift> result = shiftDao.queryByLine_typeAndLine_name(type,line_name);
        return result;
    }

    public Schedule getSchedule(String type, String line_name){
        List<Shift> shiftInfo = shiftDao.queryByLine_typeAndLine_name(type, line_name);
        System.out.println("shiftInfoSize:"+shiftInfo.size());
        List<String> startTimeList = new ArrayList<>();
        List<String> commentList = new ArrayList<>();
        List<String> shiftidList = new ArrayList<>();
        for (int i = 0; i < shiftInfo.size(); i++ ){
            String startTime = shiftInfo.get(i).getDeparture_time().toString();
            startTimeList.add(startTime);
            String comment = shiftInfo.get(i).getComment();
            commentList.add(comment);
            String shiftid = shiftInfo.get(i).getShift_id();
            shiftidList.add(shiftid);
        }
        System.out.println("startTimeListSize:"+startTimeList.size());
        Schedule result = new Schedule(line_name, type);
        result.setLineName(line_name);
        result.setTypes(type);
        result.setScheduleTime(startTimeList);
        result.setScheduleComment(commentList);
        result.setScheduleShift(shiftidList);
        return result;
    }
}
