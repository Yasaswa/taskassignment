package com.erp.MtSalesOrderMasterWorks.Repository;

import com.erp.MtSalesOrderMasterWorks.Model.CMtSalesOrderMasterWorksSummaryViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMtSalesOrderMasterWorksSummaryViewRepository
		extends JpaRepository<CMtSalesOrderMasterWorksSummaryViewModel, Integer> {

	@Query(value = "FROM CMtSalesOrderMasterWorksSummaryViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.sales_order_master_transaction_id Desc")
	Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CMtSalesOrderMasterWorksSummaryViewModel model where model.is_delete =0 and model.sales_order_master_transaction_id = ?1 and model.company_id = ?2 order by model.sales_order_master_transaction_id Desc")
	Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowParticularRecord(int sales_order_master_transaction_id,
	                                                                      Pageable pageable, int company_id);

	@Query(value = "FROM CMtSalesOrderMasterWorksSummaryViewModel model where model.is_delete =0 and model.customer_order_no = ?1 and model.company_id = ?2 order by model.customer_order_no Desc")
	Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(
			String customer_order_no, Pageable pageable, int company_id);

}
