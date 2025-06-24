package com.erp.Godown.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Godown.Model.CGodownModel;
import com.erp.Godown.Model.CGodownViewModel;
import com.erp.Godown.Repository.IGodownRepository;
import com.erp.Godown.Repository.IGodownViewRepository;
import com.erp.Godown_Section.Model.CGodownSectionModel;
import com.erp.Godown_Section.Repository.IGodownSectionRepository;
import com.erp.Godown_Section_Beans.Model.CGodownSectionBeansModel;
import com.erp.Godown_Section_Beans.Repository.IGodownSectionBeansRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CGodownServiceImpl implements IGodownService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IGodownRepository iGodownRepository;

	@Autowired
	IGodownViewRepository iGodownViewRepository;
	
	@Autowired
	IGodownSectionRepository iGodownSectionRepository;
	
	@Autowired
	IGodownSectionBeansRepository iGodownSectionBeansRepository;

	
	@Override
	public JSONObject FnAddUpdateRecord(CGodownModel cGodownModel) {
		
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = cGodownModel.getCompany_id();
		
		try {
			Optional<CGodownModel> option = iGodownRepository.findById(cGodownModel.getGodown_id());

			if (option.isPresent()) {
				CGodownModel mymodel = iGodownRepository.save(cGodownModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success",1);
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				return resp;
				
			} else {
				//Check similar Godown Name,Godown short name is exist or not
				CGodownModel resultsGodownName = null;
				if (cGodownModel.getGodown_short_name() == null ||cGodownModel.getGodown_short_name().isEmpty()) {
					
					resultsGodownName = iGodownRepository.getCheck(cGodownModel.getGodown_name(),null, cGodownModel.getCompany_id());
				} else {
					resultsGodownName = iGodownRepository.getCheck(cGodownModel.getGodown_name(),cGodownModel.getGodown_short_name(),cGodownModel.getCompany_id());
				}

				if (resultsGodownName != null) {
					resp.put("success", 0);
					resp.put("error", "Godown Name or Short-Name is already exist!");
					return resp;
				}else {
					CGodownModel GodownModel = iGodownRepository.save(cGodownModel);
					
					CGodownSectionModel cGodownSectionModel = new CGodownSectionModel();
					cGodownSectionModel.setCompany_id(company_id);
					cGodownSectionModel.setCompany_branch_id(GodownModel.getCompany_branch_id());
					cGodownSectionModel.setGodown_id(GodownModel.getGodown_id());					
					cGodownSectionModel.setGodown_section_name("NA");
					cGodownSectionModel.setSection_beans_count(1);		
					cGodownSectionModel.setGodown_section_area(1.0000);
					CGodownSectionModel respGodownSectionModel = iGodownSectionRepository.save(cGodownSectionModel);
					
					CGodownSectionBeansModel cGodownSectionBeansModel = new CGodownSectionBeansModel();
					cGodownSectionBeansModel.setCompany_id(company_id);		
					cGodownSectionBeansModel.setCompany_branch_id(GodownModel.getCompany_branch_id());
					cGodownSectionBeansModel.setGodown_id(respGodownSectionModel.getGodown_id());
					cGodownSectionBeansModel.setGodown_section_id(respGodownSectionModel.getGodown_section_id());
					cGodownSectionBeansModel.setGodown_section_beans_name("NA");			
					cGodownSectionBeansModel.setGodown_section_beans_area(1.0000);
					iGodownSectionBeansRepository.save(cGodownSectionBeansModel);					
					
					String json = objectMapper.writeValueAsString(GodownModel);
					resp.put("success", 1);
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/godown/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/godown/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;


	}

	@Override
	public Object FnDeleteRecord(int godown_id, String deleted_by) {
		return iGodownRepository.FnDeleteRecord(godown_id, deleted_by);
	}


	@Override
	public JSONObject FnShowParticularRecordForUpdate(int godown_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();

		try {

			CGodownViewModel json = iGodownViewRepository.FnShowParticularRecordForUpdate(godown_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int godown_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CGodownViewModel json = iGodownViewRepository.FnShowParticularRecord(company_id, godown_id);
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
		return iGodownViewRepository.FnShowAllReportRecords(pageable);
	}


	


	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CGodownViewModel> data = iGodownViewRepository.FnShowAllRecords(pageable);
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
			Page<CGodownViewModel> data = iGodownViewRepository.FnShowAllActiveRecords(pageable);
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


}
