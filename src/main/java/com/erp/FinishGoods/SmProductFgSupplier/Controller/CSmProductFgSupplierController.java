package com.erp.FinishGoods.SmProductFgSupplier.Controller;

import com.erp.FinishGoods.SmProductFgSupplier.Model.CSmProductFgSupplierModel;
import com.erp.FinishGoods.SmProductFgSupplier.Model.CSmProductFgSupplierViewModel;
import com.erp.FinishGoods.SmProductFgSupplier.Service.ISmProductFgSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/SmProductFgSupplier")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgSupplierController {

	@Autowired
	ISmProductFgSupplierService iSmProductFgSupplierService;

//	@PostMapping("/FnAddUpdateRecord")
//	public Object FnAddUpdateRecord(@RequestParam("productFgSuppliersJson") JSONObject jsonObject) throws SQLException {
//		JSONObject resp = new JSONObject();
//		resp = iSmProductFgSupplierService.FnAddUpdateRecord(jsonObject);
//
//		return resp.toString();
//
//	}

	@GetMapping("/FnShowAllActiveRecords")
	Page<CSmProductFgSupplierViewModel> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Page<CSmProductFgSupplierViewModel> cSmProductFgSupplierViewModel = null;
		try {
			cSmProductFgSupplierViewModel = iSmProductFgSupplierService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductFgSupplierViewModel;
	}

	@GetMapping("/FnShowParticularRecords/{product_fg_id}")
	public Page<CSmProductFgSupplierModel> FnShowParticularRecord(@PathVariable int product_fg_id, Pageable pageable)
			throws SQLException {
		Page<CSmProductFgSupplierModel> cSmProductFgSupplierModel = null;
		try {
			cSmProductFgSupplierModel = iSmProductFgSupplierService.FnShowParticularRecord(product_fg_id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductFgSupplierModel;

	}

	@GetMapping("/FnShowAllActiveRecordsSupplierFG/{supplier_id}/{company_id}")
	Page<CSmProductFgSupplierViewModel> FnShowAllActiveRecordsSupplierFG(Pageable pageable, @PathVariable int company_id, @PathVariable int supplier_id) throws SQLException {
		Page<CSmProductFgSupplierViewModel> cSmProductFgSupplierViewModel = iSmProductFgSupplierService.FnShowAllActiveRecordsSupplierFG(pageable, company_id, supplier_id);
		return cSmProductFgSupplierViewModel;
	}


}
