package com.erp.MtSalesOrderStockTransfer.Repository;

import com.erp.MtSalesOrderStockTransfer.Model.CMtSalesOrderStockTransferMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMtSalesOrderStockTransferMasterViewModelRepository extends JpaRepository<CMtSalesOrderStockTransferMasterViewModel, Integer> {
	@Query("FROM CMtSalesOrderStockTransferMasterViewModel model WHERE model.sales_order_transfer_id = ?1")
	CMtSalesOrderStockTransferMasterViewModel FnShowParticularRecordForUpdate(int sales_order_transfer_id);
}