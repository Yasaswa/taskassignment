package com.erp.Customer.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.erp.Supplier.Model.CSupplierModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesFormsUserAccess.Model.CUserViewModel;
import com.erp.AmModulesFormsUserAccess.Repository.IAmModulesFormsUserAccessViewRepository;
import com.erp.AmModulesFormsUserAccess.Repository.IUserViewModelRepository;
import com.erp.Customer.Model.CCustomerModel;
import com.erp.Customer.Model.CCustomerViewModel;
import com.erp.Customer.Repository.ICustomerRepository;
import com.erp.Customer.Repository.ICustomerViewRepository;
import com.erp.Customer_Bank.Model.CCustomerBankModel;
import com.erp.Customer_Bank.Repository.ICustomerBankRepository;
import com.erp.Customer_Branch.Model.CCustomerBranchModel;
import com.erp.Customer_Branch.Repository.ICustomerBranchRepository;
import com.erp.Customer_Contacts.Model.CCustomerContactsModel;
import com.erp.Customer_Contacts.Model.CCustomerContactsViewModel;
import com.erp.Customer_Contacts.Repository.ICustomerContactsRepository;
import com.erp.Customer_Contacts.Repository.ICustomerContactsViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CCustomerServiceImpl implements ICustomerService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICustomerRepository iCustomerRepository;

	@Autowired
	ICustomerViewRepository iCustomerViewRepository;

	@Autowired
	ICustomerBranchRepository iCustomerBranchRepository;

	@Autowired
	ICustomerBankRepository iCustomerBankRepository;

	@Autowired
	ICustomerContactsRepository iCustomerContactsRepository;

	@Autowired
	ICustomerContactsViewRepository iCustomerContactsViewRepository;

	@Autowired
	IAmModulesFormsUserAccessViewRepository iAmModulesFormsUserAccessViewRepository;

	@Autowired
	IUserViewModelRepository iUserViewModelRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {

		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int customer_id = commonIdsObj.getInt("customer_id");
		String userName = commonIdsObj.getString("userName");

		int cust_branch_id = commonIdsObj.getInt("cust_branch_id");
		String key = jsonObject.getString("saveKey");

		AtomicInteger atomicCustomerId = new AtomicInteger(customer_id);
		AtomicInteger atomicCustomerBranchId = new AtomicInteger(cust_branch_id);

		JSONObject json = jsonObject.getJSONObject("CustomerMasterData");
		JSONObject branchjson = jsonObject.getJSONObject("CustomerBranchData");
		JSONArray customerbankArray = (JSONArray) jsonObject.get("CustomerBankData");
		JSONArray customerContactArray = (JSONArray) jsonObject.get("CustomerContactData");
        Integer customerBranchId=0;
        Integer customerId=0;
		try {

			CCustomerModel responseCustomerMaster = null;
			CCustomerModel jsonModel = objectMapper.readValue(json.toString(), CCustomerModel.class);

			if (key.equals("totalCustEntryInfo")) {

				// Check User Name Exist or Not
//				// Commented Because UserName is unique for all companies.(as per prashant sir told on 08-07-2024)
//				CUserViewModel checkUserName = iUserViewModelRepository.getUserName(jsonModel.getUsername(), company_id, jsonModel.getCustomer_code());
				CUserViewModel checkUserName = iUserViewModelRepository.getUserName(jsonModel.getUsername(), jsonModel.getCustomer_code());

				if (checkUserName != null) {
					responce.put("success", 0);
					responce.put("error", "User Name is already exist!");
					return responce;
				}

				if (jsonModel.getCustomer_id() == 0) {
//				If New Customer then generate the random password and encrypt it and save it in db.
					String randomPassword = RandomStringUtils.random(6, true, true);
					jsonModel.setPassword(passwordEncoder.encode(randomPassword));

//					Check similar Customer name or short name is exist or not
					//CCustomerModel resultsCustName = null;
//					if (jsonModel.getCustomer_short_name() == null || jsonModel.getCustomer_short_name().isEmpty()) {
////						resultsCustName = iCustomerRepository.getCheck(jsonModel.getCustomer_name(),
////								null, jsonModel.getCompany_id());
//						resultsCustName = iCustomerRepository.getCheck(jsonModel.getCustomer_name(), jsonModel.getCompany_id());
//					} else {
////						resultsCustName = iCustomerRepository.getCheck(jsonModel.getCustomer_name(),
////								jsonModel.getCustomer_short_name(), jsonModel.getCompany_id());
//						resultsCustName = iCustomerRepository.getCheck(jsonModel.getCustomer_name(),jsonModel.getCompany_id());
//					}
					CCustomerModel resultsCustName = iCustomerRepository.getCheck(jsonModel.getCustomer_name()
							, jsonModel.getCompany_id());

					if (resultsCustName != null) {
						responce.put("success", 0);
						responce.put("error", "Customer Name  is already exist!");
						return responce;
					}

				} else {
					// Means Customer is already exist then get the password and encrypt it and
					// store it in db.
					String encryptedPassword = passwordEncoder.encode(jsonModel.getPassword());
					System.out.println("Customer: \t" + "Original Password: " + jsonModel.getPassword()
							+ "\tEncrypted Password: " + encryptedPassword);
					jsonModel.setPassword(encryptedPassword);

				}
				responseCustomerMaster = iCustomerRepository.save(jsonModel);
				atomicCustomerId.set(responseCustomerMaster.getCustomer_id());
			} else {
				responseCustomerMaster = jsonModel;
			}

			// Customer Branch
			if (key.equals("custBranchInfo") || key.equals("totalCustEntryInfo")) {
				CCustomerBranchModel branchjsonModel = objectMapper.readValue(branchjson.toString(),
						CCustomerBranchModel.class);

				branchjsonModel.setCustomer_id(atomicCustomerId.get());

				if (branchjsonModel.getCust_branch_id() == 0) {
//				    Check similar supllier name or short name is exist or not
					CCustomerBranchModel resultSuppBranchName = null;
					if (branchjsonModel.getCust_branch_short_name() == null || branchjsonModel.getCust_branch_short_name().isEmpty()) {
//						resultSuppBranchName = iCustomerBranchRepository.getCheck(
//								branchjsonModel.getCust_branch_name(), branchjsonModel.getCust_branch_short_name(),
//								branchjsonModel.getCompany_id());
						resultSuppBranchName = iCustomerBranchRepository.getCheck(
								branchjsonModel.getCust_branch_name(),atomicCustomerId.get(),
								branchjsonModel.getCompany_id());
					} else {
						resultSuppBranchName = iCustomerBranchRepository.getCheck(
//								branchjsonModel.getCust_branch_name(), null,
//								branchjsonModel.getCompany_id());
								branchjsonModel.getCust_branch_name(),atomicCustomerId.get(),
								branchjsonModel.getCompany_id());
					}
				//	resultSuppBranchName = iCustomerBranchRepository.getCheck(branchjsonModel.getCust_branch_name(),atomicCustomerId.get(),branchjsonModel.getCompany_id());
					if (resultSuppBranchName != null) {
						responce.put("success", 0);
						responce.put("error", "Customer Branch Name is already exist!");

						return responce;
					}
				}

				CCustomerBranchModel responseCustomerBranchDetails = iCustomerBranchRepository.save(branchjsonModel);
				 customerBranchId = responseCustomerBranchDetails.getCust_branch_id();
				 customerId = responseCustomerBranchDetails.getCustomer_id();
				responce.put("cust_branch_id", customerBranchId);

				// Customer Bank
				if (!customerbankArray.isEmpty()) {
					iCustomerBankRepository.updateCustomerBankActiveStatus(customer_id, company_id);

					List<CCustomerBankModel> customerBankdetails = objectMapper.readValue(customerbankArray.toString(),
							new TypeReference<List<CCustomerBankModel>>() {
							});

                    Integer finalCustomerBranchId = customerBranchId;
                    customerBankdetails.forEach(item -> {
						item.setCustomer_id(atomicCustomerId.get());
						item.setCust_branch_id(finalCustomerBranchId);
						item.setCust_bank_id(0);
						item.setCreated_by(userName);
					});

					iCustomerBankRepository.saveAll(customerBankdetails);
				}
			}

			// Customer Contact
			if (!customerContactArray.isEmpty() && customerId != 0) {
				iCustomerContactsRepository.updateCustomerContactActiveStatus(customer_id, company_id);

				List<CCustomerContactsModel> customerContactdetails = objectMapper
						.readValue(customerContactArray.toString(), new TypeReference<List<CCustomerContactsModel>>() {
						});

//				customerContactdetails.forEach(item -> {
//					item.setCustomer_id(atomicCustomerId.get());
//					item.setCust_branch_id(atomicCustomerBranchId.get());
//					item.setCreated_by(userName);
//				});

                Integer finalCustomerBranchId1 = customerBranchId;
                Integer finalCustomerId = customerId;
                customerContactdetails.forEach(item -> {
					item.setCustomer_id(finalCustomerId);
					item.setCust_branch_id(finalCustomerBranchId1);
					item.setCreated_by(userName);
				});

				iCustomerContactsRepository.saveAll(customerContactdetails);
			}

			responce.put("success", 1);
			responce.put("data", responseCustomerMaster);
			responce.put("error", "");
			responce.put("message", customer_id == 0 ? "Record added successfully!" : "Record updated successfully!");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/customer/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/customer/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;

	}

	@Override
	public Object FnDeleteRecord(int customer_id, String deleted_by, int company_id) {
		iCustomerRepository.FnDeleteRecord(customer_id, deleted_by, company_id);
		iCustomerRepository.FnDeleteCustBranchRecord(customer_id, deleted_by, company_id);
		iCustomerRepository.FnDeleteCustBankRecords(customer_id, deleted_by, company_id);
		return iCustomerRepository.FnDeleteCustConstactsRecord(customer_id, deleted_by, company_id);
	}



	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<Map<String, Object>> data = iCustomerRepository.FnShowAllReportRecords(pageable);

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
	public JSONObject FnShowParticularRecordForUpdate(int customer_id, int company_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CCustomerViewModel json = iCustomerViewRepository.FnShowParticularRecordForUpdate(customer_id, company_id);

//			String decryptedPassword = PasswordManager.decrypt(json.getCustomer_password());
//			json.setCustomer_password(decryptedPassword);

			String json1 = objectMapper.writeValueAsString(json);

			resp.put("success", "1");
			resp.put("data", json1);
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
	public JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int customer_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CCustomerViewModel json = iCustomerViewRepository.FnShowParticularRecord(company_id, company_branch_id,
					customer_id);
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
	public Map<String, Object> FnShowAllRecords(Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CCustomerViewModel> data = iCustomerViewRepository.FnShowAllRecords(pageable);
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
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CCustomerViewModel> data = iCustomerViewRepository.FnShowAllActiveRecords(pageable);
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
	public Map<String, Object> FnDeleteCustomerBranchRecord(int customer_id, int cust_branch_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iCustomerContactsRepository.FnDeleteCustomerBranchContactsRecords(cust_branch_id, deleted_by);
			iCustomerBankRepository.FnDeleteCustomerBranchBankRecords(cust_branch_id, deleted_by);
			iCustomerBranchRepository.FnDeleteCustomerBranchRecords(cust_branch_id, deleted_by);

			// Send the latest Customer contact-data.
			List<CCustomerContactsViewModel> custContact = iCustomerContactsViewRepository
					.FnShowParticularRecordForUpdate(customer_id);
			responce.put("custContact", custContact);
			responce.put("success", 1);

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();

				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();

			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}

		return responce;
	}

	@Override
	public Map<String, Object> FnShowCustomerBankBranchAndContactAllRecords(int customer_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			CCustomerModel customerRecords = iCustomerRepository.FnShowCustomerRecords(customer_id, company_id);
			List<CCustomerContactsModel> customerContactRecords = iCustomerContactsRepository.FnShowCustomerBranchRecords(customer_id, company_id);

			responce.put("CustomerRecords", customerRecords);
			responce.put("CustomerContactRecords", customerContactRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

}
