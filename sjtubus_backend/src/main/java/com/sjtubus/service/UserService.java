package com.sjtubus.service;

import com.sjtubus.dao.UserDao;
import com.sjtubus.entity.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User login(String name, String password) {
    	return userDao.findByUsernameAndPassword(name, password);
    }
    public List<User> getList(){
    	return userDao.findAll();
    }
    public String getNameById(Integer id) {
    	User user = userDao.findByUserId(id);
    	if(user == null) return "NULL";
        return user.getName();
    }
    public User getUserById(Integer id) {
    	return userDao.findByUserId(id);
    }
    public boolean addUser(String name, String password, String phone, boolean isteacher) {
    	User user = userDao.findByUsername(name);
    	if(user != null) return false;
    	userDao.save(new User(name, password, phone, isteacher));
    	return true;
    }
    public void update(User user) {
    	userDao.save(user);
    }
    public void deleteById(Integer id) {
    	User user = userDao.findById(id).get();
    	if(user != null) userDao.delete(user);
    }

}
