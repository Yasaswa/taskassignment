package com.erp.SmProductSrCustomer.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductSrCustomer.Model.CSmProductSrCustomerModel;
import com.erp.SmProductSrCustomer.Model.CSmProductSrCustomerViewModel;
import com.erp.SmProductSrCustomer.Repository.ISmProductSrCustomerRepository;
import com.erp.SmProductSrCustomer.Repository.ISmProductSrCustomerViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CSmProductSrCustomerServiceImpl implements ISmProductSrCustomerService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductSrCustomerRepository iSmProductSrCustomerRepository;

	@Autowired
	ISmProductSrCustomerViewRepository iSmProductSrCustomerViewRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		String product_sr_id = commonIdsObj.getString("product_sr_id");
		JSONArray array = (JSONArray) jsonObject.get("TransDetailData");

		try {
			int custModel = iSmProductSrCustomerRepository.updateStatus(product_sr_id, company_branch_id, company_id);
			ObjectMapper objectMapper = new ObjectMapper();
			List<CSmProductSrCustomerModel> accessModels = objectMapper.readValue(array.toString(),
					new TypeReference<List<CSmProductSrCustomerModel>>() {
					});

			iSmProductSrCustomerRepository.saveAll(accessModels);

			responce.put("success", "1");
			responce.put("data", "");
			responce.put("message", "Records added successfully!...");
			responce.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/SmProductSrCustomer/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/SmProductSrCustomer/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Page<CSmProductSrCustomerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iSmProductSrCustomerViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Page<CSmProductSrCustomerViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable,
	                                                                  int company_id) {
		return iSmProductSrCustomerViewRepository.FnShowParticularRecord(product_sr_id, pageable, company_id);
	}

}
