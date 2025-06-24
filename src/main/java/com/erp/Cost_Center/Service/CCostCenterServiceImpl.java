package com.erp.Cost_Center.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Cost_Center.Model.CCostCenterModel;
import com.erp.Cost_Center.Model.CCostCenterViewModel;
import com.erp.Cost_Center.Repository.ICostCenterRepository;
import com.erp.Cost_Center.Repository.ICostCenterViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CCostCenterServiceImpl implements ICostCenterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICostCenterRepository iCostCenterRepository;

	@Autowired
	ICostCenterViewRepository iCostCenterViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CCostCenterModel cCostCenterModel) {
		Map<String, Object> resp = new HashMap<>();
//		CCostCenterModel MyModel = null;
		int company_id = cCostCenterModel.getCompany_id();
		try {
//			Optional<CCostCenterModel> option = iCostCenterRepository.findById(cCostCenterModel.getCost_center_id());
//
//			if (option.isPresent()) {
//				CCostCenterModel mymodel = iCostCenterRepository.save(cCostCenterModel);
//				resp.put("success", "1");
//				resp.put("data", mymodel);
//				resp.put("error", "");
//				resp.put("message", "Record updated succesfully!...");
//
//			}
//			Check similar Cost Center Name or short name is exist or not
			CCostCenterModel resultsCostCenterName = null;
			if (cCostCenterModel.getCost_center_short_name() == null
					|| cCostCenterModel.getCost_center_short_name().isEmpty()) {

				resultsCostCenterName = iCostCenterRepository.getCheck(cCostCenterModel.getCost_center_name(),
						null, cCostCenterModel.getCompany_id(), cCostCenterModel.getCost_center_group());
			} else {
				resultsCostCenterName = iCostCenterRepository.getCheck(cCostCenterModel.getCost_center_name(),
						cCostCenterModel.getCost_center_short_name(), cCostCenterModel.getCompany_id(), cCostCenterModel.getCost_center_group());
			}if (resultsCostCenterName != null) {
				resp.put("success", "0");
				resp.put("error", "Cost Center Name or Short Name is already exist!");
				return resp;
			} else {
				CCostCenterModel agentModel = iCostCenterRepository.save(cCostCenterModel);
				resp.put("success", "1");
				resp.put("data", agentModel);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");

				System.out.println(" Cost Center saved succesfully!..");
				return resp;
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/costcenter/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/costcenter/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int cost_center_id, int company_id, String deleted_by) {
		return iCostCenterRepository.FnDeleteRecord(cost_center_id, company_id, deleted_by);
	}

	@Override
	public Map<String, Object> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CCostCenterViewModel> data = iCostCenterViewRepository.FnShowAllActiveRecords(pageable, company_id);
			resp.put("data", data);
			resp.put("success", "1");
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return resp;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int cost_center_id, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {

			CCostCenterViewModel json = iCostCenterViewRepository.FnShowParticularRecordForUpdate(cost_center_id,
					company_id);
			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public Page<Map<String, Object>> FnShowReportRecords(Pageable pageable, int company_id) {
		return iCostCenterViewRepository.FnShowReportRecords(pageable, company_id);
	}

//	@Override
//	public Page FnShowReportRecords(Pageable pageable) {		
//		return iCostcenterRptRepository.FnShowReportRecords(pageable);
//	}

}
