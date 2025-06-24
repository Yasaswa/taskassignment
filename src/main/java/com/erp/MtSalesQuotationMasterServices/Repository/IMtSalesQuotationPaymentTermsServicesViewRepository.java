package com.erp.MtSalesQuotationMasterServices.Repository;

import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationPaymentTermsServiceViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationPaymentTermsServicesViewRepository extends JpaRepository<CMtSalesQuotationPaymentTermsServiceViewModel, Integer> {

	@Query(value = "from CMtSalesQuotationPaymentTermsServiceViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.quotation_version = ?2 and model.company_id = ?3")
	List<CMtSalesQuotationPaymentTermsServiceViewModel> FnShowQuotationPaymentTerms(String quotation_no,
	                                                                                int quotation_version, int company_id);

	@Query(value = "SELECT * FROM mtv_sales_quotation_payment_terms_services  WHERE quotation_no = ?1 and quotation_version = ?2 and company_id= ?3 and is_delete = 0", nativeQuery = true)
	List<CMtSalesQuotationPaymentTermsServiceViewModel> FnGetQuotationPaymentTermsData(String quotation_no,
	                                                                                   int quotation_version, int company_id);
}
