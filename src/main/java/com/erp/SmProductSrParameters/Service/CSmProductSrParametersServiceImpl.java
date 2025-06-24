package com.erp.SmProductSrParameters.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductSrParameters.Model.CSmProductSrParametersModel;
import com.erp.SmProductSrParameters.Model.CSmProductSrParametersViewModel;
import com.erp.SmProductSrParameters.Repository.ISmProductSrParametersRepository;
import com.erp.SmProductSrParameters.Repository.ISmProductSrParametersViewRepository;

@Service
public class CSmProductSrParametersServiceImpl implements ISmProductSrParametersService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductSrParametersRepository iSmProductSrParametersRepository;

	@Autowired
	ISmProductSrParametersViewRepository iSmProductSrParametersViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CSmProductSrParametersModel cSmProductSrParametersModel) {
		Map<String, Object> responce = new HashMap<>();
		
		//find Particular Record using Product Parameter id
		Optional<CSmProductSrParametersModel> option = iSmProductSrParametersRepository.findById(cSmProductSrParametersModel.getProduct_parameter_id());
				
		int company_id = cSmProductSrParametersModel.getCompany_id();
		try {
			if (option.isPresent()) {
				
				//update Product Sr Parameters Records
				CSmProductSrParametersModel csmProductSrParametersModel = iSmProductSrParametersRepository.save(cSmProductSrParametersModel);
				
				responce.put("success", "1");
				responce.put("data", csmProductSrParametersModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				
			} else {
				
				//Check Product Sr Parameters Name exist or not
				CSmProductSrParametersModel model = iSmProductSrParametersRepository.checkIfNameExist(cSmProductSrParametersModel.getProduct_sr_parameter_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " Product Sr Parameters Name is already exist!");
					return responce;

				} else {

					//Saved Product Sr Parameters Records
					CSmProductSrParametersModel respContent = iSmProductSrParametersRepository.save(cSmProductSrParametersModel);

					responce.put("success", 1);
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductSrParameters/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductSrParameters/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;

	}


	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_type_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		
		CSmProductSrParametersViewModel cSmProductSrParametersViewModel = null;
		
		try {
			 
			//Fetch Product Sr Parameters Records
			cSmProductSrParametersViewModel  = iSmProductSrParametersViewRepository.FnShowParticularRecordForUpdate(product_type_id, company_id);
			
			responce.put("success", 1);
			responce.put("data", cSmProductSrParametersViewModel);
			responce.put("error", "");
			
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


	@Override
	public Map<String, Object> FnDeleteRecord(int product_parameter_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Delete Product Sr Parameters Records
			iSmProductSrParametersRepository.FnDeleteProductSrParametersRecord(product_parameter_id,deleted_by);
			
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
