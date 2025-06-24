package com.erp.XmProductionWastageTypes.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmProductionWastageTypes.Model.CXmProductionWastageTypesModel;
import com.erp.XmProductionWastageTypes.Model.CXmProductionWastageTypesViewModel;
import com.erp.XmProductionWastageTypes.Repository.IXmProductionWastageTypesRepository;
import com.erp.XmProductionWastageTypes.Repository.IXmProductionWastageTypesRptRepository;
import com.erp.XmProductionWastageTypes.Repository.IXmProductionWastageTypesViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CXmProductionWastageTypesServiceImpl implements IXmProductionWastageTypesService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmProductionWastageTypesRepository iXmProductionWastageTypesRepository;

	@Autowired
	IXmProductionWastageTypesViewRepository iXmProductionWastageTypesViewRepository;

	@Autowired
	IXmProductionWastageTypesRptRepository iXmProductionWastageTypesRptRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CXmProductionWastageTypesModel cXmProductionWastageTypesModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CXmProductionWastageTypesModel> option = iXmProductionWastageTypesRepository
				.findById(cXmProductionWastageTypesModel.getProduction_wastage_types_id());
		CXmProductionWastageTypesModel MyModel = null;
		int company_id = cXmProductionWastageTypesModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cXmProductionWastageTypesModel.setModified_on(new Date());
				CXmProductionWastageTypesModel cxmProductionWastageTypesModel = iXmProductionWastageTypesRepository
						.save(cXmProductionWastageTypesModel);
				responce.put("success", "1");
				responce.put("data", cxmProductionWastageTypesModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" XmProductionWastageTypes Updated Successfully!..");
			} else {
				CXmProductionWastageTypesModel model = iXmProductionWastageTypesRepository
						.checkIfNameExist(cXmProductionWastageTypesModel.getProduction_wastage_types_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " XmProductionWastageTypes  is already exist!");

					return responce;

				} else {

					CXmProductionWastageTypesModel respContent = iXmProductionWastageTypesRepository
							.save(cXmProductionWastageTypesModel);

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
	public Object FnDeleteRecord(int production_wastage_types_id, int company_id, String deleted_by) {
		Optional<CXmProductionWastageTypesModel> option = iXmProductionWastageTypesRepository
				.findById(production_wastage_types_id);
		CXmProductionWastageTypesModel cXmProductionWastageTypesModel = new CXmProductionWastageTypesModel();
		if (option.isPresent()) {
			cXmProductionWastageTypesModel = option.get();
			cXmProductionWastageTypesModel.setIs_delete(true);
			cXmProductionWastageTypesModel.setDeleted_on(new Date());
			cXmProductionWastageTypesModel.setDeleted_by(deleted_by);
			iXmProductionWastageTypesRepository.save(cXmProductionWastageTypesModel);

		}
		return cXmProductionWastageTypesModel;
	}

	@Override
	public Page<CXmProductionWastageTypesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iXmProductionWastageTypesViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int production_wastage_types_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CXmProductionWastageTypesModel cXmProductionWastageTypesModel = null;
		try {
			cXmProductionWastageTypesModel = iXmProductionWastageTypesRepository
					.FnShowParticularRecordForUpdate(production_wastage_types_id, company_id);
			responce.put("success", "1");
			responce.put("data", cXmProductionWastageTypesModel);
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
	public Page<CXmProductionWastageTypesViewModel> FnShowParticularRecord(int production_wastage_types_id,
	                                                                       Pageable pageable, int company_id) {
		return iXmProductionWastageTypesViewRepository.FnShowParticularRecord(production_wastage_types_id, pageable,
				company_id);
	}

	@Override
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) {
		return iXmProductionWastageTypesViewRepository.FnShowAllReportRecords(pageable);
	}

}
