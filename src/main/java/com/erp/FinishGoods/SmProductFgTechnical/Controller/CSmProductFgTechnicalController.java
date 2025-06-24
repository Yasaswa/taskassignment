package com.erp.FinishGoods.SmProductFgTechnical.Controller;

import com.erp.FinishGoods.SmProductFgTechnical.Service.ISmProductFgTechnicalService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/SmProductFgTechnical")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgTechnicalController {

	@Autowired
	ISmProductFgTechnicalService iSmProductFgTechnicalService;

//	@PostMapping("/FnAddUpdateRecord")
//	public Object FnAddUpdateRecord(@RequestBody CSmProductFgTechnicalModel SmProductFgTechnicalModel) throws SQLException {
//		JSONObject resp = new JSONObject();
//		try {
//			resp = iSmProductFgTechnicalService.FnAddUpdateRecord(SmProductFgTechnicalModel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp.toString();
//
//	}

	@DeleteMapping("/FnDeleteRecord/{product_fg_technical_id}")
	public Object FnDeleteRecord(@PathVariable int product_fg_technical_id) {
		return iSmProductFgTechnicalService.FnDeleteRecord(product_fg_technical_id);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_fg_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_fg_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iSmProductFgTechnicalService.FnShowParticularRecordForUpdate(product_fg_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}


}
