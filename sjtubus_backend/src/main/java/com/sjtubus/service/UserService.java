package com.sjtubus.service;

import com.sjtubus.dao.DriverDao;
import com.sjtubus.dao.UserDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            if (user.getUsername().contains(content) || String.valueOf(user.getUserId()).contains(content)){
                results.add(user);
            }
        }
        return  results;
    }


    public User addUser(String username, String password, boolean isTeacher, String phone){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCredit(100);
        user.setTeacher(isTeacher);
        user.setPhone(phone);
        return userDao.save(user);
    }

    @Transactional
    public User findByUserName(String username){ return userDao.findByUsername(username); }

    @Transactional
    public List<User> listAllUsers(){
        return userDao.findAll();
    }

}
