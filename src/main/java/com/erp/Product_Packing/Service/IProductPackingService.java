package com.erp.Product_Packing.Service;

import com.erp.Product_Packing.Model.CProductPackingModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductPackingService {

	JSONObject FnAddUpdateRecord(CProductPackingModel cProductPackingModel);

	Object FnDeleteRecord(int product_packing_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int product_packing_id);

	JSONObject FnShowParticularRecord(int product_packing_id);

	Object FnShowAllReportRecords();


}
