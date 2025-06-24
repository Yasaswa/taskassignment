package com.erp.MtSalesQuotationMasterProject.Repository;

import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationFollowupProjectViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesQuotationFollowupProjectViewRepository extends JpaRepository<CMtSalesQuotationFollowupProjectViewModel, Integer> {


	@Query(value = "from CMtSalesQuotationFollowupProjectViewModel model where model.is_delete = 0 and model.quotation_no = ?1 and model.company_id = ?2")
	List<CMtSalesQuotationFollowupProjectViewModel> FnShowQuotationFollowupTradingRecord(String quotation_no,
	                                                                                     int company_id);

}
