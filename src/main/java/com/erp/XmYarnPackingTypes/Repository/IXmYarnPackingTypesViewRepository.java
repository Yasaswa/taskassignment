package com.erp.XmYarnPackingTypes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XmYarnPackingTypes.Model.CXmYarnPackingTypesViewModel;

public interface IXmYarnPackingTypesViewRepository extends JpaRepository<CXmYarnPackingTypesViewModel, Integer>{

	@Query(value = "FROM CXmYarnPackingTypesViewModel model where model.is_delete = false and model.yarn_packing_types_id = ?1 and model.company_id = ?2")
	CXmYarnPackingTypesViewModel FnShowParticularRecordForUpdate(int yarn_packing_types_id, int company_id);

}
