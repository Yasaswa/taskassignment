package com.erp.HtSalarySummaryNew.Repository;

import com.erp.HtSalarySummary.Model.CHtSalarySummaryModel;
import com.erp.HtSalarySummaryNew.Model.CHtSalarySummaryNewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IHtSalarySummaryNewRepository extends JpaRepository<CHtSalarySummaryNewModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "Update CHtSalarySummaryNewModel model set model.is_delete = 1, model.deleted_by = ?5, model.deleted_on = CURRENT_TIMESTAMP() where model.employee_id IN ?1 and model.salary_year = ?2 and model.salary_month = ?3 and model.company_id = ?4 ")
	int deletePreviousRecords(List<Integer> employee_ids, int salary_year, int salary_month, int company_id, String deleted_by);

}
