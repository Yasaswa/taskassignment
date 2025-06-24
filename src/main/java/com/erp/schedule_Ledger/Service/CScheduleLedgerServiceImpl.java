package com.erp.schedule_Ledger.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.schedule_Ledger.Model.CScheduleLedgerModel;
import com.erp.schedule_Ledger.Model.CScheduleLedgerViewModel;
import com.erp.schedule_Ledger.Repository.IScheduleLedgerRepository;
import com.erp.schedule_Ledger.Repository.IScheduleLedgerViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CScheduleLedgerServiceImpl implements IScheduleLedgerService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IScheduleLedgerRepository iScheduleLedgerRepository;

	@Autowired
	IScheduleLedgerViewRepository iScheduleLedgerViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CScheduleLedgerModel cScheduleLedgerModel) {
		Map<String, Object> resp = new HashMap<>();
		CScheduleLedgerModel MyModel = null;
		int company_id = cScheduleLedgerModel.getCompany_id();
		try {
			Optional<CScheduleLedgerModel> option = iScheduleLedgerRepository
					.findById(cScheduleLedgerModel.getSchedule_ledger_id());

			if (option.isPresent()) {
				cScheduleLedgerModel.setModified_on(new Date());
				CScheduleLedgerModel mymodel = iScheduleLedgerRepository.save(cScheduleLedgerModel);
				resp.put("success", "1");
				resp.put("data", mymodel);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
			} else {
				CScheduleLedgerModel model = iScheduleLedgerRepository
						.getCheck(cScheduleLedgerModel.getSchedule_ledger_name(), company_id);
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Schedule Ledger Name is already exist!");
				} else {

					CScheduleLedgerModel ScheduleLedgerModel = iScheduleLedgerRepository.save(cScheduleLedgerModel);
					resp.put("success", "1");
					resp.put("data", ScheduleLedgerModel);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/scheduleledger/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/scheduleledger/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int schedule_ledger_id, int company_id, String deleted_by) {
		return iScheduleLedgerRepository.FnDeleteRecord(schedule_ledger_id, company_id, deleted_by);
	}

	@Override
	public Map<String, Object> FnShowAllRecords(Pageable pageable, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CScheduleLedgerViewModel> data = iScheduleLedgerViewRepository.FnShowAllRecords(pageable, company_id);
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
			Page<CScheduleLedgerViewModel> data = iScheduleLedgerViewRepository.FnShowAllActiveRecords(pageable,
					company_id);
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int schedule_ledger_id, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {

			CScheduleLedgerViewModel json = iScheduleLedgerViewRepository
					.FnShowParticularRecordForUpdate(schedule_ledger_id, company_id);
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
	public Map<String, Object> FnShowParticularRecord(int schedule_ledger_id, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {

			CScheduleLedgerViewModel json = iScheduleLedgerViewRepository.FnShowParticularRecord(schedule_ledger_id,
					company_id);

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
			Page<Map<String, Object>> data = iScheduleLedgerViewRepository.FnShowAllReportRecords(pageable,
					company_id);
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
	public Map<String, Object> FnShowScheduleLedgerSrNo(String report_side, String report_type, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			int max_sr_no = iScheduleLedgerRepository.FnShowScheduleLedgerSrNo(report_side, report_type, company_id);
			responce.put("data", max_sr_no);
			responce.put("success", "1");
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("data", "");
				responce.put("success", "0");
				responce.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			responce.put("data", "");
			responce.put("success", "0");
			responce.put("error", "");
			e.printStackTrace();
		}
		return responce;
	}

}
