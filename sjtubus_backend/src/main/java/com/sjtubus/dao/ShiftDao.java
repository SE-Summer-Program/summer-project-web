package com.sjtubus.dao;

import com.sjtubus.entity.Shift;
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

    @Query(value = "select shift from Shift shift where shift.line_type=:type and shift.line_name=:line_name")
    List<Shift> queryByLine_typeAndLine_name(@Param("type")String type,@Param("line_name")String line_name);



}
