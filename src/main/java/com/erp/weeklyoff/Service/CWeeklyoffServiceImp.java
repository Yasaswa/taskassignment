package com.erp.weeklyoff.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.weeklyoff.Model.CWeeklyoffModel;
import com.erp.weeklyoff.Model.CWeeklyoffRptModel;
import com.erp.weeklyoff.Model.CWeeklyoffViewModel;
import com.erp.weeklyoff.Repository.IWeeklyoffRepository;
import com.erp.weeklyoff.Repository.IWeeklyoffRptRepository;
import com.erp.weeklyoff.Repository.IWeeklyoffViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class CWeeklyoffServiceImp implements IWeeklyoffService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IWeeklyoffRepository iWeeklyoffRepository;

	@Autowired
	IWeeklyoffViewRepository iWeeklyoffViewRepository;

	@Autowired
	IWeeklyoffRptRepository iWeeklyoffRptRepository;


	@Override
	public Object FnDeleteRecord(int weeklyoff_id) {
		return iWeeklyoffRepository.FnDeleteRecord(weeklyoff_id);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CWeeklyoffViewModel> data = iWeeklyoffViewRepository.FnShowAllRecords(pageable);
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
			Page<CWeeklyoffViewModel> data = iWeeklyoffViewRepository.FnShowAllActiveRecords(pageable);
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
	public JSONObject FnShowParticularRecordForUpdate(int weeklyoff_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CWeeklyoffViewModel json = iWeeklyoffViewRepository.FnShowParticularRecordForUpdate(weeklyoff_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int weeklyoff_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CWeeklyoffViewModel json = iWeeklyoffViewRepository.FnShowParticularRecord(company_id, company_branch_id, weeklyoff_id);
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
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CWeeklyoffRptModel> data = iWeeklyoffRptRepository.FnShowAllReportRecords(pageable);
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
	public JSONObject FnAddUpdateRecord(CWeeklyoffModel cWeeklyoffModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		Optional<CWeeklyoffModel> option = iWeeklyoffRepository.findById(cWeeklyoffModel.getWeeklyoff_id());
		CWeeklyoffModel MyModel = null;
		int company_id = cWeeklyoffModel.getCompany_id();
		try {
			if (option.isPresent()) {
				CWeeklyoffModel WeeklyoffModel = iWeeklyoffRepository.save(cWeeklyoffModel);
				String json = objectMapper.writeValueAsString(WeeklyoffModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated successfully!...");
				System.out.println(" Weekly off Updated Successfully!..");
				return resp;
			} else {
				CWeeklyoffModel model = iWeeklyoffRepository.getCheck(cWeeklyoffModel.getWeeklyoff_name());

				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "  Weekly off  is already exist!");

					return resp;

				} else {

					CWeeklyoffModel json = iWeeklyoffRepository.save(cWeeklyoffModel);
					String json1 = objectMapper.writeValueAsString(json);
					resp.put("success", "1");
					resp.put("data", json1);
					resp.put("error", "");
					resp.put("message", "Record updated successfully!...");

					System.out.println("  Weekly off  saved succesfully!..");
					return resp;
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/weeklyoff/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/weeklyoff/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

}
