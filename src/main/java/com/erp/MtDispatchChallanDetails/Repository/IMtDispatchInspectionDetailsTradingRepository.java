package com.erp.MtDispatchChallanDetails.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchInspectionDetailsTradingModel;

import java.util.List;

public interface IMtDispatchInspectionDetailsTradingRepository extends JpaRepository<CMtDispatchInspectionDetailsTradingModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CMtDispatchInspectionDetailsTradingModel didt SET didt.is_delete = 1, didt.deleted_on = CURRENT_TIMESTAMP() WHERE  didt.dispatch_challan_no = ?1 and didt.dispatch_challan_version = ?2 and didt.company_id = ?3")
	void updateInspectionDetails(String dispatch_challan_no, int dispatch_challan_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CMtDispatchInspectionDetailsTradingModel didt SET didt.is_delete = 1, didt.deleted_on = CURRENT_TIMESTAMP(), didt.deleted_by = ?4 WHERE didt.dispatch_challan_no = ?1 and didt.dispatch_challan_version = ?2 and didt.company_id = ?3")
	void deleteInspectionDetails(String dispatch_challan_no, int dispatch_challan_version, int company_id,
			String deletedBy);

	@Query(value = "FROM CMtDispatchInspectionDetailsTradingModel model where model.is_delete =0 and model.dispatch_challan_master_transaction_id = ?1 and model.company_id = ?2")
	List<CMtDispatchInspectionDetailsTradingModel> FnshowRecordsInspectionDetails(int dispatchChallanMasterTransactionId, int companyId);

}
