package com.erp.MaterialTransferMaster.Service;

import com.erp.MaterialTransferMaster.Model.CMaterialTransferDetailsModel;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IMaterialTransferDetailsService {

    ResponseEntity<Map<String, Object>> FnAddUpdateTransferDetail(JSONObject jsonObject, Integer company_id);

//    List<CMaterialTransferDetailsModel> getAllDetails();
}
