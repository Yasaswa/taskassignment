package com.erp.MtQuotationDetails.Repository;

import com.erp.MtQuotationDetails.Model.CMtSalesQuotationMasterTradingSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMtSalesQuotationMasterTradingViewRepository extends JpaRepository<CMtSalesQuotationMasterTradingSummaryViewModel, Integer> {

}
