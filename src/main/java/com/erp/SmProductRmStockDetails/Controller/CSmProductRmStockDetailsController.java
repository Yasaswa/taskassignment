package com.erp.SmProductRmStockDetails.Controller;

import com.erp.SmProductRmStockDetails.Service.ISmProductRmStockDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/SmProductRmStockDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductRmStockDetailsController {

	@Autowired
	ISmProductRmStockDetailsService iSmProductRmStockDetailsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("materialDetails") HashMap<Object, Object> materialDetails) {
		Map<String, Object> responce = iSmProductRmStockDetailsService.FnAddUpdateRecord(materialDetails);
		return responce;
	}

	@Scheduled(cron = "0 0 0 * * ?") // Run every day at 12 AM
//	@Scheduled(fixedDelay = 15 * 60 * 1000) // Run every 10 minutes
	public Map<String, Object> FnUpdateScheduledRmStockSummary() {
		System.out.println("Stock Updation method called!....");
		return iSmProductRmStockDetailsService.FnUpdateScheduledRmStockSummary();
	}


	public Map<String, Object> FnAddUpdateFGStock(@RequestParam("stockDetails") Map<String, Object> stockDetails, @PathVariable String IUFlag, @PathVariable int company_id) {
		return iSmProductRmStockDetailsService.FnAddUpdateFGStock(stockDetails, IUFlag, company_id);
	}

//	public Map<String, Object> FnAddUpdateCustomerStock(@RequestParam("stockDetails") Map<String, Object> stockDetails, @PathVariable String IUFlag, @PathVariable int company_id) {
//		return iSmProductRmStockDetailsService.FnAddUpdateCustomerStock(stockDetails, IUFlag, company_id);
//	}

	public Map<String, Object> FnAddUpdateRmStockRawMaterials(@RequestParam("stockDetails") Map<String, Object> stockDetails, @PathVariable String IUFlag, @PathVariable int company_id) {
		return iSmProductRmStockDetailsService.FnAddUpdateRmStockRawMaterials(stockDetails, IUFlag, company_id);
	}

}
