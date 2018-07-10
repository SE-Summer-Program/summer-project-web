package com.sjtubus.dao;

import com.sjtubus.entity.RideBusInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideBusInfoDao extends JpaRepository<RideBusInfo,Integer> {
}
