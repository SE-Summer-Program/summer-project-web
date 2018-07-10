package com.sjtubus.controller;

import com.sjtubus.entity.Shift;
import com.sjtubus.model.LineInfo;
import com.sjtubus.model.Schedule;
import com.sjtubus.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController("/shift")
public class ShiftController {

    @Autowired
    ShiftService shiftService;

    @GetMapping(name = "/shift_info")
    public List<LineInfo> getShiftInfo(String type){
        return shiftService.getShiftInfo(type);
    }

    @GetMapping(name="/line_name")
    public List<String> getLinename(String type){
        return shiftService.getLineName(type);
    }



    @GetMapping(name="/schedule")
    public Schedule getOutSchedule(@RequestParam("type") String type,
                                @RequestParam("line_name") String line_name){
        return shiftService.getSchedule(type, line_name);
    }


}
