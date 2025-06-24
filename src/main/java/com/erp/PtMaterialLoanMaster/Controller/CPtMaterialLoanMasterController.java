package com.erp.PtMaterialLoanMaster.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.PtMaterialLoanMaster.Model.CPtMaterialLoanMasterModel;
import com.erp.PtMaterialLoanMaster.Service.IPtMaterialLoanMasterService;
import com.erp.PtMaterialLoanMaster.Service.CPtMaterialLoanMasterServiceImpl;
import com.erp.security.auth.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/PtMaterialLoanDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtMaterialLoanMasterController {

	@Autowired
	IPtMaterialLoanMasterService iPtMaterialLoanMasterService;

	@PostMapping("/FnAddUpdateRecord/{addOrIssued}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("PtMaterialLoanData") JSONObject jsonObject, @PathVariable String addOrIssued) {
		return iPtMaterialLoanMasterService.FnAddUpdateRecord(jsonObject, addOrIssued);

	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{loan_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(
			@RequestParam("loan_no") String loan_no, @PathVariable int loan_version,
			@PathVariable String financial_year, @PathVariable int company_id) throws SQLException {
		return iPtMaterialLoanMasterService.FnShowAllDetailsandMastermodelRecords(loan_no,
				loan_version, financial_year , company_id );
	}
	@DeleteMapping("/FnDeleteRecord/{material_loan_master_id}/{UserName}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int material_loan_master_id, @PathVariable String UserName) {
		return iPtMaterialLoanMasterService.FnDeleteRecord(material_loan_master_id, UserName);

	}

}
