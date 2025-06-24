package com.erp.JobType.Service;


import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.JobType.Model.CJobTypeModel;
import com.erp.JobType.Model.CJobTypeViewModel;
import com.erp.JobType.Repository.IJobTypeRepository;
import com.erp.JobType.Repository.IJobTypeViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CJobTypeServiceImpl implements JobTypeService {

	@Autowired
	IJobTypeRepository iJobTypeRepository;

	@Autowired
	IJobTypeViewRepository iJobTypeViewRepository;

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CJobTypeModel cJobTypeModel) {
		Map<String, Object> resp = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		CJobTypeModel MyModel = null;
		int company_id = cJobTypeModel.getCompany_id();

		try {
			CJobTypeModel resultsJobTypeName = null;
			Optional<CJobTypeModel> option = iJobTypeRepository.findById(cJobTypeModel.getJob_type_id());

			if (option.isPresent()) {
				resultsJobTypeName = iJobTypeRepository.getCheckForUpdate(cJobTypeModel.getJob_type_name(),
						 cJobTypeModel.getJob_type_id());

				if (resultsJobTypeName != null && cJobTypeModel.getJob_type_id() > 0) {
					resp.put("success", 0);
					resp.put("error", "Job Type Name or Short Name already exists!");
					return resp;
				}else {
					cJobTypeModel.setModified_on(new Date());
					CJobTypeModel mymodel = iJobTypeRepository.save(cJobTypeModel);
					String json = objectMapper.writeValueAsString(mymodel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record updated succesfully!...");
					return resp;
				}
			}else{
//			    Check similar Job Type Name or short name is exist or not

//				if (cJobTypeModel.getJob_type_short_name() == null || cJobTypeModel.getJob_type_short_name().isEmpty()) {
//
//					resultsJobTypeName = iJobTypeRepository.getCheck(cJobTypeModel.getJob_type_name());
//				} else {
//					resultsJobTypeName = iJobTypeRepository.getCheck(cJobTypeModel.getJob_type_name(),
//							cJobTypeModel.getJob_type_short_name());
//				}

				resultsJobTypeName = iJobTypeRepository.getCheck(cJobTypeModel.getJob_type_name());

				if (resultsJobTypeName != null) {
					resp.put("success", 0);
					resp.put("error", "Job Type Name or Short Name is already exist!");
					return resp;
				} else {

					CJobTypeModel cJobtypeModel = iJobTypeRepository.save(cJobTypeModel);
					String json = objectMapper.writeValueAsString(cJobtypeModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");

					System.out.println(" Job Type saved succesfully!..");
					return resp;
				}
			}



		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/jobType/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/jobType/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int job_type_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iJobTypeRepository.FnDeleteRecord(job_type_id, deleted_by);
			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", "");
		}
		return responce;
	}

//	@Override
//	public Map<String, Object> FnShowParticularRecord(int company_id, int job_type_id) {
//		Map<String, Object> resp = new HashMap();
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//
//			CJobTypeViewModel json = iJobTypeViewRepository.FnShowParticularRecord(company_id, job_type_id);
//			String json1 = objectMapper.writeValueAsString(json);
//
//			resp.put("success", "1");
//			resp.put("data", json1);
//			resp.put("error", "");
//
//			return resp;
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return resp;
//	}
@Override
public Map<String, Object> FnShowParticularRecord(int job_type_id) {
	Map<String, Object> resp = new HashMap();
	ObjectMapper objectMapper = new ObjectMapper();
	try {

		CJobTypeViewModel json = iJobTypeViewRepository.FnShowParticularRecord(job_type_id);
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
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {

			Map<String, Object> data = iJobTypeViewRepository.FnShowAllReportRecords();
			resp.put("data", data);
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
