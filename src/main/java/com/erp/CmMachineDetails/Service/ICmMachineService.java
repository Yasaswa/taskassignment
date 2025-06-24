package com.erp.CmMachineDetails.Service;

import com.erp.CmMachineDetails.Model.CCmMachineModel;
import com.erp.CmMachineDetails.Model.CCmMachineProcessModel;
import com.erp.CmMachineDetails.Model.CCmMachineProcessViewModel;
import com.erp.CmMachineDetails.Model.CCmMachineViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICmMachineService {


	Map<String, Object> FnAddUpdateMachineRecord(CCmMachineModel ccmMachineModel);

	JSONObject FnAddUpdateMachineProcessRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int machine_id, int company_id, String deleted_by);

	List<CCmMachineProcessViewModel> FnShowAllActiveMachineProcessRecords(int company_id);

	List<CCmMachineProcessModel> FnShowParticularMachineProcessRecord(int machine_id, int company_id);

	Page<List<Map<String, Object>>> FnShowAllMachineReportRecords(Pageable pageable, int company_id);

	Page<CCmMachineViewModel> FnShowAllActiveMachineRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularMachineRecordForUpdate(int machine_id, int company_id);


}
