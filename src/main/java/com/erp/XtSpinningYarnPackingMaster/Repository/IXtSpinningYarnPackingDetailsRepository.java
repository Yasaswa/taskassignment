package com.erp.XtSpinningYarnPackingMaster.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtSpinningYarnPackingMaster.Model.CXtSpinningYarnPackingDetailsModel;

public interface IXtSpinningYarnPackingDetailsRepository extends JpaRepository<CXtSpinningYarnPackingDetailsModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CXtSpinningYarnPackingDetailsModel model SET model.is_delete = 1 ,model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.yarn_packing_master_id = ?1 and model.company_id = ?2")
	void FnDeleteSpinningYarnPackingDetailsRecord(int yarn_packing_master_id, int company_id, String deleted_by);

	@Modifying
    @Transactional	
    @Query(value = "UPDATE xt_spinning_yarn_packing_details SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE yarn_packing_details_id NOT IN (?1)", nativeQuery = true)
	void updateSpinningYarnPackingDetails(List<Integer> distinctSpinningYarnPackingIds);


	@Modifying
	@Transactional
	@Query(value = "update xt_spinning_yarn_packing_details set item_dispatch_quantity = ?1, packing_details_status = ?2 , modified_on = CURRENT_TIMESTAMP() WHERE yarn_packing_details_id = ?3 and company_id = ?4", nativeQuery = true)
	void updateDispatchPackingDetails(double totalDispatchQty, String dispatchStatus,
			Integer dispatch_challan_packing_id, Integer company_id);
	
	@Modifying
    @Transactional	
    @Query(value = "UPDATE CXtSpinningYarnPackingDetailsModel model SET model.packing_details_status = ?2, model.modified_by = ?3, model.modified_on = CURRENT_TIMESTAMP() WHERE model.yarn_packing_details_id IN (?1) and model.is_delete = false")
	void updateSpinningYarnPackingDetailsByDispNote(List<Integer> distinctSpinningYarnPackingIds, String pkgDtlStatus, String modifiedBy);

	
	@Query(value = "FROM CXtSpinningYarnPackingDetailsModel model where model.is_delete = 0 and model.yarn_packing_details_id = ?1 and model.company_id = ?2")
	List<CXtSpinningYarnPackingDetailsModel> FnshowRecordsYarnPackingdetailsDetails(Integer yarn_packing_details_id,
			Integer company_id);
	
	
	

}
