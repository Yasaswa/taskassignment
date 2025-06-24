package com.erp.Common.GenerateAutoNo.Service;

import com.erp.Common.GenerateAutoNo.Model.CProdTransactionNo;
import com.erp.Common.GenerateAutoNo.Model.GAutoNoModel;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class AutoGenerateServiceImp implements AutoGenerateNoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String FnGenerateAutoNo(JSONObject json, GAutoNoModel autoNoQuery) {
		int company_id = json.getInt("company_id");
		String tblName = json.getString("tblName");
		String fieldName = json.getString("fieldName");
		String column_name = json.getString("column_name");
		String column_value = json.getString("column_value");
		String financialShortYear = json.getString("financialShortYear");
		int length = Integer.parseInt(json.getString("Length"));

		String autoNumber = "";
		int diffLength = 0;
		String strAppend = "";
		Map<String, Object> generatedAutoNo = new HashMap<>();

		try {
			StringBuilder queryBuilder = new StringBuilder();

			queryBuilder.append("SELECT ");
			queryBuilder.append("COALESCE(MAX(SUBSTRING(" + fieldName + ", LENGTH(" + fieldName + ") - " + length
					+ " + 1, " + length + "))+1, 1)");
			queryBuilder.append(" FROM ");
			queryBuilder.append(tblName);
			queryBuilder.append(" WHERE ");
			queryBuilder.append(" company_id = " + company_id + " ");
			if (!tblName.equals("sm_product_fg_bom_mst") && !tblName.equals("sm_product_rm_bom_mst")) {
				queryBuilder.append("and financial_year = '" + financialShortYear + "'");
			}
			if (!column_name.equals("") && !column_value.equals("") ) {
				queryBuilder.append("and ")
						.append(column_name)
						.append(" = '")
						.append(column_value)
						.append("'");
			}

			System.out.println("autoGenerateNoJsonQueryExecution query: " + queryBuilder);
			Integer maxNumber = jdbcTemplate.queryForObject(queryBuilder.toString(), Integer.class);
			System.out.println("autoGenerateNoJsonQueryExecution Result: " + maxNumber);

			autoNumber = maxNumber.toString();

			if (length > autoNumber.length())
				diffLength = length - autoNumber.length();
			else if (length < autoNumber.length())
				diffLength = autoNumber.length() - length;
			if (diffLength > 0) {
				for (int recCnt = 0; recCnt < diffLength; recCnt++)
					strAppend = "0" + strAppend;
			}
			autoNumber = strAppend + autoNumber;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return autoNumber;
	}

	@Override
	public String FnGenerateMaterialId(JSONObject json, GAutoNoModel autoNoQuery) {
//		int company_id = json.getInt("company_id");
		String tblName = json.getString("tblName");
		String fieldName = json.getString("fieldName");
		JSONObject conditionsKey = json.getJSONObject("conditionsKey");
//		Get where condition key & value
		String key = conditionsKey.getString("key");
		String value = conditionsKey.getString("value");
		int length = Integer.parseInt(json.getString("Length"));

		String autoNumber = "";
		int diffLength = 0;
		String strAppend = "";
		Map<String, Object> generatedAutoNo = new HashMap<>();

		try {
			StringBuilder queryBuilder = new StringBuilder();

			queryBuilder.append("SELECT ");
			queryBuilder.append("COALESCE(MAX(SUBSTRING(" + fieldName + ", LENGTH(" + fieldName + ") - " + length
					+ " + 1, " + length + "))+1, 1)");
			queryBuilder.append(" FROM ");
			queryBuilder.append(tblName);
			
//			queryBuilder.append(" company_id = " + company_id + " ");
			if (!key.equals("") && !value.equals("")) {
				queryBuilder.append(" WHERE ");
//				queryBuilder.append("and product_type_id = '" + product_type_id + "'");
//				queryBuilder.append("and ").append(key).append(" = '").append(value).append("'");
				queryBuilder.append(key).append(" = '").append(value).append("'");

			}

			System.out.println("autoGenerateNoJsonQueryExecution query: " + queryBuilder);
			Integer maxNumber = jdbcTemplate.queryForObject(queryBuilder.toString(), Integer.class);
			System.out.println("autoGenerateNoJsonQueryExecution Result: " + maxNumber);

			autoNumber = maxNumber.toString();

			if (length > autoNumber.length())
				diffLength = length - autoNumber.length();
			else if (length < autoNumber.length())
				diffLength = autoNumber.length() - length;
			if (diffLength > 0) {
				for (int recCnt = 0; recCnt < diffLength; recCnt++)
					strAppend = "0" + strAppend;
			}
			autoNumber = strAppend + autoNumber;

			System.out.println("Autonumber: " + autoNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autoNumber;

	}

	@Override
	public Map<String, Object> FnGenerateTransactionNo(CProdTransactionNo autoNoQuery) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Get today's date in the format YYMMDD
//			String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyy"));

			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("SELECT COALESCE(MAX(SUBSTRING(")
					.append(autoNoQuery.getField_name())
					.append(", LENGTH(")
					.append(autoNoQuery.getField_name())
					.append(") - ")
					.append(autoNoQuery.getTrans_no_length())
					.append(" + 1, ")
					.append(autoNoQuery.getTrans_no_length())
					.append(")) + 1, 1) FROM ")
					.append(autoNoQuery.getEntity_name())
					.append(" WHERE company_id = ")
					.append(autoNoQuery.getCompany_id())
//					.append(" AND sub_section_id = '")
//					.append(autoNoQuery.getSub_section())
//					.append("'")
					;

			System.out.println("autoGenerateNoJsonQueryExecution query: " + queryBuilder);
			Integer maxNumber = jdbcTemplate.queryForObject(queryBuilder.toString(), Integer.class);
			System.out.println("autoGenerateNoJsonQueryExecution Result: " + maxNumber);

			if (maxNumber != null) {
				String transactionNumber = String.format("%s-%s-%04d", autoNoQuery.getSub_section_short_name(), autoNoQuery.getTransaction_date(), maxNumber);
				autoNoQuery.setProduction_code(transactionNumber);
			}

			response.put("data", autoNoQuery );
			response.put("success", 1);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 1);
			response.put("error", e.getMessage());
		}
		return response;
	}

}
