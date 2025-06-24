package com.erp.Common.MasterData.Controller;

import com.erp.Common.MasterData.Model.CGlobalQueryObjectModel;
import com.erp.Common.MasterData.Service.IMasterDataService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/masterData")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMasterDataController {

	@Autowired
	IMasterDataService iMasterDataService;

	@PostMapping("/FnShowParticularRecord/{company_id}")
	public Map<String, Object> FnShowParticularRecords(@RequestParam("data") JSONObject json, @PathVariable int company_id) {
		return iMasterDataService.FnShowParticularRecord(json, company_id);
	}

	@PostMapping("/FnShowParticularRecordByOperator/{company_id}")
	public Map<String, Object> FnShowParticularRecordByOperator(@RequestParam("data") JSONObject json, @PathVariable int company_id) {
		return iMasterDataService.FnShowParticularRecordByOperator(json, company_id);
	}


	@PostMapping("/FnShowRecords")
	public List<Map<String, Object>> FnShowRecords(@RequestParam("data") JSONObject json, HttpServletRequest req) throws IOException {
		String jsonData = json.toString();

		Gson gson = new Gson();
		CGlobalQueryObjectModel globalQuery = gson.fromJson(jsonData, CGlobalQueryObjectModel.class);
		return iMasterDataService.FnShowRecords(json, globalQuery);
	}


	@PostMapping("/FnEvictRecords")
	public List<Map<String, Object>> FnEvictRecords(@RequestParam("data") JSONObject json, HttpServletRequest req) throws IOException {
		String jsonData = json.toString();

		Gson gson = new Gson();
		CGlobalQueryObjectModel globalQuery = gson.fromJson(jsonData, CGlobalQueryObjectModel.class);
		return iMasterDataService.FnEvictRecords(json, globalQuery);

	}

	@PostMapping("/EvictCatche")
//	@CacheEvict(value = "catcher", allEntries = true)
	public String EvictCache() {
		return "Cache evict successfully.";

	}

	@PostMapping("/FnShowMaterialItems")
	public Map<String, Object> FnShowMaterialItems(@RequestParam("materialSearchObject") JSONObject materialSearchObject) {
		return iMasterDataService.FnShowMaterialItems(materialSearchObject);
	}


	@PostMapping("/FnShowSearchMaterialItems")
	public Map<String, Object> FnShowSearchMaterialItems(@RequestParam("materialSearchObject") JSONObject materialSearchObject) {
		return iMasterDataService.FnShowSearchMaterialItems(materialSearchObject);
	}



}
