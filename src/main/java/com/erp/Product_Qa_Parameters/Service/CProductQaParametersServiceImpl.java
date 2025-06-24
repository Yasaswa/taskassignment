package com.erp.Product_Qa_Parameters.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Qa_Parameters.Model.CProductQaParametersModel;
import com.erp.Product_Qa_Parameters.Model.CProductQaParametersViewModel;
import com.erp.Product_Qa_Parameters.Repository.IProductQaParametersRepository;
import com.erp.Product_Qa_Parameters.Repository.IProductQaParametersViewRepository;
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
public class CProductQaParametersServiceImpl implements IProductQaParametersService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;


	@Autowired
	IProductQaParametersRepository iProductQaParametersRepository;

	@Autowired
	IProductQaParametersViewRepository iProductQaParametersViewRepository;


	@Override
	public Object FnDeleteRecord(int product_qa_parameters_id, String deleted_by) {
		return iProductQaParametersRepository.FnDeleteRecord(product_qa_parameters_id, deleted_by);
	}


	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductQaParametersViewModel> data = iProductQaParametersViewRepository
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
	public JSONObject FnShowParticularRecordForUpdate(int product_qa_parameters_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductQaParametersViewModel json = iProductQaParametersViewRepository
					.FnShowParticularRecordForUpdate(product_qa_parameters_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int product_qa_parameters_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductQaParametersViewModel json = iProductQaParametersViewRepository.FnShowParticularRecord(company_id,
					product_qa_parameters_id);
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
			Page<Map<String, Object>> data = iProductQaParametersViewRepository.FnShowAllReportRecords(pageable);
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
	public JSONObject FnAddUpdateRecord(CProductQaParametersModel cProductQaParametersModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductQaParametersModel MyModel = null;
		int company_id = cProductQaParametersModel.getCompany_id();
		try {
			Optional<CProductQaParametersModel> option = iProductQaParametersRepository
					.findById(cProductQaParametersModel.getProduct_qa_parameters_id());

			if (option.isPresent()) {
				CProductQaParametersModel mymodel = iProductQaParametersRepository.save(cProductQaParametersModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Product Qa Parameters updated successfully!..");
				return resp;
			}
//			Check Product Qa Parameters Name or short name is exist or not

			CProductQaParametersModel resultsProductQaParameterName = null;

			if (cProductQaParametersModel.getProduct_qa_parameters_short_name() == null
					|| cProductQaParametersModel.getProduct_qa_parameters_short_name().isEmpty()) {

				resultsProductQaParameterName = iProductQaParametersRepository.getCheck(cProductQaParametersModel.getProduct_qa_parameters_name(), null,
						cProductQaParametersModel.getCompany_id());
			} else {
				resultsProductQaParameterName = iProductQaParametersRepository.getCheck(cProductQaParametersModel.getProduct_qa_parameters_name(),
						cProductQaParametersModel.getProduct_qa_parameters_short_name(), cProductQaParametersModel.getCompany_id());
			}

			if (resultsProductQaParameterName != null) {
				resp.put("success", 0);
				resp.put("error", "Product Qa Parameters Name or Short name is already exist!");
				return resp;
			} else {
				CProductQaParametersModel ProductQaParametersModel = iProductQaParametersRepository.save(cProductQaParametersModel);
				String json = objectMapper.writeValueAsString(ProductQaParametersModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");
				System.out.println(" Product Qa Parameters saved succesfully!..");
				return resp;
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productqaparameters/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productqaparameters/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}


	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductQaParametersViewModel> data = iProductQaParametersViewRepository.FnShowAllRecords(pageable);
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


}
