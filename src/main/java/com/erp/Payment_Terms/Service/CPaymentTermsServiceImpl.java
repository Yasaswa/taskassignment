package com.erp.Payment_Terms.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Payment_Terms.Model.CPaymentTermsModel;
import com.erp.Payment_Terms.Model.CPaymentTermsViewModel;
import com.erp.Payment_Terms.Repository.IPaymentTermsRepository;
import com.erp.Payment_Terms.Repository.IPaymentTermsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CPaymentTermsServiceImpl implements IPaymentTermsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IPaymentTermsRepository iPaymentTermsRepository;

	@Autowired
	IPaymentTermsViewRepository iPaymentTermsViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CPaymentTermsModel cPaymentTermsModel) {
		Map<String, Object> resp = new HashMap<>();
		CPaymentTermsModel MyModel = null;
		int company_id = cPaymentTermsModel.getCompany_id();
		try {
			Optional<CPaymentTermsModel> option = iPaymentTermsRepository
					.findById(cPaymentTermsModel.getPayment_terms_id());

			if (option.isPresent()) {
				cPaymentTermsModel.setModified_on(new Date());
				CPaymentTermsModel mymodel = iPaymentTermsRepository.save(cPaymentTermsModel);
				resp.put("success", "1");
				resp.put("data", mymodel);
				resp.put("message", "Record updated succesfully!...");

				return resp;
			} else {
				CPaymentTermsModel model = iPaymentTermsRepository.getCheck(cPaymentTermsModel.getPayment_terms_name());
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Payment terms name is already exist!");

					return resp;
				} else {

					CPaymentTermsModel PaymentTermsModel = iPaymentTermsRepository.save(cPaymentTermsModel);
					resp.put("success", "1");
					resp.put("data", PaymentTermsModel);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");

					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/paymentterms/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

				return resp;

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/paymentterms/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int payment_terms_id, String deleted_by) {
		return iPaymentTermsRepository.FnDeleteRecord(payment_terms_id, deleted_by);
	}

	@Override
	public Map<String, Object> FnShowAllRecords(Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CPaymentTermsViewModel> data = iPaymentTermsViewRepository.FnShowAllRecords(pageable);
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
	public Map<String, Object> FnShowAllActiveRecords(Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CPaymentTermsViewModel> data = iPaymentTermsViewRepository.FnShowAllActiveRecords(pageable);
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int payment_terms_id) {
		Map<String, Object> resp = new HashMap<>();
		try {

			CPaymentTermsViewModel json = iPaymentTermsViewRepository.FnShowParticularRecordForUpdate(payment_terms_id);
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
	public Map<String, Object> FnShowParticularRecord(int company_id, int payment_terms_id) {
		Map<String, Object> resp = new HashMap<>();
		try {

			CPaymentTermsViewModel json = iPaymentTermsViewRepository.FnShowParticularRecord(company_id,
					payment_terms_id);
			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

			return resp;

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
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<List<Map<String, Object>>> data = iPaymentTermsViewRepository.FnShowAllReportRecords(pageable);
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
