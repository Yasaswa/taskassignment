package com.erp.SmProductSrProcess.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductSrProcess.Model.CSmProductSrProcessModel;
import com.erp.SmProductSrProcess.Model.CSmProductSrProcessViewModel;
import com.erp.SmProductSrProcess.Repository.ISmProductSrProcessRepository;
import com.erp.SmProductSrProcess.Repository.ISmProductSrProcessViewRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CSmProductSrProcessServiceImpl implements ISmProductSrProcessService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductSrProcessRepository iSmProductSrProcessRepository;

	@Autowired
	ISmProductSrProcessViewRepository iSmProductSrProcessViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		{
			Map<String, Object> responce = new HashMap<>();

			JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
			int company_id = commonIdsObj.getInt("company_id");
			int company_branch_id = commonIdsObj.getInt("company_branch_id");
			String product_sr_id = commonIdsObj.getString("product_sr_id");
			JSONArray array = (JSONArray) jsonObject.get("SrServiceData");
			try {
				int custModel = iSmProductSrProcessRepository.updateStatus(product_sr_id, company_branch_id,
						company_id);
				ObjectMapper objectMapper = new ObjectMapper();
				List<CSmProductSrProcessModel> accessModels = objectMapper.readValue(array.toString(),
						new TypeReference<List<CSmProductSrProcessModel>>() {
						});

				iSmProductSrProcessRepository.saveAll(accessModels);

				responce.put("success", "1");
				responce.put("data", "");

				responce.put("message", "Record updated successfully!...");
				// System.out.println(" SmProductSrProcess Updated Successfully!..");
				responce.put("error", "");

			} catch (DataAccessException e) {
				if (e.getRootCause() instanceof SQLException) {
					SQLException sqlEx = (SQLException) e.getRootCause();
					amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
							"/api/SmProductSrProcess/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
					System.out.println(sqlEx.getMessage());
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", e.getMessage());
				}

			} catch (Exception e) {
				e.printStackTrace();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/SmProductSrProcess/FnAddUpdateRecord", 0, e.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());

			}

			return responce;

		}
	}

	@Override
	public Page<CSmProductSrProcessViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iSmProductSrProcessViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Page<CSmProductSrProcessViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable,
	                                                                 int company_id) {
		return iSmProductSrProcessViewRepository.FnShowParticularRecord(product_sr_id, pageable, company_id);
	}

}
