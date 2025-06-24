package com.erp.MtServiceReportingMaster.Repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtServiceReportingMaster.Model.CMtServiceReportingMasterViewModel;

public interface IMtServiceReportingMasterViewRepository extends JpaRepository<CMtServiceReportingMasterViewModel, Integer>{
	
//	@Query(value = "FROM CMtServiceReportingMasterViewModel model WHERE model.is_delete = 0 and model.service_reporting_master_transaction_id = ?1 and model.company_id = ?2")
//	CMtServiceReportingMasterViewModel FnShowServiceReportingMasterRecordForUpdate(
//			int service_reporting_master_transaction_id, int company_id);

	
	@Query(value = "SELECT * FROM mtv_service_reporting_master  WHERE is_delete = 0 and service_reporting_master_transaction_id = ?1 and company_id = ?2",nativeQuery = true)
	Map<String, Object> FnShowServiceReportingMasterRecord(int service_reporting_master_transaction_id, int company_id);

	
}
