package com.sjtubus.dao;


import com.sjtubus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {
}
