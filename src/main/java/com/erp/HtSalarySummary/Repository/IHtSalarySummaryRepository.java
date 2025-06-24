package com.erp.HtSalarySummary.Repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.HtSalarySummary.Model.CHtSalarySummaryModel;


public interface IHtSalarySummaryRepository extends JpaRepository<CHtSalarySummaryModel, Integer> {

	@Query(value = "FROM CHtSalarySummaryModel model where model.is_delete =0 and model.salary_transaction_id = ?1 and model.company_id = ?2")
	CHtSalarySummaryModel FnShowParticularRecordForUpdate(int salary_transaction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "Update CHtSalarySummaryModel model set model.is_delete = 1, model.deleted_by = ?5, model.deleted_on = CURRENT_TIMESTAMP() where model.employee_id IN ?1 and model.salary_year = ?2 and model.salary_month = ?3 and model.company_id = ?4 ")
	int deletePreviousRecords(List<Integer> employee_ids, int salary_year, int salary_month, int company_id, String deleted_by);

}
