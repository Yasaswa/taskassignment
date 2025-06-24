package com.erp.Common.Properties.Service;

import com.erp.Common.Properties.Model.CPropertiesModel;
import com.erp.Common.Properties.Model.CPropertiesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IPropertiesService {

	List<CPropertiesViewModel> FnShowAllRecords();

	List<CPropertiesViewModel> FnShowParticularRecord(String controlName, int company_id);

	Map<String, Object> FnAddUpdateRecord(CPropertiesModel cAmPropertiesModel);

	Object FnDeleteRecord(long property_id, int company_id, String deleted_by);

	Page<CPropertiesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int property_id, int company_id);

	Page<CPropertiesViewModel> FnShowParticularRecordById(int property_id, Pageable pageable, int company_id);

}
