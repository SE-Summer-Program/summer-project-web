package com.sjtubus.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringCalendarUtils {

    public static java.util.Date StringToDate(String datestr){
        java.util.Date date= new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("date:"+date);
        return date;
    }

    public static java.util.Date StringToTime(String timestr){
        java.util.Date date= new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = dateFormat.parse(timestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String DateToString(java.util.Date date){
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
        java.util.Date date = new java.util.Date();
        java.util.Date current = new java.util.Date();

        try {
            date = timeFormat.parse(datetime);
            current = timeFormat.parse(timeFormat.format(new java.util.Date()));
        } catch (Exception e){
            e.printStackTrace();
        }

        return date.before(current);
    }

    public static String getCurrrentDate(){
        String current_date="";
        java.util.Date date = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        current_date=simpleDateFormat.format(date);
        return current_date;
    }

    /* java.util.date转化成java.sql.date */
    public static java.sql.Date UtilDateToSqlDate(java.util.Date util_date){
        return new java.sql.Date(util_date.getTime());
    }

    /* java.sql.date转化成java.util.date */
    public static java.util.Date SqlDateToUtilDate(java.sql.Date sql_date){
        return new Date(sql_date.getTime());
    }

}
