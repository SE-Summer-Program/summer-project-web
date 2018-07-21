package com.sjtubus.model.response;

import com.sjtubus.entity.TimeTable;
import com.sjtubus.model.Station;

import java.util.ArrayList;
import java.util.List;

public class StationResponse extends HttpResponse {
    private List<TimeTable> stations;

    public List<TimeTable> getStations() {
        return stations;
    }

    public void setStations(List<TimeTable> stations) {
        this.stations = stations;
    }
}
