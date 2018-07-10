package com.sjtubus.dao;

import com.sjtubus.entity.Administrator;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministratorDao extends JpaRepository<Administrator, Long> {

	Administrator findById(Integer id);
	Administrator findByName(String name);
	Administrator findByNameAndPassword(String name, String password);

    /**
     * Find user.
     * User为@Entity 的名字
     * @param name the name
     * @return the user
     */
    //@Query("from Administrator u where u.name=:name")
    //Administrator findUser(@Param("name") String name);
}