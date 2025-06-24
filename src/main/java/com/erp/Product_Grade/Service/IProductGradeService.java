package com.erp.Product_Grade.Service;

import com.erp.Product_Grade.Model.CProductGradeModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductGradeService {

	JSONObject FnAddUpdateRecord(CProductGradeModel cProductGradeModel);

	Object FnDeleteRecord(int product_grade_id);

	/* Added By Dipti (ERP DB Testing 1.1) */
//	JSONObject FnAddUpdateRecord(CProductMaterialGradeModel cProductMaterialGradeModel);
//	Object FnDeleteRecord(int product_material_grade_id);
	/* Added By Dipti (ERP DB Testing 1.1) */

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowAllReportRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int company_id, int product_grade_id);

	JSONObject FnShowParticularRecordForUpdate(int product_rm_id);


}
