package com.erp.HmAttendanceStatus.Service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.erp.HmAttendanceStatus.Model.CHmAttendanceStatusModel;

import com.erp.HmAttendanceStatus.Repository.IHmAttendanceStatusRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CHmAttendanceStatusServiceImpl implements IHmAttendanceStatusService {

//	@Autowired
//	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
//
//	@Autowired
//	IHmAttendanceStatusRepository iHmAttendanceStatusRepository;
//
//	@Autowired
//	IHmAttendanceStatusViewRepository iHmAttendanceStatusViewRepository;
//
//	@Autowired
//	IHmAttendanceStatusRptRepository iHmAttendanceStatusRptRepository;
//
//	@Override
//	public Map<String, Object> FnAddUpdateRecord(CHmAttendanceStatusModel cHmAttendanceStatusModel) {
//		Map<String, Object> responce = new HashMap<>();
//		Optional<CHmAttendanceStatusModel> option = iHmAttendanceStatusRepository
//				.findById(cHmAttendanceStatusModel.getcompany_id());
//		CHmAttendanceStatusModel MyModel = null;
//		int company_id = cHmAttendanceStatusModel.getCompany_id();
//		try {
//			if (option.isPresent()) {
//			    cHmAttendanceStatusModel.setModified_on(new Date());
//				CHmAttendanceStatusModel cHmAttendanceStatusModel = iHmAttendanceStatusRepository.save(cHmAttendanceStatusModel);
//				responce.put("success", "1");
//				responce.put("data", cHmAttendanceStatusModel);
//				responce.put("error", "");
//				responce.put("message", "Record updated successfully!...");
//				System.out.println(" HmAttendanceStatus Updated Successfully!..");
//			} else {
//				CHmAttendanceStatusModel model = iHmAttendanceStatusRepository
//						.checkIfNameExist(cHmAttendanceStatusModel.getSupp_branch_name());
//
//				if (model != null) {
//					responce.put("success", "0");
//					responce.put("data", "");
//					responce.put("error", " HmAttendanceStatus  is already exist!");
//
//					return responce;
//
//				} else {
//
//					CHmAttendanceStatusModel respContent = iHmAttendanceStatusRepository.save(cHmAttendanceStatusModel);
//
//					responce.put("success", "1");
//					responce.put("data", respContent);
//					responce.put("error", "");
//					responce.put("message", "Record added successfully!...");
//				}
//			}
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord",
//						sqlEx.getErrorCode(), sqlEx.getMessage());
//				System.out.println(sqlEx.getMessage());
//				responce.put("success", "0");
//				responce.put("data", "");
//				responce.put("error", e.getMessage());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord", 0,
//					e.getMessage());
//			responce.put("success", "0");
//			responce.put("data", "");
//			responce.put("error", e.getMessage());
//
//		}
//
//		return responce;
//
//	}
//
//
//
//	@Override
//	public Object FnDeleteRecord(int company_id, int company_id) {
//	Optional<CHmAttendanceStatusModel> option = iHmAttendanceStatusRepository.findById(company_id);
//		CHmAttendanceStatusModel cHmAttendanceStatusModel = new CHmAttendanceStatusModel();
//		if(option.isPresent()) {
//			cHmAttendanceStatusModel = option.get();
//			cHmAttendanceStatusModel.setIs_delete(true);
//			cHmAttendanceStatusModel.setDeleted_on(new Date());
//			iHmAttendanceStatusRepository.save(cHmAttendanceStatusModel);
//
//		}
//		return cHmAttendanceStatusModel;
//	}
//
//	@Override
//	public Page<CHmAttendanceStatusViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
//		return iHmAttendanceStatusViewRepository.FnShowAllActiveRecords(pageable);
//	}
//
//	@Override
//	public Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int company_id) {
//		Map<String, Object> responce = new HashMap<>();
//		CHmAttendanceStatusModel cHmAttendanceStatusModel = null;
//		try {
//			cHmAttendanceStatusModel  = iHmAttendanceStatusRepository.FnShowParticularRecordForUpdate(company_id, company_id);
//			responce.put("success", "1");
//			responce.put("data", cHmAttendanceStatusModel);
//			responce.put("error", "");
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				responce.put("success", "0");
//				responce.put("data", "");
//				responce.put("error", e.getMessage());
//				return responce;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			responce.put("success", "0");
//			responce.put("data", "");
//			responce.put("error", e.getMessage());
//            return responce;
//		}
//	return responce;
//	}
//
//	@Override
//	public Page<CHmAttendanceStatusRptModel> FnShowAllReportRecords(Pageable pageable, int company_id) {
//		return iHmAttendanceStatusRptRepository.FnShowAllReportRecords(pageable, company_id);
//	}
//
//	@Override
//	public Page<CHmAttendanceStatusViewModel> FnShowParticularRecord(int company_id, Pageable pageable, int company_id) {
//		return iHmAttendanceStatusViewRepository.FnShowParticularRecord(company_id, pageable, company_id);
//	}
	
	}
