package com.sjtubus.service;

import com.sjtubus.dao.AdministratorDao;
import com.sjtubus.entity.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorDao administratorDao;

    public List<Administrator> searchAdministrator(String username){
        return administratorDao.searchByUsername(username);
    }

    public Administrator saveAdministrator(String username, String password){
        Administrator administrator = new Administrator();
        administrator.setPassword(password);
        administrator.setUsername(username);
        return administratorDao.save(administrator);
    }

    public Administrator findAdminByUsername(String username){
        return administratorDao.findByUsername(username);
    }

}
