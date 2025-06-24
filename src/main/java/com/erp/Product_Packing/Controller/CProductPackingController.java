package com.erp.Product_Packing.Controller;

import com.erp.Product_Packing.Model.CProductPackingModel;
import com.erp.Product_Packing.Service.IProductPackingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/productpacking")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductPackingController {

	@Autowired
	IProductPackingService iProductPackingService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductPackingModel cProductPackingModel) throws SQLException {

		JSONObject resp = new JSONObject();
		try {
			resp = iProductPackingService.FnAddUpdateRecord(cProductPackingModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{product_packing_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_packing_id, @PathVariable String deleted_by) {
		return iProductPackingService.FnDeleteRecord(product_packing_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductPackingViewModel = null;
		try {
			cProductPackingViewModel = iProductPackingService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductPackingViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductPackingViewModel = null;
		try {
			cProductPackingViewModel = iProductPackingService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductPackingViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_packing_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_packing_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductPackingService.FnShowParticularRecordForUpdate(product_packing_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{product_packing_id}")
	public Object FnShowParticularRecord(@PathVariable int product_packing_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {

			resp = iProductPackingService.FnShowParticularRecord(product_packing_id);
			System.out.println("Responce: " + resp.toString());


		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords() throws SQLException {
		Object cProductPackingRptModel = null;
		try {
			cProductPackingRptModel = iProductPackingService.FnShowAllReportRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductPackingRptModel;

	}


}
