package com.erp.Contractors.Repository;

import com.erp.Agents.Agent.Model.CAgentModel;
import com.erp.Contractors.Model.CContractorsModel;
import com.erp.Contractors.Model.CContractorsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface IContractorsViewRepository extends JpaRepository<CContractorsViewModel, Long> {

	@Query(value = "Select * FROM  cmv_contractor order by contractor_id Desc", nativeQuery = true)
	Page<CContractorsViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_contractor where is_delete =0  order by contractor_id Desc", nativeQuery = true)
	Page<CContractorsViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_contractor where is_delete = 0 and contractor_id = ?1", nativeQuery = true)
	CContractorsViewModel FnShowParticularRecordForUpdate(int contractor_id);


//	@Query(nativeQuery = true, value = "Select * FROM  cmv_contractor where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and contractor_id = ?3")
//	CContractorsViewModel FnShowParticularRecord(int company_id, int company_branch_id);
	
	@Query(value = "FROM CContractorsViewModel model where model.company_id = ?1 and model.contractor_id = ?2")
	CContractorsViewModel FnShowParticularRecord(int company_id, int contractor_id);
	
	
	
	@Query(value = "SELECT * FROM cmv_contractor_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
