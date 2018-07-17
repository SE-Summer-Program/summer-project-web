package com.sjtubus.dao;

import com.sjtubus.entity.Shift;
import com.sjtubus.entity.projection.Linename;
import com.sjtubus.entity.projection.ShiftInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.Collection;
import java.util.List;

public interface ShiftDao extends JpaRepository<Shift,String> {
    @Query(value = "select distinct shift.lineNameCn from Shift shift where shift.lineType=:type")
    Collection<Linename> getLineNameByType(@Param("type")String type);

    @Query(value = "select departureTime,comment from Shift where lineType=:type and lineName=:line_name")
    List<ShiftInfo> getShiftInfoByTypeAndLinename(@Param("type")String type, @Param("line_name")String line_name);

    @Query(value = "select shift from Shift shift where shift.lineName=:lineName and shift.lineType=:lineType")
    List<Shift> queryByLinetypeAndLinename(@Param("lineType")String lineType,@Param("lineName")String lineName);

    @Query(value = "select shift from Shift shift where shift.lineName=:lineName and shift.lineType=:lineType and shift.departureTime=:departureTime")
    List<Shift> queryByLinetypeAndLinenameAndDepartureTime(@Param("lineType")String lineType, @Param("lineName")String lineName,@Param("departureTime") Time departureTime);

    @Query(value = "select shift from Shift shift where shift.shiftId=:shiftId")
    Shift queryShiftByShiftId(@Param("shiftId") String shiftId);

    @Query(value = "select departureTime from Shift where lineType=:lineType and lineNameCn=:lineNameCn")
    List<Time> getTimeListByLineNameCnAndLineType(@Param("lineType") String lineType, @Param("lineNameCn") String lineNameCn);

    @Transactional
    @Modifying
    @Query("update Shift shift set shift.reserveSeat = :reserveSeat where shift.shiftId =:shiftId")
    int updateReserveSeat(@Param("reserveSeat") int reserveSeat,@Param("shiftId") String shiftId);
}
