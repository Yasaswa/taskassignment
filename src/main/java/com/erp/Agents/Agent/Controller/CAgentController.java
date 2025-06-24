package com.erp.Agents.Agent.Controller;

import com.erp.Agents.Agent.Service.IAgentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAgentController {

	@Autowired
	IAgentService iAgentService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("TransAgentData") JSONObject jsonObject) throws SQLException {
		return iAgentService.FnAddUpdateRecord(jsonObject);
	}

	@DeleteMapping("/FnDeleteRecord/{agent_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int agent_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iAgentService.FnDeleteRecord(agent_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		JSONObject cAgentViewModel = new JSONObject();
		cAgentViewModel = iAgentService.FnShowAllRecords(pageable);
		return cAgentViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		JSONObject cAgentViewModel = new JSONObject();
		cAgentViewModel = iAgentService.FnShowAllActiveRecords(pageable);

		return cAgentViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{agent_id}/{company_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int agent_id, @PathVariable int company_id) throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iAgentService.FnShowParticularRecordForUpdate(agent_id, company_id);

		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{agent_id}")
	public Map<String, Object> FnShowParticularRecord(@PathVariable int company_id,
	                                                  @PathVariable int agent_id) throws SQLException {
		return iAgentService.FnShowParticularRecord(company_id, agent_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object CAgentRptModel = null;
		CAgentRptModel = iAgentService.FnShowAllReportRecords(pageable);

		return CAgentRptModel;

	}

	@PostMapping("/FnGenerateCode/{company_id}")
	public Object FnShowParticularRecord(@RequestParam("data") JSONObject json, @PathVariable int company_id)
			throws SQLException {
		String jsonData = json.toString();
		String tblName = json.getString("tblName");
		String fieldName = json.getString("fieldName");
		int length = json.getInt("Length");

		String autoNumber = "";
		int diffLength = 0;
		String strAppend = "";

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT ");
		queryBuilder.append("COALESCE(MAX(CAST(SUBSTRING(" + fieldName + ", LENGTH(" + fieldName + ") - " + length + " + 1, " + length + ") AS SIGNED))+1, 1)");
		queryBuilder.append("FROM " + tblName);
		queryBuilder.append(" WHERE company_id = '" + company_id + "' ");

		Integer maxNumber = jdbcTemplate.queryForObject(queryBuilder.toString(), Integer.class);
		autoNumber = maxNumber.toString();
		System.out.println("Agent Code Generation Query : " + queryBuilder.toString());

		if (length > autoNumber.length())
			diffLength = length - autoNumber.length();
		else if (length < autoNumber.length())
			diffLength = autoNumber.length() - length;
		if (diffLength > 0) {
			for (int recCnt = 0; recCnt < diffLength; recCnt++)
				strAppend = "0" + strAppend;
		}
		autoNumber = strAppend + autoNumber;
		return autoNumber;
	}

}
