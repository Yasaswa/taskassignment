package com.erp.XmProductionProcess.Controller;

import com.erp.XmProductionProcess.Model.CXmProductionProcessModel;
import com.erp.XmProductionProcess.Model.CXmProductionProcessViewModel;
import com.erp.XmProductionProcess.Service.IXmProductionProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/XmProductionProcess")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXmProductionProcessController {

	@Autowired
	IXmProductionProcessService iXmProductionProcessService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CXmProductionProcessModel XmProductionProcessModel) {
		return iXmProductionProcessService.FnAddUpdateRecord(XmProductionProcessModel);

	}

	@DeleteMapping("/FnDeleteRecord/{production_process_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int production_process_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iXmProductionProcessService.FnDeleteRecord(production_process_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CXmProductionProcessViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CXmProductionProcessViewModel> cXmProductionProcessViewModel = iXmProductionProcessService.FnShowAllActiveRecords(pageable, company_id);
		return cXmProductionProcessViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{production_process_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int production_process_id, @PathVariable int company_id) {
		return iXmProductionProcessService.FnShowParticularRecordForUpdate(production_process_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iXmProductionProcessService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{production_process_id}/{company_id}")
	public Page<CXmProductionProcessViewModel> FnShowParticularRecord(@PathVariable int production_process_id, Pageable pageable, @PathVariable int company_id) {
		return iXmProductionProcessService.FnShowParticularRecord(production_process_id, pageable, company_id);

	}


}
