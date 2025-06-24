package com.erp.SmProductFgStockDetails.Controller;

import com.erp.SmProductFgStockDetails.Service.ISmProductFgStockDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/SmProductFgStockDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgStockDetailsController {

	@Autowired
	ISmProductFgStockDetailsService iSmProductFgStockDetailsService;

	//	@Scheduled(cron = "0 0 0 * * ?") // Run every day at 12 AM
////	@Scheduled(fixedDelay = 2 * 60 * 1000) // Run every 2 minutes
	public Map<String, Object> FnUpdateScheduledRmStockSummary() {
		System.out.println("Stock Updation method called!....");
		return iSmProductFgStockDetailsService.FnUpdateScheduledFgStock();
	}

}
