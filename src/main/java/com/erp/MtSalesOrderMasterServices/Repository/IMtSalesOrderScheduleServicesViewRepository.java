package com.erp.MtSalesOrderMasterServices.Repository;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderSchedulesServicesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesOrderScheduleServicesViewRepository extends JpaRepository<CMtSalesOrderSchedulesServicesViewModel, Integer> {

	@Query(value = "from CMtSalesOrderSchedulesServicesViewModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	List<CMtSalesOrderSchedulesServicesViewModel> FnShowSalesOrderScheduleServiceRecord(String sales_order_no,
	                                                                                    int sales_order_version, int company_id);

}
