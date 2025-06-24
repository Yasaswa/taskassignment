package com.erp.MtDispatchChallanDetails.Repository;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanTaxSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtDispatchChallanTaxSummaryViewRepository extends JpaRepository<CMtDispatchChallanTaxSummaryViewModel, Integer> {

	@Query(value = "from CMtDispatchChallanTaxSummaryViewModel model where model.is_delete = 0 and model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.company_id = ?3")
	List<CMtDispatchChallanTaxSummaryViewModel> FnShowDispatchChallanTaxSummaryRecords(String dispatch_challan_no,
	                                                                                   int dispatch_challan_version, int company_id);

}
