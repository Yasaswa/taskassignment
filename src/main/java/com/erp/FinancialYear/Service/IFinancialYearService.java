package com.erp.FinancialYear.Service;

import com.erp.FinancialYear.Model.CFinancialYearModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IFinancialYearService {

	JSONObject FnAddUpdateRecord(CFinancialYearModel financialYearModel);

	Object FnDeleteRecord(int financial_year_id, String deleted_by);


	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int company_id, int financial_year_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int financial_year_id);

	Object FnShowAllReportRecords(Pageable pageable);


}
