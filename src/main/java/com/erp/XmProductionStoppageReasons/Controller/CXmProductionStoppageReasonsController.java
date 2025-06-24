package com.erp.XmProductionStoppageReasons.Controller;

import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsModel;
import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsViewModel;
import com.erp.XmProductionStoppageReasons.Service.IXmProductionStoppageReasonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/XmProductionStoppageReasons")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXmProductionStoppageReasonsController {

	@Autowired
	IXmProductionStoppageReasonsService iXmProductionStoppageReasonsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CXmProductionStoppageReasonsModel XmProductionStoppageReasonsModel) {
		return iXmProductionStoppageReasonsService.FnAddUpdateRecord(XmProductionStoppageReasonsModel);

	}

	@DeleteMapping("/FnDeleteRecord/{production_stoppage_reasons_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int production_stoppage_reasons_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iXmProductionStoppageReasonsService.FnDeleteRecord(production_stoppage_reasons_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CXmProductionStoppageReasonsViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CXmProductionStoppageReasonsViewModel> cXmProductionStoppageReasonsViewModel = iXmProductionStoppageReasonsService.FnShowAllActiveRecords(pageable, company_id);
		return cXmProductionStoppageReasonsViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{production_stoppage_reasons_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int production_stoppage_reasons_id, @PathVariable int company_id) {
		return iXmProductionStoppageReasonsService.FnShowParticularRecordForUpdate(production_stoppage_reasons_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iXmProductionStoppageReasonsService.FnShowAllReportRecords(pageable);

	}

	@GetMapping("/FnShowParticularRecords/{production_stoppage_reasons_id}/{company_id}")
	public Page<CXmProductionStoppageReasonsViewModel> FnShowParticularRecord(@PathVariable int production_stoppage_reasons_id, Pageable pageable, @PathVariable int company_id) {
		return iXmProductionStoppageReasonsService.FnShowParticularRecord(production_stoppage_reasons_id, pageable, company_id);

	}


}
