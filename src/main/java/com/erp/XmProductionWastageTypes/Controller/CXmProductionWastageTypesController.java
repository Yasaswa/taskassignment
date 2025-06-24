package com.erp.XmProductionWastageTypes.Controller;

import com.erp.XmProductionWastageTypes.Model.CXmProductionWastageTypesModel;
import com.erp.XmProductionWastageTypes.Model.CXmProductionWastageTypesViewModel;
import com.erp.XmProductionWastageTypes.Service.IXmProductionWastageTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/XmProductionWastageTypes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXmProductionWastageTypesController {

	@Autowired
	IXmProductionWastageTypesService iXmProductionWastageTypesService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CXmProductionWastageTypesModel XmProductionWastageTypesModel) {
		return iXmProductionWastageTypesService.FnAddUpdateRecord(XmProductionWastageTypesModel);

	}

	@DeleteMapping("/FnDeleteRecord/{production_wastage_types_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int production_wastage_types_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iXmProductionWastageTypesService.FnDeleteRecord(production_wastage_types_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CXmProductionWastageTypesViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CXmProductionWastageTypesViewModel> cXmProductionWastageTypesViewModel = iXmProductionWastageTypesService.FnShowAllActiveRecords(pageable, company_id);
		return cXmProductionWastageTypesViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{production_wastage_types_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int production_wastage_types_id, @PathVariable int company_id) {
		return iXmProductionWastageTypesService.FnShowParticularRecordForUpdate(production_wastage_types_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iXmProductionWastageTypesService.FnShowAllReportRecords(pageable);

	}

	@GetMapping("/FnShowParticularRecords/{production_wastage_types_id}/{company_id}")
	public Page<CXmProductionWastageTypesViewModel> FnShowParticularRecord(@PathVariable int production_wastage_types_id, Pageable pageable, @PathVariable int company_id) {
		return iXmProductionWastageTypesService.FnShowParticularRecord(production_wastage_types_id, pageable, company_id);

	}
}
