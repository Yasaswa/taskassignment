package com.erp.RawMaterial.Product_Rm_Commercial.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.RawMaterial.Product_Rm_Commercial.Model.CProductRmCommercialModel;
import com.erp.RawMaterial.Product_Rm_Commercial.Repository.IProductRmCommercialRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class CProductRmCommercialServiceImpl implements IProductRmCommercialService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	private IProductRmCommercialRepository iProductRmCommercialRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CProductRmCommercialModel cProductRmCommercialModel) {
		JSONObject responce = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = cProductRmCommercialModel.getCompany_id();
		try {
			Optional<CProductRmCommercialModel> option = iProductRmCommercialRepository
					.findById(cProductRmCommercialModel.getProduct_rm_commercial_id());
			CProductRmCommercialModel cProductCommercialModel = null;

			if (option.isPresent()) {
				cProductCommercialModel = option.get();
				Object updateStatusProductCommercial = iProductRmCommercialRepository
						.updateActiveStatusProductCommercial(cProductCommercialModel.getProduct_rm_commercial_id());
				cProductRmCommercialModel.setProduct_rm_commercial_id(0);

				CProductRmCommercialModel cModel = iProductRmCommercialRepository.save(cProductRmCommercialModel);

				String json = objectMapper.writeValueAsString(cModel);

				responce.put("success", "1");
				responce.put("data", json);
				responce.put("error", "");
				responce.put("message", "Record updated suceesfully!...");

			} else {
				CProductRmCommercialModel cModel = iProductRmCommercialRepository.save(cProductRmCommercialModel);
				String json = objectMapper.writeValueAsString(cModel);

				responce.put("success", "1");
				responce.put("data", json);
				responce.put("error", "");
				responce.put("message", "Record added suceesfully!...");

			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/productrmcommercial/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/productrmcommercial/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;

	}

	@Override
	public JSONObject FnShowParticularRecordForUpdate(int company_id, int company_branch_id, String product_rm_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CProductRmCommercialModel json = iProductRmCommercialRepository.FnShowParticularRecordForUpdate(company_id,
					company_branch_id, product_rm_id);
			String json1 = objectMapper.writeValueAsString(json);

			resp.put("success", "1");
			resp.put("data", json1);
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			e.printStackTrace();
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
