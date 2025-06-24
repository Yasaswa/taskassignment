package com.erp.XtWarpingBottomReturn.Service;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.Map;
public interface IXtWarpingBottomReturnService {
    Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String acceptFlag);


    Map<String, Object> FnShowParticularRecordForUpdate(String bottomReturnNo, int companyId,String keyForViewUpdate);
//
    Map<String, Object> FnDeleteRecord(String bottom_return_no, String deleted_by, int companyId);

}
