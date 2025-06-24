package com.erp.Company.Company_Directors.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Company.Company_Directors.Model.CCompanyDirectorsModel;
import com.erp.Company.Company_Directors.Model.CCompanyDirectorsViewModel;
import com.erp.Company.Company_Directors.Repository.ICompanyDirectorsRepository;
import com.erp.Company.Company_Directors.Repository.ICompanyDirectorsViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@Service
public class CCompanyDirectorsServiceImpl implements ICompanyDirectorsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICompanyDirectorsRepository iCompanyDirectorsRepository;

	@Autowired
	ICompanyDirectorsViewRepository iCompanyDirectorsViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CCompanyDirectorsModel companydirectorsModel) {
		JSONObject resp = new JSONObject();
		int company_id = companydirectorsModel.getCompany_id();
		try {
			Optional<CCompanyDirectorsModel> option = iCompanyDirectorsRepository
					.findById(companydirectorsModel.getCompany_director_id());
			CCompanyDirectorsModel MyModel = null;

			if (option.isPresent()) {

				MyModel = iCompanyDirectorsRepository.save(companydirectorsModel);
				ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
				String json = mapper.writeValueAsString(MyModel);

				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated successfully!...");
				return resp;
			} else {

				CCompanyDirectorsModel directorsModel = iCompanyDirectorsRepository
						.checkIfExistDirectorName(companydirectorsModel.getCompany_director_name());

				if (directorsModel != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Director name is already exist!...");
					return resp;
				} else {

					ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
					iCompanyDirectorsRepository.save(companydirectorsModel);
					String json = mapper.writeValueAsString(companydirectorsModel);

					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added successfully!...");
					return resp;
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/companydirectors/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/companydirectors/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}
		return resp;
	}

	@Override
	public CCompanyDirectorsModel FnDeleteRecord(int company_director_id) {
		Optional<CCompanyDirectorsModel> option = iCompanyDirectorsRepository.findById(company_director_id);
		CCompanyDirectorsModel cCompanyDirectorsModel = new CCompanyDirectorsModel();
		if (option.isPresent()) {
			cCompanyDirectorsModel = option.get();
			cCompanyDirectorsModel.setIs_delete(true);
			cCompanyDirectorsModel.setDeleted_on(new Date());
			iCompanyDirectorsRepository.save(cCompanyDirectorsModel);

		}
		return cCompanyDirectorsModel;
	}

	@Override
	public Page<CCompanyDirectorsViewModel> FnShowAllActiveRecords(Pageable pageable) {
		return iCompanyDirectorsViewRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public Page<CCompanyDirectorsViewModel> FnShowParticularRecord(int company_id, Pageable pageable) {
		return iCompanyDirectorsViewRepository.FnShowParticularRecord(company_id, pageable);
	}

	@Override
	public Page<CCompanyDirectorsViewModel> FnShowAllRecords(Pageable pageable) {
		return iCompanyDirectorsViewRepository.FnShowAllRecords(pageable);
	}

	@Override
	public CCompanyDirectorsViewModel FnShowParticularRecordForUpdate(int company_director_id) {
		return iCompanyDirectorsViewRepository.FnShowParticularRecordForUpdate(company_director_id);
	}

}
