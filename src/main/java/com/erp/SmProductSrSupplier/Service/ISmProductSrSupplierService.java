package com.erp.SmProductSrSupplier.Service;

import com.erp.SmProductSrSupplier.Model.CSmProductSrSupplierViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ISmProductSrSupplierService {

	Page<CSmProductSrSupplierViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);


	Page<CSmProductSrSupplierViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

}
