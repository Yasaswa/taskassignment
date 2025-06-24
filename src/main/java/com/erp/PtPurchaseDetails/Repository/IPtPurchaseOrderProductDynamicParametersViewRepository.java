package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderPaymentTermsModel;
import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderProductDynamicParametersViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtPurchaseOrderProductDynamicParametersViewRepository extends JpaRepository<CPtPurchaseOrderProductDynamicParametersViewModel , Integer> {

    @Query(value = "from CPtPurchaseOrderProductDynamicParametersViewModel model where model.purchase_order_no = ?1 and model.purchase_order_version = ?2 and model.company_id = ?3")
    List<CPtPurchaseOrderProductDynamicParametersViewModel> FnShowPurchaseOrderDynamicParameters(String purchase_order_no, Integer purchase_order_version, int company_id);


}
