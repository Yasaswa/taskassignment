package com.erp.Product_Material_Shape.Controller;

import com.erp.Product_Material_Shape.Model.CProductMaterialShapeModel;
import com.erp.Product_Material_Shape.Service.IProductMaterialShapeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/productmaterialshape")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductMaterialShapeController {

	@Autowired
	IProductMaterialShapeService iProductMaterialShapeService;

	@Autowired
	DataSource dataSource;


	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductMaterialShapeModel cProductMaterialShapeModel) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductMaterialShapeService.FnAddUpdateRecord(cProductMaterialShapeModel);
			System.out.println("Responce: " + resp.toString());
			return resp.toString();

		} catch (Exception e) {
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{product_material_shape_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_material_shape_id, @PathVariable String deleted_by) {
		return iProductMaterialShapeService.FnDeleteRecord(product_material_shape_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductMaterialShapeModel = null;
		try {
			cProductMaterialShapeModel = iProductMaterialShapeService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialShapeModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		JSONObject resp = new JSONObject();
		Object cProductMaterialShapeViewModel = null;
		try {
			cProductMaterialShapeViewModel = iProductMaterialShapeService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialShapeViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{product_material_shape_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_material_shape_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductMaterialShapeService.FnShowParticularRecord(company_id, product_material_shape_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();


	}


	@GetMapping("/FnShowParticularRecordForUpdate/{product_material_shape_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_material_shape_id) throws SQLException {
		Map<String, Object> responce = new HashMap<>();
		responce = iProductMaterialShapeService.FnShowParticularRecordForUpdate(product_material_shape_id);
		return responce;
	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductMaterialShapeRptModel = null;
		try {
			cProductMaterialShapeRptModel = iProductMaterialShapeService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialShapeRptModel;

	}


}
