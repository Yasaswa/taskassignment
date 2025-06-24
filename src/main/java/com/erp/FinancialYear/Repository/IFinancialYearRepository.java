package com.erp.FinancialYear.Repository;

import com.erp.FinancialYear.Model.CFinancialYearModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IFinancialYearRepository extends JpaRepository<CFinancialYearModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update am_financial_year set is_delete = 1, deleted_by=?2, deleted_on = CURRENT_TIMESTAMP()  where financial_year_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int financial_year_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM am_financial_year WHERE (financial_year = ?1 or (?2 IS NOT NULL AND short_name = ?2)) and company_id = ?3")
	CFinancialYearModel getCheck(String financial_year, String short_name, Integer company_id);

//	@Query(nativeQuery = true, value = "SELECT * FROM am_financial_year WHERE ( financial_year = ?1 or short_name =?2 or short_year = ?3 ) and company_id = ?4 and is_delete = 0 ")
//	CFinancialYearModel getCheck(String financial_year, String short_name, String short_year, Integer company_id);

}
