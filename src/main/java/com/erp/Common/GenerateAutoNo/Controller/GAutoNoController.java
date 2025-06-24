package com.erp.Common.GenerateAutoNo.Controller;

import com.erp.Common.GenerateAutoNo.Model.CProdTransactionNo;
import com.erp.Common.GenerateAutoNo.Model.GAutoNoModel;
import com.erp.Common.GenerateAutoNo.Service.AutoGenerateNoService;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/GenerateNo")
public class GAutoNoController {

	@Autowired
	AutoGenerateNoService autoGenerateNoService;

	@PostMapping("/FnGenerateAutoTransactionNo")
	public String FnGenerateAutoNo(@RequestParam("data") JSONObject json) throws IOException {
		String jsonData = json.toString();
		Gson gson = new Gson();
		GAutoNoModel autoNoQuery = gson.fromJson(jsonData, GAutoNoModel.class);
		return autoGenerateNoService.FnGenerateAutoNo(json, autoNoQuery);
	}

	@PostMapping("/FnGenerateMaterialId")
	public String FnGenerateMaterialId(@RequestParam("data") JSONObject json) throws IOException {
		String jsonData = json.toString();
		Gson gson = new Gson();
		GAutoNoModel autoNoQuery = gson.fromJson(jsonData, GAutoNoModel.class);
		return autoGenerateNoService.FnGenerateMaterialId(json, autoNoQuery);
	}

	@PostMapping("/FnGenerateTransactionNo")
	public Map<String, Object> FnGenerateTransactionNo(@Valid  @RequestBody CProdTransactionNo cProdTransactionNo) throws IOException {
		return autoGenerateNoService.FnGenerateTransactionNo(cProdTransactionNo);
	}
}
