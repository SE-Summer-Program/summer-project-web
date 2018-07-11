package com.sjtubus.controller;
import com.sjtubus.dao.UserDao;

import com.sjtubus.entity.User;
import com.sjtubus.model.response.HttpResponse;
import com.sjtubus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/username")
    public List<User> getAllUsers(){
        return userService.listAllUsers();
    }

    @RequestMapping(path="/search")
    public List<User> getRelatedUsers(@RequestParam("content") String content){
        return userService.getUserInfo(content);
    }

    @RequestMapping(path="/add" )
    public HttpResponse addUser(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("userId") int userId,
                                @RequestParam("credit") int credit,
                                @RequestParam("phone") String phone,
                                @RequestParam("isTeacher") boolean isTeacher){
        HttpResponse response = new HttpResponse();
        User olduser = userService.addUser(username, password, isTeacher, phone, userId, credit);
        if (olduser == null){
            response.setMsg("success");
        }
        else{
            response.setMsg("fail");
        }
        return response;
    }


    @RequestMapping(path="/delete" )
    public HttpResponse deleteUser(@RequestParam("userId") int userId){
        String result = userService.deleteUser(userId);
        HttpResponse response = new HttpResponse();
        response.setMsg(result);
        return response;
    }
}
