package com.erp.XmProductionSpinningPlanParameter.Service;


import com.erp.XmProductionSpinningPlanParameter.Model.CXmProductionSpinningPlanParameterModel;
import com.erp.XmProductionSpinningPlanParameter.Model.CXmProductionSpinningPlanParameterViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IXmProductionSpinningPlanParameterService {

	Map<String, Object> FnAddUpdateRecord(CXmProductionSpinningPlanParameterModel cXmProductionSpinningPlanParameterModel);

	Object FnDeleteRecord(int production_spinning_plan_parameter_id, int company_id, String deleted_by);

	Page<CXmProductionSpinningPlanParameterViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int production_spinning_plan_parameter_id, int company_id);

	Page<CXmProductionSpinningPlanParameterViewModel> FnShowParticularRecord(int production_spinning_plan_parameter_id, Pageable pageable, int company_id);

}
