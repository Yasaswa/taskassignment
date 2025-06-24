package com.erp.FmCostCenterHeads.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FmCostCenterHeads.Model.CFmCostCenterHeadsModel;
import com.erp.FmCostCenterHeads.Model.CFmCostCenterHeadsViewModel;
import com.erp.FmCostCenterHeads.Repository.IFmCostCenterHeadsRepository;
import com.erp.FmCostCenterHeads.Repository.IFmCostCenterHeadsViewRepository;

@Service
public class CFmCostCenterHeadsServiceImpl implements IFmCostCenterHeadsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IFmCostCenterHeadsRepository iFmCostCenterHeadsRepository;
	
	@Autowired
	IFmCostCenterHeadsViewRepository iFmCostCenterHeadsViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CFmCostCenterHeadsModel costCenterHeadRequest) {
		Map<String, Object> response = new HashMap<>();

		int company_id = costCenterHeadRequest.getCompany_id();

		AtomicInteger const_center_heads_id = new AtomicInteger(costCenterHeadRequest.getCost_center_heads_id());

		try {
			if (const_center_heads_id.get() == 0) {
				CFmCostCenterHeadsModel checkIsCostCenterHeadExist = iFmCostCenterHeadsRepository
						.checkIsCostCenterHeadExist(costCenterHeadRequest.getCost_center_heads_name(), costCenterHeadRequest.getCost_center_id());
				if (checkIsCostCenterHeadExist != null) {
					response.put("success", 0);
					response.put("data", "");
					response.put("error", "Cost center head is already exist!...");
					return response;
				}
			}

			CFmCostCenterHeadsModel respContent = iFmCostCenterHeadsRepository.save(costCenterHeadRequest);

			response.put("success", 1);
			response.put("data", respContent);
			response.put("error", "");
			response.put("message", const_center_heads_id.get() == 0 ? "Record added successfully!..." : "Record updated successfully!...");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/FmCostCenterHeads/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				response.put("success", "0");
				response.put("data", "");
				response.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/FmCostCenterHeads/FnAddUpdateRecord", 0,
					e.getMessage());
			response.put("success", "0");
			response.put("data", "");
			response.put("error", e.getMessage());

		}

		return response;

	}


	@Override
	public Map<String, Object> FnDeleteRecord(int cost_center_heads_id,int company_id, String deleted_by) {
		Map<String, Object> response = new HashMap<>();
		try {
			
			iFmCostCenterHeadsRepository.FnDeleteCostCenterHead(cost_center_heads_id,company_id,deleted_by);

			response.put("success", 1);
			response.put("data", "");
			response.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());
		}
		return response;
	}


	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int cost_center_heads_id) {
		Map<String, Object> response = new HashMap<>();
		try {
			CFmCostCenterHeadsViewModel getCostCenterHeadRecord = iFmCostCenterHeadsViewRepository.FnShowParticularRecordForUpdate(cost_center_heads_id);
			response.put("success", 1);
			response.put("data", getCostCenterHeadRecord);
			response.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				response.put("success", 0);
				response.put("data", "");
				response.put("error", e.getMessage());
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());
			return response;
		}
		return response;
	}


}
