package com.erp.SmProductCurrentRate.Controller;


import java.util.Map;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.erp.SmProductCurrentRate.Service.ISmProductCurrentRateService;

@RestController
@RequestMapping("/api/SmProductCurrentRate")
public class CSmProductCurrentRateController {

	@Autowired
	ISmProductCurrentRateService iSmProductCurrentRateService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam ("ProductCurrentRateData") JSONObject jsonObject) {
		 Map<String, Object> responce =  iSmProductCurrentRateService.FnAddUpdateRecord(jsonObject);
		 return responce;

	}

}
