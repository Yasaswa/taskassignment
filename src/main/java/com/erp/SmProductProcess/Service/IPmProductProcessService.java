package com.erp.SmProductProcess.Service;

import com.erp.SmProductProcess.Model.CPmProductProcessModel;
import com.erp.SmProductProcess.Model.CPmProductProcessViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IPmProductProcessService {

	Map<String, Object> FnAddUpdateRecord(CPmProductProcessModel cPmProductProcessModel);

	Object FnDeleteRecord(int product_process_id, String deleted_by);

	Page<CPmProductProcessViewModel> FnShowAllActiveRecords(Pageable pageable);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_process_id);

	Page<CPmProductProcessViewModel> FnShowParticularRecord(int product_process_id, Pageable pageable);

}
