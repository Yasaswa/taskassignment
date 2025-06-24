package com.erp.HmShiftManagement.Service;

import com.erp.HmShiftManagement.Model.CHtMissPunchCorrectionModel;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface IHtShiftManagementService {

    Map<String, Object> FnAddUpdateMissPunchRecord(JSONObject jsonObject);
    Map<String, Object> FnUpdateLockStatus(Map<String, Object> jsonObject);
}
