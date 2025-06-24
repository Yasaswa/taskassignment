package com.erp.MtSalesOrderMasterTrading.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderProductDynamicParametersViewModel;


public interface IMtSalesOrderProductDynamicParametersViewRepository extends JpaRepository<CMtSalesOrderProductDynamicParametersViewModel, Integer> {

	
//	@Query(value = "FROM CMtSalesOrderProductDynamicParametersViewModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3 order by sales_order_parameter_id asc")
//	List<CMtSalesOrderProductDynamicParametersViewModel> FnShowSalesOrderProductDynamicParameters(String sales_order_no,
//			int sales_order_version, int company_id);


	@Query(value = "FROM CMtSalesOrderProductDynamicParametersViewModel model where model.is_delete = 0 and model.customer_order_no IN ?1 and model.company_id = ?2 order by sales_order_parameter_id asc")
	List<CMtSalesOrderProductDynamicParametersViewModel> FnShowSOProductDynamicParameters(List<String> customerOrderNos, int company_id);

}
