package com.erp.SmProductProcess.Controller;

import com.erp.SmProductProcess.Model.CPmProductProcessModel;
import com.erp.SmProductProcess.Model.CPmProductProcessViewModel;
import com.erp.SmProductProcess.Service.IPmProductProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/PmProductProcess")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPmProductProcessController {

	@Autowired
	IPmProductProcessService iPmProductProcessService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CPmProductProcessModel PmProductProcessModel) {
		Map<String, Object> responce = new HashMap<>();
		responce = iPmProductProcessService.FnAddUpdateRecord(PmProductProcessModel);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{product_process_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_process_id, @PathVariable String deleted_by) {
		return iPmProductProcessService.FnDeleteRecord(product_process_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords")
	public Page<CPmProductProcessViewModel> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Page<CPmProductProcessViewModel> cPmProductProcessViewModel = iPmProductProcessService.FnShowAllActiveRecords(pageable);
		return cPmProductProcessViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_process_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_process_id) {
		Map<String, Object> responce = new HashMap<>();
		responce = iPmProductProcessService.FnShowParticularRecordForUpdate(product_process_id);
		return responce;
	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) {
		return iPmProductProcessService.FnShowAllReportRecords(pageable);
	}

	@GetMapping("/FnShowParticularRecords/{product_process_id}")
	public Page<CPmProductProcessViewModel> FnShowParticularRecord(@PathVariable int product_process_id, Pageable pageable) {
		Page<CPmProductProcessViewModel> cPmProductProcessViewModel = iPmProductProcessService.FnShowParticularRecord(product_process_id, pageable);
		return cPmProductProcessViewModel;

	}


}
