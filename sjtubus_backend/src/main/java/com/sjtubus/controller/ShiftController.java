package com.sjtubus.controller;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.entity.projection.ShiftInfo;
import com.sjtubus.model.Schedule;
import com.sjtubus.model.response.HttpResponse;
import com.sjtubus.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping(path = "/shift")
public class ShiftController {

    @Autowired
    ShiftService shiftService;
    @Autowired
    ShiftDao shiftDao;

    @RequestMapping(path = "/shiftinfo", method = RequestMethod.GET)
    public List<ShiftInfo> getShiftInfo(String type,String line_name){
        return shiftService.getSortedShiftInfo(type,line_name);
    }

    @RequestMapping(path = "/linename",method = RequestMethod.GET)
    public List<String> getLinename(String type){
        return shiftService.getAllLineName(type);
    }

    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public List<Shift> listAll(){
        return shiftDao.findAll();
    }

    @RequestMapping(path="/schedule")
    public Schedule getSchedule(@RequestParam("type") String lineType,
                                   @RequestParam("line_name") String lineName){
        return shiftService.getSchedule(lineType, lineName);
    }

    @RequestMapping(path="/add")
    public HttpResponse addShift(@RequestParam("lineName") String lineName,
                                 @RequestParam("lineNameCn") String lineNameCn,
                                 @RequestParam("lineType") String lineType,
                                 @RequestParam("departureTime") Time departureTime,
                                 @RequestParam("reserveSeat") int reserveSeat,
                                 @RequestParam("comment") String comment){
        HttpResponse response = new HttpResponse();
        String result = shiftService.addShift(lineName, lineNameCn, lineType, departureTime, reserveSeat, comment);
        response.setMsg(result);
        return response;
    }

}
