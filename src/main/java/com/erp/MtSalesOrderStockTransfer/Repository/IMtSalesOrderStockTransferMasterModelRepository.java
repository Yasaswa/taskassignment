package com.erp.MtSalesOrderStockTransfer.Repository;

import com.erp.MtSalesOrderStockTransfer.Model.CMtSalesOrderStockTransferMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMtSalesOrderStockTransferMasterModelRepository extends JpaRepository<CMtSalesOrderStockTransferMasterModel, Long> {
}