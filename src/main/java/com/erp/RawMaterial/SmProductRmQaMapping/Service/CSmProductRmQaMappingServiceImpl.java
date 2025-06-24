package com.erp.RawMaterial.SmProductRmQaMapping.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.RawMaterial.SmProductRmQaMapping.Model.CSmProductRmQaMappingModel;
import com.erp.RawMaterial.SmProductRmQaMapping.Model.CSmProductRmQaMappingViewModel;
import com.erp.RawMaterial.SmProductRmQaMapping.Repository.ISmProductRmQaMappingRepository;
import com.erp.RawMaterial.SmProductRmQaMapping.Repository.ISmProductRmQaMappingViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CSmProductRmQaMappingServiceImpl implements ISmProductRmQaMappingService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductRmQaMappingRepository iSmProductRmQaMappingRepository;

	@Autowired
	ISmProductRmQaMappingViewRepository iSmProductRmQaMappingViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject resp = new JSONObject();
		JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
		JSONArray qaParameterIds = (JSONArray) jsonObject.get("qaParameterIds");
		int company_branch_id = compAndBrnchId.getInt("company_branch_id");
		int company_id = compAndBrnchId.getInt("company_id");
		String product_rm_id = compAndBrnchId.getString("product_rm_id");
		try {

			Object obj = iSmProductRmQaMappingRepository.updateProductRMQaMappingActiveStatus(product_rm_id, company_id);

			List<CSmProductRmQaMappingModel> qaMappingModel = objectMapper.readValue(qaParameterIds.toString(),
					new TypeReference<List<CSmProductRmQaMappingModel>>() {
					});
			iSmProductRmQaMappingRepository.saveAll(qaMappingModel);

			resp.put("success", "1");
			resp.put("message", "Records added successfully !...");
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/SmProductRmQaMapping/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
				return resp;

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/SmProductRmQaMapping/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;

		}

		return resp;
	}

	@Override
	public Page<CSmProductRmQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iSmProductRmQaMappingViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Page<CSmProductRmQaMappingModel> FnShowParticularRecord(int product_rm_id, Pageable pageable,
	                                                               int company_id) {
		return iSmProductRmQaMappingRepository.FnShowParticularRecord(product_rm_id, pageable, company_id);
	}

}
