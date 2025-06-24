package com.erp.FinishGoods.SmProductFgBomMst.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FinishGoods.SmProductFgBomMst.Model.CSmProductFgBomDtlModel;
import com.erp.FinishGoods.SmProductFgBomMst.Model.CSmProductFgBomMstModel;
import com.erp.FinishGoods.SmProductFgBomMst.Model.CSmvProductFgBomListingModel;
import com.erp.FinishGoods.SmProductFgBomMst.Repository.ISmProductFgBomDtlRepository;
import com.erp.FinishGoods.SmProductFgBomMst.Repository.ISmProductFgBomMstRepository;
import com.erp.FinishGoods.SmProductFgBomMst.Repository.ISmvProductFgBomDetailsRepository;
import com.erp.FinishGoods.SmProductFgBomMst.Repository.ISmvProductFgBomListingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CSmProductFgBomMstServiceImpl implements ISmProductFgBomMstService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductFgBomMstRepository iSmProductFgBomMstRepository;

	@Autowired
	ISmProductFgBomDtlRepository iSmProductFgBomDtlRepository;

	@Autowired
	ISmvProductFgBomListingRepository iSmvProductFgBomListingRepository;

	@Autowired
	ISmvProductFgBomDetailsRepository iSmvProductFgBomDetailsRepository;


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> FnAddUpdateRecord(JSONObject detailObject) {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject json = detailObject.getJSONObject("TransHeaderData");
		JSONArray jsonObject = (JSONArray) detailObject.get("TransFooterData");

		Map<String, Object> response = new HashMap<>();

		CSmProductFgBomMstModel cSmProductFgBomMst = new CSmProductFgBomMstModel();
		int company_id = json.getInt("company_id");
		AtomicInteger bom_version = new AtomicInteger(json.getInt("product_fg_bom_version"));

		try {
			Optional<CSmProductFgBomMstModel> option = iSmProductFgBomMstRepository
					.findById(json.getInt("product_fg_bom_id"));
			CSmProductFgBomMstModel bomModel = null;
			CSmProductFgBomMstModel responseBomMaster = null;
//			int product_Fg_bom_version = 0;

			if (option.isPresent()) {
				bomModel = option.get();

//				product_Fg_bom_version = bomModel.getProduct_fg_bom_version();

				bomModel.setModified_on(new Date());
				bomModel.setIs_delete(true);
				bomModel.setDeleted_on(new Date());

//				Delete Previous Bom
				iSmProductFgBomMstRepository.save(bomModel);

//				Add New Bom
				CSmProductFgBomMstModel cSmProductFgBomMstModel = new CSmProductFgBomMstModel();
				responseBomMaster = objectMapper.readValue(json.toString(), CSmProductFgBomMstModel.class);
				responseBomMaster.setProduct_fg_bom_id(0);
				responseBomMaster.setProduct_fg_bom_version(bom_version.get() + 1);

				responseBomMaster = iSmProductFgBomMstRepository.save(responseBomMaster);

				bom_version.set(responseBomMaster.getProduct_fg_bom_version());

//				Save Bom Details
				iSmProductFgBomDtlRepository.updateBomDetailsActiveStatusBomDetail(json.getString("product_fg_bom_no"), company_id);

				List<CSmProductFgBomDtlModel> accessModels = objectMapper.readValue(jsonObject.toString(),
						new TypeReference<List<CSmProductFgBomDtlModel>>() {
						});

				accessModels.forEach(details -> details.setProduct_fg_bom_version(bom_version.get()));
				iSmProductFgBomDtlRepository.saveAll(accessModels);
//				FnAddDetailRecord(jsonObject, cSmProductFgBomMstModel.getproduct_fg_bom_version());


			} else {
				responseBomMaster = objectMapper.readValue(json.toString(), CSmProductFgBomMstModel.class);
				responseBomMaster.setProduct_fg_bom_version(bom_version.get());

				responseBomMaster = iSmProductFgBomMstRepository.save(responseBomMaster);

				bom_version.set(responseBomMaster.getProduct_fg_bom_version());

				//				Save Bom Details
				iSmProductFgBomDtlRepository.updateBomDetailsActiveStatusBomDetail(json.getString("product_fg_bom_no"), company_id);

				List<CSmProductFgBomDtlModel> accessModels = objectMapper.readValue(jsonObject.toString(),
						new TypeReference<List<CSmProductFgBomDtlModel>>() {
						});

				accessModels.forEach(details -> details.setProduct_fg_bom_version(bom_version.get()));
				iSmProductFgBomDtlRepository.saveAll(accessModels);
			}

			response.put("success", "1");
			response.put("data", responseBomMaster);
			response.put("error", "");
			response.put("message", json.getInt("product_fg_bom_id") != 0 ? "Record updated successfully!..." : "Record added successfully!...");

			System.out.println(" SmProductRmBomMst Updated Successfully!..");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/SmProductFgBomMst/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				response.put("success", "0");
				response.put("data", "");
				response.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", "0");
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgBomMst/FnAddUpdateRecord",
					0, e.getMessage());
			response.put("data", "");
			response.put("error", e.getMessage());
		}

		return response;

	}

	public Map<String, Object> FnAddDetailRecord(JSONObject jsonObject, int bomVersion) {
		Map<String, Object> resp = new HashMap<>();
		try {
			JSONObject headerDetails = (JSONObject) jsonObject.get(Integer.toString(0));

			Object obj = iSmProductFgBomDtlRepository.updateBomDetailsActiveStatusBomDetail(
					headerDetails.getString("product_fg_bom_no"), headerDetails.getInt("company_id"));

			for (int jsonIndex = 0; jsonIndex < jsonObject.length(); jsonIndex++) {
				CSmProductFgBomDtlModel bomDtlModel = new CSmProductFgBomDtlModel();
				JSONObject data = (JSONObject) jsonObject.get(Integer.toString(jsonIndex));
				bomDtlModel.setCompany_id(data.getInt("company_id"));
				bomDtlModel.setCompany_branch_id(data.getInt("company_branch_id"));
				bomDtlModel.setProduct_fg_bom_no(data.getString("product_fg_bom_no"));
				bomDtlModel.setProduct_child_rm_id(data.getString("product_child_rm_id"));
				bomDtlModel.setProduct_child_rm_quantity(data.getDouble("product_child_rm_quantity"));
				bomDtlModel.setProduct_child_rm_drawing_no(data.getString("product_child_rm_drawing_no"));
				bomDtlModel.setProduct_child_rm_tech_spect(data.getString("product_child_rm_tech_spect"));
				bomDtlModel.setProduct_child_rm_weight(data.getDouble("product_child_rm_weight"));
				bomDtlModel.setProduct_child_rm_unit_id(data.getInt("product_child_rm_unit_id"));
				bomDtlModel.setProduct_fg_bom_version(bomVersion);
				bomDtlModel.setCreated_on(new Date());

				iSmProductFgBomDtlRepository.save(bomDtlModel);
				System.out.println("Detail records inserted!...");
			}

			resp.put("success", "1");
			resp.put("data", "");
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}
		return resp;

	}

	@Override
	public Page<CSmvProductFgBomListingModel> FnShowParticularRecords(String product_parent_Fg_id, Pageable pageable) {
		return iSmvProductFgBomListingRepository.FnShowParticularRecords(product_parent_Fg_id, pageable);
	}

	@Override
	public List<CSmvProductFgBomListingModel> FnShowAllActiveRecordsToExport(String product_parent_Fg_id) {
		return iSmvProductFgBomListingRepository.FnShowAllActiveRecordsToExport(product_parent_Fg_id);
	}

	@Override
	public List<Map<String, Object>> FnShowParticularRecordForUpdate(int product_fg_bom_id, Pageable pageable) {
		List<Map<String, Object>> cSmProductFgBomMstModel = null;
		try {
			cSmProductFgBomMstModel = iSmvProductFgBomDetailsRepository
					.FnShowParticularRecordForUpdate(product_fg_bom_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductFgBomMstModel;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String productFgBomNo, String deleted_by, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			CSmProductFgBomMstModel bomMstModel = iSmProductFgBomMstRepository.checkIfExist(productFgBomNo);
			if (bomMstModel != null) {
				bomMstModel.setIs_delete(true);
				bomMstModel.setDeleted_on(new Date());
				bomMstModel.setDeleted_by(deleted_by);

				iSmProductFgBomMstRepository.save(bomMstModel);

				iSmProductFgBomDtlRepository.updateBomDetailsActiveStatus(productFgBomNo, deleted_by, company_id);
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

	@Override
	public List<Map<String, Object>> FnShowProductFgBomMstRecord(String product_parent_fg_id,
	                                                             int company_id) {
		List<Map<String, Object>> cSmvProductFgBomDetails = null;
		try {
			cSmvProductFgBomDetails = iSmvProductFgBomDetailsRepository.FnShowProductFgBomSummaryRecord(product_parent_fg_id, company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmvProductFgBomDetails;
	}


}
