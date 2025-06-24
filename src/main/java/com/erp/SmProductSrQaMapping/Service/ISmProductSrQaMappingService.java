package com.erp.SmProductSrQaMapping.Service;

import com.erp.SmProductSrQaMapping.Model.CSmProductSrQaMappingViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ISmProductSrQaMappingService {


	Page<CSmProductSrQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);


	Page<CSmProductSrQaMappingViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

}
