package com.erp.Agents.Agent.Repository;

import com.erp.Agents.Agent.Model.CAgentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IAgentRepository extends JpaRepository<CAgentModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_agent set is_delete = 1, deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where agent_id=?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int agent_id, int company_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_agent WHERE (agent_name = ?1 or (?2 IS NOT NULL AND agent_short_name = ?2)) and company_id = ?3 order by agent_id Desc limit 1")
	CAgentModel getCheck(String agent_name, String agent_short_name, int company_id);

	@Query(value = "FROM CAgentModel model where model.company_id = ?1 and model.agent_id = ?2")
	CAgentModel FnShowParticularRecord(int company_id, int agent_id);
}
