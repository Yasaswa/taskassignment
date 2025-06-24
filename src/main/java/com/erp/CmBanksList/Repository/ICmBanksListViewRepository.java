package com.erp.CmBanksList.Repository;

import com.erp.CmBanksList.Model.CCmBanksListViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface ICmBanksListViewRepository extends JpaRepository<CCmBanksListViewModel, Integer> {


	@Query(value = "FROM CCmBanksListViewModel model where model.is_delete =0 and model.bank_id = ?1 and model.company_id = ?2 order by model.bank_id Desc")
	Page<CCmBanksListViewModel> FnShowParticularRecord(int bank_id, int company_id, Pageable pageable);

	@Query(value = "Select * FROM cmv_banks_List where is_delete =0 and company_id = ?1 order by bank_id Desc", nativeQuery = true)
	Page<CCmBanksListViewModel> FnShowAllActiveRecords(int company_id, Pageable pageable);

	@Query(value = "select * FROM cmv_banks_List_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);


}
