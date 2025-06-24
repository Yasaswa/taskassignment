package com.erp.PtCottonBalesQualityTesting.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IPtCottonBalesQualityTestingService {
    Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);
//Map<String, Object> FnAddUpdateRecord(Map<String, Object> jsonObject);


    Map<String, Object> FnShowParticularRecordForUpdate(int companyId, String quality_testing_no);
}
