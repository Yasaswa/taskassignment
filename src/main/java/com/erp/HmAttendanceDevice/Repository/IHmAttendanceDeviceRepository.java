package com.erp.HmAttendanceDevice.Repository;

import com.erp.HmAttendanceDevice.Model.CHmAttendanceDeviceModel;
import com.erp.HmAttendanceDevice.Model.CHmattendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


public interface IHmAttendanceDeviceRepository extends JpaRepository<CHmAttendanceDeviceModel, Integer> {

	@Query(value = "FROM CHmAttendanceDeviceModel model where model.is_delete =0 and model.attendance_device_name = ?1")
	CHmAttendanceDeviceModel checkIfNameExist(String attendance_device_name);

	@Modifying
	@Transactional
	@Query(value = "update hm_attendance_device set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where attendance_device_id = ?1", nativeQuery = true)
	void FnDeleteRecord(int attendance_device_id, String deleted_by);


	@Query(value = "FROM CHmAttendanceDeviceModel model where model.is_delete = 0 and model.company_id = ?1 and model.attendance_device_id = ?2")
	CHmAttendanceDeviceModel FnShowParticularRecordForUpdate(int company_id, int attendance_device_id);


//	@Query("SELECT model FROM CHmattendanceLog model WHERE model.att_device_emp_code = ?1")
//	Iterable<CHmattendanceLog> findAllLogsByEmployeeCode(String attDeviceEmpCode);

	@Query("SELECT model FROM CHmattendanceLog model WHERE model.att_device_emp_code = :empCode AND " +
			"DATE(model.att_date_time) = :attDateTime " +
			"AND (model.att_date_time = (SELECT MIN(m.att_date_time) FROM CHmattendanceLog m WHERE m.att_device_emp_code = :empCode AND DATE(m.att_date_time) = :attDateTime) " +
			"OR model.att_date_time = (SELECT MAX(m.att_date_time) FROM CHmattendanceLog m WHERE m.att_device_emp_code = :empCode AND DATE(m.att_date_time) = :attDateTime))")
	List<CHmattendanceLog> findFirstAndLastLogsByEmployeeCodeAndToday(@Param("empCode") String empCode, @Param("attDateTime") Date attDateTime);

}