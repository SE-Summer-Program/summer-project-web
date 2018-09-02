package com.sjtubus.controller;

import com.sjtubus.model.response.StaAppointResponse;
import com.sjtubus.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(path="appointment_month")
    public StaAppointResponse getMonthData(@RequestParam("month") Month month,
                                            @RequestParam("lineNameCn") String lineNameCn,
                                            @RequestParam("lineType") String lineType,
                                            @RequestParam("time") Time time){
        StaAppointResponse response = new StaAppointResponse();
        List<Integer> result = statisticsService.dealMonthData(month, lineNameCn, lineType, time);
        return response;
    }

}
