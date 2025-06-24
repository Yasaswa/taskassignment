package com.erp.Product_Category2.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Category1.Model.CProductCategory1Model;
import com.erp.Product_Category2.Model.CProductCategory2Model;
import com.erp.Product_Category2.Model.CProductCategory2ViewModel;
import com.erp.Product_Category2.Repository.IProductCategory2Repository;
import com.erp.Product_Category2.Repository.IProductCategory2ViewRepository;
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
public class CProductCategory2ServiceImpl implements IProductCategory2Service {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductCategory2Repository iProductCategory2Repository;

	@Autowired
	IProductCategory2ViewRepository iProductCategory2ViewRepository;

	

	@Override
	public JSONObject FnAddUpdateRecord(CProductCategory2Model cProductCategory2Model) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductCategory1Model MyModel = null;
		int company_id = cProductCategory2Model.getCompany_id();
		try {
			Optional<CProductCategory2Model> option = iProductCategory2Repository
					.findById(cProductCategory2Model.getProduct_category2_id());

			if (option.isPresent()) {
				cProductCategory2Model.setModified_on(new Date());
				CProductCategory2Model mymodel = iProductCategory2Repository.save(cProductCategory2Model);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Product Category 2 updated successfully!..");
				return resp;
			} else {
				
				//Check similar Product Category 2 Name and short name is exist or not
				CProductCategory2Model resultsProductCategory2Name = null;
				if (cProductCategory2Model.getProduct_category2_short_name() == null || cProductCategory2Model.getProduct_category2_short_name().isEmpty()) {
					resultsProductCategory2Name = iProductCategory2Repository.getCheck(cProductCategory2Model.getProduct_category2_name(),
							null, cProductCategory2Model.getCompany_id(), cProductCategory2Model.getProduct_category1_id() );
				} else {
					resultsProductCategory2Name = iProductCategory2Repository.getCheck(cProductCategory2Model.getProduct_category2_name(),
							cProductCategory2Model.getProduct_category2_short_name(),cProductCategory2Model.getCompany_id(), cProductCategory2Model.getProduct_category1_id());
				}

				if (resultsProductCategory2Name != null) {
					resp.put("success", 0);
					resp.put("error", "Product Category 2 Name or Short name is already exist!");
					return resp;
				}else {

					CProductCategory2Model ProductCategory2Model = iProductCategory2Repository.save(cProductCategory2Model);
					String json = objectMapper.writeValueAsString(ProductCategory2Model);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
					System.out.println(" Product Category 2 saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory2/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory2/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	
	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductCategory2ViewModel> data = iProductCategory2ViewRepository
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
	public JSONObject FnShowParticularRecordForUpdate(int product_category2_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductCategory2ViewModel json = iProductCategory2ViewRepository
					.FnShowParticularRecordForUpdate(product_category2_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int product_category2_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductCategory2ViewModel json = iProductCategory2ViewRepository.FnShowParticularRecord(company_id,
					product_category2_id);
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
			Page<Map<String, Object>> data = iProductCategory2ViewRepository.FnShowAllReportRecords(pageable);
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
	public Object FnDeleteRecord(int product_category2_id, String deleted_by) {
		return iProductCategory2Repository.FnDeleteRecord(product_category2_id, deleted_by);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductCategory2ViewModel> data = iProductCategory2ViewRepository
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


}
