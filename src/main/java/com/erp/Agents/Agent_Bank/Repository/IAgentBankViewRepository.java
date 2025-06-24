package com.erp.Agents.Agent_Bank.Repository;

import com.erp.Agents.Agent_Bank.Model.CAgentBankViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAgentBankViewRepository extends JpaRepository<CAgentBankViewModel, Long> {

	@Query(value = "Select * FROM cmv_agent_banks order by agent_bank_id Desc", nativeQuery = true)
	Page<CAgentBankViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_agent_banks where is_delete =0  order by agent_bank_id Desc", nativeQuery = true)
	Page<CAgentBankViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query("FROM CAgentBankViewModel cabm where cabm.is_delete =0 and cabm.agent_id = ?1 and cabm.company_id = ?2")
	List<CAgentBankViewModel> FnShowParticularRecordForUpdate(int agent_id, int company_id);


	@Query(nativeQuery = true, value = "Select * FROM  cmv_agent_banks where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and agent_bank_id = ?3")
	CAgentBankViewModel FnShowParticularRecord(int company_id, int company_branch_id, int agent_bank_id);


}
