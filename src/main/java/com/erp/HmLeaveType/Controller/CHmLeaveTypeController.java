package com.erp.HmLeaveType.Controller;

import com.erp.HmLeaveType.Model.CHmLeaveTypeModel;
import com.erp.HmLeaveType.Service.IHmLeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/HmLeaveType")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmLeaveTypeController {

	@Autowired
	IHmLeaveTypeService iHmLeaveTypeService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CHmLeaveTypeModel HmLeaveTypeModel) {
		Map<String, Object> resp = new HashMap<>();
		resp = iHmLeaveTypeService.FnAddUpdateRecord(HmLeaveTypeModel);
		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{leave_type_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int leave_type_id, @PathVariable String deleted_by) {
		Map<String, Object> resp = iHmLeaveTypeService.FnDeleteRecord(leave_type_id, deleted_by);
		return resp;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{leave_type_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id,
	                                                           @PathVariable int leave_type_id) {
		return iHmLeaveTypeService.FnShowParticularRecordForUpdate(company_id, leave_type_id);
	}

//	@GetMapping("/FnShowAllActiveRecords/{company_id}")
//	public Page<CHmLeaveTypeViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id)
//			throws SQLException {
//		Page<CHmLeaveTypeViewModel> cHmLeaveTypeViewModel = iHmLeaveTypeService.FnShowAllActiveRecords(pageable,
//				company_id);
//		return cHmLeaveTypeViewModel;
//	}

}
