package com.sjtubus.controller;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
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
@RequestMapping(value = "/shift")
public class ShiftController {

    @Autowired
    ShiftService shiftService;
    @Autowired
    ShiftDao shiftDao;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Shift> listAll(){
        return shiftDao.findAll();
    }

    /**
     * @description: 根据line_type和line_name获取schedule对象
     * @date: 2018/7/10 18:20
     * @params: type - 线路类别 line_name - 线路名称
     * @return: Schedule - 线路时刻表
    */
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


    @RequestMapping(value="/add")
    public HttpResponse addShift(@RequestParam("lineName") String lineName,
                                 @RequestParam("lineNameCn") String lineNameCn,
                                 @RequestParam("lineType") String lineType,
                                 @RequestParam("departureTime")Time departureTime,
                                 @RequestParam("reserveSeat") int reserveSeat,
                                 @RequestParam("comment") String comment,
                                 @RequestParam("busId") int busId,
                                 @RequestParam("arriveTime") Time arriveTime){
        HttpResponse response = new HttpResponse();
        try {
            //System.out.println("lineName:"+lineName);
            //System.out.println("departureTime:"+ String.valueOf(departureTime));
            String result = shiftService.addShift(lineName, lineNameCn, lineType, departureTime, reserveSeat, comment, busId, arriveTime);
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
            response.setMsg("success");
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

    @RequestMapping(path="/modify")
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
