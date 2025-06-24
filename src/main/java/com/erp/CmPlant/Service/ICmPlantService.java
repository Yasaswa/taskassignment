package com.erp.CmPlant.Service;

import com.erp.CmPlant.Model.CCmPlantModel;
import com.erp.CmPlant.Model.CCmPlantViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface ICmPlantService {

	Map<String, Object> FnAddUpdateRecord(CCmPlantModel cCmPlantModel);

	Object FnDeleteRecord(int plant_id, int company_id, String deleted_by);

	Page<CCmPlantViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int plant_id, int company_id);


	Page<CCmPlantViewModel> FnShowParticularRecord(int plant_id, int company_id, Pageable pageable);

}
