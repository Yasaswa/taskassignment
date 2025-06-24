package com.erp.MtSalesOrderMasterServices.Repository;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderDetailsServicesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtSalesOrderDetailsServicesViewRepository extends JpaRepository<CMtSalesOrderDetailsServicesViewModel, Integer> {

	@Query(value = "SELECT * FROM  mtv_sales_order_details_services WHERE  is_delete = 0 and sales_order_no = ?1 and sales_order_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowSalesOrderDetailsServiceRecord(String sales_order_no, int sales_order_version,
	                                                               String financial_year, int company_id);

}
