package com.erp.CmCommonParameters.Controller;

import com.erp.CmCommonParameters.Model.CCmCommonParametersModel;
import com.erp.CmCommonParameters.Model.CCmCommonParametersViewModel;
import com.erp.CmCommonParameters.Service.ICmCommonParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/CmCommonParameters")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCmCommonParametersController {

	@Autowired
	ICmCommonParametersService iCmCommonParametersService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CCmCommonParametersModel CmCommonParametersModel) {
		return iCmCommonParametersService.FnAddUpdateRecord(CmCommonParametersModel);

	}

	@DeleteMapping("/FnDeleteRecord/{common_parameters_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int common_parameters_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iCmCommonParametersService.FnDeleteRecord(common_parameters_id, company_id, deleted_by);

	}

//	@GetMapping("/FnShowAllActiveRecords/{common_parameters_master_name}/{company_id}")
//	public Page<CCmCommonParametersViewModel> FnShowAllActiveRecords(@PathVariable String common_parameters_master_name, Pageable pageable, @PathVariable int company_id) throws SQLException {
//		Page<CCmCommonParametersViewModel> cCmCommonParametersViewModel = iCmCommonParametersService.FnShowAllActiveRecords(common_parameters_master_name, pageable, company_id);
//		return cCmCommonParametersViewModel;
//	}
@GetMapping("/FnShowAllActiveRecords/{common_parameters_master_name}")
public Page<CCmCommonParametersViewModel> FnShowAllActiveRecords(@PathVariable String common_parameters_master_name, Pageable pageable) throws SQLException {
	Page<CCmCommonParametersViewModel> cCmCommonParametersViewModel = iCmCommonParametersService.FnShowAllActiveRecords(common_parameters_master_name, pageable);
	return cCmCommonParametersViewModel;
}
	@GetMapping("/FnShowParticularRecordForUpdate/{common_parameters_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int common_parameters_id, @PathVariable int company_id) {
		return iCmCommonParametersService.FnShowParticularRecordForUpdate(common_parameters_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) {
		return iCmCommonParametersService.FnShowAllReportRecords(pageable);

	}

	@GetMapping("/FnShowParticularRecords/{common_parameters_id}/{company_id}")
	public Page<CCmCommonParametersViewModel> FnShowParticularRecord(@PathVariable int common_parameters_id, Pageable pageable, @PathVariable int company_id) {
		return iCmCommonParametersService.FnShowParticularRecord(common_parameters_id, pageable, company_id);

	}


}
