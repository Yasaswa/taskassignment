package com.erp.StRawMaterialReturnMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.ExceptionHandling.ExceptionHandlingClass;
import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptDetailsViewModel;
import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsDetailsModel;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.StIndentIssueMaster.Model.CStIndentIssueDetailsModel;
import com.erp.StIndentIssueMaster.Model.CStMaterialIssueBatchWiseModel;
import com.erp.StIndentIssueMaster.Repository.IStIndentMaterialIssueDetailRepository;
import com.erp.StIndentIssueMaster.Repository.IStMaterialIssueBatchWiseRepository;
import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnDetailsModel;
import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnDetailsViewModel;
import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnMasterModel;
import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnMasterViewModel;
import com.erp.StRawMaterialReturnMaster.Repository.IStRawMaterialReturnDetailsModelRepository;
import com.erp.StRawMaterialReturnMaster.Repository.IStRawMaterialReturnDetailsViewModelRepository;
import com.erp.StRawMaterialReturnMaster.Repository.IStRawMaterialReturnMasterModelRepository;
import com.erp.StRawMaterialReturnMaster.Repository.IStRawMaterialReturnMasterViewModelRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CStRawMaterialReturnMasterService {

    private final CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    private final ExceptionHandlingClass exceptionHandlingClass;

    private final IStRawMaterialReturnMasterModelRepository iStRawMaterialReturnMasterModelRepository;

    private final IStRawMaterialReturnMasterViewModelRepository iStRawMaterialReturnMasterViewModelRepository;

    private final IStRawMaterialReturnDetailsModelRepository iStRawMaterialReturnDetailsModelRepository;

    private final IStRawMaterialReturnDetailsViewModelRepository iStRawMaterialReturnDetailsViewModelRepository;

    private final IStIndentMaterialIssueDetailRepository iStIndentMaterialIssueDetailRepository;

    private final CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    IStMaterialIssueBatchWiseRepository iStMaterialIssueBatchWiseRepository;
    @Autowired
    private JdbcTemplate executeQuery;

    @Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
    public ResponseEntity<?> FnAddUpdateRecord(JSONObject jsonObject, String acceptFlag) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");
        int companyId = commonIdsObj.getInt("company_id");
        try {
            JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
            JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
            JSONArray materialIssueBatchWiseArray = (JSONArray) jsonObject.get("TransIssueBatchWiseData");

//            int sub_department_id = masterjson.getInt("sub_department_id");

//			CONVERt RETURN MASTER DATA FROM JSON
            CStRawMaterialReturnMasterModel returnMasterModel = objectMapper.readValue(masterjson.toString(),
                    CStRawMaterialReturnMasterModel.class);

//			INDENT ISSUE RETURN MASTER SAVE
            CStRawMaterialReturnMasterModel responseMaterialReturnMaster = iStRawMaterialReturnMasterModelRepository.save(returnMasterModel);

            List<CStRawMaterialReturnDetailsModel> materialReturnDetailsModel = objectMapper.readValue(detailsArray.toString(), new TypeReference<List<CStRawMaterialReturnDetailsModel>>() {
            });

            materialReturnDetailsModel.forEach(items -> {
                items.setIssue_return_master_transaction_id(responseMaterialReturnMaster.getIssue_return_master_transaction_id());
            });

            iStRawMaterialReturnDetailsModelRepository.saveAll(materialReturnDetailsModel);   // save indent issue details
            List<CStMaterialIssueBatchWiseModel> issueBatchWiseDetailsModel = objectMapper.readValue(materialIssueBatchWiseArray.toString(),
                    new TypeReference<List<CStMaterialIssueBatchWiseModel>>() {
                    });
            iStMaterialIssueBatchWiseRepository.saveAll(issueBatchWiseDetailsModel);
//			GET ISSUE NO.'S DETAILS FROM st_indent_material_issue_details AND UPDATE THE RETURN QUANTITY
            if ("R".equals(returnMasterModel.getIssue_return_status())) {
                List<CStIndentIssueDetailsModel> indentIssueDetailsRecords = iStIndentMaterialIssueDetailRepository.FnShowIndentMaterialIssueDetailRecords(returnMasterModel.getIssue_no(), companyId);
                List<CStIndentIssueDetailsModel> addIndentIssueDetails = new ArrayList<>();
//				materialReturnDetailsModel.forEach(item -> {
//					Optional<CStIndentIssueDetailsModel> indentIssueDetailsModel = indentIssueDetailsRecords.stream()
//							.filter(issueRecord -> issueRecord.getProduct_material_id().equals(item.getProduct_material_id()) && issueRecord.getIssue_batch_no().equals(item.getIssue_batch_no()) && issueRecord.getCreel_no().equals(item.getCreel_no())).findFirst();
                materialReturnDetailsModel.forEach(item -> {
                    Optional<CStIndentIssueDetailsModel> indentIssueDetailsModel = indentIssueDetailsRecords.stream()
                            .filter(issueRecord ->
                                    Objects.equals(issueRecord.getProduct_material_id(), item.getProduct_material_id()) &&
                                            Objects.equals(issueRecord.getIssue_batch_no(), item.getIssue_batch_no()) &&
                                            Objects.equals(issueRecord.getCreel_no(), item.getCreel_no()) // Handles null values safely
                            )
                            .findFirst();
//                materialReturnDetailsModel.forEach(item -> {
//                    Optional<CStIndentIssueDetailsModel> indentIssueDetailsModel = indentIssueDetailsRecords.stream()
//                            .filter(issueRecord -> issueRecord.getProduct_material_id().equals(item.getProduct_material_id()) &&
//                                    issueRecord.getIssue_batch_no().equals(item.getIssue_batch_no()) &&
//                                    (sub_department_id == 93 ? issueRecord.getCreel_no().equals(item.getCreel_no()) : true))
//                            .findFirst();

                    if (indentIssueDetailsModel.isPresent()) {
                        CStIndentIssueDetailsModel stIndentIssueDetailsModel = indentIssueDetailsModel.get();

                        stIndentIssueDetailsModel.setProduct_material_issue_return_quantity(item.getProduct_material_receipt_quantity());
                        stIndentIssueDetailsModel.setProduct_material_issue_return_weight(item.getProduct_material_receipt_weight());
                        addIndentIssueDetails.add(stIndentIssueDetailsModel);
                    }
                });
// Group entries by goods_receipt_no and product_material_id
                Map<String, CStRawMaterialReturnDetailsModel> groupedDetails = materialReturnDetailsModel.stream()
                        .collect(Collectors.toMap(
                                item -> item.getIssue_batch_no() + "_" + item.getProduct_material_id(), // Composite key
                                item -> item,
                                (existing, newItem) -> {
                                    // Combine existing and new items by summing quantities and weights
                                    existing.setProduct_material_receipt_quantity(
                                            existing.getProduct_material_receipt_quantity() + newItem.getProduct_material_receipt_quantity());
                                    existing.setProduct_material_receipt_weight(
                                            existing.getProduct_material_receipt_weight() + newItem.getProduct_material_receipt_weight());
                                    existing.setProduct_material_receipt_boxes(
                                            existing.getProduct_material_receipt_boxes() + newItem.getProduct_material_receipt_boxes());
                                    return existing;
                                }
                        ));

                // Replace the original list with the summed entries
                List<CStRawMaterialReturnDetailsModel> consolidatedIssueList = new ArrayList<>(groupedDetails.values());

//				UPDATE STOCK DETAILS
                FnUpdateRawMaterialStockDetails(returnMasterModel, issueBatchWiseDetailsModel, indentIssueDetailsRecords, companyId); // STOCK ISSUE

            }

            Map<String, Object> successResponse = exceptionHandlingClass.createSuccessResponse("Record processed successfully");
            successResponse.put("responseIndentIssueMaster", responseMaterialReturnMaster);
            return ResponseEntity.ok(successResponse);

        } catch (DataAccessException e) {
            return exceptionHandlingClass.handleException(e, companyId, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR, "/api/StRawMaterialReturnMaster/FnAddUpdateRecord");
        } catch (Exception e) {
            return exceptionHandlingClass.handleException(e, companyId, "An unexpected error occurred", HttpStatus.BAD_REQUEST, "/api/StRawMaterialReturnMaster/FnAddUpdateRecord");
        }
    }

    private void FnUpdateRawMaterialStockDetails(CStRawMaterialReturnMasterModel responseMaterialReturnMaster, List<CStMaterialIssueBatchWiseModel> materialReturnDetailsModel, List<CStIndentIssueDetailsModel> indentIssueDetailsRecords, int companyId) {
//		Get CurrentDate
        Date currentDate = new Date();
//	    Formatting the date to display only the date portion
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);

        Map<String, Object> stockDetails = new HashMap<>();

        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addStockTracking = new ArrayList<>();
        //		Get distinct material ids
        List<String> distinctMaterialIds = materialReturnDetailsModel.stream().map(CStMaterialIssueBatchWiseModel::getProduct_material_id).distinct()
                .collect(Collectors.toList());

        List<String> distinctIssue_batch_no = materialReturnDetailsModel.stream().map(CStMaterialIssueBatchWiseModel::getBatch_no).distinct()
                .collect(Collectors.toList());
        Map<String, List<Double>> distinctBatchWithWeights = materialReturnDetailsModel.stream()
                .collect(Collectors.groupingBy(
                        CStMaterialIssueBatchWiseModel::getBatch_no,  // Key: batch_no
                        Collectors.mapping(CStMaterialIssueBatchWiseModel::getCone_per_wt, Collectors.toList())
                ));
        List<Double> weights = distinctBatchWithWeights.values().stream()
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

        int subDepartmentIds = responseMaterialReturnMaster.getSub_department_id();
        int godownId = iSmProductRmStockDetailsRepository.getGodownId(subDepartmentIds);
        List<CSmProductRmStockDetailsModel> productStockDetailsDptWiseList = !distinctMaterialIds.isEmpty() ? iStRawMaterialReturnDetailsModelRepository.FnGetAllProductRmStockDetailsRawMaterialsDptAndLotwise(distinctMaterialIds, companyId, distinctIssue_batch_no, godownId) : null;

//		Get the data from sm_product_rm_stock_details table for stock issue
        List<CSmProductRmStockDetailsModel> productStockDetailsList = !distinctMaterialIds.isEmpty() ? iStRawMaterialReturnDetailsModelRepository.FnGetAllProductRmStockDetailsRawMaterialsLotwise(distinctMaterialIds, companyId, distinctIssue_batch_no,weights, 2) : null;

        //		iterate on indent materials for stock issue
        materialReturnDetailsModel.forEach(returnRecord -> {
//			Get only specific material stock
            List<CSmProductRmStockDetailsModel> getDistinctMaterialWiseStock = productStockDetailsDptWiseList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(returnRecord.getProduct_material_id())
                                    && Objects.equals(item.getCompany_id(), companyId)
                                    && item.getClosing_balance_quantity() > 0
                                    && Objects.equals(item.getGodown_id(), godownId)
                                    && Objects.equals(
                                    item.getGoods_receipt_no(),
                                    (returnRecord.getSet_no() != null && !returnRecord.getSet_no().trim().isEmpty()
                                            ? returnRecord.getGoods_receipt_no() + "-" + returnRecord.getSet_no()
                                            : returnRecord.getGoods_receipt_no()
                                    )
                            )

                    )
                    .collect(Collectors.toList());
            System.out.println("Filtered List: " + getDistinctMaterialWiseStock);

            List<CSmProductRmStockDetailsModel> getDistinctMaterialAndWtWiseStock = productStockDetailsList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(returnRecord.getProduct_material_id())
                                    && Objects.equals(item.getCompany_id(), companyId)
                                    && item.getClosing_balance_quantity() > 0
                                    && Objects.equals(item.getGodown_id(), returnRecord.getGodown_id())
                                    && Objects.equals(item.getWeight_per_box_item(), returnRecord.getCone_per_wt())
                            && Objects.equals(item.getGoods_receipt_no(), returnRecord.getGoods_receipt_no())
//                                    && Objects.equals(
//                                    item.getGoods_receipt_no(),
//                                    (returnRecord.getSet_no() != null && !returnRecord.getSet_no().trim().isEmpty()
//                                            ? returnRecord.getGoods_receipt_no() + "-" + returnRecord.getSet_no()
//                                            : returnRecord.getGoods_receipt_no()
//                                    )
//              )

                    )
                    .collect(Collectors.toList());
            System.out.println("Filtered List: " + getDistinctMaterialAndWtWiseStock);

            AtomicReference<Double> received_quantity = new AtomicReference<>(returnRecord.getReceipt_quantity());
            if (getDistinctMaterialAndWtWiseStock.isEmpty()) {
                CSmProductRmStockDetailsModel newStock = new CSmProductRmStockDetailsModel();

                newStock.setCompany_id(returnRecord.getCompany_id());
                newStock.setCompany_branch_id(returnRecord.getCompany_branch_id());
                newStock.setFinancial_year(responseMaterialReturnMaster.getFinancial_year());
                newStock.setBatch_no(returnRecord.getBatch_no());
                newStock.setWeight_per_box_item(returnRecord.getCone_per_wt());
                newStock.setSupplier_id(returnRecord.getSupplier_id());
                newStock.setGoods_receipt_no(returnRecord.getGoods_receipt_no());
                newStock.setProduct_rm_id(returnRecord.getProduct_material_id());
                newStock.setProduct_type_id(responseMaterialReturnMaster.getIndent_issue_return_type_id());
                newStock.setGodown_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_id())));
                newStock.setGodown_section_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_section_id())));
                newStock.setGodown_section_beans_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_section_beans_id())));
                newStock.setClosing_balance_quantity(returnRecord.getReceipt_quantity());
                newStock.setClosing_balance_weight(returnRecord.getReceipt_weight());
                newStock.setClosing_no_of_boxes(returnRecord.getReceipt_boxes());
                newStock.setProduction_issue_return_quantity(returnRecord.getReceipt_quantity());
                newStock.setProduction_issue_return_weight(returnRecord.getReceipt_weight());
                newStock.setProduction_issue_return_boxes(returnRecord.getReceipt_boxes());
                newStock.setStock_date(todayDate);
                if (!getDistinctMaterialWiseStock.isEmpty()) {
                    Double batchRate = getDistinctMaterialWiseStock.get(0).getBatch_rate();
                    newStock.setBatch_rate(batchRate);
                }
                // Add the new stock to the list so it gets processed below
                addProductRmStockDetailsList.add(newStock);

                // Also create summary record if needed
                Optional<CSmProductRmStockSummaryModel> getSummaryObject = addProductRmStockSummaryList.stream()
                        .filter(item -> item.getProduct_rm_id().equals(returnRecord.getProduct_material_id())
                                && Objects.equals(returnRecord.getGodown_id(), item.getGodown_id())
                                && Objects.equals(returnRecord.getGodown_section_id(), item.getGodown_section_id())
                                && Objects.equals(returnRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                        ).findFirst();

                if (!getSummaryObject.isPresent()) {
                    CSmProductRmStockSummaryModel newSummary = new CSmProductRmStockSummaryModel();
                    newSummary.setCompany_id(returnRecord.getCompany_id());
                    newSummary.setCompany_branch_id(returnRecord.getCompany_branch_id());
                    newSummary.setProduct_type_id(responseMaterialReturnMaster.getIndent_issue_return_type_id());

                    newSummary.setFinancial_year(responseMaterialReturnMaster.getFinancial_year());
                    newSummary.setClosing_balance_quantity(returnRecord.getReceipt_quantity());
                    newSummary.setClosing_balance_weight(returnRecord.getReceipt_weight());
                    newSummary.setClosing_no_of_boxes(returnRecord.getReceipt_boxes());
                    newSummary.setProduction_issue_return_quantity(returnRecord.getReceipt_quantity());
                    newSummary.setProduction_issue_return_weight(returnRecord.getReceipt_weight());
                    newSummary.setProduction_issue_return_boxes(returnRecord.getReceipt_boxes());
                    newSummary.setProduct_rm_id(returnRecord.getProduct_material_id());
                    newSummary.setModified_by(responseMaterialReturnMaster.getCreated_by());
                    newSummary.setGodown_id(returnRecord.getGodown_id());
                    newSummary.setGodown_section_id(returnRecord.getGodown_section_id());
                    newSummary.setGodown_section_beans_id(returnRecord.getGodown_section_beans_id());
                    newSummary.setWeight_per_box_item(returnRecord.getCone_per_wt());
                    if (!getDistinctMaterialWiseStock.isEmpty()) {
                        Double batchRate = getDistinctMaterialWiseStock.get(0).getBatch_rate();
                        newStock.setBatch_rate(batchRate);
                        newSummary.setProduct_type_group(getDistinctMaterialWiseStock.get(0).getProduct_type_group());
                    }
                    addProductRmStockSummaryList.add(newSummary);
                }
            }
            getDistinctMaterialAndWtWiseStock.forEach(stockRecord -> {
                CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();
                CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

//					if object is present in details then just increase the qty of existing object
                Optional<CSmProductRmStockDetailsModel> indentIssueDetailsModel = addProductRmStockDetailsList.stream()
                        .filter(item -> item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                         item.getBatch_no().equals(stockRecord.getBatch_no()) &&
                                        Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                        Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                        Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())

                                        && item.getGoods_receipt_no().equals(stockRecord.getGoods_receipt_no())
                        ).findFirst();

                if (indentIssueDetailsModel.isPresent()) {
                    productRmStockDetailsModel = indentIssueDetailsModel.get();

                    productRmStockDetailsModel.setCompany_id(returnRecord.getCompany_id());
                    productRmStockDetailsModel.setCompany_branch_id(returnRecord.getCompany_branch_id());
                    productRmStockDetailsModel.setFinancial_year(responseMaterialReturnMaster.getFinancial_year());
                    productRmStockDetailsModel.setBatch_no(returnRecord.getBatch_no());
                    productRmStockDetailsModel.setWeight_per_box_item(returnRecord.getCone_per_wt());
                    productRmStockDetailsModel.setSupplier_id(returnRecord.getSupplier_id());
                    productRmStockDetailsModel.setGoods_receipt_no(returnRecord.getGoods_receipt_no());
                    productRmStockDetailsModel.setProduct_rm_id(returnRecord.getProduct_material_id());
                    productRmStockDetailsModel.setProduct_type_id(responseMaterialReturnMaster.getIndent_issue_return_type_id());

                    productRmStockDetailsModel.setGodown_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_id())));
                    productRmStockDetailsModel.setGodown_section_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_section_id())));
                    productRmStockDetailsModel.setGodown_section_beans_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_section_beans_id())));

                    productRmStockDetailsModel.setClosing_balance_quantity(returnRecord.getReceipt_quantity());
                    productRmStockDetailsModel.setClosing_balance_weight(returnRecord.getReceipt_weight());
                    productRmStockDetailsModel.setClosing_no_of_boxes(returnRecord.getReceipt_boxes());
                    productRmStockDetailsModel.setProduction_issue_return_quantity(returnRecord.getReceipt_quantity());
                    productRmStockDetailsModel.setProduction_issue_return_weight(returnRecord.getReceipt_weight());
                    productRmStockDetailsModel.setProduction_issue_return_boxes(returnRecord.getReceipt_boxes());
                    productRmStockDetailsModel.setProduct_material_unit_id(stockRecord.getProduct_material_unit_id());
                    productRmStockDetailsModel.setStock_date(todayDate);

                } else {
                    productRmStockDetailsModel.setCompany_id(returnRecord.getCompany_id());
                    productRmStockDetailsModel.setCompany_branch_id(returnRecord.getCompany_branch_id());
                    productRmStockDetailsModel.setFinancial_year(responseMaterialReturnMaster.getFinancial_year());
                    productRmStockDetailsModel.setBatch_no(returnRecord.getBatch_no());
                    productRmStockDetailsModel.setWeight_per_box_item(returnRecord.getCone_per_wt());
                    productRmStockDetailsModel.setSupplier_id(returnRecord.getSupplier_id());
                    productRmStockDetailsModel.setGoods_receipt_no(returnRecord.getGoods_receipt_no());
                    productRmStockDetailsModel.setProduct_rm_id(returnRecord.getProduct_material_id());
                    productRmStockDetailsModel.setProduct_type_id(responseMaterialReturnMaster.getIndent_issue_return_type_id());
                    productRmStockDetailsModel.setProduct_material_unit_id(stockRecord.getProduct_material_unit_id());

                    productRmStockDetailsModel.setGodown_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_id())));
                    productRmStockDetailsModel.setGodown_section_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_section_id())));
                    productRmStockDetailsModel.setGodown_section_beans_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_section_beans_id())));

                    productRmStockDetailsModel.setClosing_balance_quantity(returnRecord.getReceipt_quantity());
                    productRmStockDetailsModel.setClosing_balance_weight(returnRecord.getReceipt_weight());
                    productRmStockDetailsModel.setClosing_no_of_boxes(returnRecord.getReceipt_boxes());
                    productRmStockDetailsModel.setProduction_issue_return_quantity(returnRecord.getReceipt_quantity());
                    productRmStockDetailsModel.setProduction_issue_return_weight(returnRecord.getReceipt_weight());
                    productRmStockDetailsModel.setProduction_issue_return_boxes(returnRecord.getReceipt_boxes());
                    productRmStockDetailsModel.setCustomer_id(stockRecord.getCustomer_id());
                    productRmStockDetailsModel.setStock_date(todayDate);

                }
                addProductRmStockDetailsList.add(productRmStockDetailsModel);


//			    if object is present in summary then just increase the qty of existing object
                Optional<CSmProductRmStockSummaryModel> getSummaryObject = addProductRmStockSummaryList.stream()
                        .filter(item -> item.getProduct_rm_id().equals(returnRecord.getProduct_material_id()) &&
                                Objects.equals(returnRecord.getGodown_id(), item.getGodown_id()) &&
                                Objects.equals(returnRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                Objects.equals(returnRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())

                        ).findFirst();

                if (getSummaryObject.isPresent()) {
                    CSmProductRmStockSummaryModel getProductRmStockSummaryModel = getSummaryObject.get();
                    productRmStockSummaryModel.setClosing_balance_quantity(getProductRmStockSummaryModel.getClosing_balance_quantity() + (returnRecord.getReceipt_quantity()));
                    productRmStockSummaryModel.setClosing_balance_weight(getProductRmStockSummaryModel.getClosing_balance_weight() + (returnRecord.getReceipt_weight()));
                    productRmStockSummaryModel.setClosing_no_of_boxes(getProductRmStockSummaryModel.getClosing_no_of_boxes() + (returnRecord.getReceipt_boxes()));
                    productRmStockSummaryModel.setProduction_issue_return_quantity(getProductRmStockSummaryModel.getProduction_issue_return_quantity() + returnRecord.getReceipt_quantity());
                    productRmStockSummaryModel.setProduction_issue_return_weight(getProductRmStockSummaryModel.getProduction_issue_return_weight() + returnRecord.getReceipt_weight());
                    productRmStockSummaryModel.setProduction_issue_return_boxes(getProductRmStockSummaryModel.getProduction_issue_return_boxes() + returnRecord.getReceipt_boxes());
                } else {
                    productRmStockSummaryModel.setCompany_id(returnRecord.getCompany_id());
                    productRmStockSummaryModel.setCompany_branch_id(returnRecord.getCompany_branch_id());
                    productRmStockSummaryModel.setProduct_type_id(responseMaterialReturnMaster.getIndent_issue_return_type_id());
                    productRmStockSummaryModel.setFinancial_year(responseMaterialReturnMaster.getFinancial_year());
                    productRmStockSummaryModel.setClosing_balance_quantity(returnRecord.getReceipt_quantity());
                    productRmStockSummaryModel.setClosing_balance_weight(returnRecord.getReceipt_weight());
                    productRmStockSummaryModel.setClosing_no_of_boxes(returnRecord.getReceipt_boxes());
                    productRmStockSummaryModel.setProduction_issue_return_quantity(returnRecord.getReceipt_quantity());
                    productRmStockSummaryModel.setProduction_issue_return_weight(returnRecord.getReceipt_weight());
                    productRmStockSummaryModel.setProduction_issue_return_boxes(returnRecord.getReceipt_boxes());
                    productRmStockSummaryModel.setProduct_rm_id(returnRecord.getProduct_material_id());
                    productRmStockSummaryModel.setModified_by(responseMaterialReturnMaster.getCreated_by());
                    productRmStockSummaryModel.setWeight_per_box_item(returnRecord.getCone_per_wt());
                    productRmStockSummaryModel.setGodown_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_id())));
                    productRmStockSummaryModel.setGodown_section_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_section_id())));
                    productRmStockSummaryModel.setGodown_section_beans_id(Integer.parseInt(String.valueOf(returnRecord.getGodown_section_beans_id())));
                    productRmStockSummaryModel.setProduct_material_unit_id(stockRecord.getProduct_material_unit_id());
                    productRmStockSummaryModel.setCustomer_id(stockRecord.getCustomer_id());
                    addProductRmStockSummaryList.add(productRmStockSummaryModel);

                }

            });

            // indent no present so check grn no
            getDistinctMaterialWiseStock.forEach(stockRecord -> {
                if (received_quantity.get() > 0) {

//              ADD THE STOCK IN WEAVING GODOWN FOR sm_product_rm_stock_details table only if set no will present
                    int subDptID = responseMaterialReturnMaster.getSub_department_id();

                    List<Object[]> godownIds = iSmProductRmStockDetailsRepository.getGodownIdAndSubDPt(subDptID);

                    Map<List<Integer>, Integer> subDepartmentMappings = godownIds.stream()
                            .collect(Collectors.toMap(
                                    row -> Collections.singletonList(((BigInteger) row[0]).intValue()), // Convert BigInteger to Integer and wrap in List
                                    row -> ((BigInteger) row[1]).intValue() // Convert BigInteger to Integer
                            ));

                    if (subDepartmentIds != 0) {

                        CSmProductRmStockDetailsModel productRmStockDetailsModelForWeaving = new CSmProductRmStockDetailsModel();
                        productRmStockDetailsModelForWeaving.setCompany_id(responseMaterialReturnMaster.getCompany_id());
                        productRmStockDetailsModelForWeaving.setCompany_branch_id(responseMaterialReturnMaster.getCompany_branch_id());
                        productRmStockDetailsModelForWeaving.setFinancial_year(responseMaterialReturnMaster.getFinancial_year());
                        productRmStockDetailsModelForWeaving.setProduct_type_id(responseMaterialReturnMaster.getIndent_issue_return_type_id());
                        productRmStockDetailsModelForWeaving.setStock_date(todayDate);
//					    productRmStockDetailsModelForWeaving.setGoods_receipt_no(returnRecord.getGoods_receipt_no() + "-" + responseMaterialReturnMaster.getSet_no());
                        productRmStockDetailsModelForWeaving.setGoods_receipt_no(returnRecord.getGoods_receipt_no() +
                                (responseMaterialReturnMaster.getSet_no() != null && !responseMaterialReturnMaster.getSet_no().trim().isEmpty() ? "-" + responseMaterialReturnMaster.getSet_no() : "")
                        );
//									productRmStockDetailsModelForWeaving.setGoods_receipt_no(returnRecord.getGoods_receipt_no());
                        productRmStockDetailsModelForWeaving.setWeight_per_box_item(stockRecord.getWeight_per_box_item());
                        productRmStockDetailsModelForWeaving.setSupplier_id(stockRecord.getSupplier_id());
                        productRmStockDetailsModelForWeaving.setBatch_no(returnRecord.getBatch_no());
                        productRmStockDetailsModelForWeaving.setProduct_rm_id(returnRecord.getProduct_material_id());
                        productRmStockDetailsModelForWeaving.setClosing_balance_quantity(-returnRecord.getReceipt_quantity());
                        productRmStockDetailsModelForWeaving.setClosing_balance_weight(-returnRecord.getReceipt_weight());
                        productRmStockDetailsModelForWeaving.setClosing_no_of_boxes(-returnRecord.getReceipt_boxes());
                        // Determine and set values based on sub_department_id
                        int subDepartmentId = responseMaterialReturnMaster.getSub_department_id();


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

//               ADD THE STOCK IN WEAVING GODOWN FOR sm_product_rm_stock_summary table
                        CSmProductRmStockSummaryModel productRmStockSummaryModelForWeaving = new CSmProductRmStockSummaryModel();

                        Optional<CSmProductRmStockSummaryModel> getSummaryObjectForWeaving = addProductRmStockSummaryList.stream()
                                .filter(item -> item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                        Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                        Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                        Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                                ).findFirst();

                        if (getSummaryObjectForWeaving.isPresent()) {
                            CSmProductRmStockSummaryModel getProductRmStockSummaryModel = getSummaryObjectForWeaving.get();
                            productRmStockSummaryModelForWeaving.setClosing_balance_quantity(getProductRmStockSummaryModel.getClosing_balance_quantity() + (-returnRecord.getReceipt_quantity()));
                            productRmStockSummaryModelForWeaving.setClosing_balance_weight(getProductRmStockSummaryModel.getClosing_balance_weight() + (-returnRecord.getReceipt_weight()));
                            productRmStockSummaryModelForWeaving.setClosing_no_of_boxes(getProductRmStockSummaryModel.getClosing_no_of_boxes() + (-returnRecord.getReceipt_boxes()));
                        } else {
                            productRmStockSummaryModelForWeaving.setCompany_id(returnRecord.getCompany_id());
                            productRmStockSummaryModelForWeaving.setSupplier_id(stockRecord.getSupplier_id());
                            productRmStockSummaryModelForWeaving.setFinancial_year(responseMaterialReturnMaster.getFinancial_year());
                            productRmStockSummaryModelForWeaving.setCompany_branch_id(returnRecord.getCompany_branch_id());
                            productRmStockSummaryModelForWeaving.setProduct_type_id(responseMaterialReturnMaster.getIndent_issue_return_type_id());
                            productRmStockSummaryModelForWeaving.setClosing_balance_quantity(-returnRecord.getReceipt_quantity());
                            productRmStockSummaryModelForWeaving.setClosing_balance_weight(-returnRecord.getReceipt_weight());
                            productRmStockSummaryModelForWeaving.setClosing_no_of_boxes(-returnRecord.getReceipt_boxes());
                            productRmStockSummaryModelForWeaving.setProduct_rm_id(returnRecord.getProduct_material_id());
                            productRmStockSummaryModelForWeaving.setModified_by(returnRecord.getCreated_by());
                            productRmStockSummaryModelForWeaving.setWeight_per_box_item(stockRecord.getWeight_per_box_item());


                            godownMapping.ifPresent(value -> {
                                productRmStockSummaryModelForWeaving.setGodown_section_beans_id(value);
                                productRmStockSummaryModelForWeaving.setGodown_id(value);
                                productRmStockSummaryModelForWeaving.setGodown_section_id(value);
                            });


                            addProductRmStockSummaryList.add(productRmStockSummaryModelForWeaving);

                        }
                    }
                }

            });
        });
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        stockDetails.put("StockTracking", addStockTracking);

        cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", responseMaterialReturnMaster.getCompany_id());

    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
    public ResponseEntity<?> FnDeleteRecord(int issue_return_master_transaction_id, int company_id, String deleted_by) {
        try {
            iStRawMaterialReturnMasterModelRepository.FnDeleteRawMaterialReturnMasterRecord(issue_return_master_transaction_id, company_id, deleted_by);
            iStRawMaterialReturnDetailsModelRepository.FnDeleteRawMaterialReturnDetailsRecord(issue_return_master_transaction_id, company_id, deleted_by);
//			iStMaterialIssueBatchWiseRepository.FnDeleteIssueBatchDetailsRecord(issue_no, company_id, deleted_by);
            Map<String, Object> successResponse = exceptionHandlingClass.createSuccessResponse("Record deleted successfully");
            return ResponseEntity.ok(successResponse);

        } catch (DataAccessException e) {
            return exceptionHandlingClass.handleException(e, company_id, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR, "/api/StRawMaterialReturnMaster/FnDeleteRecord");
        } catch (Exception e) {
            return exceptionHandlingClass.handleException(e, company_id, "An unexpected error occurred", HttpStatus.BAD_REQUEST, "/api/StRawMaterialReturnMaster/FnDeleteRecord");
        }
    }


    public ResponseEntity<?> FnShowRawMaterialReturnDetails(String issue_return_no, int company_id) {
        try {
            CStRawMaterialReturnMasterViewModel rawMaterialReturnMasterRecord = iStRawMaterialReturnMasterViewModelRepository.FnShowRawMaterialMasterRecord(issue_return_no, company_id);

            List<CStRawMaterialReturnDetailsViewModel> rawMaterialDetailsRecords = iStRawMaterialReturnDetailsViewModelRepository
                    .FnShowRawMaterialReturnDetailRecords(issue_return_no, company_id);
            List<Map<String, Object>> returnBatchWiseStockRecords = iStMaterialIssueBatchWiseRepository
                    .FnShowMaterialReturnBatchWiseRecord(issue_return_no, company_id);
            Map<String, Object> successResponse = exceptionHandlingClass.createSuccessResponse("Record fetched successfully");
            successResponse.put("rawMaterialReturnMasterRecord", rawMaterialReturnMasterRecord);
            successResponse.put("rawMaterialDetailsRecords", rawMaterialDetailsRecords);
            successResponse.put("returnBatchWiseStockRecords", returnBatchWiseStockRecords);
            return ResponseEntity.ok(successResponse);

        } catch (DataAccessException e) {
            return exceptionHandlingClass.handleException(e, company_id, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR, "/api/StRawMaterialReturnMaster/FnShowRawMaterialReturnDetails");
        } catch (Exception e) {
            return exceptionHandlingClass.handleException(e, company_id, "An unexpected error occurred", HttpStatus.BAD_REQUEST, "/api/StRawMaterialReturnMaster/FnShowRawMaterialReturnDetails");
        }
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
    public ResponseEntity<?> FnAddUpdateRecordForRMReturn(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");
        int companyId = commonIdsObj.getInt("company_id");
        String userName = commonIdsObj.getString("UserName");
        try {
            JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
            JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");

//			CONVERT RETURN MASTER DATA FROM JSON
            CStRawMaterialReturnMasterModel returnMasterModel = objectMapper.readValue(masterjson.toString(),
                    CStRawMaterialReturnMasterModel.class);

//			MATERIAL RETURN MASTER SAVE
            CStRawMaterialReturnMasterModel responseMaterialReturnMaster = iStRawMaterialReturnMasterModelRepository.save(returnMasterModel);

            List<CStRawMaterialReturnDetailsModel> materialReturnDetailsModel = objectMapper.readValue(detailsArray.toString(), new TypeReference<List<CStRawMaterialReturnDetailsModel>>() {
            });
            // delete all material before update then make delete false for only saved rows
            iStRawMaterialReturnDetailsModelRepository.deleteDetailsRecordsForupdate(responseMaterialReturnMaster.getIssue_return_master_transaction_id(), companyId,userName);


            materialReturnDetailsModel.forEach(items -> {
                items.setIssue_return_master_transaction_id(responseMaterialReturnMaster.getIssue_return_master_transaction_id());
                items.set_delete(false);
            });
//           MATERIAL RETURN DETAILS SAVE
            iStRawMaterialReturnDetailsModelRepository.saveAll(materialReturnDetailsModel);

            if ("R".equals(returnMasterModel.getIssue_return_status()) && "UU".equals(returnMasterModel.getMaterial_type())) {
//				MATERIAL RETURN DETAILS UPDATE
                FnUpdateRawMaterialStockDetailsReturn(responseMaterialReturnMaster, materialReturnDetailsModel, companyId); // STOCK ISSUE

                // Define a date formatter matching your date string format
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern as needed
                // Get today's date
                LocalDate todayDate = LocalDate.now();
                LocalDate grnDate = LocalDate.parse(returnMasterModel.getIssue_return_date(), dateFormatter);
                if (grnDate.isBefore(todayDate)) {
                    String storedProcedure = "{call Insert_reschedule_stock(?,?,?)}";
                    executeQuery.execute((Connection con) -> {
                        try (CallableStatement cs = con.prepareCall(storedProcedure)) {
                            for (CStRawMaterialReturnDetailsModel cPtGRNItem : materialReturnDetailsModel) {
                                cs.setString(1, cPtGRNItem.getGoods_receipt_no());
                                cs.setString(2, cPtGRNItem.getIssue_return_date());
                                cs.setString(3, cPtGRNItem.getProduct_material_id());
                                cs.addBatch(); // Add the statement to the batch
                            }
                            cs.executeBatch(); // Execute the batch
                        }
                        return null;
                    });
                }
            }

            Map<String, Object> successResponse = exceptionHandlingClass.createSuccessResponse("Record processed successfully");
            successResponse.put("responseReturnMaster", responseMaterialReturnMaster);
            return ResponseEntity.ok(successResponse);

        } catch (DataAccessException e) {
            return exceptionHandlingClass.handleException(e, companyId, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR, "/api/StRawMaterialReturnMaster/FnAddUpdateRecord");
        } catch (Exception e) {
            return exceptionHandlingClass.handleException(e, companyId, "An unexpected error occurred", HttpStatus.BAD_REQUEST, "/api/StRawMaterialReturnMaster/FnAddUpdateRecord");
        }
    }

    private Map<String, Object> FnUpdateRawMaterialStockDetailsReturn(CStRawMaterialReturnMasterModel responseMaterialReturnMaster, List<CStRawMaterialReturnDetailsModel> materialReturnDetails, int company_id) {

        Map<String, Object> stockDetails = new HashMap<>();
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addStockTracking = new ArrayList<>();

//		iterate on Return materials for STOCK to Spare stores
        materialReturnDetails.forEach(returnRecord -> {
            AtomicReference<Double> goods_return_quantity = new AtomicReference<>(returnRecord.getProduct_material_receipt_quantity());

            CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
            CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

            productRmStockDetailsModel.setGoods_receipt_no(responseMaterialReturnMaster.getIssue_return_no());
            productRmStockDetailsModel.setCompany_id(returnRecord.getCompany_id());
            productRmStockDetailsModel.setProduct_rm_id(returnRecord.getProduct_material_id());
            productRmStockDetailsModel.setCompany_branch_id(returnRecord.getCompany_branch_id());
            productRmStockDetailsModel.setFinancial_year(returnRecord.getFinancial_year());
            productRmStockDetailsModel.setProduct_rm_id(returnRecord.getProduct_material_id());
            productRmStockDetailsModel.setProduct_material_unit_id(returnRecord.getProduct_material_unit_id());
            productRmStockDetailsModel.setProduct_type_id(responseMaterialReturnMaster.getIndent_issue_return_type_id());
            productRmStockDetailsModel.setGodown_id(Integer.parseInt(returnRecord.getGodown_id()));
            productRmStockDetailsModel.setGodown_section_id(Integer.parseInt(returnRecord.getGodown_section_id()));
            productRmStockDetailsModel.setGodown_section_beans_id(Integer.parseInt(returnRecord.getGodown_section_beans_id()));
            productRmStockDetailsModel.setClosing_balance_quantity(goods_return_quantity.get());
            productRmStockDetailsModel.setProduction_issue_return_quantity(goods_return_quantity.get());
            productRmStockDetailsModel.setBatch_rate(returnRecord.getMaterial_batch_rate());
            productRmStockDetailsModel.setBatch_no(responseMaterialReturnMaster.getIssue_return_no());
            productRmStockDetailsModel.setStock_date(responseMaterialReturnMaster.getIssue_return_date());

            addProductRmStockDetailsList.add(productRmStockDetailsModel);

//			if object is present in summary then just increase the qty of existing object
            Optional<CSmProductRmStockSummaryModel> getSummaryObject = addProductRmStockSummaryList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(returnRecord.getProduct_material_id()) &&
                            Objects.equals(Integer.parseInt(returnRecord.getGodown_id()), item.getGodown_id()) &&
                            Objects.equals(Integer.parseInt(returnRecord.getGodown_section_id()), item.getGodown_section_id()) &&
                            Objects.equals(Integer.parseInt(returnRecord.getGodown_section_beans_id()), item.getGodown_section_beans_id())
                    ).findFirst();

            if (getSummaryObject.isPresent()) {
                CSmProductRmStockSummaryModel getProductRmStockSummaryModel = getSummaryObject.get();
                productRmStockSummaryModel.setClosing_balance_quantity(getProductRmStockSummaryModel.getClosing_balance_quantity() + (goods_return_quantity.get()));
                productRmStockSummaryModel.setProduction_issue_return_quantity(getProductRmStockSummaryModel.getSupplier_return_quantity() + goods_return_quantity.get());

            } else {
                productRmStockSummaryModel.setCompany_id(returnRecord.getCompany_id());
                productRmStockSummaryModel.setCompany_branch_id(returnRecord.getCompany_branch_id());
                productRmStockSummaryModel.setFinancial_year(returnRecord.getFinancial_year());
                productRmStockSummaryModel.setProduct_rm_id(returnRecord.getProduct_material_id());
                productRmStockSummaryModel.setGodown_id(Integer.parseInt(returnRecord.getGodown_id()));
                productRmStockSummaryModel.setGodown_section_id(Integer.parseInt(returnRecord.getGodown_section_id()));
                productRmStockSummaryModel.setGodown_section_beans_id(Integer.parseInt(returnRecord.getGodown_section_beans_id()));
                productRmStockSummaryModel.setProduct_type_id(responseMaterialReturnMaster.getIndent_issue_return_type_id());
                productRmStockSummaryModel.setModified_by(returnRecord.getCreated_by());
                productRmStockSummaryModel.setClosing_balance_quantity(goods_return_quantity.get());
                productRmStockSummaryModel.setProduction_issue_return_quantity(goods_return_quantity.get());

                addProductRmStockSummaryList.add(productRmStockSummaryModel);
            }
        });
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        stockDetails.put("StockTracking", addStockTracking);

        Map<String, Object> batchresponse = cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", company_id);
        return batchresponse;
    }

    public ResponseEntity<?> FnShowParticularReturnRecordDetails(int company_id, String issue_return_no) {
        try {
            CStRawMaterialReturnMasterViewModel rawMaterialReturnMasterRecord = iStRawMaterialReturnMasterViewModelRepository.FnShowRawMaterialMasterRecord(issue_return_no, company_id);

            List<CStRawMaterialReturnDetailsViewModel> rawMaterialDetailsRecords = iStRawMaterialReturnDetailsViewModelRepository
                    .FnShowRawMaterialReturnDetailRecords(issue_return_no, company_id);

            Map<String, Object> successResponse = exceptionHandlingClass.createSuccessResponse("Record fetched successfully");
            successResponse.put("rawMaterialReturnMasterRecord", rawMaterialReturnMasterRecord);
            successResponse.put("rawMaterialDetailsRecords", rawMaterialDetailsRecords);
            return ResponseEntity.ok(successResponse);

        } catch (DataAccessException e) {
            return exceptionHandlingClass.handleException(e, company_id, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR, "/api/StRawMaterialReturnMaster/FnShowRawMaterialReturnDetails");
        } catch (Exception e) {
            return exceptionHandlingClass.handleException(e, company_id, "An unexpected error occurred", HttpStatus.BAD_REQUEST, "/api/StRawMaterialReturnMaster/FnShowRawMaterialReturnDetails");
        }
    }
}
