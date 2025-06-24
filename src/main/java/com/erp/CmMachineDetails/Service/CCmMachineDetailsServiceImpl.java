package com.erp.CmMachineDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.CmMachineDetails.Model.CCmMachineModel;
import com.erp.CmMachineDetails.Model.CCmMachineProcessModel;
import com.erp.CmMachineDetails.Model.CCmMachineProcessViewModel;
import com.erp.CmMachineDetails.Model.CCmMachineViewModel;
import com.erp.CmMachineDetails.Repository.ICmMachineProcessRepository;
import com.erp.CmMachineDetails.Repository.ICmMachineProcessViewRepository;
import com.erp.CmMachineDetails.Repository.ICmMachineRepository;
import com.erp.CmMachineDetails.Repository.ICmMachineViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CCmMachineDetailsServiceImpl implements ICmMachineService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICmMachineRepository iCmMachineRepository;

	@Autowired
	ICmMachineProcessRepository iCmMachineProcessRepository;

	@Autowired
	ICmMachineProcessViewRepository iCmMachineProcessViewRepository;


	@Autowired
	ICmMachineViewRepository iCmMachineViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateMachineRecord(CCmMachineModel ccmMachineModel) {

		Map<String, Object> responce = new HashMap<>();
		Optional<CCmMachineModel> option = iCmMachineRepository.findById(ccmMachineModel.getMachine_id());

		int company_id = ccmMachineModel.getCompany_id();

		try {
			if (option.isPresent()) {
				ccmMachineModel.setModified_on(new Date());
				CCmMachineModel mMachineModel = iCmMachineRepository.save(ccmMachineModel);
				responce.put("success", "1");
				responce.put("data", mMachineModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" CmMachine Updated Successfully!..");
				return responce;
			}
//			Check similar Product Make Name or short name is exist or not

			CCmMachineModel resultsMachineName = null;

			if (ccmMachineModel.getMachine_short_name() == null
					|| ccmMachineModel.getMachine_short_name().isEmpty()) {

				resultsMachineName = iCmMachineRepository.getCheck(ccmMachineModel.getMachine_name(), null,
						ccmMachineModel.getCompany_id());
			} else {
				resultsMachineName = iCmMachineRepository.getCheck(ccmMachineModel.getMachine_name(),
						ccmMachineModel.getMachine_short_name(), ccmMachineModel.getCompany_id());
			}

			if (resultsMachineName != null) {
				responce.put("success", 0);
				responce.put("error", "Machine Name or Short Name is already exist!");
				return responce;
			} else {
				CCmMachineModel respContent = iCmMachineRepository.save(ccmMachineModel);

				responce.put("success", "1");
				responce.put("data", respContent);
				responce.put("error", "");
				responce.put("message", "Record added successfully!...");
				return responce;
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmMachineDetails/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmMachineDetails/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;

		}
		return responce;
	}

	@Override
	public JSONObject FnAddUpdateMachineProcessRecord(JSONObject jsonObject) {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject responce = new JSONObject();
		JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
		JSONArray productProcessId = (JSONArray) jsonObject.get("productProcessId");
		int company_branch_id = compAndBrnchId.getInt("company_branch_id");
		int company_id = compAndBrnchId.getInt("company_id");
		int machine_id = compAndBrnchId.getInt("machine_id");
		try {
			Object obj = iCmMachineProcessRepository.updateMachineProcessActiveStatus(machine_id, company_id,
					company_branch_id);

			List<CCmMachineProcessModel> processModel = objectMapper.readValue(productProcessId.toString(),
					new TypeReference<List<CCmMachineProcessModel>>() {
					});
			iCmMachineProcessRepository.saveAll(processModel);

			responce.put("success", "1");
			responce.put("message", "Records added successfully !...");
			responce.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/CmMachineDetails/FnAddUpdateMachineRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", sqlEx.getMessage());
				return responce;

			} else if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/CmMachineDetails/FnAddUpdateMachineProcessRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", sqlEx.getMessage());
				return responce;

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmMachineDetails/FnAddUpdateMachineRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmMachineDetails/FnAddUpdateMachineProcessRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}

	@Override
	public Object FnDeleteRecord(int machine_id, int company_id, String deleted_by) {
		Optional<CCmMachineModel> option = iCmMachineRepository.findById(machine_id);
		CCmMachineModel ccmMachineModel = new CCmMachineModel();
		if (option.isPresent()) {
			ccmMachineModel = option.get();
			ccmMachineModel.setIs_delete(true);
			ccmMachineModel.setDeleted_by(deleted_by);
			ccmMachineModel.setDeleted_on(new Date());
			iCmMachineRepository.save(ccmMachineModel);
		}
		return ccmMachineModel;
	}

	@Override
	public List<CCmMachineProcessViewModel> FnShowAllActiveMachineProcessRecords(int company_id) {
		List<CCmMachineProcessViewModel> ccmMachineProcessViewModel = null;
		try {
			ccmMachineProcessViewModel = iCmMachineProcessViewRepository.FnShowAllActiveMachineProcessRecords(company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ccmMachineProcessViewModel;
	}

	@Override
	public List<CCmMachineProcessModel> FnShowParticularMachineProcessRecord(int machine_id, int company_id) {
		List<CCmMachineProcessModel> ccmMachineProcessModel = null;
		try {
			ccmMachineProcessModel = iCmMachineProcessRepository.FnShowParticularMachineProcessRecord(machine_id, company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ccmMachineProcessModel;
	}

	@Override
	public Page<List<Map<String, Object>>> FnShowAllMachineReportRecords(Pageable pageable, int company_id) {
		return iCmMachineViewRepository.FnShowAllMachineReportRecords(pageable, company_id);

	}

	@Override
	public Page<CCmMachineViewModel> FnShowAllActiveMachineRecords(Pageable pageable, int company_id) {
		return iCmMachineViewRepository.FnShowAllActiveMachineRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularMachineRecordForUpdate(int machine_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CCmMachineModel ccmMachineModel = null;
		try {
			ccmMachineModel = iCmMachineRepository.FnShowParticularMachineRecordForUpdate(machine_id, company_id);
			responce.put("success", "1");
			responce.put("data", ccmMachineModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}


}
	

	