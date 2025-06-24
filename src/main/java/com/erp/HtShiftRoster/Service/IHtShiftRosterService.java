package com.erp.HtShiftRoster.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface IHtShiftRosterService {

    List<Map<String, Object>> FnShowShiftRosterEmployeeData(JSONObject jsonObject);
}