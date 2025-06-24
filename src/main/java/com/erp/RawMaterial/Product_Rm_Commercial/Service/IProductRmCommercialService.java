package com.erp.RawMaterial.Product_Rm_Commercial.Service;

import com.erp.RawMaterial.Product_Rm_Commercial.Model.CProductRmCommercialModel;
import org.json.JSONObject;

public interface IProductRmCommercialService {

	JSONObject FnAddUpdateRecord(CProductRmCommercialModel cProductRmCommercialModel);

	JSONObject FnShowParticularRecordForUpdate(int company_id, int company_branch_id, String product_rm_id);

}
