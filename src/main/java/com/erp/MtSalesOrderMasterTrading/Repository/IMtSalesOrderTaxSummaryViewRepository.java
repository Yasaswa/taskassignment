package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTaxSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IMtSalesOrderTaxSummaryViewRepository extends JpaRepository<CMtSalesOrderMasterTaxSummaryViewModel, Integer> {


	@Query(value = "from CMtSalesOrderMasterTaxSummaryViewModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	List<CMtSalesOrderMasterTaxSummaryViewModel> FnShowSalesOrderTaxSummary(String sales_order_no,
	                                                                        int sales_order_version, int company_id);

	@Query(value = "from CMtSalesOrderMasterTaxSummaryViewModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	List<CMtSalesOrderMasterTaxSummaryViewModel> FnShowSalesOrderTaxSummaryView(String sales_order_no, int sales_order_version, String company_id);

}
