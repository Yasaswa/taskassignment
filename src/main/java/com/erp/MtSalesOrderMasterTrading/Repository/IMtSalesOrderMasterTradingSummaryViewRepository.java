package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTradingSummaryViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMtSalesOrderMasterTradingSummaryViewRepository extends JpaRepository<CMtSalesOrderMasterTradingSummaryViewModel, Long> {


	@Query(value = "FROM CMtSalesOrderMasterTradingSummaryViewModel model where model.is_delete =0 and model.sales_order_master_transaction_id = ?1 and model.company_id = ?2 order by model.sales_order_master_transaction_id Desc")
	Page<CMtSalesOrderMasterTradingSummaryViewModel> FnShowParticularRecord(int sales_order_master_transaction_id, Pageable pageable, int company_id);


	@Query(value = "FROM CMtSalesOrderMasterTradingSummaryViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.sales_order_master_transaction_id Desc")
	Page<CMtSalesOrderMasterTradingSummaryViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

}
