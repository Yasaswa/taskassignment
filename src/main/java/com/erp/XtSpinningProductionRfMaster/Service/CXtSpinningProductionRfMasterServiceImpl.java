package com.erp.XtSpinningProductionRfMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Properties.Model.CPropertiesViewModel;
import com.erp.Common.Properties.Repository.IPropertiesViewRepository;
import com.erp.Common.ReportColumns.ReportRecords;
import com.erp.XtSpinningProductionRfMaster.Model.*;
import com.erp.XtSpinningProductionRfMaster.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CXtSpinningProductionRfMasterServiceImpl implements IXtSpinningProductionRfMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXtSpinningProductionRfMasterRepository iXtSpinningProductionRfMasterRepository;

	@Autowired
	IXtSpinningProductionRfDetailsRepository iXtSpinningProductionRfDetailsRepository;

	@Autowired
	IXtSpinningProductionRfStoppageRepository iXtSpinningProductionRfStoppageRepository;

	@Autowired
	IXtSpinningProductionRfSummaryRepository iXtSpinningProductionRfSummaryRepository;

	@Autowired
	IXtSpinningProductionRfWastageRepository iXtSpinningProductionRfWastageRepository;

	@Autowired
	IXtSpinningProductionRfSummaryViewRepository iXtSpinningProductionRfSummaryViewRepository;

	@Autowired
	IXtSpinningProductionRfDetailsViewRepository iXtSpinningProductionRfDetailsViewRepository;

	@Autowired
	IXtSpinningProductionRfStoppageViewRepository iXtSpinningProductionRfStoppageViewRepository;

	@Autowired
	IXtSpinningProductionRfWastageViewRepository iXtSpinningProductionRfWastageViewRepository;

	@Autowired
	IPropertiesViewRepository iPropertiesViewRepository;

	@Autowired
	ReportRecords reportRecords;

	@PersistenceContext
	private EntityManager entityManager;


	// Method to get the previous shift number
	public static int getPreviousShift(int currentShift, int totalShifts) {
		if (currentShift <= 1) {
			return totalShifts;
		} else {
			return currentShift - 1;
		}
	}

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		// get CommonId's
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int spinning_production_rf_master_id = commonIdsObj.getInt("spinning_production_rf_master_id");

		AtomicInteger atomicProdRfId = new AtomicInteger(spinning_production_rf_master_id);

		// get master data
		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
		JSONObject spinProdSummaryjson = jsonObject.getJSONObject("TransSpinProdSummaryData");
		JSONArray spinProdStoppageArray = (JSONArray) jsonObject.get("TransSpinProdStoppageData");
		JSONArray spinProdWastagegeArray = (JSONArray) jsonObject.get("TransSpinProdWastageeData");

		try {

			// Get Spinning Production Rf master data
			CXtSpinningProductionRfMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
					CXtSpinningProductionRfMasterModel.class);

			CXtSpinningProductionRfMasterModel responceSpinningProductionRfMaster = iXtSpinningProductionRfMasterRepository
					.save(jsonModel); // saved spinning prod rf master data
			atomicProdRfId.set(responceSpinningProductionRfMaster.getSpinning_production_rf_master_id());

//			if (!detailsArray.isEmpty() && !spinProdSummaryArray.isEmpty() && !spinProdStoppageArray.isEmpty() && !spinProdWastagegeArray.isEmpty())
			if (!detailsArray.isEmpty()) {

				// Get Spinning Production Rf details Array
				// JSON array into a list of CXtSpinningProductionRfDetailsModel objects using an ObjectMapper
				List<CXtSpinningProductionRfDetailsModel> CXtSpinningProductionRfDetailsModel = objectMapper.readValue(
						detailsArray.toString(), new TypeReference<List<CXtSpinningProductionRfDetailsModel>>() {
						});

				CXtSpinningProductionRfDetailsModel.forEach(detailsitems -> {
					detailsitems.setSpinning_production_rf_details_id(0);
					detailsitems.setSpinning_production_rf_master_id(responceSpinningProductionRfMaster.getSpinning_production_rf_master_id());
				});

				// Saved Spinning Production Rf details
				iXtSpinningProductionRfDetailsRepository.saveAll(CXtSpinningProductionRfDetailsModel);//saved spinning

			}

			//Saved Spinning Production Rf Summary data
			CXtSpinningProductionRfSummaryModel jsonProdRfSummaryModel = objectMapper.readValue(spinProdSummaryjson.toString(),
					CXtSpinningProductionRfSummaryModel.class);

			//saved Spinning_production_rf_master_id in CXtSpinningProductionRfSummaryModel
			jsonProdRfSummaryModel.setSpinning_production_rf_master_id(responceSpinningProductionRfMaster.getSpinning_production_rf_master_id());

			iXtSpinningProductionRfSummaryRepository.save(jsonProdRfSummaryModel); // saved spinning prod rf Summary datas

			if (!spinProdStoppageArray.isEmpty()) {

				// Get Spinning Production Rf Stoppage Array
				// JSON array into a list of CXtSpinningProductionRfStoppageModel objects using an ObjectMapper
				List<CXtSpinningProductionRfStoppageModel> spinningProductionRfStoppageModel = objectMapper.readValue(
						spinProdStoppageArray.toString(),
						new TypeReference<List<CXtSpinningProductionRfStoppageModel>>() {
						});

				spinningProductionRfStoppageModel.forEach(stoppageitems -> {
					stoppageitems.setSpinning_production_rf_master_id(responceSpinningProductionRfMaster.getSpinning_production_rf_master_id());
				});

				// Saved Spinning Production Rf Stoppage Array
				iXtSpinningProductionRfStoppageRepository.saveAll(spinningProductionRfStoppageModel);// saved Stoppage details Array

			}

			if (!spinProdWastagegeArray.isEmpty()) {

				// Get Spinning Production Rf Wastage Array
				// JSON array into a list of CXtSpinningProductionRfWastageModel objects using an ObjectMapper
				List<CXtSpinningProductionRfWastageModel> spinningProductionRfWastageModel = objectMapper.readValue(
						spinProdWastagegeArray.toString(),
						new TypeReference<List<CXtSpinningProductionRfWastageModel>>() {
						});

				spinningProductionRfWastageModel.forEach(wastageitems -> {
					wastageitems.setSpinning_production_rf_master_id(responceSpinningProductionRfMaster.getSpinning_production_rf_master_id());

				});

				// Saved Spinning Production Rf Wastage Array
				iXtSpinningProductionRfWastageRepository.saveAll(spinningProductionRfWastageModel); // saved wastage details Array

			}

			responce.put("success", 1);
			responce.put("data", responceSpinningProductionRfMaster);
			responce.put("message", spinning_production_rf_master_id == 0 ? "Record added successfully!..."
					: "Record update successfully!...");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/XtSpinningProductionRfMaster/FnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XtSpinningProductionRfMaster/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int spinning_production_rf_master_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			// Delete spinning production rf master
			iXtSpinningProductionRfMasterRepository.FnDeleteSpinningProductionRfMasterRecord(spinning_production_rf_master_id, deleted_by);

			// Delete spinning production rf details Records
			iXtSpinningProductionRfDetailsRepository.FnDeleteSpinningProductionRfDetailsRecord(spinning_production_rf_master_id, deleted_by);

			// Delete spinning production rf stoppage Records
			iXtSpinningProductionRfStoppageRepository.FnDeleteSpinningProductionRfStoppageRecord(spinning_production_rf_master_id, deleted_by);

			// Delete spinning production rf summary Records
			iXtSpinningProductionRfSummaryRepository.FnDeleteSpinningProductionRfSummaryRecord(spinning_production_rf_master_id, deleted_by);

			// Delete spinning production rf wastage Records
			iXtSpinningProductionRfWastageRepository.FnDeleteSpinningProductionRfWastageRecord(spinning_production_rf_master_id, deleted_by);

			responce.put("success", 1);

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordUpdate(int spinning_production_rf_master_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			// fetch spinning production rf master Records
			Map<String, Object> spinningProductionRfMasterRecord = iXtSpinningProductionRfMasterRepository
					.FnShowspinningProductionRfMasterRecord(spinning_production_rf_master_id, company_id);

			// fetch spinning production rf details Records
			List<CXtSpinningProductionRfDetailsViewModel> spinningProductionRfDetailsRecord = iXtSpinningProductionRfDetailsViewRepository
					.FnShowspinningProductionRfDetailsRecord(spinning_production_rf_master_id, company_id);

			// fetch spinning production rf stoppage Records
			List<Map<String, Object>> spinningProductionRfStoppageRecord = iXtSpinningProductionRfStoppageViewRepository
					.FnShowspinningProductionRfStoppageRecord(spinning_production_rf_master_id, company_id);


			//Get Production shifts from property master
			List<CPropertiesViewModel> getShifts = iPropertiesViewRepository.FnShowParticularRecord("ProductionShifts", company_id);

			// fetch spinning production rf summary Records
			List<CXtSpinningProductionRfSummaryModel> spinningProductionRfSummaryRecord = iXtSpinningProductionRfSummaryRepository
					.FnShowSpinningProductionRfSummaryRecords(spinning_production_rf_master_id, company_id);

			Map<String, CXtSpinningProductionRfSummaryModel> shiftWiseSummary = new LinkedHashMap<>();

			// Sorting by the property_id using Stream API
			getShifts = getShifts.stream()
					.sorted(Comparator.comparingLong(CPropertiesViewModel::getProperty_id))
					.collect(Collectors.toList());

			getShifts.forEach(item -> {
				Optional<CXtSpinningProductionRfSummaryModel> summaryModel = spinningProductionRfSummaryRecord.stream()
						.filter(checkShift -> checkShift.getShift().equals(item.getProperty_name()))
						.findAny();
				if (summaryModel.isPresent()) {
					shiftWiseSummary.put(item.getProperty_name(), summaryModel.get());
				} else {
					CXtSpinningProductionRfSummaryModel cXtSpinningProductionRfSummaryModelAl = new CXtSpinningProductionRfSummaryModel(0, 0, 0, "", 0, "",
							"", 0, 0, 0, "",
							0, 0, item.getProperty_name(), 0, 0,
							0, 0, 0, 0,
							0, 0, 0, 0,
							0, 0, 0,
							0, 0, 0,
							0, false,
							false,
							"", null, "", null, "", null);
					shiftWiseSummary.put(item.getProperty_name(), cXtSpinningProductionRfSummaryModelAl);
				}
			});


			// fetch spinning production rf wastage Records
			List<Map<String, Object>> spinningProductionRfWastageRecord = iXtSpinningProductionRfWastageViewRepository
					.FnShowspinningProductionRfWastageRecord(spinning_production_rf_master_id, company_id);

			responce.put("SpinningProductionRfMasterRecord", spinningProductionRfMasterRecord);
			responce.put("SpinningProductionRfDetailsRecord", spinningProductionRfDetailsRecord);
			responce.put("SpinningProductionRfStoppageRecord", spinningProductionRfStoppageRecord);
			responce.put("SpinningProductionRfSummaryRecord", shiftWiseSummary);
			responce.put("SpinningProductionRfWastageRecord", spinningProductionRfWastageRecord);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowParticularShiftSummary(String shift, String spinning_production_date,
	                                                        int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Map<String, Map<String, Object>> shiftWiseSummary = new LinkedHashMap<>();

			//Get Production shifts from property master
			List<CPropertiesViewModel> getShifts = iPropertiesViewRepository.FnShowParticularRecord("ProductionShifts", company_id);

			List<CXtSpinningProductionRfSummaryModel> productionSummary = iXtSpinningProductionRfSummaryRepository.FnShowParticularShiftSummary(spinning_production_date, company_id);    // Get Ring frame Production summary


			// Sorting by the property_id using Stream API
			getShifts = getShifts.stream()
					.sorted(Comparator.comparingLong(CPropertiesViewModel::getProperty_id))
					.collect(Collectors.toList());

//			Iterate on production shifts
			getShifts.forEach(shifts -> {
				Map<String, Object> shiftObject = new LinkedHashMap<>();

				List<CXtSpinningProductionRfSummaryModel> getPerticularShiftsSummary = productionSummary.parallelStream()
						.filter(item -> item.getShift().equals(shifts.getProperty_name())).collect(Collectors.toList());

//				If particular shift data is present then fill this otherwise send it as 0
				if (getPerticularShiftsSummary.size() != 0) {
					Optional<CXtSpinningProductionRfSummaryModel> summaryModel = getPerticularShiftsSummary.stream()
							.filter(item -> item.getSpinning_production_date().equals(spinning_production_date)).findAny();

					if (summaryModel.isPresent()) {
						CXtSpinningProductionRfSummaryModel cXtSpinningProductionRfSummaryModel = summaryModel.get();
						shiftObject.put("shift", shifts.getProperty_name());
						shiftObject.put("no_of_machine", 0);
						shiftObject.put("shift_production_loss", cXtSpinningProductionRfSummaryModel.getShift_production_loss());
						shiftObject.put("shift_production_100", cXtSpinningProductionRfSummaryModel.getShift_production_100());
						shiftObject.put("shift_production_target", cXtSpinningProductionRfSummaryModel.getShift_production_target());
						shiftObject.put("shift_production_actual", cXtSpinningProductionRfSummaryModel.getShift_production_actual());
						shiftObject.put("shift_efficiency_percent", cXtSpinningProductionRfSummaryModel.getShift_efficiency_percent());
						shiftObject.put("shift_stopage_time", cXtSpinningProductionRfSummaryModel.getShift_stopage_time());
						shiftObject.put("shift_wastage_kgs", cXtSpinningProductionRfSummaryModel.getShift_wastage_kgs());
						shiftObject.put("shift_spindles_utilization", cXtSpinningProductionRfSummaryModel.getShift_spindles_utilization());

						shiftObject.put("shift_production_upto_date_loss", cXtSpinningProductionRfSummaryModel.getShift_production_upto_date_loss());
						shiftObject.put("shift_production_upto_date_100", cXtSpinningProductionRfSummaryModel.getShift_production_upto_date_100());
						shiftObject.put("shift_production_upto_date_target", cXtSpinningProductionRfSummaryModel.getShift_production_upto_date_target());
						shiftObject.put("shift_production_upto_date_actual", cXtSpinningProductionRfSummaryModel.getShift_production_upto_date_actual());
						shiftObject.put("shift_efficiency_upto_date_percent", cXtSpinningProductionRfSummaryModel.getShift_efficiency_upto_date_percent());
						shiftObject.put("shift_stopage_upto_date_time", cXtSpinningProductionRfSummaryModel.getShift_stopage_upto_date_time());
						shiftObject.put("shift_wastage_upto_date_kgs", cXtSpinningProductionRfSummaryModel.getShift_wastage_upto_date_kgs());
						shiftObject.put("shift_spindles_upto_date_utilization", cXtSpinningProductionRfSummaryModel.getShift_spindles_upto_date_utilization());
					} else {
						Optional<CXtSpinningProductionRfSummaryModel> model = getPerticularShiftsSummary.stream()
								.filter(item -> !(item.getSpinning_production_date().equals(spinning_production_date)))
								.findAny();
						CXtSpinningProductionRfSummaryModel cXtSpinningProductionRfSummaryModel = model.get();

						shiftObject.put("shift", shifts.getProperty_name());
						shiftObject.put("shift_production_loss", 0);
						shiftObject.put("shift_production_100", 0);
						shiftObject.put("shift_production_target", 0);
						shiftObject.put("shift_production_actual", 0);
						shiftObject.put("shift_efficiency_percent", 0);
						shiftObject.put("shift_stopage_time", 0);
						shiftObject.put("shift_wastage_kgs", 0);
						shiftObject.put("shift_spindles_utilization", 0);

						shiftObject.put("shift_production_upto_date_loss", cXtSpinningProductionRfSummaryModel.getShift_production_upto_date_loss());
						shiftObject.put("shift_production_upto_date_100", cXtSpinningProductionRfSummaryModel.getShift_production_upto_date_100());
						shiftObject.put("shift_production_upto_date_target", cXtSpinningProductionRfSummaryModel.getShift_production_upto_date_target());
						shiftObject.put("shift_production_upto_date_actual", cXtSpinningProductionRfSummaryModel.getShift_production_upto_date_actual());
						shiftObject.put("shift_efficiency_upto_date_percent", cXtSpinningProductionRfSummaryModel.getShift_efficiency_upto_date_percent());
						shiftObject.put("shift_stopage_upto_date_time", cXtSpinningProductionRfSummaryModel.getShift_stopage_upto_date_time());
						shiftObject.put("shift_wastage_upto_date_kgs", cXtSpinningProductionRfSummaryModel.getShift_wastage_upto_date_kgs());
						shiftObject.put("shift_spindles_upto_date_utilization", cXtSpinningProductionRfSummaryModel.getShift_spindles_upto_date_utilization());

					}
				} else {
					shiftObject.put("shift", shifts.getProperty_name());
					shiftObject.put("no_of_machine", 0);
					shiftObject.put("shift_production_loss", 0);
					shiftObject.put("shift_production_100", 0);
					shiftObject.put("shift_production_target", 0);
					shiftObject.put("shift_production_actual", 0);
					shiftObject.put("shift_efficiency_percent", 0);
					shiftObject.put("shift_stopage_time", 0);
					shiftObject.put("shift_wastage_kgs", 0);
					shiftObject.put("shift_spindles_utilization", 0);

					shiftObject.put("shift_production_upto_date_loss", 0);
					shiftObject.put("shift_production_upto_date_100", 0);
					shiftObject.put("shift_production_upto_date_target", 0);
					shiftObject.put("shift_production_upto_date_actual", 0);
					shiftObject.put("shift_efficiency_upto_date_percent", 0);
					shiftObject.put("shift_stopage_upto_date_time", 0);
					shiftObject.put("shift_wastage_upto_date_kgs", 0);
					shiftObject.put("shift_spindles_upto_date_utilization", 0);
				}

				shiftWiseSummary.put(shifts.getProperty_name(), shiftObject);
			});

//			Get particular shift object for fetch shift no
			Optional<CPropertiesViewModel> getParticularShiftObj = getShifts.stream().filter(item -> item.getProperty_name().equals(shift)).findFirst();
			if (getParticularShiftObj.isPresent()) {
				int shiftNo = getPreviousShift(Integer.parseInt(getParticularShiftObj.get().getProperty_value()), getShifts.size());  // Get previous shift no
				Optional<CPropertiesViewModel> getShiftName = getShifts.stream().filter(item -> item.getProperty_value().equals(String.valueOf(shiftNo))).findFirst();
				if (getShiftName.isPresent()) {
					String sqlQuery = "SELECT * " +
							"FROM xtv_spinning_production_rf_details " +
							"WHERE ABS(DATEDIFF(spinning_production_date, :targetDate)) = (" +
							"    SELECT MIN(ABS(DATEDIFF(spinning_production_date, :targetDate))) " +
							"    FROM xtv_spinning_production_rf_details" +
							") and shift = :shift and company_id = :company_id ";
					List<CXtSpinningProductionRfDetailsViewModel> getLastShiftProductionDetails = entityManager.createNativeQuery(sqlQuery, CXtSpinningProductionRfDetailsViewModel.class)
							.setParameter("targetDate", spinning_production_date)
							.setParameter("shift", getShiftName.get().getProperty_name())
							.setParameter("company_id", company_id)
							.getResultList();

					response.put("LastShiftProductionDetails", getLastShiftProductionDetails);
				}
			}


			response.put("success", 1);
			response.put("data", shiftWiseSummary);
			response.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/XtSpinningProductionRfMaster/FnShowParticularShiftSummary", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				response.put("success", 0);
				response.put("data", "");
				response.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XtSpinningProductionRfMaster/FnShowParticularShiftSummary", 0, e.getMessage());
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());

		}
		return response;
	}

	@Override
	public Map<String, Object> FnShowProductionReport(Map<String, Object> reportRequest, HttpServletResponse httpServletResponse) {
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			Map<String, List<Map<String, Object>>> productionReportDetails = new LinkedHashMap<>();

			String spinning_production_date = (String) reportRequest.get("spinning_production_date");
			int plant_id = Integer.parseInt((String) reportRequest.get("plant_id"));
			Object companyIdObj = reportRequest.get("company_id");
			AtomicInteger company_id = new AtomicInteger();

			if (companyIdObj instanceof Integer) {
				company_id.set((Integer) companyIdObj);
			} else if (companyIdObj instanceof String) {
				company_id.set(Integer.parseInt((String) companyIdObj));
			}
//          Get all ring frame details plant & date wise
			List<CXtSpinningProductionRfDetailsViewModel> ringFrameProductionDetails = iXtSpinningProductionRfDetailsViewRepository
					.FnShowRingFrameDetailsRecord(plant_id, spinning_production_date, company_id.get());

//			Get Production shifts from property master
			List<CPropertiesViewModel> getShifts = iPropertiesViewRepository.FnShowParticularRecord("ProductionShifts", company_id.get());

//			Get distinct machine no's
			List<String> distinctMachineNos = ringFrameProductionDetails.stream().map(CXtSpinningProductionRfDetailsViewModel::getMachine_short_name)
					.distinct().collect(Collectors.toList());

			// Convert list of objects to list of maps
			List<Map<String, Object>> productionDetails = ringFrameProductionDetails.stream()
					.map(obj -> {
						Map<String, Object> map = new LinkedHashMap<>();
						// Use reflection to dynamically get all fields of the object
						Field[] fields = obj.getClass().getDeclaredFields();
						for (Field field : fields) {
							field.setAccessible(true); // Ensure private fields can be accessed
							try {
								// Map field name to its value in the object
								map.put(field.getName(), field.get(obj));
							} catch (IllegalAccessException e) {
								e.printStackTrace(); // Handle the exception as needed
							}
						}
						return map;
					}).collect(Collectors.toList());

//			Header part details for excel
			Map<String, Object> headerPartDetails = new HashMap<>();

			if (productionDetails.size() != 0) {
				Map<String, Object> headerDetails = productionDetails.get(0);
				headerPartDetails.put("spinning_production_date", headerDetails.get("spinning_production_date"));
				headerPartDetails.put("plant_name", headerDetails.get("plant_name"));
				headerPartDetails.put("company_name", headerDetails.get("company_name"));
				headerPartDetails.put("company_branch_name", headerDetails.get("company_branch_name"));
				headerPartDetails.put("company_address1", headerDetails.get("company_address1"));
				headerPartDetails.put("company_address2", headerDetails.get("company_address2"));

			}

//			Iterate on machines
			distinctMachineNos.forEach(machine -> {
				Map<String, Object> stringListMap = new LinkedHashMap<>();

//				 Filter production details machine wise
				List<Map<String, Object>> filteredProductionDetails = productionDetails.stream()
						.filter(item -> item.get("machine_short_name").equals(machine))
						.collect(Collectors.toList());

				stringListMap.put("production_details", filteredProductionDetails);

//              Calculate totals machine wise for all shift
				Predicate<CXtSpinningProductionRfDetailsViewModel> predicate = obj -> obj.getMachine_short_name().equals(machine);
				Map<String, Object> totalsMap = calculateTotals(ringFrameProductionDetails, predicate);
				stringListMap.put("totals", totalsMap);

				productionReportDetails.put(machine, Collections.singletonList(stringListMap));
			});

			// Day Totals of machines
			// Using reduce to find the sum of all elements
			double total_kgs_100 = ringFrameProductionDetails.stream().map(obj -> obj.getKgs_100()).reduce(0.0, Double::sum);
			double total_target_kgs = ringFrameProductionDetails.stream().map(obj -> obj.getKgs_target()).reduce(0.0, Double::sum);
			double total_actual_kgs = ringFrameProductionDetails.stream().map(obj -> obj.getKgs_actual()).reduce(0.0, Double::sum);
			double total_eff_percent = ringFrameProductionDetails.stream().map(obj -> obj.getEfficiency_percent()).reduce(0.0, Double::sum);
			double total_stoppage_time = ringFrameProductionDetails.stream().map(obj -> obj.getTotal_stopage_time()).reduce(0.0, Double::sum);
			double total_prod_loss = ringFrameProductionDetails.stream().map(obj -> obj.getProd_loss_with_reason() + obj.getProd_loss_with_out_reason()).reduce(0.0, Double::sum);

			Map<String, Object> dayTotalsMap = new HashMap<>();

			// Assign calculated dayTotalsMap to the map
			dayTotalsMap.put("kgs_100", total_kgs_100);
			dayTotalsMap.put("kgs_target", total_target_kgs);
			dayTotalsMap.put("kgs_actual", total_actual_kgs);
			dayTotalsMap.put("efficiency_percent", total_eff_percent);
			dayTotalsMap.put("stoppage_time", total_stoppage_time);
			dayTotalsMap.put("prod_loss", total_prod_loss);

			productionReportDetails.put("dayTotals", Collections.singletonList(dayTotalsMap));

			getShifts.forEach(shiftObj -> {
				String shift = shiftObj.getProperty_name();

//              Calculate totals  for all machine & shift
				Predicate<CXtSpinningProductionRfDetailsViewModel> predicate = obj -> obj.getShift().equals(shift);
				Map<String, Object> totalsMap = calculateTotals(ringFrameProductionDetails, predicate);

//				Add this shift wise all machine production into productionReportDetails Linked map
				productionReportDetails.put(shift, Collections.singletonList(totalsMap));


			});

//			Get data from xtv_spinning_production_rf_report_rpt for columns refrence
			Map<String, Object> getReportColumns = reportRecords.FnShowAllReportRecords("details", "xtv_spinning_production_rf_report_rpt");

			Map<String, Object> content = (Map<String, Object>) getReportColumns.get("content");  // Get content from getReportColumns

			List<Map<String, String>> columnsObject = new ArrayList<>();         // Initialize list to store  {Header: 'M/C No', accessor: 'machine_short_name'}
			List<Map<String, String>> totalsColumnsObject = new ArrayList<>();

//			Iterate on content & split to get accessor & display column name
			content.forEach((key, value) -> {
				String stringValue = String.valueOf(value);
				String[] parts = stringValue.split(":");
				Map<String, String> newEntry = new HashMap<>();

				if ("totals".equals(parts[0])) {
					newEntry.put("Header", parts[1]);
					newEntry.put("accessor", key);

					totalsColumnsObject.add(newEntry);
				} else {
					newEntry.put("Header", parts[1]);
					newEntry.put("accessor", key);

					columnsObject.add(newEntry);
				}

			});

			response.put("productionReportDetails", productionReportDetails);
			response.put("productionReportColumns", columnsObject);
			response.put("productionReportTotalColumns", totalsColumnsObject);
			response.put("productionShift", getShifts);
			response.put("machines", distinctMachineNos);
			response.put("headerPartDetails", headerPartDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Map<String, Object> calculateTotals(List<CXtSpinningProductionRfDetailsViewModel> ringFrameProductionDetails,
	                                            Predicate<CXtSpinningProductionRfDetailsViewModel> predicate) {
		Map<String, Object> totalsMap = new HashMap<>();

		double total_kgs_100 = ringFrameProductionDetails.stream()
				.filter(predicate)
				.mapToDouble(CXtSpinningProductionRfDetailsViewModel::getKgs_100)
				.sum();

		double total_target_kgs = ringFrameProductionDetails.stream()
				.filter(predicate)
				.mapToDouble(CXtSpinningProductionRfDetailsViewModel::getKgs_target)
				.sum();

		double total_actual_kgs = ringFrameProductionDetails.stream()
				.filter(predicate)
				.mapToDouble(CXtSpinningProductionRfDetailsViewModel::getKgs_actual)
				.sum();

		double total_eff_percent = ringFrameProductionDetails.stream()
				.filter(predicate)
				.mapToDouble(CXtSpinningProductionRfDetailsViewModel::getEfficiency_percent)
				.sum();

		double total_stoppage_time = ringFrameProductionDetails.stream()
				.filter(predicate)
				.mapToDouble(CXtSpinningProductionRfDetailsViewModel::getTotal_stopage_time)
				.sum();

		double total_prod_loss = ringFrameProductionDetails.stream()
				.filter(predicate)
				.mapToDouble(obj -> obj.getProd_loss_with_reason() + obj.getProd_loss_with_out_reason())
				.sum();

		// Assign calculated totals to the map
		totalsMap.put("total_kgs_100", total_kgs_100);
		totalsMap.put("total_kgs_target", total_target_kgs);
		totalsMap.put("total_kgs_actual", total_actual_kgs);
		totalsMap.put("total_efficiency_percent", total_eff_percent);
		totalsMap.put("total_stoppage_time", total_stoppage_time);
		totalsMap.put("total_prod_loss", total_prod_loss);

		return totalsMap;
	}


}
