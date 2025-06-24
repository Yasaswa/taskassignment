package com.erp.Product_Category4.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Category3.Model.CProductCategory3Model;
import com.erp.Product_Category4.Model.CProductCategory4Model;
import com.erp.Product_Category4.Model.CProductCategory4ViewModel;
import com.erp.Product_Category4.Repository.IProductCategory4Repository;
import com.erp.Product_Category4.Repository.IProductCategory4ViewRepository;
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
public class CProductCategory4ServiceImpl implements IProductCategory4Service {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductCategory4Repository iProductCategory4Repository;

	@Autowired
	IProductCategory4ViewRepository iProductCategory4ViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CProductCategory4Model cProductCategory4Model) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductCategory4Model MyModel = null;
		int company_id = cProductCategory4Model.getCompany_id();
		try {
			Optional<CProductCategory4Model> option = iProductCategory4Repository
					.findById(cProductCategory4Model.getProduct_category4_id());

			if (option.isPresent()) {
				cProductCategory4Model.setModified_on(new Date());
				CProductCategory4Model mymodel = iProductCategory4Repository.save(cProductCategory4Model);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Product Category 4 updated successfully!..");
				return resp;
			} else {
//				CProductCategory4Model model = iProductCategory4Repository
//						.getCheck(cProductCategory4Model.getProduct_category4_name());
//				if (model != null) {
//					resp.put("success", "0");
//					resp.put("data", "");
//					resp.put("error", "Product Category 4 Name is already exist!");
//
//					return resp;
//
//				} 
				//Check similar Product Category 4 Name and short name is exist or not
				CProductCategory4Model resultsProductCategory4Name = null;
				if (cProductCategory4Model.getProduct_category4_short_name() == null || cProductCategory4Model.getProduct_category4_short_name().isEmpty()) {
					resultsProductCategory4Name = iProductCategory4Repository.getCheck(cProductCategory4Model.getProduct_category4_name(),
							null, cProductCategory4Model.getCompany_id());
				} else {
					resultsProductCategory4Name = iProductCategory4Repository.getCheck(cProductCategory4Model.getProduct_category4_name(),
							cProductCategory4Model.getProduct_category4_short_name(),cProductCategory4Model.getCompany_id());
				}

				if (resultsProductCategory4Name != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Product Category 4 Name or Short Name is already exist!");
					return resp;
				}else {

					CProductCategory4Model ProductCategory4Model = iProductCategory4Repository.save(cProductCategory4Model);
					String json = objectMapper.writeValueAsString(ProductCategory4Model);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
					System.out.println("Product Category 4 saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory4/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory4/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_category4_id, String deleted_by) {
		return iProductCategory4Repository.FnDeleteRecord(product_category4_id, deleted_by);
	}


	@Override
	public JSONObject FnShowParticularRecordForUpdate(int product_category4_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductCategory4ViewModel json = iProductCategory4ViewRepository
					.FnShowParticularRecordForUpdate(product_category4_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int product_category4_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductCategory4ViewModel json = iProductCategory4ViewRepository.FnShowParticularRecord(company_id,
					product_category4_id);
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
			Page<Map<String, Object>> data = iProductCategory4ViewRepository.FnShowAllReportRecords(pageable);
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
			Page<CProductCategory4ViewModel> data = iProductCategory4ViewRepository
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
			Page<CProductCategory4ViewModel> data = iProductCategory4ViewRepository
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
