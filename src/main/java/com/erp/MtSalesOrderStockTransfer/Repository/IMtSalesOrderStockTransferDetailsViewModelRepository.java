package com.erp.MtSalesOrderStockTransfer.Repository;

import com.erp.MtSalesOrderStockTransfer.Model.CMtSalesOrderStockTransferDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesOrderStockTransferDetailsViewModelRepository extends JpaRepository<CMtSalesOrderStockTransferDetailsViewModel, Integer> {

	@Query("FROM CMtSalesOrderStockTransferDetailsViewModel model WHERE model.sales_order_transfer_id = ?1")
	List<CMtSalesOrderStockTransferDetailsViewModel> FnShowParticularRecordForUpdate(int sales_order_transfer_id);
}