package com.erp.HtMonthlyDeduction.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.HtMonthlyDeduction.Model.CHtMonthlyDeductionDeatilsModel;

public interface IHtMonthlyDeductionRepository extends JpaRepository<CHtMonthlyDeductionDeatilsModel, Integer> {

//	@Query(value = "FROM CHtMonthlyDeductionDeatilsModel model where model.is_delete =0 and model.monthly_deduction_master_transaction_id = ?1 and model.company_id = ?2")
//	List<CHtMonthlyDeductionDeatilsModel> FnShowParticularRecordForUpdate(int monthly_deduction_master_transaction_id, int company_id);

	@Query(value = "SELECT model.*, cel.department_name, cel.employee_name "
			+ "FROM ht_monthly_deduction_details model " + "LEFT JOIN cmv_employee_list cel "
			+ "ON model.employee_id = cel.employee_id " + "WHERE model.is_delete = 0 "
			+ "AND model.monthly_deduction_master_transaction_id = ?1 "
			+ "AND model.company_id = ?2", nativeQuery = true)
	List<Map<String, Object>> FnShowParticularRecordForUpdate(int monthly_deduction_master_transaction_id,
			int company_id);

	@Query(value = "FROM CHtMonthlyDeductionDeatilsModel model where model.employee_code IN ?1 and model.salary_month = ?2 and model.salary_year = ?3 and model.company_id = ?4 and is_delete = false")
	List<CHtMonthlyDeductionDeatilsModel> FnGetMonthlyDeductionsByEmplCodes(List<String> employeeCodes,
			String attendance_month, int attendance_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CHtMonthlyDeductionDeatilsModel model set model.is_delete = true, model.deleted_on = CURRENT_TIMESTAMP() where  model.deduction_type_id = ?1 and model.salary_year = ?2 and model.salary_month = ?3 and model.company_id = ?4")
	void updateStatus(int deductionTypeId, int salaryYear, String salaryMonth, int companyId);

	@Query(value = "FROM CHtMonthlyDeductionDeatilsModel model where model.is_delete =0 and model.deduction_type_id = ?1")
	Optional<CHtMonthlyDeductionDeatilsModel> findAllById(int deductionTypeId);

//	@Modifying
//	@Transactional
//	@Query(value = "Update CHtMonthlyDeductionDeatilsModel model set model.is_delete = 1, " +
//			"model.deleted_by = ?5, model.deleted_on = CURRENT_TIMESTAMP() where deduction_type_id= ?1 and salary_year = ?2 and salary_month = ?3 and company_id = ?4 ")
//	void deletePreviousRecords(Integer deduction_type_id, Integer salary_year, String salary_month, int company_id,
//							   String deleted_by, String employeeType);

	@Modifying
	@Transactional
	@Query(value = "Update CHtMonthlyDeductionDeatilsModel model set model.is_delete = 1, " +
			"model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() where model.monthly_deduction_master_transaction_id= ?1 and model.company_id = ?2 ")
	void deletePreviousRecords(Integer monthlyDeductionMasterTransactionId, int companyId, String createdBy);

	@Modifying
	@Transactional
	@Query(value = "Update CHtMonthlyDeductionDeatilsModel model set model.is_delete = 1, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() where model.monthly_deduction_master_transaction_id= ?1 and model.company_id = ?2 ")
	void deleteRecords(Integer monthly_deduction_master_transaction_id, int company_id, String deleted_by);

}
