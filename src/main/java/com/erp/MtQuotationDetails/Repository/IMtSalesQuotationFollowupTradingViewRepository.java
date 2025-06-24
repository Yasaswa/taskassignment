package com.erp.MtQuotationDetails.Repository;

import com.erp.MtQuotationDetails.Model.CMtSalesQuotationFollowupTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationFollowupTradingViewRepository extends JpaRepository<CMtSalesQuotationFollowupTradingViewModel, Integer> {

	@Query(value = "from CMtSalesQuotationFollowupTradingViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.company_id = ?2")
	List<CMtSalesQuotationFollowupTradingViewModel> FnShowQuotationFollowupTradingRecord(String quotation_no, int company_id);


}
