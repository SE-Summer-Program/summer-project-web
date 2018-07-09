package com.sjtubus.dao;


import com.sjtubus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,String> {

    Optional<User> findById(String id);

    public User findByUsername(String username);
}
