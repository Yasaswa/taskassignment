package com.erp.PtGoodsReturnFabricDetails.Service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IPtGoodsReturnFabricService {

    Map<String, Object>FnAddUpdateRecord(JSONObject jsonObject);

    Map<String, Object> FnShowAllDetailsandMastermodelRecords(String goods_return_fabric_no, int goods_return_fabric_version,
                                                              String financial_year, int company_id);
}
