package com.erp.RawMaterial.Product_Rm.Controller;

import com.erp.RawMaterial.Product_Rm.Model.CProductRmModel;
import com.erp.RawMaterial.Product_Rm.Repository.IProductRmRepository;
import com.erp.RawMaterial.Product_Rm.Service.IProductRmService;
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
@RequestMapping(value = "/api/productRm", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductRmController {

	@Autowired
	IProductRmService iProductRmService;

	@Autowired
	IProductRmRepository iProductRmRepository;

	@Value("${document.file.path}")
	private String filePath;

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

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("TransData") JSONObject jsonObject, @RequestParam(name = "qrCodeFile", required = false) MultipartFile qrCodeFile) {
		return iProductRmService.FnAddUpdateRecord(jsonObject, qrCodeFile);
	}

	@PostMapping("/FnDeleteRecord/{product_rm_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable String product_rm_id, @PathVariable String deleted_by) {
		return iProductRmService.FnDeleteRecord(product_rm_id, deleted_by);

	}

	@PostMapping("/FnStoreQRFile/{product_rm_id}/{product_rm_name}")
	public HashMap<String, String> FnStoreQRFile(@RequestParam("file") MultipartFile file,
	                                             @PathVariable int product_rm_id, @PathVariable String product_rm_name) {
		HashMap<String, String> responce = new HashMap<String, String>();
		try {
			String docStorePath1 = filePath + "DocStores" + '/';
			if (!new File(docStorePath1).exists()) {
				new File(docStorePath1).mkdir();
			}
			String docStorePath = docStorePath1 + "RawMaterial" + '/';
			if (!new File(docStorePath).exists()) {
				new File(docStorePath).mkdir();
			}
			String doctStorePathWithId = docStorePath + product_rm_id + '/';
			if (!new File(doctStorePathWithId).exists()) {
				new File(doctStorePathWithId).mkdir();
			}

			File directoryPath = new File(doctStorePathWithId);
			String[] contents = directoryPath.list();
			if (contents.length != 0) {
				for (String content : contents) {
					System.out.println("File is available");
					File deleteExistingDoc = new File(doctStorePathWithId + content);
					deleteExistingDoc.delete();
				}
			}
			String DocOrignalName = file.getOriginalFilename();
			String extesion = DocOrignalName.substring(DocOrignalName.lastIndexOf(".") + 0);
			String docName = product_rm_name + "_qrcode" + extesion;
			String storeDoc = doctStorePathWithId + docName;

			String[] storedocDb = storeDoc.split("ERP_DEV/");
			String storedocDbVal = storedocDb[1];

			java.util.Optional<CProductRmModel> option = iProductRmRepository.findById(product_rm_id);
			CProductRmModel model = null;
			if (option.isPresent()) {
				model = option.get();
				model.setProduct_rm_qr_code(storedocDbVal);
				iProductRmRepository.save(model);
				responce.put("1", storedocDbVal);
			}

			File dest = new File(storeDoc);
			file.transferTo(dest);

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responce;

	}

	@GetMapping("/FnGetQRCode/{product_rm_id}")
	public byte[] FnGetCompanyLogo(@PathVariable int product_rm_id) throws IOException {
		HashMap<File, String> doc = new HashMap<File, String>();
		byte[] bytesFromFile = null;
		try {
			File directoryPath = new File(filePath + "DocStores" + File.separator + "Raw Material" + File.separator + product_rm_id + File.separator);
			String[] contents = directoryPath.list();
			String extension = contents[0].substring(contents[0].lastIndexOf(".") + 0);
			String docLocalpath = filePath + "DocStores" + File.separator + "Raw Material" + File.separator + product_rm_id + File.separator;

			File file = new File(docLocalpath + contents[0]);

			bytesFromFile = getBytes(file);
			System.out.println(bytesFromFile.length);

		} catch (NullPointerException e) {
			System.out.print("NullPointerException Caught");
		}
		return bytesFromFile;

	}


	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductRmViewModel = null;
		cProductRmViewModel = iProductRmService.FnShowAllActiveRecords(pageable);
		return cProductRmViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{product_rm_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_rm_id)
			throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iProductRmService.FnShowParticularRecord(company_id, product_rm_id);

		return resp;

	}

	@GetMapping("/FnShowAllProductRmListRecords")
	Object FnShowAllProductRmListViewRecords(Pageable pageable) throws SQLException {
		Object cProductRmListModel = iProductRmService.FnShowAllProductRmListViewRecords(pageable);

		return cProductRmListModel;

	}

	@GetMapping("/FnShowAllProductRmSummaryViewRecords")
	Object FnShowAllProductRmmaterialSummaryViewRecords(Pageable pageable) throws SQLException {
		Object cProductRmSummaryViewModel = iProductRmService.FnShowAllProductRmmaterialSummaryViewRecords(pageable);

		return cProductRmSummaryViewModel;

	}

	@GetMapping("/FnShowAllReportSummaryRecords")
	public Map<String, Object> FnShowAllReportSummaryRecords(Pageable pageable,
	                                                         @RequestParam("reportType") String reportType) throws SQLException {
		Map<String, Object> cProductRmSummaryRptModel = iProductRmService.FnShowAllReportSummaryRecords(pageable,
				reportType);
		return cProductRmSummaryRptModel;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{company_branch_id}/{product_rm_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                              @PathVariable int product_rm_id) throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iProductRmService.FnShowParticularRecordForUpdate(company_id, company_branch_id, product_rm_id);

		return resp.toString();

	}

	@GetMapping("/FnShowAllProductRmSummaryDetailsAndMasterRecords/{product_rm_id}/{company_id}")
	public Map<String, Object> FnShowAllProductRmSummaryDetailsAndMasterRecords(@PathVariable String product_rm_id,
	                                                                            @PathVariable int company_id) throws SQLException {
		return iProductRmService.FnShowAllProductRmSummaryDetailsAndMasterRecords(product_rm_id, company_id);
	}

	@GetMapping("/FnShowAllRecords/{company_id}")
	public Map<String, Object> FnShowAllRecords(@PathVariable int company_id, @RequestParam String accordianSelectKey) throws SQLException {
		return iProductRmService.FnShowAllRecords(company_id, accordianSelectKey);
	}


}
