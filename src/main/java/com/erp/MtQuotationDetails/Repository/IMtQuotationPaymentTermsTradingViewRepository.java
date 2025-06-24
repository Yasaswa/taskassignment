package com.erp.MtQuotationDetails.Repository;

import com.erp.MtQuotationDetails.Model.CMtSalesQuotationPaymentTermsTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtQuotationPaymentTermsTradingViewRepository extends JpaRepository<CMtSalesQuotationPaymentTermsTradingViewModel, Integer> {

	@Query(value = "from CMtSalesQuotationPaymentTermsTradingViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.quotation_version = ?2 and model.company_id = ?3")
	List<CMtSalesQuotationPaymentTermsTradingViewModel> FnShowQuotationPaymentTerms(String quotation_no,
	                                                                                int quotation_version, int company_id);

	@Query(value = "SELECT * FROM mtv_sales_quotation_payment_terms_trading  WHERE quotation_no = ?1 and quotation_version = ?2 and company_id= ?3 and is_delete = 0", nativeQuery = true)
	List<CMtSalesQuotationPaymentTermsTradingViewModel> FnGetQuotationPaymentTermsData(String quotation_no,
	                                                                                   int quotation_version, int company_id);

}
