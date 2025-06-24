package com.erp.SmProductRmParameters.Service;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductRmParameters.Model.CSmProductRmParametersModel;
import com.erp.SmProductRmParameters.Model.CSmProductRmParametersViewModel;
import com.erp.SmProductRmParameters.Repository.ISmProductRmParametersRepository;
import com.erp.SmProductRmParameters.Repository.ISmProductRmParametersViewRepository;

@Service
public class CSmProductRmParametersServiceImpl implements ISmProductRmParametersService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductRmParametersRepository iSmProductRmParametersRepository;

	@Autowired
	ISmProductRmParametersViewRepository iSmProductRmParametersViewRepository;

	
	
	@Override
	public Map<String, Object> FnAddUpdateRecord(CSmProductRmParametersModel cSmProductRmParametersModel) {
		Map<String, Object> responce = new HashMap<>();
		
		//find Particular Record using Product_parameter_id for update
		Optional<CSmProductRmParametersModel> option = iSmProductRmParametersRepository.findById(cSmProductRmParametersModel.getProduct_parameter_id());
		
		int company_id = cSmProductRmParametersModel.getCompany_id();
		try {
			
			//update Product Rm Parameters 
			if (option.isPresent()) {
				
				//Find Particular Record using 
				CSmProductRmParametersModel csmProductRmParametersModel = iSmProductRmParametersRepository.save(cSmProductRmParametersModel);
				
				responce.put("success", 1);
				responce.put("data", csmProductRmParametersModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" Product Rm Parameters Updated Successfully!..");
			} else {
				
				//Check if Product Rm Parameters Name exist or not 
				CSmProductRmParametersModel model = iSmProductRmParametersRepository.checkIfNameExist(cSmProductRmParametersModel.getProduct_rm_parameter_name(),company_id);

				if (model != null) {
					responce.put("success", 0);
					responce.put("data", "");
					responce.put("error", " Product Rm Parameters Name is already exist!");
					return responce;

				} else {

					//save Product Rm Parameters Records
					CSmProductRmParametersModel respContent = iSmProductRmParametersRepository.save(cSmProductRmParametersModel);

					responce.put("success", 1);
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductRmParameters/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductRmParameters/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}
	
	@Override
	public Map<String, Object> FnDeleteRecord(int product_parameter_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Delete Product Rm Parameters Records
			iSmProductRmParametersRepository.FnDeleteProductRmParametersRecord(product_parameter_id,deleted_by);
			responce.put("success", 1);
			
		} catch (Exception e) {
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		
		return responce;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_type_id, int company_id) {
		Map<String, Object> responce =  new HashMap<>();
		
		CSmProductRmParametersViewModel cSmProductRmParametersViewModel = null;
		try {
			
			//Fetch Product Rm Parameters Record for Update
			cSmProductRmParametersViewModel = iSmProductRmParametersViewRepository.FnShowParticularRecordForUpdate(product_type_id,company_id);
			
			responce.put("success", 1);
			responce.put("data", cSmProductRmParametersViewModel);
			
			
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
//	public Map<String, Object> FnShowParticularRecordForUpdate(int product_type_id, int company_id) {
//		
//		Map<String, Object> responce = new HashMap<>();
//		CSmProductRmParametersViewModel responceProductRmParametersModel = null;
//		try {
//			
//			//fetch Product Rm Parameters Records
//			responceProductRmParametersModel  = iSmProductRmParametersViewRepository.FnShowParticularRecordForUpdate(product_type_id, company_id);
//			
//			responce.put("success", 1);
//			responce.put("data", responceProductRmParametersModel);
//			responce.put("error", "");
//			
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				responce.put("success", "0");
//				responce.put("data", "");
//				responce.put("error", e.getMessage());
//				return responce;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			responce.put("success", "0");
//			responce.put("data", "");
//			responce.put("error", e.getMessage());
//            return responce;
//		}
//		return responce;
//	}


	
	}
