package com.erp.XmProductionSpinningPlanParameter.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmProductionSpinningPlanParameter.Model.CXmProductionSpinningPlanParameterModel;
import com.erp.XmProductionSpinningPlanParameter.Model.CXmProductionSpinningPlanParameterViewModel;
import com.erp.XmProductionSpinningPlanParameter.Repository.IXmProductionSpinningPlanParameterRepository;
import com.erp.XmProductionSpinningPlanParameter.Repository.IXmProductionSpinningPlanParameterViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CXmProductionSpinningPlanParameterServiceImpl implements IXmProductionSpinningPlanParameterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmProductionSpinningPlanParameterRepository iXmProductionSpinningPlanParameterRepository;

	@Autowired
	IXmProductionSpinningPlanParameterViewRepository iXmProductionSpinningPlanParameterViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CXmProductionSpinningPlanParameterModel cXmProductionSpinningPlanParameterModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CXmProductionSpinningPlanParameterModel> option = iXmProductionSpinningPlanParameterRepository
				.findById(cXmProductionSpinningPlanParameterModel.getProduction_spinning_plan_parameter_id());
		CXmProductionSpinningPlanParameterModel MyModel = null;
		int company_id = cXmProductionSpinningPlanParameterModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cXmProductionSpinningPlanParameterModel.setModified_on(new Date());
				CXmProductionSpinningPlanParameterModel mProductionSpinningPlanParameterModel = iXmProductionSpinningPlanParameterRepository.save(cXmProductionSpinningPlanParameterModel);
				responce.put("success", "1");
				responce.put("data", mProductionSpinningPlanParameterModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" XmProductionSpinningPlanParameter Updated Successfully!..");
			} else {
				CXmProductionSpinningPlanParameterModel model = iXmProductionSpinningPlanParameterRepository
						.checkIfNameExist(cXmProductionSpinningPlanParameterModel.getProduction_spinning_plan_parameter_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " Production_spinning_plan_parameter_name is already exist!");

					return responce;

				} else {

					CXmProductionSpinningPlanParameterModel respContent = iXmProductionSpinningPlanParameterRepository.save(cXmProductionSpinningPlanParameterModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmProductionSpinningPlanParameter/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmProductionSpinningPlanParameter/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}


	@Override
	public Object FnDeleteRecord(int production_spinning_plan_parameter_id, int company_id, String deleted_by) {
		Optional<CXmProductionSpinningPlanParameterModel> option = iXmProductionSpinningPlanParameterRepository.findById(production_spinning_plan_parameter_id);
		CXmProductionSpinningPlanParameterModel cXmProductionSpinningPlanParameterModel = new CXmProductionSpinningPlanParameterModel();
		if (option.isPresent()) {
			cXmProductionSpinningPlanParameterModel = option.get();
			cXmProductionSpinningPlanParameterModel.setIs_delete(true);
			cXmProductionSpinningPlanParameterModel.setDeleted_on(new Date());
			cXmProductionSpinningPlanParameterModel.setDeleted_by(deleted_by);
			iXmProductionSpinningPlanParameterRepository.save(cXmProductionSpinningPlanParameterModel);

		}
		return cXmProductionSpinningPlanParameterModel;
	}

	@Override
	public Page<CXmProductionSpinningPlanParameterViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iXmProductionSpinningPlanParameterViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int production_spinning_plan_parameter_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CXmProductionSpinningPlanParameterModel cXmProductionSpinningPlanParameterModel = null;
		try {
			cXmProductionSpinningPlanParameterModel = iXmProductionSpinningPlanParameterRepository.FnShowParticularRecordForUpdate
					(production_spinning_plan_parameter_id, company_id);
			responce.put("success", "1");
			responce.put("data", cXmProductionSpinningPlanParameterModel);
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
		return iXmProductionSpinningPlanParameterViewRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public Page<CXmProductionSpinningPlanParameterViewModel> FnShowParticularRecord(int production_spinning_plan_parameter_id, Pageable pageable, int company_id) {
		return iXmProductionSpinningPlanParameterViewRepository.FnShowParticularRecord(production_spinning_plan_parameter_id, pageable, company_id);
	}

}
