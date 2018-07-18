package com.sjtubus.controller;

import com.sjtubus.model.AppointInfo;
import com.sjtubus.model.RecordInfo;
import com.sjtubus.model.response.AppointInfoResponse;
import com.sjtubus.model.response.LineInfoResponse;
import com.sjtubus.model.response.RecordInfoResponse;
import com.sjtubus.service.AppointService;
import com.sjtubus.service.RecordService;
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

    @Autowired
    RecordService recordService;

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
        List<AppointInfo> appoints;
        appoints = appointService.getAppointInfo(line_name, type, appoint_date);
        System.out.println("appoints:"+appoints.size());
        response.setAppointInfos(appoints);
        return response;
    }

    /**
     * @description: 获取与username用户相关的所有预约记录
     * @date:
     * @params:
     * @return:
     */
    @RequestMapping(value = "/record")
    public RecordInfoResponse getRecordInfos(String username, String current_time){
        RecordInfoResponse response = new RecordInfoResponse();
        List<RecordInfo> recordInfos;
        recordInfos = recordService.getRecordInfo(username, current_time);
        response.setRecordInfos(recordInfos);
        return response;
    }
}
