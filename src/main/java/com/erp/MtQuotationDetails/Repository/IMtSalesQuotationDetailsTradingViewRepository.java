package com.erp.MtQuotationDetails.Repository;

import com.erp.MtQuotationDetails.Model.CMtSalesQuotationDetailsTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationDetailsTradingViewRepository extends JpaRepository<CMtSalesQuotationDetailsTradingViewModel, Integer> {


	@Query(value = "SELECT * FROM mtv_sales_quotation_trading_details  WHERE quotation_no = ?1 and quotation_version = ?2 and financial_year = ?3 and quotation_item_status = ?4 and company_id= ?5 and is_delete = 0", nativeQuery = true)
	List<CMtSalesQuotationDetailsTradingViewModel> FnGetQuotationDetailsByItemStatus(String quotation_no, int quotation_version, String financial_year, String quotation_item_status, int company_id);

}
