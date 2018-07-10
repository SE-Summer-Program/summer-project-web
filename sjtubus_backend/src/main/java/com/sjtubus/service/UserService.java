package com.sjtubus.service;

import com.sjtubus.dao.DriverDao;
import com.sjtubus.dao.UserDao;
import com.sjtubus.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private DriverDao driverDao;

    @Transactional
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
