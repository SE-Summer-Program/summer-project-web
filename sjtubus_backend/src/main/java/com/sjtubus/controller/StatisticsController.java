package com.sjtubus.controller;

import com.sjtubus.model.response.StaAppointResponse;
import com.sjtubus.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Month;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(path="/appointment")
    public StaAppointResponse getMonthData(@RequestParam("startDate") String startDate,
                                           @RequestParam("endDate") String endDate,
                                            @RequestParam("lineNameCn") String lineNameCn,
                                            @RequestParam("lineType") String lineType,
                                            @RequestParam("time") Time time){
        StaAppointResponse response = new StaAppointResponse();
        try {
            //System.out.println("hello");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = formatter.parse(startDate);
            //SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = formatter.parse(endDate);
            //System.out.println(date1);
            //System.out.println(date2);
            List<Integer> result = statisticsService.dealAppointmentData(date1, date2, lineNameCn, lineType, time);
            response.setStatistics(result);
            response.setMsg("success");
        }
        catch(Exception e){
            response.setError(1);
            response.setMsg("error");
        }
        return response;
    }

}
