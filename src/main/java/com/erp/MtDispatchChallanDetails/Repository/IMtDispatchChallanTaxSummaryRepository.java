package com.erp.MtDispatchChallanDetails.Repository;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanTaxSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtDispatchChallanTaxSummaryRepository extends JpaRepository<CMtDispatchChallanTaxSummaryModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update mt_dispatch_challan_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where  dispatch_challan_no= ?1 and dispatch_challan_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateTaxSummary(String dispatch_challan_no, int dispatch_challan_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_dispatch_challan_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where dispatch_challan_no = ?1 and dispatch_challan_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteChallanTaxSummary(String dispatch_challan_no, int dispatch_challan_version, int company_id, String deleted_by);

}
