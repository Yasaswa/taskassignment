package com.erp.Taxation.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Taxation.Model.CTaxationModel;
import com.erp.Taxation.Model.CTaxationViewModel;
import com.erp.Taxation.Repository.ITaxationRepository;
import com.erp.Taxation.Repository.ITaxationViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CTaxationServiceImpl implements ITaxationService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ITaxationRepository iTaxationRepository;

	@Autowired
	ITaxationViewRepository iTaxationViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CTaxationModel cTaxationModel) {
		Map<String, Object> resp = new HashMap<>();
		CTaxationModel MyModel = null;
		int company_id = cTaxationModel.getCompany_id();
		int taxation_id = cTaxationModel.getTaxation_id();

		try {
			iTaxationRepository.updateTaxtypeActiveStatus(cTaxationModel.getTaxtype_id(), company_id);
			cTaxationModel.setTaxation_id(0);
			CTaxationModel json = iTaxationRepository.save(cTaxationModel);

			resp.put("success", "1");
			resp.put("data", json);
			resp.put("message", taxation_id == 0 ? "Record added successfully!..." : "Record added updated successfully!...");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/taxation/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/taxation/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int taxation_id, String deleted_by) {
		return iTaxationRepository.FnDeleteRecord(taxation_id, deleted_by);
	}

	@Override
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) {
		return iTaxationViewRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CTaxationViewModel> data = iTaxationViewRepository.FnShowAllRecords(pageable);
			String json = objectMapper.writeValueAsString(data);
			resp.put("data", json);
			resp.put("success", "1");
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return resp;

	}

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CTaxationViewModel> data = iTaxationViewRepository.FnShowAllActiveRecords(pageable);
			String json = objectMapper.writeValueAsString(data);
			resp.put("data", json);
			resp.put("success", "1");
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return resp;

	}

	@Override
	public JSONObject FnShowParticularRecordForUpdate(int taxation_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CTaxationViewModel json = iTaxationViewRepository.FnShowParticularRecordForUpdate(taxation_id);
			String json1 = objectMapper.writeValueAsString(json);
			resp.put("success", "1");
			resp.put("data", json1);
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public JSONObject FnShowParticularRecord(int company_id, int taxation_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CTaxationViewModel json = iTaxationViewRepository.FnShowParticularRecord(company_id, taxation_id);
			String json1 = objectMapper.writeValueAsString(json);

			resp.put("success", "1");
			resp.put("data", json1);
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}


}
