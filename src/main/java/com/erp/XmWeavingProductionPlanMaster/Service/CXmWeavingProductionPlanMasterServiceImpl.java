package com.erp.XmWeavingProductionPlanMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionPlanMasterModel;
import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionPlanSummaryViewModel;
import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionSpecSheetDetailsModel;
import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionSpecSheetDetailsViewModel;
import com.erp.XmWeavingProductionPlanMaster.Repository.IXmWeavingProductionPlanMasterRepository;
import com.erp.XmWeavingProductionPlanMaster.Repository.IXmWeavingProductionPlanSummaryViewRepository;
import com.erp.XmWeavingProductionPlanMaster.Repository.IXmWeavingProductionSpecSheetDetailsRepository;
import com.erp.XmWeavingProductionPlanMaster.Repository.IXmWeavingProductionSpecSheetDetailsViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CXmWeavingProductionPlanMasterServiceImpl implements IXmWeavingProductionPlanMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmWeavingProductionPlanMasterRepository iXmWeavingProductionPlanMasterRepository;

	@Autowired
	IXmWeavingProductionPlanSummaryViewRepository iXmWeavingProductionPlanSummaryViewRepository;

	@Autowired
	IXmWeavingProductionSpecSheetDetailsRepository iXmWeavingProductionSpecSheetDetailsRepository;


	@Autowired
	IXmWeavingProductionSpecSheetDetailsViewRepository iXmWeavingProductionSpecSheetDetailsViewRepository;

	@Override
	@Transactional
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();

		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject WeavingProdSpecSheetMaster = jsonObject.getJSONObject("WeavingProdSpecSheetMaster");

		JSONArray WeavingProdSpecSheetDetails = (JSONArray) jsonObject.get("WeavingProdSpecSheetDetails");

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");

		int company_id = commonIdsObj.getInt("company_id");

		int weaving_production_plan_master_id = commonIdsObj.getInt("weaving_production_plan_master_id");

		AtomicInteger weaving_production_plan_master_id_atomic = new AtomicInteger(weaving_production_plan_master_id);

		try {

			CXmWeavingProductionPlanMasterModel wvProductionSpecSheetMasterModel = objectMapper
					.readValue(WeavingProdSpecSheetMaster.toString(), CXmWeavingProductionPlanMasterModel.class);

			CXmWeavingProductionPlanMasterModel respWeavingProductionPlanMaster = iXmWeavingProductionPlanMasterRepository.save(wvProductionSpecSheetMasterModel);

			List<CXmWeavingProductionSpecSheetDetailsModel> cXtWeavingProductionSpecSheetDetailsModel = objectMapper.readValue(WeavingProdSpecSheetDetails.toString(),
					new TypeReference<List<CXmWeavingProductionSpecSheetDetailsModel>>() {
					});

			cXtWeavingProductionSpecSheetDetailsModel.forEach(item -> {
				item.setWeaving_production_plan_master_id(respWeavingProductionPlanMaster.getWeaving_production_plan_master_id());
			});

			iXmWeavingProductionSpecSheetDetailsRepository.saveAll(cXtWeavingProductionSpecSheetDetailsModel);

			responce.put("success", 1);
			responce.put("data", respWeavingProductionPlanMaster);
			responce.put("error", "");
			responce.put("message", weaving_production_plan_master_id_atomic.get() == 0 ? "Record added successfully!..." : "Record updated successfully!...");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmWeavingProductionPlanMaster/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmWeavingProductionPlanMaster/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int weaving_production_plan_master_id, int company_id,
	                                          String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			//Delete Weaving Production Plan Master Records
			iXmWeavingProductionPlanMasterRepository.FnDeleteWVProductionSpecSheetMasterRecord(weaving_production_plan_master_id, company_id,
					deleted_by);

			//Delete Weaving Production Plan Details Records
			iXmWeavingProductionSpecSheetDetailsRepository.FnDeleteWVProductionSpecSheetDetailsRecord(weaving_production_plan_master_id, company_id,
					deleted_by);

			responce.put("success", 1);
			responce.put("data", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_plan_master_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();

		try {

			//fetch Weaving Production Plan Summary Records
			CXmWeavingProductionPlanSummaryViewModel weavingProductionPlanSummaryRecords =
					iXmWeavingProductionPlanSummaryViewRepository.FnShowParticularRecordForUpdate(weaving_production_plan_master_id, company_id);

			//fetch Weaving Production Plan Details Records
			List<CXmWeavingProductionSpecSheetDetailsViewModel> weavingProductionSpecSheetDetailsRecord =
					iXmWeavingProductionSpecSheetDetailsViewRepository.FnShowWeavingProductionSpecSheetDetailRecord(weaving_production_plan_master_id, company_id);

			responce.put("success", 1);
			responce.put("WeavingProductionPlanSummaryRecords", weavingProductionPlanSummaryRecords);
			responce.put("WeavingProductionSpecSheetDetailsRecord", weavingProductionSpecSheetDetailsRecord);

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}


}
