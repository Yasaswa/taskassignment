package com.erp.SmProductPr.Service;


import com.erp.SmProductPr.Model.CSmProductPrModel;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface ISmProductPrService {


	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile qrCodeFile);

	Map<String, Object> FnDeleteRecord(String product_pr_id, String deleted_by);

	Map<String, Object> FnAddUpdateMaterial(CSmProductPrModel cSmProductPrModel, int company_id);

}
