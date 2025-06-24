package com.erp.FinishGoods.SmProductFg.Controller;

import com.erp.FinishGoods.SmProductFg.Repository.ISmProductFgRepository;
import com.erp.FinishGoods.SmProductFg.Service.ISmProductFgService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/api/SmProductFg")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgController {

	@Autowired
	ISmProductFgService iSmProductFgService;

	@Autowired
	ISmProductFgRepository iSmProductFgRepository;

	@Value("${document.file.path}")
	private String filePath;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("TransData") JSONObject jsonObject, @RequestParam(name = "qrCodeFile", required = false) MultipartFile qrCodeFile) {
		Map<String, Object> responce = iSmProductFgService.FnAddUpdateRecord(jsonObject, qrCodeFile);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{product_fg_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable String product_fg_id, @PathVariable String deleted_by) {
		return iSmProductFgService.FnDeleteRecord(product_fg_id, deleted_by);

	}

	@GetMapping("/FnShowAllProductFgDetailsAndMasterRecords/{product_fg_id}/{company_id}")
	public Map<String, Object> FnShowAllProductFgDetailsAndMasterRecords(@PathVariable String product_fg_id,
	                                                                     @PathVariable int company_id) throws SQLException {
		return iSmProductFgService.FnShowAllProductFgDetailsAndMasterRecords(product_fg_id, company_id);
	}


	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType)
			throws SQLException {
		return iSmProductFgService.FnShowAllReportRecords(pageable, reportType);

	}

	@GetMapping("/FnShowAllRecords/{company_id}/{updatAllRecordsKey}")
	public Map<String, Object> FnShowAllRecords(@PathVariable int company_id, @PathVariable String updatAllRecordsKey) throws SQLException {
		return iSmProductFgService.FnShowAllRecords(company_id, updatAllRecordsKey);
	}

	@GetMapping("/FnGetQRCode/{product_fg_id}")
	public byte[] FnGetCompanyLogo(@PathVariable String product_fg_id) throws IOException {
		HashMap<File, String> doc = new HashMap<File, String>();
		byte[] bytesFromFile = null;
		try {
			File directoryPath = new File(filePath + "DocStores" + File.separator + "Finish Goods" + File.separator + product_fg_id + File.separator);
			String[] contents = directoryPath.list();
			String extension = contents[0].substring(contents[0].lastIndexOf(".") + 0);
			String docLocalpath = filePath + "DocStores" + File.separator + "Finish Goods" + File.separator + product_fg_id + File.separator;

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

}
