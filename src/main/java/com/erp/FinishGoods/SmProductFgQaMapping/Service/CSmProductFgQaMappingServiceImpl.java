package com.erp.FinishGoods.SmProductFgQaMapping.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FinishGoods.SmProductFgQaMapping.Model.CSmProductFgQaMappingModel;
import com.erp.FinishGoods.SmProductFgQaMapping.Model.CSmProductFgQaMappingViewModel;
import com.erp.FinishGoods.SmProductFgQaMapping.Repository.ISmProductFgQaMappingRepository;
import com.erp.FinishGoods.SmProductFgQaMapping.Repository.ISmProductFgQaMappingViewRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CSmProductFgQaMappingServiceImpl implements ISmProductFgQaMappingService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductFgQaMappingRepository iSmProductFgQaMappingRepository;

	@Autowired
	ISmProductFgQaMappingViewRepository iSmProductFgQaMappingViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		CSmProductFgQaMappingModel cProductFgQaMappingModel = new CSmProductFgQaMappingModel();
		JSONObject resp = new JSONObject();
		int company_branch_id = 0;
		int company_id = 0;
		String product_fg_id = "0";
		try {
			JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
			JSONObject qaParameterIds = (JSONObject) jsonObject.get("qaParameterIds");

			if (!compAndBrnchId.keySet().isEmpty()) {
				for (String currentKey : compAndBrnchId.keySet()) {
					Object key = compAndBrnchId.get(currentKey);
					String value = key.toString();
					if (currentKey.equals("company_branch_id")) {
						company_branch_id = Integer.parseInt(value);
					} else if (currentKey.equals("company_id")) {
						company_id = Integer.parseInt(value);
					} else if (currentKey.equals("product_fg_id")) {
						product_fg_id = value;
					}
				}
			}

			Object obj = iSmProductFgQaMappingRepository.updateProductRMQaMappingActiveStatus(product_fg_id, company_id, company_branch_id);

			if (!qaParameterIds.keySet().isEmpty()) {
				for (String currentKey : qaParameterIds.keySet()) {
					CSmProductFgQaMappingModel qaMappingModel = new CSmProductFgQaMappingModel();
					Object key = qaParameterIds.get(currentKey);
					String product_qa_parameters_id = key.toString();

					qaMappingModel.setcompany_id(company_id);
					qaMappingModel.setcompany_branch_id(company_branch_id);
					qaMappingModel.setproduct_qa_parameters_id(Integer.parseInt(product_qa_parameters_id));
					qaMappingModel.setproduct_fg_id(product_fg_id);

					cProductFgQaMappingModel = iSmProductFgQaMappingRepository.save(qaMappingModel);
				}

			}
			resp.put("success", "1");
			resp.put("message", "Records added successfully !...");
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgQaMapping/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
				return resp;

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgQaMapping/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;

		}

		return resp;

	}


	@Override
	public Page<CSmProductFgQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable) {
		return iSmProductFgQaMappingViewRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public Page<CSmProductFgQaMappingModel> FnShowParticularRecord(int product_fg_id,
	                                                               Pageable pageable) {
		return iSmProductFgQaMappingRepository.FnShowParticularRecord(product_fg_id, pageable);
	}

}
