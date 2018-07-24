package com.sjtubus;

import com.sjtubus.entity.User;
import com.sjtubus.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceTests extends SjtubusApplicationTests{
    @Autowired
    private UserService userService;

    @Test
    public void testUser(){
        UserService userService = new UserService();
        userService.addUser("user", "password", false, "13262600000", 100);
        List<User> users = userService.listAllUsers();
        System.out.println("size");
        for (User user : users){
            System.out.println(123);
            System.out.println(user.getUsername());
        }
    }
}
