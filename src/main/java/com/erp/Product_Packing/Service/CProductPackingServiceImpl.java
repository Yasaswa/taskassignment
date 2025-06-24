package com.erp.Product_Packing.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Packing.Model.CProductPackingModel;
import com.erp.Product_Packing.Model.CProductPackingViewModel;
import com.erp.Product_Packing.Repository.IProductPackingRepository;
import com.erp.Product_Packing.Repository.IProductPackingViewRepository;
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
public class CProductPackingServiceImpl implements IProductPackingService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductPackingRepository iProductPackingRepository;

	@Autowired
	IProductPackingViewRepository iProductPackingViewRepository;

	@Override
	public Object FnDeleteRecord(int product_packing_id, String deleted_by) {
		return iProductPackingRepository.FnDeleteRecord(product_packing_id, deleted_by);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductPackingViewModel> data = iProductPackingViewRepository
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
			Page<CProductPackingViewModel> data = iProductPackingViewRepository
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
	public JSONObject FnShowParticularRecordForUpdate(int product_packing_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductPackingViewModel json = iProductPackingViewRepository
					.FnShowParticularRecordForUpdate(product_packing_id);
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
	public JSONObject FnShowParticularRecord(int product_packing_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductPackingViewModel json = iProductPackingViewRepository.FnShowParticularRecord(
					product_packing_id);
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
	public JSONObject FnAddUpdateRecord(CProductPackingModel cProductPackingModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductPackingModel MyModel = null;
		int company_id = cProductPackingModel.getCompany_id();
		try {
			Optional<CProductPackingModel> option = iProductPackingRepository
					.findById(cProductPackingModel.getProduct_packing_id());

			if (option.isPresent()) {
				CProductPackingModel mymodel = iProductPackingRepository.save(cProductPackingModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Product Packing updated successfully!..");
				return resp;
			} else {
				CProductPackingModel model = iProductPackingRepository
						.getCheck(cProductPackingModel.getProduct_packing_name(), cProductPackingModel.getCompany_id());
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "  Product Packing Name is already exist!");

					return resp;

				} else {

					CProductPackingModel ProductPackingModel = iProductPackingRepository.save(cProductPackingModel);
					String json = objectMapper.writeValueAsString(ProductPackingModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
					System.out.println(" Product Packing saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productpacking/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productpacking/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public Object FnShowAllReportRecords() {
		JSONObject resp = new JSONObject();
		try {
			Map<String, Object> data = iProductPackingViewRepository.FnShowAllReportRecords();
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
