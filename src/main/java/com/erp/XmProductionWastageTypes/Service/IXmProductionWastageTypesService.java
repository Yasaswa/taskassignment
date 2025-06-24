package com.erp.XmProductionWastageTypes.Service;

import com.erp.XmProductionWastageTypes.Model.CXmProductionWastageTypesModel;
import com.erp.XmProductionWastageTypes.Model.CXmProductionWastageTypesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IXmProductionWastageTypesService {

	Map<String, Object> FnAddUpdateRecord(CXmProductionWastageTypesModel cXmProductionWastageTypesModel);

	Object FnDeleteRecord(int production_wastage_types_id, int company_id, String deleted_by);

	Page<CXmProductionWastageTypesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int production_wastage_types_id, int company_id);

	Page<CXmProductionWastageTypesViewModel> FnShowParticularRecord(int production_wastage_types_id, Pageable pageable, int company_id);

}
