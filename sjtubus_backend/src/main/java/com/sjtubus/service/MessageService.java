package com.sjtubus.service;


import com.sjtubus.dao.MessageDao;
import com.sjtubus.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class MessageService {
    @Autowired
    MessageDao messageDao;

    public String addMessage(String messageType, String messageTitle, String messageContent, Date startDate, Date endDate){
        Message message = new Message();
        message.setMessageType(messageType);
        message.setMessageTitle(messageTitle);
        message.setMessageContent(messageContent);
        message.setStartDate(startDate);
        message.setEndDate(endDate);
        messageDao.save(message);
        return "success";
    }

}
