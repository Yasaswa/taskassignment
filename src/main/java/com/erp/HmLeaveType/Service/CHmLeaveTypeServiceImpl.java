package com.erp.HmLeaveType.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HmLeaveType.Model.CHmLeaveTypeModel;
import com.erp.HmLeaveType.Repository.IHmLeaveTypeRepository;
import com.erp.HmLeaveType.Repository.IHmLeaveTypeViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CHmLeaveTypeServiceImpl implements IHmLeaveTypeService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IHmLeaveTypeRepository iHmLeaveTypeRepository;

	@Autowired
	IHmLeaveTypeViewRepository iHmLeaveTypeViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CHmLeaveTypeModel cHmLeaveTypeModel) {
		Map<String, Object> responce = new HashMap<>();

		Optional<CHmLeaveTypeModel> option = iHmLeaveTypeRepository.findById(cHmLeaveTypeModel.getLeave_type_id());
		int company_id = cHmLeaveTypeModel.getCompany_id();

		try {
			if (option.isPresent()) {

				CHmLeaveTypeModel cHmleaveTypeModel = iHmLeaveTypeRepository.save(cHmLeaveTypeModel);
				responce.put("success", 1);
				responce.put("data", cHmleaveTypeModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" Leave Type Updated Successfully!..");
			} else {
				CHmLeaveTypeModel model = iHmLeaveTypeRepository
						.checkIfNameExist(cHmLeaveTypeModel.getLeave_type_name());

				if (model != null) {
					responce.put("success", 0);
					responce.put("data", "");
					responce.put("error", " Leave Type  is already exist!");

					return responce;

				} else {

					CHmLeaveTypeModel respContent = iHmLeaveTypeRepository.save(cHmLeaveTypeModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HmLeaveType/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HmLeaveType/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int leave_type_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iHmLeaveTypeRepository.FnDeleteRecord(leave_type_id, deleted_by);
			responce.put("success", 1);
		} catch (Exception e) {
			responce.put("success", 0);
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int leave_type_id) {
		Map<String, Object> responce = new HashMap<>();
		CHmLeaveTypeModel cHmLeaveTypeModel = null;
		try {
			cHmLeaveTypeModel = iHmLeaveTypeRepository.FnShowParticularRecordForUpdate(company_id, leave_type_id);
			responce.put("success", "1");
			responce.put("data", cHmLeaveTypeModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

//	@Override
//	public Page<CHmLeaveTypeViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
//		return iHmLeaveTypeViewRepository.FnShowAllActiveRecords(pageable,company_id);
//	}

//	@Override
//	public Page<CHmLeaveTypeRptModel> FnShowAllReportRecords(Pageable pageable, int company_id) {
//		return iHmLeaveTypeRptRepository.FnShowAllReportRecords(pageable, company_id);
//	}

}
