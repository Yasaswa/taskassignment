package com.erp.MtSalesOrderMasterProjects.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.MtSalesOrderMasterProjects.Model.CMtSalesOrderMasterProjectSummaryViewModel;
import com.erp.MtSalesOrderMasterProjects.Model.CMtSalesOrderMasterProjectsModel;
import com.erp.MtSalesOrderMasterProjects.Repository.IMtSalesOrderMasterProjectSummaryViewRepository;
import com.erp.MtSalesOrderMasterProjects.Repository.IMtSalesOrderMasterProjectsRepository;
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

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CMtSalesOrderMasterProjectsServiceImpl implements IMtSalesOrderMasterProjectsService {


	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtSalesOrderMasterProjectsRepository iMtSalesOrderMasterProjectsRepository;

	@Autowired
	IMtSalesOrderMasterProjectSummaryViewRepository iMtSalesOrderMasterProjectSummaryViewRepository;

	@Autowired
	private JdbcTemplate executeQuery;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		String sales_order_no = commonIdsObj.getString("sales_order_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject json = jsonObject.getJSONObject("TransMasterData");
		JSONArray array = (JSONArray) jsonObject.get("TransDetailData");
//		JSONArray payment_termsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");
//		JSONArray TaxSummaryArray = (JSONArray) jsonObject.get("TaxSummaryData");
//		JSONArray scheduleArray = (JSONArray) jsonObject.get("TransDetailScheduleData");
//		JSONArray orderTermsArray = (JSONArray) jsonObject.get("TransSalesOrderTermsData");

		try {
			CMtSalesOrderMasterProjectsModel jsonModel = objectMapper.readValue(json.toString(),
					CMtSalesOrderMasterProjectsModel.class);
			if (!isApprove) {

				String sales_order_date = jsonModel.getSales_order_date();

				CMtSalesOrderMasterProjectsModel cMtSalesOrderMasterProjectsModel = new CMtSalesOrderMasterProjectsModel();

				String query = "Select * FROM mt_sales_order_master_projects WHERE is_delete = 0 and sales_order_no = '"
						+ sales_order_no.toString() + "' and sales_order_version = " + jsonModel.getSales_order_version()
						+ " and financial_year = '" + financial_year.toString() + "' and company_id = " + company_id + "";

				List<CMtSalesOrderMasterProjectsModel> results = executeQuery.query(query,
						new BeanPropertyRowMapper<>(CMtSalesOrderMasterProjectsModel.class));

				if (!results.isEmpty()) {
					update = true;
					cMtSalesOrderMasterProjectsModel = results.get(0);
					cMtSalesOrderMasterProjectsModel.setDeleted_on(new Date());
					cMtSalesOrderMasterProjectsModel.setIs_delete(true);
					iMtSalesOrderMasterProjectsRepository.save(cMtSalesOrderMasterProjectsModel);

					jsonModel.setSales_order_version(cMtSalesOrderMasterProjectsModel.getSales_order_version() + 1);

				}

				CMtSalesOrderMasterProjectsModel responseSalesOrderMasterProjects = iMtSalesOrderMasterProjectsRepository
						.save(jsonModel);

				responce.put("success", "1");
				responce.put("data", responseSalesOrderMasterProjects);
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully!.." : "Record added successfully!...");
			} else {

				responce = FnSalesOrderDetailsUpdateRecord(array, commonIdsObj, jsonModel);
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesOrderMasterProjects/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesOrderMasterProjects/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;

	}

	private Map<String, Object> FnSalesOrderDetailsUpdateRecord(JSONArray array, JSONObject commonIdsObj, CMtSalesOrderMasterProjectsModel jsonModel) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = commonIdsObj.getInt("company_id");
//		int company_branch_id = commonIdsObj.getInt("company_branch_id");
//		String sales_order_no = commonIdsObj.getString("sales_order_no");
//		String financial_year = commonIdsObj.getString("financial_year");

		try {

			CMtSalesOrderMasterProjectsModel salesOrderdetailMasterProjectsModels =
					iMtSalesOrderMasterProjectsRepository.save(jsonModel);

//			List<CMtSalesOrderDetailsTradingModel> salesOrderdetailsTradingModels = objectMapper.readValue(array.toString(),
//					new TypeReference<List<CMtSalesOrderDetailsTradingModel>>() {
//					});
//
//			iMtSalesOrderDetailsTradingRepository.saveAll(salesOrderdetailsTradingModels);
			responce.put("success", "1");
			responce.put("data", salesOrderdetailMasterProjectsModels);
			responce.put("error", "");
			responce.put("message", "Record approved successfully!...");
		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesOrderMasterProjects/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtSalesOrderMasterProjects/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}


		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String sales_order_no, int sales_order_version, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			iMtSalesOrderMasterProjectsRepository.deleteSalesDetails(sales_order_no, sales_order_version, company_id);

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
	public Page<CMtSalesOrderMasterProjectSummaryViewModel> FnShowAllActiveRecords(Pageable pageable) {
		return iMtSalesOrderMasterProjectSummaryViewRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int sales_order_master_transaction_id, int company_id) {
		Map<String, Object> responce = new HashMap();
		CMtSalesOrderMasterProjectsModel cMtSalesOrderMasterProjectsModel = null;
		try {
			cMtSalesOrderMasterProjectsModel = iMtSalesOrderMasterProjectsRepository
					.FnShowParticularRecordForUpdate(sales_order_master_transaction_id, company_id);
			responce.put("success", "1");
			responce.put("data", cMtSalesOrderMasterProjectsModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}

	@Override
	public Page<CMtSalesOrderMasterProjectSummaryViewModel> FnShowParticularRecord(
			int sales_order_master_transaction_id, Pageable pageable, int company_id) {
		return iMtSalesOrderMasterProjectSummaryViewRepository.FnShowParticularRecord(sales_order_master_transaction_id,
				pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String sales_order_no, int sales_order_version,
	                                                                 String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			Map<String, Object> salesOrderMasterServiceRecord = iMtSalesOrderMasterProjectsRepository
					.FnShowSalesOrderMasterRecord(sales_order_no, sales_order_version, financial_year, company_id);

			responce.put("salesOrderMasterServiceRecord", salesOrderMasterServiceRecord);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Page<CMtSalesOrderMasterProjectSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(
			String customer_order_no, Pageable pageable, int company_id) {
		return iMtSalesOrderMasterProjectSummaryViewRepository.FnShowSalesOrderDetailsTradingCustomerRecord(customer_order_no,
				pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		String query;
		if ("summary".equals(reportType)) {
			query = "SELECT * FROM mtv_sales_order_master_trading_summary_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		} else {
			query = "SELECT * FROM mtv_sales_order_details_trading_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		}

		return response;


	}


}
