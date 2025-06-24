package com.erp.Department.Service;

import com.erp.Department.Model.CDepartmentModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IDepartmentService {

	Map<String, Object> FnAddUpdateRecord(CDepartmentModel cDepartmentModel);

	Object FnDeleteRecord(int department_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int department_id, int company_id);

	JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int department_id);

	Object FnShowAllReportRecords(Pageable pageable);


}
