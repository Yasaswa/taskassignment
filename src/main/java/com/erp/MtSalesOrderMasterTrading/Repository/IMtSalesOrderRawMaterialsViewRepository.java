package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderProductDynamicParametersViewModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderRawMaterialsDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesOrderRawMaterialsViewRepository extends JpaRepository<CMtSalesOrderRawMaterialsDetailsViewModel, Integer> {

    @Query(value = "FROM CMtSalesOrderRawMaterialsDetailsViewModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3 order by mt_sales_order_rm_transaction_id asc")
    List<CMtSalesOrderRawMaterialsDetailsViewModel> FnShowSalesOrderRawMaterialsData(String salesOrderNo, int salesOrderVersion, int companyId);

    @Query(value = "FROM CMtSalesOrderRawMaterialsDetailsViewModel model where model.is_delete = 0 and model.customer_order_no IN ?1 and model.company_id = ?2 order by mt_sales_order_rm_transaction_id asc")
    List<CMtSalesOrderRawMaterialsDetailsViewModel> FnShowRawMaterialsData(List<String> customerOrderNos, int company_id);

}
