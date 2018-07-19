package com.sjtubus.controller;


import com.sjtubus.entity.Driver;
import com.sjtubus.model.response.DriverListResponse;
import com.sjtubus.model.response.HttpResponse;
import com.sjtubus.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @RequestMapping(path="/search")
    public DriverListResponse getRelatedDrivers(@RequestParam("content") String content){
        DriverListResponse response = new DriverListResponse();
        try {
            response.setDriverList(driverService.getDriverInfo(content));
            response.setMsg("success");
        }
        catch (Exception e){
            response.setMsg("fail");
            response.setDriverList(null);
            response.setError(1);
        }
        return response;
    }

    @RequestMapping(path="/modify")
    public HttpResponse modifyDriver(@RequestParam("driverId") int driverId,
                                     @RequestParam("username") String username,
                                     @RequestParam("phone") String phone){
        HttpResponse response = new HttpResponse();
        try{
            driverService.modifyDriver(driverId, username, phone);
            response.setMsg("success");
        }
        catch (Exception e)
        {
            response.setMsg("fail");
            response.setError(1);
        }
        return response;
    }
}
