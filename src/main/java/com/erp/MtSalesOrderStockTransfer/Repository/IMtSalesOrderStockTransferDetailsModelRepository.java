package com.erp.MtSalesOrderStockTransfer.Repository;

import com.erp.MtSalesOrderStockTransfer.Model.CMtSalesOrderStockTransferDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMtSalesOrderStockTransferDetailsModelRepository extends JpaRepository<CMtSalesOrderStockTransferDetailsModel, Long> {
}