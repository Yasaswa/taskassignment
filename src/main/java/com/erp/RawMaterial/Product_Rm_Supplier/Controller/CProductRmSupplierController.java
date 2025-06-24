package com.erp.RawMaterial.Product_Rm_Supplier.Controller;

import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierModel;
import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierViewModel;
import com.erp.RawMaterial.Product_Rm_Supplier.Service.IProductRmSupplierService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/productrmsupplier")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductRmSupplierController {

	@Autowired
	private IProductRmSupplierService iProductRmSupplierService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestParam("productSuppliersJson") JSONObject jsonObject) throws SQLException {
		JSONObject responce = new JSONObject();
		responce = iProductRmSupplierService.FnAddUpdateRecord(jsonObject);
		return responce.toString();
	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public List<CProductRmSupplierViewModel> FnShowAllActiveRecords(@PathVariable int company_id) throws SQLException {
		List<CProductRmSupplierViewModel> cProductRmSupplierViewModel = iProductRmSupplierService.FnShowAllActiveRecords(company_id);
		return cProductRmSupplierViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{product_rm_id}/{company_id}")
	public List<CProductRmSupplierModel> FnShowParticularRecord(@PathVariable int product_rm_id, @PathVariable int company_id) throws SQLException {
		List<CProductRmSupplierModel> cProductRmSupplierModel = iProductRmSupplierService.FnShowParticularRecord(product_rm_id, company_id);
		return cProductRmSupplierModel;
	}


//	Raw material list supplier

	@GetMapping("/FnShowAllActiveRecordsSuppliersRM/{supplier_id}/{company_id}")
	public List<CProductRmSupplierViewModel> FnShowAllActiveRecordsSuppliersRM(@PathVariable int company_id, @PathVariable int supplier_id) throws SQLException {
		List<CProductRmSupplierViewModel> cProductRmSupplierViewModel = iProductRmSupplierService.FnShowAllActiveRecordsSuppliersRM(company_id, supplier_id);
		return cProductRmSupplierViewModel;
	}

}
