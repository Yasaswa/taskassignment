package com.erp.HtMonthlyDeduction.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.HtMonthlyDeduction.Model.CHtMonthlyDeductionMasterModel;


public interface IHtMonthlyDeductionMasterRepository extends JpaRepository<CHtMonthlyDeductionMasterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "Update CHtMonthlyDeductionMasterModel model set model.is_delete = 1, model.deleted_by = ?6, model.deleted_on = CURRENT_TIMESTAMP() " +
			"where model.deduction_type_id= ?1 and model.salary_year = ?2 and model.salary_month = ?3 and model.employee_type = ?4 and model.company_id = ?5 ")
	void deletePreviousRecords(Integer deduction_type_id, Integer salary_year, String salary_month, String employeeType, int company_id, String deleted_by);
	
	@Query(value = "FROM CHtMonthlyDeductionMasterModel model where model.is_delete =0 and model.monthly_deduction_master_transaction_id = ?1 and model.company_id = ?2")
	CHtMonthlyDeductionMasterModel FnShowParticularRecordForUpdate(int monthly_deduction_master_transaction_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "Update CHtMonthlyDeductionMasterModel model set model.is_delete = 1, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() where model.monthly_deduction_master_transaction_id= ?1 and model.company_id = ?2 ")
	void deleteRecords(Integer monthly_deduction_master_transaction_id, int company_id, String deleted_by);
}
