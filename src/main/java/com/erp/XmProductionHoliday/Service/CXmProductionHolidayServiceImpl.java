package com.erp.XmProductionHoliday.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HmEarningHeads.Model.CHmEarningHeadsModel;
import com.erp.XmProductionHoliday.Model.CXmProductionHolidayModel;
import com.erp.XmProductionHoliday.Model.CXmProductionHolidayViewModel;
import com.erp.XmProductionHoliday.Repository.IXmProductionHolidayRepository;
import com.erp.XmProductionHoliday.Repository.IXmProductionHolidayViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CXmProductionHolidayServiceImpl implements IXmProductionHolidayService {

	@Autowired
	IXmProductionHolidayRepository ihmProductionHolidayRepository;

	@Autowired
	IXmProductionHolidayViewRepository ihmProductionHolidayViewRepository;

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CXmProductionHolidayModel xmProductionHolidayModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CXmProductionHolidayModel> option = ihmProductionHolidayRepository.findById(xmProductionHolidayModel.getProduction_holiday_id());
		CHmEarningHeadsModel MyModel = null;
		int company_id = xmProductionHolidayModel.getCompany_id();
		try {
			if (option.isPresent()) {
				xmProductionHolidayModel.setModified_on(new Date());
				CXmProductionHolidayModel cxmProductionHolidayModel = ihmProductionHolidayRepository.save(xmProductionHolidayModel);
				responce.put("success", "1");
				responce.put("data", cxmProductionHolidayModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" Production Holiday Updated Successfully!..");
			} else {
				CXmProductionHolidayModel model = ihmProductionHolidayRepository
						.checkIfNameExist(xmProductionHolidayModel.getProduction_holiday_name(), company_id);

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " Production Holiday Name  is already exist!");

					return responce;

				} else {

					CXmProductionHolidayModel respContent = ihmProductionHolidayRepository.save(xmProductionHolidayModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmProductionHoliday/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmProductionHoliday/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}

	@Override
	public Object FnDeleteRecord(int production_holiday_id, String deleted_by, int company_id) {
		Optional<CXmProductionHolidayModel> option = ihmProductionHolidayRepository.findById(production_holiday_id);
		CXmProductionHolidayModel cxmProductionHolidayModel = new CXmProductionHolidayModel();
		if (option.isPresent()) {
			cxmProductionHolidayModel = option.get();
			cxmProductionHolidayModel.setIs_delete(true);
			cxmProductionHolidayModel.setDeleted_on(new Date());
			cxmProductionHolidayModel.setDeleted_by(deleted_by);
			ihmProductionHolidayRepository.save(cxmProductionHolidayModel);

		}
		return cxmProductionHolidayModel;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int production_holiday_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CXmProductionHolidayModel cXmProductionHolidayModel = null;
		try {
			cXmProductionHolidayModel = ihmProductionHolidayRepository.FnShowParticularRecordForUpdate(production_holiday_id, company_id);
			responce.put("success", "1");
			responce.put("data", cXmProductionHolidayModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

	@Override
	public Page<CXmProductionHolidayModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return ihmProductionHolidayViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Page<CXmProductionHolidayViewModel> FnShowParticularRecord(int production_holiday_id, Pageable pageable,
	                                                                  int company_id) {
		return ihmProductionHolidayViewRepository.FnShowParticularRecord(production_holiday_id, pageable, company_id);
	}

	@Override
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) {
		return ihmProductionHolidayViewRepository.FnShowAllReportRecords(pageable);
	}

}
