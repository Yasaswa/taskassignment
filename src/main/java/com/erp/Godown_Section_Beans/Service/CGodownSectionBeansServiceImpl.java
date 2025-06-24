package com.erp.Godown_Section_Beans.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Godown_Section.Model.CGodownSectionModel;
import com.erp.Godown_Section_Beans.Model.CGodownSectionBeansModel;
import com.erp.Godown_Section_Beans.Model.CGodownSectionBeansViewModel;
import com.erp.Godown_Section_Beans.Repository.IGodownSectionBeansRepository;
import com.erp.Godown_Section_Beans.Repository.IGodownSectionBeansViewRepository;
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
public class CGodownSectionBeansServiceImpl implements IGodownSectionBeansService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IGodownSectionBeansRepository iGodownSectionBeansRepository;

	@Autowired
	IGodownSectionBeansViewRepository iGodownSectionBeansViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(CGodownSectionBeansModel cGodownSectionBeansModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CGodownSectionBeansModel MyModel = null;
		int company_id = cGodownSectionBeansModel.getCompany_id();
		try {
			Optional<CGodownSectionBeansModel> option = iGodownSectionBeansRepository
					.findById(cGodownSectionBeansModel.getGodown_section_beans_id());

			if (option.isPresent()) {
				CGodownSectionBeansModel mymodel = iGodownSectionBeansRepository.save(cGodownSectionBeansModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
			} else {
//				CGodownSectionBeansModel model = iGodownSectionBeansRepository
//						.getCheck(cGodownSectionBeansModel.getGodown_section_beans_name());
//				if (model != null) {
//					resp.put("success", "0");
//					resp.put("data", "");
//					resp.put("error", "  Godown Section Beans  Name is already exist!");
//
//				} 
				//Check similar Godown Section Beans Name and short name is exist or not
				CGodownSectionBeansModel resultsGodownSectionBeansName = null;
				if (cGodownSectionBeansModel.getGodown_section_beans_short_name() == null ||cGodownSectionBeansModel.getGodown_section_beans_short_name().isEmpty()) {
					resultsGodownSectionBeansName = iGodownSectionBeansRepository.getCheck(cGodownSectionBeansModel.getGodown_section_beans_name(),
							null, cGodownSectionBeansModel.getCompany_id());
				} else {
					resultsGodownSectionBeansName = iGodownSectionBeansRepository.getCheck(cGodownSectionBeansModel.getGodown_section_beans_name(),
							cGodownSectionBeansModel.getGodown_section_beans_short_name(),cGodownSectionBeansModel.getCompany_id());
				}

				if (resultsGodownSectionBeansName != null) {
					resp.put("success", 0);
					resp.put("error", "  Godown Section Name or Short Name is already exist!");
					return resp;
				}else {

					CGodownSectionBeansModel GodownSectionBeansModel = iGodownSectionBeansRepository
							.save(cGodownSectionBeansModel);
					String json = objectMapper.writeValueAsString(GodownSectionBeansModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/godownsectionbeans/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/godownsectionbeans/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}
		return resp;

	}


	@Override
	public Object FnDeleteRecord(int godown_section_beans_id, String deleted_by) {
		return iGodownSectionBeansRepository.FnDeleteRecord(godown_section_beans_id, deleted_by);
	}

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CGodownSectionBeansViewModel> data = iGodownSectionBeansViewRepository
					.FnShowAllActiveRecords(pageable);
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
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CGodownSectionBeansViewModel> data = iGodownSectionBeansViewRepository.FnShowAllRecords(pageable);
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
	public JSONObject FnShowParticularRecordForUpdate(int godown_section_beans_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CGodownSectionBeansViewModel json = iGodownSectionBeansViewRepository
					.FnShowParticularRecordForUpdate(godown_section_beans_id);
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
		return iGodownSectionBeansViewRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public JSONObject FnShowParticularRecord(int company_id, int godown_section_beans_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CGodownSectionBeansViewModel json = iGodownSectionBeansViewRepository.FnShowParticularRecord(company_id,
					godown_section_beans_id);
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

}
