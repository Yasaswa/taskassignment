package com.erp.Common.Properties.Controller;

import com.erp.Common.Properties.Model.CPropertiesModel;
import com.erp.Common.Properties.Model.CPropertiesViewModel;
import com.erp.Common.Properties.Service.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/properties")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPropertiesController {

	@Autowired
	IPropertiesService iPropertiesService;

	@GetMapping("/FnShowAllRecords")
	public List<CPropertiesViewModel> FnShowAllRecords() throws SQLException {
		List<CPropertiesViewModel> cPropertiesViewModel = cPropertiesViewModel = iPropertiesService.FnShowAllRecords();
		return cPropertiesViewModel;
	}

	@GetMapping("/FnShowGroupRecord/{controlName}/{company_id}")
	public List<CPropertiesViewModel> FnShowParticularRecord(@PathVariable String controlName,
	                                                         @PathVariable int company_id) throws SQLException {
		List<CPropertiesViewModel> cPropertiesViewModel = iPropertiesService.FnShowParticularRecord(controlName,
				company_id);
		return cPropertiesViewModel;
	}

//	Form APis

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CPropertiesModel AmPropertiesModel) {
		Map<String, Object> responce = new HashMap<>();
		responce = iPropertiesService.FnAddUpdateRecord(AmPropertiesModel);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{property_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable long property_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iPropertiesService.FnDeleteRecord(property_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CPropertiesViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id)
			throws SQLException {
		Page<CPropertiesViewModel> cAmPropertiesViewModel = iPropertiesService.FnShowAllActiveRecords(pageable,
				company_id);
		return cAmPropertiesViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{property_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int property_id,
	                                                           @PathVariable int company_id) {
		Map<String, Object> responce = new HashMap<>();
		responce = iPropertiesService.FnShowParticularRecordForUpdate(property_id, company_id);
		return responce;
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		Page<Map<String, Object>> AmPropertiesRptModel = iPropertiesService.FnShowAllReportRecords(pageable, company_id);
		return AmPropertiesRptModel;

	}

	@GetMapping("/FnShowParticularRecords/{property_id}/{company_id}")
	public Page<CPropertiesViewModel> FnShowParticularRecordById(@PathVariable int property_id, Pageable pageable,
	                                                             @PathVariable int company_id) {
		Page<CPropertiesViewModel> cAmPropertiesViewModel = iPropertiesService.FnShowParticularRecordById(property_id,
				pageable, company_id);
		return cAmPropertiesViewModel;

	}
}
