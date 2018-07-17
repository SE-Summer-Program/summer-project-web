package com.sjtubus.model;

public class RecordInfo {

    private String lineName;
    private String departureDate;
    private String departureTime; //预约的日期和时间
    private String shiftid;
    private String status;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getShiftid() {
        return shiftid;
    }

    public void setShiftid(String shiftid) { this.shiftid = shiftid; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
