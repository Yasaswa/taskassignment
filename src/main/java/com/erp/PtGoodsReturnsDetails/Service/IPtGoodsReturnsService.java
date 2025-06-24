package com.erp.PtGoodsReturnsDetails.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IPtGoodsReturnsService {
    Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

    Map<String, Object> FnDeleteRecord(int goodsReturnMasterId, int companyId, String deletedBy);

    Map<String, Object> FnShowParticularRecordForUpdate(int goodsReturnMasterId, int companyId);
}
