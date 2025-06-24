package com.erp.SmProductCodification.Service;


import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductCodification.Model.CSmProductCodificationModel;
import com.erp.SmProductCodification.Repository.ISmProductCodificationRepository;


@Service
public class CSmProductCodificationServiceImpl implements ISmProductCodificationService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductCodificationRepository iSmProductCodificationRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CSmProductCodificationModel cSmProductCodificationModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CSmProductCodificationModel> option = iSmProductCodificationRepository
				.findById(cSmProductCodificationModel.getProduct_codification_id());
		int company_id = cSmProductCodificationModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cSmProductCodificationModel.setModified_on(new Date());
				CSmProductCodificationModel csmProductCodificationModel = iSmProductCodificationRepository
						.save(cSmProductCodificationModel);
				responce.put("success", "1");
				responce.put("data", csmProductCodificationModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" SmProductCodification Updated Successfully!..");
			} else {
				CSmProductCodificationModel model = iSmProductCodificationRepository
						.checkIfNameExist(cSmProductCodificationModel.getProduct_specification_name(), company_id);

				if (model != null) {
					responce.put("success", 0);
					responce.put("data", "");
					responce.put("error", "  Product Specification Name is already exist!");
					return responce;

				} else {

					CSmProductCodificationModel respContent = iSmProductCodificationRepository
							.save(cSmProductCodificationModel);
					responce.put("success", 1);
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductCodification/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductCodification/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int product_codification_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			iSmProductCodificationRepository.FnDeleteProductCodificationRecord(product_codification_id, deleted_by);
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_codification_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CSmProductCodificationModel responceProductCodificationModel = null;
		try {
			
			responceProductCodificationModel = iSmProductCodificationRepository.FnShowParticularRecordForUpdate(product_codification_id, company_id);
			responce.put("success", 1);
			responce.put("data", responceProductCodificationModel);
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
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

}
