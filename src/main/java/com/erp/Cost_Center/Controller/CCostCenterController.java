package com.erp.Cost_Center.Controller;

import com.erp.Cost_Center.Model.CCostCenterModel;
import com.erp.Cost_Center.Service.CCostCenterServiceImpl;
import com.erp.Cost_Center.Service.ICostCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/costcenter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCostCenterController {

	@Autowired
	CCostCenterServiceImpl objCCostCenterServiceImpl;

	@Autowired
	ICostCenterService iCostCenterService;

	@Autowired
	DataSource dataSource;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CCostCenterModel cCostCenterModel) throws SQLException {
		Map<String, Object> resp = iCostCenterService.FnAddUpdateRecord(cCostCenterModel);
		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{cost_center_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int cost_center_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iCostCenterService.FnDeleteRecord(cost_center_id, company_id, deleted_by);
	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	Map<String, Object> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Map<String, Object> cCostCenterModel = iCostCenterService.FnShowAllActiveRecords(pageable, company_id);
		return cCostCenterModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{cost_center_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int cost_center_id, @PathVariable int company_id) throws SQLException {
		Map<String, Object> resp = iCostCenterService.FnShowParticularRecordForUpdate(cost_center_id, company_id);
		return resp;

	}

//	@GetMapping("/FnShowReportRecords/{company_id}")
//	Page FnShowReportRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
//		return iCostCenterService.FnShowReportRecords(pageable);
//	}

	@GetMapping("/FnShowReportRecords/{company_id}")
	Page<Map<String, Object>> FnShowReportRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		return iCostCenterService.FnShowReportRecords(pageable, company_id);
	}

}
