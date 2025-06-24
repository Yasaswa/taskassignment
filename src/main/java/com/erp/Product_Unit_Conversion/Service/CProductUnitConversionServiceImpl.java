package com.erp.Product_Unit_Conversion.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Unit_Conversion.Model.CProductUnitConversionModel;
import com.erp.Product_Unit_Conversion.Model.CProductUnitConversionViewModel;
import com.erp.Product_Unit_Conversion.Repository.IProductUnitConversionRepository;
import com.erp.Product_Unit_Conversion.Repository.IProductUnitConversionViewRepository;
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
public class CProductUnitConversionServiceImpl implements IProductUnitConversionService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductUnitConversionRepository iProductUnitConversionRepository;

	@Autowired
	IProductUnitConversionViewRepository iProductUnitConversionViewRepository;

	@Override
	public Object FnDeleteRecord(int conversion_id, String deleted_by) {
		return iProductUnitConversionRepository.FnDeleteRecord(conversion_id, deleted_by);
	}

	@Override
	public JSONObject FnShowParticularRecordForUpdate(int conversion_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductUnitConversionViewModel json = iProductUnitConversionViewRepository
					.FnShowParticularRecordForUpdate(conversion_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int conversion_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductUnitConversionViewModel json = iProductUnitConversionViewRepository
					.FnShowParticularRecord(conversion_id, company_id);
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
	public Map<String, Object> FnAddUpdateRecord(CProductUnitConversionModel cProductUnitConversionModel) {
		Map<String, Object> resp = new HashMap<>();
		CProductUnitConversionModel MyModel = null;
		int company_id = cProductUnitConversionModel.getCompany_id();
		try {
			Optional<CProductUnitConversionModel> option = iProductUnitConversionRepository
					.findById(cProductUnitConversionModel.getConversion_id());

			if (option.isPresent()) {
				CProductUnitConversionModel mymodel = iProductUnitConversionRepository
						.save(cProductUnitConversionModel);
				resp.put("success", "1");
				resp.put("data", mymodel);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
			} else {

				CProductUnitConversionModel ProductUnitConversionModel = iProductUnitConversionRepository
						.save(cProductUnitConversionModel);
				resp.put("success", "1");
				resp.put("data", ProductUnitConversionModel);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/productunitconversion/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/productunitconversion/FnAddUpdateRecord", 0, e.getMessage());
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
			Page<CProductUnitConversionViewModel> data = iProductUnitConversionViewRepository
					.FnShowAllRecords(pageable);
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
			Page<CProductUnitConversionViewModel> data = iProductUnitConversionViewRepository
					.FnShowAllActiveRecords(pageable);
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
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) {
		return iProductUnitConversionViewRepository.FnShowAllReportRecords(pageable);
	}

}
