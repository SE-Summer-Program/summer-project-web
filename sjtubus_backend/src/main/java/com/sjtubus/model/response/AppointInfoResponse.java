package com.sjtubus.model.response;

import com.sjtubus.model.AppointInfo;

import java.util.List;

public class AppointInfoResponse {

    private List<AppointInfo> appointmentList;

    public List<AppointInfo> getAppointInfos() {
        return appointmentList;
    }

    public void setAppointment(List<AppointInfo> appointmentList) {
        this.appointmentList = appointmentList;
    }

}
