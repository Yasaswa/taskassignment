package com.erp.FmProfitCenter.Controller;

import com.erp.FmProfitCenter.Model.CFmProfitCenterModel;
import com.erp.FmProfitCenter.Model.CFmProfitCenterViewModel;
import com.erp.FmProfitCenter.Service.IFmProfitCenterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/FmProfitCenter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CFmProfitCenterController {

	@Autowired
	IFmProfitCenterService iFmProfitCenterService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CFmProfitCenterModel FmProfitCenterModel) throws SQLException {
		JSONObject resp = iFmProfitCenterService.FnAddUpdateRecord(FmProfitCenterModel);

		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{profit_center_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int profit_center_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iFmProfitCenterService.FnDeleteRecord(profit_center_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords")
	Page<CFmProfitCenterViewModel> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Page<CFmProfitCenterViewModel> cFmProfitCenterViewModel = null;
		try {
			cFmProfitCenterViewModel = iFmProfitCenterService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cFmProfitCenterViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{profit_center_id}/{company_id}")
	public CFmProfitCenterModel FnShowParticularRecordForUpdate(@PathVariable int profit_center_id, @PathVariable int company_id)
			throws SQLException {
		CFmProfitCenterModel cFmProfitCenterModel = iFmProfitCenterService.FnShowParticularRecordForUpdate(profit_center_id, company_id);
		return cFmProfitCenterModel;
	}

	@GetMapping("/FnShowParticularRecords/{profit_center_id}")
	public Page<CFmProfitCenterViewModel> FnShowParticularRecord(@PathVariable int profit_center_id, Pageable pageable)
			throws SQLException {
		Page<CFmProfitCenterViewModel> cFmProfitCenterViewModel = null;
		try {
			cFmProfitCenterViewModel = iFmProfitCenterService.FnShowParticularRecord(profit_center_id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cFmProfitCenterViewModel;

	}

//	@GetMapping("/FnShowAllReportRecords/{company_id}")
//	public Page<CFmProfitCenterRptModel> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
//		Page<CFmProfitCenterRptModel> ProfitCenterRpt = iFmProfitCenterService.FnShowAllReportRecords(pageable, company_id);
//		return ProfitCenterRpt;
//
//	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iFmProfitCenterService.FnShowAllReportRecords(pageable, company_id);


	}

}
