package com.erp.XmProductionWastageTypes.Repository;

import com.erp.XmProductionWastageTypes.Model.CXmProductionWastageTypesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IXmProductionWastageTypesRepository extends JpaRepository<CXmProductionWastageTypesModel, Integer> {

	@Query(value = "FROM CXmProductionWastageTypesModel model where model.is_delete =0 and model.production_wastage_types_id = ?1 and model.company_id = ?2")
	CXmProductionWastageTypesModel FnShowParticularRecordForUpdate(int production_wastage_types_id, int company_id);


	@Query(value = "FROM CXmProductionWastageTypesModel model where model.is_delete =0 and model.production_wastage_types_name = ?1")
	CXmProductionWastageTypesModel checkIfNameExist(String production_wastage_types_name);


}
