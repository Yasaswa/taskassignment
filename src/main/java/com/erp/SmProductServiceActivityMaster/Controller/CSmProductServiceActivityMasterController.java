package com.erp.SmProductServiceActivityMaster.Controller;


import java.sql.SQLException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.SmProductServiceActivityMaster.Model.CSmProductServiceActivityMasterModel;
import com.erp.SmProductServiceActivityMaster.Service.ISmProductServiceActivityMasterService;

@RestController
@RequestMapping("/api/SmProductServiceActivityMaster")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductServiceActivityMasterController {

	@Autowired
	ISmProductServiceActivityMasterService iSmProductServiceActivityMasterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CSmProductServiceActivityMasterModel SmProductServiceActivityMasterModel) {
		 Map<String, Object> responce = iSmProductServiceActivityMasterService.FnAddUpdateRecord(SmProductServiceActivityMasterModel);
		 return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{product_service_activity_master_id}/{deleted_by}")
	public Map<String, Object>  FnDeleteRecord(@PathVariable int product_service_activity_master_id, @PathVariable String deleted_by) {
		return iSmProductServiceActivityMasterService.FnDeleteRecord(product_service_activity_master_id, deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_service_activity_master_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_service_activity_master_id, @PathVariable int company_id)  {
		 Map<String, Object> responce = iSmProductServiceActivityMasterService.FnShowParticularRecordForUpdate(product_service_activity_master_id, company_id);
		 return responce;
	 
	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Map<String, Object> FnShowAllActiveRecords(@PathVariable int company_id) throws SQLException {
		Map<String, Object> activeRecrods = iSmProductServiceActivityMasterService.FnShowAllActiveRecords(company_id);
		return activeRecrods;
		
	}
 
}
