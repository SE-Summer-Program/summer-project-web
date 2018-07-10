package com.sjtubus.service;

import com.sjtubus.dao.TimeTableDao;
import com.sjtubus.entity.TimeTable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeTableService {
    @Autowired
    private TimeTableDao timeTableDao;

    public TimeTable getTimeTableByLineName(String station, String type) {
    	return timeTableDao.findByStationAndType(station, type);
    }
}