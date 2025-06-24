package com.erp.Cost_Center.Service;

import com.erp.Cost_Center.Model.CCostCenterModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface ICostCenterService {

	Map<String, Object> FnAddUpdateRecord(CCostCenterModel cCostCenterModel);

	Object FnDeleteRecord(int cost_center_id, int company_id, String deleted_by);

	Map<String, Object> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int cost_center_id, int company_id);

	Page<Map<String, Object>> FnShowReportRecords(Pageable pageable, int company_id);

}
