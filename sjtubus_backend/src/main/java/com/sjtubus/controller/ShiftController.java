package com.sjtubus.controller;

import com.sjtubus.entity.Shift;
import com.sjtubus.model.LineInfo;
import com.sjtubus.model.Schedule;
import com.sjtubus.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value="/shift")
public class ShiftController {

    @Autowired
    ShiftService shiftService;

    @RequestMapping(value = "/shift_info")
    public List<LineInfo> getShiftInfo(@RequestParam("type") String type){
        return shiftService.getShiftInfo(type);
    }

    @RequestMapping(value ="/line_name")
    public List<String> getLinename(@RequestParam("type") String type){
        return shiftService.getLineName(type);
    }

    @RequestMapping(value ="/sorted_info")
    public List<Shift> getSortedShift(@RequestParam("type") String type,
                                          @RequestParam("line_name") String line_name){
        return shiftService.getSortedShiftInfo(type, line_name);
    }

    @RequestMapping(value ="/schedule")
    public Schedule getOutSchedule(@RequestParam("type") String type,
                                @RequestParam("line_name") String line_name){
        return shiftService.getSchedule(type, line_name);
    }


}
