package com.erp.Product_Material_Shape.Service;

import com.erp.Product_Material_Shape.Model.CProductMaterialShapeModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IProductMaterialShapeService {

	JSONObject FnAddUpdateRecord(CProductMaterialShapeModel cProductMaterialShapeModel);

	Object FnDeleteRecord(int product_material_shape_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int company_id, int product_material_shape_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_material_shape_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
