package com.erp.CmCommonParameters.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.CmCommonParameters.Model.CCmCommonParametersModel;
import com.erp.CmCommonParameters.Model.CCmCommonParametersViewModel;
import com.erp.CmCommonParameters.Repository.ICmCommonParametersRepository;
import com.erp.CmCommonParameters.Repository.ICmCommonParametersViewRepository;
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
public class CCmCommonParametersServiceImpl implements ICmCommonParametersService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICmCommonParametersRepository iCmCommonParametersRepository;

	@Autowired
	ICmCommonParametersViewRepository iCmCommonParametersViewRepository;


	@Override
	public Map<String, Object> FnAddUpdateRecord(CCmCommonParametersModel cCmCommonParametersModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CCmCommonParametersModel> option = iCmCommonParametersRepository
				.findById(cCmCommonParametersModel.getCommon_parameters_id());
		CCmCommonParametersModel MyModel = null;

		int company_id = cCmCommonParametersModel.getCompany_id();

		try {
			if (option.isPresent()) {
				CCmCommonParametersModel cmCommonParametersModel = iCmCommonParametersRepository
						.save(cCmCommonParametersModel);
				responce.put("success", "1");
				responce.put("data", cmCommonParametersModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" CmCommonParameters Updated Successfully!..");
			} else {
				CCmCommonParametersModel model = iCmCommonParametersRepository
						.checkIfNameExist(cCmCommonParametersModel.getCommon_parameters_name(), cCmCommonParametersModel.getCompany_id());


				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", "Common Parameters name is already exist!");

					return responce;

				} else {

					CCmCommonParametersModel respContent = iCmCommonParametersRepository.save(cCmCommonParametersModel);

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
						"/api/CmCommonParameters/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/CmCommonParameters/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int common_parameters_id, int company_id, String deleted_by) {
		return iCmCommonParametersRepository.FnDeleteRecord(common_parameters_id, company_id, deleted_by);
	}

	@Override
	public Page<CCmCommonParametersViewModel> FnShowAllActiveRecords(String common_parameters_master_name,
	                                                                 Pageable pageable) {
		return iCmCommonParametersViewRepository.FnShowAllActiveRecords(common_parameters_master_name, pageable);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int common_parameters_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CCmCommonParametersModel cCmCommonParametersModel = null;

		try {
			cCmCommonParametersModel = iCmCommonParametersRepository
					.FnShowParticularRecordForUpdate(common_parameters_id, company_id);
			responce.put("success", "1");
			responce.put("data", cCmCommonParametersModel);
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
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) {
		return iCmCommonParametersViewRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public Page<CCmCommonParametersViewModel> FnShowParticularRecord(int common_parameters_id, Pageable pageable,
	                                                                 int company_id) {
		return iCmCommonParametersViewRepository.FnShowParticularRecord(common_parameters_id, pageable, company_id);
	}

}
