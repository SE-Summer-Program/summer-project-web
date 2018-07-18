package com.sjtubus.service;

import com.sjtubus.dao.DriverDao;
import com.sjtubus.entity.Driver;
import com.sjtubus.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {
    @Autowired
    DriverDao driverDao;

    /**
     * @description: 根据content比对数据库中的driver，找出username或者phone包含content字段的driver
     * @date: 2018/7/18 16:54
     * @params: content
     * @return: Driver列表
    */
    public List<Driver> getDriverInfo(String content){
        return driverDao.queryByRelatedContent(content);
    }


    /**
     * @description: 根据driverId找到对应的司机，将其用户名和电话改成新的值
     * @date: 2018/7/18 19:04
     * @params: 司机ID， 司机用户名， 司机电话
     * @return: 修改的条目数
    */
    public int modifyDriver(int driverId, String username, String phone){
        return driverDao.modifyDriver(driverId, username, phone);
    }
}
