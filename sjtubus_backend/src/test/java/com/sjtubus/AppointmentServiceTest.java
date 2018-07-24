package com.sjtubus;

import com.sjtubus.dao.AppointmentDao;
import com.sjtubus.entity.Appointment;
import com.sjtubus.entity.JaccountUser;
import com.sjtubus.entity.User;
import com.sjtubus.model.AppointInfo;
import com.sjtubus.service.AppointmentService;
import com.sjtubus.utils.StringCalendarUtils;
import edu.sjtu.api.applicationToolkit.model.App;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.sql.Time;
import java.util.List;

import static com.sjtubus.utils.StringCalendarUtils.*;

/**
 * @author allen
 * @date 2018/7/24 14:47
 */
public class AppointmentServiceTest extends SjtubusApplicationTests {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentDao appointmentDao;

    @Test
    public void a_testGetAppointInfo() {
        String line_name = "MinHangToQiBao";
        String type = "HolidayWorkday";
        String appoint_date = "2018-07-27";
        List<AppointInfo> infos = appointmentService.getAppointInfo(line_name,type,appoint_date);
        Assert.assertEquals("fail",2,infos.size());
        Assert.assertEquals("fail","17:00:00",infos.get(1).getDepartureTime());
        Assert.assertEquals("fail","MQHD1700",infos.get(1).getShiftId());
        Assert.assertEquals("fail",45,infos.get(1).getRemainSeat());
    }

    @Test
    public void b_testAddAppointment() {
        java.sql.Date date = UtilDateToSqlDate(StringToDate("2018-07-27 17:00:00"));
        String shift_id = "MQHD1700";
        int seat_num = appointmentService.getRemainSeat(shift_id,date);
        Assert.assertEquals("fail",45,seat_num);
        String username = "姚子航";
        String appoint_date = "2018-07-27";
        String line_name = "MinHangToQiBao";
        String submit_time = "2018-07-27 09:00:00";
        boolean result = appointmentService.addAppointment(username,appoint_date,shift_id,line_name,submit_time);
        Assert.assertEquals("fail",true,result);
        seat_num = appointmentService.getRemainSeat(shift_id,date);
        Assert.assertEquals("fail",44,seat_num);
        Assert.assertNull(null);
    }

    @Test
    public void c_testSearchAppointment() {
        String lineNameCN = "闵行到七宝";
        String line_type = "HolidayWorkday";
        Time time = new Time(17,0,0);
        Date date = new Date(2018,7,27);
        List<Appointment> appointments =
                appointmentService.searchAppointment(lineNameCN,line_type,time,date);
        Assert.assertEquals("fail",1,appointments.size());
    }

    @Test
    public void d_testVerifyAppointment() {
        String username = "姚子航";
        String departure_date = "2018-07-27";
        String shift_id = "MQHD1700";

        Appointment appointment = appointmentDao.findDistinctByShiftIdAndAppointDateAndUserName(
                shift_id,UtilDateToSqlDate(StringToDate(departure_date)),username);
        Assert.assertEquals("fail",false,appointment.isNormal());

        String result = appointmentService.verifyAppointment(username,departure_date,shift_id);
        Assert.assertEquals("fail","验证成功~",result);

        appointment = appointmentDao.findDistinctByShiftIdAndAppointDateAndUserName(
                shift_id,UtilDateToSqlDate(StringToDate(departure_date)),username);
        Assert.assertEquals("fail",true,appointment.isNormal());
    }
}
