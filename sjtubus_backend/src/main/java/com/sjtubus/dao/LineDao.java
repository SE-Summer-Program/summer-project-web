package com.sjtubus.dao;

import com.sjtubus.entity.Line;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LineDao extends MongoRepository<Line,String> {
	Line findByName(String name);
}