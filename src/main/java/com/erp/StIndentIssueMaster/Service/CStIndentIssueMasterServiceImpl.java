package com.erp.StIndentIssueMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Documents.Repository.IDocumentsRepository;
import com.erp.RawMaterial.Product_Rm.Repository.IProductRmRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.StIndentDetails.Model.CStIndentDetailsModel;
import com.erp.StIndentDetails.Model.CStIndentDetailsViewModel;
import com.erp.StIndentDetails.Repository.IStIndentDetailsRepository;
import com.erp.StIndentDetails.Repository.IStIndentDetailsViewRepository;
import com.erp.StIndentDetails.Repository.IStIndentMasterRepository;
import com.erp.StIndentIssueMaster.Model.CStIndentIssueDetailsModel;
import com.erp.StIndentIssueMaster.Model.CStIndentIssueDetailsViewModel;
import com.erp.StIndentIssueMaster.Model.CStIndentIssueMasterModel;
import com.erp.StIndentIssueMaster.Model.CStMaterialIssueBatchWiseModel;
import com.erp.StIndentIssueMaster.Repository.*;
import com.erp.XtWarpingProductionOrder.Repository.IXtWarpingProductionOrderStockDetailsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.AtomicDouble;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CStIndentIssueMasterServiceImpl implements IStIndentIssueMasterService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IStIndentIssueMasterRepository iStIndentIssueMasterRepository;

    @Autowired
    IStIndentMaterialIssueDetailRepository iStIndentMaterialIssueDetailRepository;

    @Autowired
    IStIndentDetailsViewRepository iStIndentDetailsViewRepository;

    @Autowired
    IStIndentMaterialIssueDetailViewRepository iStIndentMaterialIssueDetailViewRepository;

    @Autowired
    IStIndentMaterialIssueMasterViewRepository iStIndentMaterialIssueMasterViewRepository;

    @Autowired
    IStIndentMasterRepository iStIndentMasterRepository;

    @Autowired
    IStIndentDetailsRepository iStIndentDetailsRepository;

    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    IProductRmRepository iProductRmRepository;

    @Autowired
    IStMaterialIssueBatchWiseRepository iStMaterialIssueBatchWiseRepository;


    @Autowired
    IDocumentsRepository iDocumentsRepository;

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String acceptFLag) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        String issue_no = commonIdsObj.getString("issue_no");
        String financial_year = commonIdsObj.getString("financial_year");
        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsarray = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray materialIssueBatchWiseArray = (JSONArray) jsonObject.get("TransIssueBatchWiseData");

        try {
            //Get Indent Material Issue Master Data
            CStIndentIssueMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
                    CStIndentIssueMasterModel.class);

            if (acceptFLag.equals("addOrUpdate")) {
                if (!jsonModel.getIssue_status().equalsIgnoreCase("A")) {
                    CStIndentIssueMasterModel getExistingRecord = iStIndentIssueMasterRepository.getExistingRecord(
                            issue_no, jsonModel.getIssue_version(), financial_year, company_id);

                    if (getExistingRecord != null) {
//                    update = true;
                        getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
                        getExistingRecord.setDeleted_on(new Date());
                        getExistingRecord.setIs_delete(true);
                        iStIndentIssueMasterRepository.save(getExistingRecord);
                        jsonModel.setIssue_master_transaction_id(0);
                        jsonModel.setIssue_version(getExistingRecord.getIssue_version() + 1);

                        String issueNo = commonIdsObj.getString("issue_no").replaceAll("/", "_");
                        List<String> groupIds = Collections.singletonList(issueNo);
                        iDocumentsRepository.updateDocActive(groupIds);
                    }
                }
                CStIndentIssueMasterModel responseIndentIssueMaster = iStIndentIssueMasterRepository.save(jsonModel); // upadted master status save
                responce.put("data", responseIndentIssueMaster);

                List<CStIndentIssueDetailsModel> indentIssueDetails = null;
                //Get Indent Material Issue Detail Array
                if (!detailsarray.isEmpty()) {
                    indentIssueDetails = objectMapper.readValue(detailsarray.toString(),
                            new TypeReference<List<CStIndentIssueDetailsModel>>() {
                            });
                    indentIssueDetails.forEach(items -> {
                        if (!responseIndentIssueMaster.getIssue_status().equalsIgnoreCase("A")) {
                            items.setIssue_version(jsonModel.getIssue_version());
                        }
                        items.setIssue_master_transaction_id(responseIndentIssueMaster.getIssue_master_transaction_id());
                    });
                    iStIndentMaterialIssueDetailRepository.saveAll(indentIssueDetails);

                    List<CStMaterialIssueBatchWiseModel> issueBatchWiseDetailsModel = objectMapper.readValue(materialIssueBatchWiseArray.toString(),
                            new TypeReference<List<CStMaterialIssueBatchWiseModel>>() {
                            });
                    iStMaterialIssueBatchWiseRepository.saveAll(issueBatchWiseDetailsModel);
                }
                responce.put("message", responseIndentIssueMaster.getIssue_status().equalsIgnoreCase("A") ? "Record approved successfully!" : "Record added successfully!");

            } else {
                String issueNo = commonIdsObj.getString("issue_no").replaceAll("/", "_");
                List<String> groupIds = Collections.singletonList(issueNo);
                iDocumentsRepository.updateDocActive(groupIds);

                CStIndentIssueMasterModel responseIndentIssueMaster = iStIndentIssueMasterRepository.save(jsonModel); // upadted master status save
                responce.put("data", responseIndentIssueMaster);

                //Get Indent Material Issue Detail Array
                if (!detailsarray.isEmpty()) {
                    List<CStIndentIssueDetailsModel> indentIssueDetailsModel = objectMapper.readValue(detailsarray.toString(),
                            new TypeReference<List<CStIndentIssueDetailsModel>>() {
                            });

                    iStIndentMaterialIssueDetailRepository.saveAll(indentIssueDetailsModel);   // save indent issue details


                    List<CStMaterialIssueBatchWiseModel> issueBatchWiseDetailsModel = objectMapper.readValue(materialIssueBatchWiseArray.toString(),
                            new TypeReference<List<CStMaterialIssueBatchWiseModel>>() {
                            });
                    iStMaterialIssueBatchWiseRepository.saveAll(issueBatchWiseDetailsModel);

                    Map<String, Object> batchresponse = new HashMap<>();
                    String issueDateString = responseIndentIssueMaster.getIssue_date();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust pattern based on actual format
                    LocalDate issue_date = LocalDate.parse(issueDateString, formatter);
                    // Get today's date
                    LocalDate todayDate = LocalDate.now();

//                   If issue request is for Raw Material
                    if (jsonModel.getIndent_issue_type_id() == 12) {
                        // Group entries by goods_receipt_no and product_material_id
                        Map<String, CStIndentIssueDetailsModel> groupedDetails = indentIssueDetailsModel.stream()
                                .collect(Collectors.toMap(
                                        item -> item.getIssue_batch_no() + "_" + item.getProduct_material_id(), // Composite key
                                        item -> item,
                                        (existing, newItem) -> {
                                            // Combine existing and new items by summing quantities and weights
                                            existing.setProduct_material_issue_quantity(
                                                    existing.getProduct_material_issue_quantity() + newItem.getProduct_material_issue_quantity());
                                            existing.setProduct_material_issue_weight(
                                                    existing.getProduct_material_issue_weight() + newItem.getProduct_material_issue_weight());
                                            existing.setProduct_material_issue_boxes(
                                                    existing.getProduct_material_issue_boxes() + newItem.getProduct_material_issue_boxes());
                                            return existing;
                                        }
                                ));

                        // Replace the original list with the summed entries
                        List<CStIndentIssueDetailsModel> consolidatedIssueList = new ArrayList<>(groupedDetails.values());

                        // Pass consolidated list to FnUpdateRawMaterialStockDetails
                        batchresponse = FnUpdateRawMaterialStockDetails(responseIndentIssueMaster, issueBatchWiseDetailsModel, company_id);

                     } else {
                        batchresponse = FnUpdateProductStockDetails(indentIssueDetailsModel, company_id); // STOCK ISSUE
                    }
                    if (issue_date.isBefore(todayDate)) {
                        // Retrieve issuedBatchList from response
                        List<Map<String, Object>> issuedBatchList = (List<Map<String, Object>>) batchresponse.get("issuedBatchList");
                        if (!issuedBatchList.isEmpty()) {
                            System.out.println("selected previous date " + issue_date);
                            responce.put("issuedBatchList", issuedBatchList);
                        }
                    }
                    // update indent details and status
                    List<String> getDistinctIndentNos = indentIssueDetailsModel.stream().map(CStIndentIssueDetailsModel::getIndent_no).distinct().collect(Collectors.toList());
                    if (!getDistinctIndentNos.isEmpty()) {
                        List<CStIndentDetailsModel> cStIndentDetailsModels = iStIndentDetailsRepository.getIndentDetails(getDistinctIndentNos, company_id);
                        List<CStIndentDetailsModel> updateIndentDetails = new ArrayList<>();


                        // Retrieve the previous list of Indent Issue details based on Indent nos
                        List<CStIndentIssueDetailsViewModel> existingIndentIssueDetails = iStIndentMaterialIssueDetailViewRepository
                                .getAllExistingIndentDetails(getDistinctIndentNos, company_id);

                        // Create a set of distinct composite keys from the Indent list details where the condition is met
                        Set<String> distinctKeys = existingIndentIssueDetails.stream()
                                .map(issueItem -> issueItem.getProduct_material_id() + ":" + issueItem.getIndent_no())
                                .collect(Collectors.toSet());

                        // Create a map with the distinct composite keys
                        Map<String, Boolean> indentIssueDetailsMap = distinctKeys.stream()
                                .collect(Collectors.toMap(
                                        key -> key,
                                        key -> true
                                ));

                        // Filter purchase order details to only include those not in the goods receipt details map
                        List<CStIndentDetailsModel> filteredPurchaseDetails = cStIndentDetailsModels.stream()
                                .filter(indent -> !indentIssueDetailsMap.containsKey(indent.getProduct_material_id() + ":" + indent.getIndent_no()))
                                .collect(Collectors.toList());


                        indentIssueDetailsModel.forEach(issueRecord -> {

                            Optional<CStIndentDetailsModel> getMatchingIndentObj = cStIndentDetailsModels.stream()
                                    .filter(item -> item.getIndent_details_id() == issueRecord.getIndent_details_id()).findFirst();

                            if (getMatchingIndentObj.isPresent()) {
                                CStIndentDetailsModel indentDetailsModel = getMatchingIndentObj.get();
                                indentDetailsModel.setProduct_material_issue_quantity(indentDetailsModel.getProduct_material_issue_quantity() + issueRecord.getProduct_material_issue_quantity());
                                indentDetailsModel.setProduct_material_issue_weight(indentDetailsModel.getProduct_material_issue_weight() + issueRecord.getProduct_material_issue_weight());

                                if (indentDetailsModel.getProduct_material_approved_quantity() == indentDetailsModel.getProduct_material_issue_quantity()) {
                                    indentDetailsModel.setIssue_item_status("MI");//complete material issue
                                } else {
                                    indentDetailsModel.setIssue_item_status("I");//partial issue
                                }
                                updateIndentDetails.add(indentDetailsModel);
                            }
                        });

                        // Save updated indent details
                        List<CStIndentDetailsModel> updatedIndentDetailsModels;
                        if (!updateIndentDetails.isEmpty()) {
                            updatedIndentDetailsModels = iStIndentDetailsRepository.saveAll(updateIndentDetails);
                            System.out.println("Issue quantity updated in Indent Details  : " + updatedIndentDetailsModels);
                        } else {
                            updatedIndentDetailsModels = new ArrayList<>();
                        }

                        // Calculate totals and update indent master status
                        getDistinctIndentNos.forEach(indent_no -> {
                            AtomicDouble totalIndentApprovedQty = new AtomicDouble();
                            AtomicDouble totalIndentIssueQty = new AtomicDouble();
                            AtomicInteger indent_master_id = new AtomicInteger();

                            List<CStIndentDetailsModel> detailsModels = updatedIndentDetailsModels.stream()
                                    .filter(item -> item.getIndent_no().equals(indent_no))
                                    .collect(Collectors.toList());

                            detailsModels.forEach(indentItem -> {
                                totalIndentApprovedQty.getAndAdd(indentItem.getProduct_material_approved_quantity());
                                totalIndentIssueQty.getAndAdd(indentItem.getProduct_material_issue_quantity());
                                indent_master_id.set(indentItem.getIndent_master_id());
                            });
                            double remainingQty = totalIndentApprovedQty.get() - totalIndentIssueQty.get();
                            // check all approved and issued qty is same then update issue status of indent master as Material Issue
                            if (remainingQty > 0 || !filteredPurchaseDetails.isEmpty()) {
                                iStIndentMasterRepository.updateIndentMasterByIssue("I", indent_master_id.get(), company_id);
                            } else {
                                iStIndentMasterRepository.updateIndentMasterByIssue("MI", indent_master_id.get(), company_id);
                            }
                        });
                    }
                    responce.put("message", responseIndentIssueMaster.getIssue_status().equalsIgnoreCase("MI") ? "Record Issued Successfully!" : "");
                }
            }
            responce.put("success", "1");
            responce.put("error", "");
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/StIndentIssueMaster/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/StIndentIssueMaster/FnAddUpdateRecord",
                    0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }


    private Map<String, Object> FnUpdateRawMaterialStockDetails(CStIndentIssueMasterModel responseIndentIssueMaster, List<CStMaterialIssueBatchWiseModel> indentIssueDetails, int company_id) {
        Map<String, Object> stockDetails = new HashMap<>();

        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);

        // Get distinct material ids
        List<String> distinctMaterialIds = indentIssueDetails.stream().map(CStMaterialIssueBatchWiseModel::getProduct_material_id).distinct()
                .collect(Collectors.toList());

        Map<String, Double> distinctBatchWithWeights = indentIssueDetails.stream()
                .collect(Collectors.toMap(
                        CStMaterialIssueBatchWiseModel::getBatch_no,  // Key: issue_batch_no
                        CStMaterialIssueBatchWiseModel::getCone_per_wt, // Value: cone_per_weight
                        (existing, replacement) -> existing // Merge function to handle duplicates
                ));

        // Extract batch numbers and weights
        List<String> batchNumbers = new ArrayList<>(distinctBatchWithWeights.keySet());
        List<Double> weights = new ArrayList<>(distinctBatchWithWeights.values());

//		Get the data from sm_product_rm_stock_details table for stock issue
        List<CSmProductRmStockDetailsModel> productStockDetailsList = !distinctMaterialIds.isEmpty() ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetailsRawMaterialsLotwise(distinctMaterialIds, company_id, batchNumbers) : null;

        // iterate on indent materials for stock issue
        indentIssueDetails.forEach(indentRecord -> {

            //Get only specific material stock
            List<CSmProductRmStockDetailsModel> getDistinctMaterialWiseStock = productStockDetailsList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(indentRecord.getProduct_material_id())
                            && Objects.equals(item.getCompany_id(), company_id)
                            && Objects.equals(item.getGodown_id(), indentRecord.getGodown_id())
                            && Objects.equals(item.getGoods_receipt_no(), indentRecord.getGoods_receipt_no())
                            && item.getClosing_balance_quantity() > 0
                    )
                    .collect(Collectors.toList());
            System.out.println("Filtered List: " + getDistinctMaterialWiseStock);

            AtomicReference<Double> issue_quantity = new AtomicReference<>(indentRecord.getIssue_quantity());
            AtomicReference<Double> issue_weight = new AtomicReference<>(indentRecord.getIssue_weight());
            AtomicReference<Integer> issue_box = new AtomicReference<>(indentRecord.getRequisition_no_boxes());
            AtomicReference<Double> reducedQty = new AtomicReference<>();
            AtomicReference<Double> reducedWt = new AtomicReference<>();
            AtomicReference<Integer> reducedBox = new AtomicReference<>();

            // indent no present so check grn no
            getDistinctMaterialWiseStock.forEach(stockRecord -> {
                if (issue_quantity.get() > 0) {
                    double available_stock_quantity = stockRecord.getClosing_balance_quantity();
                    double available_stock_weight = stockRecord.getClosing_balance_weight();
                    Integer available_stock_Boxes = stockRecord.getClosing_no_of_boxes();

                    reducedQty.set(Math.min(issue_quantity.get(), available_stock_quantity));
                    reducedWt.set(Math.min(issue_weight.get(), available_stock_weight));
                    reducedBox.set(Math.min(issue_box.get(), available_stock_Boxes));

                    issue_quantity.set(issue_quantity.get() - reducedQty.get());
                    issue_weight.set(issue_weight.get() - reducedWt.get());
                    issue_box.set(issue_box.get() - reducedBox.get());

                    CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();
                    CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

//					if object is present in details then just increase the qty of existing object
                    Optional<CSmProductRmStockDetailsModel> getDetailsObject = addProductRmStockDetailsList.stream()
                            .filter(item -> item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                            Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                                            && item.getBatch_no().equals(stockRecord.getBatch_no())
                                            && item.getGoods_receipt_no().equals(stockRecord.getGoods_receipt_no())
                            ).findFirst();

                    if (getDetailsObject.isPresent()) {
                        productRmStockDetailsModel = getDetailsObject.get();
                        productRmStockDetailsModel.setClosing_balance_quantity(productRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        productRmStockDetailsModel.setClosing_balance_weight(productRmStockDetailsModel.getClosing_balance_weight() + (-reducedWt.get()));
                        productRmStockDetailsModel.setClosing_no_of_boxes(productRmStockDetailsModel.getClosing_no_of_boxes() + (-reducedBox.get()));
                        productRmStockDetailsModel.setProduction_issue_quantity(productRmStockDetailsModel.getProduction_issue_quantity() + reducedQty.get());
                        productRmStockDetailsModel.setProduction_issue_weight(productRmStockDetailsModel.getProduction_issue_weight() + reducedWt.get());
                        productRmStockDetailsModel.setIssue_no_of_boxes(productRmStockDetailsModel.getIssue_no_of_boxes() + reducedBox.get());

                        productRmStockDetailsModel.setStock_date(indentRecord.getTransaction_date());
                        if (indentRecord.getIndent_no() != null && !indentRecord.getIndent_no().equalsIgnoreCase("")) {
                            productRmStockDetailsModel.setReserve_quantity(-reducedQty.get());
                            productRmStockDetailsModel.setReserve_weight(-reducedWt.get());
                            productRmStockDetailsModel.setGoods_receipt_no(indentRecord.getGoods_receipt_no());
                        }
                    } else {
                        productRmStockDetailsModel.setStock_date(indentRecord.getTransaction_date());
                        productRmStockDetailsModel.setClosing_balance_quantity(-reducedQty.get());
                        productRmStockDetailsModel.setClosing_balance_weight(-reducedWt.get());
                        productRmStockDetailsModel.setClosing_no_of_boxes(-reducedBox.get());
                        productRmStockDetailsModel.setProduction_issue_quantity(reducedQty.get());
                        productRmStockDetailsModel.setProduction_issue_weight(reducedWt.get());
                        productRmStockDetailsModel.setIssue_no_of_boxes(reducedBox.get());
                        productRmStockDetailsModel.setFinancial_year(indentRecord.getFinancial_year());
                        productRmStockDetailsModel.setGoods_receipt_no(stockRecord.getGoods_receipt_no());
                        productRmStockDetailsModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        productRmStockDetailsModel.setProduct_material_unit_id((indentRecord.getProduct_material_unit_id()));
                        productRmStockDetailsModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        productRmStockDetailsModel.setGodown_id(stockRecord.getGodown_id());
                        productRmStockDetailsModel.setGodown_section_id(stockRecord.getGodown_section_id());
                        productRmStockDetailsModel.setGodown_section_beans_id(stockRecord.getGodown_section_beans_id());
                        productRmStockDetailsModel.setSupplier_id(stockRecord.getSupplier_id());
                        productRmStockDetailsModel.setWeight_per_box_item(indentRecord.getCone_per_wt());
                        productRmStockDetailsModel.setBatch_no(stockRecord.getBatch_no());
                        productRmStockDetailsModel.setCreated_by(indentRecord.getCreated_by());
                        productRmStockDetailsModel.setModified_by(indentRecord.getModified_by());
                        productRmStockDetailsModel.setCompany_id(company_id);
                        productRmStockDetailsModel.setCompany_branch_id(indentRecord.getCompany_branch_id());
                        if (indentRecord.getIndent_no() != null && !indentRecord.getIndent_no().equalsIgnoreCase("")) {
                            productRmStockDetailsModel.setReserve_quantity(-reducedQty.get());
                            productRmStockDetailsModel.setReserve_weight(-reducedWt.get());
                            productRmStockDetailsModel.setGoods_receipt_no(indentRecord.getGoods_receipt_no());
                        }
                    }
                    addProductRmStockDetailsList.add(productRmStockDetailsModel);

                    // ADD THE STOCK IN WEAVING GODOWN FOR sm_product_rm_stock_details table only if set no will present
                     int subDepartmentIds = responseIndentIssueMaster.getSub_department_id();

                    List<Object[]> godownIds = iSmProductRmStockDetailsRepository.getGodownIdAndSubDPt(subDepartmentIds);

                    Map<List<Integer>, Integer> subDepartmentMappings = godownIds.stream()
                            .collect(Collectors.toMap(
                                    row -> Collections.singletonList(((BigInteger) row[0]).intValue()), // Convert BigInteger to Integer and wrap in List
                                    row -> ((BigInteger) row[1]).intValue() // Convert BigInteger to Integer
                            ));

                    if (subDepartmentIds != 0) {

                        CSmProductRmStockDetailsModel productRmStockDetailsModelForWeaving = new CSmProductRmStockDetailsModel();
                        productRmStockDetailsModelForWeaving.setCompany_id(responseIndentIssueMaster.getCompany_id());
                        productRmStockDetailsModelForWeaving.setCompany_branch_id(responseIndentIssueMaster.getCompany_branch_id());
                        productRmStockDetailsModelForWeaving.setFinancial_year(indentRecord.getFinancial_year());
                        productRmStockDetailsModelForWeaving.setProduct_type_group("PRM");
                        productRmStockDetailsModelForWeaving.setStock_date(todayDate);
                        productRmStockDetailsModelForWeaving.setGoods_receipt_no(indentRecord.getGoods_receipt_no() +
                                (responseIndentIssueMaster.getSet_no() != null && !responseIndentIssueMaster.getSet_no().trim().isEmpty() ? "-" + responseIndentIssueMaster.getSet_no() : "")
                        );

                        productRmStockDetailsModelForWeaving.setProduct_rm_id(indentRecord.getProduct_material_id());
                        productRmStockDetailsModelForWeaving.setProduct_type_id(responseIndentIssueMaster.getIndent_issue_type_id());
                        productRmStockDetailsModelForWeaving.setClosing_balance_quantity(indentRecord.getIssue_quantity());
                        productRmStockDetailsModelForWeaving.setClosing_balance_weight(indentRecord.getIssue_weight());
                        productRmStockDetailsModelForWeaving.setClosing_no_of_boxes(indentRecord.getIssue_no_boxes());
                        productRmStockDetailsModelForWeaving.setBatch_no(indentRecord.getBatch_no());
                        productRmStockDetailsModelForWeaving.setSupplier_id(stockRecord.getSupplier_id());
                        productRmStockDetailsModelForWeaving.setWeight_per_box_item(indentRecord.getCone_per_wt());
                        productRmStockDetailsModelForWeaving.setBatch_rate(stockRecord.getBatch_rate());
                        productRmStockDetailsModelForWeaving.setProduct_material_unit_id(indentRecord.getProduct_material_unit_id());

                        // Determine and set values based on sub_department_id
                        int subDepartmentId = responseIndentIssueMaster.getSub_department_id();

                        // Check if the subDepartmentId exists in the dynamic subDepartmentMappings
                        Optional<Integer> godownMapping = subDepartmentMappings.entrySet().stream()
                                .filter(entry -> entry.getKey().contains(subDepartmentId)) // Check if subDepartmentId matches any key
                                .map(Map.Entry::getValue) // Get the corresponding godownId
                                .findFirst();
                        godownMapping.ifPresent(value -> {
                            productRmStockDetailsModelForWeaving.setGodown_section_beans_id(value);
                            productRmStockDetailsModelForWeaving.setGodown_id(value);
                            productRmStockDetailsModelForWeaving.setGodown_section_id(value);
                        });

                        addProductRmStockDetailsList.add(productRmStockDetailsModelForWeaving);

                        // ADD THE STOCK IN WEAVING GODOWN FOR sm_product_rm_stock_summary table
                        CSmProductRmStockSummaryModel productRmStockSummaryModelForWeaving = new CSmProductRmStockSummaryModel();

                        Optional<CSmProductRmStockSummaryModel> getSummaryObjectForWeaving = addProductRmStockSummaryList.stream()
                                .filter(item -> item.getProduct_rm_id().equals(indentRecord.getProduct_material_id()) &&
                                        Objects.equals(indentRecord.getGodown_id(), item.getGodown_id()) &&
                                        Objects.equals(indentRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                        Objects.equals(indentRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                                ).findFirst();

                        if (getSummaryObjectForWeaving.isPresent()) {
                            CSmProductRmStockSummaryModel getProductRmStockSummaryModel = getSummaryObjectForWeaving.get();
                            productRmStockSummaryModel.setClosing_balance_quantity(getProductRmStockSummaryModel.getClosing_balance_quantity() + (reducedQty.get()));
                            productRmStockSummaryModel.setClosing_balance_weight(getProductRmStockSummaryModel.getClosing_balance_weight() + (reducedWt.get()));
                            productRmStockSummaryModel.setClosing_no_of_boxes(getProductRmStockSummaryModel.getClosing_no_of_boxes() + (indentRecord.getIssue_no_boxes()));
                        } else {
                            productRmStockSummaryModelForWeaving.setCompany_id(indentRecord.getCompany_id());
                            productRmStockSummaryModelForWeaving.setCompany_branch_id(indentRecord.getCompany_branch_id());
                            productRmStockSummaryModelForWeaving.setProduct_type_id(responseIndentIssueMaster.getIndent_issue_type_id());
                            productRmStockSummaryModelForWeaving.setProduct_type_group("PRM");
                            productRmStockSummaryModelForWeaving.setFinancial_year(indentRecord.getFinancial_year());
                            productRmStockSummaryModelForWeaving.setClosing_balance_quantity(reducedQty.get());
                            productRmStockSummaryModelForWeaving.setClosing_balance_weight(reducedWt.get());
                            productRmStockSummaryModelForWeaving.setClosing_no_of_boxes(indentRecord.getIssue_no_boxes());
                            productRmStockSummaryModelForWeaving.setProduct_rm_id(indentRecord.getProduct_material_id());
                            productRmStockSummaryModelForWeaving.setProduct_material_unit_id(indentRecord.getProduct_material_unit_id());
                            productRmStockSummaryModelForWeaving.setProduct_material_packing_id(indentRecord.getProduct_material_unit_id());
                            productRmStockSummaryModelForWeaving.setModified_by(indentRecord.getCreated_by());
                            productRmStockSummaryModelForWeaving.setProduct_rm_id(indentRecord.getProduct_material_id());
                            productRmStockSummaryModelForWeaving.setWeight_per_box_item(indentRecord.getCone_per_wt());

                            godownMapping.ifPresent(value -> {
                                productRmStockSummaryModelForWeaving.setGodown_section_beans_id(value);
                                productRmStockSummaryModelForWeaving.setGodown_id(value);
                                productRmStockSummaryModelForWeaving.setGodown_section_id(value);
                            });
                            addProductRmStockSummaryList.add(productRmStockSummaryModelForWeaving);
                        }
                    }
//					if object is present in summary then just increase the qty of existing object
                    Optional<CSmProductRmStockSummaryModel> getSummaryObject = addProductRmStockSummaryList.stream()
                            .filter(item -> item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                    Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                            ).findFirst();

                    if (getSummaryObject.isPresent()) {
                        CSmProductRmStockSummaryModel getProductRmStockSummaryModel = getSummaryObject.get();
                        productRmStockSummaryModel.setClosing_balance_quantity(getProductRmStockSummaryModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        productRmStockSummaryModel.setClosing_balance_weight(getProductRmStockSummaryModel.getClosing_balance_weight() + (-reducedWt.get()));
                        productRmStockSummaryModel.setClosing_no_of_boxes(getProductRmStockSummaryModel.getClosing_no_of_boxes() + (-reducedBox.get()));
                        productRmStockSummaryModel.setProduction_issue_quantity(getProductRmStockSummaryModel.getProduction_issue_quantity() + reducedQty.get());
                        productRmStockSummaryModel.setProduction_issue_weight(getProductRmStockSummaryModel.getProduction_issue_weight() + reducedWt.get());
                        productRmStockSummaryModel.setIssue_no_of_boxes(getProductRmStockSummaryModel.getIssue_no_of_boxes() + reducedBox.get());
                        if (indentRecord.getIndent_no() != null && !indentRecord.getIndent_no().equalsIgnoreCase("")) {
                            productRmStockSummaryModel.setReserve_quantity(-reducedQty.get());
                            productRmStockSummaryModel.setReserve_weight(-reducedWt.get());
                        }
                    } else {
                        productRmStockSummaryModel.setCompany_id(indentRecord.getCompany_id());
                        productRmStockSummaryModel.setCompany_branch_id(indentRecord.getCompany_branch_id());
                        productRmStockSummaryModel.setFinancial_year(indentRecord.getFinancial_year());
                        productRmStockSummaryModel.setClosing_balance_quantity(-reducedQty.get());
                        productRmStockSummaryModel.setClosing_balance_weight(-reducedWt.get());
                        productRmStockSummaryModel.setClosing_no_of_boxes(-reducedBox.get());
                        productRmStockSummaryModel.setProduction_issue_quantity(reducedQty.get());
                        productRmStockSummaryModel.setProduction_issue_weight(reducedWt.get());
                        productRmStockSummaryModel.setIssue_no_of_boxes(reducedBox.get());
                        productRmStockSummaryModel.setProduct_rm_id(indentRecord.getProduct_material_id());
                        productRmStockSummaryModel.setProduct_material_unit_id(indentRecord.getProduct_material_unit_id());
                        productRmStockSummaryModel.setProduct_material_packing_id(indentRecord.getProduct_material_unit_id());
                        productRmStockSummaryModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        productRmStockSummaryModel.setModified_by(indentRecord.getCreated_by());
                        productRmStockSummaryModel.setGodown_id(productRmStockDetailsModel.getGodown_id());
                        productRmStockSummaryModel.setGodown_section_id(productRmStockDetailsModel.getGodown_section_id());
                        productRmStockSummaryModel.setGodown_section_beans_id(productRmStockDetailsModel.getGodown_section_beans_id());
                        if (indentRecord.getIndent_no() != null && !indentRecord.getIndent_no().equalsIgnoreCase("")) {
                            productRmStockSummaryModel.setReserve_quantity(-reducedQty.get());
                            productRmStockSummaryModel.setReserve_weight(-reducedWt.get());
                        }
                        addProductRmStockSummaryList.add(productRmStockSummaryModel);
                    }
                    iProductRmRepository.updateMaterialIssueRate(stockRecord.getBatch_rate(), indentRecord.getProduct_material_id(), company_id, indentRecord.getTransaction_no()); // update in issue of opening balance
                } else {
                    return;
                }
            });
        });
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);

        Map<String, Object> batchresponse = cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Decrease", company_id);
        return batchresponse;
    }

//	private void FnReverseStock(List<CStIndentIssueDetailsModel> indentIssueDetailsModel, int company_id) {
//
//		Map<String, Object> stockDetails = new HashMap<>();
//
//		List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
//		List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
//		List<CSmProductStockTracking> addStockTracking = new ArrayList<>();
//
//
////		Get distinct material ids
//		List<String> distinctMaterialIds = indentIssueDetailsModel.stream().map(CStIndentIssueDetailsModel::getProduct_material_id).distinct()
//				.collect(Collectors.toList());
//
//
////		Get the data from sm_product_rm_stock_details table for stock issue
//		List<CSmProductRmStockDetailsModel> productStockDetailsList = distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetailsRawMaterials(distinctMaterialIds, company_id) : null;
//
//		indentIssueDetailsModel.forEach(indentIssueRecord -> {
//			double remainingQty = Math.min(indentIssueRecord.getProduct_material_issue_quantity(), indentIssueRecord.getProduct_material_receipt_quantity());
//
//			double remainingWt = Math.min(indentIssueRecord.getProduct_material_issue_weight(), indentIssueRecord.getProduct_material_receipt_weight());
//
//			String material_id = indentIssueRecord.getProduct_material_id();
//
//			String indent_no = indentIssueRecord.getIndent_no();
//
//			String issue_batch_no = indentIssueRecord.getIssue_batch_no();
//
//			String[] splitBatchNo;
//
////          Check if the issue batch number contains a colon
//			if (issue_batch_no.contains(":")) {
//				// If it contains a colon, split it
//				splitBatchNo = issue_batch_no.split(":");
//			} else {
//				// Otherwise, directly get the batch number
//				splitBatchNo = new String[]{issue_batch_no};
//			}
//
//			Arrays.stream(splitBatchNo).forEach(batchNo -> {
//				Optional<CSmProductRmStockDetailsModel> rmStockDetailsModel = productStockDetailsList.parallelStream()
//						.filter(item -> item.getProduct_rm_id().equals(material_id) && item.getBatch_no().equals(batchNo)
//								&& item.getGodown_id().equals(Integer.parseInt(indentIssueRecord.getGodown_id()))).findFirst();
//
//				if (rmStockDetailsModel.isPresent()) {
//					CSmProductRmStockDetailsModel stockDetail = rmStockDetailsModel.get();
//
//					stockDetail.setProduction_issue_return_quantity(remainingQty);
//					stockDetail.setProduction_issue_return_weight(remainingWt);
//					stockDetail.setClosing_balance_quantity(remainingQty);
//					stockDetail.setClosing_balance_weight(remainingWt);
//
//					addProductRmStockDetailsList.add(stockDetail);  // Add updated object into stockDetail
//
//					CSmProductRmStockSummaryModel cSmProductRmStockSummaryModel = new CSmProductRmStockSummaryModel();
//
//					cSmProductRmStockSummaryModel.setProduct_rm_id(material_id);
//					cSmProductRmStockSummaryModel.setGodown_id(stockDetail.getGodown_id());
//					cSmProductRmStockSummaryModel.setGodown_section_id(stockDetail.getGodown_section_id());
//					cSmProductRmStockSummaryModel.setGodown_section_beans_id(stockDetail.getGodown_section_beans_id());
//					cSmProductRmStockSummaryModel.setProduction_issue_return_quantity(remainingQty);
//					cSmProductRmStockSummaryModel.setProduction_issue_return_weight(remainingWt);
//					cSmProductRmStockSummaryModel.setClosing_balance_quantity(remainingQty);
//					cSmProductRmStockSummaryModel.setClosing_balance_weight(remainingWt);
//
//					addProductRmStockSummaryList.add(cSmProductRmStockSummaryModel);  // Add updated object into stock summary
//
//					CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();
//
//					cSmProductStockTracking.setCompany_id(indentIssueRecord.getCompany_id());
//					cSmProductStockTracking.setCompany_branch_id(indentIssueRecord.getCompany_branch_id());
//					cSmProductStockTracking.setFinancial_year(indentIssueRecord.getFinancial_year());
//					cSmProductStockTracking.setProduct_material_id(indentIssueRecord.getProduct_material_id());
//					cSmProductStockTracking.setGoods_receipt_no(stockDetail.getGoods_receipt_no());
//					cSmProductStockTracking.setStock_quantity(remainingQty);
//					cSmProductStockTracking.setCreated_by(indentIssueRecord.getCreated_by());
//					cSmProductStockTracking.setStockAddOrConsume(true);
//
//					addStockTracking.add(cSmProductStockTracking);  // Add into stock tracking list
//
//
//				}
//
//			});
//
//		});
//
//		stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
//		stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
//		stockDetails.put("StockTracking", addStockTracking);
//
//		cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", company_id);
//
//	}

    private Map<String, Object> FnUpdateProductStockDetails(List<CStIndentIssueDetailsModel> indentIssueDetails, int company_id) {

        Map<String, Object> stockDetails = new HashMap<>();
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addStockTracking = new ArrayList<>();

//		Get distinct material ids
        List<String> distinctMaterialIds = indentIssueDetails.stream().map(CStIndentIssueDetailsModel::getProduct_material_id).distinct()
                .collect(Collectors.toList());

//		Get the data from sm_product_rm_stock_details table for stock issue
        List<CSmProductRmStockDetailsModel> productStockDetailsList = !distinctMaterialIds.isEmpty() ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetailsRawMaterials(distinctMaterialIds, company_id) : null;

//		iterate on indent materials for stock issue
        indentIssueDetails.forEach(indentRecord -> {
            // Default the average rate to the material rate from detailItem
//			double averageRate =  iSmProductRmStockDetailsRepository.getMaterialRate( indentRecord.getProduct_material_id(), company_id);


            AtomicReference<Double> issue_quantity = new AtomicReference<>(indentRecord.getProduct_material_issue_quantity());
            AtomicReference<Double> issue_weight = new AtomicReference<>(indentRecord.getProduct_material_issue_weight());

            String[] splitGodowns = indentRecord.getGodown_id().split(",");
            int[] getGodowns = Arrays.stream(splitGodowns)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            AtomicReference<Double> totalQuantity = new AtomicReference<>(0.0);
            AtomicReference<Double> totalAmount = new AtomicReference<>(0.0);

            AtomicReference<Double> reducedQty = new AtomicReference<>();
            AtomicReference<Double> reducedWt = new AtomicReference<>();

            CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

//			Get only specific material stock
            List<CSmProductRmStockDetailsModel> getDistinctMaterialWiseStock = productStockDetailsList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(indentRecord.getProduct_material_id())
                            && item.getClosing_balance_quantity() > 0
                            && IntStream.of(getGodowns).anyMatch(gdItem -> gdItem == item.getGodown_id())
                            && (indentRecord.getIndent_no() != null && !indentRecord.getIndent_no().isEmpty()
                            ? Objects.equals(item.getGoods_receipt_no(), indentRecord.getGoods_receipt_no())
                            : true)
                    )
                    .collect(Collectors.toList());
            // indent no present so check grn no
            getDistinctMaterialWiseStock.forEach(stockRecord -> {
                if (issue_quantity.get() > 0) {
                    double available_stock_quantity = stockRecord.getClosing_balance_quantity();

                    double available_stock_weight = stockRecord.getClosing_balance_weight();
                    Integer available_stock_box = stockRecord.getClosing_no_of_boxes();

                    reducedQty.set(Math.min(issue_quantity.get(), available_stock_quantity));

                    reducedWt.set(Math.min(issue_weight.get(), available_stock_weight));

                    issue_quantity.set(issue_quantity.get() - reducedQty.get());
                    issue_weight.set(issue_weight.get() - reducedWt.get());

                    CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

                    BeanUtils.copyProperties(stockRecord, productRmStockDetailsModel);

//					if object is present in details then just increase the qty of existing object
                    Optional<CSmProductRmStockDetailsModel> getDetailsObject = addProductRmStockDetailsList.stream()
                            .filter(item -> item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                    Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                                    && item.getGoods_receipt_no().equals(stockRecord.getGoods_receipt_no())
                            ).findFirst();

                    if (getDetailsObject.isPresent()) {
                        productRmStockDetailsModel = getDetailsObject.get();
                        productRmStockDetailsModel.setClosing_balance_quantity(productRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        productRmStockDetailsModel.setClosing_balance_weight(productRmStockDetailsModel.getClosing_balance_weight() + (-reducedWt.get()));
                        productRmStockDetailsModel.setProduction_issue_quantity(productRmStockDetailsModel.getProduction_issue_quantity() + reducedQty.get());
                        productRmStockDetailsModel.setProduction_issue_weight(productRmStockDetailsModel.getProduction_issue_weight() + reducedWt.get());

                        productRmStockDetailsModel.setStock_date(indentRecord.getIssue_date());
                        if (indentRecord.getIndent_no() != null && !indentRecord.getIndent_no().equalsIgnoreCase("")) {
                            productRmStockDetailsModel.setReserve_quantity(-reducedQty.get());
                            productRmStockDetailsModel.setReserve_weight(-reducedWt.get());
                            productRmStockDetailsModel.setGoods_receipt_no(indentRecord.getGoods_receipt_no());
                        }
                    } else {
                        productRmStockDetailsModel.setStock_date(indentRecord.getIssue_date());
                        productRmStockDetailsModel.setClosing_balance_quantity(-reducedQty.get());
                        productRmStockDetailsModel.setClosing_balance_weight(-reducedWt.get());
                        productRmStockDetailsModel.setProduction_issue_quantity(reducedQty.get());
                        productRmStockDetailsModel.setProduction_issue_weight(reducedWt.get());
                        if (indentRecord.getIndent_no() != null && !indentRecord.getIndent_no().equalsIgnoreCase("")) {
                            productRmStockDetailsModel.setReserve_quantity(-reducedQty.get());
                            productRmStockDetailsModel.setReserve_weight(-reducedWt.get());
                            productRmStockDetailsModel.setGoods_receipt_no(indentRecord.getGoods_receipt_no());
                        }
                    }
                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
                    // Concatenate the new batch number with the existing one using a colon
                    String currentBatchNo = indentRecord.getIssue_batch_no();
                    String newBatchNo = stockRecord.getBatch_no();
                    String concatenatedBatchNo = currentBatchNo != null ? currentBatchNo + ":" + newBatchNo : newBatchNo;
                    indentRecord.setIssue_batch_no(concatenatedBatchNo);


                    CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();

                    cSmProductStockTracking.setCompany_id(stockRecord.getCompany_id());
                    cSmProductStockTracking.setCompany_branch_id(stockRecord.getCompany_branch_id());
                    cSmProductStockTracking.setFinancial_year(stockRecord.getFinancial_year());
                    cSmProductStockTracking.setProduct_material_id(indentRecord.getProduct_material_id());
                    cSmProductStockTracking.setGoods_receipt_no(stockRecord.getGoods_receipt_no());
                    cSmProductStockTracking.setConsumption_no(indentRecord.getIssue_no());
                    cSmProductStockTracking.setConsumption_detail_no(indentRecord.getIndent_no());
                    cSmProductStockTracking.setConsumption_date(new Date());
                    cSmProductStockTracking.setConsumption_location("Indent Issue");
                    cSmProductStockTracking.setConsumption_detail_no(String.valueOf(indentRecord.getIndent_details_id()));
                    cSmProductStockTracking.setConsumption_quantity(reducedQty.get());
                    cSmProductStockTracking.setCreated_by(indentRecord.getCreated_by());

                    addStockTracking.add(cSmProductStockTracking);  // Add into stock tracking list


//					if object is present in summary then just increase the qty of existing object
                    Optional<CSmProductRmStockSummaryModel> getSummaryObject = addProductRmStockSummaryList.stream()
                            .filter(item -> item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                    Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                            ).findFirst();

                    if (getSummaryObject.isPresent()) {
                        CSmProductRmStockSummaryModel getProductRmStockSummaryModel = getSummaryObject.get();
                        productRmStockSummaryModel.setClosing_balance_quantity(getProductRmStockSummaryModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        productRmStockSummaryModel.setClosing_balance_weight(getProductRmStockSummaryModel.getClosing_balance_weight() + (-reducedWt.get()));
                        productRmStockSummaryModel.setProduction_issue_quantity(getProductRmStockSummaryModel.getProduction_issue_quantity() + reducedQty.get());
                        productRmStockSummaryModel.setProduction_issue_weight(getProductRmStockSummaryModel.getProduction_issue_weight() + reducedWt.get());

                        if (indentRecord.getIndent_no() != null && !indentRecord.getIndent_no().equalsIgnoreCase("")) {
                            productRmStockSummaryModel.setReserve_quantity(-reducedQty.get());
                            productRmStockSummaryModel.setReserve_weight(-reducedWt.get());
                        }
//						productRmStockSummaryModel.setStock_date(indentRecord.getIssue_date());
                    } else {
                        productRmStockSummaryModel.setCompany_id(indentRecord.getCompany_id());
                        productRmStockSummaryModel.setCompany_branch_id(indentRecord.getCompany_branch_id());
                        productRmStockSummaryModel.setFinancial_year(indentRecord.getFinancial_year());
                        productRmStockSummaryModel.setClosing_balance_quantity(-reducedQty.get());
                        productRmStockSummaryModel.setClosing_balance_weight(-reducedWt.get());
                        productRmStockSummaryModel.setProduction_issue_quantity(reducedQty.get());
                        productRmStockSummaryModel.setProduction_issue_weight(reducedWt.get());
                        productRmStockSummaryModel.setProduct_rm_id(indentRecord.getProduct_material_id());
                        productRmStockSummaryModel.setProduct_material_unit_id(indentRecord.getProduct_material_unit_id());
                        productRmStockSummaryModel.setProduct_material_packing_id(indentRecord.getProduct_material_unit_id());
                        productRmStockSummaryModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        productRmStockSummaryModel.setModified_by(indentRecord.getCreated_by());
                        productRmStockSummaryModel.setGodown_id(productRmStockDetailsModel.getGodown_id());
                        productRmStockSummaryModel.setGodown_section_id(productRmStockDetailsModel.getGodown_section_id());
                        productRmStockSummaryModel.setGodown_section_beans_id(productRmStockDetailsModel.getGodown_section_beans_id());
                        if (indentRecord.getIndent_no() != null && !indentRecord.getIndent_no().equalsIgnoreCase("")) {
                            productRmStockSummaryModel.setReserve_quantity(-reducedQty.get());
                            productRmStockSummaryModel.setReserve_weight(-reducedWt.get());
                        }
// 						productRmStockSummaryModel.setStock_date(indentRecord.getIssue_date());

                        addProductRmStockSummaryList.add(productRmStockSummaryModel);

                    }
                    double quantityForCurrentRecord = reducedQty.get();
                    totalQuantity.set(totalQuantity.get() + quantityForCurrentRecord);

                    // Calculate the amount for the current stockRecord
                    double amountForCurrentRecord = reducedQty.get() * stockRecord.getBatch_rate();
                    totalAmount.set(totalAmount.get() + amountForCurrentRecord);
                } else {
                    return;
                }
            });
            double averageForCurrentRecord = totalAmount.get() / totalQuantity.get();
            iProductRmRepository.updateMaterialIssueRate(averageForCurrentRecord, indentRecord.getProduct_material_id(), company_id, indentRecord.getIssue_no()); // update in issue of opening balance
        });
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        stockDetails.put("StockTracking", addStockTracking);

        Map<String, Object> batchresponse = cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Decrease", company_id);
        return batchresponse;
    }


    @Override
    public Map<String, Object> FnDeleteRecord(String issue_no, int issue_version, int company_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iStIndentIssueMasterRepository.FnDeleteIndentIssueMasterRecord(issue_no, company_id, deleted_by);
            iStIndentMaterialIssueDetailRepository.FnDeleteIndentIssueDetailsRecord(issue_no, issue_version, company_id, deleted_by);
            iStMaterialIssueBatchWiseRepository.FnDeleteIssueBatchDetailsRecord(issue_no, company_id, deleted_by);

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
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String issue_no, int issue_version,
                                                                     String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            Map<String, Object> indentIssueMasterRecord = iStIndentIssueMasterRepository.FnShowIndentIssueMasterRecord(issue_no,
                    issue_version, financial_year, company_id);
            List<Map<String, Object>> indentIssueDetailsRecords = iStIndentMaterialIssueDetailViewRepository
                    .FnShowIndentMaterialIssueDetailRecords(issue_no, financial_year, company_id);
            List<Map<String, Object>> issueBatchWiseStockRecords = iStMaterialIssueBatchWiseRepository
                    .FnShowMaterialIssueBatchWiseRecord(issue_no, financial_year, company_id);

            responce.put("IndentIssueMasterRecord", indentIssueMasterRecord);
            responce.put("IndentIssueDetailsRecords", indentIssueDetailsRecords);
            responce.put("IssueBatchWiseStockRecords", issueBatchWiseStockRecords);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowIndentDetailsRecords(JSONObject indentNo) {
        Map<String, Object> response = new HashMap<>();
        try {
            JSONObject jsonObject = indentNo.getJSONObject("Ids");
            int company_id = jsonObject.getInt("company_id");
            int department_id = jsonObject.getInt("department_id");
            int sub_department_id = jsonObject.getInt("sub_department_id");

            JSONArray indentNos = indentNo.getJSONArray("indent_nos");
            List<String> indentNoList = indentNos.toList().parallelStream().map(Object::toString)
                    .collect(Collectors.toList());

            List<CStIndentDetailsViewModel> IndentDetailsList = iStIndentDetailsViewRepository.FnShowIndentDetails(indentNoList, company_id, department_id, sub_department_id);
            response.put("IndentDetailsList", IndentDetailsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }

}
