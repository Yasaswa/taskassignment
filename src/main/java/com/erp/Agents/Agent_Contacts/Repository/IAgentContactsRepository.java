package com.erp.Agents.Agent_Contacts.Repository;

import com.erp.Agents.Agent_Contacts.Model.CAgentContactsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IAgentContactsRepository extends JpaRepository<CAgentContactsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_agent_contacts set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where agent_contact_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int agent_contact_id);


//	@Modifying
//	@Transactional
//	@Query(value = "update cm_agent_contacts set is_delete = 1,  deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where agent_contact_id=?1 and company_id = ?2", nativeQuery = true)
//	Object FnDeleteRecord(int agent_contact_id, int company_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_agent_contacts WHERE agent_contact_person = ?1")
	CAgentContactsModel getCheck(String agent_contact_person);

	@Query("FROM CAgentContactsModel ctcm where  ctcm.is_delete =  0 and ctcm.agent_id = ?1 ")
	List<CAgentContactsModel> findByagent_id(int agent_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_agent_contacts set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where agent_id=?1 and company_id=?2 and is_delete = 0", nativeQuery = true)
	Object updateAgentContactActiveStatus(int agent_id, int company_id);

	@Query("FROM CAgentContactsModel model where model.is_delete = 0 and company_id=?1 and model.agent_id = ?2")
	List<CAgentContactsModel> FnShowParticularRecord(int company_id, int agent_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_agent_contacts set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where agent_id=?2 and company_id = ?3", nativeQuery = true)
	Object FnDeleteAgentContactsRecord(String deleted_by, int agent_id, int company_id);
}
