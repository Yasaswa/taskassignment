package com.erp.Product_Material_Type.Controller;

import com.erp.Product_Material_Type.Model.CProductMaterialTypeModel;
import com.erp.Product_Material_Type.Service.IProductMaterialTypeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/productMaterialtype")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductMaterialTypeController {

	@Autowired
	IProductMaterialTypeService iProductMaterialTypeService;

	@Autowired
	DataSource dataSource;


	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductMaterialTypeModel cProductMaterialTypeModel) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductMaterialTypeService.FnAddUpdateRecord(cProductMaterialTypeModel);
			System.out.println("Responce: " + resp.toString());
			return resp.toString();

		} catch (Exception e) {
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{product_material_type_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_material_type_id, @PathVariable String deleted_by) {
		return iProductMaterialTypeService.FnDeleteRecord(product_material_type_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductMaterialTypeViewModel = null;
		try {
			cProductMaterialTypeViewModel = iProductMaterialTypeService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialTypeViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		JSONObject resp = new JSONObject();
		Object cProductMaterialTypeViewModel = null;
		try {
			cProductMaterialTypeViewModel = iProductMaterialTypeService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialTypeViewModel.toString();
	}


	@GetMapping("/FnShowParticularRecord/{product_material_type_id}")
	public Object FnShowParticularRecord(@PathVariable int product_material_type_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductMaterialTypeService.FnShowParticularRecord(product_material_type_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();


	}


	@GetMapping("/FnShowParticularRecordForUpdate/{product_material_type_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_material_type_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductMaterialTypeService.FnShowParticularRecordForUpdate(product_material_type_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductMaterialTypeRptModel = null;
		try {
			cProductMaterialTypeRptModel = iProductMaterialTypeService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMaterialTypeRptModel;

	}


}
