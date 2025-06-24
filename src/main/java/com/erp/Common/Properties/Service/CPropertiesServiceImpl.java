package com.erp.Common.Properties.Service;

import com.erp.Common.Properties.Model.CPropertiesModel;
import com.erp.Common.Properties.Model.CPropertiesViewModel;
import com.erp.Common.Properties.Repository.IPropertiesRepository;
import com.erp.Common.Properties.Repository.IPropertiesViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CPropertiesServiceImpl implements IPropertiesService {

	@Autowired
	IPropertiesRepository iPropertiesRepository;

	@Autowired
	IPropertiesViewRepository iPropertiesViewRepository;


	public List<CPropertiesViewModel> FnShowAllRecords() {
		return (List<CPropertiesViewModel>) iPropertiesRepository.FnShowAllRecords();
	}

	@Override
	public List<CPropertiesViewModel> FnShowParticularRecord(String controlName, int company_id) {

		return iPropertiesViewRepository.FnShowParticularRecord(controlName, company_id);
	}

	/// 
	@Override
	public Map<String, Object> FnAddUpdateRecord(CPropertiesModel cAmPropertiesModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CPropertiesModel> option = iPropertiesRepository.findById(cAmPropertiesModel.getProperty_id());
		CPropertiesModel MyModel = null;
		try {
			if (option.isPresent()) {
				cAmPropertiesModel = iPropertiesRepository.save(cAmPropertiesModel);
				responce.put("success", "1");
				responce.put("data", cAmPropertiesModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
			} else {
				CPropertiesModel model = iPropertiesRepository
						.checkIfNameExist(cAmPropertiesModel.getProperty_name(), cAmPropertiesModel.getCompany_id(), cAmPropertiesModel.getProperties_master_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", "Property name is already exist!..");

				} else {

					CPropertiesModel respContent = iPropertiesRepository.save(cAmPropertiesModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
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
	public Object FnDeleteRecord(long property_id, int company_id, String deleted_by) {
		Optional<CPropertiesModel> option = iPropertiesRepository.findById(property_id);
		CPropertiesModel cAmPropertiesModel = new CPropertiesModel();
		if (option.isPresent()) {
			cAmPropertiesModel = option.get();
			cAmPropertiesModel.setIs_delete(true);
			cAmPropertiesModel.setDeleted_on(new Date());
			cAmPropertiesModel.setDeleted_by(deleted_by);
			cAmPropertiesModel = iPropertiesRepository.save(cAmPropertiesModel);

		}
		return cAmPropertiesModel;
	}

	@Override
	public Page<CPropertiesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iPropertiesViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int property_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			CPropertiesModel cAmPropertiesModel = iPropertiesRepository.FnShowParticularRecordForUpdate(property_id, company_id);
			responce.put("success", "1");
			responce.put("data", cAmPropertiesModel);
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
		return iPropertiesViewRepository.FnShowAllReportRecords(pageable, company_id);
	}

	@Override
	public Page<CPropertiesViewModel> FnShowParticularRecordById(int property_id, Pageable pageable, int company_id) {
		return iPropertiesViewRepository.FnShowParticularRecordById(property_id, pageable, company_id);
	}
}
