package com.erp.CmPaymentSchedule.Service;

import com.erp.CmPaymentSchedule.Model.CCmPaymentScheduleModel;
import com.erp.CmPaymentSchedule.Model.CCmPaymentScheduleViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICmPaymentScheduleService {

	Map<String, Object> FnAddUpdateRecord(CCmPaymentScheduleModel cCmPaymentScheduleModel);

	Object FnDeleteRecord(int payment_schedule_id, int company_id, String deleted_by);

	Page<CCmPaymentScheduleViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int payment_schedule_id, int company_id);

	Page<CCmPaymentScheduleViewModel> FnShowParticularRecord(int payment_schedule_id, Pageable pageable, int company_id);

}
