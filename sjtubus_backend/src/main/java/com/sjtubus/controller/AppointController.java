package com.sjtubus.controller;

import com.sjtubus.model.AppointInfo;
import com.sjtubus.model.response.AppointInfoResponse;
import com.sjtubus.model.response.LineInfoResponse;
import com.sjtubus.service.AppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/appointment")
public class AppointController {
    @Autowired
    AppointService appointService;

    /**
     * @description:
     * @date:
     * @params:
     * @return:
     */
    @RequestMapping(value = "/infos")
    public AppointInfoResponse getAppointInfo(String line_name, String type, String appoint_date){
        System.out.println("linename:"+line_name);
        AppointInfoResponse response = new AppointInfoResponse();
        List<AppointInfo> appoints = new ArrayList<>();
        appoints = appointService.getAppointInfo(line_name, type, appoint_date);
        System.out.println("appoints:"+appoints.size());
        response.setAppointInfos(appoints);
        return response;
    }


}
