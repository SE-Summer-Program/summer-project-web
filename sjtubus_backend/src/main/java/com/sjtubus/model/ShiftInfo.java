package com.sjtubus.model;

import java.sql.Time;

public class ShiftInfo {
    private Time startTime;
    private String comment;

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
