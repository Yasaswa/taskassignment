package com.erp.AmApplicationErrorLogs.Service;

import com.erp.AmApplicationErrorLogs.Model.CAmApplicationErrorLogsModel;
import com.erp.AmApplicationErrorLogs.Repository.IAmApplicationErrorLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CAmApplicationErrorLogsServiceImpl implements CAmApplicationErrorLogsService {

	@Autowired
	IAmApplicationErrorLogsRepository iAmApplicationErrorLogsRepository;

	@Override
	public CAmApplicationErrorLogsModel addErrorLog(int company_id, String error_source, String error_source_name, int error_code, String error_message) {
		CAmApplicationErrorLogsModel amApplicationErrorLogsModel = new CAmApplicationErrorLogsModel();
		try {
			amApplicationErrorLogsModel.setCompany_id(company_id);
			amApplicationErrorLogsModel.setApplication_error_generated_on(new Date());
			amApplicationErrorLogsModel.setError_source(error_source);
			amApplicationErrorLogsModel.setError_source_name(error_source_name);
			amApplicationErrorLogsModel.setError_code(error_code);
			amApplicationErrorLogsModel.setError_meassage(error_message);
			amApplicationErrorLogsModel = iAmApplicationErrorLogsRepository.save(amApplicationErrorLogsModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return amApplicationErrorLogsModel;
	}

	@Override
	public Page<CAmApplicationErrorLogsModel> getamApplicationErrorLogs(int company_id, Pageable pageable) {
		return iAmApplicationErrorLogsRepository.FngetamApplicationErrorLogs(company_id, pageable);
	}
}
