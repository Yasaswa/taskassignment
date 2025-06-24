package com.erp.RawMaterial.Product_Rm_Process.Service;

import com.erp.RawMaterial.Product_Rm_Process.Model.CProductRmProcessModel;
import com.erp.RawMaterial.Product_Rm_Process.Model.CProductRmProcessViewModel;
import org.json.JSONObject;

import java.util.List;

public interface IProductRmProcessService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	List<CProductRmProcessModel> FnShowParticularRecord(int product_rm_id, int company_id);

	List<CProductRmProcessViewModel> FnShowAllActiveRecords(int company_id);

}
