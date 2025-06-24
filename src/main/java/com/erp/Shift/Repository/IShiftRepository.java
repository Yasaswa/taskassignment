package com.erp.Shift.Repository;

import com.erp.HmManualAttendance.Model.CHmManualAttendanceModel;
import com.erp.Shift.Model.CShiftModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

public interface IShiftRepository extends JpaRepository<CShiftModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_shift set is_delete = 1, deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where shift_id=?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int shift_id, int company_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_shift WHERE shift_name = ?1 and company_id = ?2 and is_delete=0")
	CShiftModel getCheck(String shift_name, Integer company_id);
	
	@Query(value = "SELECT cs.start_time, cs.end_time, cs.two_day_shift FROM CShiftModel cs WHERE cs.shift_id IN (SELECT el.shift_id FROM CEmployeesListViewModel el WHERE el.employee_code = ?1)")
	Map<String, Object> getEmployeeShiftDetails(String employeeCode);

	@Query(value = "FROM CShiftModel model where model.is_delete = false")
	List<CShiftModel> getAllShiftDetails();


//	@Query(value = "SELECT cs.start_time, cs.end_time, cs.two_day_shift FROM CShiftModel cs WHERE cs.shift_id IN (SELECT el.shift_id FROM CEmployeesListViewModel el WHERE el.employee_code IN ?1)")
//	Map<String, Object> getEmployeeShiftDetails(List<String> employeeCodesList);
}
