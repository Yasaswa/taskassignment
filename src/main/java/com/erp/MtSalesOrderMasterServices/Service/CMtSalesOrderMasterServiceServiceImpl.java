package com.erp.MtSalesOrderMasterServices.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.MtQuotationDetails.Model.CMtSalesQuotationPaymentTermsTradingViewModel;
import com.erp.MtQuotationDetails.Repository.IMtQuotationPaymentTermsTradingViewRepository;
import com.erp.MtSalesOrderMasterServices.Model.*;
import com.erp.MtSalesOrderMasterServices.Repository.*;
import com.erp.MtSalesOrderMasterTrading.Model.*;
import com.erp.MtSalesOrderMasterTrading.Repository.*;
import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationDetailsServicesViewModel;
import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationPaymentTermsServiceViewModel;
import com.erp.MtSalesQuotationMasterServices.Repository.IMtSalesQuotationDetailsServicesRepository;
import com.erp.MtSalesQuotationMasterServices.Repository.IMtSalesQuotationDetailsServicesViewRepository;
import com.erp.MtSalesQuotationMasterServices.Repository.IMtSalesQuotationMasterServicesRepository;
import com.erp.MtSalesQuotationMasterServices.Repository.IMtSalesQuotationMasterServicesViewRepository;
import com.erp.MtSalesQuotationMasterServices.Repository.IMtSalesQuotationPaymentTermsServicesViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CMtSalesOrderMasterServiceServiceImpl implements IMtSalesOrderMasterService {

	@Autowired
	IMtSalesOrderMasterServicesRepository iMtSalesOrderMasterServicesRepository;

	@Autowired
	IMtSalesOrderMasterServicesSummaryViewRepository iMtSalesOrderMasterServicesSummaryViewRepository;

	@Autowired
	IMtSalesOrderDetailsServicesRepository iMtSalesOrderDetailsServicesRepository;

	@Autowired
	IMtSalesOrderDetailsServicesViewRepository iMtSalesOrderDetailsServicesViewRepository;

	@Autowired
	IMtSalesOrderScheduleServicesRepository iMtSalesOrderScheduleServicesRepository;

	@Autowired
	IMtSalesOrderScheduleServicesViewRepository iMtSalesOrderScheduleServicesViewRepository;

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtSalesQuotationMasterServicesViewRepository iMtSalesQuotationMasterServicesViewRepository;

	@Autowired
	IMtSalesOrderPaymentTermsTradingRepository iMtSalesOrderPaymentTermsTradingRepository;

	@Autowired
	IMtSalesOrderTaxSummaryRepository iMtSalesOrderTaxSummaryRepository;

	@Autowired
	IMtSalesOrderTermsTradingRepository iMtSalesOrderTermsTradingRepository;

	@Autowired
	IMtSalesQuotationDetailsServicesViewRepository iMtSalesQuotationDetailsServicesViewRepository;

	@Autowired
	IMtSalesQuotationPaymentTermsServicesViewRepository iMtQuotationPaymentTermsServiceViewRepository;
	
	@Autowired
	IMtSalesQuotationMasterServicesRepository iMtSalesQuotationMasterServicesRepository;
	
	@Autowired
	IMtSalesQuotationDetailsServicesRepository iMtSalesQuotationDetailsServicesRepository;

	@Autowired
	IMtSalesOrderPaymentTermsTradingViewRepository iMtSalesOrderPaymentTermsTradingViewRepository;

	@Autowired
	IMtSalesOrderTaxSummaryViewRepository iMtSalesOrderTaxSummaryViewRepository;
	
	@Autowired
	IMtSalesOrderPaymentTermsServicesRepository iMtSalesOrderPaymentTermsServicesRepository;
	
	@Autowired
	IMtSalesOrderTaxSummaryServicesRepository iMtSalesOrderTaxSummaryServicesRepository;
	
	@Autowired
	IMtSalesOrderTermsServicesRepository iMtSalesOrderTermsServicesRepository;
	
	@Autowired
	IMtSalesOrderTaxSummaryServicesViewRepository iMtSalesOrderTaxSummaryServicesViewRepository;
	
	@Autowired
	IMtSalesOrderPaymentTermsServicesViewRepository iMtSalesOrderPaymentTermsServicesViewRepository;
	
	@Autowired
	IMtSalesOrderTermsServicesViewRepository iMtSalesOrderTermsServicesViewRepository;
	
	@Autowired
	IMtSalesOrderServiceActivitiesRepository iMtSalesOrderServiceActivitiesRepository;

	
	@Autowired
	IMtSalesOrderServiceActivitiesViewRepository iMtSalesOrderServiceActivitiesViewRepository;

	@Autowired
	private JdbcTemplate executeQuery;


	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String sales_order_no = commonIdsObj.getString("sales_order_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterjson = jsonObject.getJSONObject("TransMasterData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
		JSONArray scheduleArray = (JSONArray) jsonObject.get("TransScheduleData");
		JSONArray payment_termsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");
		JSONArray TaxSummaryArray = (JSONArray) jsonObject.get("TransTaxSummaryData");
		JSONArray orderTermsArray = (JSONArray) jsonObject.get("TransSalesOrderTermsData");
		JSONArray sOSrActivitiesArray = (JSONArray) jsonObject.get("SOSrActivitiesData");


		try {

			// Sales Order Master Service
			CMtSalesOrderMasterServicesModel jsonModel = objectMapper.readValue(masterjson.toString(),
					CMtSalesOrderMasterServicesModel.class);

			if (!isApprove) {

				CMtSalesOrderMasterServicesModel cMtSalesOrderMasterServicesModel = new CMtSalesOrderMasterServicesModel();

				String query = "Select * FROM mt_sales_order_master_services WHERE is_delete = 0 and sales_order_no = '"
						+ sales_order_no.toString() + "' and sales_order_version = "
						+ jsonModel.getSales_order_version() + " and financial_year = '" + financial_year.toString()
						+ "' and company_id = " + company_id + "";

				List<CMtSalesOrderMasterServicesModel> results = executeQuery.query(query,
						new BeanPropertyRowMapper<>(CMtSalesOrderMasterServicesModel.class));

				if (!results.isEmpty()) {
					update = true;
					cMtSalesOrderMasterServicesModel = results.get(0);
					cMtSalesOrderMasterServicesModel.setDeleted_on(new Date());
					cMtSalesOrderMasterServicesModel.setIs_delete(true);
					iMtSalesOrderMasterServicesRepository.save(cMtSalesOrderMasterServicesModel);

					jsonModel.setSales_order_version(cMtSalesOrderMasterServicesModel.getSales_order_version() + 1);

				}

				CMtSalesOrderMasterServicesModel responseSalesOrderServiceMaster = iMtSalesOrderMasterServicesRepository
						.save(jsonModel);

				// Sales Order Details Service
				iMtSalesOrderDetailsServicesRepository.FnUpdateSalesOrderDetailsServicesRecord(sales_order_no,
						masterjson.getInt("sales_order_version"), company_id);

				List<CMtSalesOrderDetailsServicesModel> cmtSalesOrderDetailsServicesModel = objectMapper.readValue(
						detailsArray.toString(), new TypeReference<List<CMtSalesOrderDetailsServicesModel>>() {
						});

				cmtSalesOrderDetailsServicesModel.forEach(items -> {
					items.setSales_order_details_transaction_id(0);
					items.setSales_order_version(jsonModel.getSales_order_version());
					items.setSales_order_master_transaction_id(
							responseSalesOrderServiceMaster.getSales_order_master_transaction_id());
				});

				iMtSalesOrderDetailsServicesRepository.saveAll(cmtSalesOrderDetailsServicesModel);

				// Sales Order Schedule Service
				iMtSalesOrderScheduleServicesRepository.FnUpdateSalesOrderScheduleServicesRecord(sales_order_no,
						masterjson.getInt("sales_order_version"), company_id);

				List<CMtSalesOrderSchedulesServicesModel> cmttSalesOrderSchedulesServicesModel = objectMapper.readValue(
						scheduleArray.toString(), new TypeReference<List<CMtSalesOrderSchedulesServicesModel>>() {
						});

				cmttSalesOrderSchedulesServicesModel.forEach(items -> {
					items.setSales_order_schedules_transaction_id(0);
					items.setSales_order_version(jsonModel.getSales_order_version());
					items.setSales_order_master_transaction_id(
							responseSalesOrderServiceMaster.getSales_order_master_transaction_id());
				});

				iMtSalesOrderScheduleServicesRepository.saveAll(cmttSalesOrderSchedulesServicesModel);

				// Save the Payment Terms data.
				if (!payment_termsArray.isEmpty()) {
					iMtSalesOrderPaymentTermsServicesRepository.updatePaymentTermsStatus(sales_order_no, masterjson.getInt("sales_order_version"), company_id);
					List<CMtSalesOrderPaymentTermsServiceModel> tradingOrderPaymentTerms = objectMapper.readValue(
							payment_termsArray.toString(),
							new TypeReference<List<CMtSalesOrderPaymentTermsServiceModel>>() {
							});
					tradingOrderPaymentTerms.forEach(paymentTerm -> {
						paymentTerm.setSales_order_payment_terms_transaction_id(0);
						paymentTerm.setSales_order_version(jsonModel.getSales_order_version());
						paymentTerm.setSales_order_master_transaction_id(responseSalesOrderServiceMaster.getSales_order_master_transaction_id());
					});
					iMtSalesOrderPaymentTermsServicesRepository.saveAll(tradingOrderPaymentTerms);
				}

				// Save the Taxation Summary data.
				if (!TaxSummaryArray.isEmpty()) {
					iMtSalesOrderTaxSummaryServicesRepository.updateTaxSummary(sales_order_no, masterjson.getInt("sales_order_version"), company_id);
					List<CMtSalesOrderTaxSummaryServiceModel> orderTaxSummary = objectMapper.readValue(
							TaxSummaryArray.toString(), new TypeReference<List<CMtSalesOrderTaxSummaryServiceModel>>() {
							});
					orderTaxSummary.forEach(taxSummaryRec -> {
						taxSummaryRec.setSales_order_tax_summary_transaction_id(0);
						taxSummaryRec.setSales_order_version(jsonModel.getSales_order_version());
						taxSummaryRec.setSales_order_master_transaction_id(responseSalesOrderServiceMaster.getSales_order_master_transaction_id());
					});
					iMtSalesOrderTaxSummaryServicesRepository.saveAll(orderTaxSummary);
				}

				// Save the Order Terms
				if (!orderTermsArray.isEmpty()) {
					iMtSalesOrderTermsServicesRepository.updateOrderTermdetails(sales_order_no, masterjson.getInt("sales_order_version"), company_id);
					List<CMtSalesOrderTermsServiceModel> orderTerms = objectMapper.readValue(orderTermsArray.toString(),
							new TypeReference<List<CMtSalesOrderTermsServiceModel>>() {
							});
					orderTerms.forEach(orderTerm -> {
						orderTerm.setSales_order_terms_transaction_id(0);
						orderTerm.setSales_order_version(jsonModel.getSales_order_version());
						orderTerm.setSales_order_master_transaction_id(responseSalesOrderServiceMaster.getSales_order_master_transaction_id());
					});
					iMtSalesOrderTermsServicesRepository.saveAll(orderTerms);
				}

				// Save the Sales Order Service Activities
				if (!sOSrActivitiesArray.isEmpty()) {
					
					iMtSalesOrderServiceActivitiesRepository.updateServiceActivitiesDetails(sales_order_no, masterjson.getInt("sales_order_version"), company_id);
					
					List<CMtSalesOrderServiceActivitiesModel> soSrActivities = objectMapper.readValue(sOSrActivitiesArray.toString(),
							new TypeReference<List<CMtSalesOrderServiceActivitiesModel>>() {
							});
					
					soSrActivities.forEach(orderTerm -> {
						orderTerm.setSales_order_activity_transaction_id(0);
						orderTerm.setSales_order_version(jsonModel.getSales_order_version());
						orderTerm.setSales_order_master_transaction_id(responseSalesOrderServiceMaster.getSales_order_master_transaction_id());
					});
					iMtSalesOrderServiceActivitiesRepository.saveAll(soSrActivities);
				}
				
				
				responce.put("success", "1");
				responce.put("data", responseSalesOrderServiceMaster);
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully!.." : "Record added successfully!...");
			} else {

				responce = FnSalesOrderDetailsUpdateRecord(detailsArray, commonIdsObj, jsonModel);
			}
			
			// Update the quotation status.
			if ("Q".equals(jsonModel.getSales_order_creation_type())) {
				String quotationNo = jsonModel.getSales_quotation_no();
				iMtSalesQuotationDetailsServicesRepository.updateSalesQuotationDetailsStatusBySO(quotationNo, company_id);
				iMtSalesQuotationMasterServicesRepository.updateSalesQuotationDetailsStatusBySO(quotationNo, company_id);
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesOrderMasterService/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesOrderMasterService/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;

	}

	private Map<String, Object> FnSalesOrderDetailsUpdateRecord(JSONArray detailsArray, JSONObject commonIdsObj,
	                                                            CMtSalesOrderMasterServicesModel jsonModel) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = commonIdsObj.getInt("company_id");

		try {

			CMtSalesOrderMasterServicesModel salesOrderdetailMasterServiceModels = iMtSalesOrderMasterServicesRepository
					.save(jsonModel);

			List<CMtSalesOrderDetailsServicesModel> salesOrderdetailsTradingModels = objectMapper
					.readValue(detailsArray.toString(), new TypeReference<List<CMtSalesOrderDetailsServicesModel>>() {
					});

			iMtSalesOrderDetailsServicesRepository.saveAll(salesOrderdetailsTradingModels);

			responce.put("success", "1");
			responce.put("data", salesOrderdetailMasterServiceModels);
			responce.put("error", "");
			responce.put("message",
					jsonModel.getSales_order_status().equals("A") ? "Sales Order Service approved successfully!..."
							: "Sales Order Service rejected...!");
		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesOrderMasterService/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesOrderMasterService/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String sales_order_no, int sales_order_version, int company_id,String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iMtSalesOrderMasterServicesRepository.deleteSalesOrderServiceRecords(sales_order_no, sales_order_version,
					company_id,deleted_by);

			iMtSalesOrderDetailsServicesRepository.deleteSalesOrderDetailsServiceRecords(sales_order_no,
					sales_order_version, company_id,deleted_by);

			iMtSalesOrderScheduleServicesRepository.deleteSalesOrderServiceSchedules(sales_order_no,
					sales_order_version, company_id ,deleted_by);
			
			 iMtSalesOrderPaymentTermsServicesRepository.deleteSalesOrderPaymentTermsServiceRecords(sales_order_no,
					sales_order_version, company_id,deleted_by);
			
			iMtSalesOrderTaxSummaryServicesRepository.deleteSalesOrderTaxSummaryServiceRecords(sales_order_no,
					sales_order_version, company_id,deleted_by);

			iMtSalesOrderTermsServicesRepository.deleteSalesOrderTermsServiceRecords(sales_order_no,
					sales_order_version, company_id,deleted_by);
			
			iMtSalesOrderServiceActivitiesRepository.deleteSalesOrderTermsServiceRecords(sales_order_no,
					sales_order_version, company_id,deleted_by);

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
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String sales_order_no, int sales_order_version,
	                                                                 String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			Map<String, Object> salesOrderMasterServiceRecord = iMtSalesOrderMasterServicesSummaryViewRepository
					.FnShowSalesOrderMasterServiceRecord(sales_order_no, sales_order_version, financial_year,
							company_id);

			List<Map<String, Object>> salesOrderDetailsServiceRecord = iMtSalesOrderDetailsServicesViewRepository
					.FnShowSalesOrderDetailsServiceRecord(sales_order_no, sales_order_version, financial_year,
							company_id);

			List<CMtSalesOrderSchedulesServicesViewModel> salesOrderScheduleServiceRecords = iMtSalesOrderScheduleServicesViewRepository
					.FnShowSalesOrderScheduleServiceRecord(sales_order_no, sales_order_version, company_id);

			List<CMtSalesOrderPaymentTermsServiceViewModel> ServiceSaleOrderPaymentTermsServiceRecord = iMtSalesOrderPaymentTermsServicesViewRepository
					.FnShowSalesOrderPaymentTermsServiceRecord(sales_order_no, sales_order_version, company_id);

			List<CMtSalesOrderTaxSummaryServiceViewModel> ServiceSalesOrderTaxSummaryServiceRecord = iMtSalesOrderTaxSummaryServicesViewRepository
					.FnShowSalesOrderTaxSummaryServiceRecord(sales_order_no, sales_order_version, company_id);

			List<CMtSalesOrderTermsServicesViewModel> ServiceSalesOrderTermServiceRecord = iMtSalesOrderTermsServicesViewRepository
					.FnShowSalesOrderTermsServiceRecord(sales_order_no, sales_order_version, company_id);
			
			List<CMtSalesOrderServiceActivitiesViewModel> SalesOrderServiceActivitiesRecords = iMtSalesOrderServiceActivitiesViewRepository
					.FnShowSalesOrderServiceActivitiesRecord(sales_order_no, sales_order_version, company_id);


			responce.put("SalesOrderMasterServiceRecord", salesOrderMasterServiceRecord);
			responce.put("SalesOrderDetailsServiceRecord", salesOrderDetailsServiceRecord);
			responce.put("SalesOrderScheduleServiceRecords", salesOrderScheduleServiceRecords);
			responce.put("ServiceSaleOrderPaymentTermsServiceRecord", ServiceSaleOrderPaymentTermsServiceRecord);
			responce.put("ServiceSalesOrderTaxSummaryServiceRecord", ServiceSalesOrderTaxSummaryServiceRecord);
			responce.put("ServiceSalesOrderTermServiceRecord", ServiceSalesOrderTermServiceRecord);
			responce.put("SalesOrderServiceActivitiesRecords", SalesOrderServiceActivitiesRecords);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Page<CMtSalesOrderMasterServicesSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(
			String customer_order_no, Pageable pageable, int company_id) {
		return iMtSalesOrderMasterServicesSummaryViewRepository
				.FnShowSalesOrderDetailsTradingCustomerRecord(customer_order_no, pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {

		Map<String, Object> response = new HashMap<>();
		if ("summary".equals(reportType)) {
			Map<String, Object> results = iMtSalesOrderMasterServicesSummaryViewRepository
					.FnShowAllSummaryReportRecords();
			response.put("content", results);
		} else {
			Map<String, Object> results = iMtSalesOrderMasterServicesSummaryViewRepository
					.FnShowAlldetailsReportRecords();
			response.put("content", results);
		}
		return response;

	}

	@Override
	public Map<String, Object> FnGetQuotationNoList(int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {

			List<String> quotationNoList = iMtSalesQuotationMasterServicesViewRepository.FnGetQuotationNoDetails(company_id);
			response.put("data", quotationNoList);
			response.put("success", 1);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());
		}
		return response;
	}

	@Override
	public Map<String, Object> FnShowQuotationDetailsRecords(JSONObject quotationNo, int company_id) {
		Map<String, Object> response = new HashMap<>();

		try {

			JSONArray quotationNos = quotationNo.getJSONArray("quotation_nos");

			List<String> quotationNoList = quotationNos.toList().stream().map(Object::toString)
					.collect(Collectors.toList());

			List<CMtSalesQuotationDetailsServicesViewModel> salesQuotationDetailsServicesRecords =
					iMtSalesQuotationDetailsServicesViewRepository.FnShowQuotationDetailsRecord(company_id, quotationNoList);

			response.put("SalesQuotationDetailsServicesRecords", salesQuotationDetailsServicesRecords);
			response.put("success", 1);
			response.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", "");
		}

		return response;
	}

	@Override
	public Map<String, Object> FnGetQuotationDetailsByItemStatus(JSONObject jsonObject, int company_id) {
		Map<String, Object> responce = new HashMap<>();

		try {
			int quotation_version = jsonObject.getInt("quotation_version");
			String quotation_no = jsonObject.getString("quotation_no");
			String financial_year = jsonObject.getString("financial_year");
			String quotation_item_status = jsonObject.getString("quotation_item_status");

			List<CMtSalesQuotationDetailsServicesViewModel> responseDetailsData = iMtSalesQuotationDetailsServicesViewRepository
					.FnGetQuotationDetailsByItemStatus(quotation_no, quotation_version, financial_year,
							quotation_item_status, company_id);
			
			List<CMtSalesQuotationPaymentTermsServiceViewModel> responsePaymentTermsData = iMtQuotationPaymentTermsServiceViewRepository
					.FnGetQuotationPaymentTermsData(quotation_no, quotation_version, company_id);
			
			responce.put("salesQuotationServicesDetailsData", responseDetailsData);
			responce.put("quotationPaymentTermsData", responsePaymentTermsData);
			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesOrderMasterService/FnGetQuotationDetailsByItemStatus", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesOrderMasterService/FnGetQuotationDetailsByItemStatus", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}

		return responce;
	}

	@Override
	public Map<String, Object> FnSendEmail(int company_id, int sales_order_master_transaction_id, JSONObject emailData) {
		Map<String, Object> emailingResponse = new HashMap<>();

		EmailController emailController = new EmailController();
		Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);
		Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
		// Update the mail send status in master table.
		if (emailSentStatus) {
			iMtSalesOrderMasterServicesRepository.updateMailStatus("S", company_id, sales_order_master_transaction_id);
		} else {
			iMtSalesOrderMasterServicesRepository.updateMailStatus("F", company_id, sales_order_master_transaction_id);
		}

		emailingResponse.put("Status", emailSentStatus);
		emailingResponse.put("success", emailControllerResponse.get("success"));
		emailingResponse.put("error", emailControllerResponse.get("error"));
		emailingResponse.put("message", emailControllerResponse.get("message"));
		return emailingResponse;
	}

	@Override
	public Map<String, Object> FnAcceptCustomerOrder(JSONObject customerSOAcceptanceJson, int company_id) {
		Map<String, Object> fnAcceptCustomerOrderResponse = new HashMap<>();
		try {
			int sales_order_master_transaction_id = customerSOAcceptanceJson.getInt("sales_order_master_transaction_id");
			String sales_order_acceptance_status = customerSOAcceptanceJson.getString("sales_order_acceptance_status");
			iMtSalesOrderMasterServicesRepository.FnAcceptCustomerOrder(sales_order_acceptance_status, sales_order_master_transaction_id, company_id);

			fnAcceptCustomerOrderResponse.put("success", "1");
			fnAcceptCustomerOrderResponse.put("error", "");
			fnAcceptCustomerOrderResponse.put("message", "Sales Order accepted successfully...! ");

		} catch (Exception e) {
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtSalesOrderMasterTrading/FnAcceptCustomerOrder", 0, e.getMessage());
			fnAcceptCustomerOrderResponse.put("success", "0");
			fnAcceptCustomerOrderResponse.put("data", "");
			fnAcceptCustomerOrderResponse.put("error", e.getMessage());
			e.printStackTrace();
		}
		return fnAcceptCustomerOrderResponse;
	}

}
