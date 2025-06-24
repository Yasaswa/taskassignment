package com.erp.RawMaterial.SmProductRmBomMst.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.RawMaterial.SmProductRmBomDtl.Model.CSmProductRmBomDtlModel;
import com.erp.RawMaterial.SmProductRmBomDtl.Repository.ISmProductRmBomDtlRepository;
import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmProductRmBomMstModel;
import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmvProductRmBomDetails;
import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmvProductRmBomListingModel;
import com.erp.RawMaterial.SmProductRmBomMst.Repository.ISmProductRmBomMstRepository;
import com.erp.RawMaterial.SmProductRmBomMst.Repository.ISmvProductRmBomDetailsRepository;
import com.erp.RawMaterial.SmProductRmBomMst.Repository.ISmvProductRmBomListingRepository;
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
public class CSmProductRmBomMstServiceImpl implements ISmProductRmBomMstService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductRmBomMstRepository iSmProductRmBomMstRepository;

	@Autowired
	ISmProductRmBomDtlRepository iSmProductRmBomDtlRepository;

	@Autowired
	ISmvProductRmBomListingRepository iSmvProductRmBomListingRepository;

	@Autowired
	ISmvProductRmBomDetailsRepository iSmvProductRmBomDetailsRepository;

	@Override
	@Transactional
	public Map<String, Object> FnAddUpdateRecord(JSONObject detailObject) {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject json = detailObject.getJSONObject("TransHeaderData");
		JSONArray jsonObject = (JSONArray) detailObject.get("TransDetaildata");

		Map<String, Object> resp = new HashMap<>();
		CSmProductRmBomMstModel cSmProductRmBomMst = new CSmProductRmBomMstModel();
		int company_id = json.getInt("company_id");
		AtomicInteger bom_version = new AtomicInteger(json.getInt("product_rm_bom_version"));
		try {

			Optional<CSmProductRmBomMstModel> option = iSmProductRmBomMstRepository.findById(json.getInt("product_rm_bom_id"));
			CSmProductRmBomMstModel bomModel = null;
			CSmProductRmBomMstModel responseBomMaster = null;
			if (option.isPresent()) {
				bomModel = option.get();
				bomModel.setModified_on(new Date());
				bomModel.setIs_delete(true);
				bomModel.setDeleted_on(new Date());

//				Delete Previous Bom
				iSmProductRmBomMstRepository.save(bomModel);
//				Add New Bom
				responseBomMaster = objectMapper.readValue(json.toString(), CSmProductRmBomMstModel.class);
				responseBomMaster.setProduct_rm_bom_version(bom_version.get() + 1);
				responseBomMaster = iSmProductRmBomMstRepository.save(responseBomMaster);
				bom_version.set(responseBomMaster.getProduct_rm_bom_version());

//				Save Bom Details
				List<CSmProductRmBomDtlModel> accessModels = objectMapper.readValue(jsonObject.toString(),
						new TypeReference<List<CSmProductRmBomDtlModel>>() {
						});
				Object obj = iSmProductRmBomDtlRepository.updateBomDetailsActiveStatusBomDetail(
						accessModels.get(0).getproduct_rm_bom_no(), accessModels.get(0).getcompany_id());

				accessModels.forEach(details -> details.setproduct_rm_bom_version(bom_version.get()));
				iSmProductRmBomDtlRepository.saveAll(accessModels);

			} else {
				responseBomMaster = objectMapper.readValue(json.toString(), CSmProductRmBomMstModel.class);
				responseBomMaster = iSmProductRmBomMstRepository.save(responseBomMaster);
				bom_version.set(responseBomMaster.getProduct_rm_bom_version());

//				Save Bom Details
				List<CSmProductRmBomDtlModel> accessModels = objectMapper.readValue(jsonObject.toString(),
						new TypeReference<List<CSmProductRmBomDtlModel>>() {
						});
				accessModels.forEach(details -> details.setproduct_rm_bom_version(bom_version.get()));
				iSmProductRmBomDtlRepository.saveAll(accessModels);
			}


//				FnAddDetailRecord(jsonObject, cSmProductRmBomMstModel.getProduct_rm_bom_version());

			resp.put("success", "1");
			resp.put("data", responseBomMaster);
			resp.put("error", "");
			resp.put("message", json.getInt("product_rm_bom_id") != 0 ? "Record updated successfully!..." : "Record added successfully!...");
			System.out.println(" SmProductRmBomMst Updated Successfully!..");
			return resp;


		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				e.printStackTrace();
//				add error log
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/SmProductFgBomMst/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

				return resp;
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgBomMst/FnAddUpdateRecord",
					0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;
		}

		return resp;

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<String, Object> FnAddDetailRecord(JSONArray jsonObject, int bomVersion) {
		Map<String, Object> resp = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {


			resp.put("success", "1");
			resp.put("data", "");
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

				return resp;
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
	public Page<CSmvProductRmBomListingModel> FnShowParticularRecords(String product_parent_rm_id, Pageable pageable) {
		return iSmvProductRmBomListingRepository.FnShowParticularRecords(product_parent_rm_id, pageable);
	}

	@Override
	public List<CSmvProductRmBomListingModel> FnShowAllActiveRecordsToExport(String product_parent_rm_id) {
		return iSmvProductRmBomListingRepository.FnShowAllActiveRecordsToExport(product_parent_rm_id);
	}

	@Override
	public Page<CSmvProductRmBomDetails> FnShowParticularRecordForUpdate(int product_rm_bom_id, Pageable pageable) {
		Page<CSmvProductRmBomDetails> cSmProductRmBomMstModel = null;
		try {
			cSmProductRmBomMstModel = iSmvProductRmBomDetailsRepository
					.FnShowParticularRecordForUpdate(product_rm_bom_id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductRmBomMstModel;
	}

	@Override
	public JSONObject FnDeleteRecord(String product_rm_bom_no, String deleted_by, int company_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			CSmProductRmBomMstModel bomMstModel = iSmProductRmBomMstRepository.checkIfExist(product_rm_bom_no);
			if (bomMstModel != null) {
				bomMstModel.setIs_delete(true);
				bomMstModel.setDeleted_on(new Date());
				bomMstModel.setDeleted_by(deleted_by);
				iSmProductRmBomMstRepository.save(bomMstModel);

				iSmProductRmBomDtlRepository.updateBomDetailsActiveStatus(product_rm_bom_no, deleted_by, company_id);
			}

			resp.put("success", "1");
			resp.put("data", "");
			resp.put("error", "");
			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;
		}

		return resp;
	}

}
