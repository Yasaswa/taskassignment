package com.erp.SmProductSrProcess.Service;

import com.erp.SmProductSrProcess.Model.CSmProductSrProcessViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ISmProductSrProcessService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Page<CSmProductSrProcessViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);


	Page<CSmProductSrProcessViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);


}
