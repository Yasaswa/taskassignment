package com.erp.RawMaterial.Product_Rm_Supplier.Service;

import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierModel;
import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierViewModel;
import org.json.JSONObject;

import java.util.List;

public interface IProductRmSupplierService {

	List<CProductRmSupplierViewModel> FnShowAllActiveRecords(int company_id);

	List<CProductRmSupplierModel> FnShowParticularRecord(int product_rm_id, int company_id);

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	List<CProductRmSupplierViewModel> FnShowAllActiveRecordsSuppliersRM(int company_id, int supplier_id);

}
