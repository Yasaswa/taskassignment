package com.erp.RawMaterial.SmProductRmBomMst.Controller;

import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmvProductRmBomDetails;
import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmvProductRmBomListingModel;
import com.erp.RawMaterial.SmProductRmBomMst.Service.ISmProductRmBomMstService;
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
@RequestMapping("/api/SmProductRmBomMst")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductRmBomMstController {

	@Autowired
	ISmProductRmBomMstService iSmProductRmBomMstService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam(value = "bomDetailData") JSONObject detailObject) {
		Map<String, Object> resp = new HashMap<>();
		resp = iSmProductRmBomMstService.FnAddUpdateRecord(detailObject);
		return resp;

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

	@GetMapping("/FnShowAllActiveRecordsToExport/{product_parent_rm_id}")
	List<CSmvProductRmBomListingModel> FnShowAllActiveRecordsToExport(@PathVariable String product_parent_rm_id)
			throws SQLException {
		List<CSmvProductRmBomListingModel> cSmProductRmBomMstListingModel = null;
		cSmProductRmBomMstListingModel = iSmProductRmBomMstService.FnShowAllActiveRecordsToExport(product_parent_rm_id);
		return cSmProductRmBomMstListingModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_rm_bom_id}")
	public Page<CSmvProductRmBomDetails> FnShowParticularRecordForUpdate(@PathVariable int product_rm_bom_id,
	                                                                     Pageable pageable) {
		Page<CSmvProductRmBomDetails> cSmProductRmBomMstModel = iSmProductRmBomMstService
				.FnShowParticularRecordForUpdate(product_rm_bom_id, pageable);

		return cSmProductRmBomMstModel;
	}

	@GetMapping("/FnShowParticularRecords/{product_parent_rm_id}")
	public Page<CSmvProductRmBomListingModel> FnShowParticularRecords(@PathVariable String product_parent_rm_id,
	                                                                  Pageable pageable) {
		Page<CSmvProductRmBomListingModel> cSmProductRmBomListingModel = iSmProductRmBomMstService.FnShowParticularRecords(product_parent_rm_id,
				pageable);
		return cSmProductRmBomListingModel;

	}

	@PostMapping("/FnDeleteRecord/{deleted_by}/{company_id}")
	public Object FnDeleteRecord(@RequestParam String product_rm_bom_no, @PathVariable String deleted_by, @PathVariable int company_id) {
		JSONObject object = new JSONObject();
		object = iSmProductRmBomMstService.FnDeleteRecord(product_rm_bom_no, deleted_by, company_id);
		return object.toString();
	}

}
