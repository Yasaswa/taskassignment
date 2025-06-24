package com.erp.SmProductServiceActivityMaster.Service;


import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.erp.SmProductServiceActivityMaster.Model.CSmProductServiceActivityMasterModel;

public interface ISmProductServiceActivityMasterService {

	Map<String, Object> FnAddUpdateRecord(CSmProductServiceActivityMasterModel cSmProductServiceActivityMasterModel);

	Map<String, Object> FnDeleteRecord(int product_service_activity_master_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_service_activity_master_id, int company_id);

	Map<String, Object> FnShowAllActiveRecords(int company_id);


	

}
