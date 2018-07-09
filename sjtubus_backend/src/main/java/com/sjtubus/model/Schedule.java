package com.sjtubus.model;

import java.util.ArrayList;

import java.util.List;

public class Schedule {

    private String lineName;
        //MinToXu, XuToMin, MinToQi, QiToMin

    private String types;
        //"NormalWorkday"，"NormalWeekendAndLegalHoilday"
        //"HoildayWorkday"，"HoildayWeekend"

    private List<String> scheduleShift = new ArrayList<>();
    private List<String> scheduleTime = new ArrayList<>();
    private List<String> scheduleComment = new ArrayList<>();

    public Schedule(String lineName,String types){
        initScheduleTime(types);
        this.setLineName(lineName);
        this.setTypes(types);
        //this.scheduleTime = ... 根据linename和types，只添加满足情况的schedule


    }

    private void initScheduleTime(String types){
        this.getScheduleTime().add("8:00");
        this.getScheduleTime().add("9:00");
        this.getScheduleTime().add("20:00");
        this.getScheduleComment().add("none");
        this.getScheduleComment().add("none");
        this.getScheduleComment().add("none");

        //在这里get不同类型的数据，并且赋值给scheduleTime
    }

    public String getLineName(){
        return lineName;
    } //等同于getTitle

    public String getTypes(){
        return types;
    }



    public List<String> getScheduleTime(){ return scheduleTime; }

    public List<String> getScheduleComment(){ return scheduleComment; }

    public String getDetail() {
        String detail = "123";
        for (int i = 0; i < getScheduleTime().size(); i++){
            detail.concat(getScheduleTime().get(i));
            detail.concat(getScheduleComment().get(i));
            detail.concat("\n");
        }
        return detail;
    } //实际应该使用getSchedule的，但是adapter那里传不进去list

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setScheduleTime(List<String> scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setScheduleComment(List<String> scheduleComment) {
        this.scheduleComment = scheduleComment;
    }

    public List<String> getScheduleShift() {
        return scheduleShift;
    }

    public void setScheduleShift(List<String> scheduleShift) {
        this.scheduleShift = scheduleShift;
    }
}
