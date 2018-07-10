package com.sjtubus.service;

import com.sjtubus.dao.TimeTableDao;
import com.sjtubus.entity.TimeTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeTableService {
    @Autowired
    private TimeTableDao timeTableDao;

    public Map<String, List<String>> getScheduleByStationAndType(String station, String type) {
    	TimeTable timeTable = timeTableDao.findByStationAndType(station, type);
    	Map<String, List<String>> schedule = new HashMap<String, List<String>>();
    	schedule.put("loopline", timeTable.getLoopline());
    	schedule.put("nonloopline", timeTable.getNonloopline());
    	return schedule;
    }
}