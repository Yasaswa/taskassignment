package com.erp.RawMaterial.SmProductRmBomMst.Service;

import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmvProductRmBomDetails;
import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmvProductRmBomListingModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface ISmProductRmBomMstService {


	Map<String, Object> FnAddUpdateRecord(JSONObject detailObject);

	Page<CSmvProductRmBomListingModel> FnShowParticularRecords(String product_parent_rm_id, Pageable pageable);

	List<CSmvProductRmBomListingModel> FnShowAllActiveRecordsToExport(String product_parent_rm_id);

	Page<CSmvProductRmBomDetails> FnShowParticularRecordForUpdate(int product_rm_bom_id, Pageable pageable);

	JSONObject FnDeleteRecord(String product_rm_bom_no, String deleted_by, int company_id);

}
