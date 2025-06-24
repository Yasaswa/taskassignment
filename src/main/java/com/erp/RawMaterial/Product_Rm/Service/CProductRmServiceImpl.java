package com.erp.RawMaterial.Product_Rm.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Company.Companies.Model.CCompanyModel;
import com.erp.Company.Companies.Repository.ICompanyRepository;
import com.erp.Company.Company_Branch.Model.CCompanyBranchModel;
import com.erp.Company.Company_Branch.Repository.ICompanyBranchRepository;
import com.erp.Product_Qa_Parameters.Model.CProductQaParametersViewModel;
import com.erp.Product_Qa_Parameters.Repository.IProductQaParametersViewRepository;
import com.erp.PtGoodsReceiptDetails.Repository.IPtGoodsReceiptDetailsViewRepository;
import com.erp.RawMaterial.Product_Rm.Model.*;
import com.erp.RawMaterial.Product_Rm.Repository.*;
import com.erp.RawMaterial.Product_Rm_Commercial.Model.CProductRmCommercialModel;
import com.erp.RawMaterial.Product_Rm_Commercial.Repository.IProductRmCommercialRepository;
import com.erp.RawMaterial.Product_Rm_Process.Model.CProductRmProcessModel;
import com.erp.RawMaterial.Product_Rm_Process.Repository.IProductRmProcessRepository;
import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierModel;
import com.erp.RawMaterial.Product_Rm_Supplier.Model.CProductRmSupplierViewModel;
import com.erp.RawMaterial.Product_Rm_Supplier.Repository.IProductRmSupplierRepository;
import com.erp.RawMaterial.Product_Rm_Supplier.Repository.IProductRmSupplierViewRepository;
import com.erp.RawMaterial.Product_Rm_Technical.Model.CProductRmTechnicalModel;
import com.erp.RawMaterial.Product_Rm_Technical.Repository.IProductRmTechnicalRepository;
import com.erp.RawMaterial.SmProductRmQaMapping.Model.CSmProductRmQaMappingModel;
import com.erp.RawMaterial.SmProductRmQaMapping.Repository.ISmProductRmQaMappingRepository;
import com.erp.SmProductProcess.Model.CPmProductProcessViewModel;
import com.erp.SmProductProcess.Repository.IPmProductProcessViewRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockSummaryRepository;
import com.erp.SmProductStockAdjustment.Repository.ISmProductStockAdjustmentDetailsModelRepository;
import com.erp.Supplier.Model.CSupplierViewModel;
import com.erp.Supplier.Repository.ISupplierViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.persistence.Tuple;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

@Service
public class CProductRmServiceImpl implements IProductRmService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IProductRmRepository iProductRmRepository;

    @Autowired
    IProductRmViewRepository iProductRmViewRepository;

    @Autowired
    IProductRmDetailsViewRepository iProductRmDetailsViewRepository;

    @Autowired
    IProductRmSummaryViewRepository iProductRmSummaryViewRepository;

    @Autowired
    IProductRmListViewRepository iProductRmListViewRepository;

    @Autowired
    IProductRmSupplierRepository iProductRmSupplierRepository;

    @Autowired
    IProductRmSupplierViewRepository iProductRmSupplierViewRepository;

    @Autowired
    ISmProductRmQaMappingRepository iSmProductRmQaMappingRepository;

    @Autowired
    IProductRmProcessRepository iProductRmProcessRepository;

    @Autowired
    IProductRmTechnicalRepository iProductRmTechnicalRepository;

    @Autowired
    ISupplierViewRepository iSupplierViewRepository;

    @Autowired
    IProductQaParametersViewRepository iProductQaParametersViewRepository;

    @Autowired
    IPmProductProcessViewRepository iPmProductProcessViewRepository;
    @Autowired
    IProductDynamicParameterRepository iProductDynamicParameterRepository;
    @Autowired
    IProductDynamicParameterViewRepository productDynamicParameterViewRepository;
    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;
    @Autowired
    private IProductRmCommercialRepository iProductRmCommercialRepository;

    @Autowired
    private IPtGoodsReceiptDetailsViewRepository iPtGoodsReceiptDetailsViewRepository;

    @Autowired
    private ISmProductRmStockSummaryRepository iSmProductRmStockSummaryRepository;

    @Autowired
    private ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    ICompanyRepository iCompanyRepository;

    @Autowired
    ICompanyBranchRepository iCompanyBranchRepository;


    @Autowired
    ISmProductStockAdjustmentDetailsModelRepository iSmProductStockAdjustmentDetailsModelRepository;

    @Value("${document.file.path}")
    private String filePath;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile qrCodeFile) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        String key = jsonObject.getString("saveKey");
        int company_id = commonIdsObj.getInt("company_id");
        String product_rm_id = commonIdsObj.getString("product_rm_id");
        int product_id = commonIdsObj.getInt("product_id");

        // Convert to AtomicInteger
        AtomicReference<String> atomicProduct_rm_id = new AtomicReference(product_rm_id);

        JSONObject productRmjson = jsonObject.getJSONObject("ProductRmData");
        JSONObject productRmTechnicaljson = jsonObject.getJSONObject("ProductRmTechnicalData");
        JSONObject productRmCommercialjson = jsonObject.getJSONObject("ProductRmCommercialData");

        JSONArray productRmSupplierArray = (JSONArray) jsonObject.get("ProductRmSupplierData");
        JSONArray productProcessArray = (JSONArray) jsonObject.get("ProductRmProcessData");
        JSONArray ProductQaMappinArray = (JSONArray) jsonObject.get("ProductQaMappingData");
        JSONArray ProductdDynamicParametersArrray = (JSONArray) jsonObject.get("AdditionalParametersData");


        try {

            // Product Master....
            CProductRmModel jsonModel = objectMapper.readValue(productRmjson.toString(), CProductRmModel.class);
            CProductRmModel responseProductRmModel = null;
            double product_rm_mrp = 0;

            if (key.equals("generalEntry") || key.equals("allRawMaterialData")) {
                if (jsonModel.getProduct_id().equals(0)) {

                    //Check similar Product Raw Material Name and short name is exist or not
                    CProductRmModel resultsProductRmName = null;
                    if (jsonModel.getProduct_rm_short_name() == null || jsonModel.getProduct_rm_short_name().isEmpty()) {
                        resultsProductRmName = iProductRmRepository.getCheck(jsonModel.getProduct_rm_name(),
                                null, jsonModel.getCompany_id());
                    } else {
                        resultsProductRmName = iProductRmRepository.getCheck(jsonModel.getProduct_rm_name(),
                                jsonModel.getProduct_rm_short_name(), jsonModel.getCompany_id());
                    }

                    if (resultsProductRmName != null) {
                        responce.put("success", 0);
                        responce.put("error", "Product Raw Material Name or Short name is already exist!");
                        return responce;
                    }

                }
                responseProductRmModel = iProductRmRepository.save(jsonModel);
                atomicProduct_rm_id.set(responseProductRmModel.getProduct_rm_id());

                if (qrCodeFile != null) {
                    Map<String, Object> qrCodeStoreResponse = FnStoreQRFile(qrCodeFile, atomicProduct_rm_id.get(), responseProductRmModel.getProduct_rm_name());
                    // Accessing values from qrCodeStoreResponse
                    Object successValue = qrCodeStoreResponse.get("success");
                    Object dataValue = qrCodeStoreResponse.get("data");
                    int success = (int) successValue;
                    if (success == 1) {
                        iProductRmRepository.updateQrCodePath(atomicProduct_rm_id.get(), dataValue);
                        responseProductRmModel.setProduct_rm_qr_code((String) dataValue);
                    }
                }
            }

            // Product Technical
            if (key.equals("technicalEntry") || key.equals("allRawMaterialData")) {
                CProductRmTechnicalModel productRmTechnicalModel = objectMapper.readValue(productRmTechnicaljson.toString(),
                        CProductRmTechnicalModel.class);

                if (productRmTechnicalModel.getProduct_rm_technical_id() == 0) {

                    CProductRmTechnicalModel model = iProductRmTechnicalRepository
                            .getCheck(productRmTechnicalModel.getProduct_rm_technical_name());

                    if (model != null) {
                        responce.put("success", "0");
                        responce.put("data", "");
                        responce.put("error", "Technical Name is already exist!");
                        return responce;
                    }
                }

                iProductRmTechnicalRepository.updateActiveStatusProductTechnical(productRmTechnicalModel.getProduct_rm_technical_id());
                productRmTechnicalModel.setProduct_rm_id(atomicProduct_rm_id.get());

                iProductRmTechnicalRepository.save(productRmTechnicalModel);
            }
            // Product Commarcial
            if (key.equals("commercialEntry") || key.equals("allRawMaterialData")) {
                CProductRmCommercialModel cProductRmCommercialModel = objectMapper
                        .readValue(productRmCommercialjson.toString(), CProductRmCommercialModel.class);

                iProductRmCommercialRepository
                        .updateActiveStatusProductCommercialByRMId(product_rm_id);

                cProductRmCommercialModel.setProduct_rm_id(atomicProduct_rm_id.get());
                iProductRmCommercialRepository.save(cProductRmCommercialModel);
                product_rm_mrp = cProductRmCommercialModel.getProduct_rm_mrp();
            }

            // Product Supplier
            if (key.equals("supplierMapping") || key.equals("allRawMaterialData")) {
                iProductRmSupplierRepository.updateProductRMSupplierActiveStatus(product_rm_id, company_id);

                if (!productRmSupplierArray.isEmpty()) {
                    List<CProductRmSupplierModel> cProductRmSupplierModel = objectMapper.readValue(
                            productRmSupplierArray.toString(), new TypeReference<List<CProductRmSupplierModel>>() {
                            });

                    cProductRmSupplierModel.forEach(items -> {
                        items.setProduct_rm_id(atomicProduct_rm_id.get());
                        items.setProduct_rm_supplier_id(0);
                    });

                    iProductRmSupplierRepository.saveAll(cProductRmSupplierModel);
                }
            }
            // Product Rm Process
            if (key.equals("processMapping") || key.equals("allRawMaterialData")) {
                iProductRmProcessRepository.updateProductRMProcessActiveStatus(product_rm_id, company_id);

                if (!productProcessArray.isEmpty()) {
                    List<CProductRmProcessModel> cProductRmProcessModel = objectMapper
                            .readValue(productProcessArray.toString(), new TypeReference<List<CProductRmProcessModel>>() {
                            });
                    cProductRmProcessModel.forEach(items -> {
                        items.setProduct_rm_id(atomicProduct_rm_id.get());
                        items.setProduct_rm_process_id(0);
                    });

                    iProductRmProcessRepository.saveAll(cProductRmProcessModel);
                }
            }
            // Product Qa Mapping
            if (key.equals("qaMapping") || key.equals("allRawMaterialData")) {
                iSmProductRmQaMappingRepository.updateProductRMQaMappingActiveStatus(product_rm_id, company_id);

                if (!ProductQaMappinArray.isEmpty()) {
                    List<CSmProductRmQaMappingModel> csmProductRmQaMappingModel = objectMapper.readValue(
                            ProductQaMappinArray.toString(), new TypeReference<List<CSmProductRmQaMappingModel>>() {
                            });

                    csmProductRmQaMappingModel.forEach(items -> {
                        items.setproduct_rm_id(atomicProduct_rm_id.get());
                        items.setproduct_rm_qa_mapping_id(0);
                    });

                    iSmProductRmQaMappingRepository.saveAll(csmProductRmQaMappingModel);
                }
            }


            // Product Dynamic Parameters
            if (key.equals("allRawMaterialData")) {
                if (!ProductdDynamicParametersArrray.isEmpty()) {
                    List<CSmProductDynamicParametersModel> cSmProductDynamicParametersModel = objectMapper.readValue(
                            ProductdDynamicParametersArrray.toString(), new TypeReference<List<CSmProductDynamicParametersModel>>() {
                            });

                    CSmProductDynamicParametersModel firstObject = cSmProductDynamicParametersModel.get(0); // Accessing the first object
                    String modifiedBy = firstObject.getModified_by(); // Assuming you want to access a field named "fieldName"

                    iProductDynamicParameterRepository.fnDeleteProductDynamicParameters(product_rm_id, modifiedBy);
                    iProductDynamicParameterRepository.saveAll(cSmProductDynamicParametersModel);
                }
            }

            if (product_id == 0) {
                FnUpdateProductRmStockDetails(responseProductRmModel, company_id, commonIdsObj, product_rm_mrp);
            } else {
                FnUpdateStockAllDetails(responseProductRmModel, product_rm_mrp);
            }

            responce.put("success", 1);
            responce.put("data", responseProductRmModel);
            responce.put("error", "");
            responce.put("message", product_id == 0 ? "Record added successfully..!" : "Record updated successfully..!");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productRm/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
            // Rollback transaction explicitly for any exception
//			throw e;
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productRm/FnAddUpdateRecord", 0,
                    e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
            // Rollback transaction explicitly for any exception
//			throw e;
        }
        return responce;
    }

    private void FnUpdateStockAllDetails(CProductRmModel responseProductRmModel, double product_rm_mrp) {

        Integer companyId = responseProductRmModel.getCompany_id();
        String product_rm_id = responseProductRmModel.getProduct_rm_id();
        Integer godown_id = responseProductRmModel.getGodown_id();
        Integer godown_section_id = responseProductRmModel.getGodown_section_id();
        Integer godown_section_beans_id = responseProductRmModel.getGodown_section_beans_id();

        // update stock details for current dates only which is day_closed = 0
        iProductRmRepository.updateMaterialStockSummary(godown_id, godown_section_id, godown_section_beans_id, product_rm_id, companyId);
        iProductRmRepository.updateMaterialStockDetails(godown_id, godown_section_id, godown_section_beans_id, product_rm_id, companyId);

        if (product_rm_mrp != 0) {
            Integer isGrnDone = iPtGoodsReceiptDetailsViewRepository.checkGrnExists(product_rm_id, companyId);
            if (isGrnDone == 0) {
                iProductRmRepository.updateMaterialStockRate(product_rm_mrp, product_rm_id, companyId);
                iProductRmRepository.updateAllMaterialIssueRate(product_rm_mrp, product_rm_id ,companyId);
                iSmProductStockAdjustmentDetailsModelRepository.updateMaterialAdjustmentRate(product_rm_mrp, product_rm_id ,companyId);
            }
        }
    }

    private void FnUpdateProductRmStockDetails(CProductRmModel responseProductRmModel, int company_id, JSONObject commonIdsObj, double product_rm_mrp) {
        // Get the current date
        Date currentDate = new Date();
        //need to get financial_year,product_type_group
        String financial_year = commonIdsObj.getString("financial_year");
        String product_type_group = commonIdsObj.getString("product_type_group");

        // Formatting the date to display only the date portion
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);

        Map<String, Object> stockDetails = new HashMap<>();

        //	Create list to add object to save stock details & Summary
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addProductStockTrackingList = new ArrayList<>();

        ArrayList<CCompanyModel> companiesList =  iCompanyRepository.getCompaniesList();
        ArrayList<CCompanyBranchModel> companiesBranchList =  iCompanyBranchRepository.getCompaniesBranchList();

        // create opening balance stock entry for all active companies
        for (CCompanyModel company : companiesList) {
            // Company-specific values
            int companyId = company.getCompany_id();
            // Find branches for the current company
            Optional<CCompanyBranchModel> matchingBranches = companiesBranchList.stream()
                    .filter(branch -> branch.getCompany_id() == companyId).findFirst();

            //Rm Stock Summary
            CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

            productRmStockSummaryModel.setCompany_id(companyId);
            productRmStockSummaryModel.setCompany_branch_id(matchingBranches.get().getCompany_branch_id());
            productRmStockSummaryModel.setFinancial_year(financial_year);
            productRmStockSummaryModel.setProduct_type_group(product_type_group);
            productRmStockSummaryModel.setProduct_type_id(responseProductRmModel.getProduct_type_id());
            productRmStockSummaryModel.setProduct_rm_id(responseProductRmModel.getProduct_rm_id());
            productRmStockSummaryModel.setProduct_material_unit_id(responseProductRmModel.getProduct_rm_stock_unit_id());
            productRmStockSummaryModel.setProduct_material_packing_id(responseProductRmModel.getProduct_rm_packing_id());
            productRmStockSummaryModel.setOpening_quantity(0);
            productRmStockSummaryModel.setOpening_weight(0);
            productRmStockSummaryModel.setClosing_balance_quantity(0);
            productRmStockSummaryModel.setClosing_balance_weight(0);
            productRmStockSummaryModel.setGodown_id(responseProductRmModel.getGodown_id());
            productRmStockSummaryModel.setGodown_section_id(responseProductRmModel.getGodown_section_id());
            productRmStockSummaryModel.setGodown_section_beans_id(responseProductRmModel.getGodown_section_beans_id());
            productRmStockSummaryModel.setCreated_by(responseProductRmModel.getCreated_by());
            productRmStockSummaryModel.setModified_by(responseProductRmModel.getCreated_by());
            productRmStockSummaryModel.setMaterial_rate(0);
//            productRmStockSummaryModel.setStock_date(todayDate);

            addProductRmStockSummaryList.add(productRmStockSummaryModel);

            //Rm Stock Details
            CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

            productRmStockDetailsModel.setCompany_id(companyId);
            productRmStockDetailsModel.setCompany_branch_id(matchingBranches.get().getCompany_branch_id());
            productRmStockDetailsModel.setFinancial_year(financial_year);
            productRmStockDetailsModel.setProduct_type_group(product_type_group);
            productRmStockDetailsModel.setProduct_type_id(responseProductRmModel.getProduct_type_id());
            productRmStockDetailsModel.setProduct_rm_id(responseProductRmModel.getProduct_rm_id());
            productRmStockDetailsModel.setProduct_material_unit_id(responseProductRmModel.getProduct_rm_stock_unit_id());
            productRmStockDetailsModel.setProduct_material_packing_id(responseProductRmModel.getProduct_rm_packing_id());
            productRmStockDetailsModel.setStock_date(todayDate);
            productRmStockDetailsModel.setGoods_receipt_no("Opening Balance");
            productRmStockDetailsModel.setClosing_balance_quantity(0);
            productRmStockDetailsModel.setClosing_balance_weight(0);
            productRmStockDetailsModel.setOpening_quantity(0);
            productRmStockDetailsModel.setOpening_weight(0);
            productRmStockDetailsModel.setGodown_id(responseProductRmModel.getGodown_id());
            productRmStockDetailsModel.setGodown_section_id(responseProductRmModel.getGodown_section_id());
            productRmStockDetailsModel.setGodown_section_beans_id(responseProductRmModel.getGodown_section_beans_id());
            productRmStockDetailsModel.setCreated_by(responseProductRmModel.getCreated_by());
            productRmStockDetailsModel.setModified_by(responseProductRmModel.getCreated_by());
            productRmStockDetailsModel.setBatch_rate(0);

            addProductRmStockDetailsList.add(productRmStockDetailsModel);

            stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
            stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        }
        cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", company_id);

    }

    public Map<String, Object> FnStoreQRFile(MultipartFile file, String product_rm_id, String product_rm_name) {
        Map<String, Object> responce = new HashMap<>();
        try {
            String docStorePath1 = filePath + "DocStores" + File.separator;
            if (!new File(docStorePath1).exists()) {
                new File(docStorePath1).mkdir();
            }
            String docStorePath = docStorePath1 + "Raw Material" + File.separator;
            if (!new File(docStorePath).exists()) {
                new File(docStorePath).mkdir();
            }
            String doctStorePathWithId = docStorePath + product_rm_id + File.separator;
            if (!new File(doctStorePathWithId).exists()) {
                new File(doctStorePathWithId).mkdir();
            }

            File directoryPath = new File(doctStorePathWithId);
            String contents[] = directoryPath.list();
            if (contents.length != 0) {
                for (String content : contents) {
                    System.out.println("File is available");
                    File deleteExistingDoc = new File(doctStorePathWithId + content);
                    deleteExistingDoc.delete();
                }
            }
            String DocOrignalName = file.getOriginalFilename();
            String extesion = DocOrignalName.substring(DocOrignalName.lastIndexOf(".") + 0, DocOrignalName.length());
            String docName = product_rm_name + "_qrcode" + extesion;
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
    public Map<String, Object> FnDeleteRecord(String product_rm_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iProductRmRepository.deleteProductRmDetailsRecords(product_rm_id, deleted_by);
            iProductRmTechnicalRepository.deleteProductRmTechnicalRecords(product_rm_id, deleted_by);
            iProductRmCommercialRepository.deleteProductRmCommercialRecords(product_rm_id, deleted_by);
            iProductRmSupplierRepository.deleteProductRmSupplierRecords(product_rm_id, deleted_by);
            iProductRmProcessRepository.deleteProductRmProcessRecords(product_rm_id, deleted_by);
            iSmProductRmQaMappingRepository.deleteProductRmQaMappingRecords(product_rm_id, deleted_by);
            iProductDynamicParameterRepository.deleteProductDynamicParameters(product_rm_id, deleted_by);

            //Delete Stock Details from rm_stock_details & rm_stock_summary
            iSmProductRmStockSummaryRepository.deleteRmStockSummary(product_rm_id, deleted_by);
            iSmProductRmStockDetailsRepository.deleteRmStockDetails(product_rm_id, deleted_by);

            Optional<CProductRmModel> option = iProductRmRepository.findByProduct_rm_id(product_rm_id);
            CProductRmModel cSmProductRmModel = new CProductRmModel();
            if (option.isPresent()) {
                cSmProductRmModel = option.get();
                if (cSmProductRmModel.getProduct_rm_qr_code() != null) {
                    String document_path = cSmProductRmModel.getProduct_rm_qr_code();
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
    public Object FnShowAllActiveRecords(Pageable pageable) {
        JSONObject resp = new JSONObject();
        try {
            Page<CProductRmViewModel> data = iProductRmViewRepository.FnShowAllActiveRecords(pageable);
            System.out.println(data);
            resp.put("success", "1");
            resp.put("error", "");

            return new Object[]{data, resp};

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return new Object[]{"", resp};
    }

    @Override
    public JSONObject FnShowParticularRecord(int company_id, int product_rm_id) {
        JSONObject resp = new JSONObject();
        try {

            CProductRmViewModel json = iProductRmViewRepository.FnShowParticularRecord(company_id, product_rm_id);

            resp.put("success", "1");
            resp.put("data", json);
            resp.put("error", "");

            return resp;

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("data", "");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public JSONObject FnShowParticularRecordForUpdate(int company_id, int company_branch_id, int product_rm_id) {
        JSONObject resp = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            CProductRmViewModel json = iProductRmViewRepository.FnShowParticularRecordForUpdate(company_id,
                    company_branch_id, product_rm_id);
            String json2 = objectMapper.writeValueAsString(json);
            resp.put("success", "1");
            resp.put("data", json2);
            resp.put("error", "");

            return resp;

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("data", "");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public Map<String, Object> FnShowAllReportSummaryRecords(Pageable pageable, String reportType) {
        Map<String, Object> resp = new HashMap<>();
        try {
            if ("summary".equals(reportType)) {
                Page<Map<String, Object>> data = iProductRmViewRepository
                        .FnShowAllReportSummaryRecords(pageable);
                resp.put("content", data);
            } else {
                Page<Map<String, Object>> data = iProductRmViewRepository
                        .FnShowAllProductRmDetailsRptRecords(pageable);
                resp.put("content", data);
            }

            resp.put("success", "1");
            resp.put("error", "");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public Object FnShowAllProductRmmaterialSummaryViewRecords(Pageable pageable) {
        JSONObject resp = new JSONObject();
        try {
            Page<CProductRmSummaryViewModel> data = iProductRmSummaryViewRepository
                    .FnShowAllProductRmmaterialSummaryViewRecords(pageable);
            System.out.println(data);
            resp.put("success", "1");
            resp.put("error", "");

            return new Object[]{data, resp};

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return new Object[]{"", resp};
    }

    @Override
    public Object FnShowAllProductRmListViewRecords(Pageable pageable) {
        JSONObject resp = new JSONObject();
        try {
            Page<CProductRmListViewModel> data = iProductRmListViewRepository
                    .FnShowAllProductRmListViewRecords(pageable);
            System.out.println(data);
            resp.put("success", "1");
            resp.put("error", "");

            return new Object[]{data, resp};

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return new Object[]{"", resp};
    }

    @Override
    public Object FnShowAllProductRmDetailsViewRecords(Pageable pageable) {
        JSONObject resp = new JSONObject();
        try {
            Page<CProductRmDetailsViewModel> data = iProductRmDetailsViewRepository
                    .FnShowAllProductRmDetailsViewRecords(pageable);
            System.out.println(data);
            resp.put("success", "1");
            resp.put("error", "");

            return new Object[]{data, resp};

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return new Object[]{"", resp};
    }

    @Override
    public Map<String, Object> FnShowAllProductRmSummaryDetailsAndMasterRecords(String product_rm_id, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            CProductRmModel productRmDetailsRecords = iProductRmRepository
                    .FnShowParticularProductRmmaterialRecords(product_rm_id);
            // get company wise material current stock from summary table
            Tuple result = iProductRmRepository.findMaterialQuantityAndWeightById(product_rm_id, company_id);
            BigDecimal current_stock_qty = result.get("current_stock_qty", BigDecimal.class);
            BigDecimal current_stock_weight = result.get("current_stock_weight", BigDecimal.class);

            // Assuming MaterialDto is a DTO that holds quantity and weight
            productRmDetailsRecords.setOpening_qty(current_stock_qty != null ? current_stock_qty.doubleValue() : 0.0);
            productRmDetailsRecords.setOpening_weight(current_stock_weight != null ? current_stock_weight.doubleValue() : 0.0);

            Map<String, Object> productRmmaterialTechnicalRecords = iProductRmRepository
                    .FnShowParticularProductRmmaterialTechnicalRecords(product_rm_id);

            Map<String, Object> productRmmaterialCommercialRecords = iProductRmRepository
                    .FnShowParticularProductRmmaterialcommercialRecords(product_rm_id);


            List<CProductRmSupplierViewModel> productRmmaterialSupplierRecords = iProductRmSupplierViewRepository
                    .FnShowParticularRmmaterialSupplierRecords(product_rm_id);

            List<CProductRmProcessModel> productRmmaterialProcessRecords = iProductRmProcessRepository
                    .FnShowParticularRmmaterialProcessRecords(product_rm_id);

            List<CSmProductRmQaMappingModel> productRmmaterialQaMappingRecords = iSmProductRmQaMappingRepository
                    .FnShowParticularRmmaterialQaMappingRecords(product_rm_id);

            //get product_type_id from productRmDetailsRecords
            int product_type_id = productRmDetailsRecords.getProduct_type_id();

            //Fetch Product Dynamic Parameters Records
            List<CSmProductDynamicParametersViewModel> productDynamicParametersRecords = productDynamicParameterViewRepository
                    .FnShowParticularProductDynamicParametersRecords(product_type_id, product_rm_id);

            responce.put("ProductRmDetailsRecords", productRmDetailsRecords);
            responce.put("ProductRmmaterialTechnicalRecords", productRmmaterialTechnicalRecords);
            responce.put("ProductRmmaterialCommercialRecords", productRmmaterialCommercialRecords);
            responce.put("ProductRmmaterialSupplierRecords", productRmmaterialSupplierRecords);
            responce.put("ProductRmmaterialProcessRecords", productRmmaterialProcessRecords);
            responce.put("ProductRmmaterialQaMappingRecords", productRmmaterialQaMappingRecords);
            responce.put("AdditionalParametersData", productDynamicParametersRecords);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowAllRecords(int company_id, String accordianSelectKey) {
        Map<String, Object> responce = new HashMap<>();
        try {
            if (accordianSelectKey.equalsIgnoreCase("SupplierMapping")) {
                List<CSupplierViewModel> supplierAllRecords = iSupplierViewRepository.FnShowAllSupplierRecords(company_id);
                responce.put("SupplierAllRecords", supplierAllRecords);
            } else if (accordianSelectKey.equalsIgnoreCase("ProcessMapping")) {
                List<CPmProductProcessViewModel> productRmmaterialProcessRecords = iPmProductProcessViewRepository
                        .FnShowAllProductProcessRecords(company_id);
                responce.put("ProductRmmaterialProcessRecords", productRmmaterialProcessRecords);
            } else if (accordianSelectKey.equalsIgnoreCase("QAMapping")) {
                List<CProductQaParametersViewModel> productQaParametersAllRecords = iProductQaParametersViewRepository
                        .FnShowAllQaParametersRecords(company_id);
                responce.put("ProductQaParametersAllRecords", productQaParametersAllRecords);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }


}
