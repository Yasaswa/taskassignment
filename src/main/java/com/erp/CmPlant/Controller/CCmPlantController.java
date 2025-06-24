package com.erp.CmPlant.Controller;

import com.erp.CmPlant.Model.CCmPlantModel;
import com.erp.CmPlant.Model.CCmPlantViewModel;
import com.erp.CmPlant.Service.ICmPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/CmPlant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCmPlantController {

	@Autowired
	ICmPlantService iCmPlantService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CCmPlantModel CmPlantModel) {
		return iCmPlantService.FnAddUpdateRecord(CmPlantModel);

	}

	@DeleteMapping("/FnDeleteRecord/{plant_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int plant_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iCmPlantService.FnDeleteRecord(plant_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CCmPlantViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CCmPlantViewModel> cCmPlantViewModel = iCmPlantService.FnShowAllActiveRecords(pageable, company_id);
		return cCmPlantViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{plant_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int plant_id, @PathVariable int company_id) {
		return iCmPlantService.FnShowParticularRecordForUpdate(plant_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iCmPlantService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{plant_id}/{company_id}")
	public Page<CCmPlantViewModel> FnShowParticularRecord(@PathVariable int plant_id, @PathVariable int company_id, Pageable pageable) {
		return iCmPlantService.FnShowParticularRecord(plant_id, company_id, pageable);

	}


}
