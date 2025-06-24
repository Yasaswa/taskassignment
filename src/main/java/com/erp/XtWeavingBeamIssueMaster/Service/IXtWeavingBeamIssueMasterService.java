package com.erp.XtWeavingBeamIssueMaster.Service;

import com.erp.XtWeavingBeamIssueMaster.Model.CXtWeavingBeamIssueMasterModel;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface IXtWeavingBeamIssueMasterService {
    Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

    Map<String, Object> FnDeleteRecord(int weaving_beam_issue_master_id, String deleted_by);

    Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int weaving_beam_issue_master_id);

}
