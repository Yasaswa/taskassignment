package com.erp.PtCottonBalesSales.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IPtCottonBalesSalesService {

    Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

    Map<String, Object> FnShowParticularRecordForUpdate(Integer companyId, Integer ptCottonBalesSalesMasterTransactionId);
}
