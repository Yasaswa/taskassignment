package com.erp.XmYarnPackingTypes.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmYarnPackingTypes.Model.CXmYarnPackingTypesModel;
import com.erp.XmYarnPackingTypes.Model.CXmYarnPackingTypesViewModel;
import com.erp.XmYarnPackingTypes.Repository.IXmYarnPackingTypesRepository;
import com.erp.XmYarnPackingTypes.Repository.IXmYarnPackingTypesViewRepository;

@Service
public class CXmYarnPackingTypesServiceImpl implements IXmYarnPackingTypesService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmYarnPackingTypesRepository iXmYarnPackingTypesRepository;
	
	@Autowired 
	IXmYarnPackingTypesViewRepository iXmYarnPackingTypesViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CXmYarnPackingTypesModel cXmYarnPackingTypesModel) {
		Map<String, Object> response = new HashMap<>();
		
		int company_id = cXmYarnPackingTypesModel.getCompany_id();
		AtomicInteger yarn_packing_types_id = new AtomicInteger(cXmYarnPackingTypesModel.getYarn_packing_types_id());
		
		try {
			
			if (yarn_packing_types_id.get() == 0) {
				
				CXmYarnPackingTypesModel checkIsYarnPackingTypeExist = iXmYarnPackingTypesRepository
						.checkIsYarnPackingTypeExist(cXmYarnPackingTypesModel.getYarn_packing_types_name(), cXmYarnPackingTypesModel.getYarn_packing_types_id());
				
				if (checkIsYarnPackingTypeExist != null) {
					response.put("success", 0);
					response.put("data", "");
					response.put("error", "Yarn packing type is already exist!...");
					return response;
				}
			}

			CXmYarnPackingTypesModel respContent = iXmYarnPackingTypesRepository.save(cXmYarnPackingTypesModel);

			response.put("success", 1);
			response.put("data", respContent);
			response.put("error", "");
			response.put("message", yarn_packing_types_id.get() == 0 ? "Record added successfully!..." : "Record updated successfully!...");
	
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmYarnPackingTypes/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				response.put("success", 0);
				response.put("data", "");
				response.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmYarnPackingTypes/FnAddUpdateRecord", 0,
					e.getMessage());
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());

		}
		return response;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int yarn_packing_types_id, int company_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		
		try {
			//Delete Yarn Packing Types Records
			iXmYarnPackingTypesRepository.FnDeleteYarnPackingTypesRecords(yarn_packing_types_id,company_id,deleted_by);

			responce.put("success", 1);
			
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

	public Map<String, Object> FnShowParticularRecordForUpdate(int yarn_packing_types_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Fetch Yarn Packing Types Records
			CXmYarnPackingTypesViewModel yarnPackingTypesRecords = iXmYarnPackingTypesViewRepository.FnShowParticularRecordForUpdate(yarn_packing_types_id, company_id);
			
			responce.put("success",1);
			responce.put("YarnPackingTypesModelRecords", yarnPackingTypesRecords);
			
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success",0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

}
