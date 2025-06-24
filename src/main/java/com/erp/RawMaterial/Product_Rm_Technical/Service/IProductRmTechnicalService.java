package com.erp.RawMaterial.Product_Rm_Technical.Service;

import com.erp.RawMaterial.Product_Rm_Technical.Model.CProductRmTechnicalModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductRmTechnicalService {


	JSONObject FnAddUpdateRecord(CProductRmTechnicalModel cProductRmTechnicalModel);

	Object FnDeleteRecord(int product_rm_id);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int product_rm_id);

	JSONObject FnShowParticularRecordForUpdate(int company_id, int company_branch_id, int product_rm_id);


}
