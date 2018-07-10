package com.sjtubus.entity;

import java.util.List;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "timeTable")
public class TimeTable {
	
	public TimeTable() {}
	
    @Id
    private String id ;
    private String station;
    private String type;
    private List<String> loopline;
    private List<String> nonloopline;
    
    public String getId() {
    	return this.id;
    }
    public void setId(String id){
    	this.id = id;
    }
    public String getStation() {
    	return this.station;
    }
    public void setStation(String station) {
    	this.station = station;
    }
    public String getType() {
    	return this.type;
    }
    public void setType(String type) {
    	this.type = type;
    }
    public List<String> getLoopline() {
    	return this.loopline;
    }
    public void setLoopline(List<String> loopline) {
    	this.loopline = loopline;
    }
    public List<String> getNonloopline() {
    	return this.nonloopline;
    }
    public void setNonloopline(List<String> nonloopline) {
    	this.nonloopline = nonloopline;
    }
}
