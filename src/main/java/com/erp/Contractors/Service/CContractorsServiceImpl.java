package com.erp.Contractors.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Contractors.Model.CContractorBankModel;
import com.erp.Contractors.Model.CContractorBankViewModel;
import com.erp.Contractors.Model.CContractorsModel;
import com.erp.Contractors.Model.CContractorsViewModel;
import com.erp.Contractors.Repository.IContractorsBankRepository;
import com.erp.Contractors.Repository.IContractorsRepository;
import com.erp.Contractors.Repository.IContractorsViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CContractorsServiceImpl implements IContractorsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IContractorsRepository iContractorsRepository;

	@Autowired
	IContractorsViewRepository iContractorsViewRepository;
	
	 
	
	@Autowired
	private JdbcTemplate executeQuery;
	
	@Autowired
	IContractorsBankRepository icontractorsBankRepository;
	
	
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> resp = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int contractor_id = commonIdsObj.getInt("contractor_id");
		AtomicInteger contractorId = new AtomicInteger(contractor_id);

		JSONObject contractorJson = jsonObject.getJSONObject("TransContractorData");
		JSONArray bankArray = (JSONArray) jsonObject.get("TransContractorBankData");

		try {
				
				CContractorsModel cContractorsModel = objectMapper.readValue(contractorJson.toString(),
					CContractorsModel.class);

				if (cContractorsModel.getCompany_id() == 0) {
					// Check similar Contractor Name,Contractor short name is exist or not
					CContractorsModel resultsContractorName = null;
					if (cContractorsModel.getContractor_short_name() == null
							|| cContractorsModel.getContractor_short_name().isEmpty()) {
						resultsContractorName = iContractorsRepository.getCheck(cContractorsModel.getContractor_name(),
								null, cContractorsModel.getCompany_id());
					} else {
						resultsContractorName = iContractorsRepository.getCheck(cContractorsModel.getContractor_name(),
								cContractorsModel.getContractor_short_name(), cContractorsModel.getCompany_id());
					}if (resultsContractorName != null) {
						resp.put("success", 0);
						resp.put("data", "");
						resp.put("error", " Contractor Name or Short Name is already exist!");
						return resp;
					}
				}
				CContractorsModel respContractorsModel = iContractorsRepository.save(cContractorsModel);
				contractorId.set(respContractorsModel.getContractor_id());
				if(contractorId.get() != 0){
					icontractorsBankRepository.updateContarctorBankActiveStatus(company_id, contractorId.get());
				}
					  if (!bankArray.isEmpty()) {
			                List<CContractorBankModel> cContractorBankModel = objectMapper
			                        .readValue(bankArray.toString(), new TypeReference<List<CContractorBankModel>>() {
			                        });
			                cContractorBankModel.forEach(items -> {
			                    items.setContractor_id(contractorId.get());
			                });
			                icontractorsBankRepository.saveAll(cContractorBankModel);
			            	
				}
			resp.put("success", 1);
			resp.put("data", respContractorsModel);
			resp.put("error", "");
			resp.put("message", contractor_id == 0 ? "Record added Successfully..!" : "Record updated Successfully..!");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/contractor/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/contractor/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

		}
		return resp;

	}

//	@Override
//	public HashMap<String, Object> FnAddUpdateRecord(CContractorsModel cContractorsModel) {
//		HashMap<String, Object> resp = new HashMap<>();
//		int company_id = cContractorsModel.getCompany_id();
//		try {
//			Optional<CContractorsModel> option = iContractorsRepository.findById(cContractorsModel.getContractor_id());
//			CContractorsModel MyModel = null;
//			if (option.isPresent()) {
//				cContractorsModel.setModified_on(new Date());
//				CContractorsModel json = iContractorsRepository.save(cContractorsModel);
//				resp.put("success", "1");
//				resp.put("data", json);
//				resp.put("error", "");
//				resp.put("message", "Record updated successfully!..");
//				System.out.println(" Contractors updated successfully!..");
//				return resp;
//			} else {
//
//				// Check similar Contractor Name,Contractor short name is exist or not
//				CContractorsModel resultsContractorName = null;
//				if (cContractorsModel.getContractor_short_name() == null
//						|| cContractorsModel.getContractor_short_name().isEmpty()) {
//					resultsContractorName = iContractorsRepository.getCheck(cContractorsModel.getContractor_name(),
//							null, cContractorsModel.getCompany_id());
//				} else {
//					resultsContractorName = iContractorsRepository.getCheck(cContractorsModel.getContractor_name(),
//							cContractorsModel.getContractor_short_name(), cContractorsModel.getCompany_id());
//				}if (resultsContractorName != null) {
//					resp.put("success", 0);
//					resp.put("data", "");
//					resp.put("error", " Contractor Name or Short Name is already exist!");
//					return resp;
//				} else {
//
//					CContractorsModel json = iContractorsRepository.save(cContractorsModel);
//					resp.put("success", "1");
//					resp.put("data", json);
//					resp.put("error", "");
//					resp.put("message", "Record added successfully!..");
//					System.out.println(" Contractors saved succesfully!..");
//					return resp;
//				}
//			}
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/contractor/FnAddUpdateRecord",
//						sqlEx.getErrorCode(), sqlEx.getMessage());
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/contractor/FnAddUpdateRecord", 0,
//					e.getMessage());
//			resp.put("success", "0");
//			resp.put("data", "");
//			resp.put("error", e.getMessage());
//
//			return resp;
//
//		}
//		return resp;
//	}

	@Override
	public Map<String, Object> FnDeleteRecord(int contractor_id, String deleted_by) {
		Map<String, Object> resp = new HashMap<>();
		try {
			iContractorsRepository.FnDeleteRecord(contractor_id, deleted_by);
			icontractorsBankRepository.fnDeleteContractorBankRecord(contractor_id,deleted_by);
			resp.put("success", 1);
			resp.put("error", "");
			resp.put("message","Record Deleted Successfully..!");
			
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());
			}
		} catch (Exception e) {
			resp.put("success", 0);
			resp.put("error", "");
			e.printStackTrace();
		}
		return resp;
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CContractorsViewModel> data = iContractorsViewRepository.FnShowAllRecords(pageable);

			resp.put("success", "1");
			resp.put("error", "");

			return new Object[] { data, resp };

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

		return new Object[] { "", resp };

	}

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CContractorsViewModel> data = iContractorsViewRepository.FnShowAllActiveRecords(pageable);

			resp.put("success", "1");
			resp.put("error", "");

			return new Object[] { data, resp };

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

		return new Object[] { "", resp };

	}

	@Override
	public HashMap<String, Object> FnShowParticularRecordForUpdate(int contractor_id) {
		HashMap<String, Object> resp = new HashMap<>();
		try {

			CContractorsViewModel json = iContractorsViewRepository.FnShowParticularRecordForUpdate(contractor_id);

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
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public Map<String, Object> FnShowParticularRecord(int company_id, int contractor_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			CContractorsViewModel contractorDetails = iContractorsViewRepository.FnShowParticularRecord(company_id, contractor_id);
 			List<CContractorBankViewModel> contractorBankDetails = icontractorsBankRepository.FnShowParticularRecord(company_id, contractor_id);
 
			resp.put("success", 1);
			resp.put("contractorDetails", contractorDetails);
			resp.put("contractorBankDetails", contractorBankDetails);
 			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", 0);
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}
	
	
	
	
	
	
	
//	@Override
//	public Object FnShowParticularRecord(int company_id, int contractor_id) {
//
//		JSONObject resp = new JSONObject();
//		try {
//
//			CContractorsViewModel json = iContractorsViewRepository.FnShowParticularRecord(company_id,
//					company_branch_id, contractor_id);
//
//			System.out.println(json);
//			resp.put("success", "1");
//			resp.put("data", json);
//			resp.put("error", "");
//
//			return new Object[] { json, resp };
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return new Object[] { "", resp };
//
//	}

	@Override
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {

			Page<Map<String, Object>> data = iContractorsViewRepository.FnShowAllReportRecords(pageable);
			System.out.println(data);
			resp.put("success", "1");
			resp.put("error", "");

			return new Object[] { data, resp };

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

		return new Object[] { "", resp };
	}

}
