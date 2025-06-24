package com.erp.StRawMaterialReturnMaster.Repository;

import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnMasterModel;
import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IStRawMaterialReturnMasterViewModelRepository extends JpaRepository<CStRawMaterialReturnMasterViewModel, Long> {

	@Query(value = "FROM CStRawMaterialReturnMasterViewModel model WHERE model.issue_return_no = ?1 AND model.is_delete = false AND model.company_id = ?2")
	CStRawMaterialReturnMasterViewModel FnShowRawMaterialMasterRecord(String issue_return_no, int company_id);


}