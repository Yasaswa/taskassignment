package com.erp.MtDispatchChallanDetails.Repository;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanMasterTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


public interface IMtDispatchChallanMasterTradingRepository extends JpaRepository<CMtDispatchChallanMasterTradingModel, Integer> {

	@Query(value = "FROM CMtDispatchChallanMasterTradingModel model where model.is_delete =0 and model.dispatch_challan_master_transaction_id = ?1 and model.company_id = ?2")
	CMtDispatchChallanMasterTradingModel FnShowParticularRecordForUpdate(int dispatch_challan_master_transaction_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_dispatch_challan_master_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where dispatch_challan_no = ?1 and dispatch_challan_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteChallanMaster(String dispatch_challan_no, int dispatch_challan_version,
	                         int company_id, String deletedBy);

	@Query(value = "Select * from mt_dispatch_challan_master_trading where is_delete = 0 and dispatch_challan_no = ?1 and company_id = ?2", nativeQuery = true)
	CMtDispatchChallanMasterTradingModel getLastDispatchChallanVersion(String dispatch_challan_no, String string);

	@Query(value = "SELECT * FROM mt_dispatch_challan_master_trading  WHERE dispatch_challan_no = ?1 and dispatch_challan_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowDispatchChallanMasterRecord(String dispatch_challan_no, int dispatch_challan_version,
	                                                      String financial_year, int company_id);


	@Query(value = "FROM CMtDispatchChallanMasterTradingModel model where model.is_delete =0 and model.dispatch_challan_no IN ?1 and model.company_id = ?2")
	List<CMtDispatchChallanMasterTradingModel> getDispatchChallanMaster(List<String> distinctDispatchChallanNos, Integer company_id);

	@Query(value = "FROM CMtDispatchChallanMasterTradingModel model WHERE model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.financial_year = ?3 and model.company_id= ?4")
	CMtDispatchChallanMasterTradingModel FnShowParticularRecordByDispatchChallanNo(String dispatch_challan_no, int dispatch_challan_version, String financial_year, int company_id);
}
