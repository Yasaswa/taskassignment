package com.erp.SmProductPr.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.GenerateAutoNo.Controller.GAutoNoController;
import com.erp.SmProductPr.Model.*;
import com.erp.SmProductPr.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

@Service
public class CSmProductPrServiceImpl implements ISmProductPrService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductPrRepository iSmProductPrRepository;

	@Autowired
	ISmProductPrCommercialRepository iSmProductPrCommercialRepository;

	@Autowired
	ISmProductPrCustomerRepository iSmProductPrCustomerRepository;

	@Autowired
	ISmProductPrProcessRepository iSmProductPrProcessRepository;

	@Autowired
	ISmProductPrQaMappingRepository iSmProductPrQaMappingRepository;

	@Autowired
	ISmProductPrStockDetailsRepository iSmProductPrStockDetailsRepository;

	@Autowired
	ISmProductPrStockSummaryRepository iSmProductPrStockSummaryRepository;

	@Autowired
	ISmProductPrSupplierRepository iSmProductPrSupplierRepository;

	@Autowired
	ISmProductPrTechnicalRepository iSmProductPrTechnicalRepository;

	@Autowired
	GAutoNoController autoNoController;

	@Value("${document.file.path}")
	private String filePath;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile qrCodeFile) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String product_pr_id = commonIdsObj.getString("product_pr_id");
		String key = jsonObject.getString("saveKey");

		JSONObject productPrjson = jsonObject.getJSONObject("ProductPrData");
		JSONObject Technicaljson = jsonObject.getJSONObject("TechnicalData");
		JSONObject Commercialjson = jsonObject.getJSONObject("CommercialData");

		JSONArray CustomerArray = (JSONArray) jsonObject.get("CustomerData");
		JSONArray ProcessArray = (JSONArray) jsonObject.get("ProcessData");
		JSONArray QaMappingArray = (JSONArray) jsonObject.get("QaMappingData");
		JSONArray SupplierArray = (JSONArray) jsonObject.get("SupplierData");

		AtomicReference<String> atomicProduct_pr_id = new AtomicReference<>(product_pr_id);

		try {
			// Product Pr Master....
			CSmProductPrModel productPrModel = objectMapper.readValue(productPrjson.toString(),
					CSmProductPrModel.class);
			CSmProductPrModel responseproductPrModel = new CSmProductPrModel();
			if (key.equals("generalEntry") || key.equals("allPRData")) {
				if (productPrModel.getProduct_pr_id().equalsIgnoreCase("0")) {

					CSmProductPrModel model = iSmProductPrRepository.CheckIfExist(productPrModel.getProduct_pr_name(),
							company_id);
					if (model != null) {
						responce.put("success", 0);
						responce.put("data", "");
						responce.put("error", "Project Product name is already exist!");
						return responce;
					}
				}
				responseproductPrModel = iSmProductPrRepository.save(productPrModel);
				atomicProduct_pr_id.set(responseproductPrModel.getProduct_pr_id());

				if (qrCodeFile != null) {
					Map<String, Object> qrCodeStoreResponse = FnStoreQRFile(qrCodeFile, atomicProduct_pr_id.get(),
							responseproductPrModel.getProduct_pr_name());
					// Accessing values from qrCodeStoreResponse
					Object successValue = qrCodeStoreResponse.get("success");
					Object dataValue = qrCodeStoreResponse.get("data");
					int success = (int) successValue;
					if (success == 1) {
						iSmProductPrRepository.updateQrCodePath(atomicProduct_pr_id.get(), (String) dataValue);
						responseproductPrModel.setProduct_pr_qr_code((String) dataValue);
					}
				}
			}

			// Product Pr Technical
			if (key.equals("technicalEntry") || key.equals("allPRData")) {
				CSmProductPrTechnicalModel cSmProductPrTechnicalModel = objectMapper.readValue(Technicaljson.toString(),
						CSmProductPrTechnicalModel.class);

				if (cSmProductPrTechnicalModel.getProduct_pr_technical_id() == 0) {

					CSmProductPrTechnicalModel model = iSmProductPrTechnicalRepository
							.checkIfExist(cSmProductPrTechnicalModel.getProduct_pr_technical_name(), company_id);

					if (model != null) {
						responce.put("success", 0);
						responce.put("data", "");
						responce.put("error", "Technical name  is already exist!");
						return responce;
					}
				}
				iSmProductPrTechnicalRepository.updateActiveStatusProductTechnical(
						cSmProductPrTechnicalModel.getProduct_pr_technical_id(), company_id);
				cSmProductPrTechnicalModel.setProduct_pr_id(responseproductPrModel.getProduct_pr_id());

				iSmProductPrTechnicalRepository.save(cSmProductPrTechnicalModel);

			}

			// Product Pr Commercial
			if (key.equals("commercialEntry") || key.equals("allPRData")) {
				CSmProductPrCommercialModel cSmProductPrCustomerModel = objectMapper
						.readValue(Commercialjson.toString(), CSmProductPrCommercialModel.class);

				iSmProductPrCommercialRepository.updateActiveStatusProductCommercial(product_pr_id);
				cSmProductPrCustomerModel.setProduct_pr_id(responseproductPrModel.getProduct_pr_id());

				iSmProductPrCommercialRepository.save(cSmProductPrCustomerModel);
			}

			// Product Pr Customer
			if (key.equals("customerMapping") || key.equals("allPRData")) {
				iSmProductPrCustomerRepository.updateProductPrCustomerActiveStatus(product_pr_id, company_id);

				if (!CustomerArray.isEmpty()) {
					List<CSmProductPrCustomerModel> cSmProductPrCustomerModel = objectMapper
							.readValue(CustomerArray.toString(), new TypeReference<List<CSmProductPrCustomerModel>>() {
							});

					cSmProductPrCustomerModel.forEach(items -> {
						items.setProduct_pr_id(atomicProduct_pr_id.get());
					});

					iSmProductPrCustomerRepository.saveAll(cSmProductPrCustomerModel);
				}
			}

			// Product Pr Process
			if (key.equals("processMapping") || key.equals("allPRData")) {
				iSmProductPrProcessRepository.updateProductPrProcessActiveStatus(product_pr_id, company_id);

				if (!ProcessArray.isEmpty()) {
					List<CSmProductPrProcessModel> cSmProductPrProcessModel = objectMapper
							.readValue(ProcessArray.toString(), new TypeReference<List<CSmProductPrProcessModel>>() {
							});

					cSmProductPrProcessModel.forEach(items -> {
						items.setProduct_pr_id(atomicProduct_pr_id.get());
					});

					iSmProductPrProcessRepository.saveAll(cSmProductPrProcessModel);
				}
			}

			// Product Pr QaMapping
			if (key.equals("qaMapping") || key.equals("allPRData")) {

				iSmProductPrQaMappingRepository.updateProductPrQaMappingActiveStatus(product_pr_id, company_id);

				if (!QaMappingArray.isEmpty()) {
					List<CSmProductPrQaMappingModel> cSmProductPrQaMappingModel = objectMapper.readValue(
							QaMappingArray.toString(), new TypeReference<List<CSmProductPrQaMappingModel>>() {
							});

					cSmProductPrQaMappingModel.forEach(items -> {
						items.setProduct_pr_id(atomicProduct_pr_id.get());
					});

					iSmProductPrQaMappingRepository.saveAll(cSmProductPrQaMappingModel);

				}
			}

			// Product Pr Supplier
			if (key.equals("supplierMapping") || key.equals("allPRData")) {
				iSmProductPrSupplierRepository.updateProductPrSupplierActiveStatus(product_pr_id, company_id);

				if (!SupplierArray.isEmpty()) {
					List<CSmProductPrSupplierModel> cSmProductPrSupplierModel = objectMapper
							.readValue(SupplierArray.toString(), new TypeReference<List<CSmProductPrSupplierModel>>() {
							});

					cSmProductPrSupplierModel.forEach(items -> {
						items.setProduct_pr_id(atomicProduct_pr_id.get());
					});

					iSmProductPrSupplierRepository.saveAll(cSmProductPrSupplierModel);
				}
			}
			responce.put("success", 1);
			responce.put("data", responseproductPrModel);
			responce.put("error", "");
			responce.put("message", product_pr_id.equalsIgnoreCase("0") ? "Record added Successfully..! "
					: "Record updated Successfully..!");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductPr/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductPr/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String product_pr_id, String deleted_by) {
		Map<String, Object> responce = new HashMap();
		try {

			iSmProductPrRepository.deleteProductPrRecords(product_pr_id, deleted_by);
			iSmProductPrTechnicalRepository.deleteTechnicalRecords(product_pr_id, deleted_by);
			iSmProductPrCommercialRepository.deleteCommercialRecords(product_pr_id, deleted_by);
			iSmProductPrCustomerRepository.deleteCustomerRecords(product_pr_id, deleted_by);
			iSmProductPrProcessRepository.deleteProcessRecords(product_pr_id, deleted_by);
			iSmProductPrQaMappingRepository.deleteQaMappingRecords(product_pr_id, deleted_by);
			iSmProductPrSupplierRepository.deleteSupplierRecords(product_pr_id, deleted_by);

			Optional<CSmProductPrModel> option = iSmProductPrRepository.findById(Integer.valueOf(product_pr_id));
			CSmProductPrModel cSmProductPrModel = new CSmProductPrModel();
			if (option.isPresent()) {
				cSmProductPrModel = option.get();
				if (!cSmProductPrModel.getProduct_pr_qr_code().isEmpty()) {
					String document_path = cSmProductPrModel.getProduct_pr_qr_code();
					String existingDocPath = filePath + document_path;
					File directoryPath = new File(existingDocPath);
					directoryPath.delete();
				}
			}

			responce.put("success", 1);
			responce.put("data", "");
			responce.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", "");
		}
		return responce;
	}

	public Map<String, Object> FnStoreQRFile(MultipartFile file, String product_pr_id, String product_pr_name) {
		Map<String, Object> responce = new HashMap<>();
		try {
			String docStorePath1 = filePath + "DocStores" + File.separator;
			if (!new File(docStorePath1).exists()) {
				new File(docStorePath1).mkdir();
			}
			String docStorePath = docStorePath1 + "Project Product" + File.separator;
			if (!new File(docStorePath).exists()) {
				new File(docStorePath).mkdir();
			}
			String doctStorePathWithId = docStorePath + product_pr_id + File.separator;
			if (!new File(doctStorePathWithId).exists()) {
				new File(doctStorePathWithId).mkdir();
			}

			File directoryPath = new File(doctStorePathWithId);
			String[] contents = directoryPath.list();
			for (String content : contents) {
				System.out.println("File is available");
				File deleteExistingDoc = new File(doctStorePathWithId + content);
				deleteExistingDoc.delete();
			}
			String DocOrignalName = file.getOriginalFilename();
			String extesion = DocOrignalName.substring(DocOrignalName.lastIndexOf("."));
			String docName = product_pr_name + "_qrcode" + extesion;
			String storeDoc = doctStorePathWithId + docName;

			// String[] storedocDb = storeDoc.split("ERP_DEV" + File.separator);
			// Convert to Path object
			Path path = Paths.get(storeDoc);

			// Use normalize() to standardize separators and remove redundant elements
			Path standardizedPath = path.normalize();

			// Convert back to a string
			String resultPath = standardizedPath.toString();
			String[] storedocDb = resultPath.split(Pattern.quote("ERP_DEV" + File.separator));
			String storedocDbVal = storedocDb[1];

			File dest = new File(storeDoc);
			file.transferTo(dest);

			responce.put("success", 1);
			responce.put("data", storedocDbVal);
			responce.put("error", "");

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnAddUpdateMaterial(CSmProductPrModel cSmProductPrModel, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			CSmProductPrModel existingProductPr = iSmProductPrRepository
					.CheckIfExist(cSmProductPrModel.getProduct_pr_name(), company_id);
			if (existingProductPr == null) {
				// means that material is not present in that table. then generate material id and save it.
				JSONObject JsonTOGenerateMaterialId = new JSONObject();
				JsonTOGenerateMaterialId.put("tblName", "sm_product_pr");
				JsonTOGenerateMaterialId.put("fieldName", "product_pr_id");
				JsonTOGenerateMaterialId.put("company_id", company_id);
				JsonTOGenerateMaterialId.put("Length", "6");

				JSONObject extraConditionJson = new JSONObject();
				extraConditionJson.put("key", "product_type_id");
				extraConditionJson.put("value", cSmProductPrModel.getProduct_type_id().toString());
				JsonTOGenerateMaterialId.put("conditionsKey", extraConditionJson);

				String generatedMaterialId = autoNoController.FnGenerateMaterialId(JsonTOGenerateMaterialId);
				cSmProductPrModel.setProduct_pr_id("PR" + generatedMaterialId);

				iSmProductPrRepository.save(cSmProductPrModel);
				responce.put("success", 1);
				responce.put("data", cSmProductPrModel);
				responce.put("error", "");
				responce.put("message", "Record added Successfully..! ");
				return responce;
			} else {
				cSmProductPrModel.setProduct_pr_id(existingProductPr.getProduct_pr_id());

				responce.put("success", 1);
				responce.put("data", cSmProductPrModel);
				responce.put("error", "");
				responce.put("message", "Already Exist...!");
				return responce;
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductPr/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductPr/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}

		return responce;
	}
}
