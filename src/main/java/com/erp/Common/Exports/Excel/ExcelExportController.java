package com.erp.Common.Exports.Excel;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/excelexport")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExcelExportController {

	@PostMapping("/FnExportToExcel")
	public void FnExportToExcel(@RequestBody Map<String, Object> exportJson, HttpServletResponse response) {
		try {

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String currentDateTime = dateFormatter.format(new Date());

			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
			response.setHeader(headerKey, headerValue);

			// You can convert to JSONObject if needed:
			JSONObject jsonObject = new JSONObject(exportJson);

			ExcelExporter excelExporter = new ExcelExporter(jsonObject);
			excelExporter.export(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
