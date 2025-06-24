package com.erp.Product_Material_Type.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Material_Type.Model.CProductMaterialTypeModel;
import com.erp.Product_Material_Type.Model.CProductMaterialTypeViewModel;
import com.erp.Product_Material_Type.Repository.IProductMaterialTypeRepository;
import com.erp.Product_Material_Type.Repository.IProductMaterialTypeViewRepository;
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
public class CProductMaterialTypeServiceImpl implements IProductMaterialTypeService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductMaterialTypeRepository iProductMaterialTypeRepository;

	@Autowired
	IProductMaterialTypeViewRepository iProductMaterialTypeViewRepository;

	@Override
	public Object FnDeleteRecord(int product_material_type_id, String deleted_by) {
		return iProductMaterialTypeRepository.FnDeleteRecord(product_material_type_id, deleted_by);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductMaterialTypeViewModel> data = iProductMaterialTypeViewRepository.FnShowAllRecords(pageable);
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
			Page<CProductMaterialTypeViewModel> data = iProductMaterialTypeViewRepository
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
	public JSONObject FnShowParticularRecord(int product_material_type_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductMaterialTypeViewModel json = iProductMaterialTypeViewRepository
					.FnShowParticularRecord(product_material_type_id);
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
	public JSONObject FnShowParticularRecordForUpdate(int product_material_type_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductMaterialTypeViewModel json = iProductMaterialTypeViewRepository
					.FnShowParticularRecordForUpdate(product_material_type_id);
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
	public JSONObject FnAddUpdateRecord(CProductMaterialTypeModel cProductMaterialTypeModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		Optional<CProductMaterialTypeModel> option = iProductMaterialTypeRepository
				.findById(cProductMaterialTypeModel.getProduct_material_type_id());
		CProductMaterialTypeModel MyModel = null;
		int company_id = cProductMaterialTypeModel.getCompany_id();
		try {
			if (option.isPresent()) {
				CProductMaterialTypeModel ProductMaterialTypeModel = iProductMaterialTypeRepository
						.save(cProductMaterialTypeModel);
				String json = objectMapper.writeValueAsString(ProductMaterialTypeModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated successfully!...");
				System.out.println(" Product Material Type Updated Successfully!..");
				return resp;
			}

//			Check similar Product Material Type Name or short name is exist or not

			CProductMaterialTypeModel resultsProductMaterialTypeName = null;

			if (cProductMaterialTypeModel.getProduct_material_type_short_name() == null
					|| cProductMaterialTypeModel.getProduct_material_type_short_name().isEmpty()) {

				resultsProductMaterialTypeName = iProductMaterialTypeRepository.getCheck(
						cProductMaterialTypeModel.getProduct_material_type_name(), null,
						cProductMaterialTypeModel.getCompany_id());
			} else {
				resultsProductMaterialTypeName = iProductMaterialTypeRepository.getCheck(
						cProductMaterialTypeModel.getProduct_material_type_name(),
						cProductMaterialTypeModel.getProduct_material_type_short_name(),
						cProductMaterialTypeModel.getCompany_id());
			}

			if (resultsProductMaterialTypeName != null) {
				resp.put("success", 0);
				resp.put("error", "Product Material Type Name or Short Name is already exist!");
				return resp;
			} else {

				CProductMaterialTypeModel json = iProductMaterialTypeRepository.save(cProductMaterialTypeModel);
				String json1 = objectMapper.writeValueAsString(json);
				resp.put("success", "1");
				resp.put("data", json1);
				resp.put("error", "");
				resp.put("message", "Record added successfully!...");

				System.out.println("  Product Material Type Modelsaved succesfully!..");
				return resp;
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/productMaterialtype/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/productMaterialtype/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

	@Override
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {

			Map<String, String> data = iProductMaterialTypeViewRepository.FnShowAllReportRecords();
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
