package com.erp.XmProductionProcess.Repository;

import com.erp.XmProductionProcess.Model.CXmProductionProcessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IXmProductionProcessRepository extends JpaRepository<CXmProductionProcessModel, Integer> {

	@Query(value = "FROM CXmProductionProcessModel model where model.is_delete =0 and model.production_process_id = ?1 and model.company_id = ?2")
	CXmProductionProcessModel FnShowParticularRecordForUpdate(int production_process_id, int company_id);


	@Query(nativeQuery = true, value = "SELECT * FROM xm_production_process WHERE (production_process_name = ?1 or (?2 IS NOT NULL AND production_process_short_name = ?2)) and company_id = ?3 order by production_process_id Desc limit 1")
	CXmProductionProcessModel getCheck(String production_process_name, String production_process_short_name,
	                                   Integer company_id);


}
