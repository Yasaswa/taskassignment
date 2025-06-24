package com.erp.SmProductSr.Controller;

import com.erp.SmProductSr.Model.CSmProductSrViewModel;
import com.erp.SmProductSr.Service.ISmProductSrService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/SmProductSr")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductSrController {

	@Autowired
	ISmProductSrService iSmProductSrService;

	@Value("${document.file.path}")
	private String filePath;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("SmProductSrData") JSONObject jsonObject, @RequestParam(name = "qrCodeFile", required = false) MultipartFile qrCodeFile) {
		Map<String, Object> responce = iSmProductSrService.FnAddUpdateRecord(jsonObject, qrCodeFile);
		return responce;

	}

	@GetMapping("/FnGetQRCode/{product_sr_id}")
	public byte[] FnGetCompanyLogo(@PathVariable int product_sr_id) throws IOException {
		HashMap<File, String> doc = new HashMap<File, String>();
		byte[] bytesFromFile = null;
		try {
			File directoryPath = new File(filePath + "DocStores" + File.separator + "Service" + File.separator + product_sr_id + File.separator);
			String[] contents = directoryPath.list();
			String extension = contents[0].substring(contents[0].lastIndexOf(".") + 0);
			String docLocalpath = filePath + "DocStores" + File.separator + "Service" + File.separator + product_sr_id + File.separator;

			File file = new File(docLocalpath + contents[0]);

			bytesFromFile = getBytes(file);
			System.out.println(bytesFromFile.length);

		} catch (NullPointerException e) {
			System.out.print("NullPointerException Caught");
		}
		return bytesFromFile;

	}

	public static byte[] getBytes(File f) throws IOException {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(f);
		int read;
		while ((read = fis.read(buffer)) != -1) {
			os.write(buffer, 0, read);
		}
		fis.close();
		os.close();
		return os.toByteArray();
	}


	@GetMapping("/FnShowAllMaintenanceMasterAndDetailsRecords/{product_sr_id}/{company_id}")
	public Map<String, Object> FnShowAllMaintenanceMasterAndDetailsRecords(@PathVariable String product_sr_id, @PathVariable int company_id)
			throws SQLException {
		return iSmProductSrService.FnShowAllSmProductSrMasterAndDetailsRecords(product_sr_id, company_id);
	}


	@DeleteMapping("/FnDeleteRecord/{product_sr_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable String product_sr_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iSmProductSrService.FnDeleteRecord(product_sr_id, company_id, deleted_by);

	}


	@GetMapping("/FnShowAllDefaultMappings/{company_id}")
	public Map<String, Object> FnShowAllActiveRecords(@PathVariable int company_id, @RequestParam String accordianSelectKey)
			throws SQLException {
		return iSmProductSrService.FnShowAllDefaultMappingsRecords(company_id, accordianSelectKey);
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_sr_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_sr_id, @PathVariable int company_id) {
		Map<String, Object> responce = iSmProductSrService.FnShowParticularRecordForUpdate(product_sr_id, company_id);
		return responce;
	}


	@GetMapping("/FnShowParticularRecords/{product_sr_id}/{company_id}")
	public Page<CSmProductSrViewModel> FnShowParticularRecord(@PathVariable int product_sr_id, Pageable pageable, @PathVariable int company_id) {
		Page<CSmProductSrViewModel> cSmProductSrViewModel = iSmProductSrService.FnShowParticularRecord(product_sr_id, pageable, company_id);
		return cSmProductSrViewModel;

	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iSmProductSrService.FnShowAllReportRecords(pageable, reportType);

	}

}
