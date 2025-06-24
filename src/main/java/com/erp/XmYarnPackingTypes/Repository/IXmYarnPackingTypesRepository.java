package com.erp.XmYarnPackingTypes.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XmYarnPackingTypes.Model.CXmYarnPackingTypesModel;


public interface IXmYarnPackingTypesRepository extends JpaRepository<CXmYarnPackingTypesModel, Integer> {


	@Transactional
	@Modifying
	@Query(value = "UPDATE CXmYarnPackingTypesModel ypt SET ypt.is_delete = true, ypt.deleted_by = ?3, ypt.deleted_on = CURRENT_TIMESTAMP() WHERE ypt.yarn_packing_types_id  = ?1 and ypt.company_id = ?2")
	void FnDeleteYarnPackingTypesRecords(int yarn_packing_types_id, int company_id, String deleted_by);

	@Query(value = "FROM CXmYarnPackingTypesModel model where model.is_delete = false and model.yarn_packing_types_name = ?1 and model.yarn_packing_types_id = ?2")
	CXmYarnPackingTypesModel checkIsYarnPackingTypeExist(String yarn_packing_types_name, int yarn_packing_types_id);

}
