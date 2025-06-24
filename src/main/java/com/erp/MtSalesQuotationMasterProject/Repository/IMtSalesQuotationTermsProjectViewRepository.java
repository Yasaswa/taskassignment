package com.erp.MtSalesQuotationMasterProject.Repository;

import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationTermsProjectViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationTermsProjectViewRepository extends JpaRepository<CMtSalesQuotationTermsProjectViewModel, Integer> {


	@Query(value = "from CMtSalesQuotationTermsProjectViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.quotation_version = ?2  and model.company_id = ?3 and model.is_delete = 0")
	List<CMtSalesQuotationTermsProjectViewModel> FnShowQuotationTermsTradingRecord(String quotation_no,
	                                                                               int quotation_version, int company_id);

}
