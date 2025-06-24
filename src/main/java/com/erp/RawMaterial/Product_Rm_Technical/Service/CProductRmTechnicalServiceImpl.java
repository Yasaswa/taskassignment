package com.erp.RawMaterial.Product_Rm_Technical.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.RawMaterial.Product_Rm_Technical.Model.CProductRmTechnicalModel;
import com.erp.RawMaterial.Product_Rm_Technical.Model.CProductRmTechnicalViewModel;
import com.erp.RawMaterial.Product_Rm_Technical.Repository.IProductRmTechnicalRepository;
import com.erp.RawMaterial.Product_Rm_Technical.Repository.IProductRmTechnicalViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class CProductRmTechnicalServiceImpl implements IProductRmTechnicalService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductRmTechnicalRepository iProductRmTechnicalRepository;

	@Autowired
	IProductRmTechnicalViewRepository iProductRmTechnicalViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CProductRmTechnicalModel cProductRmTechnicalModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = cProductRmTechnicalModel.getCompany_id();
		try {
			Optional<CProductRmTechnicalModel> option = iProductRmTechnicalRepository
					.findById(cProductRmTechnicalModel.getProduct_rm_technical_id());
			CProductRmTechnicalModel cProductTechicalModel = null;
			if (option.isPresent()) {
				cProductTechicalModel = option.get();
				Object updateStatusProductTechnical = iProductRmTechnicalRepository
						.updateActiveStatusProductTechnical(cProductTechicalModel.getProduct_rm_technical_id());
				cProductRmTechnicalModel.setProduct_rm_technical_id(0);

				CProductRmTechnicalModel cModel = iProductRmTechnicalRepository.save(cProductRmTechnicalModel);

				String json = objectMapper.writeValueAsString(cModel);

				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated suceesfully!...");
				System.out.println(" Product Rm Technical updated successfully!..");
				return resp;
			} else {
				CProductRmTechnicalModel model = iProductRmTechnicalRepository
						.getCheck(cProductRmTechnicalModel.getProduct_rm_technical_name());
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Technical Name is already exist!");
					return resp;
				} else {

					CProductRmTechnicalModel cModel = iProductRmTechnicalRepository.save(cProductRmTechnicalModel);
					String json = objectMapper.writeValueAsString(cModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added suceesfully!...");

					System.out.println(" Product Rm Technical saved succesfully!..");
					return resp;
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productrmtechnical/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productrmtechnical/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;

		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int product_rm_id) {
		return iProductRmTechnicalRepository.FnDeleteRecord(product_rm_id);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CProductRmTechnicalViewModel> data = iProductRmTechnicalViewRepository.FnShowAllRecords(pageable);
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
			Page<CProductRmTechnicalViewModel> data = iProductRmTechnicalViewRepository
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
	public JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int product_rm_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductRmTechnicalViewModel json = iProductRmTechnicalViewRepository.FnShowParticularRecord(company_id,
					company_branch_id, product_rm_id);
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
	public JSONObject FnShowParticularRecordForUpdate(int company_id, int company_branch_id, int product_rm_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductRmTechnicalModel json = iProductRmTechnicalRepository.FnShowParticularRecordForUpdate(company_id,
					company_branch_id, product_rm_id);
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

}
