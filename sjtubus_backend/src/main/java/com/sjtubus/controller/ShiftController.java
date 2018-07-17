package com.sjtubus.controller;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.entity.projection.ShiftInfo;
import com.sjtubus.model.Schedule;
import com.sjtubus.model.response.HttpResponse;
import com.sjtubus.model.response.ScheduleResponse;
import com.sjtubus.model.response.ShiftListResponse;
import com.sjtubus.model.response.TimeListResponse;
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


    @RequestMapping(path="/search_schedule")
    public ScheduleResponse getSchedule(@RequestParam("type") String lineType,
                                   @RequestParam("line_name") String lineName){
        ScheduleResponse response = new ScheduleResponse();
        try{
            Schedule result =  shiftService.getSchedule(lineType, lineName);
            response.setSchedule(result);
        }
        catch (Exception e){
            response.setError(1);
            response.setSchedule(null);
        }
        return response;
    }


    @RequestMapping(path="/add")
    public HttpResponse addShift(@RequestParam("lineName") String lineName,
                                 @RequestParam("lineNameCn") String lineNameCn,
                                 @RequestParam("lineType") String lineType,
                                 @RequestParam("departureTime") Time departureTime,
                                 @RequestParam("reserveSeat") int reserveSeat,
                                 @RequestParam("comment") String comment){
        HttpResponse response = new HttpResponse();
        try {
            String result = shiftService.addShift(lineName, lineNameCn, lineType, departureTime, reserveSeat, comment);
            response.setMsg(result);
        }
        catch (Exception e){
            response.setError(1);
            response.setMsg("fail");
        }
        return response;
    }


    @RequestMapping(path="/search_shift")
    public ShiftListResponse searchShift(@RequestParam("content") String content){
        ShiftListResponse response = new ShiftListResponse();
        try {
            List<Shift> result = shiftService.searchShift(content);
            response.setError(0);
            response.setShiftList(result);
        }
        catch (Exception e){
            response.setError(1);
            response.setShiftList(null);
        }
        return response;
    }


    @RequestMapping(path="/delete" )
    public HttpResponse deleteShift(@RequestParam("shiftId") String shiftId){
        HttpResponse response = new HttpResponse();
        try {
            String result = shiftService.deleteShift(shiftId);
            response.setMsg(result);
        }
        catch (Exception e){
            response.setError(1);
            response.setMsg("fail");
        }
        return response;
    }


    @RequestMapping(path="/search_time" )
    public TimeListResponse searchTimeList(@RequestParam("lineNameCn") String lineNameCn,
                                            @RequestParam("lineType") String lineType){
        TimeListResponse response = new TimeListResponse();
        try{
            List<Time> timeList = shiftService.getTimeList(lineNameCn, lineType);
            response.setTimeList(timeList);
        }
        catch (Exception e){
            response.setError(1);
            response.setTimeList(null);
        }
        return response;
    }

    @RequestMapping(path="/modify_seat")
    public HttpResponse modifySeat(@RequestParam("shiftId") String shiftId,
                                   @RequestParam("reserveSeat") int reserveSeat){
        HttpResponse response = new HttpResponse();
        try{
            shiftService.modifySeat(shiftId, reserveSeat);
            response.setMsg("success");
        }
        catch (Exception e){
            response.setMsg("fail");
            response.setError(1);
        }
        return response;
    }
}
