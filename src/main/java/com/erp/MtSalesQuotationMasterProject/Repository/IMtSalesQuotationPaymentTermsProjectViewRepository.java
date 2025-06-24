package com.erp.MtSalesQuotationMasterProject.Repository;

import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationPaymentTermsProjectViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationPaymentTermsProjectViewRepository extends JpaRepository<CMtSalesQuotationPaymentTermsProjectViewModel, Integer> {


	@Query(value = "from CMtSalesQuotationPaymentTermsProjectViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.quotation_version = ?2 and model.company_id = ?3")
	List<CMtSalesQuotationPaymentTermsProjectViewModel> FnShowQuotationPaymentTermsProject(String quotation_no,
	                                                                                       int quotation_version, int company_id);


	@Query(value = "SELECT * FROM mtv_sales_quotation_payment_terms_project  WHERE quotation_no = ?1 and quotation_version = ?2 and company_id= ?3 and is_delete = 0", nativeQuery = true)
	List<CMtSalesQuotationPaymentTermsProjectViewModel> FnGetQuotationPaymentTermsProjectData(String quotation_no,
	                                                                                          int quotation_version, int company_id);

}
