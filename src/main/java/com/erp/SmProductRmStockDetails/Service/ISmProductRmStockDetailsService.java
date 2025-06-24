package com.erp.SmProductRmStockDetails.Service;

import java.util.HashMap;
import java.util.Map;

public interface ISmProductRmStockDetailsService {

	Map<String, Object> FnAddUpdateRecord(HashMap<Object, Object> materialDetails);

	Map<String, Object> FnUpdateScheduledRmStockSummary();

	Map<String, Object> FnAddUpdateFGStock(Map<String, Object> stockDetails, String iuFlag, int company_id);

//	Map<String, Object> FnAddUpdateCustomerStock(Map<String, Object> stockDetails, String iuFlag, int company_id);

	Map<String, Object> FnAddUpdateRmStockRawMaterials(Map<String, Object> stockDetails, String iuFlag, int companyId);
}
