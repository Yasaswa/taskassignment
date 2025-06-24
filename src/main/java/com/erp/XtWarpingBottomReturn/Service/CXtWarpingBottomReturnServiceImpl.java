package com.erp.XtWarpingBottomReturn.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsDetailsModel;
import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsMasterModel;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import org.springframework.beans.BeanUtils;

import com.erp.XtWarpingBottomReturn.Model.CXtWarpingBottomReturnModel;
import com.erp.XtWarpingBottomReturn.Model.CXtWarpingBottomReturnViewModel;
import com.erp.XtWarpingBottomReturn.Model.CXtWarpingBottomStockDetailsModel;
import com.erp.XtWarpingBottomReturn.Repository.IXtWarpingBottomReturnRepository;
import com.erp.XtWarpingBottomReturn.Repository.IXtWarpingBottomStockRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CXtWarpingBottomReturnServiceImpl implements IXtWarpingBottomReturnService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IXtWarpingBottomReturnRepository iXtWarpingBottomReturnRepository;

    @Autowired
    IXtWarpingBottomStockRepository iXtWarpingBottomStockRepository;

    private final CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String acceptFlag) {
        Map<String, Object> responce = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");
        int companyId = commonIdsObj.getInt("company_id");
        JSONArray warpingBottomReturnDetails = (JSONArray) jsonObject.get("TransWarpingBottomDetailData");
        try {

            List<CXtWarpingBottomReturnModel> cXtWarpingBottomReturnModels = objectMapper.readValue(warpingBottomReturnDetails.toString(),
                    new TypeReference<List<CXtWarpingBottomReturnModel>>() {
                    });
            // Check if there are records before proceeding
            if (!cXtWarpingBottomReturnModels.isEmpty() && "update".equals(acceptFlag)) {
                // Get bottom_return_no from the first record
                String bottomReturnNo = cXtWarpingBottomReturnModels.get(0).getBottom_return_no();

                // Fetch existing records using bottom_return_no and companyId
                List<CXtWarpingBottomReturnModel> existingRecords =
                        iXtWarpingBottomReturnRepository.findByBottomReturnNo(bottomReturnNo, companyId);

                // Extract IDs from incoming records
                Set<Integer> incomingIds = cXtWarpingBottomReturnModels.stream()
                        .map(CXtWarpingBottomReturnModel::getWarping_bottom_return_details_id)  // Assuming 'id' is the primary key
                        .collect(Collectors.toSet());

                // Identify missing records (those in existing but not in incoming)
                List<Integer> missingRecordIds = existingRecords.stream()
                        .map(CXtWarpingBottomReturnModel::getWarping_bottom_return_details_id)
                        .filter(id -> !incomingIds.contains(id))  // Not present in incoming
                        .collect(Collectors.toList());

                // Mark missing records as deleted
                if (!missingRecordIds.isEmpty()) {
                    iXtWarpingBottomReturnRepository.updateReturnRecord(missingRecordIds, companyId);
                }
            }
            cXtWarpingBottomReturnModels = iXtWarpingBottomReturnRepository.saveAll(cXtWarpingBottomReturnModels);

            // Filter records where bottom_return_item_status is "R"
            List<CXtWarpingBottomReturnModel> filteredModels = cXtWarpingBottomReturnModels.stream()
                    .filter(model -> "R".equals(model.getBottom_return_item_status()))
                    .collect(Collectors.toList());
            if ("Receive".equals(acceptFlag)) {
                List<CXtWarpingBottomStockDetailsModel> stockDetailsModels = filteredModels.stream()
                        .map(model -> {
                            CXtWarpingBottomStockDetailsModel stockModel = new CXtWarpingBottomStockDetailsModel();
                            BeanUtils.copyProperties(model, stockModel,"bottom_return_date","bottom_return_item_status",
                                    "bottom_return_type_id","bottom_return_type","return_by_id",
                                    "received_by_id","remark","department_id","sub_department_id","goods_receipt_no" ,"net_weight","gross_weight"); // Copy all matching fields

                            stockModel.setStock_status("A"); // Set stock_status
                            stockModel.setNet_weight(model.getActual_net_weight()); // Set net_weight from actual_net_weight
                            stockModel.setGross_weight(model.getActual_gross_weight()); // Set net_weight from actual_net_weight

                            return stockModel;
                        })
                        .collect(Collectors.toList());


                // Save only the filtered "R" records in the stock repository
                iXtWarpingBottomStockRepository.saveAll(stockDetailsModels);
                FnUpdateRawMaterialStockDetails(filteredModels, companyId); // STOCK ISSUE


                List<Integer> warpingBottomDetailsIds = stockDetailsModels.stream()
                        .map(CXtWarpingBottomStockDetailsModel::getWeaving_production_warping_bottom_details_id)
                        .collect(Collectors.toList());

                if (warpingBottomDetailsIds != null && !warpingBottomDetailsIds.isEmpty()) {
                    iXtWarpingBottomStockRepository.updateWarpingBottomDetailsStatus(warpingBottomDetailsIds, companyId);
                }
            }

            responce.put("success", 1);
            responce.put("data", cXtWarpingBottomReturnModels);
            responce.put("error", "");
            if ("add".equals(acceptFlag)) {
                responce.put("message", "Record Added successfully!...");
            }else if ("update".equals(acceptFlag)) {
                responce.put("message", "Record Updated successfully!...");
            } else {
                responce.put("message", "Record Received successfully!...");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(companyId, "api", "/api/GoodsReturnsDetails/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(companyId, "api", "/api/GoodsReturnsDetails/FnAddUpdateRecord", 0,
                    e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(String bottom_return_no, int company_id, String keyForViewUpdate) {
        Map<String, Object> responce = new HashMap<>();

        try {

            List<CXtWarpingBottomReturnViewModel> cXtWarpingBottomReturnModel;

            if (keyForViewUpdate.equals("view")) {
                cXtWarpingBottomReturnModel = iXtWarpingBottomReturnRepository.FnShowParticularRecordForUpdateview(bottom_return_no, company_id);
            } else {
                cXtWarpingBottomReturnModel = iXtWarpingBottomReturnRepository.FnShowParticularRecordForUpdate(bottom_return_no, company_id);
            }
            responce.put("success", 1);
            responce.put("cXtWarpingBottomReturnModel", cXtWarpingBottomReturnModel);

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
                return responce;
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;
    }


    private Map<String, Object> FnUpdateRawMaterialStockDetails( List<CXtWarpingBottomReturnModel> indentIssueDetails, int company_id) {

//		Get CurrentDate
        Date currentDate = new Date();
//	    Formatting the date to display only the date portion
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Object> stockDetails = new HashMap<>();

        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

//		iterate on indent materials for stock issue
        indentIssueDetails.forEach(indentRecord -> {
            Optional<CSmProductRmStockDetailsModel> getDetailsObject = addProductRmStockDetailsList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(indentRecord.getProduct_material_id()) &&
                            Objects.equals(indentRecord.getGodown_id(), item.getGodown_id()) &&
                            Objects.equals(indentRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                            Objects.equals(indentRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                    ).findFirst();

            AtomicReference<Double> no_of_cones = new AtomicReference<>(indentRecord.getNo_of_package());
            AtomicReference<Double> net_weight = new AtomicReference<>(indentRecord.getActual_net_weight());
            CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
            CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

            if (getDetailsObject.isPresent()) {
                productRmStockDetailsModel = getDetailsObject.get();
                productRmStockDetailsModel.setClosing_balance_quantity(productRmStockDetailsModel.getClosing_balance_quantity() + (no_of_cones.get()));
                productRmStockDetailsModel.setClosing_balance_weight(productRmStockDetailsModel.getClosing_balance_weight() + (net_weight.get()));
                productRmStockDetailsModel.setClosing_no_of_boxes(productRmStockDetailsModel.getClosing_no_of_boxes() + (indentRecord.getNo_of_boxes()));
                productRmStockDetailsModel.setProduction_return_quantity(productRmStockDetailsModel.getProduction_return_quantity() + no_of_cones.get());
                productRmStockDetailsModel.setProduction_return_weight(productRmStockDetailsModel.getProduction_return_weight() + net_weight.get());

            } else {

                productRmStockDetailsModel.setGoods_receipt_no(indentRecord.getBottom_return_no());
                productRmStockDetailsModel.setProduct_rm_id(indentRecord.getProduct_material_id());
                productRmStockDetailsModel.setProduct_type_id(indentRecord.getBottom_return_type_id());
                productRmStockDetailsModel.setWeight_per_box_item(indentRecord.getWeight_per_pkg());
                productRmStockDetailsModel.setProduct_type_group("PRM");
                productRmStockDetailsModel.setSupplier_id(indentRecord.getSupplier_id());
                productRmStockDetailsModel.setCustomer_id(indentRecord.getCustomer_id());
                productRmStockDetailsModel.setGodown_id(indentRecord.getGodown_id());
                productRmStockDetailsModel.setGodown_section_id(indentRecord.getGodown_section_id());
                productRmStockDetailsModel.setGodown_section_beans_id(indentRecord.getGodown_section_beans_id());
                productRmStockDetailsModel.setClosing_balance_quantity(no_of_cones.get());
                productRmStockDetailsModel.setClosing_balance_weight(net_weight.get());
                productRmStockDetailsModel.setClosing_no_of_boxes(indentRecord.getNo_of_boxes());
                productRmStockDetailsModel.setProduction_return_quantity(no_of_cones.get());
                productRmStockDetailsModel.setProduction_return_weight(net_weight.get());
                productRmStockDetailsModel.setBatch_no(indentRecord.getBatch_no());
                productRmStockDetailsModel.setStock_date(indentRecord.getBottom_receipt_date());
                productRmStockDetailsModel.setCompany_id(indentRecord.getCompany_id());
                productRmStockDetailsModel.setCompany_branch_id(indentRecord.getCompany_branch_id());
                productRmStockDetailsModel.setFinancial_year(indentRecord.getFinancial_year());
                addProductRmStockDetailsList.add(productRmStockDetailsModel);

            }
//

//					if object is present in summary then just increase the qty of existing object
            Optional<CSmProductRmStockSummaryModel> getSummaryObject = addProductRmStockSummaryList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(indentRecord.getProduct_material_id()) &&
                            Objects.equals(indentRecord.getGodown_id(), item.getGodown_id()) &&
                            Objects.equals(indentRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                            Objects.equals(indentRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                    ).findFirst();

            if (getSummaryObject.isPresent()) {
                productRmStockSummaryModel = getSummaryObject.get();
                productRmStockSummaryModel.setClosing_balance_quantity(productRmStockSummaryModel.getClosing_balance_quantity() + (no_of_cones.get()));
                productRmStockSummaryModel.setClosing_balance_weight(productRmStockSummaryModel.getClosing_balance_weight() + (net_weight.get()));
                productRmStockSummaryModel.setClosing_no_of_boxes(productRmStockSummaryModel.getClosing_no_of_boxes() + (indentRecord.getNo_of_boxes()));
                productRmStockSummaryModel.setProduction_return_quantity(productRmStockSummaryModel.getProduction_return_quantity() + no_of_cones.get());
                productRmStockSummaryModel.setProduction_return_weight(productRmStockSummaryModel.getProduction_return_weight() + net_weight.get());

            } else {

                productRmStockSummaryModel.setCompany_id(indentRecord.getCompany_id());
                productRmStockSummaryModel.setCompany_branch_id(indentRecord.getCompany_branch_id());
                productRmStockSummaryModel.setProduct_type_id(indentRecord.getBottom_return_type_id());
                productRmStockSummaryModel.setSupplier_id(indentRecord.getSupplier_id());
                productRmStockSummaryModel.setCustomer_id(indentRecord.getCustomer_id());
                productRmStockSummaryModel.setFinancial_year(indentRecord.getFinancial_year());
                productRmStockSummaryModel.setClosing_balance_quantity(no_of_cones.get());
                productRmStockSummaryModel.setClosing_balance_weight(net_weight.get());
                productRmStockSummaryModel.setWeight_per_box_item(indentRecord.getWeight_per_pkg());
                productRmStockSummaryModel.setProduct_type_group("PRM");
                productRmStockSummaryModel.setClosing_no_of_boxes(indentRecord.getNo_of_boxes());
                productRmStockSummaryModel.setProduction_return_quantity(no_of_cones.get());
                productRmStockSummaryModel.setProduction_return_weight(net_weight.get());
                productRmStockSummaryModel.setProduct_rm_id(indentRecord.getProduct_material_id());
                productRmStockSummaryModel.setModified_by(indentRecord.getCreated_by());
                productRmStockSummaryModel.setGodown_id(indentRecord.getGodown_id());
                productRmStockSummaryModel.setGodown_section_id(indentRecord.getGodown_section_id());
                productRmStockSummaryModel.setGodown_section_beans_id(indentRecord.getGodown_section_beans_id());
                addProductRmStockSummaryList.add(productRmStockSummaryModel);

            }

        });
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);

        Map<String, Object> batchresponse = cSmProductRmStockDetailsController.FnAddUpdateFGStock(stockDetails, "Increase", company_id);

        return batchresponse;
    }
    @Override
    public Map<String, Object> FnDeleteRecord(String bottom_return_no, String deleted_by, int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
//			DELETE WARPING PRODUCTION ORDER MASTER
            iXtWarpingBottomReturnRepository.FnDeleteRecord(bottom_return_no, deleted_by, company_id);

            response.put("success", 1);
        } catch (Exception e) {
            response.put("success", 0);
            response.put("error", e.getMessage());
            return response;
        }
        return response;
    }


}
