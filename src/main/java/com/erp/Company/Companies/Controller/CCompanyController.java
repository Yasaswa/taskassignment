package com.erp.Company.Companies.Controller;

import com.erp.Company.Companies.Model.CCompanyModel;
import com.erp.Company.Companies.Model.CCompanyViewModel;
import com.erp.Company.Companies.Repository.ICompanyViewRepository;
import com.erp.Company.Companies.Service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCompanyController {

	@Autowired
	ICompanyService iCompanyService;

	@Autowired
	ICompanyViewRepository iCompanyViewRepository;

	@Value("${document.file.path}")
	private String filePath;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CCompanyModel cCompanyModel) throws SQLException {
		return iCompanyService.FnAddUpdateRecord(cCompanyModel);

	}

	@PostMapping("/FnStoreTempFile")
	public HashMap<String, String> FnStoreTempFile(@RequestParam("file") MultipartFile file) {
		HashMap<String, String> responce = new HashMap<String, String>();
		try {
			String docStorePath = filePath + "Company_Logo_Temp_File" + File.separator;
			if (!new File(docStorePath).exists()) {
				new File(docStorePath).mkdir();
			}
			File directoryPath = new File(docStorePath);
			String contents[] = directoryPath.list();
			for (String content : contents) {
				System.out.println("File is available");
				File deleteExistingDoc = new File(docStorePath + content);
				deleteExistingDoc.delete();
			}
			String DocOrignalName = file.getOriginalFilename();
			String storeDoc = docStorePath + DocOrignalName;

			File dest = new File(storeDoc);
			file.transferTo(dest);

			responce.put(DocOrignalName, storeDoc);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{company_id}")
	public Object FnDeleteRecord(@PathVariable int company_id) {
		return iCompanyService.FnDeleteRecord(company_id);

	}

	@GetMapping("/FnShowAllRecordsToExport")
	ArrayList<CCompanyViewModel> FnShowAllRecordsToExport() throws SQLException {
		ArrayList<CCompanyViewModel> cCompanyViewModel = null;
		cCompanyViewModel = iCompanyService.FnShowAllRecordsToExport();

		return cCompanyViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Page<CCompanyViewModel> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Page<CCompanyViewModel> cCompanyViewModel = null;
		cCompanyViewModel = iCompanyService.FnShowAllActiveRecords(pageable);

		return cCompanyViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{company_id}")
	public CCompanyViewModel FnShowParticularRecord(@PathVariable int company_id) throws SQLException {
		CCompanyViewModel cCompanyViewModel = null;
		cCompanyViewModel = iCompanyService.FnShowParticularRecord(company_id);

		return cCompanyViewModel;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id) throws SQLException {
		return iCompanyService.FnShowParticularRecordForUpdate(company_id);

	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iCompanyService.FnShowAllReportRecords(pageable);

	}

	@GetMapping("/FnGetCompanyLogo/{company_legal_name}")
	public byte[] FnGetCompanyLogo(@PathVariable String company_legal_name) throws FileNotFoundException, IOException {
		HashMap<File, String> doc = new HashMap<File, String>();
		byte[] bytesFromFile = null;
		try {
			File directoryPath = new File(filePath + "Company_logo" + File.separator + company_legal_name + File.separator);
			if (!new File(filePath + "Company_logo" + File.separator + company_legal_name + File.separator).exists()) {
				new File(filePath + "Company_logo" + File.separator + company_legal_name + File.separator).mkdir();
			}
			String contents[] = directoryPath.list();
			String extension = contents[0].substring(contents[0].lastIndexOf(".") + 0, contents[0].length());
			String docLocalpath = filePath + "Company_logo" + File.separator + company_legal_name + File.separator;
			if (!new File(docLocalpath).exists()) {
				new File(docLocalpath).mkdir();
			}

			File file = new File(docLocalpath + contents[0]);

			bytesFromFile = getBytes(file);
			System.out.println(bytesFromFile.length);

		} catch (NullPointerException e) {
			System.out.print("NullPointerException Caught");
		}
		return bytesFromFile;

	}

	@GetMapping("/FnGetTempCompanyLogo")
	public byte[] FnGetTempCompanyLogo() throws FileNotFoundException, IOException {
		byte[] bytesFromFile = null;
		try {
			File directoryPath = new File(filePath + "Company_Logo_Temp_File" + File.separator);
			String contents[] = directoryPath.list();
			String extension = contents[0].substring(contents[0].lastIndexOf(".") + 0, contents[0].length());
			String docLocalpath = filePath + "Company_Logo_Temp_File" + File.separator;
			String docPath = docLocalpath;

			File file = new File(docLocalpath + contents[0]);

			bytesFromFile = getBytes(file);
			System.out.println(bytesFromFile.length);

		} catch (NullPointerException e) {
			System.out.print("NullPointerException Caught");
		}
		return bytesFromFile;

	}

	public static byte[] getBytes(File f) throws FileNotFoundException, IOException {
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
