package com.erp.Agents.Agent_Contacts.Repository;

import com.erp.Agents.Agent_Contacts.Model.CAgentContactsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAgentContactsViewRepository extends JpaRepository<CAgentContactsViewModel, Long> {

	@Query(value = "Select * FROM cmv_agent_contacts order by agent_contact_id Desc", nativeQuery = true)
	Page<CAgentContactsViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_agent_contacts where is_delete =0 and agent_id = ?1 and company_id = ?2", nativeQuery = true)
	Page<CAgentContactsViewModel> FnShowAllActiveRecords(int agent_id, int company_id, Pageable pageable);

	@Query(value = "Select * FROM cmv_agent_contacts where is_delete =0 and agent_contact_id = ?1", nativeQuery = true)
	CAgentContactsViewModel FnShowParticularRecordForUpdate(int agent_contact_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_agent_contacts where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and agent_contact_id = ?3")
	CAgentContactsViewModel FnShowParticularRecord(int company_id, int company_branch_id, int agent_contact_id);

}
