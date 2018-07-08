package com.sjtubus.model.response;

import java.util.List;

public class LineNameResponse extends HttpResponse {
    private List<String> linenames;

    public List<String> getLinenames() {
        return linenames;
    }

    public void setLinenames(List<String> linenames) {
        this.linenames = linenames;
    }
}
