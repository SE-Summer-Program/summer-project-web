package com.sjtubus.controller;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.model.response.ScheduleResponse;
import com.sjtubus.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping(value = "/schedule")
    public ScheduleResponse getSchedule(@RequestParam("type") String type,
                                        @RequestParam("line_name") String line_name){
        ScheduleResponse response = new ScheduleResponse();
        response.setSchedule(shiftService.getSchedule(type, line_name));
        return response;
    }

}
