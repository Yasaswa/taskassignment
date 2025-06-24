package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderRawMaterialsDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesOrderRawMaterialsRepository extends JpaRepository<CMtSalesOrderRawMaterialsDetailsModel, Integer> {


    @Modifying
    @Transactional
    @Query(value = "update CMtSalesOrderRawMaterialsDetailsModel model set model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() where model.sales_order_no= ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
    void updateSalesOrderRawMaterialData(String sales_order_no, int sales_order_version, int company_id);


    @Modifying
    @Transactional
    @Query(value = "update CMtSalesOrderRawMaterialsDetailsModel model set model.is_delete = 1, model.deleted_by = ?4, model.deleted_on = CURRENT_TIMESTAMP() where model.sales_order_no= ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
    void deleteSalesOrderRawMaterialsRecords(String sales_order_no, int sales_order_version, int company_id,String deleted_by);

}
