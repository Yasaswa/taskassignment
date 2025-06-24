package com.erp.MtServiceReportingMaster.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtServiceReportingMaster.Model.CMtServiceReportingActivitiesDetailsViewModel;

public interface IMtServiceReportingActivitiesDetailsViewRepository extends JpaRepository<CMtServiceReportingActivitiesDetailsViewModel, Integer>{

	
//	@Query(value = "FROM CMtServiceReportingActivitiesDetailsViewModel model WHERE model.is_delete = 0 and model.service_reporting_master_transaction_id = ?1 and model.company_id = ?2")
//	List<CMtServiceReportingActivitiesDetailsViewModel> FnShowServiceReportingActivitiesDetailsRecordForUpdate(
//			int service_reporting_master_transaction_id, int company_id);

	
	@Query(value = "SELECT * FROM mtv_service_reporting_activities_details  WHERE is_delete = 0 and service_reporting_master_transaction_id = ?1 and company_id = ?2",nativeQuery = true)
	List<Map<String, Object>> FnShowServiceReportingActivitiesDetailRecordForUpdate(
			int service_reporting_master_transaction_id, int company_id);

}
