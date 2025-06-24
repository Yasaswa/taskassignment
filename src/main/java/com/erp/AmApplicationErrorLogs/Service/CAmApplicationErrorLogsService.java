package com.erp.AmApplicationErrorLogs.Service;

import com.erp.AmApplicationErrorLogs.Model.CAmApplicationErrorLogsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CAmApplicationErrorLogsService {

	CAmApplicationErrorLogsModel addErrorLog(int company_id, String error_source, String error_source_name, int error_code, String error_message);

	Page<CAmApplicationErrorLogsModel> getamApplicationErrorLogs(int company_id, Pageable pageable);

}
