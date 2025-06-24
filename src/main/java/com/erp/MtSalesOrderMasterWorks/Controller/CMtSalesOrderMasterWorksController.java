package com.erp.MtSalesOrderMasterWorks.Controller;

import com.erp.MtSalesOrderMasterWorks.Model.CMtSalesOrderMasterWorksSummaryViewModel;
import com.erp.MtSalesOrderMasterWorks.Service.IMtSalesOrderMasterWorksService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/MtSalesOrderMasterWorks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMtSalesOrderMasterWorksController {


	@Autowired
	IMtSalesOrderMasterWorksService iMtSalesOrderMasterWorksService;


	@PostMapping("/FnAddUpdateRecord/{isApprove}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("MtSalesOrderWorksData") JSONObject jsonObject, @PathVariable boolean isApprove) {
		Map<String, Object> response = iMtSalesOrderMasterWorksService.FnAddUpdateRecord(jsonObject, isApprove);
		return response;

	}

	@DeleteMapping("/FnDeleteRecord/{sales_order_version}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("sales_order_no") String sales_order_no,
	                                          @PathVariable int sales_order_version, @PathVariable int company_id) {
		return iMtSalesOrderMasterWorksService.FnDeleteRecord(sales_order_no, sales_order_version, company_id);

	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CMtSalesOrderMasterWorksSummaryViewModel> cMtSalesOrderMasterTradingSummaryViewModel = iMtSalesOrderMasterWorksService.FnShowAllActiveRecords(pageable, company_id);
		return cMtSalesOrderMasterTradingSummaryViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{sales_order_master_transaction_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int sales_order_master_transaction_id, @PathVariable int company_id) {
		return iMtSalesOrderMasterWorksService.FnShowParticularRecordForUpdate(sales_order_master_transaction_id, company_id);
	}

	@GetMapping("/FnShowParticularRecords/{sales_order_master_transaction_id}/{company_id}")
	public Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowParticularRecord(@PathVariable int sales_order_master_transaction_id, Pageable pageable, @PathVariable int company_id) {
		return iMtSalesOrderMasterWorksService.FnShowParticularRecord(sales_order_master_transaction_id, pageable, company_id);

	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{sales_order_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("sales_order_no") String sales_order_no,
	                                                                 @PathVariable int sales_order_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iMtSalesOrderMasterWorksService.FnShowAllDetailsandMastermodelRecords(sales_order_no, sales_order_version, financial_year,
				company_id);
	}

	@GetMapping("/FnShowSalesOrderDetailsTradingCustomerRecord/{company_id}")
	public Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(@RequestParam("customer_order_no") String customer_order_no, Pageable pageable, @PathVariable int company_id) {
		return iMtSalesOrderMasterWorksService.FnShowSalesOrderDetailsTradingCustomerRecord(customer_order_no, pageable, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iMtSalesOrderMasterWorksService.FnShowAllReportRecords(pageable, reportType);

	}

}
