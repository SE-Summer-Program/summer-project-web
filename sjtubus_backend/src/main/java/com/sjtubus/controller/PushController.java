package com.sjtubus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Allen
 * @date 2018/7/17 6:04
 */

@RestController
public class PushController {

    @RequestMapping(value = "/push")
    public void pushMessage(String message){

    }
}
