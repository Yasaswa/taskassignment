package com.erp.Shift.Service;

import com.erp.Shift.Model.CShiftModel;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IShiftService {

	Map<String, Object> FnAddUpdateRecord(CShiftModel cShiftModel);

	Object FnDeleteRecord(int shift_id, int company_id, String deleted_by);

	Map<String, Object> FnShowAllRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int shift_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecord(int company_id, int shift_id);

}
