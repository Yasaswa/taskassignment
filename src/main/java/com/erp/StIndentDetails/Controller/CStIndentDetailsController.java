package com.erp.StIndentDetails.Controller;

import com.erp.StIndentDetails.Model.CStIndentDetailsViewModel;
import com.erp.StIndentDetails.Repository.IStIndentMasterRepository;
import com.erp.StIndentDetails.Repository.IStIndentPOTermsRepository;
import com.erp.StIndentDetails.Repository.IStIndentSchedulesViewRepository;
import com.erp.StIndentDetails.Service.IStIndentDetailsService;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/StIndentDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CStIndentDetailsController {

	@Autowired
	IStIndentDetailsService iStIndentDetailsService;

	@Autowired
	IStIndentMasterRepository iStIndentMasterRepository;

	@Autowired
	IStIndentPOTermsRepository iStIndentPOTermsRepository;

	@Autowired
	IStIndentSchedulesViewRepository iStIndentSchedulesViewRepository;


	@Autowired
	JdbcTemplate executequery;

	@PostMapping("/FnAddUpdateRecord/{isApprove}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("StIndentData") JSONObject jsonObject, @PathVariable boolean isApprove) {
		Map<String, Object> responce = iStIndentDetailsService.FnAddUpdateRecord(jsonObject, isApprove);
		return responce;
	}


	@DeleteMapping("/FnDeleteRecord/{indent_version}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("indent_no") String indent_no, @RequestParam("user_name") String user_name, @PathVariable int indent_version, @PathVariable int company_id) {
		return iStIndentDetailsService.FnDeleteRecord(indent_no, indent_version, company_id, user_name);

	}

	@PostMapping("/FnCancleRecord/{indent_version}/{company_id}")
	public Map<String, Object> FnCancleRecord(@RequestParam("indent_no") String indent_no, @RequestParam("user_name") String user_name, @PathVariable int indent_version, @PathVariable int company_id) {
		return iStIndentDetailsService.FnCancleRecord(indent_no, indent_version, company_id, user_name);

	}

    @GetMapping("/FnGetPartialIndentMaterials/{indent_version}/{financial_year}/{company_id}")
    public Map<String, Object> FnGetPartialIndentMaterials(@RequestParam("indent_no") String indent_no, @PathVariable int indent_version, @PathVariable String financial_year, @PathVariable int company_id) {
        return iStIndentDetailsService.FnGetPartialIndentMaterials(indent_no, indent_version, financial_year, company_id);

    }

	@PostMapping("/FnPreCloseRecord/{company_id}/{UserId}")
	public Map<String, Object> FnPreCloseRecord(@PathVariable int company_id, @PathVariable int UserId, @RequestParam("indent_no") String indent_no, @RequestParam("IndentPreCloseData") JSONObject IndentPreCloseData) {
		return iStIndentDetailsService.FnPreCloseRecord(indent_no, company_id, UserId, IndentPreCloseData);
	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CStIndentDetailsViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id)
			throws SQLException {
		Page<CStIndentDetailsViewModel> cStIndentDetailsViewModel = iStIndentDetailsService
				.FnShowAllActiveRecords(pageable, company_id);
		return cStIndentDetailsViewModel;
	}

	@GetMapping("/FnShowParticularRecords/{indent_details_id}/{company_id}")
	public Page<CStIndentDetailsViewModel> FnShowParticularRecord(@PathVariable int indent_details_id,
	                                                              Pageable pageable, @PathVariable int company_id) {
		Page<CStIndentDetailsViewModel> cStIndentDetailsViewModel = iStIndentDetailsService
				.FnShowParticularRecord(indent_details_id, pageable, company_id);
		return cStIndentDetailsViewModel;

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
					if (row.getRowNum() == 9) {
						columns.add(cellValue);
					} else if (row.getRowNum() > 9) {
						getData.add(cellValue);

//						Quantity data entry check
						String decimalPattern = "([0-9]*)\\.([0-9]*)";
						boolean matchCellValue = Pattern.matches(decimalPattern, cellValue);
						if (cell.getColumnIndex() == 5
								&& !(NumberUtils.isDigits(cellValue) || matchCellValue == true)) {
							responce.put("success", "0");
							responce.put("error", "Please enter valid data in file!...");
							return responce;
						}
					} else if (row.getRowNum() >= 5 && row.getRowNum() <= 9 && cellValue.trim().length() > 0) {
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
			String enquiry_no = company_id[0].replace("_", "/");

			responce.put("success", "1");
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	public List<Map<String, Object>> transformData(ArrayList<Object> data, List<String> columns) {
		return data.stream()
				.filter(row -> row instanceof List) // Ensure that the element is a List
				.map(row -> (List<Object>) row) // Cast to List<Object>
				.map(row -> IntStream.range(0, columns.size())
						.boxed()
						.collect(Collectors.toMap(columns::get, index -> row.get(index))))
				.collect(Collectors.toList());
	}


	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{indent_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("indent_no") String indent_no,
	                                                                 @PathVariable int indent_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iStIndentDetailsService.FnShowAllDetailsandMastermodelRecords(indent_no, indent_version, financial_year,
				company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iStIndentDetailsService.FnShowAllReportRecords(pageable, reportType);

	}

}
