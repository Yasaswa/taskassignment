package com.erp.MtDispatchChallanDetails.Controller;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanBatchwiseTradingViewModel;
import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanDetailsTradingViewModel;
import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanMasterTradingModel;
import com.erp.MtDispatchChallanDetails.Repository.IMtDispatchChallanBatchwiseTradingViewRepository;
import com.erp.MtDispatchChallanDetails.Repository.IMtDispatchChallanMasterTradingRepository;
import com.erp.MtDispatchChallanDetails.Service.IMtDispatchChallanDetailsService;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/MtDispatchChallanDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMtDispatchChallanDetailsController {
	@Autowired
	IMtDispatchChallanDetailsService iMtDispatchChallanDetailsService;

	@Autowired
	IMtDispatchChallanMasterTradingRepository iMtDispatchChallanMasterTradingRepository;

	@Value("${printOuts.file.path}")
	private String printOutsDirectory;

	@Autowired
	IMtDispatchChallanBatchwiseTradingViewRepository iMtDispatchChallanBatchwiseTradingViewRepository;


	@Autowired
	JdbcTemplate executequery;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("MtDispatchChallanData") JSONObject jsonObject) {
		Map<String, Object> responce = iMtDispatchChallanDetailsService.FnAddUpdateRecord(jsonObject);
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		Map<String, Object> stockResponse = (Map<String, Object>) responce.get("stockResponse");
		if(stockResponse.containsKey("FGStockReducedDetails")){
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

	@PostMapping("/FnDispatchChallanDetailsUpdateRecord")
	public Map<String, Object> FnDispatchChallanDetailsUpdateRecord(@RequestParam("MtDispatchChallanData") JSONObject jsonObject) {
		Map<String, Object> responce = iMtDispatchChallanDetailsService.FnDispatchChallanDetailsUpdateRecord(jsonObject);
		return responce;
	}

//	@DeleteMapping("/FnDeleteRecord/{dispatch_challan_no}/{dispatch_challan_version}/{company_id}/{deleted_by}")
//	public Map<String, Object> FnDeleteRecord(@PathVariable String dispatch_challan_no, @PathVariable int dispatch_challan_version, @PathVariable int company_id, @PathVariable String deleted_by) {
//		return iMtDispatchChallanDetailsService.FnDeleteRecord(dispatch_challan_no, dispatch_challan_version, company_id, deleted_by);
//
//	}
@PostMapping("/FnDeleteRecord")
public Map<String, Object> FnDeleteRecord(@RequestBody CMtDispatchChallanMasterTradingModel request) {
	// Extract parameters from the request body
	String dispatchChallanNo = request.getDispatch_challan_no();
	int dispatchChallanVersion = request.getDispatch_challan_version();
	int companyId = request.getCompany_id();
	String deletedBy = request.getDeleted_by();

	// Call the service with the extracted parameters
	return iMtDispatchChallanDetailsService.FnDeleteRecord(dispatchChallanNo, dispatchChallanVersion, companyId, deletedBy);
}
	@DeleteMapping("/FnDeleteDailyDispatchReport/{dispatch_challan_no}/{dispatch_challan_version}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteDailyDispatchReport(@PathVariable String dispatch_challan_no, @PathVariable int dispatch_challan_version, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iMtDispatchChallanDetailsService.FnDeleteDailyDispatchReport(dispatch_challan_no, dispatch_challan_version, company_id, deleted_by);

	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CMtDispatchChallanDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id)
			throws SQLException {
		Page<CMtDispatchChallanDetailsTradingViewModel> cmtDispatchChallanDetailsTradingViewModel = iMtDispatchChallanDetailsService
				.FnShowAllActiveRecords(pageable, company_id);
		return cmtDispatchChallanDetailsTradingViewModel;
	}

	@GetMapping("/FnShowParticularRecords/{dispatch_challan_details_transaction_id}/{company_id}")
	public Page<CMtDispatchChallanDetailsTradingViewModel> FnShowParticularRecord(@PathVariable int dispatch_challan_details_transaction_id,
	                                                                              Pageable pageable, @PathVariable int company_id) {
		Page<CMtDispatchChallanDetailsTradingViewModel> cmtDispatchChallanDetailsTradingViewModel = iMtDispatchChallanDetailsService
				.FnShowParticularRecord(dispatch_challan_details_transaction_id, pageable, company_id);
		return cmtDispatchChallanDetailsTradingViewModel;

	}

	@PostMapping("/FnReadExcel")
	public HashMap<Object, Object> FnReadExcel(@RequestParam("file") MultipartFile reapExcelDataFile) {
		HashMap<Object, Object> responce = new HashMap<Object, Object>();

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			List<String> columns = new ArrayList<String>();
			List<String> formFieldData = new ArrayList<String>();
			ArrayList<Object> data = new ArrayList<Object>();
			XSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println("=> " + sheet.getSheetName());
			DataFormatter dataFormatter = new DataFormatter();
			System.out.println("Iterating over Rows and Columns using Iterator");
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				System.out.println("Row Number: " + row.getRowNum());
				Iterator<Cell> cellIterator = row.cellIterator();
				List<String> getData = new ArrayList<>();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String cellValue = dataFormatter.formatCellValue(cell);
					if (row.getRowNum() == 10) {
						columns.add(cellValue);
					} else if (row.getRowNum() > 10) {
						getData.add(cellValue);

//						Quantity data entry check
						String decimalPattern = "([0-9]*)\\.([0-9]*)";
						boolean matchCellValue = Pattern.matches(decimalPattern, cellValue);
						if (cell.getColumnIndex() == 4
								&& !(NumberUtils.isDigits(cellValue) || matchCellValue == true)) {
							responce.put("success", "0");
							responce.put("error", "Please enter valid data in file!...");
							return responce;
						}
					} else if (row.getRowNum() >= 5 && row.getRowNum() <= 8) {
						formFieldData.add(cellValue);
					}
					System.out.print(cellValue + "\t");
				}
				if (getData.size() != 0) {
					data.add(getData);
				}

				System.out.println();
			}

			String filename = reapExcelDataFile.getOriginalFilename().substring(0,
					reapExcelDataFile.getOriginalFilename().lastIndexOf("."));

			String[] company_id = filename.split("@");
			String dispatch_challan_no = company_id[0].replace("_", "/");

			CMtDispatchChallanMasterTradingModel cmtDispatchChallanMasterTradingModel = iMtDispatchChallanMasterTradingRepository.getLastDispatchChallanVersion(dispatch_challan_no, company_id[1]);


			List<CMtDispatchChallanBatchwiseTradingViewModel> cmtDispatchChallanBatchwiseTradingViewModel = iMtDispatchChallanBatchwiseTradingViewRepository
					.FnShowDispatchChallanBatchwise(cmtDispatchChallanMasterTradingModel.getDispatch_challan_no(), cmtDispatchChallanMasterTradingModel.getDispatch_challan_version(), Integer.parseInt(company_id[1]));


			responce.put("success", "1");
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);
			responce.put("dispatchChallanVersion", cmtDispatchChallanMasterTradingModel.getDispatch_challan_version());
			responce.put("dispatchChallan", cmtDispatchChallanBatchwiseTradingViewModel);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return responce;
	}


	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{dispatch_challan_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("dispatch_challan_no") String dispatch_challan_no,
	                                                                 @PathVariable int dispatch_challan_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iMtDispatchChallanDetailsService.FnShowAllDetailsandMastermodelRecords(dispatch_challan_no, dispatch_challan_version, financial_year,
				company_id);
	}

	@GetMapping("/FnCancelDispatchChallan/{dispatch_challan_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnCancelDispatchChallan(@RequestParam("dispatch_challan_no") String dispatch_challan_no,
	                                                   @PathVariable int dispatch_challan_version, @PathVariable String financial_year, @PathVariable int company_id, @RequestParam("modified_by") String modifiedBy)
			throws SQLException {
		return iMtDispatchChallanDetailsService.FnCancelDispatchChallan(dispatch_challan_no, dispatch_challan_version, financial_year,
				company_id, modifiedBy);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iMtDispatchChallanDetailsService.FnShowAllReportRecords(pageable, reportType);

	}

	@PostMapping("/FnShowDispatchScheduleMasterNos/{company_id}")
	public Map<String, Object> FnShowDispatchScheduleMasterNos(
			@RequestParam("customerOrderNos") JSONObject customerOrderNo, Pageable pageable,
			@PathVariable int company_id) {
		return iMtDispatchChallanDetailsService.FnShowDispatchScheduleMasterNos(customerOrderNo, pageable,
				company_id);
	}

	@PostMapping("/FnShowSalesOrderDetails/{company_id}")
	public Map<String, Object> FnShowSalesOrderDetails(
			@RequestParam("customerOrderNos") JSONObject customerOrderNo, Pageable pageable,
			@PathVariable int company_id) {
		return iMtDispatchChallanDetailsService.FnShowSalesOrderDetails(customerOrderNo, pageable,
				company_id);
	}


	@PostMapping("/FnShowDispatchScheduleDetails/{company_id}")
	public Map<String, Object> FnShowDispatchScheduleDetails(@RequestParam("dispatchScheduleNos") JSONObject dispatchScheduleNo, Pageable pageable,@PathVariable int company_id) {
		return iMtDispatchChallanDetailsService.FnShowDispatchScheduleDetails(dispatchScheduleNo, pageable,
				company_id);
	}

	@PostMapping("/FnSendEmail/{dispatch_challan_details_transaction_id}/{company_id}")
	public Map<String, Object> FnSendEmail(@PathVariable int dispatch_challan_details_transaction_id,
	                                       @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
	                                       @RequestParam("dispatchChallanPDF") MultipartFile dispatchChallanPDF) {

		// Ensure the upload directory exists; create it if not
		File uploadDir = new File(printOutsDirectory.toString());
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Save the file to the specified directory
		String fileName = dispatchChallanPDF.getOriginalFilename();
		Path filePath = Paths.get(printOutsDirectory.toString(), fileName);

		try {
			dispatchChallanPDF.transferTo(filePath.toFile());

			// Update the mail-attachments means add the newly created file-path.
			JSONArray mailAttachments = (JSONArray) emailData.get("mailAttachmentFilePaths");
			mailAttachments.put(filePath.toString());
			emailData.put("mailAttachmentFilePaths", mailAttachments);
			System.out.println("Sales Order PDF File Saved: " + filePath.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Now send for email sending.
		Map<String, Object> emailSendingResponse = iMtDispatchChallanDetailsService.FnSendEmail(company_id, dispatch_challan_details_transaction_id, emailData);
		return emailSendingResponse;
	}

	@PostMapping ("/FnSaveDailyDispatchChallanData")
	public Map<String, Object> FnSaveDailyDispatchChallanData(@RequestParam("DailyDispatchChallanReportData") JSONObject jsonObject)  {
		Map<String, Object> responce =  iMtDispatchChallanDetailsService.FnSaveDailyDispatchChallanData(jsonObject);
		return responce;
	}
}
