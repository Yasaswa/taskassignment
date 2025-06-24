package com.erp.HtShiftRosterNew.Controller;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import javax.sql.DataSource;

import com.erp.HtShiftRoster.Model.CHtShiftRosterModel;
import com.erp.HtShiftRosterNew.Repository.IHtShiftRosterNewRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
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

import com.erp.HtShiftRosterNew.Model.CHtShiftRosterNewModel;
import com.erp.HtShiftRosterNew.Service.IHtShiftRosterNewService;
import com.erp.HtShiftRosterNew.Service.CHtShiftRosterNewServiceImpl;
import com.erp.security.auth.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/HtShiftRosterNew")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHtShiftRosterNewController {

	@Autowired
	IHtShiftRosterNewService iHtShiftRosterNewService;

	@Autowired
	IHtShiftRosterNewRepository iHtShiftRosterNewRepository;

	@PostMapping("/FnShowShiftRosterEmployeeData")
	public List<Map<String, Object>> FnShowShiftRosterEmployeeData(@RequestParam("prevShiftDetails") String prevShiftDetails) {
		JSONObject jsonObject = new JSONObject(prevShiftDetails); // Convert the string to a JSONObject
		return iHtShiftRosterNewService.FnShowShiftRosterEmployeeData(jsonObject);
	}

	@PostMapping("/FnAddUpdateShiftRosterDetails")
	public Map<String, Object> FnAddUpdateShiftRosterDetails(@RequestParam("shiftRosterDetails") String getShiftRosterDetailsStr) throws JsonProcessingException {
		JSONObject jsonObject = new JSONObject(getShiftRosterDetailsStr); // Convert the string to a JSONObject
		return iHtShiftRosterNewService.FnAddUpdateShiftRosterDetails(jsonObject);
	}
}
