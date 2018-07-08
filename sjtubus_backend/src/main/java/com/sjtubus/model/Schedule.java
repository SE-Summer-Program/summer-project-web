package com.sjtubus.model;

import java.util.ArrayList;

import java.util.List;

public class Schedule {

    private String lineName;
        //MinToXu, XuToMin, MinToQi, QiToMin

    private String types;
        //"NormalWorkday"，"NormalWeekendAndLegalHoilday"
        //"HoildayWorkday"，"HoildayWeekend"

    private String subtitle;

    private List<String> scheduleTime = new ArrayList<>();
    private List<String> scheduleComment = new ArrayList<>();

    public Schedule(String lineName,String types){
        initScheduleTime(types);

        this.lineName = lineName;
        this.types = types;
        //this.scheduleTime = ... 根据linename和types，只添加满足情况的schedule

        this.subtitle = "首班车：8:00，末车班：20:00"; //获取schedule数组的头和尾
    }

    private void initScheduleTime(String types){
        this.scheduleTime.add("8:00");
        this.scheduleTime.add("9:00");
        this.scheduleTime.add("20:00");
        this.scheduleComment.add("none");
        this.scheduleComment.add("none");
        this.scheduleComment.add("none");

        //在这里get不同类型的数据，并且赋值给scheduleTime
    }

    public String getLineName(){
        return lineName;
    } //等同于getTitle

    public String getTypes(){
        return types;
    }

    public String getSubtitle() { return subtitle; }

    public List<String> getScheduleTime(){ return scheduleTime; }

    public List<String> getScheduleComment(){ return scheduleComment; }

    public String getDetail() {
        String detail = "123";
        for (int i = 0; i < scheduleTime.size(); i++){
            detail.concat(scheduleTime.get(i));
            detail.concat(scheduleComment.get(i));
            detail.concat("\n");
        }
        return detail;
    } //实际应该使用getSchedule的，但是adapter那里传不进去list

}
