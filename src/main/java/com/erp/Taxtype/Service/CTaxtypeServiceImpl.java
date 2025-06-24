package com.erp.Taxtype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Taxtype.Model.CTaxtypeModel;
import com.erp.Taxtype.Model.CTaxtypeViewModel;
import com.erp.Taxtype.Repository.ITaxtypeRepository;
import com.erp.Taxtype.Repository.ITaxtypeViewRepository;
import com.erp.Transporter.Model.CTransporterModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class CTaxtypeServiceImpl implements ITaxtypeService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ITaxtypeRepository iTaxtypeRepository;

	@Autowired
	ITaxtypeViewRepository iTaxtypeViewRepository;

	@Override
	public Object FnDeleteRecord(int taxtype_id, String deleted_by) {
		return iTaxtypeRepository.FnDeleteRecord(taxtype_id, deleted_by);
	}


	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CTaxtypeViewModel> data = iTaxtypeViewRepository.FnShowAllActiveRecords(pageable);
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
	public JSONObject FnShowParticularRecordForUpdate(int taxtype_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CTaxtypeViewModel json = iTaxtypeViewRepository.FnShowParticularRecordForUpdate(taxtype_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int taxtype_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CTaxtypeViewModel json = iTaxtypeViewRepository.FnShowParticularRecord(company_id, taxtype_id);
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
	public Map<String, Object> FnAddUpdateRecord(CTaxtypeModel cTaxtypeModel) {
		Map<String, Object> resp = new HashMap<>();
		Optional<CTaxtypeModel> option = iTaxtypeRepository.findById(cTaxtypeModel.getTaxtype_id());
		CTaxtypeModel MyModel = null;
		int company_id = cTaxtypeModel.getCompany_id();
		try {
			if (option.isPresent()) {
				CTaxtypeModel TaxtypeModel = iTaxtypeRepository.save(cTaxtypeModel);
				resp.put("success", "1");
				resp.put("data", TaxtypeModel);
				resp.put("message", "Record updated successfully!...");
				return resp;
			} else {
				
				//Check similar Taxtype Name,Taxtype short name is exist or not
				CTaxtypeModel resultsTaxtypeName = null;
				if (cTaxtypeModel.getTaxtype_short_name() == null || cTaxtypeModel.getTaxtype_short_name().isEmpty()) {
					resultsTaxtypeName = iTaxtypeRepository.getCheck(cTaxtypeModel.getTaxtype_name(),
							null, cTaxtypeModel.getCompany_id());
				} else {
					resultsTaxtypeName = iTaxtypeRepository.getCheck(cTaxtypeModel.getTaxtype_name(),
							cTaxtypeModel.getTaxtype_short_name(), cTaxtypeModel.getCompany_id());
				}

				if (resultsTaxtypeName != null) {
					resp.put("success", 0);
					resp.put("data", "");
					resp.put("message", "Taxtype or Short name is already exist!");
					return resp;
				}else {

					CTaxtypeModel json = iTaxtypeRepository.save(cTaxtypeModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/taxtype/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/taxtype/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}

		return resp;

	}


	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CTaxtypeViewModel> data = iTaxtypeViewRepository.FnShowAllRecords(pageable);
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
			Page<Map<String, Object>> data = iTaxtypeViewRepository.FnShowAllReportRecords(pageable);
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


}
