package com.erp.FmProfitCenter.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FmProfitCenter.Model.CFmProfitCenterModel;
import com.erp.FmProfitCenter.Model.CFmProfitCenterViewModel;
import com.erp.FmProfitCenter.Repository.IFmProfitCenterRepository;
import com.erp.FmProfitCenter.Repository.IFmProfitCenterViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class CFmProfitCenterServiceImpl implements IFmProfitCenterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IFmProfitCenterRepository iFmProfitCenterRepository;

	@Autowired
	IFmProfitCenterViewRepository iFmProfitCenterViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CFmProfitCenterModel cFmProfitCenterModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		
		Optional<CFmProfitCenterModel> option = iFmProfitCenterRepository.findById(cFmProfitCenterModel.getProfit_center_id());
		
		int company_id = cFmProfitCenterModel.getCompany_id();
		try {
			if (option.isPresent()) {
				CFmProfitCenterModel cFmProfitCenter = iFmProfitCenterRepository.save(cFmProfitCenterModel);
				String json = objectMapper.writeValueAsString(cFmProfitCenter);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated successfully!...");
				System.out.println("Profit Center Updated Successfully!..");
				return resp;
			}

//			Check similar Profit Center Name Name or short name is exist or not
			CFmProfitCenterModel resultsProfitCenterName = null;

			if (cFmProfitCenterModel.getProfit_center_short_name() == null || cFmProfitCenterModel.getProfit_center_short_name().isEmpty()) {

				resultsProfitCenterName = iFmProfitCenterRepository.getCheck(cFmProfitCenterModel.getProfit_center_name(), null, cFmProfitCenterModel.getCompany_id());
			
			} else {
				resultsProfitCenterName = iFmProfitCenterRepository.getCheck(cFmProfitCenterModel.getProfit_center_name(),
						cFmProfitCenterModel.getProfit_center_short_name(), cFmProfitCenterModel.getCompany_id());
			}

			if (resultsProfitCenterName != null) {
				resp.put("success", 0);
				resp.put("error", "Profit Center Name or Short Name is already exist!");
				return resp;
			} else {

				CFmProfitCenterModel json = iFmProfitCenterRepository.save(cFmProfitCenterModel);
				String json1 = objectMapper.writeValueAsString(json);
				resp.put("success", "1");
				resp.put("data", json1);
				resp.put("error", "");
				resp.put("message", "Record added successfully!...");

				System.out.println("Profit Center saved succesfully!..");
				return resp;
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/FmProfitCenter/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/FmProfitCenter/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int profit_center_id, int company_id, String deleted_by) {
		Optional<CFmProfitCenterModel> option = iFmProfitCenterRepository.findById(profit_center_id);
		CFmProfitCenterModel cFmProfitCenterModel = new CFmProfitCenterModel();
		if (option.isPresent()) {
			cFmProfitCenterModel = option.get();
			cFmProfitCenterModel.setIs_delete(true);
			cFmProfitCenterModel.setDeleted_on(new Date());
			cFmProfitCenterModel.setDeleted_by(deleted_by);
			cFmProfitCenterModel.setIs_active(false);

			iFmProfitCenterRepository.save(cFmProfitCenterModel);

		}
		return cFmProfitCenterModel;
	}

	@Override
	public Page<CFmProfitCenterViewModel> FnShowAllActiveRecords(Pageable pageable) {
		return iFmProfitCenterViewRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public CFmProfitCenterModel FnShowParticularRecordForUpdate(int profit_center_id, int company_id) {
		CFmProfitCenterModel cFmProfitCenterModel = null;
		try {
			cFmProfitCenterModel = iFmProfitCenterRepository.FnShowParticularRecordForUpdate(profit_center_id,
					company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cFmProfitCenterModel;
	}

	@Override
	public Page<CFmProfitCenterViewModel> FnShowParticularRecord(int profit_center_id, Pageable pageable) {
		return iFmProfitCenterViewRepository.FnShowParticularRecord(profit_center_id, pageable);
	}

	@Override
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iFmProfitCenterViewRepository.FnShowAllReportRecords(pageable, company_id);
	}

//	@Override
//	public Page<CFmProfitCenterRptModel> FnShowAllReportRecords(Pageable pageable, int company_id) {
//		// TODO Auto-generated method stub
//		return centerRptRepository.FnShowAllReportRecords(pageable,  company_id);
//	}

}
