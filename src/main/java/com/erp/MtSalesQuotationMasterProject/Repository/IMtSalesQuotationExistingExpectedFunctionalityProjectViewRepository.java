package com.erp.MtSalesQuotationMasterProject.Repository;

import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationExistingExpectedFunctionalityProjectViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationExistingExpectedFunctionalityProjectViewRepository extends JpaRepository<CMtSalesQuotationExistingExpectedFunctionalityProjectViewModel, Integer> {


	@Query(value = "from CMtSalesQuotationExistingExpectedFunctionalityProjectViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.quotation_version = ?2  and model.company_id = ?3 and model.is_delete = 0")
	List<CMtSalesQuotationExistingExpectedFunctionalityProjectViewModel> FnShowQuotationExistingExpectedFucntionalityRecord(
			String quotation_no, int quotation_version, int company_id);

}
