package com.erp.Hsn_Sac.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Hsn_Sac.Model.CHsn_SacModel;
import com.erp.Hsn_Sac.Model.CHsn_SacViewModel;
import com.erp.Hsn_Sac.Repository.IHsn_SacRepository;
import com.erp.Hsn_Sac.Repository.IHsn_SacViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CHsn_SacServiceImpl implements IHsn_SacService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IHsn_SacRepository iHsn_SacRepository;

	@Autowired
	IHsn_SacViewRepository iHsn_SacViewRepository;


	@Override
	public Map<String, Object> FnAddUpdateRecord(CHsn_SacModel cHsn_SacModel) {
		Map<String, Object> resp = new HashMap<>();
		CHsn_SacModel MyModel = null;
		int company_id = cHsn_SacModel.getCompany_id();
		try {
			Optional<CHsn_SacModel> option = iHsn_SacRepository.findById(cHsn_SacModel.getHsn_sac_id());

			if (option.isPresent()) {
				cHsn_SacModel.setModified_on(new Date());
				CHsn_SacModel mymodel = iHsn_SacRepository.save(cHsn_SacModel);
				resp.put("success", "1");
				resp.put("data", mymodel);
				resp.put("message", "Record updated succesfully!...");
			} else {
				CHsn_SacModel model = iHsn_SacRepository.getCheck(cHsn_SacModel.getHsn_sac_code(), cHsn_SacModel.getCompany_id());
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "   Hsn Sac  Name is already exist!");
				} else {

					CHsn_SacModel Hsn_SacModel = iHsn_SacRepository.save(cHsn_SacModel);
					resp.put("success", "1");
					resp.put("data", Hsn_SacModel);
					resp.put("message", "Record added succesfully!...");
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/hsnsac/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/hsnsac/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int hsn_sac_id, String deleted_by) {
		return iHsn_SacRepository.FnDeleteRecord(hsn_sac_id, deleted_by);
	}

	@Override
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iHsn_SacViewRepository.FnShowAllReportRecords(pageable);
			System.out.println(data);
			resp.put("success", "1");
			resp.put("error", "");

			return new Object[]{data, resp};

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

		return new Object[]{"", resp};
	}


	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CHsn_SacViewModel> data = iHsn_SacViewRepository.FnShowAllRecords(pageable);
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
			Page<CHsn_SacViewModel> data = iHsn_SacViewRepository.FnShowAllActiveRecords(pageable);
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
	public JSONObject FnShowParticularRecordForUpdate(int hsn_sac_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CHsn_SacViewModel json = iHsn_SacViewRepository.FnShowParticularRecordForUpdate(hsn_sac_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int hsn_sac_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CHsn_SacViewModel json = iHsn_SacViewRepository.FnShowParticularRecord(company_id, hsn_sac_id);
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
