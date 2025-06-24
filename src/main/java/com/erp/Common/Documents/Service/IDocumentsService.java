package com.erp.Common.Documents.Service;

import com.erp.Common.Documents.Model.CDocumentsModel;
import com.erp.Company.Companies.Model.CCompanyModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IDocumentsService {

	Map<String, Object> FnAddUpdateRecord(JSONObject json, MultipartFile file);
//	Map<String, Object> FnAddUpdateRecord(CCompanyModel cCompanyModel);
	Page<CDocumentsModel> FnShowAllRecords(Pageable pageable);

	Page<CDocumentsModel> FnShowAllActiveRecords(Pageable pageable);

	Map<String, Object> FnDeleteRecord(int document_id, String deleted_by);

	Page<CDocumentsModel> FnShowParticularRecord(String group_id, String document_group, Pageable pageable);

	CDocumentsModel FnShowParticularRecordForUpdate(int document_id);

	JSONObject FnUpdateDocumentRecord(CDocumentsModel cDocumentsModel);

//	Map<String, Object> FnAddUpdateTempRecord(JSONObject json, MultipartFile file);

	Map<String, Object> FnAddUpdateTempRecord(JSONObject json, JSONObject updateData, MultipartFile file);

	void deleteFile(String filePath) throws IOException;

	void updateDocument(String oldPath, String newFileName, MultipartFile file) throws IOException;

//	void updateDocument(CDocumentsModel updatedDocument);

//	void updateDocument(String oldPath, String newFileName, String newContent) throws IOException;

//	void updateDocument(String oldPath, String newFileName) throws IOException;
}
