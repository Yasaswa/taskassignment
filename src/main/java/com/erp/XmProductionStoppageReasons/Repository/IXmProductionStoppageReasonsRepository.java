package com.erp.XmProductionStoppageReasons.Repository;

import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IXmProductionStoppageReasonsRepository extends JpaRepository<CXmProductionStoppageReasonsModel, Integer> {

	@Query(value = "FROM CXmProductionStoppageReasonsModel model where model.is_delete =0 and model.production_stoppage_reasons_id = ?1 and model.company_id = ?2")
	CXmProductionStoppageReasonsModel FnShowParticularRecordForUpdate(int production_stoppage_reasons_id, int company_id);

	@Query(value = "FROM CXmProductionStoppageReasonsModel model where model.is_delete =0 and model.production_stoppage_reasons_name = ?1")
	CXmProductionStoppageReasonsModel checkIfNameExist(String production_stoppage_reasons_name);


}
