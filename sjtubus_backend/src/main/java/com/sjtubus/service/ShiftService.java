package com.sjtubus.service;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftDao shiftDao;

    /**
     * @description: 将查询到的shift列表重新组织成schedule对象
     * @date: 2018/7/10 19:33
     * @params: type - 类别 line_name - 名称
     * @return: Schedule - Schedule对象
    */
    @Transactional
    public Schedule getSchedule(String type, String line_name){
        List<Shift> shiftInfo = shiftDao.findByLineTypeAndLineNameOrderByDepartureTime(type, line_name);
        System.out.println("shiftInfoSize:"+shiftInfo.size());
        List<String> startTimeList = new ArrayList<>();
        List<String> commentList = new ArrayList<>();
        List<String> shiftidList = new ArrayList<>();
        for (int i = 0; i < shiftInfo.size(); i++ ){
            String startTime = shiftInfo.get(i).getDepartureTime().toString();
            startTimeList.add(startTime);
            String comment = shiftInfo.get(i).getComment();
            commentList.add(comment);
            String shiftid = shiftInfo.get(i).getShiftId();
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
