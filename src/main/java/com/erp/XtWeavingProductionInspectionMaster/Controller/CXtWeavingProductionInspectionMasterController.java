package com.erp.XtWeavingProductionInspectionMaster.Controller;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.*;

import com.erp.XtWeavingProductionLoomMaster.Repository.IXtWeavingProductionLoomDetailsRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.erp.XtWeavingProductionInspectionMaster.Service.IXtWeavingProductionInspectionMasterService;

import javax.servlet.http.HttpServletResponse;

import static com.erp.StoreStockReport.Controller.CStoreStockReport.exportToExcel;


@RestController
@RequestMapping("/api/XtWeavingProductionInspectionMaster")
public class CXtWeavingProductionInspectionMasterController {

	@Autowired
	IXtWeavingProductionInspectionMasterService iXtWeavingProductionInspectionMasterService;

	@Autowired
	IXtWeavingProductionLoomDetailsRepository iXtWeavingProductionLoomDetailsRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;


	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("WeavingProductionInspectionData") JSONObject jsonObjec) {
		 Map<String, Object> responce = iXtWeavingProductionInspectionMasterService.FnAddUpdateRecord(jsonObjec);
		JSONObject commonIdsObj = (JSONObject) jsonObjec.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		Map<String, Object> stockResponse = (Map<String, Object>) responce.get("stockResponse");
		if(stockResponse.containsKey("FGStockAddDetails")){
			Map<String, Object> FgStockDetails = (Map<String, Object>) stockResponse.get("FGStockAddDetails");
			List<Map<String, Object>> fgBatchList = (List<Map<String, Object>>) FgStockDetails.get("issuedBatchList");
			if (!fgBatchList.isEmpty()) {
				String storedProcedure = "{call fabric_insert_update_stock(?,?,?,?,?)}";
				jdbcTemplate.execute((Connection con) -> {
					try (CallableStatement cs = con.prepareCall(storedProcedure)) {
						for (Map<String, Object> batch : fgBatchList) {
							cs.setString(1, batch.get("goods_receipt_no").toString());
							cs.setString(2, batch.get("stock_date").toString());
							cs.setString(3, batch.get("product_material_id").toString());
							cs.setString(4, batch.get("batch_no").toString());
							cs.setInt(5, company_id);
							cs.addBatch();
						}
						cs.executeBatch();
					}
					return null;
				});
			}
		}else if(stockResponse.containsKey("FGStockReducedDetails")){
			Map<String, Object> FgStockDetails = (Map<String, Object>) stockResponse.get("FGStockReducedDetails");
			List<Map<String, Object>> fgBatchList = (List<Map<String, Object>>) FgStockDetails.get("issuedBatchList");
			if (!fgBatchList.isEmpty()) {
				String storedProcedure = "{call fabric_insert_update_stock(?,?,?,?,?)}";
				jdbcTemplate.execute((Connection con) -> {
					try (CallableStatement cs = con.prepareCall(storedProcedure)) {
						for (Map<String, Object> batch : fgBatchList) {
							cs.setString(1, batch.get("goods_receipt_no").toString());
							cs.setString(2, batch.get("stock_date").toString());
							cs.setString(3, batch.get("product_material_id").toString());
							cs.setString(4, batch.get("batch_no").toString());
							cs.setInt(5, company_id);
							cs.addBatch();
						}
						cs.executeBatch();
					}
					return null;
				});
			}
		}
		 return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{weaving_production_inspection_master_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int weaving_production_inspection_master_id, @PathVariable int company_id,@PathVariable String deleted_by) {
		 return iXtWeavingProductionInspectionMasterService.FnDeleteRecord(weaving_production_inspection_master_id, company_id,deleted_by);
	}

	@DeleteMapping("/FnDeleteWeavingProductionData/{weaving_production_inspection_master_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteWeavingProductionData(@PathVariable int weaving_production_inspection_master_id, @PathVariable int company_id,@PathVariable String deleted_by) {
		return iXtWeavingProductionInspectionMasterService.FnDeleteRecord(weaving_production_inspection_master_id, company_id,deleted_by);
	}
	
	@GetMapping("/FnShowParticularRecordForUpdate/{weaving_production_inspection_master_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int weaving_production_inspection_master_id, @PathVariable int company_id)  {
		Map<String, Object> responce = iXtWeavingProductionInspectionMasterService.FnShowParticularRecordForUpdate(weaving_production_inspection_master_id, company_id);
		return responce;
	}
	
	@GetMapping("/FnShowParticularInspectionShiftSummary/{inspection_production_date}/{company_id}")
	public Map<String, Object> FnShowParticularInspectionShiftSummary(@PathVariable String inspection_production_date, @PathVariable int company_id) {
		Map<String, Object> responce = iXtWeavingProductionInspectionMasterService.FnShowParticularInspectionShiftSummary(inspection_production_date, company_id);
		return responce;
	}

	@PostMapping ("/FnSaveDailyProductionData")
	public Map<String, Object> FnSaveDailyProductionData(@RequestParam("DailyProductionReportData") JSONObject jsonObject)  {
		Map<String, Object> responce =  iXtWeavingProductionInspectionMasterService.FnSaveDailyProductionData(jsonObject);
		return responce;
	}

	@GetMapping("/FnGetInspectionMasterData/{set_no}/{company_id}")
	public Map<String, Object> FnGetInspectionMasterData(@PathVariable String set_no, @PathVariable Integer company_id){
		Map<String, Object> response = new HashMap<>();
		try {
			ArrayList<Map<String, Object>> MasterData = iXtWeavingProductionLoomDetailsRepository.FnGetInspectionMasterData(set_no, company_id);

			response.put("Success", 1);
			response.put("InspectionMasterData", MasterData);
		} catch (Exception e) {
			response.put("data", "");
			response.put("error", e.getMessage());
		}
		return response;
	}


    @GetMapping("/FnGetFabricProductionData/{from_date}/{to_date}/{company_id}/{set_no}/{sort_no}/{roll_no}/{style}/{stock_status}/{godown_id}")
    public ArrayList<Map<String, Object>> FnGetFabricProductionData(
            @PathVariable String from_date,
            @PathVariable String to_date,
            @PathVariable Integer company_id,
            @PathVariable String set_no,
            @PathVariable String sort_no,
            @PathVariable String roll_no,
		    @PathVariable String style, @PathVariable String stock_status , @PathVariable Integer godown_id) {


        ArrayList<Map<String, Object>> masterData = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM xtv_weaving_production_inspection_details AS xwpid WHERE xwpid.company_id = ? AND xwpid.stock_status IN ('A', 'D', 'PD') ");
            List<Object> params = new ArrayList<>();
            params.add(company_id);

            if (from_date.equals(to_date)) {
                query.append("AND xwpid.inspection_production_date = ? ");
                params.add(from_date);
            } else {
                query.append("AND xwpid.inspection_production_date BETWEEN ? AND ? ");
                params.add(from_date);
                params.add(to_date);
            }

            if (!"set_no".equals(set_no) && !set_no.isEmpty()) {
                query.append("AND xwpid.inspection_production_set_no = ? ");
                params.add(set_no);
            }
            if (!"sort_no".equals(sort_no) && !sort_no.isEmpty()) {
                query.append("AND xwpid.sort_no = ? ");
                params.add(sort_no);
            }
            if (!"roll_no".equals(roll_no) && !roll_no.isEmpty()) {
                query.append("AND xwpid.roll_no = ? ");
                params.add(roll_no);
            }
			if (!"style".equals(style) && !style.isEmpty()) {
				query.append("AND xwpid.style = ? ");
				params.add(style);
			}
			if (!"stock_status".equals(stock_status) && !stock_status.isEmpty()) {
				query.append("AND xwpid.stock_status  = ?  ");
				params.add(stock_status);
			}
			if(godown_id != 0){
				query.append("AND xwpid.godown_id  = ?  ");
				params.add(godown_id);
			}
			query.append("order by xwpid.roll_no ");
            masterData = new ArrayList<>(jdbcTemplate.queryForList(query.toString(), params.toArray()));

        }catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());

            masterData.clear();
            masterData.add(errorMap);
        }
        return masterData;
    }

	@PostMapping("/GetFabricProductionReportExport")
	public void FnGetFabricProductionReportExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
		try {

			String from_date = commonIdsObj.getString("from_date");
			String to_date= commonIdsObj.getString("to_date");
			byte company_id =(byte) commonIdsObj.getInt("company_id");
			String set_no = commonIdsObj.getString("set_no");
			String sort_no = commonIdsObj.getString("sort_no");
			Integer  roll_no =  commonIdsObj.getInt("roll_no");
			String stock_status = commonIdsObj.getString("stock_status");
			String style = commonIdsObj.getString("style");


			MapSqlParameterSource params = new MapSqlParameterSource();
			Map<String, String> detailsColumns = new LinkedHashMap<>();
			List<Map<String, String>> selectedColumns = new ArrayList<>();
			Object selectedColumnsObj = commonIdsObj.get("selectedColumns");
			//Query for custom selected boxes
			StringBuilder query = new StringBuilder("SELECT ");

			if (selectedColumnsObj instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) selectedColumnsObj;
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String label = jsonObject.getString("label");
					String value = jsonObject.getString("value");

					if ("inspection_production_status".equals(value)) {
						query.append("xwpid.stock_status_description");
						detailsColumns.put("stock_status_description", label);
					} else {
						query.append("xwpid.").append(value);
						detailsColumns.put(value, label);
					}

					if (i < jsonArray.length() - 1) {
						query.append(", ");
					}
				}
			}
			query.append(" FROM xtv_weaving_production_inspection_details AS xwpid ");
			query.append("WHERE xwpid.stock_status IN ('A', 'D', 'PD') ");

			if (from_date.equals(to_date)) {
				query.append("AND xwpid.inspection_production_date = :from_date ");
				params.addValue("from_date",from_date);
			} else {
				query.append("AND xwpid.inspection_production_date BETWEEN :from_date AND :to_date ");
				params.addValue("from_date",from_date);
				params.addValue("to_date",to_date);
			}
			if (!"set_no".equals(set_no) && !set_no.isEmpty()) {
				query.append("AND xwpid.inspection_production_set_no = :set_no ");
				params.addValue("set_no",set_no);
			}
			if (!"sort_no".equals(sort_no) && !sort_no.isEmpty()) {
				query.append("AND xwpid.sort_no = :sort_no ");
				params.addValue("sort_no",sort_no);
			}
			if ( roll_no != 0) {
				query.append("AND xwpid.roll_no = :roll_no ");
				params.addValue("roll_no",roll_no);
			}
			if (!"stock_status".equals(stock_status) && !stock_status.isEmpty()) {
				query.append("AND xwpid.stock_status = :stock_status ");
				params.addValue("stock_status",stock_status);
			}
			if (!"style".equals(style) && !style.isEmpty()) {
				query.append("AND xwpid.style = :style ");
				params.addValue("style",style);
			}
			if (company_id != 0) {
				query.append("AND xwpid.company_id = :company_id ");
				params.addValue("company_id", company_id);
			}
			query.append(" order by xwpid.roll_no ");
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
			List<Map<String, Object>> FabricProductionDataToExport = namedParameterJdbcTemplate.queryForList(query.toString(), params);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			for (Map<String, Object> row : FabricProductionDataToExport) {
				Object dateValue = row.get("inspection_production_date");
				if (dateValue instanceof Date) {
					String formattedDate = dateFormat.format((Date) dateValue);
					row.put("inspection_production_date", formattedDate);
				}
			}
			List<String> totalsColumnHeader = new ArrayList<>();
			Map<String, String> subColumnsToMerge = new HashMap<>();
			exportToExcel(FabricProductionDataToExport, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/GetLastRecordRollNoWeavingInspection/{book_type_id}")
	public Map<String, Object> GetLastRecordRollNoWeavingInspection(@PathVariable int book_type_id)  {
		Map<String, Object> responce =  iXtWeavingProductionInspectionMasterService.GetLastRollNoWeavingInspection(book_type_id);
		return responce;
	}

	@PostMapping("/GetLoomProductionReportExport")
	public void GetLoomProductionReportExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
		try {

			String from_date = commonIdsObj.getString("from_date");
			String to_date= commonIdsObj.getString("to_date");
			byte company_id =(byte) commonIdsObj.getInt("company_id");
			String set_no = commonIdsObj.getString("set_no");
			String sort_no = commonIdsObj.getString("sort_no");
			String roll_no = commonIdsObj.getString("roll_no");

			MapSqlParameterSource params = new MapSqlParameterSource();
			Map<String, String> detailsColumns = new LinkedHashMap<>();
			List<Map<String, String>> selectedColumns = new ArrayList<>();
			Object selectedColumnsObj = commonIdsObj.get("selectedColumns");
			//Query for custom selected boxes
			StringBuilder query = new StringBuilder("SELECT ");
			if (selectedColumnsObj instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) selectedColumnsObj;
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String label = jsonObject.getString("label");
					String value = jsonObject.getString("value");
					query.append(value);
					if (i < jsonArray.length() - 1) {
						query.append(", ");
					}
					detailsColumns.put(value, label);
				}
			}
			query.append(" FROM xtv_weaving_production_inspection_details AS xwpid ");
			query.append("WHERE xwpid.stock_status IN ('A', 'D', 'P') ");

			if (from_date.equals(to_date)) {
				query.append("AND xwpid.inspection_production_date = :from_date ");
				params.addValue("from_date",from_date);
			} else {
				query.append("AND xwpid.inspection_production_date BETWEEN :from_date AND :to_date ");
				params.addValue("from_date",from_date);
				params.addValue("to_date",to_date);
			}
			if (!"set_no".equals(set_no) && !set_no.isEmpty()) {
				query.append("AND xwpid.inspection_production_set_no = :set_no ");
				params.addValue("set_no",set_no);
			}
			if (!"sort_no".equals(sort_no) && !sort_no.isEmpty()) {
				query.append("AND xwpid.sort_no = :sort_no ");
				params.addValue("sort_no",sort_no);
			}
			if (!"roll_no".equals(roll_no) && !roll_no.isEmpty()) {
				query.append("AND xwpid.roll_no = :roll_no ");
				params.addValue("roll_no",roll_no);
			}
			if (company_id != 0) {
				query.append("AND xwpid.company_id = :company_id ");
				params.addValue("company_id", company_id);
			}
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
			List<Map<String, Object>> FabricProductionDataToExport = namedParameterJdbcTemplate.queryForList(query.toString(), params);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			for (Map<String, Object> row : FabricProductionDataToExport) {
				Object dateValue = row.get("inspection_production_date");
				if (dateValue instanceof Date) {
					String formattedDate = dateFormat.format((Date) dateValue);
					row.put("inspection_production_date", formattedDate);
				}
			}
			List<String> totalsColumnHeader = new ArrayList<>();
			Map<String, String> subColumnsToMerge = new HashMap<>();
			exportToExcel(FabricProductionDataToExport, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
