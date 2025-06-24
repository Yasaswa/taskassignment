package com.erp.SmProductServiceActivityMaster.Service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Payment_Terms.Model.CPaymentTermsViewModel;
import com.erp.SmProductServiceActivityMaster.Model.CSmProductServiceActivityMasterModel;
import com.erp.SmProductServiceActivityMaster.Model.CSmProductServiceActivityMasterViewModel;
import com.erp.SmProductServiceActivityMaster.Repository.ISmProductServiceActivityMasterRepository;
import com.erp.SmProductServiceActivityMaster.Repository.ISmProductServiceActivityMasterViewRepository;

@Service
public class CSmProductServiceActivityMasterServiceImpl implements ISmProductServiceActivityMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductServiceActivityMasterRepository iSmProductServiceActivityMasterRepository;

	@Autowired
	ISmProductServiceActivityMasterViewRepository iSmProductServiceActivityMasterViewRepository;
	

	@Override
	public Map<String, Object> FnAddUpdateRecord(CSmProductServiceActivityMasterModel cSmProductServiceActivityMasterModel) {
		Map<String, Object> responce = new HashMap<>();
		
		Optional<CSmProductServiceActivityMasterModel> option = iSmProductServiceActivityMasterRepository.findById(cSmProductServiceActivityMasterModel.getProduct_service_activity_master_id());
		
		int company_id = cSmProductServiceActivityMasterModel.getCompany_id();
		try {
			if (option.isPresent()) {
				
				//update Product Service Activity Master
				CSmProductServiceActivityMasterModel csmProductServiceActivityMasterModel = iSmProductServiceActivityMasterRepository
						.save(cSmProductServiceActivityMasterModel);
				responce.put("success", "1");
				responce.put("data", csmProductServiceActivityMasterModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" SmProductServiceActivityMaster Updated Successfully!..");
			} else {

				CSmProductServiceActivityMasterModel model = iSmProductServiceActivityMasterRepository
						.checkIfNameExist(cSmProductServiceActivityMasterModel.getActivity_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " Product Service Activity Name  is already exist!");

					return responce;

				} else {

					//Product Service Activity Master
					CSmProductServiceActivityMasterModel respContent = iSmProductServiceActivityMasterRepository
							.save(cSmProductServiceActivityMasterModel);

					responce.put("success", 1);
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductServiceActivityMaster/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductServiceActivityMaster/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_service_activity_master_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
				
		try {
			
			//Fetch Product Service Activity Master Record For update
			CSmProductServiceActivityMasterViewModel cSmProductServiceActivityMasterModel = iSmProductServiceActivityMasterViewRepository
					.FnShowParticularRecordForUpdate(product_service_activity_master_id, company_id);
			
			responce.put("data", cSmProductServiceActivityMasterModel);
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

	@Override
	public Map<String, Object> FnDeleteRecord(int product_service_activity_master_id, String deleted_by) {		
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Delete Product Service Activity Master Record For update
			iSmProductServiceActivityMasterRepository.FnDeleteProductServiceActivityMasterRecord(product_service_activity_master_id, deleted_by);
			
			responce.put("success", 1);
			
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
	public Map<String, Object> FnShowAllActiveRecords(int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			ArrayList<CSmProductServiceActivityMasterViewModel> data = iSmProductServiceActivityMasterViewRepository.FnShowAllActiveRecords(company_id);
			resp.put("data", data);
			resp.put("success", "1");
			resp.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());
			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}
		return resp;
	}

}
