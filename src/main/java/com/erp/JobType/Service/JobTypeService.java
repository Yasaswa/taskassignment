package com.erp.JobType.Service;

import com.erp.JobType.Model.CJobTypeModel;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface JobTypeService {

    Map<String, Object> FnAddUpdateRecord(CJobTypeModel cJobTypeModel);

    Map<String, Object> FnDeleteRecord(int job_type_id, String deleted_by);

    //	Map<String, Object> FnShowParticularRecord(int company_id, int job_type_id);
    Map<String, Object> FnShowParticularRecord(int job_type_id);

    Map<String, Object> FnShowAllReportRecords(Pageable pageable);


}
