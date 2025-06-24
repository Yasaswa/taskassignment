package com.erp.XtSpinningProdSpinnPlanMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XtSpinningProdSpinnPlanMaster.Model.CXtSpinningProdSpinnPlanDetailsModel;
import com.erp.XtSpinningProdSpinnPlanMaster.Model.CXtSpinningProdSpinnPlanDetailsViewModel;
import com.erp.XtSpinningProdSpinnPlanMaster.Model.CXtSpinningProdSpinnPlanMasterModel;
import com.erp.XtSpinningProdSpinnPlanMaster.Repository.IXtSpinningProdSpinnPlanDetailsRepository;
import com.erp.XtSpinningProdSpinnPlanMaster.Repository.IXtSpinningProdSpinnPlanDetailsViewRepository;
import com.erp.XtSpinningProdSpinnPlanMaster.Repository.IXtSpinningProdSpinnPlanMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CXtSpinningProdSpinnPlanMasterServiceImpl implements IXtSpinningProdSpinnPlanMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXtSpinningProdSpinnPlanMasterRepository iXtSpinningProdSpinnPlanMasterRepository;

	@Autowired
	IXtSpinningProdSpinnPlanDetailsRepository iXtSpinningProdSpinnPlanDetailsRepository;

	@Autowired
	IXtSpinningProdSpinnPlanDetailsViewRepository iXtSpinningProdSpinnPlanDetailsViewRepository;

	@Override
	@Transactional
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String ACFlag) {
		Map<String, Object> responce = new HashMap<>();

		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");

		try {
			JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
			JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
			JSONArray prodCountKeyArray = (JSONArray) jsonObject.get("saveProdCountKey");
			JSONObject machineIdsJson = jsonObject.getJSONObject("MachineIds");

			Map<String, Object> reportRequest = objectMapper.readValue(machineIdsJson.toString(), Map.class);

			CXtSpinningProdSpinnPlanMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
					CXtSpinningProdSpinnPlanMasterModel.class);

			CXtSpinningProdSpinnPlanMasterModel respSpinningProdSpinnPlanMasterModel = iXtSpinningProdSpinnPlanMasterRepository.save(jsonModel);

//          Convert JSONArray to HashMap
			HashMap<String, Map<String, Integer>> hashMap = new LinkedHashMap<>();

//			Iterate on prodCountKeyArray put production count & their target levels
			prodCountKeyArray.forEach(item -> {
				JSONObject cchObject = (JSONObject) item;

				cchObject.keySet().forEach(productionCount -> {
					Map<String, Integer> tempObject = new LinkedHashMap<>();

					JSONObject productionCountObj = (JSONObject) cchObject.get(productionCount);

					productionCountObj.keySet().forEach(objTargetlvlKey -> {
						Object value = productionCountObj.get(objTargetlvlKey);
						int count_id = Integer.parseInt(value.toString());

						// Find the index of the first underscore
						int underscoreIndex = objTargetlvlKey.indexOf('_');

						// Check if underscore exists and it's not the first or last character
						if (underscoreIndex != -1 && underscoreIndex > 0 && underscoreIndex < objTargetlvlKey.length() - 1) {
							// Extract the substring before the underscore
							String firstPart = objTargetlvlKey.substring(0, underscoreIndex);

							// Extract the substring after the underscore
							String secondPart = objTargetlvlKey.substring(underscoreIndex + 1);

							tempObject.put(secondPart, count_id);
						} else {
							System.out.println("objTargetlvlKey format is incorrect.");
						}

					});

					hashMap.put(productionCount, tempObject);
				});
			});


			if (!ACFlag.equals("approve") && !ACFlag.equals("Cancel")) {
				hashMap.forEach((key, keyValuePairs) -> {

					List<CXtSpinningProdSpinnPlanDetailsModel> addSpinningPlanDetails = new LinkedList<>();

					for (Map.Entry<String, Integer> entry : keyValuePairs.entrySet()) {
						String targetLevel = entry.getKey();
						Integer production_count_id = entry.getValue();

						detailsArray.forEach(obj -> {
							if (obj instanceof JSONObject) {
								CXtSpinningProdSpinnPlanDetailsModel cXtSpinningProdSpinnPlanDetailsModel = new CXtSpinningProdSpinnPlanDetailsModel();
								JSONObject spinningProdSpinnPlanDetails = (JSONObject) obj;
								Object valueObj = spinningProdSpinnPlanDetails.get("production_actual_count_" + key.replaceAll(" ", "_") + "_" + targetLevel);
								if (valueObj instanceof String) {
									String valueStr = (String) valueObj;
									cXtSpinningProdSpinnPlanDetailsModel.setProduction_spinning_plan_parameter_value(Double.parseDouble(valueStr));
								} else if (valueObj instanceof Number) {
									// If it's already a number, you may directly cast it to Double
									Double valueDouble = ((Number) valueObj).doubleValue();
									cXtSpinningProdSpinnPlanDetailsModel.setProduction_spinning_plan_parameter_value(valueDouble);
								}

								JSONObject detailObject = (JSONObject) obj;
								System.out.println("Plan parameter name: " + detailObject.getString("production_spinning_plan_parameter_name"));

								Integer production_spinning_plan_parameter_id = detailObject.getInt("production_spinning_plan_parameter_id");
								Integer section_id = detailObject.getInt("production_section_id");
								Integer sub_section_id = detailObject.getInt("production_sub_section_id");
								String production_spinning_plan_parameter_name = detailObject.getString("production_spinning_plan_parameter_name");

								Double production_spinning_plan_parameter_sequance = null;
								Object sequanceValueObj = detailObject.get("production_spinning_plan_parameter_sequance");
								if (sequanceValueObj instanceof String) {
									// Attempt to parse the string value to a Double
									try {
										production_spinning_plan_parameter_sequance = Double.parseDouble((String) sequanceValueObj);
									} catch (NumberFormatException e) {
										// Handle the case when the string value is not a valid representation of a Double
										System.err.println("Invalid value for production_spinning_plan_parameter_sequance: " + sequanceValueObj);
									}
								} else if (sequanceValueObj instanceof Number) {
									// If it's already a number, you may directly cast it to Double
									production_spinning_plan_parameter_sequance = ((Number) sequanceValueObj).doubleValue();
									cXtSpinningProdSpinnPlanDetailsModel.setProduction_spinning_plan_parameter_sequance(production_spinning_plan_parameter_sequance);

								}

								cXtSpinningProdSpinnPlanDetailsModel.setSpinn_plan_transaction_id(0);
								cXtSpinningProdSpinnPlanDetailsModel.setPlant_id(jsonModel.getPlant_id());
								cXtSpinningProdSpinnPlanDetailsModel.setSection_id(section_id);
								cXtSpinningProdSpinnPlanDetailsModel.setSub_section_id(sub_section_id);
								cXtSpinningProdSpinnPlanDetailsModel.setProduction_count_id(production_count_id);
								cXtSpinningProdSpinnPlanDetailsModel.setProduction_count_target_level(targetLevel);
								cXtSpinningProdSpinnPlanDetailsModel.setSpinn_plan_code(jsonModel.getSpinn_plan_code());
								cXtSpinningProdSpinnPlanDetailsModel.setProduction_spinning_plan_parameter_id(production_spinning_plan_parameter_id);
								cXtSpinningProdSpinnPlanDetailsModel.setProduction_spinning_plan_parameter_name(production_spinning_plan_parameter_name);
								cXtSpinningProdSpinnPlanDetailsModel.setProduction_spinning_plan_parameter_sequance(production_spinning_plan_parameter_sequance);
								cXtSpinningProdSpinnPlanDetailsModel.setSpinn_plan_id(jsonModel.getSpinn_plan_id());
								cXtSpinningProdSpinnPlanDetailsModel.setPlan_totals(detailObject.has("total") ? detailObject.getDouble("total") : 0);
								cXtSpinningProdSpinnPlanDetailsModel.setProduction_spin_plan_input(detailObject.has("input") ? detailObject.getDouble("input") : 0);
								cXtSpinningProdSpinnPlanDetailsModel.setProduction_spinning_plan_machine_ids((String) reportRequest.get(key + "_" + targetLevel));
								cXtSpinningProdSpinnPlanDetailsModel.setCompany_id(jsonModel.getCompany_id());
								cXtSpinningProdSpinnPlanDetailsModel.setCompany_branch_id(jsonModel.getCompany_branch_id());
								cXtSpinningProdSpinnPlanDetailsModel.setCreated_by(jsonModel.getCreated_by());
								cXtSpinningProdSpinnPlanDetailsModel.setModified_by(jsonModel.getModified_by());
								cXtSpinningProdSpinnPlanDetailsModel.setDeleted_by(jsonModel.getDeleted_by());

								addSpinningPlanDetails.add(cXtSpinningProdSpinnPlanDetailsModel);

							}

						});
					}
					iXtSpinningProdSpinnPlanDetailsRepository.saveAll(addSpinningPlanDetails);
				});

			}
			responce.put("data", respSpinningProdSpinnPlanMasterModel);
			responce.put("success", 1);
			responce.put("error", "");
			responce.put("message", ACFlag.equals("approve") || ACFlag.equals("Cancel")
					? "Record " + ACFlag + " successfully!..."
					: "Record added successfully!...");
		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/XtSpinningProdSpinnPlanMaster/FnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XtSpinningProdSpinnPlanMaster/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int spinn_plan_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iXtSpinningProdSpinnPlanMasterRepository.FnDeleteSpinningProdSpinnPlanMasterRecords(spinn_plan_id,
					deleted_by);
			iXtSpinningProdSpinnPlanDetailsRepository.FnDeleteSpinningProdSpinnPlanDetailsRecord(spinn_plan_id,
					deleted_by);
			responce.put("success", 1);

		} catch (Exception e) {
			responce.put("success", 0);
			responce.put("error", e.getMessage());
			return responce;
		}

		return responce;
	}

	@Override
	public Map<String, Object> FnShowMasterAndDetailsModelRecordForUpdate(JSONObject jsonObject) {
		Map<String, Object> responce = new LinkedHashMap<>();
		JSONObject json = jsonObject.getJSONObject("SppinProdSpinPlanData");

		JSONObject commonIdsObj = (JSONObject) json.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int plant_id = commonIdsObj.getInt("plant_id");
		String spinn_plan_code = commonIdsObj.getString("spinn_plan_code");

		try {
//			Get production plan details from xtv_spinning_prod_spinn_plan_details
			List<CXtSpinningProdSpinnPlanDetailsViewModel> spinningProdPlanDetails = iXtSpinningProdSpinnPlanDetailsViewRepository.FnShowSpinningProdPlanDetailsRecord(plant_id, spinn_plan_code, company_id);

//			Create group using production count Id
			Map<Integer, List<CXtSpinningProdSpinnPlanDetailsViewModel>> groupedByProductionCountId = spinningProdPlanDetails.stream()
					.collect(Collectors.groupingBy(CXtSpinningProdSpinnPlanDetailsViewModel::getProduction_count_id));

			List<Map<String, Object>> addDetails = new ArrayList<>();

			// Iterate on production count group for creation of list json to display production plan
			for (Map.Entry<Integer, List<CXtSpinningProdSpinnPlanDetailsViewModel>> entry : groupedByProductionCountId.entrySet()) {
				Integer productionCountId = entry.getKey();
				List<CXtSpinningProdSpinnPlanDetailsViewModel> detailsList = entry.getValue();

				System.out.println("productionCountId:" + productionCountId);
				for (CXtSpinningProdSpinnPlanDetailsViewModel details : detailsList) {
					// Check if viewModelMap already contains an entry with the given parameter name
					Optional<Map<String, Object>> foundDetail = addDetails.stream()
							.filter(item -> {
								Object paramName = item.get("production_spinning_plan_parameter_name");
								return paramName != null && paramName.equals(details.getProduction_spinning_plan_parameter_name());
							})
							.findFirst();

					Map<String, Object> viewModelMap;
					if (foundDetail.isPresent()) {
						// If the detail is found, update its value
						viewModelMap = foundDetail.get();

						viewModelMap.put("input", details.getProduction_spin_plan_input());
						viewModelMap.put(details.getProduction_count_name()  + "_" + details.getProduction_count_target_level(), details.getProduction_spinning_plan_parameter_value());

					} else {
						// If the detail is not found, create a new map
						viewModelMap = new LinkedHashMap<>();
						// Populate viewModelMap with common details
						viewModelMap.put("spinn_plan_code", details.getSpinn_plan_code());
						viewModelMap.put("spinn_plan_month", details.getSpinn_plan_month());
						viewModelMap.put("spinn_plan_year", details.getSpinn_plan_year());
						viewModelMap.put("spinn_plan_start_date", details.getSpinn_plan_start_date());
						viewModelMap.put("spinn_plan_end_date", details.getSpinn_plan_end_date());
						viewModelMap.put("plant_name", details.getPlant_name());
						viewModelMap.put("plant_id", details.getPlant_id());
						viewModelMap.put("customer_id", details.getCustomer_id());
						viewModelMap.put("spinn_plan_created_by_id", details.getSpinn_plan_created_by_id());
						viewModelMap.put("spinn_plan_approved_by_id", details.getSpinn_plan_approved_by_id());
						viewModelMap.put("spinn_plan_description", details.getSpinn_plan_description());
						viewModelMap.put("spinn_plan_avg_count", details.getSpinn_plan_avg_count());
						viewModelMap.put("spinn_plan_status", details.getSpinn_plan_status());
						viewModelMap.put("production_section_name", details.getProduction_section_name());
						viewModelMap.put("production_sub_section_name", details.getProduction_sub_section_name());
						viewModelMap.put("section_id", details.getSection_id());
						viewModelMap.put("sub_section_id", details.getSub_section_id());
						viewModelMap.put("spinn_plan_id", details.getSpinn_plan_id());
						viewModelMap.put("production_spinning_plan_parameter_name", details.getProduction_spinning_plan_parameter_name());
						viewModelMap.put("production_spinning_plan_parameter_value", details.getProduction_spinning_plan_parameter_value());
						viewModelMap.put("production_spinning_plan_parameter_sequance", details.getProduction_spinning_plan_parameter_sequance());
						viewModelMap.put(details.getProduction_count_name() + "_" + details.getProduction_count_target_level(), details.getProduction_spinning_plan_parameter_value());
						viewModelMap.put("total", details.getPlan_totals());
						viewModelMap.put("input", details.getProduction_spin_plan_input());
						viewModelMap.put("production_spinning_plan_parameter_id", details.getProduction_spinning_plan_parameter_id());
						viewModelMap.put("spinn_plan_transaction_id", details.getSpinn_plan_transaction_id());
					}
					// Add viewModelMap to addDetails only if it's not present
					if (!foundDetail.isPresent()) {
						addDetails.add(viewModelMap);
					}
				}
			}

//			Get distinct production count name using java 8 stream
			List<String> productionCountNameList = spinningProdPlanDetails.stream()
					.map(detail -> detail.getProduction_count_name() + "_" + detail.getProduction_count_target_level())
					.distinct()
					.collect(Collectors.toList());

			responce.put("success", 1);
			responce.put("spinningPlanDetails", addDetails);
			responce.put("productionCountNameList", productionCountNameList);


		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

}
