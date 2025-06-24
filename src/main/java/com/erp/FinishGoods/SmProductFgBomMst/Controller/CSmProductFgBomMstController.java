package com.erp.FinishGoods.SmProductFgBomMst.Controller;

import com.erp.FinishGoods.SmProductFgBomMst.Model.CSmvProductFgBomListingModel;
import com.erp.FinishGoods.SmProductFgBomMst.Service.ISmProductFgBomMstService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/SmProductFgBomMst")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgBomMstController {

	@Autowired
	ISmProductFgBomMstService iSmProductFgBomMstService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam(value = "bomDetailData") JSONObject detailObject)
			throws SQLException {
		Map<String, Object> responce = new HashMap<>();
		responce = iSmProductFgBomMstService.FnAddUpdateRecord(detailObject);

		return responce;

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
							throw new Exception();
						}
					} else if (row.getRowNum() >= 5 && row.getRowNum() <= 8 && !cellValue.isEmpty()) {
						formFieldData.add(cellValue);
					}
					System.out.print(cellValue + "\t");
				}
				if (getData.size() != 0) {
					data.add(getData);
				}

				System.out.println();
			}

			responce.put("success", "1");
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);
		} catch (Exception e) {
			responce.put("success", "0");
			responce.put("error", "Please enter valid data in file!...");
			e.printStackTrace();
		}
		return responce;
	}

	@GetMapping("/FnShowAllActiveRecordsToExport/{product_parent_fg_id}")
	List<CSmvProductFgBomListingModel> FnShowAllActiveRecordsToExport(@PathVariable String product_parent_fg_id)
			throws SQLException {
		List<CSmvProductFgBomListingModel> cSmProductfgBomMstListingModel = null;
		try {
			cSmProductfgBomMstListingModel = iSmProductFgBomMstService
					.FnShowAllActiveRecordsToExport(product_parent_fg_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductfgBomMstListingModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_fg_bom_id}")
	public List<Map<String, Object>> FnShowParticularRecordForUpdate(@PathVariable int product_fg_bom_id,
	                                                                 Pageable pageable) throws SQLException {

		return iSmProductFgBomMstService.FnShowParticularRecordForUpdate(product_fg_bom_id,
				pageable);
	}

	@GetMapping("/FnShowParticularRecords/{product_parent_fg_id}")
	public Page<CSmvProductFgBomListingModel> FnShowParticularRecords(@PathVariable String product_parent_fg_id,
	                                                                  Pageable pageable) throws SQLException {
		Page<CSmvProductFgBomListingModel> cSmProductfgBomListingModel = null;
		try {
			cSmProductfgBomListingModel = iSmProductFgBomMstService.FnShowParticularRecords(product_parent_fg_id,
					pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductfgBomListingModel;

	}

	@PostMapping("/FnDeleteRecord/{deleted_by}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@RequestParam String product_fg_bom_no, @PathVariable String deleted_by, @PathVariable int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			responce = iSmProductFgBomMstService.FnDeleteRecord(product_fg_bom_no, deleted_by, company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}


	@GetMapping("/FnShowProductFgBomMstRecord/{product_parent_fg_id}/{company_id}")
	public List<Map<String, Object>> FnShowProductFgBomMstRecord(@PathVariable String product_parent_fg_id, @PathVariable int company_id) {
		List<Map<String, Object>> cSmvProductFgBomDetails = iSmProductFgBomMstService.FnShowProductFgBomMstRecord(product_parent_fg_id, company_id);
		return cSmvProductFgBomDetails;

	}

}
