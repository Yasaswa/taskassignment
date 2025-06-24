package com.erp.Common.Documents.Controller;

import com.erp.Common.Documents.Model.CDocumentsModel;
import com.erp.Common.Documents.Service.IDocumentsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Documents")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CDocumentsController {

	@Autowired
	IDocumentsService iDocumentsService;

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

	// Helper method to get the file extension
	private static String getFileExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("data") JSONObject json, @RequestParam(name = "file", required = false) MultipartFile file) {
		return iDocumentsService.FnAddUpdateRecord(json, file);
	}

	@PostMapping("/FnUpdateDocumentRecord")
	public String FnUpdateDocumentRecord(@RequestBody CDocumentsModel cDocumentsModel) {
		JSONObject resp = new JSONObject();
		try {
			resp = iDocumentsService.FnUpdateDocumentRecord(cDocumentsModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@PostMapping("/FnDeleteRecord/{document_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int document_id, @PathVariable String deleted_by) {
		return iDocumentsService.FnDeleteRecord(document_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Page<CDocumentsModel> FnShowAllRecords(Pageable pageable) throws SQLException {

		return iDocumentsService.FnShowAllRecords(pageable);

	}

	@GetMapping("/FnShowAllActiveRecords")
	Page<CDocumentsModel> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		return iDocumentsService.FnShowAllActiveRecords(pageable);
	}

	@GetMapping("/FnShowParticularRecord")
	public Page<CDocumentsModel> FnShowParticularRecord(@RequestParam String group_id, @RequestParam String document_group,
	                                                    Pageable pageable) throws SQLException {
		return iDocumentsService.FnShowParticularRecord(group_id, document_group, pageable);
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{document_id}")
	public CDocumentsModel FnShowParticularRecordForUpdate(@PathVariable int document_id) throws SQLException {
		return iDocumentsService.FnShowParticularRecordForUpdate(document_id);
	}

	@PostMapping("/FnGetDocument")
	public byte[] FnGetDocument(@RequestParam("getFile") JSONObject jsonObject)
			throws IOException {
		HashMap<File, String> doc = new HashMap<File, String>();
		byte[] bytesFromFile = null;
		String document_path = jsonObject.getString("document_path");
		try {
			char seperator = File.separatorChar;
			File directoryPath = new File(filePath + seperator + document_path);
			bytesFromFile = getBytes(directoryPath);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("NullPointerException Caught");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytesFromFile;

	}

	@GetMapping("/FnFetchFile/{document_group}")
	public HashMap<File, String> FnFetchFile(@PathVariable String document_group, @RequestParam String group_id)
			throws IOException {
		HashMap<File, String> doc = new HashMap<File, String>();
		try {
			char seperator = File.separatorChar;
			File directoryPath = new File(filePath + "DocStores" + seperator + document_group + seperator + group_id + seperator);
			String[] contents = directoryPath.list();
			String extension = contents[0].substring(contents[0].lastIndexOf(".") + 0);
			String docLocalpath = filePath + "DocStores" + seperator + document_group + seperator + group_id + seperator;
			String docPath = docLocalpath;

			File file = new File(contents[0]);

			doc.put(file, extension);

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.print("NullPointerException Caught");
		}
		return doc;

	}


	@PostMapping("/FnAddUpdateTempRecord")
	public Map<String, Object> FnAddUpdateTempRecord(@RequestParam("data") JSONObject json,@RequestParam("updatedata") JSONObject updateData, @RequestParam(name = "file", required = false) MultipartFile file) {
		return iDocumentsService.FnAddUpdateTempRecord(json,updateData, file);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Map<String, Object>> deleteFile(@RequestBody Map<String, String> request) {
		String filePath = request.get("path");

		try {
			iDocumentsService.deleteFile(filePath);
			Map<String, Object> response = new HashMap<>();
			response.put("success", "1");
			response.put("message", "File deleted successfully.");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IOException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("success", "0");
			response.put("message", "Failed to delete the file: " + filePath);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	@PutMapping("/update")
//	public ResponseEntity<Map<String, Object>> updateDocument(@RequestBody CDocumentsModel updatedDocument) {
//		try {
//			iDocumentsService.updateDocument(updatedDocument);
//			Map<String, Object> response = new HashMap<>();
//			response.put("success", "1");
//			response.put("message", "Document updated successfully.");
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			Map<String, Object> response = new HashMap<>();
//			response.put("success", "0");
//			response.put("message", "Failed to update the document.");
//			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//@PutMapping("/update")
//public ResponseEntity<Map<String, Object>> updateDocument(@RequestBody Map<String, String> request) {
//	String oldPath = request.get("document_path");
//	String newFileName = request.get("newFileName");
////	String newContent = request.get("content");
//
//	try {
//		iDocumentsService.updateDocument(oldPath, newFileName);
//		Map<String, Object> response = new HashMap<>();
//		response.put("success", "1");
//		response.put("message", "Document updated successfully.");
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	} catch (Exception e) {
//		Map<String, Object> response = new HashMap<>();
//		response.put("success", "0");
//		response.put("message", "Failed to update the document.");
//		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}



}
