package com.sjtubus.controller;
import com.sjtubus.dao.UserDao;

import com.sjtubus.entity.User;
import com.sjtubus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/username")
    public List<User> getAllUsers(){
        return userService.listAllUsers();
    }
}
