package com.sjtubus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sjtubus.dao.AdministratorDao;
import com.sjtubus.entity.Administrator;

@Service
public class AdministratorService{

    @Autowired
    private AdministratorDao administratorDao;

    /**
     * 
     * @param UserName
     * @return
     */
    public String getNameById(Integer id) {
        return administratorDao.findById(id).getName();
    }
    public void addAdministrator(String name, String password) {
        administratorDao.save(new Administrator(name, password));
    }
    public void updateAdministrator(Integer id, String name, String password) {
        administratorDao.save(new Administrator(id, name, password));
    }
}