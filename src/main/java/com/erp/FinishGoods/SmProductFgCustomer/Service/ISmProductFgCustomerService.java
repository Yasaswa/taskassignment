package com.erp.FinishGoods.SmProductFgCustomer.Service;

import com.erp.FinishGoods.SmProductFgCustomer.Model.CSmProductFgCustomerModel;
import com.erp.FinishGoods.SmProductFgCustomer.Model.CSmProductFgCustomerViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISmProductFgCustomerService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Page<CSmProductFgCustomerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<CSmProductFgCustomerModel> FnShowParticularRecord(int product_fg_id, Pageable pageable);

	Page<CSmProductFgCustomerViewModel> FnShowAllActiveRecordsCustomersFG(Pageable pageable, int company_id,
	                                                                      int customer_id);

}
