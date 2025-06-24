package com.erp.Product_Unit_Conversion.Service;

import com.erp.Product_Unit_Conversion.Model.CProductUnitConversionModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IProductUnitConversionService {

	Map<String, Object> FnAddUpdateRecord(CProductUnitConversionModel cProductUnitConversionModel);

	Object FnDeleteRecord(int conversion_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int conversion_id);

	JSONObject FnShowParticularRecord(int conversion_id, int company_id);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
