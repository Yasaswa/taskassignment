package com.erp.XmProductionProcess.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmProductionProcess.Model.CXmProductionProcessModel;
import com.erp.XmProductionProcess.Model.CXmProductionProcessViewModel;
import com.erp.XmProductionProcess.Repository.IXmProductionProcessRepository;
import com.erp.XmProductionProcess.Repository.IXmProductionProcessViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CXmProductionProcessServiceImpl implements IXmProductionProcessService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmProductionProcessRepository iXmProductionProcessRepository;

	@Autowired
	IXmProductionProcessViewRepository iXmProductionProcessViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CXmProductionProcessModel cXmProductionProcessModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CXmProductionProcessModel> option = iXmProductionProcessRepository
				.findById(cXmProductionProcessModel.getProduction_process_id());
		CXmProductionProcessModel MyModel = null;
		int company_id = cXmProductionProcessModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cXmProductionProcessModel.setModified_on(new Date());
				CXmProductionProcessModel cxmProductionProcessModel = iXmProductionProcessRepository
						.save(cXmProductionProcessModel);
				responce.put("success", "1");
				responce.put("data", cxmProductionProcessModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" XmProductionProcess Updated Successfully!..");
			}

//			Check similar Production Process Name or short name is exist or not

			CXmProductionProcessModel resultsProductProcessName = null;

			if (cXmProductionProcessModel.getProduction_process_short_name() == null
					|| cXmProductionProcessModel.getProduction_process_short_name().isEmpty()) {

				resultsProductProcessName = iXmProductionProcessRepository.getCheck(
						cXmProductionProcessModel.getProduction_process_name(), null,
						cXmProductionProcessModel.getCompany_id());
			} else {
				resultsProductProcessName = iXmProductionProcessRepository.getCheck(
						cXmProductionProcessModel.getProduction_process_name(),
						cXmProductionProcessModel.getProduction_process_short_name(),
						cXmProductionProcessModel.getCompany_id());
			}

			if (resultsProductProcessName != null) {
				responce.put("success", 0);
				responce.put("error", "Production Process Name or Short Name is already exist!");
				return responce;
			} else {
				CXmProductionProcessModel respContent = iXmProductionProcessRepository.save(cXmProductionProcessModel);

				responce.put("success", "1");
				responce.put("data", respContent);
				responce.put("error", "");
				responce.put("message", "Record added successfully!...");
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
	public Object FnDeleteRecord(int production_process_id, int company_id, String deleted_by) {
		Optional<CXmProductionProcessModel> option = iXmProductionProcessRepository.findById(production_process_id);
		CXmProductionProcessModel cXmProductionProcessModel = new CXmProductionProcessModel();
		if (option.isPresent()) {
			cXmProductionProcessModel = option.get();
			cXmProductionProcessModel.setIs_delete(true);
			cXmProductionProcessModel.setDeleted_on(new Date());
			cXmProductionProcessModel.setDeleted_by(deleted_by);
			iXmProductionProcessRepository.save(cXmProductionProcessModel);
		}
		return cXmProductionProcessModel;
	}

	@Override
	public Page<CXmProductionProcessViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iXmProductionProcessViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int production_process_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CXmProductionProcessModel cXmProductionProcessModel = null;
		try {
			cXmProductionProcessModel = iXmProductionProcessRepository
					.FnShowParticularRecordForUpdate(production_process_id, company_id);
			responce.put("success", "1");
			responce.put("data", cXmProductionProcessModel);
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
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iXmProductionProcessViewRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public Page<CXmProductionProcessViewModel> FnShowParticularRecord(int production_process_id, Pageable pageable,
	                                                                  int company_id) {
		return iXmProductionProcessViewRepository.FnShowParticularRecord(production_process_id, pageable, company_id);
	}

}
