package com.erp.Supplier.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesFormsUserAccess.Model.CUserViewModel;
import com.erp.AmModulesFormsUserAccess.Repository.IUserViewModelRepository;
import com.erp.Supplier.Model.*;
import com.erp.Supplier.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CSupplierServiceImpl implements ISupplierService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISupplierRepository iSupplierRepository;

	@Autowired
	ISupplierViewRepository iSupplierViewRepository;

	@Autowired
	ISupplierBranchRepository iSupplierBranchRepository;

	@Autowired
	ISupplierBanksRepository iSupplierBanksRepository;

	@Autowired
	ISupplierContactRepository iSupplierContactRepository;

	@Autowired
	ISupplierContactViewRepository iSupplierContactViewRepository;

	@Autowired
	ISupplierBranchViewRepository iSupplierBranchViewRepository;

	@Autowired
	ISupplierBanksViewRepository iSupplierBanksViewRepository;

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
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		int supplier_id = commonIdsObj.getInt("supplier_id");
		String userName = commonIdsObj.getString("userName");

		int supplier_branch_id = commonIdsObj.getInt("supplier_branch_id");
		String key = jsonObject.getString("saveKey");

		AtomicInteger atomicSupplierId = new AtomicInteger(supplier_id);
		AtomicInteger atomicSupplierBranchId = new AtomicInteger(supplier_branch_id);

		JSONObject json = jsonObject.getJSONObject("SupplierMasterData");
		JSONObject branchjson = jsonObject.getJSONObject("SupplierBranchData");
		JSONArray supplierbankArray = (JSONArray) jsonObject.get("SupplierBankData");
		JSONArray supplierContactArray = (JSONArray) jsonObject.get("SupplierContactData");

		try {
			CSupplierModel responseSuppilerMaster = null;
			// Supplier
			CSupplierModel jsonModel = objectMapper.readValue(json.toString(), CSupplierModel.class);

//			// Check User Name Exist or Not
//			CUserViewModel checkUserName = iUserViewModelRepository.getUserName(jsonModel.getUsername(),jsonModel.getSupplier_code());
//
//			if (checkUserName != null) {
//				responce.put("success", 0);
//				responce.put("error", "User Name is already exist!");
//
//				return responce;
//			}

			if (key.equals("suppInfo") || key.equals("totalSuppEntryInfo")) {
				if (jsonModel.getSupplier_id() == 0) {
//				If New supplier then generate the random password and encrypt it and save it in db.
					String randomPassword = RandomStringUtils.random(15, true, true);
//					System.out.println("Supplier: \t" + "Random Password: " + randomPassword + "\tEncrypted Password: " + PasswordManager.encrypt(randomPassword));
					jsonModel.setPassword(passwordEncoder.encode(randomPassword));


					CSupplierModel	resultsSupplierName = iSupplierRepository.getCheck(jsonModel.getSupplier_name()
								, jsonModel.getCompany_id());

					if (resultsSupplierName != null) {
						responce.put("success", 0);
						responce.put("data", "");
						responce.put("error", " Supplier Name  is already exist!");
						return responce;
					}

				} else {
					// Means supplier is already exist then get the password and encrypt it and store it in db.
					String encryptedPassword = passwordEncoder.encode(jsonModel.getPassword());
//					System.out.println("Supplier: \t" + "Original Password: " + jsonModel.getPassword() + "\tEncrypted Password: " + encryptedPassword);
					jsonModel.setPassword(encryptedPassword);

				}
				responseSuppilerMaster = iSupplierRepository.save(jsonModel);
				atomicSupplierId.set(responseSuppilerMaster.getSupplier_id());
			}

			// Supplier Branch
			CSupplierBranchModel responseSuppilerBranchDetails=null;
			if (key.equals("suppBranchInfo") || key.equals("totalSuppEntryInfo")) {
				CSupplierBranchModel branchjsonModel = objectMapper.readValue(branchjson.toString(),
						CSupplierBranchModel.class);

				branchjsonModel.setSupplier_id(atomicSupplierId.get());

				if (branchjsonModel.getSupp_branch_id() == 0) {
					//CSupplierBranchModel branchModel = iSupplierBranchRepository.getCheck(branchjsonModel.getSupp_branch_name(), branchjsonModel.getSupp_branch_short_name(), company_id);

					CSupplierBranchModel branchModel = iSupplierBranchRepository.getCheck(branchjsonModel.getSupp_branch_name(), atomicSupplierId.get(), company_id);
					if (branchModel != null) {
						responce.put("success", 0);
						responce.put("data", "");
						responce.put("error", " Supplier Branch Name  is already exist!");
						return responce;
					}
				}
				 responseSuppilerBranchDetails = iSupplierBranchRepository.save(branchjsonModel);

				int supplierBranchId = responseSuppilerBranchDetails.getSupp_branch_id();
				atomicSupplierBranchId.set(supplierBranchId);

				// Supplier Bank
				if (!supplierbankArray.isEmpty()) {
					iSupplierBanksRepository.updateSupplierBankActiveStatus(company_id,
							supplier_id);

					List<CSupplierBanksModel> supplierBankdetails = objectMapper.readValue(supplierbankArray.toString(),
							new TypeReference<List<CSupplierBanksModel>>() {
							});

					supplierBankdetails.forEach(item -> {
						item.setSupplier_id(atomicSupplierId.get());
						item.setSupp_branch_id(atomicSupplierBranchId.get());
						item.setSupp_bank_id(0);
						item.setCreated_by(userName);
					});

					iSupplierBanksRepository.saveAll(supplierBankdetails);
				}
			}

			// Supplier Contact


			if (!supplierContactArray.isEmpty()) {

				iSupplierContactRepository.updateSupplierContactActiveStatus(supplier_id, company_id);

				List<CSupplierContactModel> supplierContactdetails = objectMapper.readValue(
						supplierContactArray.toString(),
						new TypeReference<List<CSupplierContactModel>>() {}
				);

				List<CSupplierContactModel> existingContacts = iSupplierContactRepository
						.findBySupplierId(supplier_id);

				Set<String> existingContactNos = existingContacts.stream()
						.map(CSupplierContactModel::getSupp_contact_no)
						.collect(Collectors.toSet());

				// Filter out contacts that already exist
				List<CSupplierContactModel> newContacts = supplierContactdetails.stream()
						.filter(item -> !existingContactNos.contains(item.getSupp_contact_no())) // Avoid duplicates
						.peek(item -> {
							item.setSupplier_id(atomicSupplierId.get());
							if (item.getSupp_branch_id().equals(0)) {
								item.setSupp_branch_id(atomicSupplierBranchId.get());
							}
							item.setSupplier_contact_id(0);
							item.setCreated_by(userName);
						})
						.collect(Collectors.toList());

				// Save only new contacts
				if (!newContacts.isEmpty()) {
					iSupplierContactRepository.saveAll(newContacts);
				}
			}


//		Error: Saving multiple contacts (duplicates) and not replacing existing ones (when update)

//			if (!supplierContactArray.isEmpty()) {
//				iSupplierContactRepository.updateSupplierContactActiveStatus(supplier_id, company_id);
//
//				List<CSupplierContactModel> supplierContactdetails = objectMapper.readValue(supplierContactArray.toString(),
//						new TypeReference<List<CSupplierContactModel>>() {
//						});
//
//				supplierContactdetails.forEach(item -> {
//					item.setSupplier_id(atomicSupplierId.get());
//					if(item.getSupp_branch_id().equals(0)){
//						item.setSupp_branch_id(atomicSupplierBranchId.get());
//					}
//					item.setSupplier_contact_id(0);
//					item.setCreated_by(userName);
//				});
//
//				iSupplierContactRepository.saveAll(supplierContactdetails);
//			}

			responce.put("success", 1);
			responce.put("data", responseSuppilerMaster);
			responce.put("suplierBranchData", responseSuppilerBranchDetails);
			responce.put("error", "");
			responce.put("message", supplier_id == 0 ? "Record added successfully!" : "Record updated successfully!");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/supplier/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/supplier/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int supplier_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			iSupplierRepository.FnDeleteRecord(supplier_id, deleted_by);
			iSupplierBranchRepository.FnDeleteRecord(supplier_id, deleted_by);
			iSupplierBanksRepository.FnDeleteRecord(supplier_id, deleted_by);
			iSupplierContactRepository.FnDeleteRecord(supplier_id, deleted_by);

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
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CSupplierViewModel> data = iSupplierViewRepository.FnShowAllActiveRecords(pageable);
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int supplier_id) {
		Map<String, Object> resp = new HashMap<>();
		try {

			CSupplierViewModel supplierData = iSupplierViewRepository.FnShowParticularRecordForUpdate(supplier_id);

//			String decryptedPassword = PasswordManager.decrypt(supplierData.getSupplier_password());
//			supplierData.setSupplier_password(decryptedPassword);

			List<CSupplierContactViewModel> suppContact = iSupplierContactViewRepository.FnShowParticularRecordForUpdate(supplier_id);

			resp.put("success", "1");
			resp.put("supplierData", supplierData);
			resp.put("suppContact", suppContact);
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
	public Object FnShowParticularRecord(int company_id, int company_branch_id, int supplier_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CSupplierViewModel json = iSupplierViewRepository.FnShowParticularRecord(company_id, company_branch_id,
					supplier_id);
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
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CSupplierViewModel> data = iSupplierViewRepository.FnShowAllRecords(pageable);
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
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) {
		return iSupplierViewRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdateForBranch(int supp_branch_id) {
		Map<String, Object> response = new HashMap<>();
		try {
			CSupplierBranchViewModel suppBranchData = iSupplierBranchViewRepository.FnShowParticularRecordForUpdate(supp_branch_id);
			List<CSupplierBanksViewModel> bankData = iSupplierBanksViewRepository.FnShowParticularRecordForUpdate(supp_branch_id);

			response.put("suppBranchData", suppBranchData);
			response.put("bankData", bankData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Map<String, Object> FnDeleteSupplierBranchRecord(int supp_id, int supp_branch_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			// First Delete the banks for that branch then contacts persons.
			iSupplierContactRepository.FnDeleteSupplierBranchBankRecords(supp_branch_id, deleted_by);
			iSupplierBanksRepository.FnDeleteSupplierBranchContactsRecords(supp_branch_id, deleted_by);
			iSupplierBranchRepository.FnDeleteSupplierBranchRecord(supp_branch_id, deleted_by);
			// Send the latest supplier contact-data.
			List<CSupplierContactViewModel> suppContact = iSupplierContactViewRepository.FnShowParticularRecordForUpdate(supp_id);
			responce.put("suppContact", suppContact);

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

}
