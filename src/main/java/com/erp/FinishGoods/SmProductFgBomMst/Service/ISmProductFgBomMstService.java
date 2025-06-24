package com.erp.FinishGoods.SmProductFgBomMst.Service;

import com.erp.FinishGoods.SmProductFgBomMst.Model.CSmvProductFgBomListingModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface ISmProductFgBomMstService {

	Map<String, Object> FnDeleteRecord(String productFgBomNo, String deleted_by, int company_id);

	Page<CSmvProductFgBomListingModel> FnShowParticularRecords(String product_parent_fg_id, Pageable pageable);

	List<Map<String, Object>> FnShowParticularRecordForUpdate(int product_fg_bom_id, Pageable pageable);

	List<CSmvProductFgBomListingModel> FnShowAllActiveRecordsToExport(String product_parent_fg_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject detailObject);


	List<Map<String, Object>> FnShowProductFgBomMstRecord(String product_parent_fg_id, int company_id);


}
