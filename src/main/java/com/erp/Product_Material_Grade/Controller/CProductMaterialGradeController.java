package com.erp.Product_Material_Grade.Controller;

import com.erp.Product_Material_Grade.Model.CProductMaterialGradeModel;
import com.erp.Product_Material_Grade.Service.IProductMaterialGradeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/api/productmaterialgrade", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductMaterialGradeController {

	@Autowired
	IProductMaterialGradeService iProductMaterialGradeService;


	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductMaterialGradeModel cProductMaterialGradeModel) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductMaterialGradeService.FnAddUpdateRecord(cProductMaterialGradeModel);
			System.out.println("Responce: " + resp.toString());
			return resp.toString();
		} catch (Exception e) {
		}
		return resp.toString();
	}


	@DeleteMapping("/FnDeleteRecord/{product_material_grade_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_material_grade_id, @PathVariable String deleted_by) {
		return iProductMaterialGradeService.FnDeleteRecord(product_material_grade_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductMaterialGradeViewModel = null;
		try {
			cProductMaterialGradeViewModel = iProductMaterialGradeService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialGradeViewModel;
	}


	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductMaterialGradeViewModel = null;
		try {
			cProductMaterialGradeViewModel = iProductMaterialGradeService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialGradeViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{product_material_grade_id}")
	public Object FnShowParticularRecord(@PathVariable int product_material_grade_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductMaterialGradeService.FnShowParticularRecord(product_material_grade_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();


	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductMaterialGradeRptModel = null;
		try {
			cProductMaterialGradeRptModel = iProductMaterialGradeService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialGradeRptModel;

	}

}
