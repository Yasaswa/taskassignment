package com.erp.Common.Documents.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Documents.Model.CDocumentsModel;
import com.erp.Common.Documents.Repository.IDocumentsRepository;
import com.erp.Company.Companies.Repository.ICompanyRepository;
import com.erp.Company.Companies.Repository.ICompanyViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class CDocumentsServiceImpl implements IDocumentsService {

	@Autowired
	IDocumentsRepository iDocumentsRepository;

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICompanyRepository iCompanyRepository;

	@Autowired
	ICompanyViewRepository iCompanyViewRepository;

	@Value("${document.file.path}")
	private String filePath;


	public String FnGeneratePathForDocument(CDocumentsModel object) {
		String seperator = File.separator; // for file seperator
		String docStorePath = filePath + "DocStores" + seperator;
		if (!new File(docStorePath).exists()) {
			new File(docStorePath).mkdir();
		}

		String docGroupPath = docStorePath + object.getDocument_group() + seperator;
		if (!new File(docGroupPath).exists()) {
			new File(docGroupPath).mkdir();
		}

		String existingDocPath = docGroupPath + object.getGroup_id() + seperator;
		if (!new File(existingDocPath).exists()) {
			new File(existingDocPath).mkdir();
		}

		return existingDocPath;
	}

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject json, MultipartFile file) {
		Map<String, Object> resp = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String seperator = File.separator; // for file seperator
			String storeDoc = "";
			String storedocDbVal = "";
			String existingDocPath = "";
			String docName = "";

//          Convert json to model object
			CDocumentsModel object = objectMapper.readValue(json.toString(), CDocumentsModel.class);


			if (file != null) {

//          Generate document path
				existingDocPath = FnGeneratePathForDocument(object);

				String DocOrignalName = file.getOriginalFilename();
				String extesion = DocOrignalName.substring(DocOrignalName.lastIndexOf(".") + 0);
				docName = object.getDocument_name() + extesion;
				object.setFile_name(docName);

			}
//			Check is any record available in db with same document name
			CDocumentsModel existinDocumentObject = null;
			if (object.getDocument_id() == 0) {
				existinDocumentObject = iDocumentsRepository.checkIfExist(object.getDocument_name(), object.getCompany_id(), object.getDocument_group());
			}

			if (existinDocumentObject != null) {
				System.out.println("Documents  is present..!");
				resp.put("success", 0);
				resp.put("data", "");
				resp.put("error", "Document Name is already present!...");

			} else {
				if (file != null) {
					File directoryPath = new File(existingDocPath);
					String[] contents = directoryPath.list();
					if (contents.length != 0) {
						for (String content : contents) {
							String extension = content.substring(content.lastIndexOf(".") + 0);
							String[] splitContent = content.split(extension);
							if (object.getDocument_name().equals(splitContent[0])) {
								File deleteExistingDoc = new File(existingDocPath + object.getDocument_name() + extension);
								deleteExistingDoc.delete();
							}
						}
					}
					storeDoc = directoryPath + seperator + docName;
					System.out.println("RenameFileName " + storeDoc);

					String[] storedocDb = storeDoc.split(Pattern.quote("ERP_DEV" + File.separator));
					storedocDbVal = storedocDb[1];

//					Save path in db
					object.setDocument_path(storedocDbVal);
				}
				CDocumentsModel responseDocument = iDocumentsRepository.save(object);
				System.out.println("Documents saved succesfully!..");

//				File save into folder
				if (file != null) {
					File dest = new File(storeDoc);
					file.transferTo(dest);
				}

				resp.put("success", 1);
				resp.put("data", responseDocument);
				resp.put("error", "");
				resp.put("message", json.getInt("document_id") == 0 ? "Record added succesfully!..." : "Record updated succesfully!...");

			}


		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", 0);
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("success", 0);
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}
		return resp;
	}

	@Override
	public JSONObject FnUpdateDocumentRecord(CDocumentsModel cDocumentsModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CDocumentsModel cDocModel = null;
		try {
			Optional<CDocumentsModel> option = iDocumentsRepository.findById(cDocumentsModel.getDocument_id());
			if (option.isPresent()) {
				cDocModel = option.get();
				String document_registration_date = cDocumentsModel.getDocument_registration_date();
				String document_renewal_date = cDocumentsModel.getDocument_renewal_date();

				cDocumentsModel.setDocument_registration_date(document_registration_date);

				cDocumentsModel.setDocument_renewal_date(document_renewal_date);
				cDocumentsModel.setDocument_path(cDocModel.getDocument_path());
				cDocumentsModel = iDocumentsRepository.save(cDocumentsModel);
				String json = objectMapper.writeValueAsString(cDocumentsModel);

				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				return resp;
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
				return resp;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Page<CDocumentsModel> FnShowAllRecords(Pageable pageable) {
		return iDocumentsRepository.FnShowAllRecords(pageable);
	}

	@Override
	public Page<CDocumentsModel> FnShowAllActiveRecords(Pageable pageable) {
		return iDocumentsRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int document_id, String deleted_by) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Optional<CDocumentsModel> option = iDocumentsRepository.findById(document_id);
			CDocumentsModel cDocumentsModel = new CDocumentsModel();
			if (option.isPresent()) {
				cDocumentsModel = option.get();

//			Delete doc from folder
				String document_path = cDocumentsModel.getDocument_path();
				String existingDocPath = filePath + document_path;
				File directoryPath = new File(existingDocPath);
				directoryPath.delete();

//			Delete doc from db
				cDocumentsModel.setIs_delete(true);
				cDocumentsModel.setDeleted_on(new Date());
				cDocumentsModel.setDeleted_by(deleted_by);
				iDocumentsRepository.save(cDocumentsModel);
			}

			resp.put("success", 1);
			resp.put("data", cDocumentsModel);
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", 0);
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("success", 0);
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}
		return resp;
	}

	@Override
	public Page<CDocumentsModel> FnShowParticularRecord(String group_id, String document_group, Pageable pageable) {
		return iDocumentsRepository.FnShowParticularRecord(group_id, document_group, pageable);
	}

	@Override
	public CDocumentsModel FnShowParticularRecordForUpdate(int document_id) {
		return iDocumentsRepository.FnShowParticularRecordForUpdate(document_id);
	}


//@Override
//public Map<String, Object> FnAddUpdateTempRecord(JSONObject json,JSONObject updateData, MultipartFile file) {
//	Map<String, Object> resp = new HashMap<>();
//	ObjectMapper objectMapper = new ObjectMapper();
//
//	try {
//		String seperator = File.separator; // for file seperator
//		String storeDoc = "";
//		String storedocDbVal = "";
//		String existingDocPath = "";
//		String docName = "";
//
////          Convert json to model object
//		CDocumentsModel object = objectMapper.readValue(json.toString(), CDocumentsModel.class);
//
//
//		if (file != null) {
//
////          Generate document path
//			existingDocPath = FnGeneratePathForTempDocument(object);
//
//			String DocOrignalName = file.getOriginalFilename();
//			String extesion = DocOrignalName.substring(DocOrignalName.lastIndexOf(".") + 0);
//			docName = object.getDocument_name() + extesion;
//			object.setFile_name(docName);
//
//		}
////			Check is any record available in db with same document name
//		CDocumentsModel existinDocumentObject = null;
//		if (object.getDocument_id() == 0) {
//			String documentPath = updateData.getString("document_path");
//			String newFileName = updateData.getString("newFile");
//			if (documentPath != null && !documentPath.trim().isEmpty()) {
//				updateDocument(documentPath, newFileName, file);
//			}
//
//			existinDocumentObject = iDocumentsRepository.checkIfExist(object.getDocument_name(), object.getCompany_id(), object.getDocument_group());
//		}
//
//		if (existinDocumentObject != null) {
//			System.out.println("Documents  is present..!");
//			resp.put("success", 0);
//			resp.put("data", "");
//			resp.put("error", "Document Name is already present!...");
//
//		} else {
//			if (file != null) {
//				File directoryPath = new File(existingDocPath);
//				String[] contents = directoryPath.list();
//				if (contents.length != 0) {
//					for (String content : contents) {
//						String extension = content.substring(content.lastIndexOf(".") + 0);
//						String[] splitContent = content.split(extension);
//						if (object.getDocument_name().equals(splitContent[0])) {
//							File deleteExistingDoc = new File(existingDocPath + object.getDocument_name() + extension);
//							deleteExistingDoc.delete();
//						}
//					}
//				}
//				storeDoc = directoryPath + seperator + docName;
//				System.out.println("RenameFileName " + storeDoc);
//
//				String[] storedocDb = storeDoc.split(Pattern.quote("ERP_DEV" + File.separator));
//				storedocDbVal = storedocDb[1];
//
////					Save path in db
//				object.setDocument_path(storedocDbVal);
//			}
////			CDocumentsModel responseDocument = iDocumentsRepository.save(object);
//			System.out.println("Documents saved succesfully!..");
//
////				File save into folder
//			if (file != null) {
//				File dest = new File(storeDoc);
//				file.transferTo(dest);
//			}
//			CDocumentsModel[] objectArray = { object };
//
//			resp.put("success", 1);
//			resp.put("data", objectArray);
////			resp.put("data", object);
//			resp.put("error", "");
//			resp.put("message", json.getInt("document_id") == 0 ? "Record added succesfully!..." : "Record updated succesfully!...");
//
//		}
//
//
//	} catch (DataAccessException e) {
//		if (e.getRootCause() instanceof SQLException) {
//			SQLException sqlEx = (SQLException) e.getRootCause();
//			System.out.println(sqlEx.getMessage());
//			resp.put("success", 0);
//			resp.put("data", "");
//			resp.put("error", sqlEx.getMessage());
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//		resp.put("success", 0);
//		resp.put("data", "");
//		resp.put("error", e.getMessage());
//	}
//	return resp;
//}
public Map<String, Object> FnAddUpdateTempRecord(JSONObject json, JSONObject updateData, MultipartFile file) {
	Map<String, Object> resp = new HashMap<>();
	ObjectMapper objectMapper = new ObjectMapper();

	try {
		String separator = File.separator; // for file separator
		String storeDoc = "";
		String storedocDbVal = "";
		String existingDocPath = "";
		String docName = "";

		// Convert json to model object
		CDocumentsModel object = objectMapper.readValue(json.toString(), CDocumentsModel.class);

		if (file != null) {
			// Generate document path
			existingDocPath = FnGeneratePathForTempDocument(object);
			String originalDocName = file.getOriginalFilename();
			String extension = originalDocName.substring(originalDocName.lastIndexOf("."));
			docName = object.getDocument_name() + extension;
			object.setFile_name(docName);
		}

		// Check if any record is available in the DB with the same document name
		CDocumentsModel existingDocumentObject = null;
		if (object.getDocument_id() == 0) {
			String documentPath = updateData.getString("document_path");
			String newFileName = updateData.getString("newFile");
			if (documentPath != null && !documentPath.trim().isEmpty()) {
				updateDocument(documentPath, newFileName, file); // Call to the updated method
			}

			existingDocumentObject = iDocumentsRepository.checkIfExist(object.getDocument_name(), object.getCompany_id(), object.getDocument_group());
		}

		if (existingDocumentObject != null) {
			System.out.println("Document is present..!");
			resp.put("success", 0);
			resp.put("data", "");
			resp.put("error", "Document Name is already present!...");
		} else {
			if (file != null) {
				File directoryPath = new File(existingDocPath);
				String[] contents = directoryPath.list();
				if (contents != null && contents.length != 0) {
					for (String content : contents) {
						String extension = content.substring(content.lastIndexOf("."));
						String[] splitContent = content.split(Pattern.quote(extension));
						if (object.getDocument_name().equals(splitContent[0])) {
							File deleteExistingDoc = new File(existingDocPath + separator + object.getDocument_name() + extension);
							if (!deleteExistingDoc.delete()) {
								throw new IOException("Failed to delete the existing document: " + deleteExistingDoc.getPath());
							}
						}
					}
				}
				storeDoc = directoryPath + separator + docName;
				System.out.println("Document path: " + storeDoc);

				String[] storedocDb = storeDoc.split(Pattern.quote("ERP_DEV" + File.separator));
				storedocDbVal = storedocDb[1];

				// Save path in DB
				object.setDocument_path(storedocDbVal);
			}

			System.out.println("Documents saved successfully!..");

			// Save file into folder
			if (file != null) {
				File dest = new File(storeDoc);
				file.transferTo(dest);
			}
			CDocumentsModel[] objectArray = { object };

			resp.put("success", 1);
			resp.put("data", objectArray);
			resp.put("error", "");
			resp.put("message", json.getInt("document_id") == 0 ? "Record added successfully!..." : "Record updated successfully!...");
		}

	} catch (DataAccessException e) {
		if (e.getRootCause() instanceof SQLException) {
			SQLException sqlEx = (SQLException) e.getRootCause();
			System.out.println(sqlEx.getMessage());
			resp.put("success", 0);
			resp.put("data", "");
			resp.put("error", sqlEx.getMessage());
		}
	} catch (Exception e) {
		e.printStackTrace();
		resp.put("success", 0);
		resp.put("data", "");
		resp.put("error", e.getMessage());
	}
	return resp;
}

	public String FnGeneratePathForTempDocument(CDocumentsModel object) {
		String seperator = File.separator; // for file seperator
		String docStorePath = filePath + "TempStores" + seperator;
		if (!new File(docStorePath).exists()) {
			new File(docStorePath).mkdir();
		}

		String docGroupPath = docStorePath + object.getDocument_group() + seperator;
		if (!new File(docGroupPath).exists()) {
			new File(docGroupPath).mkdir();
		}

		String existingDocPath = docGroupPath + object.getGroup_id() + seperator;
		if (!new File(existingDocPath).exists()) {
			new File(existingDocPath).mkdir();
		}

		return existingDocPath;
	}

	public void deleteFile(String document_path) throws IOException {
		String existingDocPath = filePath + document_path;
		File file = new File(existingDocPath);
		if (file.exists()) {
			if (!file.delete()) {
				throw new IOException("Failed to delete the file: " + filePath);
			}
		} else {
			throw new NoSuchFileException("No such file: " + filePath);
		}
	}




//	@Override
//	public void updateDocument(String oldPath, String newFileName) throws IOException {
//		String existingDocPath = filePath + oldPath;
//		File oldFile = new File(existingDocPath);
//
//		// Ensure the old file exists
//		if (!oldFile.exists()) {
//			throw new NoSuchFileException("No such file: " + oldPath);
//		}
//
//		// Determine the new file path
//		String directoryPath = oldFile.getParent();
//		String newPath = directoryPath + File.separator + newFileName;
//		File newFile = new File(newPath);
//
//		// Rename the file
//		boolean renamed = oldFile.renameTo(newFile);
//		if (!renamed) {
//			throw new IOException("Failed to rename the file from " + oldPath + " to " + newPath);
//		}
//
//
//	}

//	@Override
//	public void updateDocument(String oldPath, String newFileName, MultipartFile file) throws IOException {
//		// Determine the path where the file should be saved
//		String existingDocOldPath = filePath + oldPath;
////		File oldFile = new File(existingDocPath);
//		String directoryPath = filePath + (existingDocOldPath != null ? new File(existingDocOldPath).getParent() : "");
//
//		if (existingDocOldPath != null) {
//			// Handle the case where the old file exists
//			String existingDocPath =  File.separator + existingDocOldPath;
//			File oldFile = new File(existingDocPath);
//
//			// Ensure the old file exists
//			if (oldFile.exists()) {
//				// Determine the new file path
//				String newPath = directoryPath + File.separator + newFileName;
//				File newFile = new File(newPath);
//
//				// Rename the file
//				boolean renamed = oldFile.renameTo(newFile);
//				if (!renamed) {
//					throw new IOException("Failed to rename the file from " + existingDocOldPath + " to " + newPath);
//				}
//			} else {
//				throw new NoSuchFileException("No such file: " + existingDocOldPath);
//			}
//		}
//
//		// Handle the case where the old file does not exist or is replaced
//		// Save the new file (if provided)
//		if (file != null && !file.isEmpty()) {
//			File newFile = new File(directoryPath + File.separator + newFileName);
//			file.transferTo(newFile);
//		}
//	}
@Override
public void updateDocument(String oldPath, String newFileName, MultipartFile file) throws IOException {
	String directoryPath = filePath + (oldPath != null ? new File(oldPath).getParent() : "");
	String oldFilePath =   File.separator + oldPath;
	File oldFile = new File(oldFilePath);

	// Check if the old file exists and delete it
	if (oldFile.exists()) {
		if (!oldFile.delete()) {
			throw new IOException("Failed to delete the existing file: " + oldFilePath);
		}
	}

	// Save the new file (if provided)
//	if (file != null && !file.isEmpty()) {
//		File newFile = new File(directoryPath + File.separator + newFileName);
//		file.transferTo(newFile);
//	}
}

}
