package com.erp.XmProductionLotMaster.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmProductionLotMaster.Model.CXmProductionLotMasterModel;
import com.erp.XmProductionLotMaster.Model.CXmProductionLotMasterViewModel;
import com.erp.XmProductionLotMaster.Repository.IXmProductionLotMasterRepository;
import com.erp.XmProductionLotMaster.Repository.IXmProductionLotMasterViewRepository;

@Service
public class CXmProductionLotMasterServiceImpl implements IXmProductionLotMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmProductionLotMasterRepository iXmProductionLotMasterRepository;

	@Autowired
	IXmProductionLotMasterViewRepository iXmProductionLotMasterViewRepository;


	@Override
	public Map<String, Object> FnAddUpdateRecord(CXmProductionLotMasterModel cXmProductionLotMasterModel) {
		Map<String, Object> responce = new HashMap<>();
		
		Optional<CXmProductionLotMasterModel> option = iXmProductionLotMasterRepository.findById(cXmProductionLotMasterModel.getLot_id());
		
		int company_id = cXmProductionLotMasterModel.getCompany_id();
		
		try {
			
			if (option.isPresent()) {
				
				CXmProductionLotMasterModel cxmProductionLotMasterModel = iXmProductionLotMasterRepository
						.save(cXmProductionLotMasterModel);
				
				responce.put("success",1);
				responce.put("data", cxmProductionLotMasterModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
			} else {

					CXmProductionLotMasterModel respContent = iXmProductionLotMasterRepository
							.save(cXmProductionLotMasterModel);

					responce.put("success", 1);
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
			}
			
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmProductionLotMaster/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XmProductionLotMaster/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int lot_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		
		try {
			
			//Fetch Production Lot Master Record for update
			CXmProductionLotMasterViewModel cXmProductionLotMasterModel = iXmProductionLotMasterViewRepository.FnShowParticularRecordForUpdate(lot_id,
					company_id);
			
			responce.put("success", 1);
			responce.put("ProductionLotMasterRecord", cXmProductionLotMasterModel);
			
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

	@Override
	public Map<String, Object> FnDeleteRecord(int lot_id, int company_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Delete Production Lot Master Record
			iXmProductionLotMasterRepository.FnDeleteProductionLotMasterRecord(lot_id,company_id,deleted_by);
			
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

}
