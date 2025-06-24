package com.erp.MtSalesOrderMasterServices.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderTermsServicesViewModel;

public interface IMtSalesOrderTermsServicesViewRepository extends JpaRepository<CMtSalesOrderTermsServicesViewModel, Integer>{

	
	@Query(value = "from CMtSalesOrderTermsServicesViewModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	List<CMtSalesOrderTermsServicesViewModel> FnShowSalesOrderTermsServiceRecord(String sales_order_no,
			int sales_order_version, int company_id);

}
