package com.erp.FinishGoods.SmProductFgCommercial.Controller;

import com.erp.FinishGoods.SmProductFgCommercial.Model.CSmProductFgCommercialModel;
import com.erp.FinishGoods.SmProductFgCommercial.Service.ISmProductFgCommercialService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/SmProductFgCommercial")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgCommercialController {

	@Autowired
	ISmProductFgCommercialService iSmProductFgCommercialService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CSmProductFgCommercialModel SmProductFgCommercialModel) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iSmProductFgCommercialService.FnAddUpdateRecord(SmProductFgCommercialModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{product_fg_commercial_id}")
	public Object FnDeleteRecord(@PathVariable int product_fg_commercial_id) {
		return iSmProductFgCommercialService.FnDeleteRecord(product_fg_commercial_id);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_fg_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_fg_id) throws SQLException {
		JSONObject responce = new JSONObject();
		try {
			responce = iSmProductFgCommercialService.FnShowParticularRecordForUpdate(product_fg_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce.toString();
	}


}
