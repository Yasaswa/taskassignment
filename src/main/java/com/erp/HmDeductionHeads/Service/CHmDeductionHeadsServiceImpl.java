package com.erp.HmDeductionHeads.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsModel;
import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsViewModel;
import com.erp.HmDeductionHeads.Repository.IHmDeductionHeadsRepository;
import com.erp.HmDeductionHeads.Repository.IHmDeductionHeadsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CHmDeductionHeadsServiceImpl implements IHmDeductionHeadsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IHmDeductionHeadsRepository iHmDeductionHeadsRepository;

	@Autowired
	IHmDeductionHeadsViewRepository iHmDeductionHeadsViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CHmDeductionHeadsModel chmDeductionHeadsModel) {
		Map<String, Object> responce = new HashMap<>();
		int company_id = chmDeductionHeadsModel.getCompany_id();
		try {
			Optional<CHmDeductionHeadsModel> option = iHmDeductionHeadsRepository
					.findById(chmDeductionHeadsModel.getDeduction_heads_id());

			CHmDeductionHeadsModel EarningheadsModel = new CHmDeductionHeadsModel();
			if (option.isPresent()) {
				EarningheadsModel = option.get();

				EarningheadsModel.setModified_on(new Date());
				EarningheadsModel.setDeleted_on(new Date());
				EarningheadsModel.setIs_delete(true);
				iHmDeductionHeadsRepository.save(EarningheadsModel);
				chmDeductionHeadsModel.setDeduction_head_version(EarningheadsModel.getDeduction_head_version() + 1);

				chmDeductionHeadsModel.setDeduction_heads_id(0);
				CHmDeductionHeadsModel respContent = iHmDeductionHeadsRepository.save(chmDeductionHeadsModel);
				responce.put("success", "1");
				responce.put("data", respContent);
				responce.put("error", "");
				responce.put("message",
						option.isPresent() ? "Record updated successfully!" : "Record added successfully!");
				System.out.println(" HmDeductionHeads Updated Successfully!..");
			}

//			Check similar Deduction Head Name or short name is exist or not

			CHmDeductionHeadsModel resultsDeductionHeadName = null;
			if (chmDeductionHeadsModel.getDeduction_heads_id() == 0) {
				if (chmDeductionHeadsModel.getDeduction_head_short_name() == null
						|| chmDeductionHeadsModel.getDeduction_head_short_name().isEmpty()) {

					resultsDeductionHeadName = iHmDeductionHeadsRepository.getCheck(
							chmDeductionHeadsModel.getDeduction_head_name(), null, chmDeductionHeadsModel.getCompany_id());
				} else {
					resultsDeductionHeadName = iHmDeductionHeadsRepository.getCheck(
							chmDeductionHeadsModel.getDeduction_head_name(),
							chmDeductionHeadsModel.getDeduction_head_short_name(), chmDeductionHeadsModel.getCompany_id());
				}

				if (resultsDeductionHeadName != null) {
					responce.put("success", 0);
					responce.put("error", "Deduction Head Name or Short Name is already exist!");
					return responce;
				} else {
					CHmDeductionHeadsModel respContent = iHmDeductionHeadsRepository.save(chmDeductionHeadsModel);
					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/HmDeductionHeads/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HmDeductionHeads/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int deduction_heads_id, int company_id, String deleted_by) {
		Optional<CHmDeductionHeadsModel> option = iHmDeductionHeadsRepository.findById(deduction_heads_id);
		CHmDeductionHeadsModel chmDeductionHeadsModel = new CHmDeductionHeadsModel();
		if (option.isPresent()) {
			chmDeductionHeadsModel = option.get();
			chmDeductionHeadsModel.setIs_delete(true);
			chmDeductionHeadsModel.setDeleted_on(new Date());
			chmDeductionHeadsModel.setDeleted_by(deleted_by);
			iHmDeductionHeadsRepository.save(chmDeductionHeadsModel);
		}
		return chmDeductionHeadsModel;
	}

	@Override
	public Page<CHmDeductionHeadsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iHmDeductionHeadsViewRepository.FnShowAllActiveRecords(pageable, company_id);

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int deduction_heads_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CHmDeductionHeadsModel chmDeductionHeadsModel = null;
		try {
			chmDeductionHeadsModel = iHmDeductionHeadsRepository.FnShowParticularRecordForUpdate(deduction_heads_id,
					company_id);
			responce.put("success", "1");
			responce.put("data", chmDeductionHeadsModel);
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

	@Override
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iHmDeductionHeadsViewRepository.FnShowAllReportRecords(pageable, company_id);

	}

	@Override
	public Page<CHmDeductionHeadsViewModel> FnShowParticularRecord(int deduction_heads_id, Pageable pageable,
	                                                               int company_id) {
		return iHmDeductionHeadsViewRepository.FnShowParticularRecord(deduction_heads_id, pageable, company_id);

	}

}
