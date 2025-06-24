package com.erp.SmProductPrParameters.Service;

import java.io.File;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductPrParameters.Model.CSmProductPrParametersModel;
import com.erp.SmProductPrParameters.Model.CSmProductPrParametersViewModel;
import com.erp.SmProductPrParameters.Repository.ISmProductPrParametersRepository;
import com.erp.SmProductPrParameters.Repository.ISmProductPrParametersViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CSmProductPrParametersServiceImpl implements ISmProductPrParametersService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductPrParametersRepository iSmProductPrParametersRepository;

	@Autowired
	ISmProductPrParametersViewRepository iSmProductPrParametersViewRepository;

	
	@Override
	public Map<String, Object> FnAddUpdateRecord(CSmProductPrParametersModel cSmProductPrParametersModel) {
		Map<String, Object> responce = new HashMap<>();
		
		//Find Particular Record using Product_parameter_id 
		Optional<CSmProductPrParametersModel> option = iSmProductPrParametersRepository.findById(cSmProductPrParametersModel.getProduct_parameter_id());
		
		int company_id = cSmProductPrParametersModel.getCompany_id();
		try {
			if (option.isPresent()) {
				
				//update Product Pr Parameters Record
				CSmProductPrParametersModel productPrParametersModel = iSmProductPrParametersRepository.save(cSmProductPrParametersModel);
				responce.put("success", 1);
				responce.put("data", productPrParametersModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
			} else {
				
				//Check Product Pr Parameters 
				CSmProductPrParametersModel model = iSmProductPrParametersRepository
						.checkIfNameExist(cSmProductPrParametersModel.getProduct_pr_parameter_name());

				if (model != null) {
					responce.put("success", 0);
					responce.put("data", "");
					responce.put("error", "Product Pr Parameters Name is already exist!");
					return responce;

				} else {

					//Saved Product Pr Parameters Records 
					CSmProductPrParametersModel respContent = iSmProductPrParametersRepository.save(cSmProductPrParametersModel);

					responce.put("success", 1);
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductPrParameters/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductPrParameters/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_type_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		
		CSmProductPrParametersViewModel cSmProductPrParametersViewModel = null;
		try {
			
			//Fetch Product Pr Parameters Record for update
			cSmProductPrParametersViewModel  = iSmProductPrParametersViewRepository.FnShowParticularRecordForUpdate(product_type_id, company_id);
			
			responce.put("success", 1);
			responce.put("data", cSmProductPrParametersViewModel);
			responce.put("error", "");
			
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
			responce.put("success",0);
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
			
			//Delete Product Pr Parameters Record
			iSmProductPrParametersRepository.FnDeleteProductPrParametersRecord(product_parameter_id,deleted_by);
			
			responce.put("success",1);
			responce.put("data", "");

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success",0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
            return responce;
		}
		return responce;
	}

	
	}
