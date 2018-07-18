package com.sjtubus.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringCalendarUtils {

    public static Date StringToDate(String datestr){
        Date date= new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("date:"+date);
        return date;
    }

    public static String DateToString(Date date){
//        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String date_str;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date_str = sdf.format(date);
        return date_str;
    }

    public static String TimeToString(Time time){
        String time_str;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        time_str = sdf.format(time);
        return time_str;
    }

    /**
     * @description: 比较某个时间和现在时间的先后
     * @date: 2018/07/16 13:57
     * @params: string time1， string time2
     * @return: 如果time1在time2之前，返回true
     */
    public static boolean isBeforeCurrentTime(String datetime) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Date current = new Date();

        try {
            date = timeFormat.parse(datetime);
            current = timeFormat.parse(timeFormat.format(new Date()));
        } catch (Exception e){
            e.printStackTrace();
        }
        if (date.before(current))
            return true;
        else
            return false;
    }

    public static String getCurrrentDate(){
        String current_date="";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        current_date=simpleDateFormat.format(date);
        return current_date;
    }

}
