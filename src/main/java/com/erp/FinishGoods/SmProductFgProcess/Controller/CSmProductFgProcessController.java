package com.erp.FinishGoods.SmProductFgProcess.Controller;

import com.erp.FinishGoods.SmProductFgProcess.Model.CSmProductFgProcessModel;
import com.erp.FinishGoods.SmProductFgProcess.Model.CSmProductFgProcessViewModel;
import com.erp.FinishGoods.SmProductFgProcess.Service.ISmProductFgProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/SmProductFgProcess")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgProcessController {

	@Autowired
	ISmProductFgProcessService iSmProductFgProcessService;

//	@PostMapping("/FnAddUpdateRecord")
//	public Object FnAddUpdateRecord(@RequestParam("productProcessJson") JSONObject jsonObject) throws SQLException {
//		JSONObject resp = new JSONObject();
//		resp = iSmProductFgProcessService.FnAddUpdateRecord(jsonObject);
//
//		return resp.toString();
//
//	}

	@GetMapping("/FnShowAllActiveRecords")
	List<CSmProductFgProcessViewModel> FnShowAllActiveRecords() throws SQLException {
		List<CSmProductFgProcessViewModel> cSmProductFgProcessViewModel = null;
		try {
			cSmProductFgProcessViewModel = iSmProductFgProcessService.FnShowAllActiveRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductFgProcessViewModel;
	}

	@GetMapping("/FnShowParticularRecords/{product_fg_id}")
	public List<CSmProductFgProcessModel> FnShowParticularRecord(@PathVariable int product_fg_id) throws SQLException {
		List<CSmProductFgProcessModel> cSmProductFgProcessModel = null;
		try {
			cSmProductFgProcessModel = iSmProductFgProcessService.FnShowParticularRecord(product_fg_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductFgProcessModel;

	}

}
