package com.erp.CmPaymentSchedule.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.CmPaymentSchedule.Model.CCmPaymentScheduleModel;
import com.erp.CmPaymentSchedule.Model.CCmPaymentScheduleViewModel;
import com.erp.CmPaymentSchedule.Repository.ICmPaymentScheduleRepository;
import com.erp.CmPaymentSchedule.Repository.ICmPaymentScheduleViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CCmPaymentScheduleServiceImpl implements ICmPaymentScheduleService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICmPaymentScheduleRepository iCmPaymentScheduleRepository;

	@Autowired
	ICmPaymentScheduleViewRepository iCmPaymentScheduleViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CCmPaymentScheduleModel cCmPaymentScheduleModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CCmPaymentScheduleModel> option = iCmPaymentScheduleRepository
				.findById(cCmPaymentScheduleModel.getPayment_schedule_id());
		int company_id = cCmPaymentScheduleModel.getCompany_id();
		CCmPaymentScheduleModel MyModel = null;
		try {
			if (option.isPresent()) {
				CCmPaymentScheduleModel saveResp = iCmPaymentScheduleRepository.save(cCmPaymentScheduleModel);
				responce.put("success", "1");
				responce.put("data", saveResp);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" CmPaymentSchedule Updated Successfully!..");
			} else {
				CCmPaymentScheduleModel model = iCmPaymentScheduleRepository
						.checkIfNameExist(cCmPaymentScheduleModel.getPayment_schedule_name(), company_id);

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " CmPaymentSchedule  is already exist!");

				} else {

					CCmPaymentScheduleModel respContent = iCmPaymentScheduleRepository.save(cCmPaymentScheduleModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int payment_schedule_id, int company_id, String deleted_by) {
		CCmPaymentScheduleModel option = iCmPaymentScheduleRepository.findByPaymentScheduleId(payment_schedule_id, company_id);
		CCmPaymentScheduleModel cCmPaymentScheduleModel = new CCmPaymentScheduleModel();
		if (option != null) {
			option.setIs_delete(true);
			option.setDeleted_on(new Date());
			option.setDeleted_by(deleted_by);
			iCmPaymentScheduleRepository.save(option);
		}
		return cCmPaymentScheduleModel;
	}

	@Override
	public Page<CCmPaymentScheduleViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iCmPaymentScheduleViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int payment_schedule_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CCmPaymentScheduleModel cCmPaymentScheduleModel = null;
		try {
			cCmPaymentScheduleModel = iCmPaymentScheduleRepository.FnShowParticularRecordForUpdate(payment_schedule_id, company_id);
			responce.put("success", "1");
			responce.put("data", cCmPaymentScheduleModel);
			responce.put("error", "");
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
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iCmPaymentScheduleViewRepository.FnShowAllReportRecords(pageable, company_id);
	}

	@Override
	public Page<CCmPaymentScheduleViewModel> FnShowParticularRecord(int payment_schedule_id, Pageable pageable, int company_id) {
		return iCmPaymentScheduleViewRepository.FnShowParticularRecord(payment_schedule_id, pageable, company_id);
	}

}
