package com.erp.Product_Material_Measure_Parameters.Controller;

import com.erp.Product_Material_Measure_Parameters.Model.CProductMaterialMeasureParametersModel;
import com.erp.Product_Material_Measure_Parameters.Service.IProductMaterialMeasureParametersService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;


@RestController
@RequestMapping("/api/productrmmeasureparameter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductMaterialMeasureParametersController {


	@Autowired
	IProductMaterialMeasureParametersService iProductMaterialMeasureParametersService;

	@Autowired
	DataSource dataSource;


	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductMaterialMeasureParametersModel cProductMaterialMeasureParametersModel) throws SQLException {
		JSONObject resp = new JSONObject();
		try {

			resp = iProductMaterialMeasureParametersService.FnAddUpdateRecord(cProductMaterialMeasureParametersModel);
			System.out.println("Responce: " + resp.toString());
			return resp.toString();

		} catch (Exception e) {
		}
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{product_material_measure_parameter_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_material_measure_parameter_id, @PathVariable String deleted_by) {
		return iProductMaterialMeasureParametersService.FnDeleteRecord(product_material_measure_parameter_id, deleted_by);
	}


	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductMaterialMeasureParametersViewModel = null;
		try {
			cProductMaterialMeasureParametersViewModel = iProductMaterialMeasureParametersService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialMeasureParametersViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		JSONObject resp = new JSONObject();
		Object cProductMaterialMeasureParametersViewModel = null;
		try {
			cProductMaterialMeasureParametersViewModel = iProductMaterialMeasureParametersService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialMeasureParametersViewModel;
	}


	@GetMapping("/FnShowParticularRecord/{product_material_measure_parameter_id}")
	public Object FnShowParticularRecord(@PathVariable int product_material_measure_parameter_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductMaterialMeasureParametersService.FnShowParticularRecord(product_material_measure_parameter_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();


	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_material_measure_parameter_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_material_measure_parameter_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductMaterialMeasureParametersService.FnShowParticularRecordForUpdate(product_material_measure_parameter_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductMaterialMeasureParametersRptModel = null;
		try {
			cProductMaterialMeasureParametersRptModel = iProductMaterialMeasureParametersService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialMeasureParametersRptModel;

	}

}
