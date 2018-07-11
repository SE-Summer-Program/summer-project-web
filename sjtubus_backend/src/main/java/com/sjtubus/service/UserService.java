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

    /**
     * @description: 根据关键字获取用户信息
     * @date: 2018/7/10 19:48
     * @params: content - 关键字
     * @return: List<User> - 用户列表
    */
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

    /**
     * @description: 添加用户
     * @date: 2018/7/10 19:49
     * @params: 用户名、密码、是否教师、电话
     * @return: 所添加用户
    */
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
