package com.erp.XmProductionLotMaster.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

import com.erp.XmProductionLotMaster.Model.CXmProductionLotMasterModel;
import com.erp.XmProductionLotMaster.Model.CXmProductionLotMasterViewModel;
import com.erp.XmProductionLotMaster.Service.IXmProductionLotMasterService;
import com.erp.XmProductionLotMaster.Service.CXmProductionLotMasterServiceImpl;
import com.erp.security.auth.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/XmProductionLotMaster")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXmProductionLotMasterController {

	@Autowired
	IXmProductionLotMasterService iXmProductionLotMasterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CXmProductionLotMasterModel XmProductionLotMasterModel) {
		return iXmProductionLotMasterService.FnAddUpdateRecord(XmProductionLotMasterModel);

	}

	@DeleteMapping("/FnDeleteRecord/{lot_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int lot_id, @PathVariable int company_id,@PathVariable String deleted_by) {
		return iXmProductionLotMasterService.FnDeleteRecord(lot_id, company_id,deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{lot_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int lot_id, @PathVariable int company_id)  {
		return  iXmProductionLotMasterService.FnShowParticularRecordForUpdate(lot_id, company_id);
	}

	

 	
 
}
