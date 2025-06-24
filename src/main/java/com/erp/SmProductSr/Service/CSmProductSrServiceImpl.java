package com.erp.SmProductSr.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Customer.Model.CCustomerViewModel;
import com.erp.Customer.Repository.ICustomerViewRepository;
import com.erp.Product_Qa_Parameters.Model.CProductQaParametersViewModel;
import com.erp.Product_Qa_Parameters.Repository.IProductQaParametersViewRepository;
import com.erp.SmProductProcess.Model.CPmProductProcessViewModel;
import com.erp.SmProductProcess.Repository.IPmProductProcessViewRepository;
import com.erp.SmProductSr.Model.CSmProductSrModel;
import com.erp.SmProductSr.Model.CSmProductSrViewModel;
import com.erp.SmProductSr.Repository.ISmProductSrRepository;
import com.erp.SmProductSr.Repository.ISmProductSrViewRepository;
import com.erp.SmProductSrActivities.Model.CSmProductSrActivitiesModel;
import com.erp.SmProductSrActivities.Model.CSmProductSrActivitiesViewModel;
import com.erp.SmProductSrActivities.Repository.ISmProductSrActivitiesRepository;
import com.erp.SmProductSrActivities.Repository.ISmProductSrActivitiesViewRepository;
import com.erp.SmProductSrCustomer.Model.CSmProductSrCustomerModel;
import com.erp.SmProductSrCustomer.Repository.ISmProductSrCustomerRepository;
import com.erp.SmProductSrCustomer.Repository.ISmProductSrCustomerViewRepository;
import com.erp.SmProductSrProcess.Model.CSmProductSrProcessModel;
import com.erp.SmProductSrProcess.Repository.ISmProductSrProcessRepository;
import com.erp.SmProductSrProcess.Repository.ISmProductSrProcessViewRepository;
import com.erp.SmProductSrQaMapping.Model.CSmProductSrQaMappingModel;
import com.erp.SmProductSrQaMapping.Repository.ISmProductSrQaMappingRepository;
import com.erp.SmProductSrQaMapping.Repository.ISmProductSrQaMappingViewRepository;
import com.erp.SmProductSrSupplier.Model.CSmProductSrSupplierModel;
import com.erp.SmProductSrSupplier.Repository.ISmProductSrSupplierRepository;
import com.erp.SmProductSrSupplier.Repository.ISmProductSrSupplierViewRepository;
import com.erp.Supplier.Model.CSupplierViewModel;
import com.erp.Supplier.Repository.ISupplierViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CSmProductSrServiceImpl implements ISmProductSrService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductSrRepository iSmProductSrRepository;

	@Autowired
	ISmProductSrViewRepository iSmProductSrViewRepository;

	@Autowired
	ISmProductSrCustomerRepository iSmProductSrCustomerRepository;

	@Autowired
	ISmProductSrCustomerViewRepository iSmProductSrCustomerViewRepository;

	@Autowired
	ISmProductSrProcessViewRepository iSmProductSrProcessViewRepository;

	@Autowired
	ISmProductSrProcessRepository iSmProductSrProcessRepository;

	@Autowired
	ISmProductSrQaMappingRepository iSmProductSrQaMappingRepository;

	@Autowired
	ISmProductSrQaMappingViewRepository iSmProductSrQaMappingViewRepository;

	@Autowired
	ISmProductSrSupplierRepository iSmProductSrSupplierRepository;

	@Autowired
	ISmProductSrSupplierViewRepository iSmProductSrSupplierViewRepository;

	@Autowired
	ISmProductSrActivitiesRepository iSmProductSrActivitiesRepository;

	@Autowired
	ISmProductSrActivitiesViewRepository iSmProductSrActivitiesViewRepository;

	@Autowired
	IPmProductProcessViewRepository iPmProductProcessViewRepository;
	@Autowired
	ICustomerViewRepository iCustomerViewRepository;
	@Autowired
	IProductQaParametersViewRepository iProductQaParametersViewRepository;
	@Autowired
	ISupplierViewRepository iSupplierViewRepository;

	@Value("${document.file.path}")
	private String filePath;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile qrCodeFile) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		String key = jsonObject.getString("saveKey");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		String product_sr_id = commonIdsObj.getString("product_sr_id");
		int product_id = commonIdsObj.getInt("product_id");

		AtomicInteger atomicproductId = new AtomicInteger(product_id);

//      Convert to AtomicInteger
		AtomicReference<String> atomicProduct_sr_id = new AtomicReference(product_sr_id);

		JSONObject json = jsonObject.getJSONObject("ServiceMasterData");
		JSONArray srCustomerArray = (JSONArray) jsonObject.get("TransCustomerData");
		JSONArray srServiceProcessArray = (JSONArray) jsonObject.get("SrProcessData");
		JSONArray qaMappingArray = (JSONArray) jsonObject.get("QaMappingData");
		JSONArray srSupplierarray = (JSONArray) jsonObject.get("SrSupplierData");
		JSONArray srActivitiesArray = (JSONArray) jsonObject.get("SrActivitiesData");

		try {
			CSmProductSrModel responseProductSrModel = null;
			// Product Service
			CSmProductSrModel jsonModel = objectMapper.readValue(json.toString(), CSmProductSrModel.class);
			if (key.equals("generalEntry") || key.equals("allServiceData")) {

				if (jsonModel.getProduct_id() == 0) {

//					Check similar Product Service name or short name is exist or not

					CSmProductSrModel resultsCustName = null;
					if (jsonModel.getProduct_sr_short_name() == null
							|| jsonModel.getProduct_sr_short_name().isEmpty()) {
						resultsCustName = iSmProductSrRepository.getCheck(jsonModel.getProduct_sr_name(), null,
								jsonModel.getCompany_id());
					} else {
						resultsCustName = iSmProductSrRepository.getCheck(jsonModel.getProduct_sr_name(),
								jsonModel.getProduct_sr_short_name(), jsonModel.getCompany_id());
					}

					if (resultsCustName != null) {
						responce.put("success", 0);
						responce.put("error", "Product Service Name or Short Name is already exist!");
						return responce;
					}

				}

				responseProductSrModel = iSmProductSrRepository.save(jsonModel);

				atomicProduct_sr_id.set(responseProductSrModel.getProduct_sr_id());

				if (qrCodeFile != null) {
					Map<String, Object> qrCodeStoreResponse = FnStoreQRFile(qrCodeFile, atomicProduct_sr_id.get(),
							responseProductSrModel.getProduct_sr_name());
					// Accessing values from qrCodeStoreResponse
					Object successValue = qrCodeStoreResponse.get("success");
					Object dataValue = qrCodeStoreResponse.get("data");
					int success = (int) successValue;
					if (success == 1) {
						iSmProductSrRepository.updateQrCodePath(atomicProduct_sr_id.get(), dataValue);
						responseProductSrModel.setProduct_sr_qr_code((String) dataValue);
					}
				}
			}
			// Product Service Customer
			if (key.equals("customerMapping") || key.equals("allServiceData")) {
				iSmProductSrCustomerRepository.updateStatus(product_sr_id, company_branch_id, company_id);

				List<CSmProductSrCustomerModel> csmProductSrCustomerModel = objectMapper
						.readValue(srCustomerArray.toString(), new TypeReference<List<CSmProductSrCustomerModel>>() {
						});

				csmProductSrCustomerModel.forEach(custArray -> {
					custArray.setProduct_sr_id(atomicProduct_sr_id.get());
				});

				iSmProductSrCustomerRepository.saveAll(csmProductSrCustomerModel);
			}
			// Product Service Process
			if (key.equals("processMapping") || key.equals("allServiceData")) {
				iSmProductSrProcessRepository.updateStatus(product_sr_id, company_branch_id, company_id);

				List<CSmProductSrProcessModel> csmProductSrProcessModel = objectMapper.readValue(
						srServiceProcessArray.toString(), new TypeReference<List<CSmProductSrProcessModel>>() {
						});

				csmProductSrProcessModel.forEach(processArray -> {
					processArray.setProduct_sr_id(atomicProduct_sr_id.get());
				});

				iSmProductSrProcessRepository.saveAll(csmProductSrProcessModel);
			}
			// Product Service QaMapping
			if (key.equals("processMapping") || key.equals("allServiceData")) {
				iSmProductSrQaMappingRepository.updateStatus(product_sr_id, company_branch_id, company_id);

				List<CSmProductSrQaMappingModel> csmProductSrQaMappingModel = objectMapper
						.readValue(qaMappingArray.toString(), new TypeReference<List<CSmProductSrQaMappingModel>>() {
						});

				csmProductSrQaMappingModel.forEach(qaMapping -> {
					qaMapping.setProduct_sr_id(atomicProduct_sr_id.get());
				});

				iSmProductSrQaMappingRepository.saveAll(csmProductSrQaMappingModel);
			}
			// Product Service Supplier
			if (key.equals("supplierMapping") || key.equals("allServiceData")) {
				iSmProductSrSupplierRepository.updateStatus(product_sr_id, company_branch_id, company_id);

				List<CSmProductSrSupplierModel> csmProductSrSupplierModel = objectMapper
						.readValue(srSupplierarray.toString(), new TypeReference<List<CSmProductSrSupplierModel>>() {
						});

				csmProductSrSupplierModel.forEach(supplierArray -> {
					supplierArray.setProduct_sr_id(atomicProduct_sr_id.get());
				});

				iSmProductSrSupplierRepository.saveAll(csmProductSrSupplierModel);
			}

			// Product Service Activity.
			iSmProductSrActivitiesRepository.updateStatus(product_sr_id, company_branch_id, company_id);
			List<CSmProductSrActivitiesModel> cSmProductSrActivitiesModels = objectMapper
					.readValue(srActivitiesArray.toString(), new TypeReference<List<CSmProductSrActivitiesModel>>() {
					});
			cSmProductSrActivitiesModels.forEach(activity -> {
				activity.setProduct_sr_activity_id(0);
				activity.setProduct_sr_id(atomicProduct_sr_id.get());
			});
			iSmProductSrActivitiesRepository.saveAll(cSmProductSrActivitiesModels);

			responce.put("success", "1");
			responce.put("data", responseProductSrModel);
			responce.put("error", "");
			responce.put("message",
					atomicproductId.get() == 0 ? "Record added successfully..!" : "Record updated successfully..!");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductSr/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductSr/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}

	public Map<String, Object> FnStoreQRFile(MultipartFile file, String product_sr_id, String product_sr_name) {
		Map<String, Object> responce = new HashMap<>();
		try {
			String docStorePath1 = filePath + "DocStores" + File.separator;
			if (!new File(docStorePath1).exists()) {
				new File(docStorePath1).mkdir();
			}
			String docStorePath = docStorePath1 + "Service" + File.separator;
			if (!new File(docStorePath).exists()) {
				new File(docStorePath).mkdir();
			}
			String doctStorePathWithId = docStorePath + product_sr_id + File.separator;
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
			String docName = product_sr_name + "_qrcode" + extesion;
			String storeDoc = doctStorePathWithId + docName;

//			String[] storedocDb = storeDoc.split("ERP_DEV" +  5.separator);

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
	@Transactional
	public Object FnDeleteRecord(String product_sr_id, int company_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iSmProductSrRepository.deleteproductSrMasterRecords(product_sr_id, company_id, deleted_by);
			iSmProductSrCustomerRepository.deleteproductSrCustomerRecords(product_sr_id, company_id, deleted_by);
			iSmProductSrProcessRepository.deleteproductSrProcessRecords(product_sr_id, company_id, deleted_by);
			iSmProductSrQaMappingRepository.deleteproductSrQaMappingRecords(product_sr_id, company_id, deleted_by);
			iSmProductSrSupplierRepository.deleteproductSrSupplierRecords(product_sr_id, company_id, deleted_by);
			iSmProductSrActivitiesRepository.deleteproductSrActivitiesRecords(product_sr_id, company_id, deleted_by);

			Optional<CSmProductSrModel> option = iSmProductSrRepository.findByIdProduct_sr_id(product_sr_id);
			CSmProductSrModel cSmProductSrModel = new CSmProductSrModel();
			if (option.isPresent()) {
				cSmProductSrModel = option.get();
				if (cSmProductSrModel.getProduct_sr_qr_code() != null) {
					String document_path = cSmProductSrModel.getProduct_sr_qr_code();
					String existingDocPath = filePath + document_path;
					File directoryPath = new File(existingDocPath);
					directoryPath.delete();
				}
			}

			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", "");
		}

		return responce;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int product_sr_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CSmProductSrModel cSmProductSrModel = null;
		try {
			cSmProductSrModel = iSmProductSrRepository.FnShowParticularRecordForUpdate(product_sr_id, company_id);
			responce.put("success", "1");
			responce.put("data", cSmProductSrModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}

	@Override
	public Page<CSmProductSrViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id) {
		return iSmProductSrViewRepository.FnShowParticularRecord(product_sr_id, pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowAllSmProductSrMasterAndDetailsRecords(String product_sr_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			CSmProductSrModel productServiceMasterRecords = iSmProductSrRepository
					.FnShowProductServiceMasterRecords(product_sr_id, company_id);

			List<CSmProductSrCustomerModel> productServiceCustomerRecords = iSmProductSrCustomerRepository
					.FnShowProductServiceCustomerRecords(product_sr_id, company_id);

			List<CSmProductSrProcessModel> productServiceProcessRecordsRecords = iSmProductSrProcessRepository
					.FnShowProductServiceProcessRecords(product_sr_id, company_id);

			List<CSmProductSrQaMappingModel> productServiceQaMappingModelRecords = iSmProductSrQaMappingRepository
					.FnShowProductServiceQaMappingRecords(product_sr_id, company_id);

			List<CSmProductSrSupplierModel> productServiceSupplierModelRecords = iSmProductSrSupplierRepository
					.FnShowProductServiceSupplierRecords(product_sr_id, company_id);

			List<CSmProductSrActivitiesViewModel> ProductServiceActivitiesRecords = iSmProductSrActivitiesViewRepository
					.FnShowProductServiceActivitiesRecords(product_sr_id, company_id);

//			List<CSmProductSrCustomerViewModel> productServiceCustomerRecords = iSmProductSrCustomerViewRepository
//			.FnShowProductServiceCustomerRecords(product_sr_id, company_id);
//	

//			List<CSmProductSrProcessViewModel> productServiceProcessRecordsRecords = iSmProductSrProcessViewRepository
//					.FnShowProductServiceProcessRecords(product_sr_id, company_id);
//			

//			List<CSmProductSrQaMappingModel> productServiceQaMappingModelRecords = iSmProductSrQaMappingViewRepository
//					.FnShowProductServiceQaMappingRecords(product_sr_id, company_id);

//			List<CSmProductSrSupplierViewModel> productServiceSupplierModelRecords = iSmProductSrSupplierViewRepository
//					.FnShowProductServiceSupplierRecords(product_sr_id, company_id);

			responce.put("ProductServiceMasterRecords", productServiceMasterRecords);
			responce.put("ProductServiceCustomerRecords", productServiceCustomerRecords);
			responce.put("ProductServiceProcessRecordsRecords", productServiceProcessRecordsRecords);
			responce.put("ProductServiceQaMappingModelRecords", productServiceQaMappingModelRecords);
			responce.put("ProductServiceSupplierModelRecords", productServiceSupplierModelRecords);
			responce.put("ProductServiceActivitiesRecords", ProductServiceActivitiesRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		if ("summary".equals(reportType)) {
			Map<String, Object> results = iSmProductSrRepository.FnShowAllSummaryReportRecords();
			response.put("content", results);
		} else {
			Map<String, Object> results = iSmProductSrRepository.FnShowAlldetailsReportRecords();
			response.put("content", results);
		}

		return response;
	}

	@Override
	public Map<String, Object> FnShowAllDefaultMappingsRecords(int company_id, String accordianSelectKey) {
		Map<String, Object> responce = new HashMap<>();
		try {

			if (accordianSelectKey.equalsIgnoreCase("customerMapping")) {

				List<CCustomerViewModel> productSrCustomerAllActiveRecords = iCustomerViewRepository
						.FnShowProductSrCustomerActiveRecords(company_id);

				responce.put("ProductSrCustomerAllActiveRecords", productSrCustomerAllActiveRecords);

			} else if (accordianSelectKey.equalsIgnoreCase("ProcessMapping")) {

				List<CPmProductProcessViewModel> productServiceProcessAllActiveRecords = iPmProductProcessViewRepository
						.FnShowProductSrProcessActiveRecords(company_id);
				responce.put("ProductServiceProcessAllActiveRecords", productServiceProcessAllActiveRecords);

			} else if (accordianSelectKey.equalsIgnoreCase("QaMapping")) {

				List<CProductQaParametersViewModel> productServiceQaMappingAllActiveRecords = iProductQaParametersViewRepository
						.FnShowProductSrQaMappingActiveRecords(company_id);
				responce.put("ProductServiceQaMappingAllActiveRecords", productServiceQaMappingAllActiveRecords);

			} else if (accordianSelectKey.equalsIgnoreCase("SupplierMapping")) {

				List<CSupplierViewModel> productServiceSupplierAllActiveRecords = iSupplierViewRepository
						.FnShowProductSrSupplierActiveRecords(company_id);

				responce.put("ProductServiceSupplierAllActiveRecords", productServiceSupplierAllActiveRecords);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

}
