package com.erp.HtSalarySummary.Service;

import com.erp.HtSalarySummary.Model.CHtSalarySummaryModel;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface IHtSalarySummaryService {

    Map<String, Object> FnAddUpdateRecord(JSONObject MtSalaryProcessingData, int company_id);

    Object FnDeleteRecord(int salary_transaction_id, int company_id);

    Map<String, Object> FnShowParticularRecordForUpdate(int salary_transaction_id, int company_id);

	Map<String, Object> FnDisplaySalariesCalculations(
			@RequestParam("MtSalaryProcessingFilters") JSONObject MtSalaryProcessingFilters, int company_id);
}
