package com.erp.SmProductCodification.Service;

import java.util.Map;

import com.erp.SmProductCodification.Model.CSmProductCodificationModel;


public interface ISmProductCodificationService {

	Map<String, Object> FnAddUpdateRecord(CSmProductCodificationModel cSmProductCodificationModel);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_codification_id, int company_id);

	Map<String, Object> FnDeleteRecord(int product_codification_id, String deleted_by);
	

}
