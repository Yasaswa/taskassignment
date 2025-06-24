package com.erp.AmTemplatesCommunications.Repository;

import com.erp.AmTemplatesCommunications.Model.CAmTemplatesCommunicationsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IAmTemplatesCommunicationsViewRepository extends JpaRepository<CAmTemplatesCommunicationsViewModel, Integer> {

	@Query(value = "FROM CAmTemplatesCommunicationsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.communications_templates_id Desc")
	Page<CAmTemplatesCommunicationsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CAmTemplatesCommunicationsViewModel model where model.is_delete =0 and model.communications_templates_id = ?1 and model.company_id = ?2 order by model.communications_templates_id Desc")
	Page<CAmTemplatesCommunicationsViewModel> FnShowParticularRecord(int communications_templates_id, Pageable pageable,
	                                                                 int company_id);

	@Query(value = "SELECT * FROM amv_templates_communications_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
