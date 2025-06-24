package com.erp.Product_Material_Shape.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Material_Shape.Model.CProductMaterialShapeModel;
import com.erp.Product_Material_Shape.Model.CProductMaterialShapeViewModel;
import com.erp.Product_Material_Shape.Repository.IProductMaterialShapeRepository;
import com.erp.Product_Material_Shape.Repository.IProductMaterialShapeViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CProductMaterialShapeServiceImpl implements IProductMaterialShapeService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductMaterialShapeRepository iProductMaterialShapeRepository;

	@Autowired
	IProductMaterialShapeViewRepository iProductMaterialShapeViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CProductMaterialShapeModel cProductMaterialShapeModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		Optional<CProductMaterialShapeModel> option = iProductMaterialShapeRepository
				.findById(cProductMaterialShapeModel.getProduct_material_shape_id());
		CProductMaterialShapeModel MyModel = null;
		int company_id = cProductMaterialShapeModel.getCompany_id();
		try {
			if (option.isPresent()) {
				CProductMaterialShapeModel WeeklyoffModel = iProductMaterialShapeRepository
						.save(cProductMaterialShapeModel);
				String json = objectMapper.writeValueAsString(WeeklyoffModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated successfully!...");
				System.out.println(" Product Material Shape Updated Successfully!..");
				return resp;
			}
//			Check similar Product Material Shape Name or short name is exist or not

			CProductMaterialShapeModel resultsProductMaterialShpaeName = null;

			if (cProductMaterialShapeModel.getProduct_material_shape_short_name() == null
					|| cProductMaterialShapeModel.getProduct_material_shape_short_name().isEmpty()) {

				resultsProductMaterialShpaeName = iProductMaterialShapeRepository.getCheck(
						cProductMaterialShapeModel.getProduct_material_shape_name(), null,
						cProductMaterialShapeModel.getCompany_id());
			} else {
				resultsProductMaterialShpaeName = iProductMaterialShapeRepository.getCheck(
						cProductMaterialShapeModel.getProduct_material_shape_name(),
						cProductMaterialShapeModel.getProduct_material_shape_short_name(),
						cProductMaterialShapeModel.getCompany_id());
			}

			if (resultsProductMaterialShpaeName != null) {
				resp.put("success", 0);
				resp.put("error", "Product Material Shape Name or Short Name is already exist!");
				return resp;
			} else {

				CProductMaterialShapeModel json = iProductMaterialShapeRepository.save(cProductMaterialShapeModel);
				String json1 = objectMapper.writeValueAsString(json);
				resp.put("success", "1");
				resp.put("data", json1);
				resp.put("error", "");
				resp.put("message", "Record saved successfully!...");

				System.out.println("Product Material Shape saved succesfully!..");
				return resp;
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/productmaterialshape/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/productmaterialshape/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_material_shape_id, String deleted_by) {
		return iProductMaterialShapeRepository.FnDeleteRecord(product_material_shape_id, deleted_by);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CProductMaterialShapeViewModel> data = iProductMaterialShapeViewRepository.FnShowAllRecords(pageable);
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
			Page<CProductMaterialShapeViewModel> data = iProductMaterialShapeViewRepository
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
	public JSONObject FnShowParticularRecord(int company_id, int product_material_shape_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductMaterialShapeViewModel json = iProductMaterialShapeViewRepository.FnShowParticularRecord(company_id,
					product_material_shape_id);
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_material_shape_id) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductMaterialShapeViewModel cProductMaterialShapeViewModel = null;
		try {

			cProductMaterialShapeViewModel = iProductMaterialShapeViewRepository
					.FnShowParticularRecordForUpdate(product_material_shape_id);
			responce.put("success", "1");
			responce.put("data", cProductMaterialShapeViewModel);
			responce.put("error", "");
			return responce;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

			return responce;
		}

		return responce;
	}

	@Override
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iProductMaterialShapeViewRepository.FnShowAllReportRecords(pageable);

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
