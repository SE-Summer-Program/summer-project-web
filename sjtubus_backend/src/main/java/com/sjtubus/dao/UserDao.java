package com.sjtubus.dao;

import com.sjtubus.entity.User;
import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

	User findByUserId(Integer id);
	
	User findByUsername(String name);

	User findByUsernameAndPassword(String name, String password);
	
}
