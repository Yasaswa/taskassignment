package com.erp.ContractorCommission.Service;

import com.erp.ContractorCommission.Model.CContractorCommissionModel;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface IContractorCommissionService {

    Map<String, Object> FnAddUpdateRecord(CContractorCommissionModel cContractorCommissionModel);

    Map<String, Object> FnDeleteRecord(int commission_rate_id, String deleted_by);

    //	Map<String, Object> FnShowParticularRecord(int company_id, int job_type_id);
    Map<String, Object> FnShowParticularRecord(int commission_rate_id);

//    Map<String, Object> FnShowAllReportRecords(Pageable pageable);


}
