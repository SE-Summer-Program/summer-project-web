package com.sjtubus.service;

import com.sjtubus.dao.DriverDao;
import com.sjtubus.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private DriverDao driverDao;

}
