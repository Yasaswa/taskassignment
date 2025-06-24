package com.erp.HmLeavesApplications.Service;


import com.erp.HmLeavesApplications.Model.CHmLeavesApplicationsModel;

import java.util.Map;


public interface IHmLeavesApplicationsService {

	Map<String, Object> FnAddUpdateRecord(CHmLeavesApplicationsModel hmLeavesApplicationsModel, boolean isApprove);

//	Map<String, Object> FnDeleteRecord(int leaves_transaction_id, String deleted_by);
	Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int leaves_transaction_id);

	Map<String, Object> FnShowLeaveBalanceDetails(int company_id, String employee_code);

	Map<String, Object> FnShowExisitingCount(String employee_code, String leaves_requested_from_date,
	                                         String leaves_requested_to_date);

    Map<String, Object> FnDeleteRecord(String punch_code, String leavesRequestedFromDate, String leavesRequestedToDate, String deletedBy);

//	boolean FnShowCheckSandwich(String weeklyOFf, String leavesRequestedFromDate, String leavesRequestedToDate);
}
