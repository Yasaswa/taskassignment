package com.erp.Employee.Employee_Band.Repository;

import com.erp.Employee.Employee_Band.Model.CEmployeeBandViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmployeeBandViewRepository extends JpaRepository<CEmployeeBandViewModel, Long> {
	@Query(value = "Select * FROM  cmv_employee_band order by employee_band_id Desc", nativeQuery = true)
	Page<CEmployeeBandViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "FROM CEmployeeBandViewModel EMV where EMV.is_delete = 0  order by EMV.employee_band_id Desc")
	Page<CEmployeeBandViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_employee_band where is_delete =0 and employee_band_id = ?1", nativeQuery = true)
	CEmployeeBandViewModel FnShowParticularRecordForUpdate(int employee_band_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_employee_band where is_delete =0 and company_id = ?1 and employee_band_id = ?2")
	CEmployeeBandViewModel FnShowParticularRecord(int company_id, int employee_band_id);


}
