package com.erp.MtServiceReportingMaster.Repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtServiceReportingMaster.Model.CMtServiceReportingMasterModel;

public interface IMtServiceReportingMasterRepository extends JpaRepository<CMtServiceReportingMasterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update CMtServiceReportingMasterModel model SET model.is_delete = 1 , model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.service_reporting_master_transaction_id = ?1 AND model.company_id = ?2")
	void FnDeleteServiceReportingMasterRecord(int service_reporting_master_transaction_id, int company_id, String deleted_by);

}
