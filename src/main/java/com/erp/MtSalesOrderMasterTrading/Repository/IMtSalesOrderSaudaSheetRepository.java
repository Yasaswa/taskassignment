package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderSaudaSheetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IMtSalesOrderSaudaSheetRepository extends JpaRepository<CMtSalesOrderSaudaSheetModel, Integer> {

    @Query("From CMtSalesOrderSaudaSheetModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.company_id = ?2")
    List<CMtSalesOrderSaudaSheetModel> FnShowParticularRecordForUpdate(String sales_order_no, Integer company_id);

    @Modifying
    @Transactional
    @Query(value = "update CMtSalesOrderSaudaSheetModel model set model.is_delete = 1, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() where model.sales_order_no= ?1 and model.company_id = ?2")
    void deleteSalesOrderSaudaSheetRecords(String salesOrderNo, int companyId, String deletedBy);


    @Modifying
    @Transactional
    @Query(value = "update mt_sales_order_sauda_sheet set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sales_order_no= ?1 and company_id = ?2", nativeQuery = true)
    void updateSaudaShitDetails(String salesOrderNo, int companyId);

}
