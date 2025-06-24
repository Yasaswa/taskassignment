package com.erp.Company.Companies.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Company.Companies.Model.CCompanyModel;
import com.erp.Company.Companies.Model.CCompanyViewModel;
import com.erp.Company.Companies.Repository.ICompanyRepository;
import com.erp.Company.Companies.Repository.ICompanyViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class CCompanyServiceImpl implements ICompanyService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICompanyRepository iCompanyRepository;

	@Autowired
	ICompanyViewRepository iCompanyViewRepository;

	@Autowired
	private JdbcTemplate executeQuery;

	@Value("${document.file.path}")
	private String filePath;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CCompanyModel cCompanyModel) {
		Map<String, Object> resp = new HashMap<>();
		int company_id = cCompanyModel.getCompany_id();
		String storeDoc = "";
		String storedocDbVal = "";

		try {

			String seperator = File.separator;
			cCompanyModel.setRegistration_date(cCompanyModel.getRegistration_date());
			cCompanyModel.setEstablish_date(cCompanyModel.getEstablish_date());

			Optional<CCompanyModel> option = iCompanyRepository.findById(cCompanyModel.getCompany_id());

			if (option.isPresent()) {
				File directoryPath = new File(filePath + "Company_Logo_Temp_File" + seperator);
				if (!new File(filePath + "Company_Logo_Temp_File").exists()) {
					new File(directoryPath.toString()).mkdir();
				}
				String[] contents = directoryPath.list();

				if (contents.length != 0) {
					String docStorePath = filePath + "Company_logo" + seperator;
					if (!new File(docStorePath).exists()) {
						new File(docStorePath).mkdir();
					}
					String existingDocPath = docStorePath + cCompanyModel.getCompany_legal_name() + seperator;
					if (!new File(existingDocPath).exists()) {
						new File(existingDocPath).mkdir();
					}
					File listPath = new File(existingDocPath);

					String[] files = listPath.list();
					for (String content : files) {
						System.out.println("File is available");
						File deleteExistingDoc = new File(existingDocPath + content);
						deleteExistingDoc.delete();
					}

					String localDocPath = null;
					if (!new File(existingDocPath).exists()) {
						new File(existingDocPath).mkdir();
						localDocPath = docStorePath + cCompanyModel.getCompany_legal_name() + seperator;
						new File(localDocPath).mkdir();
					}

					storeDoc = localDocPath + contents[0];
					if (localDocPath == null) {
						storeDoc = existingDocPath + contents[0];
					}

//					String[] storedocDb = storeDoc.split("ERP_DEV" + seperator);
//					storedocDbVal = storedocDb[1];

					Path path = Paths.get(storeDoc);
					String[] storedocDb = path.toString().split(Pattern.quote(File.separator + "ERP_DEV" + File.separator));
					System.out.println(Arrays.toString(storedocDb));
					storedocDbVal = storedocDb[1];

					cCompanyModel.setCompany_logo_document_path(storedocDbVal);
					cCompanyModel.setLogo_file_name(contents[0]);

//					Company save
					cCompanyModel = iCompanyRepository.save(cCompanyModel);

//					Company logo save in folder
					File file = new File(contents[0]);
					File dest = new File(storeDoc);
					File file1 = new File(directoryPath + seperator + contents[0]);
					file1.renameTo(dest);
					resp.put("success", "1");
					resp.put("data", cCompanyModel);
					resp.put("error", "");
					resp.put("message", "Record updated succesfully!...");

				} else {
					String docStorePath = filePath + "Company_logo" + seperator;

					String existingDocPath = docStorePath + cCompanyModel.getCompany_legal_name() + seperator;
					File path = new File(existingDocPath);
					String[] content = path.list();

					storeDoc = existingDocPath + content[0];

					String[] storedocDb = storeDoc.split("ERP_DEV" + seperator);
					storedocDbVal = storedocDb[1];
					cCompanyModel.setCompany_logo_document_path(storedocDbVal);
					cCompanyModel.setLogo_file_name(content[0]);

//					Company save
					cCompanyModel = iCompanyRepository.save(cCompanyModel);

					resp.put("success", "1");
					resp.put("data", cCompanyModel);
					resp.put("error", "");
					resp.put("message", "Record updated succesfully!...");
				}
			} else {

				CCompanyModel companyModel = iCompanyRepository
						.checkIfExistCompanyName(cCompanyModel.getCompany_legal_name());
				if (companyModel != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Company legal name is already exist!...");
				}

				// Check similar Company Name,Company short name is exist or not
				CCompanyModel resultsCCompanyName = null;
				if (cCompanyModel.getCompany_short_name() == null || cCompanyModel.getCompany_short_name().isEmpty()) {

					resultsCCompanyName = iCompanyRepository.getCheck(cCompanyModel.getCompany_legal_name(),
							null, cCompanyModel.getCompany_id());
				} else {

					resultsCCompanyName = iCompanyRepository.getCheck(cCompanyModel.getCompany_legal_name(),
							cCompanyModel.getCompany_short_name(), cCompanyModel.getCompany_id());

				}
				if (resultsCCompanyName != null) {

					resp.put("success", 0);
					resp.put("data", "");
					resp.put("error", " Company Name or Short Name is already exist!");
					return resp;

				} else {
					File directoryPath = new File(filePath + "Company_Logo_Temp_File" + seperator);
					String[] contents = directoryPath.list();

					String docStorePath = filePath + "Company_logo" + seperator;
					if (!new File(docStorePath).exists()) {
						new File(docStorePath).mkdir();
					}
					String existingDocPath = docStorePath + cCompanyModel.getCompany_legal_name() + seperator;
					String localDocPath = null;
					if (!new File(existingDocPath).exists()) {
						new File(existingDocPath).mkdir();
						localDocPath = docStorePath + cCompanyModel.getCompany_legal_name() + seperator;
						new File(localDocPath).mkdir();
					}

					storeDoc = localDocPath + contents[0];
					if (localDocPath == null) {
						storeDoc = existingDocPath + contents[0];
					}
					Path path = Paths.get(storeDoc);
					String[] storedocDb = path.toString().split(Pattern.quote(File.separator + "ERP_DEV" + File.separator));
					System.out.println(Arrays.toString(storedocDb));

					storedocDbVal = storedocDb[1];
					cCompanyModel.setCompany_logo_document_path(storedocDbVal);
					cCompanyModel.setLogo_file_name(contents[0]);

//					Company save
					cCompanyModel = iCompanyRepository.save(cCompanyModel);

//					Company logo save in folder
					File file = new File(contents[0]);
					File dest = new File(storeDoc);
					File file1 = new File(directoryPath + seperator + contents[0]);
					file1.renameTo(dest);
					System.out.println("Company saved Succesfully");
					resp.put("success", "1");
					resp.put("data", cCompanyModel);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");

				}
			}

			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/company/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
				return resp;

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/company/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;
		}
		return resp;

	}

	@Override
	public Object FnDeleteRecord(int company_id) {
		return iCompanyRepository.FnDeleteRecord(company_id);
	}

	@Override
	public ArrayList<CCompanyViewModel> FnShowAllRecordsToExport() {
		return iCompanyViewRepository.FnShowAllRecordsToExport();
	}

	@Override
	public Page<CCompanyViewModel> FnShowAllActiveRecords(Pageable pageable) {
		return iCompanyViewRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) {
		return iCompanyViewRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int company_id) {
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			CCompanyModel cCompanyModel = iCompanyRepository.FnShowParticularRecordForUpdate(company_id);

			response.put("success", 1);
			response.put("data", cCompanyModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public CCompanyViewModel FnShowParticularRecord(int company_id) {
		return iCompanyViewRepository.FnShowParticularRecord(company_id);
	}

}
