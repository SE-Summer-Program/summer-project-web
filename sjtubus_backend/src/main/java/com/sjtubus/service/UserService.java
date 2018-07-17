package com.sjtubus.service;

import com.sjtubus.dao.DriverDao;
import com.sjtubus.dao.JaccountUserDao;
import com.sjtubus.dao.UserDao;
import com.sjtubus.entity.JaccountUser;
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
    private JaccountUserDao jaccountUserDao;

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

    /**
     * @description: 添加jaccount用户
     * @date: 2018/7/16 12:54
     * @params: 用户名、是否教师、电话
     * @return: 所添加用户
     */
    public JaccountUser addJaccountUser(String username, boolean isTeacher, String phone){
        JaccountUser user = new JaccountUser();
        user.setUsername(username);
        user.setCredit(100);
        user.setTeacher(isTeacher);
        user.setPhone(phone);
        return jaccountUserDao.save(user);
    }

    /**
     * @description: 通过电话号码找到user
     * @date: 2018/7/13 11:12
     * @params: phone - 电话号码
     * @return: User
    */
    @Transactional
    public User findUserByPhone(String phone){
        return userDao.findByPhone(phone);
    }

    @Transactional
    public User findByUserName(String username){ return userDao.findByUsername(username); }

    @Transactional
    public List<User> listAllUsers(){
        return userDao.findAll();
    }

    @Transactional
    public void saveUser(User user){
        userDao.save(user);
    }

    @Transactional
    public JaccountUser findJaccountUserByPhone(String phone){
        return jaccountUserDao.findByPhone(phone);
    }

    @Transactional
    public JaccountUser findByJaccountUserName(String username){ return jaccountUserDao.findByUsername(username); }

    @Transactional
    public List<JaccountUser> listAllJaccountUsers(){
        return jaccountUserDao.findAll();
    }

    @Transactional
    public void saveJaccountUser(JaccountUser user){ jaccountUserDao.save(user); }
}
