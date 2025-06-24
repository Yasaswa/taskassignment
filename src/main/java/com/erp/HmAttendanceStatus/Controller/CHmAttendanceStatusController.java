package com.erp.HmAttendanceStatus.Controller;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.HmAttendanceStatus.Model.CHmAttendanceStatusModel;

import com.erp.HmAttendanceStatus.Service.IHmAttendanceStatusService;
import com.erp.HmAttendanceStatus.Service.CHmAttendanceStatusServiceImpl;
import com.erp.security.auth.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/HmAttendanceStatus")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmAttendanceStatusController {

//	@Autowired
//	IHmAttendanceStatusService iHmAttendanceStatusService;
//
//	@PostMapping("/FnAddUpdateRecord")
//	public Map<String, Object> FnAddUpdateRecord(@RequestBody CHmAttendanceStatusModel HmAttendanceStatusModel) {
//		return iHmAttendanceStatusService.FnAddUpdateRecord(HmAttendanceStatusModel);
//
//	}
//
//	@DeleteMapping("/FnDeleteRecord/{company_id}/{company_id}")
//	public Object FnDeleteRecord(@PathVariable int company_id, @PathVariable int company_id) {
//		return iHmAttendanceStatusService.FnDeleteRecord(company_id, company_id);
//
//	}
//
//	@GetMapping("/FnShowAllActiveRecords/{company_id}")
//	public Page<CHmAttendanceStatusViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
//		Page<CHmAttendanceStatusViewModel> cHmAttendanceStatusViewModel = iHmAttendanceStatusService.FnShowAllActiveRecords(pageable, company_id);
//		return cHmAttendanceStatusViewModel;
//	}
//
//	@GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{company_id}")
//	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id, @PathVariable int company_id)  {
//		return  iHmAttendanceStatusService.FnShowParticularRecordForUpdate(company_id, company_id);
//	}
//
//	@GetMapping("/FnShowAllReportRecords/{company_id}")
//	public Page<CHmAttendanceStatusRptModel> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
//		return iHmAttendanceStatusService.FnShowAllReportRecords(pageable, company_id);
//
//	}
//
//	@GetMapping("/FnShowParticularRecords/{company_id}/{company_id}")
//	public Page<CHmAttendanceStatusViewModel> FnShowParticularRecord(@PathVariable int company_id, Pageable pageable, @PathVariable int company_id){
//		return iHmAttendanceStatusService.FnShowParticularRecord(company_id, pageable, company_id);
//
//	}
//
//

}
