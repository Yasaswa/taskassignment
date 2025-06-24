package com.erp.StRawMaterialReturnMaster.Repository;

import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStRawMaterialReturnDetailsViewModelRepository extends JpaRepository<CStRawMaterialReturnDetailsViewModel, Integer> {

	@Query("FROM CStRawMaterialReturnDetailsViewModel model WHERE model.issue_return_no = ?1 AND model.is_delete = false AND model.company_id = ?2")
	List<CStRawMaterialReturnDetailsViewModel> FnShowRawMaterialReturnDetailRecords(String issue_return_no, int company_id);

}