package com.erp.FinancialYear.Repository;

import com.erp.FinancialYear.Model.CFinancialYearViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IFinancialYearViewRepository extends JpaRepository<CFinancialYearViewModel, Integer> {


	@Query(value = "Select * FROM  amv_financial_year where is_delete =0  order by financial_year_id Desc", nativeQuery = true)
	Page<CFinancialYearViewModel> FnShowAllActiveRecords(Pageable pageable);


	@Query(value = "Select * FROM  amv_financial_year order by financial_year_id Desc", nativeQuery = true)
	Page<CFinancialYearViewModel> FnShowAllRecords(Pageable pageable);

	@Query(nativeQuery = true, value = "Select * FROM  amv_financial_year where is_delete =0 and company_id = ?1 and financial_year_id = ?2")
	CFinancialYearViewModel FnShowParticularRecord(int company_id, int financial_year_id);

	@Query(value = "Select * FROM  amv_financial_year where is_delete =0 and financial_year_id = ?1", nativeQuery = true)
	CFinancialYearViewModel FnShowParticularRecordForUpdate(int financial_year_id);

	@Query(value = "SELECT * FROM amv_financial_year", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);


}
