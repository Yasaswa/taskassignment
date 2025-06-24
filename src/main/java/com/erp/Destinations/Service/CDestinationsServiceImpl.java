package com.erp.Destinations.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Destinations.Model.CDestinationsModel;
import com.erp.Destinations.Model.CDestinationsViewModel;
import com.erp.Destinations.Repository.IDestinationsRepository;
import com.erp.Destinations.Repository.IDestinationsViewRepository;
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
public class CDestinationsServiceImpl implements IDestinationsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IDestinationsRepository iDestinationsRepository;

	@Autowired
	IDestinationsViewRepository iDestinationsViewRepository;

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CDestinationsViewModel> data = iDestinationsViewRepository.FnShowAllRecords(pageable);
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
	public Object FnDeleteRecord(int destination_id, String deleted_by) {
		return iDestinationsRepository.FnDeleteRecord(destination_id, deleted_by);
	}

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CDestinationsViewModel> data = iDestinationsViewRepository.FnShowAllActiveRecords(pageable);
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int destination_id) {
		// TODO Auto-generated method stub
		Map<String, Object> resp = new HashMap();
		try {

			CDestinationsViewModel json = iDestinationsViewRepository.FnShowParticularRecordForUpdate(destination_id);

			resp.put("success", "1");
			resp.put("data", json);
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
	public JSONObject FnShowParticularRecord(int company_id, int destination_id) {
		JSONObject resp = new JSONObject();
		try {
			CDestinationsViewModel json = iDestinationsViewRepository.FnShowParticularRecord(company_id,
					destination_id);

			resp.put("success", "1");
			resp.put("data", json);
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
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iDestinationsViewRepository.FnShowAllReportRecords(pageable);
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
	public Map<String, Object> FnAddUpdateRecord(CDestinationsModel cDestinationsModel) {
		Map<String, Object> resp = new HashMap<>();
		CDestinationsModel MyModel = null;
		int company_id = cDestinationsModel.getCompany_id();
		try {
			Optional<CDestinationsModel> option = iDestinationsRepository
					.findById(cDestinationsModel.getDestination_id());

			if (option.isPresent()) {
				CDestinationsModel mymodel = iDestinationsRepository.save(cDestinationsModel);

				resp.put("success", "1");
				resp.put("data", mymodel);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Destinations updated successfully!..");

			} else {
				CDestinationsModel model = iDestinationsRepository.getCheck(cDestinationsModel.getDestination_name(), company_id);
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", " Destinations Name is already exist!");
				} else {

					CDestinationsModel DestinationsModel = iDestinationsRepository.save(cDestinationsModel);
					resp.put("success", "1");
					resp.put("data", DestinationsModel);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");

					System.out.println(" Destinations saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/destination/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/destination/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

}
