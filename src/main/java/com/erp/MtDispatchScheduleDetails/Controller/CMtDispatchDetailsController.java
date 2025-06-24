package com.erp.MtDispatchScheduleDetails.Controller;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleDetailsTradingViewModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleMasterTradingModel;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleDetailsTradingRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleMasterTradingRepository;
import com.erp.MtDispatchScheduleDetails.Service.IMtDispatchDetailsService;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/MtDispatchScheduleDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMtDispatchDetailsController {

	@Autowired
	IMtDispatchDetailsService iMtDispatchDetailsService;

	@Autowired
	IMtDispatchScheduleMasterTradingRepository iMtDispatchScheduleMasterTradingRepository;

	@Autowired
	IMtDispatchScheduleDetailsTradingRepository iMtDispatchScheduleDetailsTradingRepository;

	@Autowired
	JdbcTemplate executequery;


	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("MtDispatchScheduleData") JSONObject jsonObject) {
		Map<String, Object> responce = iMtDispatchDetailsService.FnAddUpdateRecord(jsonObject);
		return responce;
	}

	@PostMapping("/FnDispatchScheduleDetailsUpdateRecord")
	public Map<String, Object> FnDispatchScheduleDetailsUpdateRecord(@RequestParam("MtDispatchScheduleData") JSONObject jsonObject) {
		Map<String, Object> responce = iMtDispatchDetailsService.FnDispatchScheduleDetailsUpdateRecord(jsonObject);
		return responce;
	}


	@DeleteMapping("/FnDeleteRecord/{dispatch_schedule_version}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("dispatch_schedule_no") String dispatch_schedule_no, @PathVariable int dispatch_schedule_version, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iMtDispatchDetailsService.FnDeleteRecord(dispatch_schedule_no, dispatch_schedule_version, company_id, deleted_by);

	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CMtDispatchScheduleDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id)
			throws SQLException {
		Page<CMtDispatchScheduleDetailsTradingViewModel> cmtDispatchScheduleDetailsTradingViewModel = iMtDispatchDetailsService
				.FnShowAllActiveRecords(pageable, company_id);
		return cmtDispatchScheduleDetailsTradingViewModel;
	}


	@PostMapping("/FnShowDispatchScheduleDetailsTradingRecords/{dispatch_schedule_version}/{company_id}")
	public Map<String, Object> FnShowDispatchScheduleDetailsTradingRecords(@RequestParam("customerOrderNos") JSONObject customerOrderNo, @RequestParam("dispatch_schedule_no") String dispatch_schedule_no,
	                                                                       @PathVariable int dispatch_schedule_version, @PathVariable int company_id)
			throws SQLException {
		return iMtDispatchDetailsService
				.FnShowDispatchScheduleDetailsTradingRecords(customerOrderNo, dispatch_schedule_no, dispatch_schedule_version, company_id);
	}


	@GetMapping("/FnShowParticularRecords/{dispatch_schedule_details_transaction_id}/{company_id}")
	public Page<CMtDispatchScheduleDetailsTradingViewModel> FnShowParticularRecord(@PathVariable int dispatch_schedule_details_transaction_id,
	                                                                               Pageable pageable, @PathVariable int company_id) {
		Page<CMtDispatchScheduleDetailsTradingViewModel> cmtDispatchScheduleDetailsTradingViewModel = iMtDispatchDetailsService
				.FnShowParticularRecord(dispatch_schedule_details_transaction_id, pageable, company_id);
		return cmtDispatchScheduleDetailsTradingViewModel;

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
			String dispatch_schedule_no = company_id[0].replace("_", "/");

			CMtDispatchScheduleMasterTradingModel cmtDispatchScheduleMasterTradingModel = iMtDispatchScheduleMasterTradingRepository.getLastDispatchScheduleVersion(dispatch_schedule_no, company_id[1]);

			//*** Escape master & details tables in readExel function
			// *** WHEN NEW TABLES ADD THEN FOLLOWING FUNCTIONALITY USE...
//			List<CPtPurchaseOrderSchedulesViewModel> cptPurchaseOrderSchedulesViewModel = iPtPurchaseOrderSchedulesViewRepository
//					.FnShowPurchaseOrderSchedules(cPtPurchaseOrderMasterModel.getPurchase_order_no(), cPtPurchaseOrderMasterModel.getPurchase_order_version(), Integer.parseInt(company_id[1]));
//			List<CPtPurchaseOrderPaymentTermsModel> cptPurchaseOrderPaymentTermsModel = iPtPurchaseOrderPaymentTermsRepository
//					.FnShowPurchaseOrderPaymentTerms(cPtPurchaseOrderMasterModel.getPurchase_order_no(), cPtPurchaseOrderMasterModel.getPurchase_order_version(), Integer.parseInt(company_id[1]));
//			List<CPtPurchaseOrderTermsModel> cptPurchaseOrderTermsTradingModel = iPtPurchaseOrderTermsTradingRepository
//					.FnShowPurchaseOrderTermsTrading(cPtPurchaseOrderMasterModel.getPurchase_order_no(), cPtPurchaseOrderMasterModel.getPurchase_order_version(), Integer.parseInt(company_id[1]));
//			List<CPtPurchaseOrderTaxSummaryViewModel> cptPurchaseOrderTaxSummaryViewModel = iPtPurchaseOrderTaxSummaryViewRepository
//					.FnShowPurchaseOrderTaxSummaryView(cPtPurchaseOrderMasterModel.getPurchase_order_no(), cPtPurchaseOrderMasterModel.getPurchase_order_version(), Integer.parseInt(company_id[1]));


			responce.put("success", "1");
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);
			responce.put("dispatchScheduleVersion", cmtDispatchScheduleMasterTradingModel.getDispatch_schedule_version());
//			responce.put("PurchaseOrderSchedules", cptPurchaseOrderSchedulesViewModel);
//			responce.put("PurchaseOrderPaymentTerms", cptPurchaseOrderPaymentTermsModel);
//			responce.put("PurchaseOrderTermsTrading", cptPurchaseOrderTermsTradingModel);
//			responce.put("PurchaseOrderTaxSummaryView", cptPurchaseOrderTaxSummaryViewModel);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return responce;
	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{dispatch_schedule_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("dispatch_schedule_no") String dispatch_schedule_no,
	                                                                 @PathVariable int dispatch_schedule_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iMtDispatchDetailsService.FnShowAllDetailsandMastermodelRecords(dispatch_schedule_no, dispatch_schedule_version, financial_year,
				company_id);
	}


	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iMtDispatchDetailsService.FnShowAllReportRecords(pageable, reportType);

	}


}
