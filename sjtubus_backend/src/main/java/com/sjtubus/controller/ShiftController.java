package com.sjtubus.controller;

import com.sjtubus.entity.Shift;
import com.sjtubus.model.LineInfo;
import com.sjtubus.model.ShiftInfo;
import com.sjtubus.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(name="/sorted_info")
    public List<ShiftInfo> getSortedShift(String type, String line_name){
        return shiftService.getSortedShiftInfo(type, line_name);
    }


}
