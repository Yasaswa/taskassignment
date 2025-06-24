package com.erp.Agents.Agent_Bank.Repository;

import com.erp.Agents.Agent_Bank.Model.CAgentBankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IAgentBankRepository extends JpaRepository<CAgentBankModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_agent_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where agent_bank_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int agent_bank_id);

//	@Modifying
//  @Transactional
//  @Query(value = "update cm_agent_banks set is_delete = 1, deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where agent_bank_id=?1 and company_id = ?2" , nativeQuery = true)
//	Object FnDeleteRecord(int agent_bank_id, int company_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_agent_banks WHERE agent_bank_account_type = ?1")
	CAgentBankModel getCheck(String agent_bank_account_type);


	@Modifying
	@Transactional
	@Query(value = "update cm_agent_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id=?1 and agent_id = ?2 and is_delete = 0", nativeQuery = true)
	Object updateAgentBankActiveStatus(int company_id, int agent_id);

	@Query(value = "FROM CAgentBankModel model where model.is_delete = 0 AND model.company_id = ?1 AND model.agent_id = ?2")
	List<CAgentBankModel> FnShowParticularRecord(int company_id, int agent_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_agent_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where agent_id=?2 and company_id = ?3", nativeQuery = true)
	Object FnDeleteAgentBanksRecord(String deleted_by, int agent_id, int company_id);
}
