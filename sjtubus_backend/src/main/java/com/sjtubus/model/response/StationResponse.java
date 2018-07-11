package com.sjtubus.model.response;

import com.sjtubus.model.Station;

import java.util.ArrayList;
import java.util.List;

public class StationResponse extends HttpResponse {
    private List<String> stations;

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }
}
