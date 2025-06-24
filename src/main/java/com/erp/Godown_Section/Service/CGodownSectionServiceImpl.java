package com.erp.Godown_Section.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Godown.Model.CGodownModel;
import com.erp.Godown_Section.Model.CGodownSectionModel;
import com.erp.Godown_Section.Model.CGodownSectionViewModel;
import com.erp.Godown_Section.Repository.IGodownSectionRepository;
import com.erp.Godown_Section.Repository.IGodownSectionViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class CGodownSectionServiceImpl implements IGodownSectionService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IGodownSectionRepository iGodownSectionRepository;

	@Autowired
	IGodownSectionViewRepository iGodownSectionViewRepository;


	@Override
	public Object FnDeleteRecord(int godown_section_id, String deleted_by) {
		return iGodownSectionRepository.FnDeleteRecord(godown_section_id, deleted_by);
	}

	@Override
	public JSONObject FnAddUpdateRecord(CGodownSectionModel cGodownSectionModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CGodownSectionModel MyModel = null;
		int company_id = cGodownSectionModel.getCompany_id();
		try {
			Optional<CGodownSectionModel> option = iGodownSectionRepository.findById(cGodownSectionModel.getGodown_section_id());

			if (option.isPresent()) {
				CGodownSectionModel mymodel = iGodownSectionRepository.save(cGodownSectionModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println("  Godown Section updated successfully!..");
				return resp;
			} else {
				//Check similar Godown Section Name,Godown Section short name is exist or not
				CGodownSectionModel resultsGodownSectionName = null;
				if (cGodownSectionModel.getGodown_section_short_name() == null ||cGodownSectionModel.getGodown_section_short_name().isEmpty()) {
					
					resultsGodownSectionName = iGodownSectionRepository.getCheck(cGodownSectionModel.getGodown_section_name(),
							null, cGodownSectionModel.getCompany_id());
				} else {
					resultsGodownSectionName = iGodownSectionRepository.getCheck(cGodownSectionModel.getGodown_section_name(),
							cGodownSectionModel.getGodown_section_short_name(),cGodownSectionModel.getCompany_id());
				}

				if (resultsGodownSectionName != null) {
					resp.put("success", 0);
					resp.put("error", "  Godown Section Name or Short Name is already exist!");
					return resp;
				}else {

					CGodownSectionModel GodownSectionModel = iGodownSectionRepository.save(cGodownSectionModel);
					String json = objectMapper.writeValueAsString(GodownSectionModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
					System.out.println(" Godown Section saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/godownsection/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/godownsection/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;


	}


	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CGodownSectionViewModel> data = iGodownSectionViewRepository.FnShowAllRecords(pageable);
			String json = objectMapper.writeValueAsString(data);
			resp.put("data", json);
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


	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CGodownSectionViewModel> data = iGodownSectionViewRepository.FnShowAllActiveRecords(pageable);
			String json = objectMapper.writeValueAsString(data);
			resp.put("data", json);
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


	@Override
	public JSONObject FnShowParticularRecordForUpdate(int godown_section_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CGodownSectionViewModel json = iGodownSectionViewRepository.FnShowParticularRecordForUpdate(godown_section_id);
			String json1 = objectMapper.writeValueAsString(json);
			resp.put("success", "1");
			resp.put("data", json1);
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}


	@Override
	public JSONObject FnShowParticularRecord(int company_id, int godown_section_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CGodownSectionViewModel json = iGodownSectionViewRepository.FnShowParticularRecord(company_id, godown_section_id);
			String json1 = objectMapper.writeValueAsString(json);

			resp.put("success", "1");
			resp.put("data", json1);
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) {
		return iGodownSectionViewRepository.FnShowAllReportRecords(pageable);

	}


}
