package com.erp.AmTemplatesCommunications.Repository;

import com.erp.AmTemplatesCommunications.Model.CAmTemplatesCommunicationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IAmTemplatesCommunicationsRepository extends JpaRepository<CAmTemplatesCommunicationsModel, Integer> {

	@Query(value = "FROM CAmTemplatesCommunicationsModel model where model.is_delete =0 and model.communications_templates_id = ?1 and model.company_id = ?2")
	CAmTemplatesCommunicationsModel FnShowParticularRecordForUpdate(int communications_templates_id, int company_id);


}
