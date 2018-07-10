package com.sjtubus.service;

import com.sjtubus.dao.LineDao;
import com.sjtubus.entity.Line;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineService {
    @Autowired
    private LineDao lineDao;

    public List<String> getStationByLineName(String name) {
    	return lineDao.findByName(name).getStation();
    }
}
