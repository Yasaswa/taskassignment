package com.erp.Product_Category3.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Category3.Model.CProductCategory3Model;
import com.erp.Product_Category3.Model.CProductCategory3ViewModel;
import com.erp.Product_Category3.Repository.IProductCategory3Repository;
import com.erp.Product_Category3.Repository.IProductCategory3ViewRepository;
import com.erp.Product_Type.Model.CProductTypeModel;
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
public class CProductCategory3ServiceImpl implements IProductCategory3Service {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductCategory3Repository iProductCategory3Repository;

	@Autowired
	IProductCategory3ViewRepository iProductCategory3ViewRepository;


	@Override
	public JSONObject FnAddUpdateRecord(CProductCategory3Model cProductCategory3Model) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductCategory3Model MyModel = null;
		int company_id = cProductCategory3Model.getCompany_id();
		try {
			Optional<CProductCategory3Model> option = iProductCategory3Repository
					.findById(cProductCategory3Model.getProduct_category3_id());

			if (option.isPresent()) {
				cProductCategory3Model.setModified_on(new Date());
				CProductCategory3Model mymodel = iProductCategory3Repository.save(cProductCategory3Model);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println("Product Category 3 updated successfully!..");
				return resp;
			} else {
				
				//Check similar Product Category 3 Name and short name is exist or not
				CProductCategory3Model resultsProductCategory3Name = null;
				if (cProductCategory3Model.getProduct_category3_short_name() == null || cProductCategory3Model.getProduct_category3_short_name().isEmpty()) {
					resultsProductCategory3Name = iProductCategory3Repository.getCheck(cProductCategory3Model.getProduct_category3_name(),
							null, cProductCategory3Model.getCompany_id());
				} else {
					resultsProductCategory3Name = iProductCategory3Repository.getCheck(cProductCategory3Model.getProduct_category3_name(),
							cProductCategory3Model.getProduct_category3_short_name(),cProductCategory3Model.getCompany_id());
				}

				if (resultsProductCategory3Name != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Product Category 3 Name or Short Name is already exist!");
					return resp;
				}else {

					CProductCategory3Model ProductCategory3Model = iProductCategory3Repository.save(cProductCategory3Model);
					String json = objectMapper.writeValueAsString(ProductCategory3Model);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
					System.out.println(" Product Category 3 saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory3/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory3/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_category3_id, String deleted_by) {
		return iProductCategory3Repository.FnDeleteRecord(product_category3_id, deleted_by);
	}


	@Override
	public JSONObject FnShowParticularRecordForUpdate(int product_category3_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductCategory3ViewModel json = iProductCategory3ViewRepository
					.FnShowParticularRecordForUpdate(product_category3_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int product_category3_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductCategory3ViewModel json = iProductCategory3ViewRepository.FnShowParticularRecord(company_id,
					product_category3_id);
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
			Page<Map<String, Object>> data = iProductCategory3ViewRepository.FnShowAllReportRecords(pageable);
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
			Page<CProductCategory3ViewModel> data = iProductCategory3ViewRepository
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
			Page<CProductCategory3ViewModel> data = iProductCategory3ViewRepository
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
