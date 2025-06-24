package com.erp.SmProductPrBomMst.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductPrBomMst.Model.CSmProductPrBomDtlModel;
import com.erp.SmProductPrBomMst.Model.CSmProductPrBomMstModel;
import com.erp.SmProductPrBomMst.Repository.ISmProductPrBomDetailRepository;
import com.erp.SmProductPrBomMst.Repository.ISmProductPrBomMstRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CSmProductPrBomMstServiceImpl implements ISmProductPrBomMstService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductPrBomMstRepository iSmProductPrBomMstRepository;

	@Autowired
	ISmProductPrBomDetailRepository iSmProductPrBomDetailRepository;

//	@Autowired
//	ISmProductPrBomMstViewRepository iSmProductPrBomMstViewRepository;
//
//	@Autowired
//	ISmProductPrBomMstRptRepository iSmProductPrBomMstRptRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject detailObject) {
		Map<String, Object> response = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject json = detailObject.getJSONObject("TransHeaderData");
		JSONArray jsonObject = (JSONArray) detailObject.get("TransFooterData");

		int company_id = json.getInt("company_id");
		int product_pr_bom_version = json.getInt("product_pr_bom_version");

		AtomicInteger bom_version = new AtomicInteger(product_pr_bom_version);

		CSmProductPrBomMstModel cSmProductPrBomMstModel = new CSmProductPrBomMstModel();

		try {
			Optional<CSmProductPrBomMstModel> option = iSmProductPrBomMstRepository
					.findById(json.getInt("product_pr_bom_id"));
			CSmProductPrBomMstModel csmProductPrBomMstModel = null;
			CSmProductPrBomMstModel responseBomMaster = null;
			int product_Pr_bom_version = 0;

			if (option.isPresent()) {
				csmProductPrBomMstModel = option.get();
				product_Pr_bom_version = csmProductPrBomMstModel.getProduct_pr_bom_version();

				csmProductPrBomMstModel.setModified_on(new Date());
				csmProductPrBomMstModel.setIs_delete(true);
				csmProductPrBomMstModel.setDeleted_on(new Date());
				//Delete Previous Bom

				cSmProductPrBomMstModel = iSmProductPrBomMstRepository.save(csmProductPrBomMstModel);

				//Add New Bom
//				CSmProductPrBomMstModel csmProductPrbomMstModel = new CSmProductPrBomMstModel();
				responseBomMaster = objectMapper.readValue(json.toString(), CSmProductPrBomMstModel.class);
				responseBomMaster.setProduct_pr_bom_version(bom_version.get() + 1);

				responseBomMaster = iSmProductPrBomMstRepository.save(responseBomMaster);

				bom_version.set(responseBomMaster.getProduct_pr_bom_version());

				//Save Bom Details
				iSmProductPrBomDetailRepository.updateBomDetailsActiveStatusBomDetail(json.getString("product_pr_bom_no"), company_id);

				List<CSmProductPrBomDtlModel> accessModels = objectMapper.readValue(jsonObject.toString(),
						new TypeReference<List<CSmProductPrBomDtlModel>>() {
						});

				accessModels.forEach(details -> details.setProduct_pr_bom_version(bom_version.get()));
				iSmProductPrBomDetailRepository.saveAll(accessModels);
			} else {
				responseBomMaster = objectMapper.readValue(json.toString(), CSmProductPrBomMstModel.class);

				responseBomMaster.setProduct_pr_bom_version(bom_version.get() + 1);

				responseBomMaster = iSmProductPrBomMstRepository.save(responseBomMaster);

				bom_version.set(responseBomMaster.getProduct_pr_bom_version());

				// Save Bom Details
				iSmProductPrBomDetailRepository.updateBomDetailsActiveStatusBomDetail(json.getString("product_pr_bom_no"), company_id);

				List<CSmProductPrBomDtlModel> cSmProductPrBomDtlModel = objectMapper.readValue(jsonObject.toString(),
						new TypeReference<List<CSmProductPrBomDtlModel>>() {
						});

				cSmProductPrBomDtlModel.forEach(details -> details.setProduct_pr_bom_version(bom_version.get()));
				iSmProductPrBomDetailRepository.saveAll(cSmProductPrBomDtlModel);
			}

			response.put("success", "1");
			response.put("data", responseBomMaster);
			response.put("error", "");
			response.put("message", json.getInt("product_pr_bom_id") == 0 ? "Record added successfully!..." : "Record updated successfully!...");
			System.out.println(" SmProductPrBomMst Updated Successfully!..");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/SmProductPrBomMst/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				response.put("success", "0");
				response.put("data", "");
				response.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", "0");
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductPrBomMst/FnAddUpdateRecord",
					0, e.getMessage());
			response.put("data", "");
			response.put("error", e.getMessage());
		}

		return response;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String product_pr_bom_no, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			CSmProductPrBomMstModel bomMstModel = iSmProductPrBomMstRepository.checkIfExist(product_pr_bom_no);
			if (bomMstModel != null) {
				bomMstModel.setIs_delete(true);
				bomMstModel.setDeleted_on(new Date());
				bomMstModel.setDeleted_by(deleted_by);

				iSmProductPrBomMstRepository.save(bomMstModel);
				iSmProductPrBomMstRepository.updateBomDetailsActiveStatus(product_pr_bom_no, deleted_by);
			}

			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");
			return responce;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}

		return responce;
	}

}
