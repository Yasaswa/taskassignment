package com.erp.MtQuotationDetails.Repository;

import com.erp.MtQuotationDetails.Model.CMtSalesQuotationExistingExpectedFunctionalityViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationExistingExpectedFunctionalityViewRepository extends JpaRepository<CMtSalesQuotationExistingExpectedFunctionalityViewModel, Integer> {

	@Query(value = "from CMtSalesQuotationExistingExpectedFunctionalityViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.quotation_version = ?2 and model.company_id = ?3")
	List<CMtSalesQuotationExistingExpectedFunctionalityViewModel> FnShowQuotationExistingExpectedFunctionality(
			String quotation_no, Integer quotation_version, int parseInt);

	@Query(value = "from CMtSalesQuotationExistingExpectedFunctionalityViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.quotation_version = ?2  and model.company_id = ?3 and model.is_delete = 0")
	List<CMtSalesQuotationExistingExpectedFunctionalityViewModel> FnShowQuotationExistingExpectedFucntionalityRecord(
			String quotation_no, int quotation_version, int company_id);

}
