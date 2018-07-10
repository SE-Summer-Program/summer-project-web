package com.sjtubus.dao;

import com.sjtubus.entity.Shift;
import com.sjtubus.entity.projection.Linename;
import com.sjtubus.entity.projection.ShiftInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ShiftDao extends JpaRepository<Shift,String> {
    @Query(value = "select distinct shift.lineNameCn from Shift shift where shift.lineType=:type")
    Collection<Linename> getLineNameByType(@Param("type")String type);

    @Query(value = "select departureTime,comment from Shift where lineType=:type and lineName=:line_name")
    List<ShiftInfo> getShiftInfoByTypeAndLinename(@Param("type")String type, @Param("line_name")String line_name);


//    @Query(value = "select  min(departure_time) from Shift where type=:type and line_name=:line_name")
//    Shift getFirstTimeByLineNameAndType(@Param("line_name")String line_name,@Param("type")String type);
//
//    @Query(value = "select  max(departure_time) from Shift where type=:type and line_name=:line_name")
//    Shift getLastTimeByLineNameAndType(@Param("line_name")String line_name,@Param("type")String type);

}
