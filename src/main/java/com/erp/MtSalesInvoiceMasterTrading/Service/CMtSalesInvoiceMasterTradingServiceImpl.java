package com.erp.MtSalesInvoiceMasterTrading.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmCompanySettings.Model.CAmCompanySettingsModel;
import com.erp.AmCompanySettings.Model.CAmCompanySettingsViewModel;
import com.erp.AmCompanySettings.Repository.IAmCompanySettingsRepository;
import com.erp.AmCompanySettings.Repository.IAmCompanySettingsViewRepository;
import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanDetailsTradingModel;
import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanDetailsTradingViewModel;
import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanMasterTradingModel;
import com.erp.MtDispatchChallanDetails.Repository.IMtDispatchChallanDetailsTradingRepository;
import com.erp.MtDispatchChallanDetails.Repository.IMtDispatchChallanDetailsTradingViewRepository;
import com.erp.MtDispatchChallanDetails.Repository.IMtDispatchChallanMasterTradingRepository;
import com.erp.MtSalesInvoiceMasterTrading.Model.*;
import com.erp.MtSalesInvoiceMasterTrading.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class CMtSalesInvoiceMasterTradingServiceImpl implements IMtSalesInvoiceMasterTradingService {

	private final RestTemplate restTemplate;

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtSalesInvoiceMasterTradingRepository iMtSalesInvoiceMasterTradingRepository;

	@Autowired
	IMtSalesInvoiceMasterTradingViewRepository iMtSalesInvoiceMasterTradingViewRepository;

	@Autowired
	IMtSalesInvoiceDetailsTradingViewRepository iMtSalesInvoiceDetailsTradingViewRepository;

	@Autowired
	IMtSalesInvoiceDetailsTradingRepository iMtSalesInvoiceDetailsTradingRepository;

	@Autowired
	IMtSalesInvoiceTaxSummaryRepository iMtSalesInvoiceTaxSummaryRepository;

	@Autowired
	IMtSalesInvoiceTaxSummaryViewRepository iMtSalesInvoiceTaxSummaryViewRepository;

	@Autowired
	IMtDispatchChallanDetailsTradingViewRepository iMtDispatchChallanDetailsTradingViewRepository;

	@Autowired
	IMtDispatchChallanDetailsTradingRepository iMtDispatchChallanDetailsTradingRepository;

	@Autowired
	IMtDispatchChallanMasterTradingRepository iMtDispatchChallanMasterTradingRepository;

	@Autowired
	IMtSalesInvoiceTermsTradingRepository iMtSalesInvoiceTermsTradingRepository;

	@Autowired
	IAmCompanySettingsViewRepository iAmCompanySettingsViewRepository;

	@Autowired
	IAmCompanySettingsRepository iAmCompanySettingsRepository;

	public CMtSalesInvoiceMasterTradingServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private static boolean FnIsValidDateTime(LocalDateTime dateTime) {
		// Add your validation logic here
		// For example, you might check if the date is in the future or has already passed.
		LocalDateTime currentDateTime = LocalDateTime.now();
		return dateTime.isAfter(currentDateTime);
	}

	@Override
	@Transactional
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String sales_invoice_no = commonIdsObj.getString("sales_invoice_no");
		int sales_invoice_version = commonIdsObj.getInt("sales_invoice_version");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray detailsData = (JSONArray) jsonObject.get("TransDetailData");
		JSONArray TaxSummaryArray = (JSONArray) jsonObject.get("TransTaxSummaryData");
		JSONArray commonTerms = (JSONArray) jsonObject.get("TransCommonTermsData");

		try {
			CMtSalesInvoiceMasterTradingModel jsonModel = objectMapper.readValue(masterjson.toString(),
					CMtSalesInvoiceMasterTradingModel.class);

			// Sales Invoice Trading Master
			CMtSalesInvoiceMasterTradingModel getExistingRecord = iMtSalesInvoiceMasterTradingRepository
					.getExistingRecord(sales_invoice_no, jsonModel.getSales_invoice_version(), financial_year,
							company_id);

			if (getExistingRecord != null) {
				update = true;
				getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
				getExistingRecord.setDeleted_on(new Date());
				getExistingRecord.setIs_delete(true);
				iMtSalesInvoiceMasterTradingRepository.save(getExistingRecord);

				jsonModel.setSales_invoice_version(getExistingRecord.getSales_invoice_version() + 1);

			}
			CMtSalesInvoiceMasterTradingModel responseSalesInvoiceMasterRecords = iMtSalesInvoiceMasterTradingRepository
					.save(jsonModel);

			// Sales Invoice Trading Details
			if (update) {
				iMtSalesInvoiceDetailsTradingRepository.FnUpdateSalesInvoiceDetailsRecord(sales_invoice_no,
						sales_invoice_version, company_id);
			}
			List<CMtSalesInvoiceDetailsTradingModel> cmtSalesInvoiceDetailsTradingModel = objectMapper
					.readValue(detailsData.toString(), new TypeReference<List<CMtSalesInvoiceDetailsTradingModel>>() {
					});

			cmtSalesInvoiceDetailsTradingModel.forEach(items -> {
				items.setSales_invoice_version(jsonModel.getSales_invoice_version());
				items.setSales_invoice_master_transaction_id(
						responseSalesInvoiceMasterRecords.getSales_invoice_master_transaction_id());
			});

			List<CMtSalesInvoiceDetailsTradingModel> responseSalesInvoiceDetails = iMtSalesInvoiceDetailsTradingRepository.saveAll(cmtSalesInvoiceDetailsTradingModel);

			//Sales Invoice Tax Summary

			if (!TaxSummaryArray.isEmpty()) {
				if (update) {
					iMtSalesInvoiceTaxSummaryRepository.updateSalesInvoiceTaxSummary(sales_invoice_no, sales_invoice_version,
							company_id);
				}

				List<CMtSalesInvoiceTaxSummaryModel> salesInvoiceTaxSummary = objectMapper.readValue(
						TaxSummaryArray.toString(), new TypeReference<List<CMtSalesInvoiceTaxSummaryModel>>() {
						});
				salesInvoiceTaxSummary.forEach(items -> {
					items.setSales_invoice_version(jsonModel.getSales_invoice_version());
					items.setSales_invoice_master_transaction_id(responseSalesInvoiceMasterRecords.getSales_invoice_master_transaction_id());

				});

				iMtSalesInvoiceTaxSummaryRepository.saveAll(salesInvoiceTaxSummary);

			}

			//Common Terms
			if (!commonTerms.isEmpty()) {
				if (update) {
					iMtSalesInvoiceTermsTradingRepository.updateSalesInvoiceTerms(sales_invoice_no, company_id);
				}

				List<CMtSalesInvoiceTermsTradingModel> salesInvoiceTerms = objectMapper.readValue(
						TaxSummaryArray.toString(), new TypeReference<List<CMtSalesInvoiceTermsTradingModel>>() {
						});

				salesInvoiceTerms.forEach(items -> {
					items.setSales_invoice_version(jsonModel.getSales_invoice_version());
					items.setSales_invoice_master_transaction_id(responseSalesInvoiceMasterRecords.getSales_invoice_master_transaction_id());

				});

				iMtSalesInvoiceTermsTradingRepository.saveAll(salesInvoiceTerms);
			}


			if ("O".equals(responseSalesInvoiceMasterRecords.getSales_invoice_creation_type())) {
				FnUpdateDispatchChallanStatus(responseSalesInvoiceMasterRecords, responseSalesInvoiceDetails);
			}

			responce.put("data", responseSalesInvoiceMasterRecords);
			responce.put("success", 1);
			responce.put("error", "");
			responce.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesInvoiceMasterTrading/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesInvoiceMasterTrading/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnCancelSalesInvoice(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String sales_invoice_no = commonIdsObj.getString("sales_invoice_no");
		JSONArray dispatchChallanNos = (JSONArray) commonIdsObj.get("dispatch_challan_no");
		JSONArray detailsData = (JSONArray) jsonObject.get("TransDetailData");
		try {
			List<CMtDispatchChallanDetailsTradingModel> updateDispatchChallanDetailsStatus = new ArrayList<>();
			List<CMtDispatchChallanMasterTradingModel> updateDispatchChallanMasterStatus = new ArrayList<>();
			List<String> dispatchChallanNoList = objectMapper.readValue(dispatchChallanNos.toString(), new TypeReference<List<String>>() {
			});

			List<CMtDispatchChallanDetailsTradingModel> getDispatchChallandetails = iMtDispatchChallanDetailsTradingRepository.getDispatchChallandetails(dispatchChallanNoList, company_id);
			List<CMtDispatchChallanMasterTradingModel> getDispatchChallanMaster = iMtDispatchChallanMasterTradingRepository.getDispatchChallanMaster(dispatchChallanNoList, company_id);

			List<CMtSalesInvoiceDetailsTradingModel> cmtSalesInvoiceDetailsTradingModel = objectMapper
					.readValue(detailsData.toString(), new TypeReference<List<CMtSalesInvoiceDetailsTradingModel>>() {
					});

			dispatchChallanNoList.forEach(challanNo -> {
				AtomicBoolean isAnyPartiallyIssue = new AtomicBoolean(false);
				List<CMtSalesInvoiceDetailsTradingModel> responseFilteredSalesInvoiceDetails = cmtSalesInvoiceDetailsTradingModel.stream().filter(item -> item.getDispatch_challan_no().equals(challanNo)).collect(Collectors.toList());

				List<CMtDispatchChallanDetailsTradingModel> responseFilteredDispatchChallanDetails = getDispatchChallandetails.stream().filter(item -> item.getDispatch_challan_no().equals(challanNo)).collect(Collectors.toList());

				responseFilteredSalesInvoiceDetails.forEach(invoiceDetail -> {
					CMtDispatchChallanDetailsTradingModel filteredDispatchDetail = responseFilteredDispatchChallanDetails.stream()
							.filter(detail -> detail.getDispatch_challan_details_transaction_id() == invoiceDetail.getDispatch_challan_details_transaction_id())
							.findFirst()
							.orElse(null); // If no match found, null is returned

					if (filteredDispatchDetail != null) {
						filteredDispatchDetail.setPending_quantity(filteredDispatchDetail.getPending_quantity() + invoiceDetail.getInvoice_quantity());
						filteredDispatchDetail.setPending_weight(filteredDispatchDetail.getPending_weight() + invoiceDetail.getInvoice_weight());
						if (filteredDispatchDetail.getInvoice_quantity() == invoiceDetail.getInvoice_quantity()) {
							filteredDispatchDetail.setDispatch_challan_item_status("A");
						} else {
							filteredDispatchDetail.setDispatch_challan_item_status("I");
							isAnyPartiallyIssue.set(true);
						}
						updateDispatchChallanDetailsStatus.add(filteredDispatchDetail);
					}

				});

				CMtDispatchChallanMasterTradingModel getMaster = getDispatchChallanMaster.stream().filter(dispatchChallanMaster -> dispatchChallanMaster.getDispatch_challan_no()
								.equals(challanNo))
						.findFirst()
						.orElse(null);

				if (isAnyPartiallyIssue.get()) {
					if (getMaster != null) {
						getMaster.setDispatch_challan_status("I");
						updateDispatchChallanMasterStatus.add(getMaster);
					}
				} else {
					getMaster.setDispatch_challan_status("C");
					updateDispatchChallanMasterStatus.add(getMaster);
				}
			});
			if (!updateDispatchChallanDetailsStatus.isEmpty() && !updateDispatchChallanMasterStatus.isEmpty()) {
				iMtDispatchChallanDetailsTradingRepository.saveAll(updateDispatchChallanDetailsStatus);
				iMtDispatchChallanMasterTradingRepository.saveAll(updateDispatchChallanMasterStatus);
			}

			iMtSalesInvoiceMasterTradingRepository.updateSalesInvoiceStatus(sales_invoice_no, company_id);
			cmtSalesInvoiceDetailsTradingModel.forEach(invoiceDetail -> {
				invoiceDetail.setSales_invoice_item_status("X");
			});

			iMtSalesInvoiceDetailsTradingRepository.saveAll(cmtSalesInvoiceDetailsTradingModel);

			responce.put("success", 1);
			responce.put("data", "");
			responce.put("message", "Record canceled!...");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesInvoiceMasterTrading/FnCancelSalesInvoice", sqlEx.getErrorCode(), sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesInvoiceMasterTrading/FnCancelSalesInvoice", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return null;
	}

	private void FnUpdateDispatchChallanStatus(CMtSalesInvoiceMasterTradingModel responseSalesInvoiceMasterRecords, List<CMtSalesInvoiceDetailsTradingModel> responseSalesInvoiceDetails) {
		List<CMtDispatchChallanDetailsTradingModel> updateDispatchChallanDetailsStatus = new ArrayList<>();
		List<CMtDispatchChallanMasterTradingModel> updateDispatchChallanMasterStatus = new ArrayList<>();


//      Get distinct challan nos from responseSalesInvoiceDetails
		List<String> distinctDispatchChallanNos = responseSalesInvoiceDetails.stream().map(CMtSalesInvoiceDetailsTradingModel::getDispatch_challan_no).distinct().collect(Collectors.toList());
		List<CMtDispatchChallanDetailsTradingModel> getDispatchChallandetails = iMtDispatchChallanDetailsTradingRepository.getDispatchChallandetails(distinctDispatchChallanNos, responseSalesInvoiceMasterRecords.getCompany_id());
		List<CMtDispatchChallanMasterTradingModel> getDispatchChallanMaster = iMtDispatchChallanMasterTradingRepository.getDispatchChallanMaster(distinctDispatchChallanNos, responseSalesInvoiceMasterRecords.getCompany_id());

		distinctDispatchChallanNos.forEach(challanNo -> {
			AtomicBoolean isAnyPartiallyIssue = new AtomicBoolean(false);
			List<CMtSalesInvoiceDetailsTradingModel> responseFilteredSalesInvoiceDetails = responseSalesInvoiceDetails.stream().filter(item -> item.getDispatch_challan_no().equals(challanNo)).collect(Collectors.toList());

			List<CMtDispatchChallanDetailsTradingModel> responseFilteredDispatchChallanDetails = getDispatchChallandetails.stream().filter(item -> item.getDispatch_challan_no().equals(challanNo)).collect(Collectors.toList());

			responseFilteredSalesInvoiceDetails.forEach(invoiceDetail -> {
				CMtDispatchChallanDetailsTradingModel filteredDispatchDetail = responseFilteredDispatchChallanDetails.stream()
						.filter(detail -> detail.getDispatch_challan_details_transaction_id() == invoiceDetail.getDispatch_challan_details_transaction_id())
						.findFirst()
						.orElse(null); // If no match found, null is returned

				if (filteredDispatchDetail != null) {
					if (invoiceDetail.getInvoice_quantity() == filteredDispatchDetail.getDispatch_quantity()) {
						filteredDispatchDetail.setDispatch_challan_item_status("C");
					} else if (invoiceDetail.getInvoice_quantity() < filteredDispatchDetail.getDispatch_quantity()) {
						filteredDispatchDetail.setDispatch_challan_item_status("I");
						filteredDispatchDetail.setPending_quantity(filteredDispatchDetail.getDispatch_quantity() - invoiceDetail.getInvoice_quantity());
						filteredDispatchDetail.setPending_weight(filteredDispatchDetail.getDispatch_weight() - invoiceDetail.getInvoice_quantity());
						isAnyPartiallyIssue.set(true);
					}
					filteredDispatchDetail.setInvoice_quantity(invoiceDetail.getInvoice_quantity());
					filteredDispatchDetail.setInvoice_weight(invoiceDetail.getInvoice_weight());

					updateDispatchChallanDetailsStatus.add(filteredDispatchDetail);
				}
			});

			CMtDispatchChallanMasterTradingModel getMaster = getDispatchChallanMaster.stream().filter(dispatchChallanMaster -> dispatchChallanMaster.getDispatch_challan_no()
							.equals(challanNo))
					.findFirst()
					.orElse(null);

			if (isAnyPartiallyIssue.get()) {
				if (getMaster != null) {
					getMaster.setDispatch_challan_status("I");
					updateDispatchChallanMasterStatus.add(getMaster);
				}
			} else {
				getMaster.setDispatch_challan_status("C");
				updateDispatchChallanMasterStatus.add(getMaster);
			}
		});

		if (!updateDispatchChallanDetailsStatus.isEmpty() && !updateDispatchChallanMasterStatus.isEmpty()) {
			iMtDispatchChallanDetailsTradingRepository.saveAll(updateDispatchChallanDetailsStatus);
			iMtDispatchChallanMasterTradingRepository.saveAll(updateDispatchChallanMasterStatus);
		}


	}

	@Override
	public Map<String, Object> FnDeleteRecord(String sales_invoice_no, int sales_invoice_version, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			iMtSalesInvoiceMasterTradingRepository.deleteSalesInvoiceMasterRecords(sales_invoice_no,
					sales_invoice_version, company_id);
			iMtSalesInvoiceDetailsTradingRepository.deleteSalesInvoiceDetailsRecords(sales_invoice_no,
					sales_invoice_version, company_id);
			iMtSalesInvoiceTaxSummaryRepository.deleteSalesInvoiceTaxSummaryRecords(sales_invoice_no,
					sales_invoice_version, company_id);

			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", "");
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();

		if ("summary".equals(reportType)) {
			Map<String, Object> results = iMtSalesInvoiceMasterTradingViewRepository.FnShowAllSummaryReportRecords();
			response.put("content", results);
		} else {
//			Map<String, Object> results = iMtSalesInvoiceDetailsTradingViewRepository
//					.FnShowAlldetailsReportRecords();
//			response.put("content", results);
		}

		return response;
	}

	@Override
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		String sales_invoice_no = jsonObject.getString("sales_invoice_no");
		int sales_invoice_version = jsonObject.getInt("sales_invoice_version");
		String financial_year = jsonObject.getString("financial_year");
		int company_id = jsonObject.getInt("company_id");
		try {

			CMtSalesInvoiceMasterTradingViewModel salesInvoiceMasterRecords = iMtSalesInvoiceMasterTradingViewRepository
					.FnShowSalesInvoiceMasterTradingRecords(sales_invoice_no, sales_invoice_version, financial_year,
							company_id);
			List<Map<String, Object>> salesInvoiceDetailsRecords = iMtSalesInvoiceDetailsTradingViewRepository
					.FnShowSalesInvoiceDetailsTradingRecords(sales_invoice_no, sales_invoice_version, financial_year,
							company_id);
			List<CMtSalesInvoiceTaxSummaryViewModel> salesInvoiceTaxSummaryRecords = iMtSalesInvoiceTaxSummaryViewRepository
					.FnShowSalesInvoiceTaxSummaryRecords(sales_invoice_no, sales_invoice_version, financial_year,
							company_id);
//          Extract distinct challan numbers
			List<Map<String, Object>> distinctChallanNoList =
					salesInvoiceDetailsRecords.stream()
							.map(record -> record.get("dispatch_challan_no"))
							.filter(challanNo -> challanNo != null)
							.distinct()
							.map(challanNo -> {
								Map<String, Object> map = new HashMap<>();
								map.put("dispatch_challan_no", challanNo);
								return map;
							})
							.collect(Collectors.toList());

			responce.put("salesInvoiceMasterRecords", salesInvoiceMasterRecords);
			responce.put("salesInvoiceDetailsRecords", salesInvoiceDetailsRecords);
			responce.put("salesInvoiceTaxSummaryRecords", salesInvoiceTaxSummaryRecords);
			responce.put("distinctChallanNosList", distinctChallanNoList);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;

	}

	@Override
	public Map<String, Object> FnShowDispatchChallanDetails(JSONObject dispatchChallanData) {
		Map<String, Object> response = new HashMap<>();
		try {
			int company_id = dispatchChallanData.getInt("company_id");
			JSONArray dispatch_challan_nos = dispatchChallanData.getJSONArray("dispatch_challan_nos");
			List<String> challanNos = dispatch_challan_nos.toList().parallelStream().map(Object::toString)
					.collect(Collectors.toList());

			List<CMtDispatchChallanDetailsTradingViewModel> dispatchChallanDetails = iMtDispatchChallanDetailsTradingViewRepository.FnShowDispatchChallanDetails(challanNos, company_id);
			response.put("data", dispatchChallanDetails);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	//	E-Invoicing & E-way billing functionality
	@Override
	public Map<String, Object> FnGetSaleInvoiceDataByStatus(String sales_invoice_status, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			List<CMtSalesInvoiceMasterTradingViewModel> salesInvoiceMasterAllRecordsByStatus = null;

			if ("A".equals(sales_invoice_status)) {
				salesInvoiceMasterAllRecordsByStatus = iMtSalesInvoiceMasterTradingViewRepository.getAllSalesInvoiceRecords(company_id);
			} else {
				salesInvoiceMasterAllRecordsByStatus = iMtSalesInvoiceMasterTradingViewRepository.getSalesInvoiceMasterRecordsByStatus(sales_invoice_status, company_id);
			}
			responce.put("SalesInvoiceMasterAllRecordsByStatus", salesInvoiceMasterAllRecordsByStatus);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnGenerateTokenForEInvoice(int company_id) {
		Map<String, Object> responce = new HashMap<>();
		Map<String, String> tokenResponse = new HashMap<>();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			CAmCompanySettingsModel cAmCompanySettingsModel = iAmCompanySettingsRepository.FnGetCompanySettings(company_id);
			if (cAmCompanySettingsModel != null) {
				if (!cAmCompanySettingsModel.getE_inv_auth_token().isEmpty()) {
					LocalDateTime expiryDateTime = LocalDateTime.parse(cAmCompanySettingsModel.getE_inv_token_expiry(), formatter);
					boolean isValidDateTime = FnIsValidDateTime(expiryDateTime);
					if (isValidDateTime) {
						tokenResponse.put("AuthToken", cAmCompanySettingsModel.getE_inv_auth_token());
						tokenResponse.put("TokenExpiry", cAmCompanySettingsModel.getE_inv_token_expiry());
						System.out.println("The provided date and time is valid.");
					} else {
						tokenResponse = FnGenerateToken(cAmCompanySettingsModel);
						cAmCompanySettingsModel.setE_inv_auth_token(tokenResponse.get("AuthToken"));
						cAmCompanySettingsModel.setE_inv_token_expiry(tokenResponse.get("TokenExpiry"));
						FnAddUpdateToken(cAmCompanySettingsModel);
					}

				} else {
					tokenResponse = FnGenerateToken(cAmCompanySettingsModel);
					cAmCompanySettingsModel.setE_inv_auth_token(tokenResponse.get("AuthToken"));
					cAmCompanySettingsModel.setE_inv_token_expiry(tokenResponse.get("TokenExpiry"));
					FnAddUpdateToken(cAmCompanySettingsModel);
				}
			}
			responce.put("success", 1);
			responce.put("data", tokenResponse);
			responce.put("company_settings", cAmCompanySettingsModel);
			responce.put("error", "");


		} catch (HttpClientErrorException.Unauthorized unauthorizedException) {
			String responseBody = unauthorizedException.getResponseBodyAsString();

			String errorMessage = extractErrorMessage(responseBody);
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", errorMessage);

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}


	private Map<String, String> FnGenerateToken(CAmCompanySettingsModel companySettings) {
		Map<String, String> responce = new HashMap<>();
		ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
		};
		StringBuilder urlBuilder = new StringBuilder();

		urlBuilder.append(companySettings.getE_inv_sandbox_auth_url()).append("aspid=1677441922");
		urlBuilder.append("&password=5S@Innovations");
		urlBuilder.append("&Gstin=34AACCC1596Q002");
		urlBuilder.append("&user_name=").append(companySettings.getE_inv_sandbox_user_name());
		urlBuilder.append("&eInvPwd=").append(companySettings.getE_inv_sandbox_password());

		String url = urlBuilder.toString();

		// Assuming restTemplate is an instance of RestTemplate
		ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			Map<String, Object> responseBody = responseEntity.getBody();

			if (responseBody != null && responseBody.containsKey("Data")) {
				Map<String, Object> userData = (Map<String, Object>) responseBody.get("Data");
				if (userData != null && userData.containsKey("AuthToken")) {
					String authToken = (String) userData.get("AuthToken");
					String tokenExpiry = (String) userData.get("TokenExpiry");
					System.out.println("AuthToken: " + authToken);
					responce.put("AuthToken", authToken);
					responce.put("TokenExpiry", tokenExpiry);
				}
			}
			return responce;
		} else {
			return Collections.emptyMap();
		}
	}

	private void FnAddUpdateToken(CAmCompanySettingsModel cAmCompanySettingsModelObj) {
		CAmCompanySettingsModel cAmCompanySettingsModel = new CAmCompanySettingsModel();
		BeanUtils.copyProperties(cAmCompanySettingsModelObj, cAmCompanySettingsModel);
		cAmCompanySettingsModel.setCompany_id(cAmCompanySettingsModelObj.getCompany_id());
		cAmCompanySettingsModel.setAm_company_settings_id(cAmCompanySettingsModelObj.getAm_company_settings_id());
		cAmCompanySettingsModel.setE_inv_auth_token(cAmCompanySettingsModelObj.getE_inv_auth_token());
		cAmCompanySettingsModel.setE_inv_token_expiry(cAmCompanySettingsModelObj.getE_inv_token_expiry());

		iAmCompanySettingsRepository.save(cAmCompanySettingsModel);
	}

	// Extract error message from the JSON response
	// You should use a JSON library for proper parsing in a real-world scenario
	// For simplicity, using string manipulation here
	private String extractErrorMessage(String responseBody) {
		int startIndex = responseBody.indexOf("\"message\":\"") + "\"message\":\"".length();
		int endIndex = responseBody.indexOf("\"", startIndex);
		return responseBody.substring(startIndex, endIndex);
	}


	@Override
	public Map<String, Object> FnGetSalesInvoiceDetails(String sales_invoice_no, int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<CMtSalesInvoiceDetailsTradingViewModel> tradingViewModels = iMtSalesInvoiceDetailsTradingViewRepository.FnGetSalesInvoiceDetails(sales_invoice_no, company_id);
			response.put("data", tradingViewModels);
			response.put("success", 1);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());
		}
		return response;
	}

//	https://gstsandbox.charteredinfo.com/eicore/dec/v1.03/Invoice?aspid=1677441922&password=5S@Innovations&Gstin=34AACCC1596Q002&AuthToken=q3qtcRHJnk0Ul7wV5AjLRkQw1
//	&user_name=TaxProEnvPON

	@Override
	public Map<String, Object> FnGenerateEInvoice(JSONObject jsonObject) {
		JSONObject eInvoiceJson = jsonObject.getJSONObject("E-InvoiceJson");
		JSONObject invoiceNos = jsonObject.getJSONObject("invoiceNos");
		String sales_invoice_no = invoiceNos.getString("sales_invoice_no");
		int company_id = invoiceNos.getInt("company_id");
		try {
			ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {
			};
			String requestBody = eInvoiceJson.toString();

//          Request headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

//			HttpEntity with headers and body
			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

//           Make the POST request
//			ResponseEntity<Map<String, Object>> responseEntity = restTemplate.postForEntity(url, requestEntity, responseType);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
