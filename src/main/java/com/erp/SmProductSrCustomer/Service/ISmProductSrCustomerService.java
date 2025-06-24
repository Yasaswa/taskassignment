package com.erp.SmProductSrCustomer.Service;

import com.erp.SmProductSrCustomer.Model.CSmProductSrCustomerViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ISmProductSrCustomerService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Page<CSmProductSrCustomerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<CSmProductSrCustomerViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);

}
