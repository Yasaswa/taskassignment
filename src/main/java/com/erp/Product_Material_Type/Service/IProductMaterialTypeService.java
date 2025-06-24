package com.erp.Product_Material_Type.Service;

import com.erp.Product_Material_Type.Model.CProductMaterialTypeModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductMaterialTypeService {


	Object FnDeleteRecord(int product_material_type_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int product_material_type_id);

	JSONObject FnAddUpdateRecord(CProductMaterialTypeModel cProductMaterialTypeModel);

	JSONObject FnShowParticularRecordForUpdate(int product_material_type_id);

	Object FnShowAllReportRecords(Pageable pageable);


}
