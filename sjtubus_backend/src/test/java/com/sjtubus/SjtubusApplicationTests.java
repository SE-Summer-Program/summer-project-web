package com.sjtubus;

import com.sjtubus.controller.AppointController;
import com.sjtubus.controller.LineController;
import com.sjtubus.entity.User;
import com.sjtubus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SjtubusApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private UserService userService;

	@Autowired
	private AppointController appointController;

	@Autowired
	private LineController lineController;

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

	@Test
	public void testAppointController(){
		appointController.getAppointInfo("MinHangToXuHui" ,"HolidayWorkday","2018-07-16");
	}

	@Test
	public void testLineController(){
		lineController.getLineInfo("NormalWorkday");
	}

	@Test
	public void getBeginDayOfYesterday() {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		System.out.println(cal.getTime());
	}

	@Test
	public void getCurrrentDate(){
		String current_date="";
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		current_date=simpleDateFormat.format(date);

		String s = "2018-07-18";
		if (s.equals(current_date)){
			System.out.println("The same!");
		}
		else
			System.out.println("Different");
	}

	@Test
	public void testPostAppoint(){
		appointController.appoint("wxw","2018-07-19","MXHD1215",
				"MinHangToXuHui","2018-07-19 08:50:00");
	}

	@Test
	public void testRecordSerivce(){
		appointController.getRecordInfos("王鑫伟");
	}
}
