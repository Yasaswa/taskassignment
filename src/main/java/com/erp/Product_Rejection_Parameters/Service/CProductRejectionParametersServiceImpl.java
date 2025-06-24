package com.erp.Product_Rejection_Parameters.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Rejection_Parameters.Model.CProductRejectionParametersModel;
import com.erp.Product_Rejection_Parameters.Model.CProductRejectionParametersViewModel;
import com.erp.Product_Rejection_Parameters.Repository.IProductRejectionParametersRepository;
import com.erp.Product_Rejection_Parameters.Repository.IProductRejectionParametersViewRepository;
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
public class CProductRejectionParametersServiceImpl implements IProductRejectionParametersService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductRejectionParametersRepository iProductRejectionParametersRepository;

	@Autowired
	IProductRejectionParametersViewRepository iProductRejectionParametersViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CProductRejectionParametersModel cProductRejectionParametersModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductRejectionParametersModel MyModel = null;
		int company_id = cProductRejectionParametersModel.getCompany_id();
		try {
			Optional<CProductRejectionParametersModel> option = iProductRejectionParametersRepository
					.findById(cProductRejectionParametersModel.getProduct_rejection_parameters_id());

			if (option.isPresent()) {
				CProductRejectionParametersModel mymodel = iProductRejectionParametersRepository
						.save(cProductRejectionParametersModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Product Rejection Parameters updated successfully!..");
				return resp;
			}
//			Check similar Product Rejection Name or short name is exist or not

			CProductRejectionParametersModel resultsProductRejectionName = null;

			if (cProductRejectionParametersModel.getProduct_rejection_parameters_short_name() == null
					|| cProductRejectionParametersModel.getProduct_rejection_parameters_short_name().isEmpty()) {

				resultsProductRejectionName = iProductRejectionParametersRepository.getCheck(
						cProductRejectionParametersModel.getProduct_rejection_parameters_name(), null,
						cProductRejectionParametersModel.getCompany_id());
			} else {
				resultsProductRejectionName = iProductRejectionParametersRepository.getCheck(
						cProductRejectionParametersModel.getProduct_rejection_parameters_name(),
						cProductRejectionParametersModel.getProduct_rejection_parameters_short_name(),
						cProductRejectionParametersModel.getCompany_id());
			}

			if (resultsProductRejectionName != null) {
				resp.put("success", 0);
				resp.put("error", "Rejection Parameter Name or Short Name is already exist!");
				return resp;
			} else {
				CProductRejectionParametersModel ProductRejectionParametersModel = iProductRejectionParametersRepository
						.save(cProductRejectionParametersModel);
				String json = objectMapper.writeValueAsString(ProductRejectionParametersModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");
				System.out.println(" Product Rejection Parameters saved succesfully!..");
				return resp;
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/Productrejectionparameter/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/Productrejectionparameter/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_rejection_parameters_id, String deleted_by) {
		return iProductRejectionParametersRepository.FnDeleteRecord(product_rejection_parameters_id, deleted_by);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CProductRejectionParametersViewModel> data = iProductRejectionParametersViewRepository
					.FnShowAllRecords(pageable);
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
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CProductRejectionParametersViewModel> data = iProductRejectionParametersViewRepository
					.FnShowAllActiveRecords(pageable);
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
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iProductRejectionParametersViewRepository.FnShowAllReportRecords(pageable);
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
	public JSONObject FnShowParticularRecord(int company_id, int product_rejection_parameters_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductRejectionParametersViewModel json = iProductRejectionParametersViewRepository
					.FnShowParticularRecord(company_id, product_rejection_parameters_id);
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
	public JSONObject FnShowParticularRecordForUpdate(int product_rejection_parameters_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductRejectionParametersViewModel json = iProductRejectionParametersViewRepository
					.FnShowParticularRecordForUpdate(product_rejection_parameters_id);
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
