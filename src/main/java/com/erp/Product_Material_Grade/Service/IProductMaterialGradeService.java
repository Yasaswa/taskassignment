package com.erp.Product_Material_Grade.Service;

import com.erp.Product_Material_Grade.Model.CProductMaterialGradeModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductMaterialGradeService {

	JSONObject FnAddUpdateRecord(CProductMaterialGradeModel cProductMaterialGradeModel);

	Object FnShowAllRecords(Pageable pageable);

	Object FnDeleteRecord(int product_material_grade_id, String deleted_by);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int product_material_grade_id);


	Object FnShowAllReportRecords(Pageable pageable);


}
