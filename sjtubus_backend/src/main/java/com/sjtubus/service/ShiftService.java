package com.sjtubus.service;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftDao shiftDao;

    /**
     * @description: 将查询到的shift列表重新组织成schedule对象
     * @date: 2018/7/10 19:33
     * @params: type - 类别 line_name - 名称
     * @return: Schedule - Schedule对象
    */
    @Transactional
    public Schedule getSchedule(String type, String line_name){
        List<Shift> shiftInfo = shiftDao.findByLineTypeAndLineNameOrderByDepartureTime(type, line_name);
        System.out.println("shiftInfoSize:"+shiftInfo.size());
        List<String> startTimeList = new ArrayList<>();
        List<String> commentList = new ArrayList<>();
        List<String> shiftidList = new ArrayList<>();
        int size = shiftInfo.size();
        for (int i = 0; i < size; i++ ){
            String startTime = shiftInfo.get(i).getDepartureTime().toString();
            startTimeList.add(startTime);
            String comment = shiftInfo.get(i).getComment();
            commentList.add(comment);
            String shiftid = shiftInfo.get(i).getShiftId();
            shiftidList.add(shiftid);
        }
        System.out.println("startTimeListSize:"+startTimeList.size());
        Schedule result = new Schedule(line_name, type);
        result.setLineName(line_name);
        result.setTypes(type);
        result.setScheduleTime(startTimeList);
        result.setScheduleComment(commentList);
        result.setScheduleShift(shiftidList);
        return result;
    }

    /**
     * @description: 将线路类型转换成shiftid中的类型部分，仅限于校区间巴士
     * @date: 2018/7/15 11:41
     * @params: lineType
     * @return: shiftidType 如WD、WE...
    */
    public String changeTypeToId(String lineType){
        String shiftidType = "";
        switch(lineType) {
            case "NormalWorkday":
                shiftidType = "WD";
                break;
            case "NormalWeekendAndLegalHoliday":
                shiftidType = "WE";
                break;
            case "HolidayWorkday":
                shiftidType = "HD";
                break;
            default:
                shiftidType = "HE";
        }
        return shiftidType;
    }

    /**
     * @description: 将一个Time类型的出发时间转换成shiftid中的时间格式
     * @date: 2018/7/15 11:57
     * @params: departureTime，如08:00:00
     * @return: 字符串型的时间，如0800
     */
    public String changeDepartureTimeToStringTime(Time departureTime){
        String tempHour = String.valueOf(departureTime.getHours());
        String tempMinute = String.valueOf(departureTime.getMinutes());
        int hourLen = tempHour.length();
        int minuteLen = tempMinute.length();
        String tempZero = "";
        for (int i = 0; i < (2-hourLen); i++){
            tempZero += '0';
        }
        tempHour = tempZero + tempHour;
        tempZero = "";
        for (int i = 0; i < (2-minuteLen); i++){
            tempZero += '0';
        }
        tempMinute = tempZero + tempMinute;
        return tempHour + tempMinute;

    }


    /**
     * @description: 管理员通过该添加班次，先根据参数解析成id，然后存入数据库，返回一个字符串
     * @date: 2018/7/11 23:28
     * @params: lineName, lineNameCn, lineType, departureTime, reserveSeat, comment
     * @return:
    */
    public String addShift(String lineName, String lineNameCn, String lineType, Time departureTime, int reserveSeat, String comment){
        //生成对应的id
        String time = changeDepartureTimeToStringTime(departureTime);
        String shiftid;
        if (lineName.equals("LoopLineAntiClockwise")){
            if (lineType.equals("HolidayWorkday"))
                shiftid = "LLAH" + time;
            else
                shiftid = "LLAW" + time;
        }
        else if (lineName.equals("LoopLineClockwise")){
            if (lineType.equals("HolidayWorkday"))
                shiftid = "LLCH" + time;
            else
                shiftid = "LLCW" + time;
        }
        else{
            if (lineName.equals("MinHangToQiBao"))
                shiftid = "MQ";
            else if (lineName.equals("MinHangToXuHui"))
                shiftid = "MX";
            else if (lineName.equals("QiBaoToMinHang"))
                shiftid = "QM";
            else
                shiftid = "XM";
            shiftid = shiftid + changeTypeToId(lineType);
            shiftid = shiftid +time;
        }

        List<Shift> sameShifts = shiftDao.queryByLinetypeAndLinenameAndDepartureTime(lineType, lineName, departureTime);
        //System.out.println(sameShifts.size());
        int sum = sameShifts.size();
        if (sum == 1){
            if (lineName.equals("LoopLineAntiClockwise") || lineName.equals("LoopLineClockwise"))
                return "existed";
            String newShiftid = shiftid + "A";
            System.out.println("new shiftid:" + newShiftid);
            Shift oldShift = sameShifts.get(0);
            int oldSeat = oldShift.getReserveSeat();
            String oldComment = oldShift.getComment();
            shiftDao.delete(oldShift);
            Shift newShift = new Shift();
            newShift.setShiftId(newShiftid);
            newShift.setLineName(lineName);
            newShift.setLineNameCn(lineNameCn);
            newShift.setLineType(lineType);
            newShift.setReserveSeat(oldSeat);
            newShift.setDepartureTime(departureTime);
            newShift.setComment(oldComment);
            shiftDao.save(newShift);
            shiftid = shiftid + "B";
        }
        else if (sum > 1){
            char letter = (char)(sum + 65);
            shiftid = shiftid + letter;
        }
        System.out.println(shiftid);
        //存储新的shift对象
        Shift shift = new Shift();
        shift.setShiftId(shiftid);
        shift.setLineName(lineName);
        shift.setLineNameCn(lineNameCn);
        shift.setDepartureTime(departureTime);
        shift.setLineType(lineType);
        shift.setReserveSeat(reserveSeat);
        shift.setComment(comment);
        shiftDao.save(shift);
        return "success";
    }

/**
 * @description: 根据content去数据库进行比对，凡是班次信息中含有content的内容的则将它返回给用户
 * @date: 2018/7/17 17:10
 * @params: content
 * @return: 含有关键字的班次列表
*/
    public List<Shift> searchShift(String content){
        return shiftDao.queryByRelatedContent(content);
    }


    public String deleteShift(String shiftId){
        Shift oldShift = shiftDao.queryShiftByShiftId(shiftId);
        shiftDao.delete(oldShift);
        return "success";
    }

    public List<Time> getTimeList(String lineNameCn, String lineType){
        List<Time> timeList =  shiftDao.getTimeListByLineNameCnAndLineType(lineType, lineNameCn);
        List<Time> result = new ArrayList<>();
        int size = timeList.size();
        for (int i = 0; i < size; i++){
            Time time = timeList.get(i);
            if (!result.contains(time)){
                result.add(time);
            }
        }
        System.out.println(result.size());
        return result;
    }


/**
 * @description: 根据shiftid寻找班次，修改其预约座位数为reserveSeat
 * @date: 2018/7/17 21:07
 * @params: 班次号shiftid，预约座位数reserveSeat
 * @return: 修改条目数
*/
    public int modifySeat(String shiftId, int reserveSeat){
        return shiftDao.updateReserveSeat(reserveSeat, shiftId);
    }




}
