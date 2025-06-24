package com.erp.FinishGoods.SmProductFgSupplier.Service;

import com.erp.FinishGoods.SmProductFgSupplier.Model.CSmProductFgSupplierModel;
import com.erp.FinishGoods.SmProductFgSupplier.Model.CSmProductFgSupplierViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISmProductFgSupplierService {

//	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Page<CSmProductFgSupplierViewModel> FnShowAllActiveRecords(Pageable pageable);

	Page<CSmProductFgSupplierModel> FnShowParticularRecord(int company_id, Pageable pageable);

	Page<CSmProductFgSupplierViewModel> FnShowAllActiveRecordsSupplierFG(Pageable pageable, int company_id,
	                                                                     int supplier_id);

}
