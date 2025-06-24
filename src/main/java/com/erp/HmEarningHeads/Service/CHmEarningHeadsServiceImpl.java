package com.erp.HmEarningHeads.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HmEarningHeads.Model.CHmEarningHeadsModel;
import com.erp.HmEarningHeads.Model.CHmEarningHeadsViewModel;
import com.erp.HmEarningHeads.Repository.IHmEarningHeadsRepository;
import com.erp.HmEarningHeads.Repository.IHmEarningHeadsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CHmEarningHeadsServiceImpl implements IHmEarningHeadsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IHmEarningHeadsRepository iHmEarningHeadsRepository;

	@Autowired
	IHmEarningHeadsViewRepository iHmEarningHeadsViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CHmEarningHeadsModel cHmEarningHeadsModel) {
		Map<String, Object> responce = new HashMap<>();
		int company_id = cHmEarningHeadsModel.getCompany_id();
		try {
			Optional<CHmEarningHeadsModel> option = iHmEarningHeadsRepository
					.findById(cHmEarningHeadsModel.getEarning_heads_id());
			CHmEarningHeadsModel chmEarningheadsModel = new CHmEarningHeadsModel();

			if (option.isPresent()) {
				chmEarningheadsModel = option.get();

				chmEarningheadsModel.setModified_on(new Date());
				chmEarningheadsModel.setDeleted_on(new Date());
				chmEarningheadsModel.setIs_delete(true);
				iHmEarningHeadsRepository.save(chmEarningheadsModel);
				cHmEarningHeadsModel.setEarning_head_version(chmEarningheadsModel.getEarning_head_version() + 1);

				cHmEarningHeadsModel.setEarning_heads_id(0);
				CHmEarningHeadsModel respContent = iHmEarningHeadsRepository.save(cHmEarningHeadsModel);
				responce.put("success", "1");
				responce.put("data", respContent);
				responce.put("error", "");
				responce.put("message",
						option.isPresent() ? "Record updated successfully!" : "Record added successfully!");
				System.out.println(" HmEarningHeads Updated Successfully!..");
			}

//			Check similar Earning Head Name or short name is exist or not

			CHmEarningHeadsModel resultsEarningHeadName = null;
			if (chmEarningheadsModel.getEarning_heads_id() == 0) {
				if (cHmEarningHeadsModel.getEarning_head_short_name() == null
						|| cHmEarningHeadsModel.getEarning_head_short_name().isEmpty()) {

					resultsEarningHeadName = iHmEarningHeadsRepository.getCheck(cHmEarningHeadsModel.getEarning_head_name(),
							null, cHmEarningHeadsModel.getCompany_id());
				} else {
					resultsEarningHeadName = iHmEarningHeadsRepository.getCheck(cHmEarningHeadsModel.getEarning_head_name(),
							cHmEarningHeadsModel.getEarning_head_short_name(), cHmEarningHeadsModel.getCompany_id());
				}

				if (resultsEarningHeadName != null) {
					responce.put("success", 0);
					responce.put("error", "Earning Head Name or Short Name is already exist!");
					return responce;
				} else {
					CHmEarningHeadsModel respContent = iHmEarningHeadsRepository.save(cHmEarningHeadsModel);
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
						"/api/HmEarningHeads/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HmEarningHeads/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int earning_heads_id, int company_id, String deleted_by) {
		Optional<CHmEarningHeadsModel> option = iHmEarningHeadsRepository.findById(earning_heads_id);
		CHmEarningHeadsModel cHmEarningHeadsModel = new CHmEarningHeadsModel();
		if (option.isPresent()) {
			cHmEarningHeadsModel = option.get();
			cHmEarningHeadsModel.setIs_delete(true);
			cHmEarningHeadsModel.setDeleted_by(deleted_by);
			cHmEarningHeadsModel.setDeleted_on(new Date());
			iHmEarningHeadsRepository.save(cHmEarningHeadsModel);

		}
		return cHmEarningHeadsModel;
	}

	@Override
	public Page<CHmEarningHeadsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iHmEarningHeadsViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int earning_heads_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CHmEarningHeadsModel cHmEarningHeadsModel = null;
		try {
			cHmEarningHeadsModel = iHmEarningHeadsRepository.FnShowParticularRecordForUpdate(earning_heads_id,
					company_id);
			responce.put("success", "1");
			responce.put("data", cHmEarningHeadsModel);
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
		return iHmEarningHeadsViewRepository.FnShowAllReportRecords(pageable, company_id);
	}

	@Override
	public Page<CHmEarningHeadsViewModel> FnShowParticularRecord(int earning_heads_id, Pageable pageable,
	                                                             int company_id) {
		return iHmEarningHeadsViewRepository.FnShowParticularRecord(earning_heads_id, pageable, company_id);
	}

}
