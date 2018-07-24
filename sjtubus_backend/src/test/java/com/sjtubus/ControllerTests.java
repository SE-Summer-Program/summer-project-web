package com.sjtubus;

import com.sjtubus.controller.AppointController;
import com.sjtubus.controller.LineController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ControllerTests extends SjtubusApplicationTests {
    @Autowired
    private AppointController appointController;

    @Autowired
    private LineController lineController;

    @Test
    public void testAppointController(){
        appointController.getAppointInfo("MinHangToXuHui" ,"HolidayWorkday","2018-07-16");
    }

    @Test
    public void testLineController(){
        lineController.getLineInfo("NormalWorkday");
    }
}
