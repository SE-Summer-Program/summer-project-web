package com.sjtubus.dao;


import com.sjtubus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {

   // Optional<User> findById(String id);

    User findByUsername(String username);
}
