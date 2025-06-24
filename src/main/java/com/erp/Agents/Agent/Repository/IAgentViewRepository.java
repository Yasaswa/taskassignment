package com.erp.Agents.Agent.Repository;

import com.erp.Agents.Agent.Model.CAgentViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IAgentViewRepository extends JpaRepository<CAgentViewModel, Long> {


	@Query(value = "Select * FROM  cmv_agent order by agent_id Desc", nativeQuery = true)
	Page<CAgentViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_agent where is_delete =0  order by agent_id Desc", nativeQuery = true)
	Page<CAgentViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_agent where is_delete =0 and agent_id = ?1 and company_id = ?2", nativeQuery = true)
	CAgentViewModel FnShowParticularRecordForUpdate(int agent_id, int company_id);

	@Query(value = "SELECT * FROM cmv_agent_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);
}
