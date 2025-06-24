package com.erp.XtWeavingProductionWarpingMaster.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.erp.StIndentIssueMaster.Repository.IStIndentMaterialIssueDetailRepository;
import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingDetailsViewModel;
import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingBottomDetailsModel;
import com.erp.XtWeavingProductionWarpingMaster.Repository.IXtWeavingProductionWarpingDetailsRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.XtWeavingProductionWarpingMaster.Service.IXtWeavingProductionWarpingMasterService;

import javax.transaction.Transactional;


@RestController
@RequestMapping("/api/XtWeavingProductionWarping")
public class CXtWeavingProductionWarpingMasterController {

	@Autowired
	IXtWeavingProductionWarpingMasterService iXtWeavingProductionWarpingMasterService;

	@Autowired
	IXtWeavingProductionWarpingDetailsRepository iXtWeavingProductionWarpingDetailsRepository;

	@Autowired
	IStIndentMaterialIssueDetailRepository iStIndentMaterialIssueDetailRepository;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("WeavingProductionWarpingData") JSONObject jsonObject)  {
		Map<String, Object> responce = iXtWeavingProductionWarpingMasterService.FnAddUpdateRecord(jsonObject);
		return responce;
	}

	@DeleteMapping("/FnDeleteRecord/{weaving_production_warping_master_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int weaving_production_warping_master_id, @PathVariable String deleted_by) {
		Map<String, Object> responce = iXtWeavingProductionWarpingMasterService.FnDeleteRecord(weaving_production_warping_master_id, deleted_by);
		return responce;
	}

	@GetMapping("/FnChangePrintStatus/{weaving_production_warping_master_id}/{company_id}")
	public Map<String, Object> FnChangePrintStatus(@PathVariable int weaving_production_warping_master_id, @PathVariable int company_id)  {
		Map<String, Object> responce = iXtWeavingProductionWarpingMasterService.FnChangePrintStatus(weaving_production_warping_master_id);
		return responce;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{weaving_production_warping_master_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int weaving_production_warping_master_id, @PathVariable int company_id)  {
		Map<String, Object> responce = iXtWeavingProductionWarpingMasterService.FnShowParticularRecordForUpdate(weaving_production_warping_master_id, company_id);
		return responce;
	}
	

	@GetMapping("/FnShowParticularWarpingShiftSummary/{warping_production_date}/{company_id}")
	public Map<String, Object> FnShowParticularWarpingShiftSummary(@PathVariable String warping_production_date, @PathVariable int company_id) {
		return iXtWeavingProductionWarpingMasterService.FnShowParticularWarpingShiftSummary(warping_production_date, company_id);
	}


	@Transactional
	@GetMapping("/FnGetWarpingMasterData/{set_no}/{company_id}")
	public Map<String, Object> FnGetWarpingMasterData(@PathVariable String set_no, @PathVariable int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {
			ArrayList<Map<String, Object>> WarpingMasterData = iStIndentMaterialIssueDetailRepository.FnGetWarpingMasterData(set_no, company_id);
			response.put("WarpingMasterData", WarpingMasterData);
		}catch (Exception e){
			response.put("data", "");
			response.put("error", e.getMessage());
		}
		return response;
	}


	@GetMapping("/FnGetWarpingProductionReportData/{company_id}/{from_date}/{to_date}/{set_no}")
	public Map<String, Object> FnGetWarpingProductionReportData(@PathVariable Integer company_id, @PathVariable String from_date, @PathVariable String to_date, @PathVariable  String set_no){
		Map<String, Object> response = new HashMap<>();
		try {
			//Warping Data For Break Per Millions
			set_no = !set_no.equals("set_no") ? set_no : null;

			List<Map<String, Object>> warpingBreaksPerMillionData =  iXtWeavingProductionWarpingDetailsRepository.FnGetBreaksPerMillion(set_no, company_id, from_date, to_date);

			List<String> setNos = warpingBreaksPerMillionData.stream().map(data -> (String) data.get("set_no")).distinct().collect(Collectors.toList());

			//Batch wise & material wise Data
			List<Map<String, Object>> batchwiseData = iXtWeavingProductionWarpingDetailsRepository.FnGetBatchWiseData(setNos, company_id);

			//Supplier Names
			List<Map<String, Object>> supplierNames = iXtWeavingProductionWarpingDetailsRepository.FnGetSupplierNames(setNos, company_id);

			List<String> customerOrderNos = supplierNames.stream().map(data -> {Object val = data.get("customer_order_no");return val != null ? val.toString().split(",")[0] : "";}).distinct().collect(Collectors.toList());

			//JobType Data
			List<Map<String, Object>> jobtypeNames = iXtWeavingProductionWarpingDetailsRepository.FnGetJobType(customerOrderNos, company_id);

			response.put("JobTypeData", jobtypeNames);
			response.put("WarpingProductionData", warpingBreaksPerMillionData);
			response.put("BatchWiseIssueData", batchwiseData);
			response.put("SupplierData", supplierNames);

		}catch (Exception e){
			response.put("data", "");
			response.put("error", e.getMessage());
		}
		return response;
	}

}
