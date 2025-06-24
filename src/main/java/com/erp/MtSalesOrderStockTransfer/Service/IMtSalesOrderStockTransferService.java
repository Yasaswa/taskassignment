package com.erp.MtSalesOrderStockTransfer.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;

import java.util.Map;

public interface IMtSalesOrderStockTransferService {

	Map<String, Object> FnAddUpdateRecord(JSONObject requestTransferStock) throws JsonProcessingException;

	Map<String, Object> FnShowParticularRecordForUpdate(int sales_order_transfer_id);
}
