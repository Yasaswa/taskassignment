package com.erp.HtSalarySummary.Repository;


import com.erp.HtSalarySummary.Model.CHtSalaryEarningsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.HtSalarySummary.Model.CHtSalaryDeductionModel;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


public interface IHtSalaryDeductionRepository extends JpaRepository<CHtSalaryDeductionModel, Integer> {

	@Query(value = "FROM CHtSalaryDeductionModel model where model.is_delete =0 and model.salary_deduction_id = ?1 and model.company_id = ?2")
	CHtSalaryDeductionModel FnShowParticularRecordForUpdate(int salary_deduction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "Update CHtSalaryDeductionModel model set model.is_delete = 1, model.deleted_by = ?5, model.deleted_on = CURRENT_TIMESTAMP() where model.employee_id IN ?1 and model.salary_year = ?2 and model.salary_month = ?3 and model.company_id = ?4 ")
	int deletePreviousRecords(List<Integer> employee_ids, int salary_year, int salary_month, int company_id, String deleted_by);

//	@Query(value = "FROM CHtSalaryDeductionModel model where model.is_delete =0 and model.salary_transaction_id = ?1 and model.company_id = ?2")
//	List<CHtSalaryDeductionModel> FnGetMonthlyDeductionBySalarySummaryTransId(int salaryTransactionId, int companyId);
		@Query(value = "SELECT model.*, hmded.deduction_head_name, hmded.calculation_type "
			+ "FROM ht_salary_deduction model " + "LEFT JOIN hm_deduction_heads hmded "
			+ "ON model.deduction_heads_id = hmded.deduction_heads_id " + "WHERE model.is_delete = 0 "
			+ "AND model.salary_transaction_id = ?1  AND model.company_id = ?2", nativeQuery = true)
	List<Map<String, Object>> FnGetMonthlyDeductionBySalarySummaryTransId(int salary_transaction_id, int company_id);


	@Query(value = "FROM CHtSalaryDeductionModel model where model.is_delete =0 and model.salary_transaction_id IN ?1 and model.company_id = ?2")
	List<CHtSalaryDeductionModel> FnGetSalaryDeductionsBySalSummaryTransIds(List<Integer> salary_transaction_ids, int company_id);

	@Query(value = "FROM CHtSalaryDeductionModel model where model.is_delete =0 and model.employee_type = ?1 and model.salary_month = ?2 and model.salary_year = ?3 and model.company_id = ?4")
	List<CHtSalaryDeductionModel> FnGetSalaryDeductions(String employee_type, int salary_month, int salary_year, int company_id);}
