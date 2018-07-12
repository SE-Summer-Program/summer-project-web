package com.sjtubus.service;

import com.sjtubus.dao.ShiftDao;
import com.sjtubus.entity.Shift;
import com.sjtubus.entity.projection.Linename;
import com.sjtubus.entity.projection.ShiftInfo;
import com.sjtubus.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftDao shiftDao;


    public List<String> getAllLineName(String type){
        List<String> result = new ArrayList<>();
        Collection<Linename> names = shiftDao.getLineNameByType(type);
        for (Linename linename:names){
            String line_name = linename.getLineNameCn();
            result.add(line_name);
        }
        return result;
    }


    public List<ShiftInfo> getSortedShiftInfo(String type, String line_name){
        List<ShiftInfo> result = shiftDao.getShiftInfoByTypeAndLinename(type,line_name);
        return result;
    }

/**
 * @description: 根据type和linename得到相应的时刻表，返回一个schedule对象
 * @date: 2018/7/10 10:16
 * @params:
 * @return:
*/
    public Schedule getSchedule(String lineType, String lineName){
        List<Shift> shiftInfo = shiftDao.queryByLinetypeAndLinename(lineType, lineName);
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
        Schedule result = new Schedule(lineName, lineType);
        result.setLineName(lineName);
        result.setTypes(lineType);
        result.setScheduleTime(startTimeList);
        result.setScheduleComment(commentList);
        result.setScheduleShift(shiftidList);
        return result;
    }


    /**
     * @description: 管理员通过该添加班次，先根据参数解析成id，然后存入数据库，返回一个字符串
     * @date: 2018/7/11 23:28
     * @params:
     * @return:
    */
    public String addShift(String lineName, String lineNameCn, String lineType, Time departureTime, int reserveSeat, String comment){
        //生成对应的id
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
        String time = tempHour + tempMinute;
        System.out.println(time);
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
            if (lineType.equals("NormalWorkday"))
                shiftid = shiftid + "WD";
            else if (lineType.equals("NormalWeekendAndLegalHoliday"))
                shiftid = shiftid + "WE";
            else if (lineType.equals("HolidayWorkday"))
                shiftid = shiftid + "HD";
            else
                shiftid = shiftid + "HE";
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


    public List<Shift> searchShift(String content){
        List<Shift> shiftInfo = shiftDao.findAll();
        List<Shift> resultList = new ArrayList<>();
        int size = shiftInfo.size();
        for (int i = 0; i < size; i++ ){
            Shift shift = shiftInfo.get(i);
            if (shift.getShiftId().contains(content) || shift.getLineNameCn().contains(content) ||String.valueOf(shift.getDepartureTime()).contains(content)
                    ||shift.getComment().contains(content) || shift.getLineType().contains(content)){
                resultList.add(shift);
               // System.out.println(shift.getShiftId());
            }
        }
        System.out.println("resultListSize:"+ resultList.size());
        return resultList;
    }


    public String deleteShift(String shiftId){
        Shift oldShift = shiftDao.queryShiftByShiftId(shiftId);
        shiftDao.delete(oldShift);
        return "success";
    }



    //    public List<ShiftInfo> getShiftInfo(String type){
//        List<ShiftInfo> results = new ArrayList<>();
//        List<Shift> shifts = shiftDao.getLineNameByType(type);
//        for(Shift shift:shifts){
//            String line_name = shift.getLineName();
//            Shift first_shift = shiftDao.getFirstTimeByLineNameAndType(line_name,type);
//            Shift last_shift = shiftDao.getLastTimeByLineNameAndType(line_name,type);
//            ShiftInfo info = new ShiftInfo();
//            info.setStart_time(first_shift.getDepartureTime());
//            info.setEnd_time(last_shift.getDepartureTime());
//            info.setLine_name(line_name);
//            results.add(info);
//        }
//        return results;
//    }
}
