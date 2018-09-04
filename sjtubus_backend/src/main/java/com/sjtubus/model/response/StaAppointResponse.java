package com.sjtubus.model.response;

import java.util.List;

public class StaAppointResponse extends HttpResponse {
    private List<Integer> statistics;

    public List<Integer> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Integer> statistics) {
        this.statistics = statistics;
    }
}
