package com.sjtubus;

import com.sjtubus.entity.User;
import com.sjtubus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SjtubusApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private UserService userService;

	@Test
	public void testUser(){
	//	UserService userService = new UserService();
		//userService.addUser("user", "password", false);
		List<User> users = userService.listAllUsers();
		System.out.println(1234);
		for (User user : users){
			System.out.println(123);
			System.out.println(user.getUsername());
		}
	}
}
