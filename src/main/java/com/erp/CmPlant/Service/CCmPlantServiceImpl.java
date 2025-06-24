package com.erp.CmPlant.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.CmPlant.Model.CCmPlantModel;
import com.erp.CmPlant.Model.CCmPlantViewModel;
import com.erp.CmPlant.Repository.ICmPlantRepository;
import com.erp.CmPlant.Repository.ICmPlantViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CCmPlantServiceImpl implements ICmPlantService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICmPlantRepository iCmPlantRepository;

	@Autowired
	ICmPlantViewRepository iCmPlantViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CCmPlantModel cCmPlantModel) {
		Map<String, Object> responce = new HashMap<>();

		CCmPlantModel MyModel = null;
		int company_id = cCmPlantModel.getCompany_id();
		try {
			Optional<CCmPlantModel> option = iCmPlantRepository
					.findById(cCmPlantModel.getPlant_id());
			if (option.isPresent()) {
				cCmPlantModel.setModified_on(new Date());
				CCmPlantModel ccmPlantModel = iCmPlantRepository.save(cCmPlantModel);
				responce.put("success", "1");
				responce.put("data", ccmPlantModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" CmPlant Updated Successfully!..");
			} else {
				CCmPlantModel model = iCmPlantRepository
						.checkIfNameExist(cCmPlantModel.getPlant_name(), company_id);

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", "Plant name is already exist!...");
				} else {
					CCmPlantModel respContent = iCmPlantRepository.save(cCmPlantModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmPlant/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmPlant/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}


	@Override
	public Object FnDeleteRecord(int plant_id, int company_id, String deleted_by) {
		Optional<CCmPlantModel> option = iCmPlantRepository.findById(plant_id);
		CCmPlantModel cCmPlantModel = new CCmPlantModel();
		if (option.isPresent()) {
			cCmPlantModel = option.get();
			cCmPlantModel.setIs_delete(true);
			cCmPlantModel.setDeleted_by(deleted_by);
			cCmPlantModel.setDeleted_on(new Date());
			iCmPlantRepository.save(cCmPlantModel);
		}
		return cCmPlantModel;
	}

	@Override
	public Page<CCmPlantViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iCmPlantViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int plant_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CCmPlantModel cCmPlantModel = null;
		try {
			cCmPlantModel = iCmPlantRepository.FnShowParticularRecordForUpdate(plant_id, company_id);
			responce.put("success", "1");
			responce.put("data", cCmPlantModel);
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
		return iCmPlantViewRepository.FnShowAllReportRecords(pageable, company_id);
	}

	@Override
	public Page<CCmPlantViewModel> FnShowParticularRecord(int plant_id, int company_id, Pageable pageable) {
		return iCmPlantViewRepository.FnShowParticularRecord(plant_id, company_id, pageable);
	}

}
