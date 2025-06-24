package com.erp.XmProductionSpinningPlanParameter.Controller;

import com.erp.XmProductionSpinningPlanParameter.Model.CXmProductionSpinningPlanParameterModel;
import com.erp.XmProductionSpinningPlanParameter.Model.CXmProductionSpinningPlanParameterViewModel;
import com.erp.XmProductionSpinningPlanParameter.Service.IXmProductionSpinningPlanParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/XmProductionSpinningPlanParameter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXmProductionSpinningPlanParameterController {

	@Autowired
	IXmProductionSpinningPlanParameterService iXmProductionSpinningPlanParameterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CXmProductionSpinningPlanParameterModel XmProductionSpinningPlanParameterModel) {
		return iXmProductionSpinningPlanParameterService.FnAddUpdateRecord(XmProductionSpinningPlanParameterModel);

	}

	@DeleteMapping("/FnDeleteRecord/{production_spinning_plan_parameter_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int production_spinning_plan_parameter_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iXmProductionSpinningPlanParameterService.FnDeleteRecord(production_spinning_plan_parameter_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CXmProductionSpinningPlanParameterViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CXmProductionSpinningPlanParameterViewModel> cXmProductionSpinningPlanParameterViewModel = iXmProductionSpinningPlanParameterService.FnShowAllActiveRecords(pageable, company_id);
		return cXmProductionSpinningPlanParameterViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{production_spinning_plan_parameter_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int production_spinning_plan_parameter_id, @PathVariable int company_id) {
		return iXmProductionSpinningPlanParameterService.FnShowParticularRecordForUpdate(production_spinning_plan_parameter_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iXmProductionSpinningPlanParameterService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{production_spinning_plan_parameter_id}/{company_id}")
	public Page<CXmProductionSpinningPlanParameterViewModel> FnShowParticularRecord(@PathVariable int production_spinning_plan_parameter_id, Pageable pageable, @PathVariable int company_id) {
		return iXmProductionSpinningPlanParameterService.FnShowParticularRecord(production_spinning_plan_parameter_id, pageable, company_id);

	}


}
