package com.erp.SmProductFgParameters.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Service;
import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductFgParameters.Model.CSmProductFgParametersModel;
import com.erp.SmProductFgParameters.Model.CSmProductFgParametersViewModel;
import com.erp.SmProductFgParameters.Repository.ISmProductFgParametersRepository;
import com.erp.SmProductFgParameters.Repository.ISmProductFgParametersViewRepository;


@Service
public class CSmProductFgParametersServiceImpl implements ISmProductFgParametersService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductFgParametersRepository iSmProductFgParametersRepository;

	@Autowired
	ISmProductFgParametersViewRepository iSmProductFgParametersViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CSmProductFgParametersModel cSmProductFgParametersModel) {
		Map<String, Object> responce = new HashMap<>();

		//find Particular Record using Product_parameter_id for update
		Optional<CSmProductFgParametersModel> option = iSmProductFgParametersRepository.findById(cSmProductFgParametersModel.getProduct_parameter_id());	
		
		int company_id = cSmProductFgParametersModel.getCompany_id();
		
		try {
			if (option.isPresent()) {
	
				//Update Product Fg Parameters Record 
				CSmProductFgParametersModel csmProductFgParametersModel = iSmProductFgParametersRepository.save(cSmProductFgParametersModel);
				
				responce.put("success", "1");
				responce.put("data", csmProductFgParametersModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println("Product Fg Parameters Updated Successfully!..");
				
			} else {
				
				//Check if Product Fg Parameter Name exist or not
				CSmProductFgParametersModel model = iSmProductFgParametersRepository.checkIfNameExist(cSmProductFgParametersModel.getProduct_fg_parameter_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", "Product Fg Parameters Name is already exist!");
					return responce;

				} else {
					
					//Saved Product Fg Parameters
					CSmProductFgParametersModel respContent = iSmProductFgParametersRepository.save(cSmProductFgParametersModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgParameters/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFgParameters/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}
	
	

	@Override
	public Map<String, Object> FnDeleteRecord(int product_parameter_id, String deleted_by) {
		Map<String, Object> responce =  new HashMap<>();
		try {
			
			//Delete Product Fg Parameters Records
			iSmProductFgParametersRepository.FnDeleteProductFgParametersRecord(product_parameter_id,deleted_by);
			
		} catch (Exception e) {
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return null;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_type_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		
		CSmProductFgParametersViewModel cSmProductFgParametersViewModel = null;
		try {

			//fetch Product Fg Parameters Records
			cSmProductFgParametersViewModel = iSmProductFgParametersViewRepository.FnShowParticularRecordForUpdate(product_type_id, company_id);
			
			responce.put("success", "1");
			responce.put("data", cSmProductFgParametersViewModel);
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


}
