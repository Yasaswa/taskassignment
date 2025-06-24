package com.erp.AmTemplatesCommunications.Controller;

import com.erp.AmTemplatesCommunications.Model.CAmTemplatesCommunicationsModel;
import com.erp.AmTemplatesCommunications.Model.CAmTemplatesCommunicationsViewModel;
import com.erp.AmTemplatesCommunications.Service.IAmTemplatesCommunicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/AmTemplatesCommunications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAmTemplatesCommunicationsController {

	@Autowired
	IAmTemplatesCommunicationsService iAmTemplatesCommunicationsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CAmTemplatesCommunicationsModel AmTemplatesCommunicationsModel) {
		return iAmTemplatesCommunicationsService.FnAddUpdateRecord(AmTemplatesCommunicationsModel);

	}

	@DeleteMapping("/FnDeleteRecord/{communications_templates_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int communications_templates_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iAmTemplatesCommunicationsService.FnDeleteRecord(communications_templates_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CAmTemplatesCommunicationsViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CAmTemplatesCommunicationsViewModel> cAmTemplatesCommunicationsViewModel = iAmTemplatesCommunicationsService.FnShowAllActiveRecords(pageable, company_id);
		return cAmTemplatesCommunicationsViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{communications_templates_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int communications_templates_id, @PathVariable int company_id) {
		return iAmTemplatesCommunicationsService.FnShowParticularRecordForUpdate(communications_templates_id, company_id);
	}

//	@GetMapping("/FnShowAllReportRecords/{company_id}")
//	public Page<CAmTemplatesCommunicationsRptModel> FnShowAllReportRecords(Pageable pageable,  @PathVariable int company_id) {
//		return iAmTemplatesCommunicationsService.FnShowAllReportRecords(pageable, company_id);
//
//	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iAmTemplatesCommunicationsService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{communications_templates_id}/{company_id}")
	public Page<CAmTemplatesCommunicationsViewModel> FnShowParticularRecord(@PathVariable int communications_templates_id, Pageable pageable, @PathVariable int company_id) {
		return iAmTemplatesCommunicationsService.FnShowParticularRecord(communications_templates_id, pageable, company_id);

	}


}
