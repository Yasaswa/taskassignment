package com.erp.Product_Category5.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Category4.Model.CProductCategory4Model;
import com.erp.Product_Category5.Model.CProductCategory5Model;
import com.erp.Product_Category5.Model.CProductCategory5ViewModel;
import com.erp.Product_Category5.Repository.IProductCategory5Repository;
import com.erp.Product_Category5.Repository.IProductCategory5ViewRepository;
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
public class CProductCategory5ServiveImpl implements IProductCategory5Service {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductCategory5Repository iProductCategory5Repository;

	@Autowired
	IProductCategory5ViewRepository iProductCategory5ViewRepository;


	@Override
	public JSONObject FnAddUpdateRecord(CProductCategory5Model cProductCategory5Model) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductCategory5Model MyModel = null;
		int company_id = cProductCategory5Model.getCompany_id();
		try {
			Optional<CProductCategory5Model> option = iProductCategory5Repository
					.findById(cProductCategory5Model.getProduct_category5_id());

			if (option.isPresent()) {
				cProductCategory5Model.setModified_on(new Date());
				CProductCategory5Model mymodel = iProductCategory5Repository.save(cProductCategory5Model);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Product Category 5 updated successfully!..");
				return resp;
			} else {

				//Check similar Product Category 4 Name and short name is exist or not
				CProductCategory5Model resultsProductCategory5Name = null;
				if (cProductCategory5Model.getProduct_category5_short_name() == null || cProductCategory5Model.getProduct_category5_short_name().isEmpty()) {
					resultsProductCategory5Name = iProductCategory5Repository.getCheck(cProductCategory5Model.getProduct_category5_name(),
							null, cProductCategory5Model.getCompany_id());
				} else {
					resultsProductCategory5Name = iProductCategory5Repository.getCheck(cProductCategory5Model.getProduct_category5_name(),
							cProductCategory5Model.getProduct_category5_short_name(),cProductCategory5Model.getCompany_id());
				}

				if (resultsProductCategory5Name != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Product Category 5 Name or Short Name is already exist!");
					return resp;
				}else {

					CProductCategory5Model ProductCategory5Model = iProductCategory5Repository.save(cProductCategory5Model);
					String json = objectMapper.writeValueAsString(ProductCategory5Model);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
					System.out.println(" Product Category 5 saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory5/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory5/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_category5_id, String deleted_by) {
		return iProductCategory5Repository.FnDeleteRecord(product_category5_id, deleted_by);
	}


	@Override
	public JSONObject FnShowParticularRecordForUpdate(int product_category5_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductCategory5ViewModel json = iProductCategory5ViewRepository
					.FnShowParticularRecordForUpdate(product_category5_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int product_category5_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductCategory5ViewModel json = iProductCategory5ViewRepository.FnShowParticularRecord(company_id,
					product_category5_id);
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
			Page<Map<String, Object>> data = iProductCategory5ViewRepository.FnShowAllReportRecords(pageable);
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
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductCategory5ViewModel> data = iProductCategory5ViewRepository
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
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductCategory5ViewModel> data = iProductCategory5ViewRepository
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

}
