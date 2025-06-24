package com.erp.MtSalesOrderMasterProjects.Repository;

import com.erp.MtSalesOrderMasterProjects.Model.CMtSalesOrderMasterProjectSummaryViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMtSalesOrderMasterProjectSummaryViewRepository extends JpaRepository<CMtSalesOrderMasterProjectSummaryViewModel, Integer> {

	@Query(value = "Select * FROM  mtv_sales_order_master_projects_summary where is_delete =0  order by sales_order_master_transaction_id Desc", nativeQuery = true)
	Page<CMtSalesOrderMasterProjectSummaryViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "FROM CMtSalesOrderMasterProjectSummaryViewModel cmts where cmts.is_delete =0 and cmts.sales_order_master_transaction_id = ?1 order by cmts.sales_order_master_transaction_id Desc")
	Page<CMtSalesOrderMasterProjectSummaryViewModel> FnShowParticularRecord(int sales_order_master_transaction_id,
	                                                                        Pageable pageable, int company_id);

	@Query(value = "FROM CMtSalesOrderMasterProjectSummaryViewModel model where model.is_delete =0 and model.customer_order_no = ?1 and model.company_id = ?2 order by model.customer_order_no Desc")
	Page<CMtSalesOrderMasterProjectSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(
			String customer_order_no, Pageable pageable, int company_id);


}
