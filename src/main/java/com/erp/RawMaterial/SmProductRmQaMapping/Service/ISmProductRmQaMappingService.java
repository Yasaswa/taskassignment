package com.erp.RawMaterial.SmProductRmQaMapping.Service;

import com.erp.RawMaterial.SmProductRmQaMapping.Model.CSmProductRmQaMappingModel;
import com.erp.RawMaterial.SmProductRmQaMapping.Model.CSmProductRmQaMappingViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISmProductRmQaMappingService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Page<CSmProductRmQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<CSmProductRmQaMappingModel> FnShowParticularRecord(int product_rm_id, Pageable pageable, int company_id);

}
