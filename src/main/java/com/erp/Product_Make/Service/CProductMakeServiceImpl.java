package com.erp.Product_Make.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Make.Model.CProductMakeModel;
import com.erp.Product_Make.Model.CProductMakeViewModel;
import com.erp.Product_Make.Repository.IProductMakeRepository;
import com.erp.Product_Make.Repository.IProductMakeViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class CProductMakeServiceImpl implements IProductMakeService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductMakeRepository iProductMakeRepository;

	@Autowired
	IProductMakeViewRepository iProductMakeViewRepository;

	@Override
	public Object FnDeleteRecord(int product_make_id, String deleted_by) {
		return iProductMakeRepository.FnDeleteRecord(product_make_id, deleted_by);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductMakeViewModel> data = iProductMakeViewRepository.FnShowAllRecords(pageable);
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
			Page<CProductMakeViewModel> data = iProductMakeViewRepository.FnShowAllActiveRecords(pageable);
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
	public JSONObject FnShowParticularRecordForUpdate(int product_make_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductMakeViewModel json = iProductMakeViewRepository.FnShowParticularRecordForUpdate(product_make_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int product_make_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductMakeViewModel json = iProductMakeViewRepository.FnShowParticularRecord(company_id, product_make_id);
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
			Map<String, Object> data = iProductMakeViewRepository.FnShowAllReportRecords();
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
	public JSONObject FnAddUpdateRecord(CProductMakeModel cProductMakeModel) {

		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductMakeModel MyModel = null;
		int company_id = cProductMakeModel.getCompany_id();

		try {
			Optional<CProductMakeModel> option = iProductMakeRepository
					.findById(cProductMakeModel.getProduct_make_id());

			if (option.isPresent()) {
				cProductMakeModel.setModified_on(new Date());
				CProductMakeModel mymodel = iProductMakeRepository.save(cProductMakeModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Product make  updated successfully!..");
				return resp;
			}

//			Check similar Product Make Name or short name is exist or not

			CProductMakeModel resultsProductMakeName = null;

			if (cProductMakeModel.getProduct_make_short_name() == null
					|| cProductMakeModel.getProduct_make_short_name().isEmpty()) {
				resultsProductMakeName = iProductMakeRepository.getCheck(cProductMakeModel.getProduct_make_name(), null,
						cProductMakeModel.getCompany_id());
			} else {
				resultsProductMakeName = iProductMakeRepository.getCheck(cProductMakeModel.getProduct_make_name(),
						cProductMakeModel.getProduct_make_short_name(), cProductMakeModel.getCompany_id());
			}

			if (resultsProductMakeName != null) {
				resp.put("success", 0);
				resp.put("error", "Product Make Name or Short Name is already exist!");
				return resp;
			} else {

				CProductMakeModel ProductMakeModel = iProductMakeRepository.save(cProductMakeModel);

				String json = objectMapper.writeValueAsString(ProductMakeModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");
				System.out.println(" Product make saved succesfully!..");

				return resp;
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productmake/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productmake/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

}
