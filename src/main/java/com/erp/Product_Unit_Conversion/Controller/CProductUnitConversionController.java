package com.erp.Product_Unit_Conversion.Controller;

import com.erp.Product_Unit_Conversion.Model.CProductUnitConversionModel;
import com.erp.Product_Unit_Conversion.Service.IProductUnitConversionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/productunitconversion", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductUnitConversionController {

	@Autowired
	IProductUnitConversionService iProductUnitConversionService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CProductUnitConversionModel cProductUnitConversionModel)
			throws SQLException {

		Map<String, Object> resp = iProductUnitConversionService.FnAddUpdateRecord(cProductUnitConversionModel);

		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{conversion_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int conversion_id, @PathVariable String deleted_by) {
		return iProductUnitConversionService.FnDeleteRecord(conversion_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductUnitConversionViewModel = iProductUnitConversionService.FnShowAllRecords(pageable);

		return cProductUnitConversionViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductUnitConversionViewModel = iProductUnitConversionService.FnShowAllActiveRecords(pageable);
		return cProductUnitConversionViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{conversion_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int conversion_id) throws SQLException {
		JSONObject resp = iProductUnitConversionService.FnShowParticularRecordForUpdate(conversion_id);
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{conversion_id}/{company_id}")
	public Object FnShowParticularRecord(@PathVariable int conversion_id, @PathVariable int company_id)
			throws SQLException {
		JSONObject resp = iProductUnitConversionService.FnShowParticularRecord(conversion_id, company_id);
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iProductUnitConversionService.FnShowAllReportRecords(pageable);
	}

}
