package com.erp.Product_Material_Grade.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Material_Grade.Model.CProductMaterialGradeModel;
import com.erp.Product_Material_Grade.Model.CProductMaterialGradeViewModel;
import com.erp.Product_Material_Grade.Repository.IProductMaterialGradeRepository;
import com.erp.Product_Material_Grade.Repository.IProductMaterialGradeViewRepository;
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
public class CProductMaterialGradeServiceImpl implements IProductMaterialGradeService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;


	@Autowired
	IProductMaterialGradeRepository iProductMaterialGradeRepository;

	@Autowired
	IProductMaterialGradeViewRepository iProductMaterialGradeViewRepository;


	@Override
	public Object FnDeleteRecord(int product_material_grade_id, String deleted_by) {
		return iProductMaterialGradeRepository.FnDeleteRecord(product_material_grade_id, deleted_by);
	}


	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CProductMaterialGradeViewModel> data = iProductMaterialGradeViewRepository.FnShowAllActiveRecords(pageable);
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
		try {
			Page<CProductMaterialGradeViewModel> data = iProductMaterialGradeViewRepository.FnShowAllRecords(pageable);
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
	public JSONObject FnShowParticularRecord(int product_material_grade_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductMaterialGradeViewModel json = iProductMaterialGradeViewRepository.FnShowParticularRecord(product_material_grade_id);
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
			Page<Map<String, Object>> data = iProductMaterialGradeViewRepository.FnShowAllReportRecords(pageable);
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
	public JSONObject FnAddUpdateRecord(CProductMaterialGradeModel cProductMaterialGradeModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductMaterialGradeModel MyModel = null;
		int company_id = cProductMaterialGradeModel.getCompany_id();
		try {
			Optional<CProductMaterialGradeModel> option = iProductMaterialGradeRepository
					.findById(cProductMaterialGradeModel.getProduct_material_grade_id());

			if (option.isPresent()) {
				cProductMaterialGradeModel.setModified_on(new Date());
				CProductMaterialGradeModel mymodel = iProductMaterialGradeRepository.save(cProductMaterialGradeModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Product  Material Grade updated successfully!..");
				return resp;
			}

//			Check similar Product Material Grade Name or short name is exist or not

			CProductMaterialGradeModel resultsProductMaterialGradeName = null;

			if (cProductMaterialGradeModel.getProduct_material_grade_short_name() == null
					|| cProductMaterialGradeModel.getProduct_material_grade_short_name().isEmpty()) {

				resultsProductMaterialGradeName = iProductMaterialGradeRepository.getCheck(cProductMaterialGradeModel.getProduct_material_grade_name(), null,
						cProductMaterialGradeModel.getCompany_id());
			} else {
				resultsProductMaterialGradeName = iProductMaterialGradeRepository.getCheck(cProductMaterialGradeModel.getProduct_material_grade_name(),
						cProductMaterialGradeModel.getProduct_material_grade_short_name(), cProductMaterialGradeModel.getCompany_id());
			}

			if (resultsProductMaterialGradeName != null) {
				resp.put("success", 0);
				resp.put("error", "Product Material Grade Name or Short Name is already exist!");
				return resp;
			} else {

				CProductMaterialGradeModel ProductMaterialGradeModel = iProductMaterialGradeRepository.save(cProductMaterialGradeModel);
				String json = objectMapper.writeValueAsString(ProductMaterialGradeModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");
				System.out.println(" Product Material Grade saved succesfully!..");
				return resp;
			}


		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productmaterialgrade/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productmaterialgrade/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}


}
