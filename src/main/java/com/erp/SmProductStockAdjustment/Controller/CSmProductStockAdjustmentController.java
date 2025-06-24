package com.erp.SmProductStockAdjustment.Controller;

import com.erp.SmProductStockAdjustment.Service.ISmProductStockAdjustmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/SmProductStockAdjustment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductStockAdjustmentController {

	@Autowired
	ISmProductStockAdjustmentService iSmProductStockAdjustmentService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("requestStockAdjustment") JSONObject stockAdjustmentJson) throws JsonProcessingException {
		return iSmProductStockAdjustmentService.FnAddUpdateRecord(stockAdjustmentJson);
	}

	@DeleteMapping("/FnDeleteRecord/{stock_adjustment_transaction_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int stock_adjustment_transaction_id, @PathVariable int company_id,@PathVariable String deleted_by) {
		return iSmProductStockAdjustmentService.FnDeleteRecord(stock_adjustment_transaction_id, company_id,deleted_by);

	}


	@GetMapping("/FnShowParticularRecordForUpdate/{stock_adjustment_transaction_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int stock_adjustment_transaction_id,@PathVariable int company_id) {
		return iSmProductStockAdjustmentService.FnShowParticularRecordForUpdate(stock_adjustment_transaction_id,company_id);
	}



}
