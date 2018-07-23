package com.sjtubus.controller;


import com.sjtubus.model.response.HttpResponse;
import com.sjtubus.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;


@RestController
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping(value="/add")
    public HttpResponse addMessage(@RequestParam("messageType") String messageType,
                                   @RequestParam("messageTitle") String messageTitle,
                                   @RequestParam("messageContent") String messageContent,
                                   @RequestParam("startDate") Date startDate,
                                   @RequestParam("endDate") Date endDate){
        HttpResponse response = new HttpResponse();
        try{
            response.setMsg(messageService.addMessage(messageType, messageTitle, messageContent, startDate, endDate));
        }
        catch (Exception e){
            response.setError(1);
            response.setMsg("fail");
        }
        return response;
    }
}
