package com.sjtubus.controller;

import com.sjtubus.model.LineInfo;
import com.sjtubus.model.response.LineInfoResponse;
import com.sjtubus.model.response.StationResponse;
import com.sjtubus.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/line")
public class LineController {
    @Autowired
    LineService lineService;

    /**
     * @description: 根据type获取一系列lineinfo
     * @date: 2018/7/10 19:40
     * @params: type - 线路类别
     * @return: LineInfoResponse
    */
    @RequestMapping(value = "/infos")
    public LineInfoResponse getLineInfo(String type){
        LineInfoResponse response = new LineInfoResponse();
        List<String> names = lineService.getAllLineName(type);
        List<LineInfo> lines = new ArrayList<>();
        for(String name:names){
            LineInfo info = lineService.getLineInfo(type,name);
            if(info!=null) lines.add(info);
        }
        response.setLineInfos(lines);
        return response;
    }

    /**
     * @description: 根据line_name获取一系列站点名
     * @date: 2018/7/10 18:18
     * @params: line_name - 线路名称
     * @return: StationResponse
    */
    @RequestMapping(value = "/stations")
    public StationResponse getStations(String line_name){
        StationResponse response = new StationResponse();
        List<String> stations = lineService.getStationByLineName(line_name);
        if(stations == null || stations.size()==0){
            response.setError(1);
            response.setMsg("站点信息获取失败");
        }
        response.setStations(stations);
        return response;
    }
}
