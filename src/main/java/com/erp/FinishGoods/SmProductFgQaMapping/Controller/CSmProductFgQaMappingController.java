package com.erp.FinishGoods.SmProductFgQaMapping.Controller;

import com.erp.FinishGoods.SmProductFgQaMapping.Model.CSmProductFgQaMappingModel;
import com.erp.FinishGoods.SmProductFgQaMapping.Model.CSmProductFgQaMappingViewModel;
import com.erp.FinishGoods.SmProductFgQaMapping.Service.ISmProductFgQaMappingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/SmProductFgQaMapping")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgQaMappingController {

	@Autowired
	ISmProductFgQaMappingService iSmProductFgQaMappingService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestParam("productQaMappingJson") JSONObject jsonObject) throws SQLException {
		JSONObject responce = new JSONObject();
		responce = iSmProductFgQaMappingService.FnAddUpdateRecord(jsonObject);
		return responce.toString();

	}

	@GetMapping("/FnShowAllActiveRecords")
	Page<CSmProductFgQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Page<CSmProductFgQaMappingViewModel> cSmProductFgQaMappingViewModel = null;
		try {
			cSmProductFgQaMappingViewModel = iSmProductFgQaMappingService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductFgQaMappingViewModel;
	}

	@GetMapping("/FnShowParticularRecords/{product_fg_id}")
	public Page<CSmProductFgQaMappingModel> FnShowParticularRecord(@PathVariable int product_fg_id, Pageable pageable)
			throws SQLException {
		Page<CSmProductFgQaMappingModel> cSmProductFgQaMappingModel = null;
		try {
			cSmProductFgQaMappingModel = iSmProductFgQaMappingService.FnShowParticularRecord(product_fg_id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductFgQaMappingModel;

	}

}
