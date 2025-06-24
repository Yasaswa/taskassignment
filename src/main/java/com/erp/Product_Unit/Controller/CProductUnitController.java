package com.erp.Product_Unit.Controller;

import com.erp.Product_Unit.Model.CProductUnitModel;
import com.erp.Product_Unit.Service.IProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/productunit", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductUnitController {

	@Autowired
	IProductUnitService iProductUnitService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CProductUnitModel cProductUnitModel) throws SQLException {
		Map<String, Object> resp = iProductUnitService.FnAddUpdateRecord(cProductUnitModel);
		return resp;
	}

	@DeleteMapping("/FnDeleteRecord/{product_unit_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_unit_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iProductUnitService.FnDeleteRecord(product_unit_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	public Map<String, Object> FnShowAllRecords(Pageable pageable) throws SQLException {
		Map<String, Object> cProductUnitviewModel = iProductUnitService.FnShowAllRecords(pageable);
		return cProductUnitviewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	public Map<String, Object> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Map<String, Object> cProductUnitviewModel = iProductUnitService.FnShowAllActiveRecords(pageable);
		return cProductUnitviewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_unit_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_unit_id, @PathVariable int company_id) throws SQLException {
		Map<String, Object> resp = iProductUnitService.FnShowParticularRecordForUpdate(product_unit_id, company_id);
		return resp;

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{product_unit_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_unit_id)
			throws SQLException {
		Map<String, Object> resp = iProductUnitService.FnShowParticularRecord(company_id, product_unit_id);
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iProductUnitService.FnShowAllReportRecords(pageable);
	}


}
