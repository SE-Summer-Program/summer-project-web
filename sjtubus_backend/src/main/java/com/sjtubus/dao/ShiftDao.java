package com.sjtubus.dao;

import com.sjtubus.entity.Shift;
import com.sjtubus.model.ShiftInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.List;

public interface ShiftDao extends JpaRepository<Shift,String> {
    @Query(value = "select distinct line_name from Shift where type=:type")
    List<Shift> getLineNameByType(@Param("type")String type);

    @Query(value = "select  min(departure_time) from Shift where type=:type and line_name=:line_name")
    Shift getFirstTimeByLineNameAndType(@Param("line_name")String line_name,@Param("type")String type);

    @Query(value = "select  max(departure_time) from Shift where type=:type and line_name=:line_name")
    Shift getLastTimeByLineNameAndType(@Param("line_name")String line_name,@Param("type")String type);

    @Query(value = "select departure_time,comment from Shift where type=:type and line_name=:line_name")
    List<ShiftInfo> getShiftInfoByTypeAndLine_name(@Param("type")String type,@Param("line_name")String line_name);

}
