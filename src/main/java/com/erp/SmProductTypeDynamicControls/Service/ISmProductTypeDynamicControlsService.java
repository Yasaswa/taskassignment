package com.erp.SmProductTypeDynamicControls.Service;


import java.util.Map;


import com.erp.SmProductTypeDynamicControls.Model.CSmProductTypeDynamicControlsModel;

public interface ISmProductTypeDynamicControlsService {

	Map<String, Object> FnAddUpdateRecord(CSmProductTypeDynamicControlsModel cSmProductTypeDynamicControlsModel);

	Map<String, Object> FnDeleteRecord(int product_type_dynamic_controls_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_type_dynamic_controls_id, int company_id);
	
}
