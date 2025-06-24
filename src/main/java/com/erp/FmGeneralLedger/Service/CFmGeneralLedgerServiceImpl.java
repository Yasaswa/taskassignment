package com.erp.FmGeneralLedger.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.FmGeneralLedger.Model.CFmGeneralLedgerModel;
import com.erp.FmGeneralLedger.Model.CFmGeneralLedgerViewModel;
import com.erp.FmGeneralLedger.Repository.IFmGeneralLedgerRepository;
import com.erp.FmGeneralLedger.Repository.IFmGeneralLedgerViewRepository;
import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingModel;
import com.erp.Sl_Gl_Mapping.Repository.ISl_Gl_MappingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CFmGeneralLedgerServiceImpl implements IFmGeneralLedgerService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IFmGeneralLedgerRepository iFmGeneralLedgerRepository;

	@Autowired
	IFmGeneralLedgerViewRepository iFmGeneralLedgerViewRepository;

	@Autowired
	ISl_Gl_MappingRepository iSl_Gl_MappingRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		CFmGeneralLedgerModel cFmGeneralLedgerModel = new CFmGeneralLedgerModel();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		Integer general_ledger_id = commonIdsObj.getInt("general_ledger_id");

		JSONObject TransHeaderData = (JSONObject) jsonObject.get("TransHeaderData");

		JSONArray array = (JSONArray) jsonObject.get("TransDetailData");
		try {
			Optional<CFmGeneralLedgerModel> option = iFmGeneralLedgerRepository.findById(general_ledger_id);

			CFmGeneralLedgerModel ledgerModel = objectMapper.readValue(TransHeaderData.toString(),
					CFmGeneralLedgerModel.class);

			if (option.isPresent()) {
				ledgerModel.setmodified_on(new Date());
				cFmGeneralLedgerModel = iFmGeneralLedgerRepository.save(ledgerModel);
				responce.put("success", "1");
				responce.put("data", cFmGeneralLedgerModel);
				responce.put("message", "Records Updated successfully!...");
				responce.put("error", "");

			} else {
				cFmGeneralLedgerModel = iFmGeneralLedgerRepository
						.checkIfGLedgerExist(ledgerModel.getgeneral_ledger_name(), company_id);
				if (cFmGeneralLedgerModel != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", "General ledger name already exist!...");
					return responce;
				}

				cFmGeneralLedgerModel = iFmGeneralLedgerRepository.save(ledgerModel);

				responce.put("success", "1");
				responce.put("data", cFmGeneralLedgerModel);
				responce.put("message", "Records added successfully!...");
				responce.put("error", "");
			}

			iSl_Gl_MappingRepository.updateStatus(cFmGeneralLedgerModel.getgeneral_ledger_id(),
					company_id);
			int new_general_ledger_id = cFmGeneralLedgerModel.getgeneral_ledger_id();
			List<CSl_Gl_MappingModel> SlGLMappingModels = objectMapper.readValue(array.toString(), new TypeReference<List<CSl_Gl_MappingModel>>() {
			});
			SlGLMappingModels.forEach(details -> details.setGeneral_ledger_id(new_general_ledger_id));

			iSl_Gl_MappingRepository.saveAll(SlGLMappingModels);

			responce.put("success", "1");
			return responce;

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
	public Object FnDeleteRecord(int general_ledger_id, int company_id, String deleted_by) {
		Optional<CFmGeneralLedgerModel> option = iFmGeneralLedgerRepository.findById(general_ledger_id);
		CFmGeneralLedgerModel cFmGeneralLedgerModel = new CFmGeneralLedgerModel();

		if (option.isPresent()) {
			cFmGeneralLedgerModel = option.get();
			cFmGeneralLedgerModel.setis_delete(true);
			cFmGeneralLedgerModel.setdeleted_on(new Date());
			cFmGeneralLedgerModel.setdeleted_by(deleted_by);
			iFmGeneralLedgerRepository.save(cFmGeneralLedgerModel);

			iSl_Gl_MappingRepository.deleteMapping(general_ledger_id, deleted_by,
					company_id);
		}
		return cFmGeneralLedgerModel;
	}

	@Override
	public Page<CFmGeneralLedgerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iFmGeneralLedgerViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int general_ledger_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CFmGeneralLedgerModel cFmGeneralLedgerModel = null;
		try {
			cFmGeneralLedgerModel = iFmGeneralLedgerRepository.FnShowParticularRecordForUpdate(general_ledger_id,
					company_id);
			responce.put("success", "1");
			responce.put("data", cFmGeneralLedgerModel);
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
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iFmGeneralLedgerRepository.FnShowAllReportRecords(pageable, company_id);
	}

	@Override
	public Page<CSl_Gl_MappingModel> FnShowParticularRecord(int general_ledger_id, Pageable pageable,
	                                                        int company_id) {
		return iSl_Gl_MappingRepository.FnShowParticularRecord(general_ledger_id, pageable, company_id);
	}

}
