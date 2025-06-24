package com.erp.SmProductTypeDynamicControls.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductTypeDynamicControls.Model.CSmProductTypeDynamicControlsModel;
import com.erp.SmProductTypeDynamicControls.Model.CSmProductTypeDynamicControlsViewModel;
import com.erp.SmProductTypeDynamicControls.Repository.ISmProductTypeDynamicControlsRepository;
import com.erp.SmProductTypeDynamicControls.Repository.ISmProductTypeDynamicControlsViewRepository;

@Service
public class CSmProductTypeDynamicControlsServiceImpl implements ISmProductTypeDynamicControlsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductTypeDynamicControlsRepository iSmProductTypeDynamicControlsRepository;

	@Autowired
	ISmProductTypeDynamicControlsViewRepository iSmProductTypeDynamicControlsViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(
			CSmProductTypeDynamicControlsModel cSmProductTypeDynamicControlsModel) {
		Map<String, Object> responce = new HashMap<>();

		// Find existing details using Product_type_dynamic_controls_id
		Optional<CSmProductTypeDynamicControlsModel> option = iSmProductTypeDynamicControlsRepository
				.findById(cSmProductTypeDynamicControlsModel.getProduct_type_dynamic_controls_id());

		int company_id = cSmProductTypeDynamicControlsModel.getCompany_id();

		try {
			
			if (option.isPresent()) {
				CSmProductTypeDynamicControlsModel csmProductTypeDynamicControlsModel = iSmProductTypeDynamicControlsRepository
						.save(cSmProductTypeDynamicControlsModel);
				responce.put("success", 1);
				responce.put("data", csmProductTypeDynamicControlsModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");

			} else {

				// Check if control Name already exist.
				CSmProductTypeDynamicControlsModel model = iSmProductTypeDynamicControlsRepository
						.checkIfNameExist(cSmProductTypeDynamicControlsModel.getControl_name(), company_id, cSmProductTypeDynamicControlsModel.getProduct_type_id());

				if (model != null) {
					responce.put("success", 0);
					responce.put("data", "");
					responce.put("error", "Control Name is already exist!");

					return responce;

				} else {

					// saved Product Type Dynamic Controls Records
					CSmProductTypeDynamicControlsModel respProductTypeDynamicControlsModel = iSmProductTypeDynamicControlsRepository
							.save(cSmProductTypeDynamicControlsModel);

					responce.put("success", "1");
					responce.put("data", respProductTypeDynamicControlsModel);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/SmProductTypeDynamicControls/FnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/SmProductTypeDynamicControls/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int product_type_dynamic_controls_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			// Delete Product Type Dynamic Controls Details
			iSmProductTypeDynamicControlsRepository
					.FnDeleteProductTypeDynamicControlsRecord(product_type_dynamic_controls_id, deleted_by);
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_type_dynamic_controls_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			// Fetch Product Type Dynamic Controls Record for update
			CSmProductTypeDynamicControlsViewModel cSmProductTypeDynamicControlsModel = iSmProductTypeDynamicControlsViewRepository
					.FnShowParticularRecordForUpdate(product_type_dynamic_controls_id, company_id);

			responce.put("data", cSmProductTypeDynamicControlsModel);
			responce.put("success", 1);
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
