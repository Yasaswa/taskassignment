package com.erp.MtSalesOrderMasterWorks.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.MtSalesOrderMasterWorks.Model.CMtSalesOrderMasterWorksModel;
import com.erp.MtSalesOrderMasterWorks.Model.CMtSalesOrderMasterWorksSummaryRptModel;
import com.erp.MtSalesOrderMasterWorks.Model.CMtSalesOrderMasterWorksSummaryViewModel;
import com.erp.MtSalesOrderMasterWorks.Repository.IMtSalesOrderMasterWorksRepository;
import com.erp.MtSalesOrderMasterWorks.Repository.IMtSalesOrderMasterWorksSummaryRptRepository;
import com.erp.MtSalesOrderMasterWorks.Repository.IMtSalesOrderMasterWorksSummaryViewRepository;
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
public class CMtSalesOrderMasterWorksServiceImpl implements IMtSalesOrderMasterWorksService {


	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtSalesOrderMasterWorksRepository iMtSalesOrderMasterWorksRepository;

	@Autowired
	IMtSalesOrderMasterWorksSummaryViewRepository iMtSalesOrderMasterWorksSummaryViewRepository;

	@Autowired
	IMtSalesOrderMasterWorksSummaryRptRepository iMtSalesOrderMasterWorksSummaryRptRepository;

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
			CMtSalesOrderMasterWorksModel jsonModel = objectMapper.readValue(json.toString(),
					CMtSalesOrderMasterWorksModel.class);
			if (!isApprove) {

				String sales_order_date = jsonModel.getSales_order_date();

				CMtSalesOrderMasterWorksModel cMtSalesOrderMasterWorksModel = new CMtSalesOrderMasterWorksModel();

				String query = "Select * FROM mt_sales_order_master_works WHERE is_delete = 0 and sales_order_no = '"
						+ sales_order_no.toString() + "' and sales_order_version = " + jsonModel.getSales_order_version()
						+ " and financial_year = '" + financial_year.toString() + "' and company_id = " + company_id + "";

				List<CMtSalesOrderMasterWorksModel> results = executeQuery.query(query,
						new BeanPropertyRowMapper<>(CMtSalesOrderMasterWorksModel.class));

				if (!results.isEmpty()) {
					update = true;
					cMtSalesOrderMasterWorksModel = results.get(0);
					cMtSalesOrderMasterWorksModel.setDeleted_on(new Date());
					cMtSalesOrderMasterWorksModel.setIs_delete(true);
					iMtSalesOrderMasterWorksRepository.save(cMtSalesOrderMasterWorksModel);

					jsonModel.setSales_order_version(cMtSalesOrderMasterWorksModel.getSales_order_version() + 1);

				}

				CMtSalesOrderMasterWorksModel responseSalesOrderMasterWorks = iMtSalesOrderMasterWorksRepository
						.save(jsonModel);

				responce.put("success", "1");
				responce.put("data", responseSalesOrderMasterWorks);
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
						"/api/MtSalesOrderMasterWorks/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesOrderMasterWorks/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	private Map<String, Object> FnSalesOrderDetailsUpdateRecord(JSONArray array, JSONObject commonIdsObj, CMtSalesOrderMasterWorksModel jsonModel) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = commonIdsObj.getInt("company_id");
//		int company_branch_id = commonIdsObj.getInt("company_branch_id");
//		String sales_order_no = commonIdsObj.getString("sales_order_no");
//		String financial_year = commonIdsObj.getString("financial_year");
		try {

			CMtSalesOrderMasterWorksModel salesOrderdetailMasterWorksModels =
					iMtSalesOrderMasterWorksRepository.save(jsonModel);

//			List<CMtSalesOrderDetailsTradingModel> salesOrderdetailsTradingModels = objectMapper.readValue(array.toString(),
//					new TypeReference<List<CMtSalesOrderDetailsTradingModel>>() {
//					});
//
//			iMtSalesOrderDetailsTradingRepository.saveAll(salesOrderdetailsTradingModels);
//			
			responce.put("success", "1");
			responce.put("data", salesOrderdetailMasterWorksModels);
			responce.put("error", "");
			responce.put("message", "Record updates successfully!...");
		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesOrderMasterWorks/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtSalesOrderMasterWorks/FnAddUpdateRecord",
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
			iMtSalesOrderMasterWorksRepository.deleteSalesDetails(sales_order_no, sales_order_version, company_id);

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
	public Map<String, Object> FnShowParticularRecordForUpdate(int sales_order_master_transaction_id, int company_id) {
		Map<String, Object> responce = new HashMap();
		CMtSalesOrderMasterWorksModel cMtSalesOrderMasterWorksModel = null;
		try {
			cMtSalesOrderMasterWorksModel = iMtSalesOrderMasterWorksRepository
					.FnShowParticularRecordForUpdate(sales_order_master_transaction_id, company_id);
			responce.put("success", "1");
			responce.put("data", cMtSalesOrderMasterWorksModel);
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
	public Page<CMtSalesOrderMasterWorksSummaryRptModel> FnShowAllReportRecords(Pageable pageable) {
		return iMtSalesOrderMasterWorksSummaryRptRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iMtSalesOrderMasterWorksSummaryViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}


	@Override
	public Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowParticularRecord(
			int sales_order_master_transaction_id, Pageable pageable, int company_id) {
		return iMtSalesOrderMasterWorksSummaryViewRepository.FnShowParticularRecord(sales_order_master_transaction_id,
				pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String sales_order_no, int sales_order_version,
	                                                                 String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			Map<String, Object> salesOrderMasterWorksRecord = iMtSalesOrderMasterWorksRepository
					.FnShowSalesOrderMasterRecord(sales_order_no, sales_order_version, financial_year, company_id);

			responce.put("salesOrderMasterWorksRecord", salesOrderMasterWorksRecord);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(
			String customer_order_no, Pageable pageable, int company_id) {
		return iMtSalesOrderMasterWorksSummaryViewRepository.FnShowSalesOrderDetailsTradingCustomerRecord(customer_order_no,
				pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {

		Map<String, Object> response = new HashMap<>();
		String query;

		if ("summary".equals(reportType)) {
			query = "SELECT * FROM mtv_sales_order_master_works_summary_rpt";
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
