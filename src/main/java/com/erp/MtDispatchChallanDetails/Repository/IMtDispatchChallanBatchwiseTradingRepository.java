package com.erp.MtDispatchChallanDetails.Repository;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanBatchwiseTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IMtDispatchChallanBatchwiseTradingRepository extends JpaRepository<CMtDispatchChallanBatchwiseTradingModel, Integer> {

	@Query(value = "FROM CMtDispatchChallanBatchwiseTradingModel model where model.is_delete =0 and model.dispatch_challan__batchwise_transaction_id = ?1 and model.company_id = ?2")
	CMtDispatchChallanBatchwiseTradingModel FnShowParticularRecordForUpdate(int dispatch_challan__batchwise_transaction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_dispatch_challan_batchwise_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where dispatch_challan_no = ?1 and dispatch_challan_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	void updateStatus(String dispatch_challan_no, Integer dispatch_challan_version, String financial_year,
	                  int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_dispatch_challan_batchwise_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where dispatch_challan_no = ?1 and dispatch_challan_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteChallanBatchWise(String dispatch_challan_no,
	                            int dispatch_challan_version, int company_id, String deleted_by);

	@Query(value = "FROM CMtDispatchChallanBatchwiseTradingModel model where model.is_delete =0 and model.dispatch_challan_master_transaction_id = ?1 and model.company_id = ?2")
	List<CMtDispatchChallanBatchwiseTradingModel> FnShowRecordsByDispatchChallan(int dispatch_challan_master_transaction_id, Integer company_id);


}
