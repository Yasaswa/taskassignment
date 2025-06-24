package com.erp.MtSalesQuotationMasterServices.Repository;

import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationExistingExpectedFunctionalityServiceViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationExistingExpectedFunctionalityServiceViewRepository extends JpaRepository<CMtSalesQuotationExistingExpectedFunctionalityServiceViewModel, Integer> {

	@Query(value = "from CMtSalesQuotationExistingExpectedFunctionalityServiceViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.quotation_version = ?2 and model.company_id = ?3")
	List<CMtSalesQuotationExistingExpectedFunctionalityServiceViewModel> FnShowQuotationExistingExpectedFunctionality(
			String quotation_no, Integer quotation_version, int parseInt);

	@Query(value = "from CMtSalesQuotationExistingExpectedFunctionalityServiceViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.quotation_version = ?2  and model.company_id = ?3 and model.is_delete = 0")
	List<CMtSalesQuotationExistingExpectedFunctionalityServiceViewModel> FnShowQuotationExistingExpectedFucntionalityServiceRecord(
			String quotation_no, int quotation_version, int company_id);

}
