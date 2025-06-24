package com.erp.XmProductionHoliday.Service;

import com.erp.XmProductionHoliday.Model.CXmProductionHolidayModel;
import com.erp.XmProductionHoliday.Model.CXmProductionHolidayViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IXmProductionHolidayService {

	Map<String, Object> FnAddUpdateRecord(CXmProductionHolidayModel xmProductionHolidayModel);

	Object FnDeleteRecord(int production_holiday_id, String deleted_by, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int production_holiday_id, int company_id);

	Page<CXmProductionHolidayModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

//	Page<CXmProductionHolidayRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

	Page<CXmProductionHolidayViewModel> FnShowParticularRecord(int production_holiday_id, Pageable pageable, int company_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
