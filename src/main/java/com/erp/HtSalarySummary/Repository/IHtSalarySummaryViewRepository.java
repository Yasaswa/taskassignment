package com.erp.HtSalarySummary.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.HtSalarySummary.Model.CHtSalarySummaryViewModel;

import java.util.List;


public interface IHtSalarySummaryViewRepository extends JpaRepository<CHtSalarySummaryViewModel, Integer> {

	@Query(value = "FROM CHtSalarySummaryViewModel model where model.is_delete =0 and model.salary_transaction_id = ?1 and model.company_id = ?2")
    CHtSalarySummaryViewModel FnShowParticularRecordForUpdate(int salary_transaction_id, int company_id);

    @Query(value = "FROM CHtSalarySummaryViewModel model where model.is_delete =0 and model.salary_transaction_id IN ?1 and model.company_id = ?2")
    List<CHtSalarySummaryViewModel> FnGetSalarySummaryBySalSummaryTransIds(List<Integer> salary_transaction_ids, int company_id);

    @Query(value = "FROM CHtSalarySummaryViewModel model where model.is_delete =0 and model.employee_type = ?1 and model.salary_month = ?2 and model.salary_year = ?3 and model.company_id = ?4")
    List<CHtSalarySummaryViewModel> FnGetSalarySummaries(String employee_type, int salary_month, int salary_year, int company_id);

}
