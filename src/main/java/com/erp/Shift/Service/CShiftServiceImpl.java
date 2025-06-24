package com.erp.Shift.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Shift.Model.CShiftModel;
import com.erp.Shift.Model.CShiftViewModel;
import com.erp.Shift.Repository.IShiftRepository;
import com.erp.Shift.Repository.IShiftViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CShiftServiceImpl implements IShiftService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IShiftRepository iShiftRepository;

	@Autowired
	IShiftViewRepository iShiftViewRepository;


	@Override
	public Map<String, Object> FnAddUpdateRecord(CShiftModel cShiftModel) {
		Map<String, Object> resp = new HashMap<>();
		CShiftModel MyModel = null;
		int company_id = cShiftModel.getCompany_id();
		try {
			Optional<CShiftModel> option = iShiftRepository.findById(cShiftModel.getShift_id());

			if (option.isPresent()) {
				CShiftModel mymodel = iShiftRepository.save(cShiftModel);
				resp.put("success", "1");
				resp.put("data", mymodel);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
			} else {
				CShiftModel model = iShiftRepository.getCheck(cShiftModel.getShift_name(), cShiftModel.getCompany_id());
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Shift Name is already exist!...");
				} else {

					CShiftModel ShiftModel = iShiftRepository.save(cShiftModel);
					resp.put("success", "1");
					resp.put("data", ShiftModel);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/shift/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/shift/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int shift_id, int company_id, String deleted_by) {
		return iShiftRepository.FnDeleteRecord(shift_id, company_id, deleted_by);
	}

	@Override
	public Map<String, Object> FnShowAllRecords(Pageable pageable, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CShiftViewModel> data = iShiftViewRepository.FnShowAllRecords(pageable, company_id);
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

	@Override
	public Map<String, Object> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CShiftViewModel> data = iShiftViewRepository.FnShowAllActiveRecords(pageable, company_id);
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

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int shift_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			CShiftViewModel json = iShiftViewRepository.FnShowParticularRecordForUpdate(shift_id);
			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

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
	public Map<String, Object> FnShowParticularRecord(int company_id, int shift_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			CShiftViewModel json = iShiftViewRepository.FnShowParticularRecord(company_id, shift_id);
			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

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
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<Map<String, Object>> data = iShiftViewRepository.FnShowAllReportRecords(pageable);
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
