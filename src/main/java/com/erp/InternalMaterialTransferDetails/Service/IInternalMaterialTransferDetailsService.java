package com.erp.InternalMaterialTransferDetails.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IInternalMaterialTransferDetailsService {


    Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String addOrIssued);


    Map<String, Object> FnDeleteRecord(int inter_material_transfer_master_id, String userName);

    Map<String, Object> FnShowAllDetailsandMastermodelRecords(String inter_material_transfer_no, String financialYear
            , int companyId );

    Map<String, Object> FnAddUpdateRmStockRawMaterials(Map<String, Object> stockDetails, String iuFlag, int companyId);


}
