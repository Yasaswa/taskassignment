package com.erp.AmModulesForms.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesForms.Model.CAmModulesFormsModel;
import com.erp.AmModulesForms.Model.CAmModulesFormsViewModel;
import com.erp.AmModulesForms.Repository.IAmModulesFormsRepository;
import com.erp.AmModulesForms.Repository.IAmModulesFormsViewRepository;
import com.erp.AmModulesFormsUserAccess.Repository.IAmModulesFormsUserAccessRepository;
import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CAmModulesFormsServiceImpl implements IAmModulesFormsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	private IAmModulesFormsUserAccessRepository iAmModulesFormsUserAccessRepository;

	@Autowired
	IAmModulesFormsRepository iAmModulesFormsRepository;

	@Autowired
	IAmModulesFormsViewRepository iAmModulesFormsViewRepository;


	@Override
	public List<CAmModulesFormsViewModel> FnShowAllActiveRecords(int company_id) {
		return iAmModulesFormsViewRepository.FnShowAllActiveRecords(company_id);
	}

	@Override
	public CAmModulesFormsViewModel FnShowParticularRecord(int modules_forms_id,
	                                                       int company_id) {
		return iAmModulesFormsViewRepository.FnShowParticularRecord(modules_forms_id, company_id);
	}


	@Override
	public JSONObject FnAddUpdateRecord(CAmModulesFormsModel cAmModulesFormsModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		Optional<CAmModulesFormsModel> option = iAmModulesFormsRepository.findById(cAmModulesFormsModel.getModules_forms_id());
		CAmModulesFormsModel MyModel = null;
		int company_id = cAmModulesFormsModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cAmModulesFormsModel.setModified_on(new Date());
				CAmModulesFormsModel AmModulesFormsModel = iAmModulesFormsRepository.save(cAmModulesFormsModel);
				String json = objectMapper.writeValueAsString(AmModulesFormsModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated successfully!...");
				System.out.println(" Am Modules Forms Updated Successfully!..");
				return resp;
			} else {
				CAmModulesFormsModel model = iAmModulesFormsRepository.getCheck(cAmModulesFormsModel.getModules_forms_name());

				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", " Am Modules Forms  is already exist!");

					return resp;

				} else {
					CAmModulesFormsModel json = iAmModulesFormsRepository.save(cAmModulesFormsModel);

					int generatedId = json.getModules_forms_id();
					Integer module_id = json.getModule_id();
					Integer sub_module_id = json.getSub_module_id();
					iAmModulesFormsRepository.insertUserAccess(1, 1, "Administrators", "1", generatedId, module_id, sub_module_id,1, 1, 1, 1, 1, 1, 1, "dakshabhiadmin", "Y:Y:Y:Y:Y:Y:Y", "S0000", "0" );



					String json2 = objectMapper.writeValueAsString(json);
					resp.put("success", "1");
					resp.put("data", json2);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
					System.out.println(" Am Modules Forms Saved Successfully..!");
					return resp;
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmModulesForms/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());

				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmModulesForms/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int modules_forms_id, int company_id) {
		return iAmModulesFormsRepository.FnDeleteRecord(modules_forms_id, company_id);

	}

//	@Override
//	public Object FnDeleteRecord(int modules_forms_id, int company_id, String deleted_by) {
//		return iAmModulesFormsRepository.FnDeleteRecord(modules_forms_id, company_id, deleted_by);
//		
//	}

//	@Override
//	public Page<CAmModulesFormsRptModel> FnShowAllReportRecords(Pageable pageable) {
//		return iAmModulesFormsRptRepository.FnShowAllReportRecords(pageable);
//	}

	@Override
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) {
		return iAmModulesFormsViewRepository.FnShowAllReportRecords(pageable);
	}

}


