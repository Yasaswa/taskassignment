package com.erp.HmCompOffDetails.Service;

import com.erp.HmCompOffDetails.Model.CHmCompOffDetailsModel;

import java.util.Map;

public interface IHmCompOffDetailsService {
    Map<String, Object> FnAddUpdateRecord(CHmCompOffDetailsModel cHmCompOffDetailsModel);

    Map<String, Object> FnShowParticularRecordForUpdate(int companyId, int comp_off_intimation_details_id);

    Object FnDeleteRecord(String punch_code, int companyId, String deleted_by, String deletedBy);
}
