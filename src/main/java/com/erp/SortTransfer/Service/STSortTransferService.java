package com.erp.SortTransfer.Service;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface STSortTransferService {

    Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

    Map<String, Object> FnShowParticularRecordForUpdate(Integer companyId, Integer sort_transfer_master_id);


}
