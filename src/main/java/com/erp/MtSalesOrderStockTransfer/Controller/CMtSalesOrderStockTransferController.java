package com.erp.MtSalesOrderStockTransfer.Controller;

import com.erp.MtSalesOrderStockTransfer.Service.IMtSalesOrderStockTransferService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/MtSalesOrderStockTransfer")
public class CMtSalesOrderStockTransferController {

	@Autowired
	IMtSalesOrderStockTransferService iMtSalesOrderStockTransferService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("RequestTransferStock") JSONObject requestTransferStock) throws JsonProcessingException {
		return  iMtSalesOrderStockTransferService.FnAddUpdateRecord(requestTransferStock);
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{sales_order_transfer_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int sales_order_transfer_id) {
		return  iMtSalesOrderStockTransferService.FnShowParticularRecordForUpdate(sales_order_transfer_id);
	}

}
