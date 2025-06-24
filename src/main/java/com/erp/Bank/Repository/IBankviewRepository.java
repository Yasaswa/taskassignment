package com.erp.Bank.Repository;

import com.erp.Bank.Model.CBankViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IBankviewRepository extends JpaRepository<CBankViewModel, Long> {

	@Query(value = "Select * FROM cmv_banks where is_delete =0 order by bank_id Desc", nativeQuery = true)
	List<CBankViewModel> FnShowAllRecords();

	@Query(value = "Select * FROM cmv_banks where is_delete =0 and company_id = ?1 order by bank_id Desc", nativeQuery = true)
	Page<CBankViewModel> FnShowAllActiveRecords(int company_id, Pageable pageable);

	@Query(value = "Select * FROM cmv_banks where is_delete =0 and company_id = ?1", nativeQuery = true)
	Page<CBankViewModel> FnShowParticularRecord(int company_id, Pageable pageable);

	@Query(value = "Select * FROM cmv_banks where is_delete =0 and bank_id = ?1 and company_id = ?2", nativeQuery = true)
	CBankViewModel FnShowParticularRecordForUpdate(int bank_id, int company_id);

	@Query(value = "SELECT * FROM  cmv_banks_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
