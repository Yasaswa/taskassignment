package com.erp.Sl_Gl_Mapping.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingModel;
import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingViewModel;
import com.erp.Sl_Gl_Mapping.Repository.ISl_Gl_MappingRepository;
import com.erp.Sl_Gl_Mapping.Repository.ISl_Gl_MappingViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Service
public class CSl_Gl_MappingServiceImpl implements ISl_Gl_MappingService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISl_Gl_MappingRepository iSl_Gl_MappingRepository;

	@Autowired
	ISl_Gl_MappingViewRepository iSl_Gl_MappingViewRepository;


	@Override
	public Object FnDeleteRecord(int sl_gl_mapping_id) {
		return iSl_Gl_MappingRepository.FnDeleteRecord(sl_gl_mapping_id);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CSl_Gl_MappingViewModel> data = iSl_Gl_MappingViewRepository.FnShowAllRecords(pageable);
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
			Page<CSl_Gl_MappingViewModel> data = iSl_Gl_MappingViewRepository.FnShowAllActiveRecords(pageable);
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
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Map<String, Object> data = iSl_Gl_MappingViewRepository.FnShowAllReportRecords();
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
	public JSONObject FnAddUpdateRecord(CSl_Gl_MappingModel cSl_Gl_MappingModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CSl_Gl_MappingModel MyModel = null;
		int company_id = cSl_Gl_MappingModel.getCompany_id();
		try {
			Optional<CSl_Gl_MappingModel> option = iSl_Gl_MappingRepository
					.findById(cSl_Gl_MappingModel.getSchedule_ledger_id());

			if (option.isPresent()) {
				CSl_Gl_MappingModel mymodel = iSl_Gl_MappingRepository.save(cSl_Gl_MappingModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println("  Sl Gl Mapping updated successfully!..");
				return resp;
			} else {
				CSl_Gl_MappingModel model = iSl_Gl_MappingRepository
						.getCheck(cSl_Gl_MappingModel.getSchedule_ledger_id());
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", " Sl Gl Mapping  Name is already exist!");

					return resp;

				} else {

					CSl_Gl_MappingModel Sl_Gl_MappingModel = iSl_Gl_MappingRepository.save(cSl_Gl_MappingModel);
					String json = objectMapper.writeValueAsString(Sl_Gl_MappingModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
					System.out.println("Sl Gl Mapping saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/slglmapping/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/slglmapping/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public JSONObject FnShowParticularRecordForUpdate(int sl_gl_mapping_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CSl_Gl_MappingViewModel json = iSl_Gl_MappingViewRepository
					.FnShowParticularRecordForUpdate(sl_gl_mapping_id);
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
	public Page<CSl_Gl_MappingViewModel> FnShowParticularRecord(int company_id, int schedule_ledger_id,
	                                                            Pageable pageable) {
		return iSl_Gl_MappingViewRepository.FnShowParticularRecord(company_id, schedule_ledger_id, pageable);

	}

}
