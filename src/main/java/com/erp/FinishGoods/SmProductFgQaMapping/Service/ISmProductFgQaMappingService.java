package com.erp.FinishGoods.SmProductFgQaMapping.Service;

import com.erp.FinishGoods.SmProductFgQaMapping.Model.CSmProductFgQaMappingModel;
import com.erp.FinishGoods.SmProductFgQaMapping.Model.CSmProductFgQaMappingViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISmProductFgQaMappingService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Page<CSmProductFgQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable);

	Page<CSmProductFgQaMappingModel> FnShowParticularRecord(int product_fg_id, Pageable pageable);

}
