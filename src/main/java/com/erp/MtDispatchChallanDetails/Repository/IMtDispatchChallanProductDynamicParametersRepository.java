package com.erp.MtDispatchChallanDetails.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanProductDynamicParametersModel;


public interface IMtDispatchChallanProductDynamicParametersRepository extends JpaRepository<CMtDispatchChallanProductDynamicParametersModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE CMtDispatchChallanProductDynamicParametersModel model SET model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() where  model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.company_id = ?3")
	void updateProductDynamicParameterDetails(String dispatch_challan_no, int dispatch_challan_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CMtDispatchChallanProductDynamicParametersModel pdp SET pdp.is_delete = 1, pdp.deleted_on = CURRENT_TIMESTAMP(), pdp.deleted_by = ?4 WHERE pdp.dispatch_challan_no = ?1 and pdp.dispatch_challan_version = ?2 and pdp.company_id = ?3")
	void deleteProductDynamicParameters(String dispatch_challan_no, int dispatch_challan_version, int company_id,
			String deletedBy);


}
