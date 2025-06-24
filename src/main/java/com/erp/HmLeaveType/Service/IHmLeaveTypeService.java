package com.erp.HmLeaveType.Service;

import com.erp.HmLeaveType.Model.CHmLeaveTypeModel;

import java.util.Map;

public interface IHmLeaveTypeService {

	Map<String, Object> FnAddUpdateRecord(CHmLeaveTypeModel cHmLeaveTypeModel);

	Map<String, Object> FnDeleteRecord(int leave_type_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int leave_type_id);

//	Page<CHmLeaveTypeViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

}
