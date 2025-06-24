package com.erp.AmApplicationErrorLogs.Controller;

import com.erp.AmApplicationErrorLogs.Model.CAmApplicationErrorLogsModel;
import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/AmApplicationErrorLogs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAmApplicationErrorLogsController {

	@Autowired
	CAmApplicationErrorLogsService amApplicationErrorLogsService;

	public Map<String, Object> addErrorLog(int company_id, String error_source, String error_source_name,
	                                       int error_code, String error_message) {
		Map<String, Object> responce = new HashMap<>();
		try {
			CAmApplicationErrorLogsModel respContent = amApplicationErrorLogsService.addErrorLog(company_id, error_source,
					error_source_name, error_code, error_message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return responce;
	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CAmApplicationErrorLogsModel> getamApplicationErrorLogs(@PathVariable int company_id, Pageable pageable) {
		return amApplicationErrorLogsService.getamApplicationErrorLogs(company_id, pageable);

	}

//	@PostMapping("/FnAddUpdateRecord")
//	public Map<String, Object> FnAddUpdateRecord(@RequestBody CAmApplicationErrorLogsModel AmApplicationErrorLogsModel)
//			throws SQLException {
//		Map<String, Object> responce = new HashMap<>();
//		try {
//			CAmApplicationErrorLogsModel respContent = iAmApplicationErrorLogsRepository
//					.save(AmApplicationErrorLogsModel);
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				responce.put("success", "0");
//				responce.put("data", "");
//				responce.put("error", e.getMessage());
//				return responce;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			responce.put("success", "0");
//			responce.put("data", "");
//			responce.put("error", e.getMessage());
//
//			return responce;
//		}
//
//		return responce;
//
//	}

}
