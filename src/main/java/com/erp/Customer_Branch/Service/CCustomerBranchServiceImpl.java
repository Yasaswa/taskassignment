package com.erp.Customer_Branch.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Customer.Repository.ICustomerRepository;
import com.erp.Customer_Branch.Model.CCustomerBranchModel;
import com.erp.Customer_Branch.Model.CCustomerBranchViewModel;
import com.erp.Customer_Branch.Repository.ICustomerBranchRepository;
import com.erp.Customer_Branch.Repository.ICustomerBranchViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
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
public class CCustomerBranchServiceImpl implements ICustomerBranchService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICustomerBranchRepository iCustomerBranchRepository;

	@Autowired
	ICustomerBranchViewRepository iCustomerBranchViewRepository;

	@Autowired
	ICustomerRepository iCustomerRepository;

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CCustomerBranchViewModel> data = iCustomerBranchViewRepository.FnShowAllActiveRecords(pageable);
			String json = objectMapper.writeValueAsString(data);
			resp.put("data", json);
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
	public Object FnShowParticularRecordForUpdate(int cust_branch_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CCustomerBranchViewModel json = iCustomerBranchViewRepository.FnShowParticularRecordForUpdate(cust_branch_id);
			String json1 = objectMapper.writeValueAsString(json);
			resp.put("success", "1");
			resp.put("data", json1);
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
	public Object FnDeleteRecord(int cust_branch_id, int company_id, String deleted_by) {
		iCustomerRepository.FnDeleteCustBranchBankRecords(cust_branch_id, deleted_by, company_id);
		return iCustomerBranchRepository.FnDeleteRecord(cust_branch_id, company_id);
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<Map<String, Object>> data = iCustomerBranchViewRepository.FnShowAllReportRecords(pageable);
			System.out.println(data);
			resp.put("success", "1");
			resp.put("data", data);
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
	public JSONObject FnAddUpdateRecord(CCustomerBranchModel customerBranchModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CCustomerBranchModel MyModel = null;
		int company_id = customerBranchModel.getCompany_id();
		try {
			Optional<CCustomerBranchModel> option = iCustomerBranchRepository.findById(customerBranchModel.getCust_branch_id());

			if (option.isPresent()) {
				customerBranchModel.setModified_on(new Date());
				CCustomerBranchModel mymodel = iCustomerBranchRepository.save(customerBranchModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Customer Branch updated successfully!..");
				return resp;
			} else {
				CCustomerBranchModel model = iCustomerBranchRepository.getCheck(customerBranchModel.getCust_branch_name(),customerBranchModel.getCustomer_id(), customerBranchModel.getCompany_id());
				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Customer Branch Name is already exist!");

					return resp;
				} else {

					CCustomerBranchModel CustomerBranchModel = iCustomerBranchRepository.save(customerBranchModel);
					String json = objectMapper.writeValueAsString(CustomerBranchModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");

					System.out.println("  Customer Branch saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/customerbranch/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/customerbranch/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CCustomerBranchViewModel> data = iCustomerBranchViewRepository.FnShowAllRecords(pageable);
			String json = objectMapper.writeValueAsString(data);
			resp.put("data", json);
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
	public Object FnShowParticularRecord(int company_id, int company_branch_id, int cust_branch_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CCustomerBranchViewModel json = iCustomerBranchViewRepository.FnShowParticularRecord(company_id, company_branch_id, cust_branch_id);
			String json1 = objectMapper.writeValueAsString(json);

			resp.put("success", "1");
			resp.put("data", json1);
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

}
