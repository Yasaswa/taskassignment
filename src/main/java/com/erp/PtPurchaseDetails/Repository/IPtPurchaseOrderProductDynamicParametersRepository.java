package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderPaymentTermsModel;
import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderProductDynamicParametersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtPurchaseOrderProductDynamicParametersRepository extends JpaRepository<CPtPurchaseOrderProductDynamicParametersModel , Integer>{

    @Query(value = "from CPtPurchaseOrderProductDynamicParametersModel model where model.is_delete = 0 and model.purchase_order_no = ?1 and model.purchase_order_version = ?2 and model.company_id = ?3")
    List<CPtPurchaseOrderPaymentTermsModel> FnShowPurchaseOrderDynamicParameters(String purchase_order_no,  Integer purchase_order_version, int company_id);


    @Modifying
    @Transactional
    @Query(value = "update pt_purchase_order_product_dynamic_parameters set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
    void updatePurchaseOrderParametersData(String purchaseOrderNo, int purchaseOrderVersion, int companyId);



    @Modifying
    @Transactional
    @Query(value = "update pt_purchase_order_product_dynamic_parameters set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3 and deleted_by = ?4", nativeQuery = true)
    void deleteProductDynamicParameters(String purchaseOrderNo, int purchaseOrderVersion, int companyId, String userName);
}
