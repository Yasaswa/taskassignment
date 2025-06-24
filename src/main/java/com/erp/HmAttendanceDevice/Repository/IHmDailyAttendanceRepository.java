package com.erp.HmAttendanceDevice.Repository;


import com.erp.HmAttendanceDevice.Model.CHmDailyAttendanceProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface IHmDailyAttendanceRepository extends JpaRepository<CHmDailyAttendanceProcess, Integer> {
    @Query("FROM CHmDailyAttendanceProcess model WHERE model.is_delete = 0 AND model.employee_code = ?1 AND model.att_date_time = ?2")
    CHmDailyAttendanceProcess checkIfNameExist(String employeeCode, Date attendanceDate);
}
