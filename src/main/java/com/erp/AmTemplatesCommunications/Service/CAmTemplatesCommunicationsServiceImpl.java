package com.erp.AmTemplatesCommunications.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmTemplatesCommunications.Model.CAmTemplatesCommunicationsModel;
import com.erp.AmTemplatesCommunications.Model.CAmTemplatesCommunicationsViewModel;
import com.erp.AmTemplatesCommunications.Repository.IAmTemplatesCommunicationsRepository;
import com.erp.AmTemplatesCommunications.Repository.IAmTemplatesCommunicationsViewRepository;
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
public class CAmTemplatesCommunicationsServiceImpl implements IAmTemplatesCommunicationsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAmTemplatesCommunicationsRepository iAmTemplatesCommunicationsRepository;

	@Autowired
	IAmTemplatesCommunicationsViewRepository iAmTemplatesCommunicationsViewRepository;


	@Override
	public Map<String, Object> FnAddUpdateRecord(CAmTemplatesCommunicationsModel cAmTemplatesCommunicationsModel) {
		Map<String, Object> responce = new HashMap<>();

		CAmTemplatesCommunicationsModel MyModel = null;

		int company_id = cAmTemplatesCommunicationsModel.getCompany_id();
		try {
			Optional<CAmTemplatesCommunicationsModel> option = iAmTemplatesCommunicationsRepository
					.findById(cAmTemplatesCommunicationsModel.getCommunications_templates_id());

			if (option.isPresent()) {
				cAmTemplatesCommunicationsModel.setModified_on(new Date());
				CAmTemplatesCommunicationsModel camTemplatesCommunicationsModel = iAmTemplatesCommunicationsRepository
						.save(cAmTemplatesCommunicationsModel);
				responce.put("success", "1");
				responce.put("data", camTemplatesCommunicationsModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" AmTemplatesCommunications Updated Successfully!..");
			} else {

				CAmTemplatesCommunicationsModel respContent = iAmTemplatesCommunicationsRepository
						.save(cAmTemplatesCommunicationsModel);

				responce.put("success", "1");
				responce.put("data", respContent);
				responce.put("error", "");
				responce.put("message", "Record added successfully!...");
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/AmTemplatesCommunications/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/AmTemplatesCommunications/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int communications_templates_id, int company_id, String deleted_by) {
		Optional<CAmTemplatesCommunicationsModel> option = iAmTemplatesCommunicationsRepository
				.findById(communications_templates_id);
		CAmTemplatesCommunicationsModel cAmTemplatesCommunicationsModel = new CAmTemplatesCommunicationsModel();
		if (option.isPresent()) {
			cAmTemplatesCommunicationsModel = option.get();
			cAmTemplatesCommunicationsModel.setIs_delete(true);
			cAmTemplatesCommunicationsModel.setDeleted_on(new Date());
			cAmTemplatesCommunicationsModel.setDeleted_by(deleted_by);
			iAmTemplatesCommunicationsRepository.save(cAmTemplatesCommunicationsModel);

		}
		return cAmTemplatesCommunicationsModel;
	}

	@Override
	public Page<CAmTemplatesCommunicationsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iAmTemplatesCommunicationsViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int communications_templates_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CAmTemplatesCommunicationsModel cAmTemplatesCommunicationsModel = null;
		try {
			cAmTemplatesCommunicationsModel = iAmTemplatesCommunicationsRepository
					.FnShowParticularRecordForUpdate(communications_templates_id, company_id);
			responce.put("success", "1");
			responce.put("data", cAmTemplatesCommunicationsModel);
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

//	@Override
//	public Page<CAmTemplatesCommunicationsRptModel> FnShowAllReportRecords(Pageable pageable, int company_id) {
//		return iAmTemplatesCommunicationsRptRepository.FnShowAllReportRecords(pageable, company_id);
//	}

	@Override
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iAmTemplatesCommunicationsViewRepository.FnShowAllReportRecords(pageable, company_id);
	}


	@Override
	public Page<CAmTemplatesCommunicationsViewModel> FnShowParticularRecord(int communications_templates_id, Pageable pageable, int company_id) {
		return iAmTemplatesCommunicationsViewRepository.FnShowParticularRecord(communications_templates_id, pageable, company_id);
	}


}
