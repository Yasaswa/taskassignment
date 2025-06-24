package com.erp.HtSalarySummary.Repository;


import com.erp.HtSalarySummary.Model.CHtSalarySummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.HtSalarySummary.Model.CHtSalaryEarningsModel;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


public interface IHtSalaryEarningsRepository extends JpaRepository<CHtSalaryEarningsModel, Integer> {

	@Query(value = "FROM CHtSalaryEarningsModel model where model.is_delete =0 and model.salary_earnings_id = ?1 and model.company_id = ?2")
	CHtSalaryEarningsModel FnShowParticularRecordForUpdate(int salary_earnings_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "Update CHtSalaryEarningsModel model set model.is_delete = 1, model.deleted_by = ?5, model.deleted_on = CURRENT_TIMESTAMP() where model.employee_id IN ?1 and model.salary_year = ?2 and model.salary_month = ?3 and model.company_id = ?4 ")
	int deletePreviousRecords(List<Integer> employee_ids, int salary_year, int salary_month, int company_id, String deleted_by);

//	@Query(value = "FROM CHtSalaryEarningsModel model where model.is_delete =0 and model.salary_transaction_id = ?1 and model.company_id = ?2")
//	List<CHtSalaryEarningsModel> FnGetMonthlyEarningBySalarySummaryTransId(int salary_transaction_id, int company_id);
	@Query(value = "SELECT model.*, hmern.earning_head_name, hmern.calculation_type "
			+ "FROM ht_salary_earnings model " + "LEFT JOIN hm_earning_heads hmern "
			+ "ON model.earning_heads_id = hmern.earning_heads_id " + "WHERE model.is_delete = 0 "
			+ "AND model.salary_transaction_id = ?1  AND model.company_id = ?2", nativeQuery = true)
	List<Map<String, Object>> FnGetMonthlyEarningBySalarySummaryTransId(int salary_transaction_id, int company_id);

	@Query(value = "FROM CHtSalaryEarningsModel model where model.is_delete =0 and model.salary_transaction_id IN ?1 and model.company_id = ?2")
	List<CHtSalaryEarningsModel> FnGetSalaryEarningsBySalSummaryTransIds(List<Integer> salary_transaction_ids, int company_id);

	@Query(value = "FROM CHtSalaryEarningsModel model where model.is_delete =0 and model.employee_type = ?1 and model.salary_month = ?2 and model.salary_year = ?3 and model.company_id = ?4")
	List<CHtSalaryEarningsModel> FnGetSalaryEarnings(String employee_type, int salary_month, int salary_year, int company_id);
}
