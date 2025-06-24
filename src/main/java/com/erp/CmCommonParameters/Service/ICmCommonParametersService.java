package com.erp.CmCommonParameters.Service;

import com.erp.CmCommonParameters.Model.CCmCommonParametersModel;
import com.erp.CmCommonParameters.Model.CCmCommonParametersViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ICmCommonParametersService {

	Map<String, Object> FnAddUpdateRecord(CCmCommonParametersModel cCmCommonParametersModel);

	Object FnDeleteRecord(int common_parameters_id, int company_id, String deleted_by);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int common_parameters_id, int company_id);

	Page<CCmCommonParametersViewModel> FnShowParticularRecord(int common_parameters_id, Pageable pageable, int company_id);

	Page<CCmCommonParametersViewModel> FnShowAllActiveRecords(String common_parameters_master_name, Pageable pageable);


}
