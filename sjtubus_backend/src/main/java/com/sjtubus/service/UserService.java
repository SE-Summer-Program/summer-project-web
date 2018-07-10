package com.sjtubus.service;

import com.sjtubus.dao.DriverDao;
import com.sjtubus.dao.UserDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.entity.User;
import com.sjtubus.model.LineInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private DriverDao driverDao;

    public List<User> getUserInfo(String content){
        List<User> results = new ArrayList<>();
        List<User> userList = userDao.findAll();
        for(User user:userList){
            if (user.getUsername().contains(content) || user.getUser_id().contains(content)){
                results.add(user);
            }
        }
        return  results;
    }

}
