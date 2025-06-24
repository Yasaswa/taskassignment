package com.erp.FinancialYear.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FinancialYear.Model.CFinancialYearModel;
import com.erp.FinancialYear.Model.CFinancialYearViewModel;
import com.erp.FinancialYear.Repository.IFinancialYearRepository;
import com.erp.FinancialYear.Repository.IFinancialYearViewRepository;
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
public class CFinancialYearServiceImpl implements IFinancialYearService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IFinancialYearRepository iFinancialYearRepository;

	@Autowired
	IFinancialYearViewRepository iFinancialYearViewRepository;


	@Override
	public Object FnDeleteRecord(int financial_year_id, String deleted_by) {
		// TODO Auto-generated method stub
		return iFinancialYearRepository.FnDeleteRecord(financial_year_id, deleted_by);
	}


	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CFinancialYearViewModel> data = iFinancialYearViewRepository.FnShowAllActiveRecords(pageable);
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
	public JSONObject FnAddUpdateRecord(CFinancialYearModel financialYearModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CFinancialYearModel MyModel = null;
		int company_id = financialYearModel.getCompany_id();
		try {
			Optional<CFinancialYearModel> option = iFinancialYearRepository.findById(financialYearModel.getFinancial_year_id());

			if (option.isPresent()) {
				CFinancialYearModel mymodel = iFinancialYearRepository.save(financialYearModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
			}
//			Check similar Financial Year, short name or Short Year is exist or not
			CFinancialYearModel resultsFinacialName = null;

			if (financialYearModel.getShort_name() == null
					|| financialYearModel.getShort_name().isEmpty()) {

				resultsFinacialName = iFinancialYearRepository.getCheck(financialYearModel.getFinancial_year(),
						null, financialYearModel.getCompany_id());

			} else {
				resultsFinacialName = iFinancialYearRepository.getCheck(financialYearModel.getFinancial_year(),
						financialYearModel.getShort_name(), financialYearModel.getCompany_id());
			}

			if (resultsFinacialName != null) {
				resp.put("success", 0);
				resp.put("error", " Financial Year, short name or Short Year is already exist!");
				return resp;
			}else {

				CFinancialYearModel FinancialYearModel = iFinancialYearRepository.save(financialYearModel);
				String json = objectMapper.writeValueAsString(FinancialYearModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/finaincialyear/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/finaincialyear/FnAddUpdateRecord", 0, e.getMessage());
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
			Page<CFinancialYearViewModel> data = iFinancialYearViewRepository.FnShowAllRecords(pageable);
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
	public JSONObject FnShowParticularRecord(int company_id, int financial_year_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CFinancialYearViewModel json = iFinancialYearViewRepository.FnShowParticularRecord(company_id, financial_year_id);
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int financial_year_id) {
		Map<String, Object> resp = new HashMap<String, Object>();
		try {

			CFinancialYearViewModel json = iFinancialYearViewRepository.FnShowParticularRecordForUpdate(financial_year_id);
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
			Page<Map<String, Object>> data = iFinancialYearViewRepository.FnShowAllReportRecords(pageable);
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
