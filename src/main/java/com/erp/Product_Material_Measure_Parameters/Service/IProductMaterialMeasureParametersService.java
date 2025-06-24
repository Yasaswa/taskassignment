package com.erp.Product_Material_Measure_Parameters.Service;

import com.erp.Product_Material_Measure_Parameters.Model.CProductMaterialMeasureParametersModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductMaterialMeasureParametersService {

	JSONObject FnAddUpdateRecord(CProductMaterialMeasureParametersModel cProductMaterialMeasureParametersModel);

	Object FnDeleteRecord(int product_material_measure_parameter_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int product_material_measure_parameter_id);

	JSONObject FnShowParticularRecordForUpdate(int product_material_measure_parameter_id);

	Object FnShowAllReportRecords(Pageable pageable);


}
