package com.erp.SmProductProcess.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductProcess.Model.CPmProductProcessModel;
import com.erp.SmProductProcess.Model.CPmProductProcessViewModel;
import com.erp.SmProductProcess.Repository.IPmProductProcessRepository;
import com.erp.SmProductProcess.Repository.IPmProductProcessViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CPmProductProcessServiceImpl implements IPmProductProcessService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IPmProductProcessRepository iPmProductProcessRepository;

	@Autowired
	IPmProductProcessViewRepository iPmProductProcessViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CPmProductProcessModel cPmProductProcessModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CPmProductProcessModel> option = iPmProductProcessRepository
				.findById(cPmProductProcessModel.getProduct_process_id());
		CPmProductProcessModel MyModel = null;
		int company_id = cPmProductProcessModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cPmProductProcessModel.setModified_on(new Date());
				CPmProductProcessModel cPmProductProcess = iPmProductProcessRepository.save(cPmProductProcessModel);
				responce.put("success", "1");
				responce.put("data", cPmProductProcess);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" PmProductProcess Updated Successfully!..");
			}
//			Check similar Product Make Name or short name is exist or not

			CPmProductProcessModel resultsProductProcessName = null;

			if (cPmProductProcessModel.getProduct_process_short_name() == null
					|| cPmProductProcessModel.getProduct_process_short_name().isEmpty()) {

				resultsProductProcessName = iPmProductProcessRepository.getCheck(
						cPmProductProcessModel.getProduct_process_name(), null, cPmProductProcessModel.getCompany_id());
			} else {
				resultsProductProcessName = iPmProductProcessRepository.getCheck(
						cPmProductProcessModel.getProduct_process_name(),
						cPmProductProcessModel.getProduct_process_short_name(), cPmProductProcessModel.getCompany_id());
			}

			if (resultsProductProcessName != null) {
				responce.put("success", "0");
				responce.put("error", "Product Process Name or Short Name is already exist!");
				return responce;
			} else {

				CPmProductProcessModel respContent = iPmProductProcessRepository.save(cPmProductProcessModel);

				responce.put("success", "1");
				responce.put("data", respContent);
				responce.put("error", "");
				responce.put("message", "Record added successfully!...");

				System.out.println("PmProductProcess  saved succesfully!..");
				return responce;
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/PmProductProcess/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/PmProductProcess/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

			return responce;
		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int product_process_id, String deleted_by) {
		Optional<CPmProductProcessModel> option = iPmProductProcessRepository.findById(product_process_id);
		CPmProductProcessModel cPmProductProcessModel = new CPmProductProcessModel();
		if (option.isPresent()) {
			cPmProductProcessModel = option.get();
			cPmProductProcessModel.setIs_delete(true);
			cPmProductProcessModel.setDeleted_by(deleted_by);
			cPmProductProcessModel.setDeleted_on(new Date());
			iPmProductProcessRepository.save(cPmProductProcessModel);

		}
		return cPmProductProcessModel;
	}

	@Override
	public Page<CPmProductProcessViewModel> FnShowAllActiveRecords(Pageable pageable) {
		return iPmProductProcessViewRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_process_id) {
		Map<String, Object> responce = new HashMap<>();
		CPmProductProcessModel cPmProductProcessModel = null;
		try {
			cPmProductProcessModel = iPmProductProcessRepository.FnShowParticularRecordForUpdate(product_process_id);
			responce.put("success", "1");
			responce.put("data", cPmProductProcessModel);
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
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) {
		return iPmProductProcessViewRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public Page<CPmProductProcessViewModel> FnShowParticularRecord(int product_process_id, Pageable pageable) {
		return iPmProductProcessViewRepository.FnShowParticularRecord(product_process_id, pageable);
	}

}
