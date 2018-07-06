package com.sjtubus.service;

import com.sjtubus.dao.RideBusInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideBusInfoService {
    @Autowired
    RideBusInfoDao rideBusInfoDao;
}
