package com.erp.RawMaterial.Product_Rm_Process.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.RawMaterial.Product_Rm_Process.Model.CProductRmProcessModel;
import com.erp.RawMaterial.Product_Rm_Process.Model.CProductRmProcessViewModel;
import com.erp.RawMaterial.Product_Rm_Process.Repository.IProductRmProcessRepository;
import com.erp.RawMaterial.Product_Rm_Process.Repository.IProductRmProcessViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CProductRmProcessServiceImpl implements IProductRmProcessService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	private IProductRmProcessRepository iProductRmProcessRepository;

	@Autowired
	private IProductRmProcessViewRepository iProductRmProcessViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject responce = new JSONObject();
		JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
		JSONArray productProcessIds = (JSONArray) jsonObject.get("productProcessIds");
		int company_branch_id = compAndBrnchId.getInt("company_branch_id");
		int company_id = compAndBrnchId.getInt("company_id");
		String product_rm_id = compAndBrnchId.getString("product_rm_id");
		try {

			Object obj = iProductRmProcessRepository.updateProductRMProcessActiveStatus(product_rm_id, company_id);

			List<CProductRmProcessModel> processModel = objectMapper.readValue(productProcessIds.toString(),
					new TypeReference<List<CProductRmProcessModel>>() {
					});
			iProductRmProcessRepository.saveAll(processModel);

			responce.put("success", "1");
			responce.put("message", "Records added successfully !...");
			responce.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/productrmprocess/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", sqlEx.getMessage());
				return responce;

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productrmprocess/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}

	@Override
	public List<CProductRmProcessModel> FnShowParticularRecord(int product_rm_id, int company_id) {
		List<CProductRmProcessModel> cProductRmProcessModels = null;
		try {
			cProductRmProcessModels = iProductRmProcessRepository.FnShowParticularRecords(product_rm_id, company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRmProcessModels;
	}

	@Override
	public List<CProductRmProcessViewModel> FnShowAllActiveRecords(int company_id) {
		List<CProductRmProcessViewModel> cProductRmProcessViewModels = null;
		try {
			cProductRmProcessViewModels = iProductRmProcessViewRepository.FnShowAllActiveRecords(company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRmProcessViewModels;
	}

}
