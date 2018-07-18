package com.sjtubus.service;


import com.sjtubus.dao.UserDao;
import com.sjtubus.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * @description: 根据content比对数据库中的user，找出username或者phone包含content字段的user
     * @date: 2018/7/18 20:14
     * @params: 字段content
     * @return: 包含字段content的用户列表
    */
    public List<User> getUserInfo(String content){
        return  userDao.queryByRelatedContent(content);
    }


    public User addUser(String username, String password, boolean isTeacher, String phone, int userId, int credit){
        User olduser = userDao.findByUserId(userId);
        if( olduser == null ){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setCredit(credit);
            user.setTeacher(isTeacher);
            user.setPhone(phone);
            user.setUserId(userId);
            userDao.save(user);
        }
        return olduser;
    }

    public String deleteUser(int userId){
        User olduser = userDao.findByUserId(userId);
        if( olduser != null ){
            userDao.delete(olduser);
            return "success";
        }
        else {
            return "fail";
        }
    }

    public int modifyUser(int userId, String username, String phone, int credit){
        return userDao.modifyUser(userId, username, phone, credit);
    }


    @Transactional
    public User findByUserName(String username){ return userDao.findByUsername(username); }

    @Transactional
    public List<User> listAllUsers(){
        return userDao.findAll();
    }

}
