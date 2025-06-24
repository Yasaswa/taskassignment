package com.erp.MtDispatchChallanDetails.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanPackingDetailsModel;

public interface IMtDispatchChallanPackingDetailsRepository extends JpaRepository<CMtDispatchChallanPackingDetailsModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "UPDATE CMtDispatchChallanPackingDetailsModel model SET model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() where  model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.company_id = ?3")
	void updateDispatchChallanPackingDetails(String dispatch_challan_no, int dispatch_challan_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CMtDispatchChallanPackingDetailsModel model SET model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP(), model.deleted_by = ?4 WHERE model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.company_id = ?3")
	void deletePackingDetails(String dispatch_challan_no, int dispatch_challan_version, int company_id,
			String deletedBy);

	@Query(value = "FROM CMtDispatchChallanPackingDetailsModel model WHERE model.is_delete = false and model.dispatch_challan_packing_id  IN ?1 and company_id = ?2")
	List<CMtDispatchChallanPackingDetailsModel> getAllDispatchQtySummary(List<Integer> distinctPackingDetailsIds,
			int company_id);
	
	@Query(value = "FROM CMtDispatchChallanPackingDetailsModel model where model.is_delete = 0 and model.dispatch_challan_master_transaction_id = ?1 and model.company_id = ?2")
	List<CMtDispatchChallanPackingDetailsModel> FnshowRecordsPackingDetails(int dispatch_challan_master_transaction_id,
			int company_id);
	


}
