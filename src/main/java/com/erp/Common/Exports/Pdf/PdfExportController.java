package com.erp.Common.Exports.Pdf;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/pdfexport")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PdfExportController {

	@PostMapping("/FnExportToPdf")
	public void exportToPDF(@RequestParam("export") JSONObject exportJson, HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		PdfExporter exporter = new PdfExporter(exportJson);
		exporter.export(response);

	}

}
