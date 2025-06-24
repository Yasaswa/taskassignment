package com.erp.Employee.Employee_Band.Service;

import com.erp.Employee.Employee_Band.Model.CEmployeeBandModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IEmployeeBandService {

	JSONObject FnAddUpdateRecord(CEmployeeBandModel cEmployeeBandModel);

	Object FnDeleteRecord(int employee_band_id);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int employee_band_id);

	JSONObject FnShowParticularRecord(int company_id, int employee_band_id);

}
