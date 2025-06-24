package com.erp.XmProductionStoppageReasons.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsModel;
import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsViewModel;
import com.erp.XmProductionStoppageReasons.Repository.IXmProductionStoppageReasonsRepository;
import com.erp.XmProductionStoppageReasons.Repository.IXmProductionStoppageReasonsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CXmProductionStoppageReasonsServiceImpl implements IXmProductionStoppageReasonsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmProductionStoppageReasonsRepository iXmProductionStoppageReasonsRepository;

	@Autowired
	IXmProductionStoppageReasonsViewRepository iXmProductionStoppageReasonsViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CXmProductionStoppageReasonsModel cXmProductionStoppageReasonsModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CXmProductionStoppageReasonsModel> option = iXmProductionStoppageReasonsRepository
				.findById(cXmProductionStoppageReasonsModel.getProduction_stoppage_reasons_id());
		CXmProductionStoppageReasonsModel MyModel = null;
		int company_id = cXmProductionStoppageReasonsModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cXmProductionStoppageReasonsModel.setModified_on(new Date());
				CXmProductionStoppageReasonsModel cXmProductionStoppageReasonsmodel = iXmProductionStoppageReasonsRepository
						.save(cXmProductionStoppageReasonsModel);
				responce.put("success", "1");
				responce.put("data", cXmProductionStoppageReasonsmodel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" Production Stoppage Reasons Updated Successfully!..");
			} else {
				CXmProductionStoppageReasonsModel model = iXmProductionStoppageReasonsRepository
						.checkIfNameExist(cXmProductionStoppageReasonsModel.getProduction_stoppage_reasons_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " Production Stoppage Reasons Name is already exist!");

					return responce;

				} else {

					CXmProductionStoppageReasonsModel respContent = iXmProductionStoppageReasonsRepository
							.save(cXmProductionStoppageReasonsModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/XmProductionStoppageReasons/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XmProductionStoppageReasons/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int production_stoppage_reasons_id, int company_id, String deleted_by) {
		Optional<CXmProductionStoppageReasonsModel> option = iXmProductionStoppageReasonsRepository
				.findById(production_stoppage_reasons_id);
		CXmProductionStoppageReasonsModel cXmProductionStoppageReasonsModel = new CXmProductionStoppageReasonsModel();
		if (option.isPresent()) {
			cXmProductionStoppageReasonsModel = option.get();
			cXmProductionStoppageReasonsModel.setIs_delete(true);
			cXmProductionStoppageReasonsModel.setDeleted_on(new Date());
			cXmProductionStoppageReasonsModel.setDeleted_by(deleted_by);
			iXmProductionStoppageReasonsRepository.save(cXmProductionStoppageReasonsModel);

		}
		return cXmProductionStoppageReasonsModel;
	}

	@Override
	public Page<CXmProductionStoppageReasonsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iXmProductionStoppageReasonsViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int production_stoppage_reasons_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CXmProductionStoppageReasonsModel cXmProductionStoppageReasonsModel = null;
		try {
			cXmProductionStoppageReasonsModel = iXmProductionStoppageReasonsRepository
					.FnShowParticularRecordForUpdate(production_stoppage_reasons_id, company_id);
			responce.put("success", "1");
			responce.put("data", cXmProductionStoppageReasonsModel);
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
	public Page<CXmProductionStoppageReasonsViewModel> FnShowParticularRecord(int production_stoppage_reasons_id,
	                                                                          Pageable pageable, int company_id) {
		return iXmProductionStoppageReasonsViewRepository.FnShowParticularRecord(production_stoppage_reasons_id,
				pageable, company_id);
	}

	@Override
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) {
		return iXmProductionStoppageReasonsViewRepository.FnShowAllReportRecords(pageable);
	}

}
