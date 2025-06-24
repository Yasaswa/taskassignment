package com.erp.MtSalesQuotationMasterServices.Repository;

import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationFollowUpServiceViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationFollowUpServicesViewRepository extends JpaRepository<CMtSalesQuotationFollowUpServiceViewModel, Integer> {

	@Query(value = "from CMtSalesQuotationFollowUpServiceViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.company_id = ?2")
	List<CMtSalesQuotationFollowUpServiceViewModel> FnShowQuotationFollowupServiceRecord(String quotation_no, int company_id);

}
