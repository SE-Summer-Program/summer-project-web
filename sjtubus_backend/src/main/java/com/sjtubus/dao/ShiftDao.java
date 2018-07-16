package com.sjtubus.dao;

import com.sjtubus.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShiftDao extends JpaRepository<Shift,String> {
    List<Shift> findByLineTypeAndLineNameOrderByDepartureTime(String type,String line_name);

    Shift findByShiftId(String shiftId);
}
