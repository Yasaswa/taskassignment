package com.erp.Employee.Employee_Band.Repository;

import com.erp.Employee.Employee_Band.Model.CEmployeeBandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IEmployeeBandRepository extends JpaRepository<CEmployeeBandModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_employee_band set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where employee_band_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int employee_band_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_employee_band WHERE employee_band_name = ?1")
	CEmployeeBandModel getCheck(String employee_band_name);

}
