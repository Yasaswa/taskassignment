package com.erp.HtShiftRosterNew.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.erp.HtShiftRosterNew.Model.CHtShiftRosterNewModel;

public interface IHtShiftRosterNewService {

	List<Map<String, Object>> FnShowShiftRosterEmployeeData(JSONObject jsonObject);
	Map<String, Object> FnAddUpdateShiftRosterDetails(JSONObject jsonObject) throws JsonProcessingException;

}
