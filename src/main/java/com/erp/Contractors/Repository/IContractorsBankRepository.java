package com.erp.Contractors.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.Agents.Agent_Bank.Model.CAgentBankModel;
import com.erp.Contractors.Model.CContractorBankModel;
import com.erp.Contractors.Model.CContractorBankViewModel;

public interface IContractorsBankRepository extends JpaRepository<CContractorBankModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_contractor_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id = ?1 and contractor_id = ?2 and is_delete = 0", nativeQuery = true)
	void updateContarctorBankActiveStatus(int company_id, int contractor_id);

	@Query(value = "FROM CContractorBankViewModel model where model.is_delete = 0 AND model.company_id = ?1 AND model.contractor_id = ?2")
	List<CContractorBankViewModel> FnShowParticularRecord(int company_id, int contractor_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_contractor_banks set is_delete = 1, deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where  contractor_id=?1 ", nativeQuery = true)
	void fnDeleteContractorBankRecord( int contractor_id , String deleted_by);
	
}
