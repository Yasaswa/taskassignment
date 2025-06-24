package com.erp.JobType.Controller;

import com.erp.JobType.Model.CJobTypeModel;
import com.erp.JobType.Service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/jobType", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CJobTypeController {

	@Autowired
	JobTypeService jobTypeService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CJobTypeModel cJobTypeModel) {
		Map<String, Object> responce = jobTypeService.FnAddUpdateRecord(cJobTypeModel);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{job_type_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int job_type_id, @PathVariable String deleted_by) {
		return jobTypeService.FnDeleteRecord(job_type_id, deleted_by);

	}

//	@GetMapping("/FnShowParticularRecord/{company_id}/{job_type_id}")
//	public Map<String, Object> FnShowParticularRecord(@PathVariable int company_id, @PathVariable int job_type_id)
//			throws SQLException {
//		Map<String, Object> resp = jobTypeService.FnShowParticularRecord(company_id, job_type_id);
//		return resp;
//
//	}

	@GetMapping("/FnShowParticularRecord/{job_type_id}")
	public Map<String, Object> FnShowParticularRecord( @PathVariable int job_type_id)
			throws SQLException {
		Map<String, Object> resp = jobTypeService.FnShowParticularRecord(job_type_id);
		return resp;

	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Map<String, Object> cJobTypeRptModel = jobTypeService.FnShowAllReportRecords(pageable);
		return cJobTypeRptModel;

	}

}
