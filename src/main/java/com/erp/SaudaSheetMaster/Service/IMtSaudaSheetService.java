package com.erp.SaudaSheetMaster.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IMtSaudaSheetService {
    Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);
    Map<String, Object> FnDeleteRecord(int saudasheetMasterId, int companyId, String deletedBy);

    Map<String, Object> FnShowParticularRecordForUpdate(int saudasheetMasterId, int companyId);


}
