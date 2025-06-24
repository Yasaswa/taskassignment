package com.erp.SmProductPr.Controller;

import com.erp.SmProductPr.Service.ISmProductPrService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/SmProductPr")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductPrController {

	@Autowired
	ISmProductPrService iSmProductPrService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("TransData") JSONObject jsonObject, @RequestParam(name = "qrCodeFile", required = false) MultipartFile qrCodeFile) {
		return iSmProductPrService.FnAddUpdateRecord(jsonObject, qrCodeFile);

	}

	@DeleteMapping("/FnDeleteRecord/{product_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable String product_pr_id, @PathVariable String deleted_by) {
		return iSmProductPrService.FnDeleteRecord(product_pr_id, deleted_by);

	}


}
