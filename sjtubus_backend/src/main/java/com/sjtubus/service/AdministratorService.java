package com.sjtubus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sjtubus.dao.AdministratorDao;
import com.sjtubus.entity.Administrator;

@Service
public class AdministratorService{

    @Autowired
    private AdministratorDao administratorDao;

    public Administrator login(String name, String password) {
    	return administratorDao.findByNameAndPassword(name, password);
    }
    public boolean addAdmin(String name, String password) {
    	Administrator admin = administratorDao.findByName(name);
    	if(admin != null) return false;
        administratorDao.save(new Administrator(name, password));
        return true;
    }
    public void resetPassword(Integer id, String password) {
    	Administrator admin = administratorDao.findById(id);
    	admin.setPassword(password);
        administratorDao.save(admin);
    }
}