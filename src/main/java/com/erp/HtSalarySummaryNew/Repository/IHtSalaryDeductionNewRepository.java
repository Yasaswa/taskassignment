package com.erp.HtSalarySummaryNew.Repository;


import com.erp.HtSalarySummary.Model.CHtSalaryDeductionModel;
import com.erp.HtSalarySummaryNew.Model.CHtSalaryDeductionNewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


public interface IHtSalaryDeductionNewRepository extends JpaRepository<CHtSalaryDeductionNewModel, Integer> {
	@Modifying
	@Transactional
	@Query(value = "Update CHtSalaryDeductionNewModel model set model.is_delete = 1, model.deleted_by = ?5, model.deleted_on = CURRENT_TIMESTAMP() where model.employee_id IN ?1 and model.salary_year = ?2 and model.salary_month = ?3 and model.company_id = ?4 ")
	int deletePreviousRecords(List<Integer> employee_ids, int salary_year, int salary_month, int company_id, String deleted_by);

	@Query(value = "SELECT model.*, hmded.deduction_head_name, hmded.calculation_type "
			+ "FROM ht_salary_deduction_new model " + "LEFT JOIN hm_deduction_heads hmded "
			+ "ON model.deduction_heads_id = hmded.deduction_heads_id " + "WHERE model.is_delete = 0 "
			+ "AND model.salary_transaction_id = ?1  AND model.company_id = ?2", nativeQuery = true)
	List<Map<String, Object>> FnGetMonthlyDeductionBySalarySummaryTransId(int salary_transaction_id, int company_id);
}
