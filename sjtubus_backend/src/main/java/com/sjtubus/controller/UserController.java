package com.sjtubus.controller;
import com.sjtubus.dao.UserDao;
import com.sjtubus.entity.User;
import com.sjtubus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/search")
    public List<User> getUserInfo(@RequestParam("content") String content){
        return userService.getUserInfo(content);
    }

    @GetMapping(path = "/list")
    public List<User> list(){
        return userDao.findAll();
    }
}
