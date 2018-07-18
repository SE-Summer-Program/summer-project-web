package com.sjtubus.controller;

import com.sjtubus.model.AppointInfo;
import com.sjtubus.model.RecordInfo;
import com.sjtubus.model.response.AppointInfoResponse;
import com.sjtubus.model.response.HttpResponse;
import com.sjtubus.model.response.LineInfoResponse;
import com.sjtubus.model.response.RecordInfoResponse;
import com.sjtubus.service.AppointService;
import com.sjtubus.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
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
    @RequestMapping(value = "/record",method = RequestMethod.GET)
    public RecordInfoResponse getRecordInfos(String username, String current_time){
        RecordInfoResponse response = new RecordInfoResponse();
        List<RecordInfo> recordInfos;
        recordInfos = recordService.getRecordInfo(username, current_time);
        response.setRecordInfos(recordInfos);
        return response;
    }

    /**
     * @description: 用户提交预约申请
     * @date: 2018/7/18 8:10
     * @params:
     * @return: HttpResponse 返回预约反馈信息
    */
    @RequestMapping(value = "/appoint",method = RequestMethod.POST)
    public HttpResponse appoint(String username,
                                String appoint_date,
                                String shift_id,
                                String line_name,
                                String submit_time){
        HttpResponse response  = new HttpResponse();
        boolean result = appointService.addAppointment(username,appoint_date,shift_id,line_name, submit_time);
        if(result){
            response.setMsg("预约成功!");
            response.setError(0);
            return response;
        }else{
            response.setMsg("预约失败!");
            response.setError(1);
            return response;
        }
    }
}
