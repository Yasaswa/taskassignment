package com.erp.Product_Material_Measure_Parameters.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Material_Measure_Parameters.Model.CProductMaterialMeasureParametersModel;
import com.erp.Product_Material_Measure_Parameters.Model.CProductMaterialMeasureParametersViewModel;
import com.erp.Product_Material_Measure_Parameters.Repository.IProductMaterialMeasureParametersRepository;
import com.erp.Product_Material_Measure_Parameters.Repository.IProductMaterialMeasureParametersViewRepository;
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
public class CProductMaterialMeasureParametersServiceImpl implements IProductMaterialMeasureParametersService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductMaterialMeasureParametersRepository iProductMaterialMeasureParametersRepository;

	@Autowired
	IProductMaterialMeasureParametersViewRepository iProductMaterialMeasureParametersViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CProductMaterialMeasureParametersModel cProductMaterialMeasureParametersModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CProductMaterialMeasureParametersModel MyModel = null;
		int company_id = cProductMaterialMeasureParametersModel.getCompany_id();
		try {
			Optional<CProductMaterialMeasureParametersModel> option = iProductMaterialMeasureParametersRepository
					.findById(cProductMaterialMeasureParametersModel.getProduct_material_measure_parameter_id());

			if (option.isPresent()) {
				CProductMaterialMeasureParametersModel mymodel = iProductMaterialMeasureParametersRepository.save(cProductMaterialMeasureParametersModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println("Product Material Measure Parameters updated successfully!..");
				return resp;
			}
//			Check similar Product Material Measure Parameter Name or short name is exist or not

			CProductMaterialMeasureParametersModel resultsProductMaterialParameterName = null;

			if (cProductMaterialMeasureParametersModel.getProduct_material_measure_parameter_short_name() == null
					|| cProductMaterialMeasureParametersModel.getProduct_material_measure_parameter_short_name().isEmpty()) {

				resultsProductMaterialParameterName = iProductMaterialMeasureParametersRepository.getCheck(
						cProductMaterialMeasureParametersModel.getProduct_material_measure_parameter_name(), null,
						cProductMaterialMeasureParametersModel.getCompany_id());
			} else {
				resultsProductMaterialParameterName = iProductMaterialMeasureParametersRepository.getCheck(
						cProductMaterialMeasureParametersModel.getProduct_material_measure_parameter_name(),
						cProductMaterialMeasureParametersModel.getProduct_material_measure_parameter_short_name(),
						cProductMaterialMeasureParametersModel.getCompany_id());
			}

			if (resultsProductMaterialParameterName != null) {
				resp.put("success", "0");
				resp.put("error", "Product Material Measure Parameter Name or Short Name is already exist!");
				return resp;
			}


//			else {
//				CProductMaterialMeasureParametersModel model = iProductMaterialMeasureParametersRepository
//						.getCheck(cProductMaterialMeasureParametersModel.getProduct_material_measure_parameter_name(),cProductMaterialMeasureParametersModel.getProduct_material_measure_parameter_short_name(),cProductMaterialMeasureParametersModel.getCompany_id());
//				if (model != null) {
//					resp.put("success", "0");
//					resp.put("data", "");
//					resp.put("error", "Product Material Measure Parameters Name or Short Name is already exist!");
//
//					return resp;
//
//				} 
			else {

				CProductMaterialMeasureParametersModel ProductMaterialMeasureParametersModel = iProductMaterialMeasureParametersRepository.save(cProductMaterialMeasureParametersModel);
				String json = objectMapper.writeValueAsString(ProductMaterialMeasureParametersModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");
				System.out.println(" Product  Material Measure Parameters saved succesfully!..");
				return resp;
			}
//			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productrmmeasureparameter/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productrmmeasureparameter/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_material_measure_parameter_id, String deleted_by) {
		return iProductMaterialMeasureParametersRepository.FnDeleteRecord(product_material_measure_parameter_id, deleted_by);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CProductMaterialMeasureParametersViewModel> data = iProductMaterialMeasureParametersViewRepository
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
			Page<CProductMaterialMeasureParametersViewModel> data = iProductMaterialMeasureParametersViewRepository
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
	public JSONObject FnShowParticularRecord(int product_material_measure_parameter_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductMaterialMeasureParametersViewModel json = iProductMaterialMeasureParametersViewRepository
					.FnShowParticularRecord(product_material_measure_parameter_id);
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
	public JSONObject FnShowParticularRecordForUpdate(int product_material_measure_parameter_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductMaterialMeasureParametersViewModel json = iProductMaterialMeasureParametersViewRepository.FnShowParticularRecordForUpdate(product_material_measure_parameter_id);
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
			Page<Map<String, Object>> data = iProductMaterialMeasureParametersViewRepository.FnShowAllReportRecords(pageable);
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
