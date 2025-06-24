package com.erp.CmMachineDetails.Controller;

import com.erp.CmMachineDetails.Model.CCmMachineModel;
import com.erp.CmMachineDetails.Model.CCmMachineProcessModel;
import com.erp.CmMachineDetails.Model.CCmMachineProcessViewModel;
import com.erp.CmMachineDetails.Model.CCmMachineViewModel;
import com.erp.CmMachineDetails.Service.ICmMachineService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/CmMachineDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCmMachineDetailsController {

	@Autowired
	ICmMachineService iCmMachineService;


	@PostMapping("/FnAddUpdateMachineRecord")
	public Map<String, Object> FnAddUpdateMachineRecord(@RequestBody CCmMachineModel ccmMachineModel) {
		return iCmMachineService.FnAddUpdateMachineRecord(ccmMachineModel);

	}

	@PostMapping("/FnAddUpdateMachineProcessRecord")
	public Object FnAddUpdateMachineProcessRecord(@RequestParam("machineProcessJson") JSONObject jsonObject) throws SQLException {
		JSONObject responce = new JSONObject();
		responce = iCmMachineService.FnAddUpdateMachineProcessRecord(jsonObject);
		return responce.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{machine_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int machine_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iCmMachineService.FnDeleteRecord(machine_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveMachineRecords/{company_id}")
	public Page<CCmMachineViewModel> FnShowAllActiveMachineRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CCmMachineViewModel> ccmMachineViewModel = iCmMachineService.FnShowAllActiveMachineRecords(pageable, company_id);
		return ccmMachineViewModel;
	}


	@GetMapping("/FnShowParticularMachineRecordForUpdate/{machine_id}/{company_id}")
	public Map<String, Object> FnShowParticularMachineRecordForUpdate(@PathVariable int machine_id, @PathVariable int company_id) {
		return iCmMachineService.FnShowParticularMachineRecordForUpdate(machine_id, company_id);
	}


	@GetMapping("/FnShowAllActiveMachineProcessRecords/{company_id}")
	public List<CCmMachineProcessViewModel> FnShowAllActiveMachineProcessRecords(@PathVariable int company_id) throws SQLException {
		List<CCmMachineProcessViewModel> ccmMachineProcessViewModel = iCmMachineService.FnShowAllActiveMachineProcessRecords(company_id);
		return ccmMachineProcessViewModel;
	}

	@GetMapping("/FnShowParticularMachineProcessRecord/{machine_id}/{company_id}")
	public List<CCmMachineProcessModel> FnShowParticularMachineProcessRecord(@PathVariable int machine_id,
	                                                                         @PathVariable int company_id) throws SQLException {
		List<CCmMachineProcessModel> ccmMachineProcessModel = iCmMachineService
				.FnShowParticularMachineProcessRecord(machine_id, company_id);
		return ccmMachineProcessModel;
	}


	@GetMapping("/FnShowAllMachineReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllMachineReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iCmMachineService.FnShowAllMachineReportRecords(pageable, company_id);

	}

}



