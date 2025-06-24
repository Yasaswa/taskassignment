package com.erp.FinishGoods.SmProductFg.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import com.erp.RawMaterial.Product_Rm.Model.CProductRmModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Customer.Model.CCustomerViewModel;
import com.erp.Customer.Repository.ICustomerViewRepository;
import com.erp.FinishGoods.SmProductFg.Model.CSmProductFgModel;
import com.erp.FinishGoods.SmProductFg.Repository.ISmProductFgRepository;
import com.erp.FinishGoods.SmProductFg.Repository.ISmProductFgViewRepository;
import com.erp.FinishGoods.SmProductFgBomMst.Repository.ISmProductFgBomDtlRepository;
import com.erp.FinishGoods.SmProductFgBomMst.Repository.ISmProductFgBomMstRepository;
import com.erp.FinishGoods.SmProductFgBomMst.Repository.ISmvProductFgBomDetailsRepository;
import com.erp.FinishGoods.SmProductFgCommercial.Model.CSmProductFgCommercialModel;
import com.erp.FinishGoods.SmProductFgCommercial.Repository.ISmProductFgCommercialRepository;
import com.erp.FinishGoods.SmProductFgCustomer.Model.CSmProductFgCustomerModel;
import com.erp.FinishGoods.SmProductFgCustomer.Repository.ISmProductFgCustomerRepository;
import com.erp.FinishGoods.SmProductFgProcess.Model.CSmProductFgProcessModel;
import com.erp.FinishGoods.SmProductFgProcess.Repository.ISmProductFgProcessRepository;
import com.erp.FinishGoods.SmProductFgQaMapping.Model.CSmProductFgQaMappingModel;
import com.erp.FinishGoods.SmProductFgQaMapping.Repository.ISmProductFgQaMappingRepository;
import com.erp.FinishGoods.SmProductFgSupplier.Model.CSmProductFgSupplierModel;
import com.erp.FinishGoods.SmProductFgSupplier.Repository.ISmProductFgSupplierRepository;
import com.erp.FinishGoods.SmProductFgTechnical.Model.CSmProductFgTechnicalModel;
import com.erp.FinishGoods.SmProductFgTechnical.Repository.ISmProductFgTechnicalRepository;
import com.erp.Product_Qa_Parameters.Model.CProductQaParametersViewModel;
import com.erp.Product_Qa_Parameters.Repository.IProductQaParametersViewRepository;
import com.erp.RawMaterial.Product_Rm.Model.CSmProductDynamicParametersModel;
import com.erp.RawMaterial.Product_Rm.Model.CSmProductDynamicParametersViewModel;
import com.erp.RawMaterial.Product_Rm.Repository.IProductDynamicParameterRepository;
import com.erp.RawMaterial.Product_Rm.Repository.IProductDynamicParameterViewRepository;
import com.erp.SmProductProcess.Model.CPmProductProcessViewModel;
import com.erp.SmProductProcess.Repository.IPmProductProcessViewRepository;
import com.erp.Supplier.Model.CSupplierViewModel;
import com.erp.Supplier.Repository.ISupplierViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CSmProductFgServiceImpl implements ISmProductFgService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductFgRepository iSmProductFgRepository;

	@Autowired
	ISmProductFgViewRepository iSmProductFgViewRepository;

	@Autowired
	ISmProductFgBomDtlRepository iSmProductFgBomDtlRepository;

	@Autowired
	ISmvProductFgBomDetailsRepository iSmvProductFgBomDetailsRepository;

	@Autowired
	ISmProductFgBomMstRepository iSmProductFgBomMstRepository;

	@Autowired
	ISmProductFgCommercialRepository iSmProductFgCommercialRepository;

	@Autowired
	ISmProductFgCustomerRepository iSmProductFgCustomerRepository;

	@Autowired
	ISmProductFgProcessRepository iSmProductFgProcessRepository;

	@Autowired
	ISmProductFgQaMappingRepository iSmProductFgQaMappingRepository;

	@Autowired
	ISmProductFgSupplierRepository iSmProductFgSupplierRepository;

	@Autowired
	ISmProductFgTechnicalRepository iSmProductFgTechnicalRepository;

	@Autowired
	ISupplierViewRepository iSupplierViewRepository;

	@Autowired
	IProductQaParametersViewRepository iProductQaParametersViewRepository;

	@Autowired
	IPmProductProcessViewRepository iPmProductProcessViewRepository;

	@Autowired
	ICustomerViewRepository iCustomerViewRepository;

	@Autowired
	IProductDynamicParameterRepository iProductDynamicParameterRepository;

	@Autowired
	IProductDynamicParameterViewRepository productDynamicParameterViewRepository;

	@Value("${document.file.path}")
	private String filePath;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile qrCodeFile) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int product_id = commonIdsObj.getInt("product_id");
		String product_fg_id = commonIdsObj.getString("product_fg_id");
		String key = jsonObject.getString("saveKey");

		JSONObject productFgjson = jsonObject.getJSONObject("ProductFgData");
		JSONObject Technicaljson = jsonObject.getJSONObject("TechnicalData");
		JSONObject Commercialjson = jsonObject.getJSONObject("CommercialData");

		JSONArray CustomerArray = (JSONArray) jsonObject.get("CustomerData");
		JSONArray ProcessArray = (JSONArray) jsonObject.get("ProcessData");
		JSONArray QaMappingArray = (JSONArray) jsonObject.get("QaMappingData");
		JSONArray SupplierArray = (JSONArray) jsonObject.get("SupplierData");
		JSONArray ProductdDynamicParametersArrray = (JSONArray) jsonObject.get("AdditionalParametersData");

		AtomicReference<String> atomicProduct_fg_id = new AtomicReference<>(product_fg_id);

		try {

			// Product Fg Master....
			CSmProductFgModel productFgModel = objectMapper.readValue(productFgjson.toString(),
					CSmProductFgModel.class);
			CSmProductFgModel responseProductFgModel = new CSmProductFgModel();
			if (key.equals("generalEntry") || key.equals("allFGData")) {
				if (productFgModel.getProduct_id().equals(0)) {

					Map<String, Object> modelCheckCode = iSmProductFgRepository
							.CheckIfCodeExist(productFgModel.getProduct_fg_code(), company_id);
					if (!modelCheckCode.isEmpty()) {
						responce.put("success", 0);
						responce.put("data", "");
						responce.put("error", "Finish good Code is already exist!");
						return responce;
					}
					CSmProductFgModel model = iSmProductFgRepository.CheckIfExist(productFgModel.getProduct_fg_name(),
							company_id);
					if (model != null) {
						responce.put("success", 0);
						responce.put("data", "");
						responce.put("error", "Finish good name is already exist!");
						return responce;
					}

				}
				responseProductFgModel = iSmProductFgRepository.save(productFgModel);
				atomicProduct_fg_id.set(responseProductFgModel.getProduct_fg_id());

				if (qrCodeFile != null) {
					Map<String, Object> qrCodeStoreResponse = FnStoreQRFile(qrCodeFile, atomicProduct_fg_id.get(),
							responseProductFgModel.getProduct_fg_name());
					// Accessing values from qrCodeStoreResponse
					Object successValue = qrCodeStoreResponse.get("success");
					Object dataValue = qrCodeStoreResponse.get("data");
					int success = (int) successValue;
					if (success == 1) {
						iSmProductFgRepository.updateQrCodePath(atomicProduct_fg_id.get(), (String) dataValue);
						responseProductFgModel.setProduct_fg_qr_code((String) dataValue);
					}
				}
			}
			// Product Fg Technical
			if (key.equals("technicalEntry") || key.equals("allFGData")) {
				CSmProductFgTechnicalModel cSmProductFgTechnicalModel = objectMapper.readValue(Technicaljson.toString(),
						CSmProductFgTechnicalModel.class);

				if (cSmProductFgTechnicalModel.getproduct_fg_technical_id() == 0) {

					CSmProductFgTechnicalModel model = iSmProductFgTechnicalRepository
							.checkIfExist(cSmProductFgTechnicalModel.getproduct_fg_technical_name(), company_id);

					if (model != null) {
						responce.put("success", 0);
						responce.put("data", "");
						responce.put("error", "Technical name is already exist!");
						return responce;
					}
				}
				iSmProductFgTechnicalRepository
						.updateActiveStatusProductTechnical(cSmProductFgTechnicalModel.getproduct_fg_technical_id());
				cSmProductFgTechnicalModel.setproduct_fg_id(responseProductFgModel.getProduct_fg_id());

				iSmProductFgTechnicalRepository.save(cSmProductFgTechnicalModel);

			}
			// Product Fg Commercial
			if (key.equals("commercialEntry") || key.equals("allFGData")) {
				CSmProductFgCommercialModel cSmProductFgCommercialModel = objectMapper
						.readValue(Commercialjson.toString(), CSmProductFgCommercialModel.class);

				iSmProductFgCommercialRepository.updateActiveStatusProductCommercial(product_fg_id);
				cSmProductFgCommercialModel.setproduct_fg_id(responseProductFgModel.getProduct_fg_id());

				iSmProductFgCommercialRepository.save(cSmProductFgCommercialModel);
			}
			// Product Fg Customer
			if (key.equals("customerMapping") || key.equals("allFGData")) {
				iSmProductFgCustomerRepository.updateProductFgCustomerActiveStatus(product_fg_id, company_id);

				if (!CustomerArray.isEmpty()) {
					List<CSmProductFgCustomerModel> cSmProductFgCustomerModel = objectMapper
							.readValue(CustomerArray.toString(), new TypeReference<List<CSmProductFgCustomerModel>>() {
							});

					cSmProductFgCustomerModel.forEach(items -> {
						items.setproduct_fg_id(atomicProduct_fg_id.get());
						items.setproduct_fg_customer_id(0);
					});

					iSmProductFgCustomerRepository.saveAll(cSmProductFgCustomerModel);
				}
			}
			// Product Fg Process
			if (key.equals("processMapping") || key.equals("allFGData")) {
				iSmProductFgProcessRepository.updateProductFGProcessActiveStatus(product_fg_id, company_id);

				if (!ProcessArray.isEmpty()) {
					List<CSmProductFgProcessModel> cSmProductFgProcessModel = objectMapper
							.readValue(ProcessArray.toString(), new TypeReference<List<CSmProductFgProcessModel>>() {
							});

					cSmProductFgProcessModel.forEach(items -> {
						items.setproduct_fg_id(atomicProduct_fg_id.get());
						items.setproduct_fg_process_id(0);
					});

					iSmProductFgProcessRepository.saveAll(cSmProductFgProcessModel);
				}
			}
			// Product Fg QaMapping
			if (key.equals("qaMapping") || key.equals("allFGData")) {

				iSmProductFgQaMappingRepository.updateProductFgQaMappingActiveStatus(product_fg_id, company_id);

				if (!QaMappingArray.isEmpty()) {
					List<CSmProductFgQaMappingModel> cSmProductFgQaMappingModel = objectMapper.readValue(
							QaMappingArray.toString(), new TypeReference<List<CSmProductFgQaMappingModel>>() {
							});

					cSmProductFgQaMappingModel.forEach(items -> {
						items.setproduct_fg_id(atomicProduct_fg_id.get());
						items.setproduct_fg_qa_mapping_id(0);
					});

					iSmProductFgQaMappingRepository.saveAll(cSmProductFgQaMappingModel);

				}
			}
			// Product Fg Supplier
			if (key.equals("supplierMapping") || key.equals("allFGData")) {
				iSmProductFgSupplierRepository.updateProductFgSupplierActiveStatus(product_fg_id, company_id);

				if (!SupplierArray.isEmpty()) {
					List<CSmProductFgSupplierModel> cSmProductFgSupplierModel = objectMapper
							.readValue(SupplierArray.toString(), new TypeReference<List<CSmProductFgSupplierModel>>() {
							});

					cSmProductFgSupplierModel.forEach(items -> {
						items.setProduct_fg_id(atomicProduct_fg_id.get());
						items.setProduct_fg_supplier_id(0);
					});

					iSmProductFgSupplierRepository.saveAll(cSmProductFgSupplierModel);
				}
			}

			// Product Dynamic Parameters
			if (key.equals("allFGData")) {

				if (!ProductdDynamicParametersArrray.isEmpty()) {
					List<CSmProductDynamicParametersModel> cSmProductDynamicParametersModel = objectMapper.readValue(
							ProductdDynamicParametersArrray.toString(),
							new TypeReference<List<CSmProductDynamicParametersModel>>() {
							});
					CSmProductDynamicParametersModel firstObject = cSmProductDynamicParametersModel.get(0); // Accessing
																											// the first
																											// object
					String modifiedBy = firstObject.getModified_by();
					iProductDynamicParameterRepository.fnDeleteProductDynamicParameters(product_fg_id, modifiedBy);
					iProductDynamicParameterRepository.saveAll(cSmProductDynamicParametersModel);
				}
			}

			responce.put("success", 1);
			responce.put("data", responseProductFgModel);
			responce.put("error", "");
			responce.put("message",
					product_id == 0 ? "Record added Successfully..! " : "Record updated Successfully..!");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFg/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductFg/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}

	public Map<String, Object> FnStoreQRFile(MultipartFile file, String product_fg_id, String product_fg_name) {
		Map<String, Object> responce = new HashMap<>();
		try {
			String docStorePath1 = filePath + "DocStores" + File.separator;
			if (!new File(docStorePath1).exists()) {
				new File(docStorePath1).mkdir();
			}
			String docStorePath = docStorePath1 + "Finish Goods" + File.separator;
			if (!new File(docStorePath).exists()) {
				new File(docStorePath).mkdir();
			}
			String doctStorePathWithId = docStorePath + product_fg_id + File.separator;
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
			String docName = product_fg_name + "_qrcode" + extesion;
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
	public Object FnDeleteRecord(String product_fg_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iSmProductFgRepository.deleteProductFgRecords(product_fg_id, deleted_by);
			iSmProductFgTechnicalRepository.deleteTechnicalRecords(product_fg_id, deleted_by);
			iSmProductFgCommercialRepository.deleteCommercialRecords(product_fg_id, deleted_by);
			iSmProductFgCustomerRepository.deleteCustomerRecords(product_fg_id, deleted_by);
			iSmProductFgProcessRepository.deleteProcessRecords(product_fg_id, deleted_by);
			iSmProductFgQaMappingRepository.deleteQaMappingRecords(product_fg_id, deleted_by);
			iSmProductFgSupplierRepository.deleteSupplierRecords(product_fg_id, deleted_by);
			iProductDynamicParameterRepository.deleteProductDynamicParameters(product_fg_id, deleted_by);

			Optional<CSmProductFgModel> option = iSmProductFgRepository.findByProductFgId(product_fg_id);
			CSmProductFgModel cSmProductFgModel = new CSmProductFgModel();
			if (option.isPresent()) {
				cSmProductFgModel = option.get();
				if (cSmProductFgModel.getProduct_fg_qr_code() != null) {
					String document_path = cSmProductFgModel.getProduct_fg_qr_code();
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

	@Override
	public Map<String, Object> FnShowAllProductFgDetailsAndMasterRecords(String product_fg_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			CSmProductFgModel productFgRecords = iSmProductFgRepository.FnShowParticularProductFgRecords(product_fg_id);

			Map<String, Object> technicalRecords = iSmProductFgTechnicalRepository
					.FnShowParticularProductFgTechnicalRecords(product_fg_id);

			Map<String, Object> commercialRecords = iSmProductFgCommercialRepository
					.FnShowParticularProductFgCommercialRecords(product_fg_id);

			List<CSmProductFgCustomerModel> customerRecords = iSmProductFgCustomerRepository
					.FnShowParticularProductFgCustomerRecords(product_fg_id);

			List<CSmProductFgProcessModel> processRecords = iSmProductFgProcessRepository
					.FnShowParticularProductFgProcessRecords(product_fg_id);

			List<CSmProductFgQaMappingModel> qaMappingRecords = iSmProductFgQaMappingRepository
					.FnShowParticularProductFGQaMappingRecords(product_fg_id);

			List<CSmProductFgSupplierModel> supplierRecords = iSmProductFgSupplierRepository
					.FnShowParticularProductFgSupplierRecords(product_fg_id);

			// get product_type_id from productFgRecords
			int product_type_id = productFgRecords.getProduct_type_id();

			// Fetch Product Dynamic Parameters Records
			List<CSmProductDynamicParametersViewModel> productDynamicParametersRecords = productDynamicParameterViewRepository
					.FnShowParticularProductDynamicParametersRecords(product_type_id, product_fg_id);

			responce.put("ProductFgRecords", productFgRecords);
			responce.put("TechnicalRecords", technicalRecords);
			responce.put("CommercialRecords", commercialRecords);
			responce.put("CustomerRecords", customerRecords);
			responce.put("ProcessRecords", processRecords);
			responce.put("QaMappingRecords", qaMappingRecords);
			responce.put("SupplierRecords", supplierRecords);
			responce.put("AdditionalParametersData", productDynamicParametersRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;

	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {

		Map<String, Object> response = new HashMap<>();
		Set<String> headerKeys = null;
		Map<String, Object> results = null;

		if ("summary".equals(reportType)) {
			results = iSmProductFgRepository.FnShowAllSummaryReportRecords();
			response.put("content", results);
			headerKeys = results.keySet();
		} else {
			results = iSmProductFgRepository.FnShowAlldetailsReportRecords();
			response.put("content", results);
			headerKeys = results.keySet();
		}

		if (!results.isEmpty()) {
			response.put("headerKeys", headerKeys);
		}

		return response;
	}

	@Override
	public Map<String, Object> FnShowAllRecords(int company_id, String updatAllRecords) {
		Map<String, Object> responce = new HashMap<>();
		try {

			if (updatAllRecords.equalsIgnoreCase("SupplierMapping")) {

				List<CSupplierViewModel> supplierRecords = iSupplierViewRepository.FnShowAllSupplierRecords(company_id);
				responce.put("SupplierAllRecords", supplierRecords);

			} else if (updatAllRecords.equalsIgnoreCase("CustomerMapping")) {

				List<CCustomerViewModel> customerRecords = iCustomerViewRepository.FnShowAllCustomerRecords(company_id);
				responce.put("CustomerRecords", customerRecords);

			} else if (updatAllRecords.equalsIgnoreCase("ProcessRecords")) {

				List<CPmProductProcessViewModel> processRecords = iPmProductProcessViewRepository
						.FnShowAllProductProcessRecords(company_id);
				responce.put("ProcessRecords", processRecords);

			} else if (updatAllRecords.equalsIgnoreCase("QaParameters")) {

				List<CProductQaParametersViewModel> qaParametersAllRecords = iProductQaParametersViewRepository
						.FnShowAllQaParametersRecords(company_id);
				responce.put("QaParametersAllRecords", qaParametersAllRecords);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

}
