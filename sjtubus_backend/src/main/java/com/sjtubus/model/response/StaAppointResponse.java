package com.sjtubus.model.response;

import java.util.List;

public class StaAppointResponse extends HttpResponse {
    private List<Integer> statistics;

    public List<Integer> getStatistics() {
        return statistics;
    }

    public void setShiftList(List<Integer> statistics) {
        this.statistics = statistics;
    }
}
