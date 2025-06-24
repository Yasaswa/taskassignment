package com.erp.SmProductRmStockDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanBatchwiseTradingModel;
import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanBatchwiseTradingViewModel;
import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanDetailsTradingModel;
import com.erp.MtDispatchChallanDetails.Repository.IMtDispatchChallanDetailsTradingRepository;
import com.erp.SmProductFgStockDetails.Repository.ISmProductFgStockDetailsRepository;
import com.erp.SmProductFgStockDetails.Repository.ISmProductFgStockSummaryRepository;
import com.erp.SmProductPr.Repository.ISmProductPrStockDetailsRepository;
import com.erp.SmProductPr.Repository.ISmProductPrStockSummaryRepository;
import com.erp.SmProductRmStockDetails.Model.*;
import com.erp.SmProductRmStockDetails.Repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class CSmProductRmStockDetailsServiceImpl implements ISmProductRmStockDetailsService {

    @Autowired
    ISmProductRmStockDetailsViewRepository iSmProductRmStockDetailsViewRepository;

    @Autowired
    IMtDispatchChallanDetailsTradingRepository iMtDispatchChallanDetailsTradingRepository;

    @Autowired
    ISmProductRmStockSummaryRepository iSmProductRmStockSummaryRepository;

    @Autowired
    ISmProductRmCustomerStockSummaryModelRepository iSmProductRmCustomerStockSummaryModelRepository;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    ISmProductRmCustomerStockDetailsModelRepository iSmProductRmCustomerStockDetailsModelRepository;

    @Autowired
    ISmProductFgStockDetailsRepository iSmProductFgStockDetailsRepository;

    @Autowired
    ISmProductFgStockSummaryRepository iSmProductFgStockSummaryRepository;

    @Autowired
    ISmProductPrStockDetailsRepository iSmProductPrStockDetailsRepository;

    @Autowired
    ISmProductPrStockSummaryRepository iSmProductPrStockSummaryRepository;

    @Autowired
    ISmProductRmCustomerStockdetailsHistoryModelRepository iSmProductRmCustomerStockdetailsHistoryModelRepository;

    @Autowired
    ISmProductRmCustomerStockSummaryHistoryModelRepository iSmProductRmCustomerStockSummaryHistoryModelRepository;
    @Autowired
    ISmProductRmStockSummaryHistoryModelRepository iSmProductRmStockSummaryHistoryModelRepository;

    @Autowired
    ISmProductStockTrackingRepository iSmProductStockTrackingRepository;

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate executeQuery;

    public static CMtDispatchChallanBatchwiseTradingModel FnConvertToDispMaterialDispChallanBatchWiseObj(CMtDispatchChallanDetailsTradingModel dispatchMaterial) {
        CMtDispatchChallanBatchwiseTradingModel challanBatchwiseTradingModel = new CMtDispatchChallanBatchwiseTradingModel();

        challanBatchwiseTradingModel.setDispatch_challan_details_transaction_id(dispatchMaterial.getDispatch_challan_details_transaction_id());
        challanBatchwiseTradingModel.setCompany_id(dispatchMaterial.getCompany_id());
        challanBatchwiseTradingModel.setCompany_branch_id(dispatchMaterial.getCompany_branch_id());
        challanBatchwiseTradingModel.setFinancial_year(dispatchMaterial.getFinancial_year());
        challanBatchwiseTradingModel.setDispatch_challan_no(dispatchMaterial.getDispatch_challan_no());
        challanBatchwiseTradingModel.setDispatch_challan_date(dispatchMaterial.getDispatch_challan_date());
        challanBatchwiseTradingModel.setCustomer_order_no(dispatchMaterial.getCustomer_order_no());
        challanBatchwiseTradingModel.setCustomer_order_Date(dispatchMaterial.getCustomer_order_Date());
        challanBatchwiseTradingModel.setSales_order_no(dispatchMaterial.getSales_order_no());
        challanBatchwiseTradingModel.setSales_order_version(dispatchMaterial.getSales_order_version());
        challanBatchwiseTradingModel.setSales_order_Date(dispatchMaterial.getSales_order_Date());
        challanBatchwiseTradingModel.setDispatch_schedule_no(dispatchMaterial.getDispatch_schedule_no());
        challanBatchwiseTradingModel.setDispatch_schedule_version(dispatchMaterial.getDispatch_schedule_version());
        challanBatchwiseTradingModel.setDispatch_schedule_date(dispatchMaterial.getDispatch_schedule_date());
        challanBatchwiseTradingModel.setDispatch_challan_batch_status(dispatchMaterial.getDispatch_challan_item_status());
        challanBatchwiseTradingModel.setSo_sr_no(dispatchMaterial.getSo_sr_no());
        challanBatchwiseTradingModel.setCreated_by(dispatchMaterial.getCreated_by());
        return challanBatchwiseTradingModel;
    }

    public static void updateProperty(String existingValue, String newValue, Consumer<String> setter) {
        if (newValue == null) {
            return;
        }
        if (existingValue == null || !existingValue.contains(newValue)) {
            setter.accept((existingValue != null && !existingValue.isEmpty()) ? existingValue + ":" + newValue : newValue);
        } else {
            setter.accept(existingValue);
        }
    }

    //	This is for the update the stocks from the dispatch-challan.
    @Override
    public Map<String, Object> FnAddUpdateRecord(HashMap<Object, Object> materialDetails) {
        Map<String, Object> responce = new HashMap<>();
        // Get the current date
        Date currentDate = new Date();
        List<Map<String, Object>> rollcount = (List<Map<String, Object>>) materialDetails.get("dispatch_roll");
        Integer dispatchRollNo = 0;

//        int totalDispatchRollNo = rollcount.stream()
//                .mapToInt(material -> (Integer) material.get("roll_count"))  // Convert each roll_count to an int
//                .sum();  // Sum up all the values
        int totalDispatchRollNo = rollcount.stream()
                .map(detailsMap -> (Map<String, Map<String, Double>>) detailsMap.get("sort_no_details"))
                .filter(Objects::nonNull)
                .flatMap(sortNoDetailsMap -> sortNoDetailsMap.values().stream())
                .mapToInt(metricsMap -> metricsMap.get("roll_count").intValue())
                .sum();
        List<CMtDispatchChallanDetailsTradingModel> DCMaterialDetails = (List<CMtDispatchChallanDetailsTradingModel>) materialDetails
                .get("materialDetails");
        Integer company_id = DCMaterialDetails.get(0).getCompany_id();

        // For send the batchwise deduction data.
        List<CMtDispatchChallanBatchwiseTradingModel> batchWiseMaterialUpdatedData = new ArrayList<CMtDispatchChallanBatchwiseTradingModel>();

//			------- List TO store the Materials details and summary. -----------------------------------------------

        // For materials.
        List<CSmProductRmStockDetailsModel> updatedRMMaterialStockDetails = new ArrayList<>();    // List to store updated the stock-details.
        List<CSmProductRmStockSummaryModel> updatedRMMaterialStockSummary = new ArrayList<>();    // List to store updated the stock-summary.
        List<CSmProductStockTracking> addProductStockTrackingList = new ArrayList<>();
        Map<String, Object> stockDetails = new HashMap<>();
        // Update the RawMaterials Stocks.
        List<String> distinctMaterialIds = DCMaterialDetails.parallelStream()
                .map(CMtDispatchChallanDetailsTradingModel::getProduct_material_id).distinct()
                .collect(Collectors.toList());

        List<CSmProductRmStockSummaryModel> productRmStockSummaryList = distinctMaterialIds != null ? iSmProductRmStockSummaryRepository.FnGetAllProductRmStockSummary(distinctMaterialIds, company_id) : null;
        List<CSmProductRmStockDetailsModel> productRmStockDetailsList = distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetails(distinctMaterialIds, company_id) : null;

        // Iterate the loop on the received materials for update For RM.
        for (CMtDispatchChallanDetailsTradingModel dispatchMaterial : DCMaterialDetails) {
            if (dispatchMaterial.getDispatch_quantity() == 0) {     // Skip the current iteration if quantity is 0
                System.out.println("\nNot For Dispatch Material ID:" + dispatchMaterial.getProduct_material_id() + " Dispatch Required Qty: " + dispatchMaterial.getDispatch_quantity());
                continue;
            }
            System.out.println("\nDispatch Material ID:" + dispatchMaterial.getProduct_material_id() + " Dispatch Required Qty: " + dispatchMaterial.getDispatch_quantity());
            // From stock-details the the grouped material where material id matched.

//            List<CSmProductRmStockDetailsModel> batchWiseMaterialList = productRmStockDetailsList.stream()
//                    .filter(materialBatch ->
//                            dispatchMaterial.getProduct_material_id().equals(materialBatch.getProduct_rm_id()) &&
////                                    rollcount.stream().anyMatch(rollcounts ->
////                                            rollcounts.get("set_no").equals(Integer.parseInt(materialBatch.getGoods_receipt_no()))
////                                    )
//                                    rollcount.stream().anyMatch(rollcounts -> {
//                                        Object sortNosObj = rollcounts.get("sort_no_list");
//                                        return rollcounts.get("set_no").equals(Integer.parseInt(materialBatch.getGoods_receipt_no())) &&
//                                                sortNosObj instanceof Collection &&
//                                                ((Collection<?>) sortNosObj).contains(materialBatch.getBatch_no());
//                                    })
//                    )
//                    .collect(Collectors.toList());
            List<CSmProductRmStockDetailsModel> batchWiseMaterialList = productRmStockDetailsList.stream()
                    .filter(materialBatch ->
                            dispatchMaterial.getProduct_material_id().equals(materialBatch.getProduct_rm_id()) &&
                                    rollcount.stream().anyMatch(rollcounts -> {
                                        Object setNoObj = rollcounts.get("set_no");
                                        Object sortNoDetailsObj = rollcounts.get("sort_no_details");

                                        if (!(setNoObj instanceof Integer) || !(sortNoDetailsObj instanceof Map))
                                            return false;

                                        int setNo = (Integer) setNoObj;
                                        Map<?, ?> sortNoDetails = (Map<?, ?>) sortNoDetailsObj;

                                        return setNo == Integer.parseInt(materialBatch.getGoods_receipt_no())
                                                && sortNoDetails.containsKey(materialBatch.getBatch_no());
                                    })
                    )
                    .collect(Collectors.toList());

            AtomicReference<Double> requiredDispatchedQty = new AtomicReference<>(dispatchMaterial.getDispatch_quantity());

            // There is no field is available for std_wt in all three tbls. thereFore calculate wt per-qty.
            double dispatch_weight = dispatchMaterial.getDispatch_weight();
            double dispWtPerQty = dispatchMaterial.getDispatch_weight() / dispatchMaterial.getDispatch_quantity();
            Double dispatchQTY = 0.0;
            Double dispatchWT = 0.0;
            Set<String> processedMaterialIds = new HashSet<>();


            for (CSmProductRmStockDetailsModel batchWiseMaterial : batchWiseMaterialList) {
//                String batchSetNo = batchWiseMaterial.getGoods_receipt_no();
//                String batchNo = batchWiseMaterial.getBatch_no();
//
//
//                Optional<Map<String, Object>> matchingSet = rollcount.stream()
//                        .filter(setDetails -> {
//                            Integer setNo = (Integer) setDetails.get("set_no");
//                            Set<String> sortNoList = (Set<String>) setDetails.get("sort_no_list");
//                            return setNo != null
//                                    && setNo.toString().equals(batchSetNo.trim())
//
//                                    && sortNoList.contains(batchNo.trim());
//                        })
//                        .findFirst();
//
//                if (matchingSet.isPresent()) {
//                    // Extract roll_count from the matched map
//                    dispatchRollNo = (Integer) matchingSet.get().get("roll_count");
//                    dispatchQTY = (Double) matchingSet.get().get("dispatch_quantity");
//                    dispatchWT = (Double) matchingSet.get().get("dispatch_weight");
//                    System.out.println("Set No: " + batchSetNo + " has roll count: " + dispatchRollNo);
//                }
                String batchSetNo = batchWiseMaterial.getGoods_receipt_no();
                String batchNo = batchWiseMaterial.getBatch_no();

                Optional<Map<String, Object>> matchingSet = rollcount.stream()
                        .filter(setDetails -> {
                            Integer setNo = (Integer) setDetails.get("set_no");
                            Map<String, Map<String, Object>> sortNoDetails = (Map<String, Map<String, Object>>) setDetails.get("sort_no_details");

                            return setNo != null
                                    && setNo.toString().equals(batchSetNo.trim())
                                    && sortNoDetails != null
                                    && sortNoDetails.containsKey(batchNo.trim());
                        })
                        .findFirst();

                if (matchingSet.isPresent()) {
                    Map<String, Object> set = matchingSet.get();
                    Map<String, Map<String, Object>> sortNoDetails = (Map<String, Map<String, Object>>) set.get("sort_no_details");
                    Map<String, Object> batchData = sortNoDetails.get(batchNo.trim());

                    if (batchData != null) {
                        Double rollCountDouble = (Double) batchData.get("roll_count");
                        Double dispatchQty = (Double) batchData.get("dispatch_quantity");
                        Double dispatchWeight = (Double) batchData.get("dispatch_weight");

                        dispatchRollNo = rollCountDouble != null ? rollCountDouble.intValue() : 0;
                        dispatchQTY = dispatchQty != null ? dispatchQty : 0.0;
                        dispatchWT = dispatchWeight != null ? dispatchWeight : 0.0;

                        System.out.println("Set No: " + batchSetNo + " has roll count: " + dispatchRollNo);
                    }
                }
                CSmProductRmStockDetailsModel cSmProductRmStockDetailsModel = new CSmProductRmStockDetailsModel();
                CSmProductRmStockSummaryModel cSmProductRmStockSummaryModel = new CSmProductRmStockSummaryModel();
                CMtDispatchChallanBatchwiseTradingModel batchWiseStockUpdatation = new CMtDispatchChallanBatchwiseTradingModel();

                // Means required qty 10 and available qty is 40; Means required qty is available in one batch.
//                if (batchWiseMaterial.getClosing_balance_quantity() >= requiredDispatchedQty.get()) {
//                    System.out.println("Dispatching Qty: " + requiredDispatchedQty.get() + " From Batch: " + batchWiseMaterial.getBatch_no());
//                    // Then update the that material batch stock details.
//                    double batchWiseStockWt = requiredDispatchedQty.get() * dispWtPerQty;
//
//                    cSmProductRmStockDetailsModel.setGodown_id(batchWiseMaterial.getGodown_id());
//                    cSmProductRmStockDetailsModel.setGodown_section_id(batchWiseMaterial.getGodown_section_id());
//                    cSmProductRmStockDetailsModel.setGodown_section_beans_id(batchWiseMaterial.getGodown_section_beans_id());
//                    cSmProductRmStockDetailsModel.setProduct_rm_id(dispatchMaterial.getProduct_material_id());
////                  cSmProductRmStockDetailsModel.setClosing_balance_quantity(-requiredDispatchedQty.get().doubleValue());
////                  cSmProductRmStockDetailsModel.setClosing_balance_weight(-batchWiseStockWt);
//                    cSmProductRmStockDetailsModel.setClosing_balance_quantity(-dispatchQTY);
//                    cSmProductRmStockDetailsModel.setClosing_balance_weight(-dispatchWT);
//                    cSmProductRmStockDetailsModel.setClosing_no_of_boxes(-dispatchRollNo);
////                  cSmProductRmStockDetailsModel.setSales_quantity(requiredDispatchedQty.get().doubleValue());
////                  cSmProductRmStockDetailsModel.setSales_weight(batchWiseStockWt);
//                    cSmProductRmStockDetailsModel.setSales_quantity(dispatchQTY);
//                    cSmProductRmStockDetailsModel.setSales_weight(dispatchWT);
//                    cSmProductRmStockDetailsModel.setSales_no_of_boxes(dispatchRollNo);
//                    cSmProductRmStockDetailsModel.setSales_order_no(dispatchMaterial.getSales_order_no());
//                    cSmProductRmStockDetailsModel.setSales_order_version(dispatchMaterial.getSales_order_version());
//                    cSmProductRmStockDetailsModel.setGoods_receipt_no(batchWiseMaterial.getGoods_receipt_no());
//                    cSmProductRmStockDetailsModel.setModified_by(dispatchMaterial.getCreated_by());
//                    updatedRMMaterialStockDetails.add(cSmProductRmStockDetailsModel);            // Push object to update stock-details
//
//                    // Now send the stock-updatation batchwise as a response.
//                    batchWiseStockUpdatation = FnConvertToDispMaterialDispChallanBatchWiseObj(dispatchMaterial);
//                    batchWiseStockUpdatation.setGodown_id(batchWiseMaterial.getGodown_id());
//                    batchWiseStockUpdatation.setGodown_section_id(batchWiseMaterial.getGodown_section_id());
//                    batchWiseStockUpdatation.setGodown_section_beans_id(batchWiseMaterial.getGodown_section_beans_id());
//                    batchWiseStockUpdatation.setGoods_receipt_no(batchWiseMaterial.getGoods_receipt_no());
//                    batchWiseStockUpdatation.setProduct_material_id(batchWiseMaterial.getProduct_rm_id());
//                    batchWiseStockUpdatation.setBatch_no(batchWiseMaterial.getBatch_no());
////                  batchWiseStockUpdatation.setBatch_dispatch_quantity(requiredDispatchedQty.get());
////                  batchWiseStockUpdatation.setBatch_dispatch_weight(batchWiseStockWt);
//                    batchWiseStockUpdatation.setBatch_dispatch_quantity(dispatchQTY);
//                    batchWiseStockUpdatation.setBatch_dispatch_weight(dispatchWT);
//                    batchWiseStockUpdatation.setBatch_dispatch_roll(dispatchRollNo);
//                    batchWiseMaterialUpdatedData.add(batchWiseStockUpdatation);
                if (!processedMaterialIds.contains(dispatchMaterial.getProduct_material_id())) {

                    requiredDispatchedQty.set(0.0);                // set the required qty as 0.
                    // Here also update data is stock-summary if requirement is fulfilled.
                    Optional<CSmProductRmStockSummaryModel> materialSummary = productRmStockSummaryList.stream()
                            .filter(matSummary -> {
                                return dispatchMaterial.getProduct_material_id().equals(matSummary.getProduct_rm_id());
                            })
                            .findFirst();

                    // If present then update it. else create and update.
                    if (materialSummary.isPresent()) {
                        CSmProductRmStockSummaryModel foundedMatSummary = materialSummary.get();

                        cSmProductRmStockSummaryModel.setProduct_rm_id(dispatchMaterial.getProduct_material_id());
                        cSmProductRmStockSummaryModel.setGodown_id(foundedMatSummary.getGodown_id());
                        cSmProductRmStockSummaryModel.setGodown_section_id(foundedMatSummary.getGodown_section_id());
                        cSmProductRmStockSummaryModel.setGodown_section_beans_id(foundedMatSummary.getGodown_section_beans_id());
                        cSmProductRmStockSummaryModel.setSales_quantity(dispatchMaterial.getDispatch_quantity());
                        cSmProductRmStockSummaryModel.setSales_weight(dispatchMaterial.getDispatch_weight());
                        cSmProductRmStockSummaryModel.setSales_no_of_boxes(totalDispatchRollNo);
                        cSmProductRmStockSummaryModel.setClosing_balance_quantity(-dispatchMaterial.getDispatch_quantity());
                        cSmProductRmStockSummaryModel.setClosing_balance_weight(-dispatchMaterial.getDispatch_weight());
                        cSmProductRmStockSummaryModel.setClosing_no_of_boxes(-totalDispatchRollNo);
                        cSmProductRmStockSummaryModel.setModified_by(dispatchMaterial.getCreated_by());
                        updatedRMMaterialStockSummary.add(cSmProductRmStockSummaryModel);
                    }
                    processedMaterialIds.add(dispatchMaterial.getProduct_material_id());
                }
                //Stock tracking details
//                    CSmProductStockTracking smProductStockTrackingFirstBatch = new CSmProductStockTracking();
//
//                    smProductStockTrackingFirstBatch.setCompany_id(dispatchMaterial.getCompany_id());
//                    smProductStockTrackingFirstBatch.setCompany_branch_id(dispatchMaterial.getCompany_branch_id());
//                    smProductStockTrackingFirstBatch.setFinancial_year(dispatchMaterial.getFinancial_year());
//                    smProductStockTrackingFirstBatch.setGoods_receipt_no(batchWiseMaterial.getGoods_receipt_no());
//                    smProductStockTrackingFirstBatch.setProduct_material_id(dispatchMaterial.getProduct_material_id());
//                    smProductStockTrackingFirstBatch.setStock_date(batchWiseMaterial.getStock_date());
//                    smProductStockTrackingFirstBatch.setStock_quantity(batchWiseMaterial.getClosing_balance_quantity());
//                    smProductStockTrackingFirstBatch.set_stock_consumed(true);
//                    smProductStockTrackingFirstBatch.setConsumption_date(currentDate);
//                    smProductStockTrackingFirstBatch.setConsumption_quantity(requiredDispatchedQty.get());
//                    smProductStockTrackingFirstBatch.setConsumption_no(dispatchMaterial.getDispatch_challan_no());
//                    smProductStockTrackingFirstBatch.setConsumption_detail_no(dispatchMaterial.getDispatch_challan_no());
//                    smProductStockTrackingFirstBatch.setConsumption_location("Dispatch Challan");
//                    smProductStockTrackingFirstBatch.setProduct_material_unit_id(dispatchMaterial.getProduct_material_unit_id());
//                    smProductStockTrackingFirstBatch.setStock_type("Own");
//                    smProductStockTrackingFirstBatch.setCreated_by(dispatchMaterial.getCreated_by());
//
//                    addProductStockTrackingList.add(smProductStockTrackingFirstBatch);
//                    break;  // Due to our requirement is fulfilled.

//                } else {
                // Means required qty 50 and available qty is 30;   Means required qty is not available in one batch.
                System.out.println("Dispatching Qty: " + batchWiseMaterial.getClosing_balance_quantity() + " From Batch: " + batchWiseMaterial.getBatch_no());
                requiredDispatchedQty.set(requiredDispatchedQty.get() - cSmProductRmStockDetailsModel.getClosing_balance_quantity());
                double batchWiseStockWt = batchWiseMaterial.getClosing_balance_quantity() * dispWtPerQty;
                // Now send the stock-updatation batch-wise as a response.
                batchWiseStockUpdatation = FnConvertToDispMaterialDispChallanBatchWiseObj(dispatchMaterial);
                batchWiseStockUpdatation.setGodown_id(batchWiseMaterial.getGodown_id());
                batchWiseStockUpdatation.setGodown_section_id(batchWiseMaterial.getGodown_section_id());
                batchWiseStockUpdatation.setGodown_section_beans_id(batchWiseMaterial.getGodown_section_beans_id());
                batchWiseStockUpdatation.setGoods_receipt_no(batchWiseMaterial.getGoods_receipt_no());
                batchWiseStockUpdatation.setProduct_material_id(batchWiseMaterial.getProduct_rm_id());
                batchWiseStockUpdatation.setBatch_no(batchWiseMaterial.getBatch_no());
//              batchWiseStockUpdatation.setBatch_dispatch_quantity(requiredDispatchedQty.get());
//              batchWiseStockUpdatation.setBatch_dispatch_weight(batchWiseStockWt);
                batchWiseStockUpdatation.setBatch_dispatch_quantity(dispatchQTY);
                batchWiseStockUpdatation.setBatch_dispatch_weight(dispatchWT);
                batchWiseStockUpdatation.setBatch_dispatch_roll(dispatchRollNo);
                batchWiseMaterialUpdatedData.add(batchWiseStockUpdatation);            // Add the object in batwise list.

                cSmProductRmStockDetailsModel.setGodown_id(batchWiseMaterial.getGodown_id());
                cSmProductRmStockDetailsModel.setGodown_section_id(batchWiseMaterial.getGodown_section_id());
                cSmProductRmStockDetailsModel.setGodown_section_beans_id(batchWiseMaterial.getGodown_section_beans_id());
                cSmProductRmStockDetailsModel.setProduct_rm_id(dispatchMaterial.getProduct_material_id());
//              cSmProductRmStockDetailsModel.setClosing_balance_quantity(-batchWiseMaterial.getClosing_balance_quantity());
//              cSmProductRmStockDetailsModel.setClosing_balance_weight(-batchWiseMaterial.getClosing_balance_weight());
                cSmProductRmStockDetailsModel.setClosing_balance_quantity(-dispatchQTY);
                cSmProductRmStockDetailsModel.setClosing_balance_weight(-dispatchWT);
                cSmProductRmStockDetailsModel.setClosing_no_of_boxes(-dispatchRollNo);
//              cSmProductRmStockDetailsModel.setSales_quantity(batchWiseMaterial.getClosing_balance_quantity());
//              cSmProductRmStockDetailsModel.setSales_weight(batchWiseStockWt);
                cSmProductRmStockDetailsModel.setSales_quantity(dispatchQTY);
                cSmProductRmStockDetailsModel.setSales_weight(dispatchWT);
                cSmProductRmStockDetailsModel.setSales_no_of_boxes(dispatchRollNo);
                cSmProductRmStockDetailsModel.setSales_order_no(dispatchMaterial.getSales_order_no());
                cSmProductRmStockDetailsModel.setSales_order_version(dispatchMaterial.getSales_order_version());
                cSmProductRmStockDetailsModel.setGoods_receipt_no(batchWiseMaterial.getGoods_receipt_no());
                cSmProductRmStockDetailsModel.setModified_by(dispatchMaterial.getCreated_by());
                cSmProductRmStockDetailsModel.setBatch_no(batchWiseMaterial.getBatch_no());
                cSmProductRmStockDetailsModel.setStock_date(dispatchMaterial.getDispatch_challan_date());
                updatedRMMaterialStockDetails.add(cSmProductRmStockDetailsModel);    // add the object in material details for stock updatation.
//                }

                //Stock tracking details
                CSmProductStockTracking smProductStockTracking = new CSmProductStockTracking();

                smProductStockTracking.setCompany_id(dispatchMaterial.getCompany_id());
                smProductStockTracking.setCompany_branch_id(dispatchMaterial.getCompany_branch_id());
                smProductStockTracking.setFinancial_year(dispatchMaterial.getFinancial_year());
                smProductStockTracking.setGoods_receipt_no(batchWiseMaterial.getGoods_receipt_no());
                smProductStockTracking.setProduct_material_id(dispatchMaterial.getProduct_material_id());
                smProductStockTracking.setStock_date(batchWiseMaterial.getStock_date());
                smProductStockTracking.setStock_quantity(batchWiseMaterial.getClosing_balance_quantity());
                smProductStockTracking.set_stock_consumed(true);
                smProductStockTracking.setConsumption_date(currentDate);
                smProductStockTracking.setConsumption_quantity(requiredDispatchedQty.get());
                smProductStockTracking.setConsumption_no(dispatchMaterial.getDispatch_challan_no());
                smProductStockTracking.setConsumption_detail_no(dispatchMaterial.getDispatch_challan_no());
                smProductStockTracking.setConsumption_location("Dispatch Challan");
                smProductStockTracking.setProduct_material_unit_id(dispatchMaterial.getProduct_material_unit_id());
                smProductStockTracking.setStock_type("Own");
                smProductStockTracking.setCreated_by(dispatchMaterial.getCreated_by());

                addProductStockTrackingList.add(smProductStockTracking);
            }

        }
        Map<String, Object> batchresponse = new HashMap<>();

//-------------------------------------- Save all materials. -----------------------------------------------------------
        stockDetails.put("RmStockSummary", updatedRMMaterialStockSummary);
        stockDetails.put("RmStockDetails", updatedRMMaterialStockDetails);
        stockDetails.put("StockTracking", addProductStockTrackingList);
//        FnAddUpdateRmStockRawMaterials(stockDetails, "Decrease", company_id);
        batchresponse = FnAddUpdateFGStock(stockDetails, "Decrease", company_id);
        System.out.println("All Stock Details and summary Will be updated for the Dispatch Challan RM.");
        responce.put("BatchWiseStockData", batchWiseMaterialUpdatedData);
        responce.put("FGStockReducedDetails", batchresponse);

        return responce;
    }

    // This will be called when dispatch-challan has been cancelled this will be batchwise.
    public Map<String, Object> FnReverseStockByDispatchChallan(List<CMtDispatchChallanDetailsTradingModel> challanDetails, List<CMtDispatchChallanBatchwiseTradingViewModel> releasedChallanBatchWiseMaterialsData, int company_id, String detailModifiedBy) {
        Map<String, Object> responce = new HashMap<>();
//		Get the current date
        Date currentDate = new Date();
//		Formatting the date to display only the date portion
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);
        try {
            // For stock Material.
            List<CSmProductRmStockDetailsModel> updatedRMMaterialStockDetails = new ArrayList<CSmProductRmStockDetailsModel>();
            List<CSmProductRmStockSummaryModel> updatedRMMaterialStockSummaryData = new ArrayList<CSmProductRmStockSummaryModel>();
            List<CSmProductStockTracking> addProductStockTrackingList = new ArrayList<>();
            Map<String, Object> stockDetails = new HashMap<>();


            // 1. From received batch-wised released data extract all the distinct rm_ids and from that get the stock_details from rm_stock_details.
            List<String> distinctRMMaterialIds = releasedChallanBatchWiseMaterialsData.parallelStream()
                    .map(CMtDispatchChallanBatchwiseTradingViewModel::getProduct_material_id).distinct()
                    .collect(Collectors.toList());

            // Then get the stock-details from material-ids.
            List<CSmProductRmStockDetailsModel> rmMaterialStockDetails = iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetails(distinctRMMaterialIds, company_id);

            // Get Stock Summary from sm_product_rm_stock_summary.
            List<CSmProductRmStockSummaryModel> rmMaterialStockSummaryData = iSmProductRmStockSummaryRepository.FnGetAllProductRmStockSummary(distinctRMMaterialIds, company_id);

            // 2. Then update the stock_details respective stock_batch.
            releasedChallanBatchWiseMaterialsData.forEach(releasedBatchRMMaterial -> {
                // From materialStockDetails find the batch_material matched with releasedBatchMaterial's batch.
                System.out.println("Reversing the stock for the batch No: " + releasedBatchRMMaterial.getBatch_no() + " Material Id: " + releasedBatchRMMaterial.getProduct_material_id());

                CSmProductRmStockDetailsModel productStockDetail = new CSmProductRmStockDetailsModel();
                // Update the stock-details object closing-quantity.

                productStockDetail.setProduct_rm_id(releasedBatchRMMaterial.getProduct_material_id());
                productStockDetail.setGoods_receipt_no(releasedBatchRMMaterial.getGoods_receipt_no());
                productStockDetail.setGodown_id(releasedBatchRMMaterial.getGodown_id());
                productStockDetail.setGodown_section_id(releasedBatchRMMaterial.getGodown_section_id());
                productStockDetail.setGodown_section_beans_id(releasedBatchRMMaterial.getGodown_section_beans_id());
                productStockDetail.setClosing_balance_quantity(releasedBatchRMMaterial.getBatch_dispatch_quantity());
                productStockDetail.setClosing_balance_weight(releasedBatchRMMaterial.getBatch_dispatch_weight());
                productStockDetail.setClosing_no_of_boxes(releasedBatchRMMaterial.getBatch_dispatch_roll());
                productStockDetail.setSales_rejection_quantity(releasedBatchRMMaterial.getBatch_dispatch_quantity());
                productStockDetail.setSales_rejection_weight(releasedBatchRMMaterial.getBatch_dispatch_weight());
                productStockDetail.setSales_rejection_no_of_boxes(releasedBatchRMMaterial.getBatch_dispatch_roll());
                productStockDetail.setBatch_no(releasedBatchRMMaterial.getBatch_no());
                productStockDetail.setStock_date(todayDate);

                productStockDetail.setModified_by(detailModifiedBy);
                updatedRMMaterialStockDetails.add(productStockDetail);
                System.out.println("Reversing in the stock detail for Stock Id: " + productStockDetail.getStock_transaction_id() + " closing qty: " + productStockDetail.getClosing_balance_quantity() + " SalesRejQty: " + productStockDetail.getSales_rejection_quantity() + " SalesQty: " + productStockDetail.getSales_quantity());

                //Stock tracking details
                CSmProductStockTracking smProductStockTrackingFirstBatch = new CSmProductStockTracking();

                smProductStockTrackingFirstBatch.setCompany_id(releasedBatchRMMaterial.getCompany_id());
                smProductStockTrackingFirstBatch.setCompany_branch_id(releasedBatchRMMaterial.getCompany_branch_id());
                smProductStockTrackingFirstBatch.setFinancial_year(releasedBatchRMMaterial.getFinancial_year());
                smProductStockTrackingFirstBatch.setGoods_receipt_no(releasedBatchRMMaterial.getGoods_receipt_no());
                smProductStockTrackingFirstBatch.setProduct_material_id(releasedBatchRMMaterial.getProduct_material_id());
                smProductStockTrackingFirstBatch.setStock_date(todayDate);
                smProductStockTrackingFirstBatch.setStock_quantity(releasedBatchRMMaterial.getBatch_dispatch_quantity());
                smProductStockTrackingFirstBatch.set_stock_consumed(false);
                smProductStockTrackingFirstBatch.setConsumption_date(currentDate);
//              smProductStockTrackingFirstBatch.setConsumption_quantity(releasedBatchRMMaterial.getBatch_dispatch_quantity());
//              smProductStockTrackingFirstBatch.setConsumption_no(dispatchMaterial.getDispatch_challan_no());
//              smProductStockTrackingFirstBatch.setConsumption_detail_no(dispatchMaterial.getDispatch_challan_no());
//              smProductStockTrackingFirstBatch.setConsumption_location("Dispatch Challan");
//              smProductStockTrackingFirstBatch.setProduct_material_unit_id(releasedBatchRMMaterial.get);
                smProductStockTrackingFirstBatch.setStock_type("Own");
                smProductStockTrackingFirstBatch.setCreated_by(releasedBatchRMMaterial.getCreated_by());

                addProductStockTrackingList.add(smProductStockTrackingFirstBatch);
            });

            // 3. Also update the stock summary.
            // First store the sum of the weight and quantity respective material_id in aggregatedMaterials.
            Map<String, Object> aggregatedRMMaterialsDetails = releasedChallanBatchWiseMaterialsData.stream()
                    .collect(Collectors.groupingBy(
                            CMtDispatchChallanBatchwiseTradingViewModel::getProduct_material_id,
                            Collectors.collectingAndThen(
                                    Collectors.toList(),
                                    list -> {
                                        Map<String, Double> result = new HashMap<>();
                                        result.put("TotalMaterialQuantity", list.stream().mapToDouble(CMtDispatchChallanBatchwiseTradingViewModel::getBatch_dispatch_quantity).sum());
                                        result.put("TotalMaterialWeight", list.stream().mapToDouble(CMtDispatchChallanBatchwiseTradingViewModel::getBatch_dispatch_weight).sum());
                                        result.put("TotalDispatchRoll", list.stream().mapToDouble(CMtDispatchChallanBatchwiseTradingViewModel::getBatch_dispatch_roll).sum());

                                        return result;
                                    }
                            )
                    ));

            // From stock-summary collect only the those materials we have in distinctMaterialsIds.
            List<CSmProductRmStockSummaryModel> filteredRMMaterialsSummary = rmMaterialStockSummaryData.stream()
                    .filter(material -> distinctRMMaterialIds.contains(material.getProduct_rm_id()))
                    .collect(Collectors.toList());

            // As per the aggrecate material update the data in stock-summary.
            aggregatedRMMaterialsDetails.forEach((productMaterialId, values) -> {
                Map<String, Double> aggregatedValues = (Map<String, Double>) values;
                Optional<CSmProductRmStockSummaryModel> matchingRMMaterialFromStockSummaryObjs = filteredRMMaterialsSummary.stream()
                        .filter(materialBatch -> {
                            return productMaterialId.equals(materialBatch.getProduct_rm_id());
                        })
                        .findFirst();

                if (matchingRMMaterialFromStockSummaryObjs.isPresent()) {
                    CSmProductRmStockSummaryModel productStockSummary = new CSmProductRmStockSummaryModel();
                    CSmProductRmStockSummaryModel matchedRMStockSummary = matchingRMMaterialFromStockSummaryObjs.get();
                    // Update the summary-data.
                    productStockSummary.setProduct_rm_id(productMaterialId);
                    productStockSummary.setSales_rejection_quantity(aggregatedValues.get("TotalMaterialQuantity"));
                    productStockSummary.setSales_rejection_weight(aggregatedValues.get("TotalMaterialWeight"));
                    productStockSummary.setSales_rejection_no_of_boxes(((Double) aggregatedValues.get("TotalDispatchRoll")).intValue());
                    productStockSummary.setClosing_balance_quantity(aggregatedValues.get("TotalMaterialQuantity"));
                    productStockSummary.setClosing_balance_weight(aggregatedValues.get("TotalMaterialWeight"));
                    productStockSummary.setClosing_no_of_boxes(((Double) aggregatedValues.get("TotalDispatchRoll")).intValue());
                    productStockSummary.setGodown_id(matchedRMStockSummary.getGodown_id());
                    productStockSummary.setGodown_section_id(matchedRMStockSummary.getGodown_section_id());
                    productStockSummary.setGodown_section_beans_id(matchedRMStockSummary.getGodown_section_beans_id());
                    productStockSummary.setModified_by(detailModifiedBy);
                    updatedRMMaterialStockSummaryData.add(productStockSummary);
                    System.out.println("Reversing the in stock Summary for Stock Id: " + productStockSummary.getStock_transaction_id() + " closing qty: " + productStockSummary.getClosing_balance_quantity() + " SalesRejQty: " + productStockSummary.getSales_rejection_quantity() + " SalesQty: " + productStockSummary.getSales_quantity());
                }
            });

// 				// ---------------- Finally Update the all Stocks Details & Summary. ----------------------------------------------------------------------

            System.out.println("All Stock Details and summary Will be updated(Reverse) for the Dispatch Challan .");
            stockDetails.put("RmStockSummary", updatedRMMaterialStockSummaryData);
            stockDetails.put("RmStockDetails", updatedRMMaterialStockDetails);
            stockDetails.put("StockTracking", addProductStockTrackingList);
            FnAddUpdateFGStock(stockDetails, "Increase", company_id);
            responce.put("Success", 1);
            responce.put("message", "Stock reversed successfully!...");
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "FnReverseStockByDispatchChallan", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "FnReverseStockByDispatchChallan",
                    0, e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
        } finally {
            return responce;
        }
    }

    @Override
    @Transactional
    public Map<String, Object> FnUpdateScheduledRmStockSummary() {
//		Get the current date
        Date currentDate = new Date();
//		Formatting the date to display only the date portion
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);
        try {
//			Get RM Stock Details data
            List<CSmProductRmStockDetailsModel> getAllStockDetail = iSmProductRmStockDetailsRepository.getAllStockDetail();

//--------------------------------------------------------Raw Material Stock list creation Start--------------------------------------------------------------------------------
            List<CSmProductRmStockDetailsModel> addNewRmStockDetailsMaterialsBydate = new ArrayList<>();
            List<CSmProductRmStockdetailsHistoryModel> addNewRmStockDetailsHistory = new ArrayList<>();

//			Create List to save For RM own stock details
            getAllStockDetail.forEach(item -> {
                boolean isOpeningBalance = "Opening Balance".equals(item.getGoods_receipt_no());

                //				Add existing data into history table sm_product_rm_stock_details_history
                CSmProductRmStockdetailsHistoryModel cSmProductRmStockdetailsHistoryModel = new CSmProductRmStockdetailsHistoryModel();
                BeanUtils.copyProperties(item, cSmProductRmStockdetailsHistoryModel);
                addNewRmStockDetailsHistory.add(cSmProductRmStockdetailsHistoryModel);

                // Update sm_product_rm_stock_details
                if (isOpeningBalance || item.getClosing_balance_quantity() > 0) {
                    CSmProductRmStockDetailsModel modifiedDetails = new CSmProductRmStockDetailsModel();
                    BeanUtils.copyProperties(item, modifiedDetails);
                    modifiedDetails.setOpening_quantity(item.getClosing_balance_quantity());
                    modifiedDetails.setOpening_weight(item.getClosing_balance_weight());
                    modifiedDetails.setOpening_no_of_boxes(item.getClosing_no_of_boxes());
                    modifiedDetails.setClosing_balance_quantity(item.getClosing_balance_quantity());
                    modifiedDetails.setClosing_balance_weight(item.getClosing_balance_weight());
                    modifiedDetails.setClosing_no_of_boxes(item.getClosing_no_of_boxes());
                    modifiedDetails.setPurchase_quantity(0);
                    modifiedDetails.setPurchase_weight(0);
                    modifiedDetails.setPurchase_no_of_boxes(0);
                    modifiedDetails.setProduction_issue_quantity(0);
                    modifiedDetails.setProduction_issue_weight(0);
                    modifiedDetails.setIssue_no_of_boxes(0);
                    modifiedDetails.setStock_transaction_id(0);
                    modifiedDetails.setProduction_issue_return_boxes(0);
                    modifiedDetails.setProduction_issue_return_quantity(0);
                    modifiedDetails.setProduction_issue_return_weight(0);
                    modifiedDetails.setSupplier_return_boxes(0);
                    modifiedDetails.setSupplier_return_quantity(0);
                    modifiedDetails.setSupplier_return_weight(0);
                    modifiedDetails.setTransfer_issue_quantity(0);
                    modifiedDetails.setTransfer_issue_weight(0);
                    modifiedDetails.setTransfer_receipt_quantity(0);
                    modifiedDetails.setTransfer_receipt_weight(0);
                    modifiedDetails.setLoan_issue_quantity(0);
                    modifiedDetails.setLoan_issue_weight(0);
                    modifiedDetails.setLoan_issue_boxes(0);
                    modifiedDetails.setLoan_receipt_quantity(0);
                    modifiedDetails.setLoan_receipt_weight(0);
                    modifiedDetails.setLoan_receipt_boxes(0);
                    modifiedDetails.setProduction_no_of_boxes(0);
                    modifiedDetails.setProduction_quantity(0);
                    modifiedDetails.setProduction_weight(0);
                    modifiedDetails.setSales_no_of_boxes(0);
                    modifiedDetails.setSales_quantity(0);
                    modifiedDetails.setSales_weight(0);
                    modifiedDetails.setSales_rejection_no_of_boxes(0);
                    modifiedDetails.setSales_rejection_quantity(0);
                    modifiedDetails.setSales_rejection_weight(0);
                    modifiedDetails.setProduction_return_quantity(0);
                    modifiedDetails.setProduction_return_weight(0);
                    modifiedDetails.setStock_date(todayDate);
                    addNewRmStockDetailsMaterialsBydate.add(modifiedDetails);
                }
            });

//--------------------------------------------------------Raw Material Stock list creation End--------------------------------------------------------------------------------
            iSmProductRmStockDetailsRepository.FnUpdateStockDetailsDayClosed();                                     // update day_closed of sm_product_rm_stock_details
            iSmProductRmStockDetailsRepository.saveAll(addNewRmStockDetailsMaterialsBydate);                        // save updated data of sm_product_rm_stock_details\
            System.out.println("Stock updated succesfully!....." + " " + todayDate);
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(1, "api",
                        "Stock updation scheduler", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                System.out.println("Stock updation failed!....." + " " + todayDate);
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(1, "api",
                    "Stock Updation scheduler error ", e.hashCode(), e.getMessage());

        }

        return null;
    }

    @Override
    public Map<String, Object> FnAddUpdateFGStock(Map<String, Object> stockDetails, String iuFlag, int company_id) {

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> batchList = new ArrayList<>();


//  ----------------------------------------------------------  Product Stock Updation--------------------------------------------------------------------------------------
//			Create list to add object to save stock details & Summary
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addProductStockTracking = new ArrayList<>();

//			Get Rm & Fg Stock
        List<CSmProductRmStockSummaryModel> getRmStockSummary = (List<CSmProductRmStockSummaryModel>) stockDetails.get("RmStockSummary");
        List<CSmProductRmStockDetailsModel> getRmStockDetails = (List<CSmProductRmStockDetailsModel>) stockDetails.get("RmStockDetails");
        List<CSmProductStockTracking> getStockTrackingDetails = (List<CSmProductStockTracking>) stockDetails.get("StockTracking");

//        boolean is_product_type_raw_material = false;
        AtomicReference<List<CSmProductRmStockSummaryModel>> productRmStockSummaryList = new AtomicReference<>();
        AtomicReference<List<CSmProductRmStockDetailsModel>> productRmStockDetailsList = new AtomicReference<>();
        AtomicReference<List<CSmProductStockTracking>> productRmStockTrackingDetailsList = new AtomicReference<>();

        // Define a date formatter matching your date string format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern as needed
        // Get today's date
        LocalDate todayDate = LocalDate.now();
        Optional<LocalDate> issue_date = Optional.empty();

//          Get distinct material Ids
        List<String> distinctMaterialIds = null;
        if (getRmStockDetails != null && !getRmStockDetails.isEmpty()) {
            distinctMaterialIds = getRmStockDetails.stream().map(CSmProductRmStockDetailsModel::getProduct_rm_id).distinct().collect(Collectors.toList());
            issue_date = getRmStockDetails.stream()
                    .map(CSmProductRmStockDetailsModel::getStock_date) // map to stock_date as String
                    .filter(Objects::nonNull) // filter out any null values to avoid NullPointerException
                    .map(date -> LocalDate.parse(date, dateFormatter)) // parse directly in stream
                    .findFirst(); // get the first element if available

        }
        productRmStockSummaryList.set(distinctMaterialIds != null ? iSmProductRmStockSummaryRepository.FnGetAllProductRmStockSummary(distinctMaterialIds, company_id) : null);

        // Get data from stock table if Dispatch date is small then current
        if (issue_date.isPresent() && issue_date.get().isBefore(todayDate)) {
            // Using map and orElse to handle the empty case
            String issueDate = issue_date.map(LocalDate::toString).orElse("");
            productRmStockDetailsList.set(distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetailsByIssueDate(distinctMaterialIds, company_id, issueDate) : null);
        } else {
            productRmStockDetailsList.set(distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetails(distinctMaterialIds, company_id) : null);
        }
        productRmStockTrackingDetailsList.set(distinctMaterialIds != null ? iSmProductStockTrackingRepository.FnGetAllProductTrackingDetails(distinctMaterialIds, company_id) : null);

//  Check incoming stock is available in our stock then if available the update it or add it (For Raw material/ Finish goods stock summary table)
        if (getRmStockSummary != null && !getRmStockSummary.isEmpty()) {
            getRmStockSummary.forEach(summObject -> {
                CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
                String material_id = summObject.getProduct_rm_id();
                Integer godown_id = summObject.getGodown_id();
                Integer godown_section_id = summObject.getPrev_godown_section_id() != null ? summObject.getPrev_godown_section_id() : summObject.getGodown_section_id();
                Integer godown_section_beans_id = summObject.getPrev_godown_section_id() != null ? summObject.getPrev_godown_section_beans_id() : summObject.getGodown_section_beans_id();

//				Find the object in productRmStockSummaryList based on material_id
                Optional<CSmProductRmStockSummaryModel> matchingObject = productRmStockSummaryList.get().stream()
                        .filter(item ->
                                material_id.equals(item.getProduct_rm_id()) &&
                                        (Objects.equals(company_id, item.getCompany_id())) &&
                                        Objects.equals(godown_id, item.getGodown_id()) &&
                                        Objects.equals(godown_section_id, item.getGodown_section_id()) &&
                                        Objects.equals(godown_section_beans_id, item.getGodown_section_beans_id()))
                        .findFirst();

//              Matching object present using material Id then update it otherwise insert it
                if (matchingObject.isPresent()) {
                    CSmProductRmStockSummaryModel existingSummMaterislStock = matchingObject.get();

                    BeanUtils.copyProperties(existingSummMaterislStock, productRmStockSummaryModel);

                    productRmStockSummaryModel.setPending_quantity(existingSummMaterislStock.getPending_quantity() + (summObject.getPending_quantity()));
                    productRmStockSummaryModel.setPending_weight(existingSummMaterislStock.getPending_weight() + (summObject.getPending_weight()));
                    productRmStockSummaryModel.setReserve_quantity(existingSummMaterislStock.getReserve_quantity() + (summObject.getReserve_quantity()));
                    productRmStockSummaryModel.setReserve_weight(existingSummMaterislStock.getReserve_weight() + (summObject.getReserve_weight()));
                    productRmStockSummaryModel.setExcess_quantity(existingSummMaterislStock.getExcess_quantity() + (summObject.getExcess_quantity()));
                    productRmStockSummaryModel.setExcess_weight(existingSummMaterislStock.getExcess_weight() + (summObject.getExcess_weight()));
                    productRmStockSummaryModel.setPree_closed_quantity(existingSummMaterislStock.getPree_closed_quantity() + (summObject.getPree_closed_quantity()));
                    productRmStockSummaryModel.setPree_closed_weight(existingSummMaterislStock.getPree_closed_weight() + (summObject.getPree_closed_weight()));
                    productRmStockSummaryModel.setPurchase_quantity(existingSummMaterislStock.getPurchase_quantity() + (summObject.getPurchase_quantity()));
                    productRmStockSummaryModel.setPurchase_weight(existingSummMaterislStock.getPurchase_weight() + (summObject.getPurchase_weight()));
                    productRmStockSummaryModel.setPurchase_return_quantity(existingSummMaterislStock.getPurchase_return_quantity() + (summObject.getPurchase_return_quantity()));
                    productRmStockSummaryModel.setPurchase_return_weight(existingSummMaterislStock.getPurchase_return_weight() + (summObject.getPurchase_return_weight()));
                    productRmStockSummaryModel.setPurchase_rejection_quantity(existingSummMaterislStock.getPurchase_rejection_quantity() + (summObject.getPurchase_rejection_quantity()));
                    productRmStockSummaryModel.setPurchase_rejection_weight(existingSummMaterislStock.getPurchase_rejection_weight() + (summObject.getPurchase_rejection_weight()));
//                    productRmStockSummaryModel.setJobcard_quantity(existingSummMaterislStock.getJobcard_quantity() + (summObject.getJobcard_quantity()));
//                    productRmStockSummaryModel.setJobcard_weight(existingSummMaterislStock.getJobcard_weight() + (summObject.getJobcard_weight()));
                    productRmStockSummaryModel.setProduction_issue_quantity(existingSummMaterislStock.getProduction_issue_quantity() + (summObject.getProduction_issue_quantity()));
                    productRmStockSummaryModel.setProduction_issue_weight(existingSummMaterislStock.getProduction_issue_weight() + (summObject.getProduction_issue_weight()));
                    productRmStockSummaryModel.setProduction_issue_return_quantity(existingSummMaterislStock.getProduction_issue_return_quantity() + (summObject.getProduction_issue_return_quantity()));
                    productRmStockSummaryModel.setProduction_issue_return_weight(existingSummMaterislStock.getProduction_issue_return_weight() + (summObject.getProduction_issue_return_weight()));
                    productRmStockSummaryModel.setProduction_issue_rejection_quantity(existingSummMaterislStock.getProduction_issue_rejection_quantity() + (summObject.getProduction_issue_rejection_quantity()));
                    productRmStockSummaryModel.setProduction_issue_rejection_weight(existingSummMaterislStock.getProduction_issue_rejection_weight() + (summObject.getProduction_issue_rejection_weight()));
                    productRmStockSummaryModel.setProduction_no_of_boxes(existingSummMaterislStock.getProduction_no_of_boxes() + (summObject.getProduction_no_of_boxes()));
                    productRmStockSummaryModel.setProduction_quantity(existingSummMaterislStock.getProduction_quantity() + (summObject.getProduction_quantity()));
                    productRmStockSummaryModel.setProduction_weight(existingSummMaterislStock.getProduction_weight() + (summObject.getProduction_weight()));
                    productRmStockSummaryModel.setProduction_return_quantity(existingSummMaterislStock.getProduction_return_quantity() + (summObject.getProduction_return_quantity()));
                    productRmStockSummaryModel.setProduction_return_weight(existingSummMaterislStock.getProduction_return_weight() + (summObject.getProduction_return_weight()));
                    productRmStockSummaryModel.setProduction_rejection_quantity(existingSummMaterislStock.getProduction_rejection_quantity() + (summObject.getProduction_rejection_quantity()));
                    productRmStockSummaryModel.setProduction_rejection_weight(existingSummMaterislStock.getProduction_rejection_weight() + (summObject.getProduction_rejection_quantity()));
//                    productRmStockSummaryModel.setAssembly_production_issue_quantity(existingSummMaterislStock.getAssembly_production_issue_quantity() + (summObject.getAssembly_production_issue_quantity()));
//                    productRmStockSummaryModel.setAssembly_production_issue_weight(existingSummMaterislStock.getAssembly_production_issue_weight() + (summObject.getAssembly_production_issue_weight()));
                    productRmStockSummaryModel.setSales_no_of_boxes(existingSummMaterislStock.getSales_no_of_boxes() + (summObject.getSales_no_of_boxes()));
                    productRmStockSummaryModel.setSales_quantity(existingSummMaterislStock.getSales_quantity() + (summObject.getSales_quantity()));
                    productRmStockSummaryModel.setSales_weight(existingSummMaterislStock.getSales_weight() + (summObject.getSales_weight()));
                    productRmStockSummaryModel.setSales_return_quantity(existingSummMaterislStock.getSales_return_quantity() + (summObject.getSales_return_quantity()));
                    productRmStockSummaryModel.setSales_return_weight(existingSummMaterislStock.getSales_return_weight() + (summObject.getSales_return_weight()));
                    productRmStockSummaryModel.setSales_rejection_quantity(existingSummMaterislStock.getSales_rejection_quantity() + (summObject.getSales_rejection_quantity()));
                    productRmStockSummaryModel.setSales_rejection_weight(existingSummMaterislStock.getSales_rejection_weight() + (summObject.getSales_rejection_weight()));
                    productRmStockSummaryModel.setSales_rejection_no_of_boxes(existingSummMaterislStock.getSales_rejection_no_of_boxes() + (summObject.getSales_rejection_no_of_boxes()));

//                    productRmStockSummaryModel.setCustomer_receipt_quantity(existingSummMaterislStock.getCustomer_receipt_quantity() + (summObject.getCustomer_receipt_quantity()));
//                    productRmStockSummaryModel.setCustomer_receipt_weight(existingSummMaterislStock.getCustomer_receipt_weight() + (summObject.getCustomer_receipt_weight()));
//                    productRmStockSummaryModel.setCustomer_return_quantity(existingSummMaterislStock.getCustomer_return_quantity() + (summObject.getCustomer_return_quantity()));
//                    productRmStockSummaryModel.setCustomer_return_weight(existingSummMaterislStock.getCustomer_return_weight() + (summObject.getCustomer_return_weight()));
//                    productRmStockSummaryModel.setCustomer_rejection_weight(existingSummMaterislStock.getCustomer_rejection_weight() + (summObject.getCustomer_rejection_weight()));
//                    productRmStockSummaryModel.setCustomer_rejection_quantity(existingSummMaterislStock.getCustomer_rejection_quantity() + (summObject.getCustomer_rejection_quantity()));
                    productRmStockSummaryModel.setTransfer_issue_quantity(existingSummMaterislStock.getTransfer_issue_quantity() + (summObject.getTransfer_issue_quantity()));
                    productRmStockSummaryModel.setTransfer_issue_weight(existingSummMaterislStock.getTransfer_issue_weight() + (summObject.getTransfer_issue_weight()));
                    productRmStockSummaryModel.setTransfer_receipt_quantity(existingSummMaterislStock.getTransfer_receipt_quantity() + (summObject.getTransfer_receipt_quantity()));
                    productRmStockSummaryModel.setTransfer_receipt_weight(existingSummMaterislStock.getTransfer_receipt_weight() + (summObject.getTransfer_receipt_weight()));
//                    productRmStockSummaryModel.setOutsources_out_quantity(existingSummMaterislStock.getOutsources_out_quantity() + (summObject.getOutsources_out_quantity()));
//                    productRmStockSummaryModel.setOutsources_out_weight(existingSummMaterislStock.getOutsources_out_weight() + (summObject.getOutsources_out_weight()));
//                    productRmStockSummaryModel.setOutsources_in_quantity(existingSummMaterislStock.getOutsources_in_quantity() + (summObject.getOutsources_in_quantity()));
//                    productRmStockSummaryModel.setOutsources_in_weight(existingSummMaterislStock.getOutsources_in_weight() + (summObject.getOutsources_in_weight()));
//                    productRmStockSummaryModel.setOutsources_rejection_quantity(existingSummMaterislStock.getOutsources_rejection_quantity() + (summObject.getOutsources_rejection_quantity()));
//                    productRmStockSummaryModel.setOutsources_rejection_weight(existingSummMaterislStock.getOutsources_rejection_weight() + (summObject.getOutsources_rejection_weight()));
//                    productRmStockSummaryModel.setLoan_receipt_quantity(existingSummMaterislStock.getLoan_receipt_quantity() + (summObject.getLoan_receipt_quantity()));
//                    productRmStockSummaryModel.setLoan_receipt_weight(existingSummMaterislStock.getLoan_receipt_weight() + (summObject.getLoan_receipt_weight()));
//                    productRmStockSummaryModel.setLoan_receipt_boxes(existingSummMaterislStock.getLoan_receipt_boxes() + (summObject.getLoan_receipt_boxes()));
//                    productRmStockSummaryModel.setLoan_issue_quantity(existingSummMaterislStock.getLoan_issue_quantity() + (summObject.getLoan_issue_quantity()));
//                    productRmStockSummaryModel.setLoan_issue_weight(existingSummMaterislStock.getLoan_issue_weight() + (summObject.getLoan_issue_weight()));
//                    productRmStockSummaryModel.setLoan_issue_boxes(existingSummMaterislStock.getLoan_issue_boxes() + (summObject.getLoan_issue_boxes()));
//                    productRmStockSummaryModel.setCancel_quantity(existingSummMaterislStock.getCancel_quantity() + (summObject.getCancel_quantity()));
//                    productRmStockSummaryModel.setCancel_weight(existingSummMaterislStock.getCancel_weight() + (summObject.getCancel_weight()));
//                    productRmStockSummaryModel.setDifference_quantity(existingSummMaterislStock.getDifference_quantity() + (summObject.getDifference_quantity()));
//                    productRmStockSummaryModel.setDifference_weight(existingSummMaterislStock.getDifference_weight() + (summObject.getDifference_weight()));
                    productRmStockSummaryModel.setClosing_balance_quantity(existingSummMaterislStock.getClosing_balance_quantity() + (summObject.getClosing_balance_quantity()));
                    productRmStockSummaryModel.setClosing_balance_weight(existingSummMaterislStock.getClosing_balance_weight() + (summObject.getClosing_balance_weight()));
                    productRmStockSummaryModel.setModified_by(summObject.getCreated_by());
                    productRmStockSummaryModel.setGodown_id(summObject.getGodown_id());
                    productRmStockSummaryModel.setGodown_section_id(summObject.getGodown_section_id());
                    productRmStockSummaryModel.setGodown_section_beans_id(summObject.getGodown_section_beans_id());
                    productRmStockSummaryModel.setOpening_no_of_boxes(existingSummMaterislStock.getOpening_no_of_boxes() + (summObject.getOpening_no_of_boxes()));
                    productRmStockSummaryModel.setPurchase_no_of_boxes(existingSummMaterislStock.getPurchase_no_of_boxes() + (summObject.getPurchase_no_of_boxes()));
                    productRmStockSummaryModel.setReserve_no_of_boxes(existingSummMaterislStock.getReserve_no_of_boxes() + (summObject.getReserve_no_of_boxes()));
                    productRmStockSummaryModel.setIssue_no_of_boxes(existingSummMaterislStock.getIssue_no_of_boxes() + (summObject.getIssue_no_of_boxes()));
                    productRmStockSummaryModel.setClosing_no_of_boxes(existingSummMaterislStock.getClosing_no_of_boxes() + (summObject.getClosing_no_of_boxes()));

                    productRmStockSummaryModel.setSupplier_return_quantity(existingSummMaterislStock.getSupplier_return_quantity() + (summObject.getSupplier_return_quantity()));
                    productRmStockSummaryModel.setSupplier_return_weight(existingSummMaterislStock.getSupplier_return_weight() + (summObject.getSupplier_return_weight()));
                    productRmStockSummaryModel.setSupplier_return_boxes(existingSummMaterislStock.getSupplier_return_boxes() + (summObject.getSupplier_return_boxes()));
                    if (iuFlag.equalsIgnoreCase("Increase")) {
                        productRmStockSummaryModel.setMaterial_rate(summObject.getMaterial_rate());
                    }

                    addProductRmStockSummaryList.add(productRmStockSummaryModel);
                } else {
                    productRmStockSummaryModel.setCompany_id(summObject.getCompany_id());
                    productRmStockSummaryModel.setCompany_branch_id(summObject.getCompany_branch_id());
                    productRmStockSummaryModel.setFinancial_year(summObject.getFinancial_year());
//                    productRmStockSummaryModel.setCustomer_id(summObject.getCustomer_id());
                    productRmStockSummaryModel.setProduct_type_group(summObject.getProduct_type_group());
                    productRmStockSummaryModel.setProduct_type_id(summObject.getProduct_type_id());
                    productRmStockSummaryModel.setProduct_rm_id(summObject.getProduct_rm_id());
                    productRmStockSummaryModel.setProduct_material_unit_id(summObject.getProduct_material_unit_id());
                    productRmStockSummaryModel.setProduct_material_packing_id(summObject.getProduct_material_packing_id());
//                    productRmStockSummaryModel.setCustomer_id(summObject.getCustomer_id());
//                    productRmStockSummaryModel.setOrder_quantity(summObject.getOrder_quantity());
//                    productRmStockSummaryModel.setOrder_weight(summObject.getOrder_weight());
                    productRmStockSummaryModel.setPending_quantity(summObject.getPending_quantity());
                    productRmStockSummaryModel.setPending_weight(summObject.getPending_weight());
                    productRmStockSummaryModel.setReserve_quantity(summObject.getReserve_quantity());
                    productRmStockSummaryModel.setReserve_weight(summObject.getReserve_weight());
                    productRmStockSummaryModel.setExcess_quantity(summObject.getExcess_quantity());
                    productRmStockSummaryModel.setExcess_weight(summObject.getExcess_weight());
                    productRmStockSummaryModel.setPree_closed_quantity(summObject.getPree_closed_quantity());
                    productRmStockSummaryModel.setPree_closed_weight(summObject.getPree_closed_weight());
                    productRmStockSummaryModel.setPurchase_quantity(summObject.getPurchase_quantity());
                    productRmStockSummaryModel.setPurchase_weight(summObject.getPurchase_weight());
                    productRmStockSummaryModel.setPurchase_return_quantity(summObject.getPurchase_return_quantity());
                    productRmStockSummaryModel.setPurchase_return_weight(summObject.getPurchase_return_weight());
                    productRmStockSummaryModel.setPurchase_rejection_quantity(summObject.getPurchase_rejection_quantity());
                    productRmStockSummaryModel.setPurchase_rejection_weight(summObject.getPurchase_rejection_weight());
//                    productRmStockSummaryModel.setJobcard_quantity(summObject.getJobcard_quantity());
//                    productRmStockSummaryModel.setJobcard_weight(summObject.getJobcard_weight());
                    productRmStockSummaryModel.setProduction_issue_quantity(summObject.getProduction_issue_quantity());
                    productRmStockSummaryModel.setProduction_issue_weight(summObject.getProduction_issue_weight());
                    productRmStockSummaryModel.setProduction_issue_return_quantity(summObject.getProduction_issue_return_quantity());
                    productRmStockSummaryModel.setProduction_issue_return_weight(summObject.getProduction_issue_return_weight());
                    productRmStockSummaryModel.setProduction_issue_rejection_quantity(summObject.getProduction_issue_rejection_quantity());
                    productRmStockSummaryModel.setProduction_issue_rejection_weight(summObject.getProduction_issue_rejection_weight());
                    productRmStockSummaryModel.setProduction_no_of_boxes(summObject.getProduction_no_of_boxes());
                    productRmStockSummaryModel.setProduction_quantity(summObject.getProduction_quantity());
                    productRmStockSummaryModel.setProduction_weight(summObject.getProduction_weight());
                    productRmStockSummaryModel.setProduction_return_quantity(summObject.getProduction_return_quantity());
                    productRmStockSummaryModel.setProduction_return_weight(summObject.getProduction_return_weight());
                    productRmStockSummaryModel.setProduction_rejection_quantity(summObject.getProduction_rejection_quantity());
                    productRmStockSummaryModel.setProduction_rejection_weight(summObject.getProduction_rejection_quantity());
//                    productRmStockSummaryModel.setAssembly_production_issue_quantity(summObject.getAssembly_production_issue_quantity());
//                    productRmStockSummaryModel.setAssembly_production_issue_weight(summObject.getAssembly_production_issue_weight());
                    productRmStockSummaryModel.setSales_no_of_boxes(summObject.getSales_no_of_boxes());
                    productRmStockSummaryModel.setSales_quantity(summObject.getSales_quantity());
                    productRmStockSummaryModel.setSales_weight(summObject.getSales_weight());
                    productRmStockSummaryModel.setSales_return_quantity(summObject.getSales_return_quantity());
                    productRmStockSummaryModel.setSales_return_weight(summObject.getSales_return_weight());
                    productRmStockSummaryModel.setSales_rejection_quantity(summObject.getSales_rejection_quantity());
                    productRmStockSummaryModel.setSales_rejection_weight(summObject.getSales_rejection_weight());
                    productRmStockSummaryModel.setSales_rejection_no_of_boxes(summObject.getSales_rejection_no_of_boxes());
//                    productRmStockSummaryModel.setCustomer_receipt_quantity(summObject.getCustomer_receipt_quantity());
//                    productRmStockSummaryModel.setCustomer_receipt_weight(summObject.getCustomer_receipt_weight());
//                    productRmStockSummaryModel.setCustomer_return_quantity(summObject.getCustomer_return_quantity());
//                    productRmStockSummaryModel.setCustomer_return_weight(summObject.getCustomer_return_weight());
//                    productRmStockSummaryModel.setCustomer_rejection_weight(summObject.getCustomer_rejection_weight());
//                    productRmStockSummaryModel.setCustomer_rejection_quantity(summObject.getCustomer_rejection_quantity());
                    productRmStockSummaryModel.setTransfer_issue_quantity(summObject.getTransfer_issue_quantity());
                    productRmStockSummaryModel.setTransfer_issue_weight(summObject.getTransfer_issue_weight());
                    productRmStockSummaryModel.setTransfer_receipt_quantity(summObject.getTransfer_receipt_quantity());
                    productRmStockSummaryModel.setTransfer_receipt_weight(summObject.getTransfer_receipt_weight());
//                    productRmStockSummaryModel.setOutsources_out_quantity(summObject.getOutsources_out_quantity());
//                    productRmStockSummaryModel.setOutsources_out_weight(summObject.getOutsources_out_weight());
//                    productRmStockSummaryModel.setOutsources_in_quantity(summObject.getOutsources_in_quantity());
//                    productRmStockSummaryModel.setOutsources_in_weight(summObject.getOutsources_in_weight());
//                    productRmStockSummaryModel.setOutsources_rejection_quantity(summObject.getOutsources_rejection_quantity());
//                    productRmStockSummaryModel.setOutsources_rejection_weight(summObject.getOutsources_rejection_weight());
//                    productRmStockSummaryModel.setLoan_receipt_quantity(summObject.getLoan_receipt_quantity());
//                    productRmStockSummaryModel.setLoan_receipt_weight(summObject.getLoan_receipt_weight());
//                    productRmStockSummaryModel.setLoan_receipt_boxes(summObject.getLoan_receipt_boxes());
//                    productRmStockSummaryModel.setLoan_issue_quantity(summObject.getLoan_issue_quantity());
//                    productRmStockSummaryModel.setLoan_issue_weight(summObject.getLoan_issue_weight());
//                    productRmStockSummaryModel.setLoan_issue_boxes(summObject.getLoan_issue_boxes());
//                    productRmStockSummaryModel.setCancel_quantity(summObject.getCancel_quantity());
//                    productRmStockSummaryModel.setCancel_weight(summObject.getCancel_weight());
//                    productRmStockSummaryModel.setDifference_quantity(summObject.getDifference_quantity());
//                    productRmStockSummaryModel.setDifference_weight(summObject.getDifference_weight());
                    productRmStockSummaryModel.setClosing_balance_quantity(summObject.getClosing_balance_quantity());
                    productRmStockSummaryModel.setClosing_balance_weight(summObject.getClosing_balance_weight());
                    productRmStockSummaryModel.setOpening_quantity(summObject.getOpening_quantity());
                    productRmStockSummaryModel.setOpening_weight(summObject.getOpening_weight());
                    productRmStockSummaryModel.setCreated_by(summObject.getCreated_by());
                    productRmStockSummaryModel.setGodown_id(godown_id);
                    productRmStockSummaryModel.setGodown_section_id(godown_section_id);
                    productRmStockSummaryModel.setGodown_section_beans_id(godown_section_beans_id);
                    productRmStockSummaryModel.setTotal_box_weight(summObject.getTotal_box_weight());
                    productRmStockSummaryModel.setTotal_quantity_in_box(summObject.getTotal_quantity_in_box());
                    productRmStockSummaryModel.setWeight_per_box_item(summObject.getWeight_per_box_item());
                    productRmStockSummaryModel.setOpening_no_of_boxes(summObject.getOpening_no_of_boxes());
                    productRmStockSummaryModel.setPurchase_no_of_boxes(summObject.getPurchase_no_of_boxes());
                    productRmStockSummaryModel.setReserve_no_of_boxes(summObject.getReserve_no_of_boxes());
                    productRmStockSummaryModel.setIssue_no_of_boxes(summObject.getIssue_no_of_boxes());
                    productRmStockSummaryModel.setClosing_no_of_boxes(summObject.getClosing_no_of_boxes());
                    productRmStockSummaryModel.setMaterial_rate(summObject.getMaterial_rate());

                    addProductRmStockSummaryList.add(productRmStockSummaryModel);
                }
            });
        }

//          Check incoming stock is available in our stock then if available the update it or add it (For Finish goods stock details table)
        if (getRmStockDetails != null && getRmStockDetails.size() != 0) {
            getRmStockDetails.forEach(detailsObject -> {
                Map<String, Object> batchObject = new HashMap<>();
                CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();
                String material_id = detailsObject.getProduct_rm_id();
                String good_receipt_no = detailsObject.getGoods_receipt_no();
                String batch_no = detailsObject.getBatch_no();


//				    Find the object in productRmStockDetailsList based on material_id & good receipt no
                Optional<CSmProductRmStockDetailsModel> matchingObject = productRmStockDetailsList.get().stream()
                        .filter(item -> material_id.equals(item.getProduct_rm_id())
                                && good_receipt_no.equals(item.getGoods_receipt_no())
                                && batch_no.equals(item.getBatch_no()))
                        .findFirst();

//                  Matching object present using material Id then update it otherwise insert it
                if (matchingObject.isPresent()) {
                    CSmProductRmStockDetailsModel existingSummMaterislStock = matchingObject.get();

                    BeanUtils.copyProperties(existingSummMaterislStock, productRmStockDetailsModel);

//						Concat existing no & new no
                    String existingBatchNo = existingSummMaterislStock.getBatch_no();
                    String newBatchNo = detailsObject.getBatch_no();
                    String existingBatchExpiryDate = existingSummMaterislStock.getBatch_expiry_date();
                    String existingGoodsReceiptNo = existingSummMaterislStock.getGoods_receipt_no();
                    String existingSalesOrderNo = existingSummMaterislStock.getSales_order_no();
                    Integer existingGodownId = existingSummMaterislStock.getGodown_id();
                    Integer existingGodownSectionId = existingSummMaterislStock.getGodown_section_id();
                    Integer existingGodownSectionBeansId = existingSummMaterislStock.getGodown_section_beans_id();

                    if (iuFlag.equalsIgnoreCase("stockAdjustment")) {
                        productRmStockDetailsModel.setBatch_rate(detailsObject.getBatch_rate());
                    }
                    productRmStockDetailsModel.setBatch_no((newBatchNo != null && !newBatchNo.isEmpty()) ? newBatchNo : existingBatchNo);
                    productRmStockDetailsModel.setBatch_expiry_date((detailsObject.getBatch_expiry_date() != null && !detailsObject.getBatch_expiry_date().isEmpty()) ? detailsObject.getBatch_expiry_date() : existingBatchExpiryDate);
                    productRmStockDetailsModel.setGoods_receipt_no((detailsObject.getGoods_receipt_no() != null && !detailsObject.getGoods_receipt_no().isEmpty()) ? detailsObject.getGoods_receipt_no() : existingGoodsReceiptNo);
                    productRmStockDetailsModel.setSales_order_no((detailsObject.getSales_order_no() != null && !detailsObject.getSales_order_no().isEmpty()) ? detailsObject.getSales_order_no() : existingSalesOrderNo);
//                    productRmStockDetailsModel.setCustomer_order_no((detailsObject.getCustomer_order_no() != null && !detailsObject.getCustomer_order_no().isEmpty()) ? detailsObject.getCustomer_order_no() : existingCustomerOrderNo);
//                    productRmStockDetailsModel.setCustomer_goods_receipt_no((detailsObject.getCustomer_goods_receipt_no() != null && !detailsObject.getCustomer_goods_receipt_no().isEmpty()) ? detailsObject.getCustomer_goods_receipt_no() : existingCustomerGoodsReceiptNo);
                    productRmStockDetailsModel.setGodown_id((detailsObject.getGodown_id() != null && detailsObject.getGodown_id() != 0) ? detailsObject.getGodown_id() : existingGodownId);
                    productRmStockDetailsModel.setGodown_section_id((detailsObject.getGodown_section_id() != null && detailsObject.getGodown_section_id() != 0) ? detailsObject.getGodown_section_id() : existingGodownSectionId);
                    productRmStockDetailsModel.setGodown_section_beans_id((detailsObject.getGodown_section_beans_id() != null && detailsObject.getGodown_section_beans_id() != 0) ? detailsObject.getGodown_section_beans_id() : existingGodownSectionBeansId);
                    productRmStockDetailsModel.setPending_quantity(existingSummMaterislStock.getPending_quantity() + (detailsObject.getPending_quantity()));
                    productRmStockDetailsModel.setPending_weight(existingSummMaterislStock.getPending_weight() + (detailsObject.getPending_weight()));
                    productRmStockDetailsModel.setReserve_quantity(existingSummMaterislStock.getReserve_quantity() + (detailsObject.getReserve_quantity()));
                    productRmStockDetailsModel.setReserve_weight(existingSummMaterislStock.getReserve_weight() + (detailsObject.getReserve_weight()));
                    productRmStockDetailsModel.setPree_closed_quantity(existingSummMaterislStock.getPree_closed_quantity() + (detailsObject.getPree_closed_quantity()));
                    productRmStockDetailsModel.setPree_closed_weight(existingSummMaterislStock.getPree_closed_weight() + (detailsObject.getPree_closed_weight()));
                    productRmStockDetailsModel.setPurchase_return_quantity(existingSummMaterislStock.getPurchase_return_quantity() + (detailsObject.getPurchase_return_quantity()));
                    productRmStockDetailsModel.setPurchase_return_weight(existingSummMaterislStock.getPurchase_return_weight() + (detailsObject.getPurchase_return_weight()));
                    productRmStockDetailsModel.setPurchase_rejection_quantity(existingSummMaterislStock.getPurchase_rejection_quantity() + (detailsObject.getPurchase_rejection_quantity()));
                    productRmStockDetailsModel.setPurchase_rejection_weight(existingSummMaterislStock.getPurchase_rejection_weight() + (detailsObject.getPurchase_rejection_weight()));
//                    productRmStockDetailsModel.setJobcard_quantity(existingSummMaterislStock.getJobcard_quantity() + (detailsObject.getJobcard_quantity()));
//                    productRmStockDetailsModel.setJobcard_weight(existingSummMaterislStock.getJobcard_weight() + (detailsObject.getJobcard_weight()));
                    productRmStockDetailsModel.setProduction_issue_quantity(existingSummMaterislStock.getProduction_issue_quantity() + (detailsObject.getProduction_issue_quantity()));
                    productRmStockDetailsModel.setProduction_issue_weight(existingSummMaterislStock.getProduction_issue_weight() + (detailsObject.getProduction_issue_weight()));
                    productRmStockDetailsModel.setProduction_issue_return_quantity(existingSummMaterislStock.getProduction_issue_return_quantity() + (detailsObject.getProduction_issue_return_quantity()));
                    productRmStockDetailsModel.setProduction_issue_return_weight(existingSummMaterislStock.getProduction_issue_return_weight() + (detailsObject.getProduction_issue_return_weight()));
                    productRmStockDetailsModel.setProduction_issue_rejection_quantity(existingSummMaterislStock.getProduction_issue_rejection_quantity() + (detailsObject.getProduction_issue_rejection_quantity()));
                    productRmStockDetailsModel.setProduction_issue_rejection_weight(existingSummMaterislStock.getProduction_issue_rejection_weight() + (detailsObject.getProduction_issue_rejection_weight()));
                    productRmStockDetailsModel.setProduction_no_of_boxes(existingSummMaterislStock.getProduction_no_of_boxes() + (detailsObject.getProduction_no_of_boxes()));
                    productRmStockDetailsModel.setProduction_quantity(existingSummMaterislStock.getProduction_quantity() + (detailsObject.getProduction_quantity()));
                    productRmStockDetailsModel.setProduction_weight(existingSummMaterislStock.getProduction_weight() + (detailsObject.getProduction_weight()));
                    productRmStockDetailsModel.setProduction_return_quantity(existingSummMaterislStock.getProduction_return_quantity() + (detailsObject.getProduction_return_quantity()));
                    productRmStockDetailsModel.setProduction_return_weight(existingSummMaterislStock.getProduction_return_weight() + (detailsObject.getProduction_return_weight()));
                    productRmStockDetailsModel.setProduction_rejection_quantity(existingSummMaterislStock.getProduction_rejection_quantity() + (detailsObject.getProduction_rejection_quantity()));
                    productRmStockDetailsModel.setProduction_rejection_weight(existingSummMaterislStock.getProduction_rejection_weight() + (detailsObject.getProduction_rejection_quantity()));
//                    productRmStockDetailsModel.setAssembly_production_issue_quantity(existingSummMaterislStock.getAssembly_production_issue_quantity() + (detailsObject.getAssembly_production_issue_quantity()));
//                    productRmStockDetailsModel.setAssembly_production_issue_weight(existingSummMaterislStock.getAssembly_production_issue_weight() + (detailsObject.getAssembly_production_issue_weight()));
                    productRmStockDetailsModel.setSales_no_of_boxes(existingSummMaterislStock.getSales_no_of_boxes() + (detailsObject.getSales_no_of_boxes()));
                    productRmStockDetailsModel.setSales_quantity(existingSummMaterislStock.getSales_quantity() + (detailsObject.getSales_quantity()));
                    productRmStockDetailsModel.setSales_weight(existingSummMaterislStock.getSales_weight() + (detailsObject.getSales_weight()));
                    productRmStockDetailsModel.setSales_return_quantity(existingSummMaterislStock.getSales_return_quantity() + (detailsObject.getSales_return_quantity()));
                    productRmStockDetailsModel.setSales_return_weight(existingSummMaterislStock.getSales_return_weight() + (detailsObject.getSales_return_weight()));
                    productRmStockDetailsModel.setSales_rejection_quantity(existingSummMaterislStock.getSales_rejection_quantity() + (detailsObject.getSales_rejection_quantity()));
                    productRmStockDetailsModel.setSales_rejection_weight(existingSummMaterislStock.getSales_rejection_weight() + (detailsObject.getSales_rejection_weight()));
                    productRmStockDetailsModel.setSales_rejection_no_of_boxes(existingSummMaterislStock.getSales_rejection_no_of_boxes() + (detailsObject.getSales_rejection_no_of_boxes()));

//                    productRmStockDetailsModel.setCustomer_receipt_quantity(existingSummMaterislStock.getCustomer_receipt_quantity() + (detailsObject.getCustomer_receipt_quantity()));
//                    productRmStockDetailsModel.setCustomer_receipt_weight(existingSummMaterislStock.getCustomer_receipt_weight() + (detailsObject.getCustomer_receipt_weight()));
//                    productRmStockDetailsModel.setCustomer_return_quantity(existingSummMaterislStock.getCustomer_return_quantity() + (detailsObject.getCustomer_return_quantity()));
//                    productRmStockDetailsModel.setCustomer_return_weight(existingSummMaterislStock.getCustomer_return_weight() + (detailsObject.getCustomer_return_weight()));
//                    productRmStockDetailsModel.setCustomer_rejection_weight(existingSummMaterislStock.getCustomer_rejection_weight() + (detailsObject.getCustomer_rejection_weight()));
//                    productRmStockDetailsModel.setCustomer_rejection_quantity(existingSummMaterislStock.getCustomer_rejection_quantity() + (detailsObject.getCustomer_rejection_quantity()));
                    productRmStockDetailsModel.setTransfer_issue_quantity(existingSummMaterislStock.getTransfer_issue_quantity() + (detailsObject.getTransfer_issue_quantity()));
                    productRmStockDetailsModel.setTransfer_issue_weight(existingSummMaterislStock.getTransfer_issue_weight() + (detailsObject.getTransfer_issue_weight()));
                    if (iuFlag.equalsIgnoreCase("Increase")) {
                        productRmStockDetailsModel.setExcess_quantity(existingSummMaterislStock.getExcess_quantity() + (detailsObject.getExcess_quantity()));
                        productRmStockDetailsModel.setExcess_weight(existingSummMaterislStock.getExcess_weight() + (detailsObject.getExcess_weight()));
                        productRmStockDetailsModel.setTransfer_receipt_quantity(existingSummMaterislStock.getTransfer_receipt_quantity() + (detailsObject.getTransfer_receipt_quantity()));
                        productRmStockDetailsModel.setTransfer_receipt_weight(existingSummMaterislStock.getTransfer_receipt_weight() + (detailsObject.getTransfer_receipt_weight()));
                    }
//                    productRmStockDetailsModel.setOutsources_out_quantity(existingSummMaterislStock.getOutsources_out_quantity() + (detailsObject.getOutsources_out_quantity()));
//                    productRmStockDetailsModel.setOutsources_out_weight(existingSummMaterislStock.getOutsources_out_weight() + (detailsObject.getOutsources_out_weight()));
//                    productRmStockDetailsModel.setOutsources_in_quantity(existingSummMaterislStock.getOutsources_in_quantity() + (detailsObject.getOutsources_in_quantity()));
//                    productRmStockDetailsModel.setOutsources_in_weight(existingSummMaterislStock.getOutsources_in_weight() + (detailsObject.getOutsources_in_weight()));
//                    productRmStockDetailsModel.setOutsources_rejection_quantity(existingSummMaterislStock.getOutsources_rejection_quantity() + (detailsObject.getOutsources_rejection_quantity()));
//                    productRmStockDetailsModel.setOutsources_rejection_weight(existingSummMaterislStock.getOutsources_rejection_weight() + (detailsObject.getOutsources_rejection_weight()));
//                    productRmStockDetailsModel.setLoan_receipt_quantity(existingSummMaterislStock.getLoan_receipt_quantity() + (detailsObject.getLoan_receipt_quantity()));
//                    productRmStockDetailsModel.setLoan_receipt_weight(existingSummMaterislStock.getLoan_receipt_weight() + (detailsObject.getLoan_receipt_weight()));
//                    productRmStockDetailsModel.setLoan_receipt_boxes(existingSummMaterislStock.getLoan_receipt_boxes() + (detailsObject.getLoan_receipt_boxes()));
//                    productRmStockDetailsModel.setLoan_issue_quantity(existingSummMaterislStock.getLoan_issue_quantity() + (detailsObject.getLoan_issue_quantity()));
//                    productRmStockDetailsModel.setLoan_issue_weight(existingSummMaterislStock.getLoan_issue_weight() + (detailsObject.getLoan_issue_weight()));
//                    productRmStockDetailsModel.setLoan_issue_boxes(existingSummMaterislStock.getLoan_issue_boxes() + (detailsObject.getLoan_issue_boxes()));
//                    productRmStockDetailsModel.setCancel_quantity(existingSummMaterislStock.getCancel_quantity() + (detailsObject.getCancel_quantity()));
//                    productRmStockDetailsModel.setCancel_weight(existingSummMaterislStock.getCancel_weight() + (detailsObject.getCancel_weight()));
//                    productRmStockDetailsModel.setDifference_quantity(existingSummMaterislStock.getDifference_quantity() + (detailsObject.getDifference_quantity()));
//                    productRmStockDetailsModel.setDifference_weight(existingSummMaterislStock.getDifference_weight() + (detailsObject.getDifference_weight()));
                    productRmStockDetailsModel.setClosing_balance_quantity(existingSummMaterislStock.getClosing_balance_quantity() + (detailsObject.getClosing_balance_quantity()));
                    productRmStockDetailsModel.setClosing_balance_weight(existingSummMaterislStock.getClosing_balance_weight() + (detailsObject.getClosing_balance_weight()));
                    productRmStockDetailsModel.setOpening_no_of_boxes(existingSummMaterislStock.getOpening_no_of_boxes() + (detailsObject.getOpening_no_of_boxes()));
                    productRmStockDetailsModel.setPurchase_no_of_boxes(existingSummMaterislStock.getPurchase_no_of_boxes() + (detailsObject.getPurchase_no_of_boxes()));
                    productRmStockDetailsModel.setReserve_no_of_boxes(existingSummMaterislStock.getReserve_no_of_boxes() + (detailsObject.getReserve_no_of_boxes()));
                    productRmStockDetailsModel.setIssue_no_of_boxes(existingSummMaterislStock.getIssue_no_of_boxes() + (detailsObject.getIssue_no_of_boxes()));
                    productRmStockDetailsModel.setClosing_no_of_boxes(existingSummMaterislStock.getClosing_no_of_boxes() + (detailsObject.getClosing_no_of_boxes()));

                    productRmStockDetailsModel.setSupplier_return_quantity(existingSummMaterislStock.getSupplier_return_quantity() + (detailsObject.getSupplier_return_quantity()));
                    productRmStockDetailsModel.setSupplier_return_weight(existingSummMaterislStock.getSupplier_return_weight() + (detailsObject.getSupplier_return_weight()));
                    productRmStockDetailsModel.setSupplier_return_boxes(existingSummMaterislStock.getSupplier_return_boxes() + (detailsObject.getSupplier_return_boxes()));

                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
                    batchObject.put("goods_receipt_no", existingSummMaterislStock.getGoods_receipt_no());
                    batchObject.put("stock_date", existingSummMaterislStock.getStock_date());
                    batchObject.put("product_material_id", existingSummMaterislStock.getProduct_rm_id());
                    batchObject.put("batch_no", existingSummMaterislStock.getBatch_no());
                    batchList.add(batchObject);

                } else {
                    productRmStockDetailsModel.setCompany_id(detailsObject.getCompany_id());
                    productRmStockDetailsModel.setCompany_branch_id(detailsObject.getCompany_branch_id());
                    productRmStockDetailsModel.setFinancial_year(detailsObject.getFinancial_year());
                    productRmStockDetailsModel.setSupplier_id(detailsObject.getSupplier_id());
                    productRmStockDetailsModel.setProduct_type_group(detailsObject.getProduct_type_group());
                    productRmStockDetailsModel.setProduct_type_id(detailsObject.getProduct_type_id());
                    productRmStockDetailsModel.setProduct_rm_id(detailsObject.getProduct_rm_id());
                    productRmStockDetailsModel.setProduct_material_unit_id(detailsObject.getProduct_material_unit_id());
                    productRmStockDetailsModel.setProduct_material_packing_id(detailsObject.getProduct_material_packing_id());

                    productRmStockDetailsModel.setBatch_rate(detailsObject.getBatch_rate());
                    productRmStockDetailsModel.setBatch_no(detailsObject.getBatch_no());
                    productRmStockDetailsModel.setBatch_expiry_date(detailsObject.getBatch_expiry_date());
                    productRmStockDetailsModel.setGoods_receipt_no(detailsObject.getGoods_receipt_no());
                    productRmStockDetailsModel.setSales_order_no(detailsObject.getSales_order_no());
                    productRmStockDetailsModel.setStock_date(detailsObject.getStock_date());
                    productRmStockDetailsModel.setPending_quantity(detailsObject.getPending_quantity());
                    productRmStockDetailsModel.setPending_weight(detailsObject.getPending_weight());
                    productRmStockDetailsModel.setReserve_quantity(detailsObject.getReserve_quantity());
                    productRmStockDetailsModel.setReserve_weight(detailsObject.getReserve_weight());
                    productRmStockDetailsModel.setExcess_quantity(detailsObject.getExcess_quantity());
                    productRmStockDetailsModel.setExcess_weight(detailsObject.getExcess_weight());
                    productRmStockDetailsModel.setPree_closed_quantity(detailsObject.getPree_closed_quantity());
                    productRmStockDetailsModel.setPree_closed_weight(detailsObject.getPree_closed_weight());
                    productRmStockDetailsModel.setPurchase_quantity(detailsObject.getPurchase_quantity());
                    productRmStockDetailsModel.setPurchase_weight(detailsObject.getPurchase_weight());
                    productRmStockDetailsModel.setPurchase_return_quantity(detailsObject.getPurchase_return_quantity());
                    productRmStockDetailsModel.setPurchase_return_weight(detailsObject.getPurchase_return_weight());
                    productRmStockDetailsModel.setPurchase_rejection_quantity(detailsObject.getPurchase_rejection_quantity());
                    productRmStockDetailsModel.setPurchase_rejection_weight(detailsObject.getPurchase_rejection_weight());
                    productRmStockDetailsModel.setJobcard_quantity(detailsObject.getJobcard_quantity());
                    productRmStockDetailsModel.setJobcard_weight(detailsObject.getJobcard_weight());
                    productRmStockDetailsModel.setProduction_issue_quantity(detailsObject.getProduction_issue_quantity());
                    productRmStockDetailsModel.setProduction_issue_weight(detailsObject.getProduction_issue_weight());
                    productRmStockDetailsModel.setProduction_issue_return_quantity(detailsObject.getProduction_issue_return_quantity());
                    productRmStockDetailsModel.setProduction_issue_return_weight(detailsObject.getProduction_issue_return_weight());
                    productRmStockDetailsModel.setProduction_issue_rejection_quantity(detailsObject.getProduction_issue_rejection_quantity());
                    productRmStockDetailsModel.setProduction_issue_rejection_weight(detailsObject.getProduction_issue_rejection_weight());
                    productRmStockDetailsModel.setProduction_no_of_boxes(detailsObject.getProduction_no_of_boxes());
                    productRmStockDetailsModel.setProduction_quantity(detailsObject.getProduction_quantity());
                    productRmStockDetailsModel.setProduction_weight(detailsObject.getProduction_weight());
                    productRmStockDetailsModel.setProduction_return_quantity(detailsObject.getProduction_return_quantity());
                    productRmStockDetailsModel.setProduction_return_weight(detailsObject.getProduction_return_weight());
                    productRmStockDetailsModel.setProduction_rejection_quantity(detailsObject.getProduction_rejection_quantity());
                    productRmStockDetailsModel.setProduction_rejection_weight(detailsObject.getProduction_rejection_quantity());
                    productRmStockDetailsModel.setAssembly_production_issue_quantity(detailsObject.getAssembly_production_issue_quantity());
                    productRmStockDetailsModel.setAssembly_production_issue_weight(detailsObject.getAssembly_production_issue_weight());
                    productRmStockDetailsModel.setSales_no_of_boxes(detailsObject.getSales_no_of_boxes());
                    productRmStockDetailsModel.setSales_quantity(detailsObject.getSales_quantity());
                    productRmStockDetailsModel.setSales_weight(detailsObject.getSales_weight());
                    productRmStockDetailsModel.setSales_return_quantity(detailsObject.getSales_return_quantity());
                    productRmStockDetailsModel.setSales_return_weight(detailsObject.getSales_return_weight());
                    productRmStockDetailsModel.setSales_rejection_quantity(detailsObject.getSales_rejection_quantity());
                    productRmStockDetailsModel.setSales_rejection_weight(detailsObject.getSales_rejection_weight());
                    productRmStockDetailsModel.setSales_rejection_no_of_boxes(detailsObject.getSales_rejection_no_of_boxes());
                    productRmStockDetailsModel.setCustomer_receipt_quantity(detailsObject.getCustomer_receipt_quantity());
                    productRmStockDetailsModel.setCustomer_receipt_weight(detailsObject.getCustomer_receipt_weight());
                    productRmStockDetailsModel.setCustomer_return_quantity(detailsObject.getCustomer_return_quantity());
                    productRmStockDetailsModel.setCustomer_return_weight(detailsObject.getCustomer_return_weight());
                    productRmStockDetailsModel.setCustomer_rejection_weight(detailsObject.getCustomer_rejection_weight());
                    productRmStockDetailsModel.setCustomer_rejection_quantity(detailsObject.getCustomer_rejection_quantity());
                    productRmStockDetailsModel.setTransfer_issue_quantity(detailsObject.getTransfer_issue_quantity());
                    productRmStockDetailsModel.setTransfer_issue_weight(detailsObject.getTransfer_issue_weight());
                    productRmStockDetailsModel.setTransfer_receipt_quantity(detailsObject.getTransfer_receipt_quantity());
                    productRmStockDetailsModel.setTransfer_receipt_weight(detailsObject.getTransfer_receipt_weight());
                    productRmStockDetailsModel.setOutsources_out_quantity(detailsObject.getOutsources_out_quantity());
                    productRmStockDetailsModel.setOutsources_out_weight(detailsObject.getOutsources_out_weight());
                    productRmStockDetailsModel.setOutsources_in_quantity(detailsObject.getOutsources_in_quantity());
                    productRmStockDetailsModel.setOutsources_in_weight(detailsObject.getOutsources_in_weight());
                    productRmStockDetailsModel.setOutsources_rejection_quantity(detailsObject.getOutsources_rejection_quantity());
                    productRmStockDetailsModel.setOutsources_rejection_weight(detailsObject.getOutsources_rejection_weight());
                    productRmStockDetailsModel.setLoan_receipt_quantity(detailsObject.getLoan_receipt_quantity());
                    productRmStockDetailsModel.setLoan_receipt_weight(detailsObject.getLoan_receipt_weight());
                    productRmStockDetailsModel.setLoan_receipt_boxes(detailsObject.getLoan_receipt_boxes());
                    productRmStockDetailsModel.setLoan_issue_quantity(detailsObject.getLoan_issue_quantity());
                    productRmStockDetailsModel.setLoan_issue_weight(detailsObject.getLoan_issue_weight());
                    productRmStockDetailsModel.setLoan_issue_boxes(detailsObject.getLoan_issue_boxes());
                    productRmStockDetailsModel.setCancel_quantity(detailsObject.getCancel_quantity());
                    productRmStockDetailsModel.setCancel_weight(detailsObject.getCancel_weight());
                    productRmStockDetailsModel.setDifference_quantity(detailsObject.getDifference_quantity());
                    productRmStockDetailsModel.setDifference_weight(detailsObject.getDifference_weight());
                    productRmStockDetailsModel.setClosing_balance_quantity(detailsObject.getClosing_balance_quantity());
                    productRmStockDetailsModel.setClosing_balance_weight(detailsObject.getClosing_balance_weight());
                    productRmStockDetailsModel.setOpening_quantity(detailsObject.getOpening_quantity());
                    productRmStockDetailsModel.setOpening_weight(detailsObject.getOpening_weight());
                    productRmStockDetailsModel.setGodown_id(detailsObject.getGodown_id());
                    productRmStockDetailsModel.setGodown_section_id(detailsObject.getGodown_section_id());
                    productRmStockDetailsModel.setGodown_section_beans_id(detailsObject.getGodown_section_beans_id());
                    productRmStockDetailsModel.setTotal_box_weight(detailsObject.getTotal_box_weight());
                    productRmStockDetailsModel.setTotal_quantity_in_box(detailsObject.getTotal_quantity_in_box());
                    productRmStockDetailsModel.setWeight_per_box_item(detailsObject.getWeight_per_box_item());
                    productRmStockDetailsModel.setOpening_no_of_boxes(detailsObject.getOpening_no_of_boxes());
                    productRmStockDetailsModel.setPurchase_no_of_boxes(detailsObject.getPurchase_no_of_boxes());
                    productRmStockDetailsModel.setReserve_no_of_boxes(detailsObject.getReserve_no_of_boxes());
                    productRmStockDetailsModel.setIssue_no_of_boxes(detailsObject.getIssue_no_of_boxes());
                    productRmStockDetailsModel.setClosing_no_of_boxes(detailsObject.getClosing_no_of_boxes());

                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
                    batchObject.put("goods_receipt_no", detailsObject.getGoods_receipt_no());
                    batchObject.put("stock_date", detailsObject.getStock_date());
                    batchObject.put("product_material_id", detailsObject.getProduct_rm_id());
                    batchObject.put("batch_no", detailsObject.getBatch_no());
                    batchList.add(batchObject);
                }
            });
        }

        if (getStockTrackingDetails != null && getStockTrackingDetails.size() != 0) {
            AtomicReference<List<CSmProductStockTracking>> productRmStockTrackingDetailsListCopy = new AtomicReference<>();

            if (productRmStockTrackingDetailsList != null) {
                List<CSmProductStockTracking> trackingList = productRmStockTrackingDetailsList.get().stream()
                        .map(details -> {
                            CSmProductStockTracking copy = new CSmProductStockTracking();
                            BeanUtils.copyProperties(details, copy);
                            return copy;
                        })
                        .collect(Collectors.toList());

                productRmStockTrackingDetailsListCopy.set(trackingList);
            }

            getStockTrackingDetails.forEach(trackingObject -> {
                CSmProductStockTracking smProductStockTracking = new CSmProductStockTracking();

                String material_id = trackingObject.getProduct_material_id();
                String good_receipt_no = trackingObject.getGoods_receipt_no();

//				Find the object in productRmStockDetailsList based on material_id & good receipt no
                Optional<CSmProductStockTracking> matchingObject = null;
                matchingObject = productRmStockTrackingDetailsList.get().stream()
                        .filter(item -> material_id.equals(item.getProduct_material_id())
                                && good_receipt_no.equals(item.getGoods_receipt_no())
                                && !item.is_stock_consumed())
                        .findFirst();

                if (!matchingObject.isPresent()) {
//                  from list added to save we need to find object after consumption
                    matchingObject = addProductStockTracking.stream()
                            .filter(item -> material_id.equals(item.getProduct_material_id())
                                    && good_receipt_no.equals(item.getGoods_receipt_no())
                                    && !item.is_stock_consumed())
                            .findFirst();

                }

                if (matchingObject.isPresent()) {
                    smProductStockTracking = matchingObject.get();

                    if (!trackingObject.isStockAddOrConsume()) {    // if it is stock consume then isStockAddOrConsume status will be false
                        smProductStockTracking.setConsumption_no(trackingObject.getConsumption_no());
                        smProductStockTracking.setConsumption_detail_no(trackingObject.getConsumption_detail_no());
                        smProductStockTracking.setConsumption_date(trackingObject.getConsumption_date());
                        smProductStockTracking.setConsumption_location(trackingObject.getConsumption_location());
                        smProductStockTracking.setConsumption_quantity(trackingObject.getConsumption_quantity());
                        smProductStockTracking.setCreated_by(trackingObject.getCreated_by());
                        smProductStockTracking.setModified_by(trackingObject.getCreated_by());
                        smProductStockTracking.set_stock_consumed(true);
                    } else {
                        smProductStockTracking.setStock_quantity(smProductStockTracking.getStock_quantity() + trackingObject.getStock_quantity());
                    }


//					Add one new entry after consumption
                    if (matchingObject.get().getStock_quantity() - smProductStockTracking.getConsumption_quantity() > 0 && !trackingObject.isStockAddOrConsume()) {

                        CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();
                        cSmProductStockTracking.setCompany_id(matchingObject.get().getCompany_id());
                        cSmProductStockTracking.setCompany_branch_id(matchingObject.get().getCompany_branch_id());
                        cSmProductStockTracking.setFinancial_year(matchingObject.get().getFinancial_year());
                        cSmProductStockTracking.setGoods_receipt_no(matchingObject.get().getGoods_receipt_no());
                        cSmProductStockTracking.setProduct_material_id(matchingObject.get().getProduct_material_id());
                        cSmProductStockTracking.setStock_date(matchingObject.get().getStock_date());
                        cSmProductStockTracking.setStock_quantity(matchingObject.get().getStock_quantity() - smProductStockTracking.getConsumption_quantity());
                        cSmProductStockTracking.setProduct_material_unit_id(matchingObject.get().getProduct_material_unit_id());
                        cSmProductStockTracking.setStock_type(matchingObject.get().getStock_type());
                        cSmProductStockTracking.setCreated_by(matchingObject.get().getCreated_by());

                        addProductStockTracking.add(cSmProductStockTracking);

                    }

                } else {
                    smProductStockTracking.setCompany_id(trackingObject.getCompany_id());
                    smProductStockTracking.setCompany_branch_id(trackingObject.getCompany_branch_id());
                    smProductStockTracking.setFinancial_year(trackingObject.getFinancial_year());
                    smProductStockTracking.setGoods_receipt_no(trackingObject.getGoods_receipt_no());
                    smProductStockTracking.setProduct_material_id(trackingObject.getProduct_material_id());
                    smProductStockTracking.setStock_date(trackingObject.getStock_date());
                    smProductStockTracking.setStock_quantity(trackingObject.getStock_quantity());
                    smProductStockTracking.setProduct_material_unit_id(trackingObject.getProduct_material_unit_id());
                    smProductStockTracking.setStock_type(trackingObject.getStock_type());
                    smProductStockTracking.setCreated_by(trackingObject.getCreated_by());
                }

                addProductStockTracking.add(smProductStockTracking);
            });
        }
        if (!addProductRmStockSummaryList.isEmpty() && addProductRmStockSummaryList != null) {
            iSmProductRmStockSummaryRepository.saveAll(addProductRmStockSummaryList);                // STore details in sm_product_rm_stock_summary
        }
        if (!addProductRmStockDetailsList.isEmpty() && addProductRmStockDetailsList != null) {
            iSmProductRmStockDetailsRepository.saveAll(addProductRmStockDetailsList);                    // STore details in sm_product_rm_stock_details

        }
        if (addProductStockTracking != null && !addProductStockTracking.isEmpty()) {
            iSmProductStockTrackingRepository.saveAll(addProductStockTracking);                      // STore details in sm_product_stock_tracking
        }
        response.put("issuedBatchList", batchList);
        response.put("Success", 1);
        response.put("message", "Stock " + iuFlag + " updated successfully!...");
        return response;
//        Map<String, Object> response = new HashMap<>();
////		try {
////  ---------------------------------------------------------- Raw material Stock Updation--------------------------------------------------------------------------------------
////			Create list to add object to save stock details & Summary
//        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
//        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
//        List<CSmProductStockTracking> addProductStockTracking = new ArrayList<>();
//
////			Get Rm & Fg Stock
//        List<CSmProductRmStockSummaryModel> getRmStockSummary = (List<CSmProductRmStockSummaryModel>) stockDetails.get("RmStockSummary");
//        List<CSmProductRmStockDetailsModel> getRmStockDetails = (List<CSmProductRmStockDetailsModel>) stockDetails.get("RmStockDetails");
//        List<CSmProductStockTracking> getStockTrackingDetails = (List<CSmProductStockTracking>) stockDetails.get("StockTracking");
//
////        boolean is_product_type_raw_material = false;
////          Get distinct material Ids
//        List<String> distinctMaterialIds = null;
//        if (getRmStockDetails != null && getRmStockDetails.size() != 0) {
//            distinctMaterialIds = getRmStockDetails.stream().map(CSmProductRmStockDetailsModel::getProduct_rm_id).distinct().collect(Collectors.toList());
//        }
//
////			Get data from stock table
//        List<CSmProductRmStockSummaryModel> productRmStockSummaryList = distinctMaterialIds != null ? iSmProductRmStockSummaryRepository.FnGetAllProductRmStockSummary(distinctMaterialIds, company_id) : null;
//        List<CSmProductRmStockDetailsModel> productRmStockDetailsList = distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetails(distinctMaterialIds, company_id) : null;
//        List<CSmProductStockTracking> productRmStockTrackingDetailsList = distinctMaterialIds != null ? iSmProductStockTrackingRepository.FnGetAllProductTrackingDetails(distinctMaterialIds, company_id) : null;
//
////          Check incoming stock is available in our stock then if available the update it or add it (For Raw material stock summary table)
//        if (getRmStockSummary != null && getRmStockSummary.size() != 0) {
//            getRmStockSummary.forEach(summObject -> {
//                CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
//                String material_id = summObject.getProduct_rm_id();
//                Integer godown_id = summObject.getGodown_id();
//                Integer godown_section_id = summObject.getPrev_godown_section_id() != null ? summObject.getPrev_godown_section_id() : summObject.getGodown_section_id();
//                Integer godown_section_beans_id = summObject.getPrev_godown_section_id() != null ? summObject.getPrev_godown_section_beans_id() : summObject.getGodown_section_beans_id();
//
////				    Find the object in productRmStockDetailsList based on material_id
//                Optional<CSmProductRmStockSummaryModel> matchingObject = productRmStockSummaryList.stream()
//                        .filter(item -> material_id.equals(item.getProduct_rm_id()) &&
//                                Objects.equals(company_id, item.getCompany_id()) &&
//                                Objects.equals(godown_id, item.getGodown_id()) &&
//                                Objects.equals(godown_section_id, item.getGodown_section_id()) &&
//                                Objects.equals(godown_section_beans_id, item.getGodown_section_beans_id()))
//                        .findFirst();
//
//
////                  Matching object present using material Id then update it otherwise insert it
//                if (matchingObject.isPresent()) {
//                    CSmProductRmStockSummaryModel existingSummMaterislStock = matchingObject.get();
//
//                    BeanUtils.copyProperties(existingSummMaterislStock, productRmStockSummaryModel);
//
//                    productRmStockSummaryModel.setCustomer_id(summObject.getCustomer_id());
//                    productRmStockSummaryModel.setOrder_quantity(existingSummMaterislStock.getOrder_quantity() + (summObject.getOrder_quantity()));
//                    productRmStockSummaryModel.setOrder_weight(existingSummMaterislStock.getOrder_weight() + (summObject.getOrder_weight()));
//                    productRmStockSummaryModel.setPending_quantity(existingSummMaterislStock.getPending_quantity() + (summObject.getPending_quantity()));
//                    productRmStockSummaryModel.setPending_weight(existingSummMaterislStock.getPending_weight() + (summObject.getPending_weight()));
//                    productRmStockSummaryModel.setReserve_quantity(existingSummMaterislStock.getReserve_quantity() + (summObject.getReserve_quantity()));
//                    productRmStockSummaryModel.setReserve_weight(existingSummMaterislStock.getReserve_weight() + (summObject.getReserve_weight()));
//                    productRmStockSummaryModel.setExcess_quantity(existingSummMaterislStock.getExcess_quantity() + (summObject.getExcess_quantity()));
//                    productRmStockSummaryModel.setExcess_weight(existingSummMaterislStock.getExcess_weight() + (summObject.getExcess_weight()));
//                    productRmStockSummaryModel.setPree_closed_quantity(existingSummMaterislStock.getPree_closed_quantity() + (summObject.getPree_closed_quantity()));
//                    productRmStockSummaryModel.setPree_closed_weight(existingSummMaterislStock.getPree_closed_weight() + (summObject.getPree_closed_weight()));
//                    productRmStockSummaryModel.setPurchase_quantity(existingSummMaterislStock.getPurchase_quantity() + (summObject.getPurchase_quantity()));
//                    productRmStockSummaryModel.setPurchase_weight(existingSummMaterislStock.getPurchase_weight() + (summObject.getPurchase_weight()));
//                    productRmStockSummaryModel.setPurchase_return_quantity(existingSummMaterislStock.getPurchase_return_quantity() + (summObject.getPurchase_return_quantity()));
//                    productRmStockSummaryModel.setPurchase_return_weight(existingSummMaterislStock.getPurchase_return_weight() + (summObject.getPurchase_return_weight()));
//                    productRmStockSummaryModel.setPurchase_rejection_quantity(existingSummMaterislStock.getPurchase_rejection_quantity() + (summObject.getPurchase_rejection_quantity()));
//                    productRmStockSummaryModel.setPurchase_rejection_weight(existingSummMaterislStock.getPurchase_rejection_weight() + (summObject.getPurchase_rejection_weight()));
//                    productRmStockSummaryModel.setJobcard_quantity(existingSummMaterislStock.getJobcard_quantity() + (summObject.getJobcard_quantity()));
//                    productRmStockSummaryModel.setJobcard_weight(existingSummMaterislStock.getJobcard_weight() + (summObject.getJobcard_weight()));
//                    productRmStockSummaryModel.setProduction_issue_quantity(existingSummMaterislStock.getProduction_issue_quantity() + (summObject.getProduction_issue_quantity()));
//                    productRmStockSummaryModel.setProduction_issue_weight(existingSummMaterislStock.getProduction_issue_weight() + (summObject.getProduction_issue_weight()));
//                    productRmStockSummaryModel.setProduction_issue_return_quantity(existingSummMaterislStock.getProduction_issue_return_quantity() + (summObject.getProduction_issue_return_quantity()));
//                    productRmStockSummaryModel.setProduction_issue_return_weight(existingSummMaterislStock.getProduction_issue_return_weight() + (summObject.getProduction_issue_return_weight()));
//                    productRmStockSummaryModel.setProduction_issue_rejection_quantity(existingSummMaterislStock.getProduction_issue_rejection_quantity() + (summObject.getProduction_issue_rejection_quantity()));
//                    productRmStockSummaryModel.setProduction_issue_rejection_weight(existingSummMaterislStock.getProduction_issue_rejection_weight() + (summObject.getProduction_issue_rejection_weight()));
//                    productRmStockSummaryModel.setProduction_quantity(existingSummMaterislStock.getProduction_quantity() + (summObject.getProduction_quantity()));
//                    productRmStockSummaryModel.setProduction_weight(existingSummMaterislStock.getProduction_weight() + (summObject.getProduction_weight()));
//                    productRmStockSummaryModel.setProduction_return_quantity(existingSummMaterislStock.getProduction_return_quantity() + (summObject.getProduction_return_quantity()));
//                    productRmStockSummaryModel.setProduction_return_weight(existingSummMaterislStock.getProduction_return_weight() + (summObject.getProduction_return_weight()));
//                    productRmStockSummaryModel.setProduction_rejection_quantity(existingSummMaterislStock.getProduction_rejection_quantity() + (summObject.getProduction_rejection_quantity()));
//                    productRmStockSummaryModel.setProduction_rejection_weight(existingSummMaterislStock.getProduction_rejection_weight() + (summObject.getProduction_rejection_quantity()));
//                    productRmStockSummaryModel.setAssembly_production_issue_quantity(existingSummMaterislStock.getAssembly_production_issue_quantity() + (summObject.getAssembly_production_issue_quantity()));
//                    productRmStockSummaryModel.setAssembly_production_issue_weight(existingSummMaterislStock.getAssembly_production_issue_weight() + (summObject.getAssembly_production_issue_weight()));
//                    productRmStockSummaryModel.setSales_quantity(existingSummMaterislStock.getSales_quantity() + (summObject.getSales_quantity()));
//                    productRmStockSummaryModel.setSales_weight(existingSummMaterislStock.getSales_weight() + (summObject.getSales_weight()));
//                    productRmStockSummaryModel.setSales_return_quantity(existingSummMaterislStock.getSales_return_quantity() + (summObject.getSales_return_quantity()));
//                    productRmStockSummaryModel.setSales_return_weight(existingSummMaterislStock.getSales_return_weight() + (summObject.getSales_return_weight()));
//                    productRmStockSummaryModel.setSales_rejection_quantity(existingSummMaterislStock.getSales_rejection_quantity() + (summObject.getSales_rejection_quantity()));
//                    productRmStockSummaryModel.setSales_rejection_weight(existingSummMaterislStock.getSales_rejection_weight() + (summObject.getSales_rejection_weight()));
//                    productRmStockSummaryModel.setCustomer_receipt_quantity(existingSummMaterislStock.getCustomer_receipt_quantity() + (summObject.getCustomer_receipt_quantity()));
//                    productRmStockSummaryModel.setCustomer_receipt_weight(existingSummMaterislStock.getCustomer_receipt_weight() + (summObject.getCustomer_receipt_weight()));
//                    productRmStockSummaryModel.setCustomer_return_quantity(existingSummMaterislStock.getCustomer_return_quantity() + (summObject.getCustomer_return_quantity()));
//                    productRmStockSummaryModel.setCustomer_return_weight(existingSummMaterislStock.getCustomer_return_weight() + (summObject.getCustomer_return_weight()));
//                    productRmStockSummaryModel.setCustomer_rejection_weight(existingSummMaterislStock.getCustomer_rejection_weight() + (summObject.getCustomer_rejection_weight()));
//                    productRmStockSummaryModel.setCustomer_rejection_quantity(existingSummMaterislStock.getCustomer_rejection_quantity() + (summObject.getCustomer_rejection_quantity()));
//                    productRmStockSummaryModel.setTransfer_issue_quantity(existingSummMaterislStock.getTransfer_issue_quantity() + (summObject.getTransfer_issue_quantity()));
//                    productRmStockSummaryModel.setTransfer_issue_weight(existingSummMaterislStock.getTransfer_issue_weight() + (summObject.getTransfer_issue_weight()));
//                    productRmStockSummaryModel.setTransfer_receipt_quantity(existingSummMaterislStock.getTransfer_receipt_quantity() + (summObject.getTransfer_receipt_quantity()));
//                    productRmStockSummaryModel.setTransfer_receipt_weight(existingSummMaterislStock.getTransfer_receipt_weight() + (summObject.getTransfer_receipt_weight()));
//                    productRmStockSummaryModel.setOutsources_out_quantity(existingSummMaterislStock.getOutsources_out_quantity() + (summObject.getOutsources_out_quantity()));
//                    productRmStockSummaryModel.setOutsources_out_weight(existingSummMaterislStock.getOutsources_out_weight() + (summObject.getOutsources_out_weight()));
//                    productRmStockSummaryModel.setOutsources_in_quantity(existingSummMaterislStock.getOutsources_in_quantity() + (summObject.getOutsources_in_quantity()));
//                    productRmStockSummaryModel.setOutsources_in_weight(existingSummMaterislStock.getOutsources_in_weight() + (summObject.getOutsources_in_weight()));
//                    productRmStockSummaryModel.setOutsources_rejection_quantity(existingSummMaterislStock.getOutsources_rejection_quantity() + (summObject.getOutsources_rejection_quantity()));
//                    productRmStockSummaryModel.setOutsources_rejection_weight(existingSummMaterislStock.getOutsources_rejection_weight() + (summObject.getOutsources_rejection_weight()));
//                    productRmStockSummaryModel.setLoan_receipt_quantity(existingSummMaterislStock.getLoan_receipt_quantity() + (summObject.getLoan_receipt_quantity()));
//                    productRmStockSummaryModel.setLoan_receipt_weight(existingSummMaterislStock.getLoan_receipt_weight() + (summObject.getLoan_receipt_weight()));
//                    productRmStockSummaryModel.setLoan_issue_quantity(existingSummMaterislStock.getLoan_issue_quantity() + (summObject.getLoan_issue_quantity()));
//                    productRmStockSummaryModel.setLoan_issue_weight(existingSummMaterislStock.getLoan_issue_weight() + (summObject.getLoan_issue_weight()));
//                    productRmStockSummaryModel.setCancel_quantity(existingSummMaterislStock.getCancel_quantity() + (summObject.getCancel_quantity()));
//                    productRmStockSummaryModel.setCancel_weight(existingSummMaterislStock.getCancel_weight() + (summObject.getCancel_weight()));
//                    productRmStockSummaryModel.setDifference_quantity(existingSummMaterislStock.getDifference_quantity() + (summObject.getDifference_quantity()));
//                    productRmStockSummaryModel.setDifference_weight(existingSummMaterislStock.getDifference_weight() + (summObject.getDifference_weight()));
//                    productRmStockSummaryModel.setClosing_balance_quantity(existingSummMaterislStock.getClosing_balance_quantity() + (summObject.getClosing_balance_quantity()));
//                    productRmStockSummaryModel.setClosing_balance_weight(existingSummMaterislStock.getClosing_balance_weight() + (summObject.getClosing_balance_weight()));
//                    productRmStockSummaryModel.setModified_by(summObject.getCreated_by());
//                    productRmStockSummaryModel.setTotal_box_weight(summObject.getTotal_box_weight());
//                    productRmStockSummaryModel.setTotal_quantity_in_box(summObject.getTotal_quantity_in_box());
//                    productRmStockSummaryModel.setWeight_per_box_item(summObject.getWeight_per_box_item());
//                    productRmStockSummaryModel.setGodown_id(summObject.getGodown_id());
//                    productRmStockSummaryModel.setGodown_section_id(summObject.getGodown_section_id());
//                    productRmStockSummaryModel.setGodown_section_beans_id(summObject.getGodown_section_beans_id());
//
//                    addProductRmStockSummaryList.add(productRmStockSummaryModel);
//                } else {
//                    productRmStockSummaryModel.setCompany_id(summObject.getCompany_id());
//                    productRmStockSummaryModel.setCompany_branch_id(summObject.getCompany_branch_id());
//                    productRmStockSummaryModel.setFinancial_year(summObject.getFinancial_year());
//                    productRmStockSummaryModel.setCustomer_id(summObject.getCustomer_id());
//                    productRmStockSummaryModel.setProduct_type_group(summObject.getProduct_type_group());
//                    productRmStockSummaryModel.setProduct_type_id(summObject.getProduct_type_id());
//                    productRmStockSummaryModel.setProduct_rm_id(summObject.getProduct_rm_id());
//                    productRmStockSummaryModel.setProduct_material_unit_id(summObject.getProduct_material_unit_id());
//                    productRmStockSummaryModel.setProduct_material_packing_id(summObject.getProduct_material_packing_id());
//                    productRmStockSummaryModel.setCustomer_id(summObject.getCustomer_id());
//                    productRmStockSummaryModel.setOrder_quantity(summObject.getOrder_quantity());
//                    productRmStockSummaryModel.setOrder_weight(summObject.getOrder_weight());
//                    productRmStockSummaryModel.setPending_quantity(summObject.getPending_quantity());
//                    productRmStockSummaryModel.setPending_weight(summObject.getPending_weight());
//                    productRmStockSummaryModel.setReserve_quantity(summObject.getReserve_quantity());
//                    productRmStockSummaryModel.setReserve_weight(summObject.getReserve_weight());
//                    productRmStockSummaryModel.setExcess_quantity(summObject.getExcess_quantity());
//                    productRmStockSummaryModel.setExcess_weight(summObject.getExcess_weight());
//                    productRmStockSummaryModel.setPree_closed_quantity(summObject.getPree_closed_quantity());
//                    productRmStockSummaryModel.setPree_closed_weight(summObject.getPree_closed_weight());
//                    productRmStockSummaryModel.setPurchase_quantity(summObject.getPurchase_quantity());
//                    productRmStockSummaryModel.setPurchase_weight(summObject.getPurchase_weight());
//                    productRmStockSummaryModel.setPurchase_return_quantity(summObject.getPurchase_return_quantity());
//                    productRmStockSummaryModel.setPurchase_return_weight(summObject.getPurchase_return_weight());
//                    productRmStockSummaryModel.setPurchase_rejection_quantity(summObject.getPurchase_rejection_quantity());
//                    productRmStockSummaryModel.setPurchase_rejection_weight(summObject.getPurchase_rejection_weight());
//                    productRmStockSummaryModel.setJobcard_quantity(summObject.getJobcard_quantity());
//                    productRmStockSummaryModel.setJobcard_weight(summObject.getJobcard_weight());
//                    productRmStockSummaryModel.setProduction_issue_quantity(summObject.getProduction_issue_quantity());
//                    productRmStockSummaryModel.setProduction_issue_weight(summObject.getProduction_issue_weight());
//                    productRmStockSummaryModel.setProduction_issue_return_quantity(summObject.getProduction_issue_return_quantity());
//                    productRmStockSummaryModel.setProduction_issue_return_weight(summObject.getProduction_issue_return_weight());
//                    productRmStockSummaryModel.setProduction_issue_rejection_quantity(summObject.getProduction_issue_rejection_quantity());
//                    productRmStockSummaryModel.setProduction_issue_rejection_weight(summObject.getProduction_issue_rejection_weight());
//                    productRmStockSummaryModel.setProduction_quantity(summObject.getProduction_quantity());
//                    productRmStockSummaryModel.setProduction_weight(summObject.getProduction_weight());
//                    productRmStockSummaryModel.setProduction_return_quantity(summObject.getProduction_return_quantity());
//                    productRmStockSummaryModel.setProduction_return_weight(summObject.getProduction_return_weight());
//                    productRmStockSummaryModel.setProduction_rejection_quantity(summObject.getProduction_rejection_quantity());
//                    productRmStockSummaryModel.setProduction_rejection_weight(summObject.getProduction_rejection_quantity());
//                    productRmStockSummaryModel.setAssembly_production_issue_quantity(summObject.getAssembly_production_issue_quantity());
//                    productRmStockSummaryModel.setAssembly_production_issue_weight(summObject.getAssembly_production_issue_weight());
//                    productRmStockSummaryModel.setSales_quantity(summObject.getSales_quantity());
//                    productRmStockSummaryModel.setSales_weight(summObject.getSales_weight());
//                    productRmStockSummaryModel.setSales_return_quantity(summObject.getSales_return_quantity());
//                    productRmStockSummaryModel.setSales_return_weight(summObject.getSales_return_weight());
//                    productRmStockSummaryModel.setSales_rejection_quantity(summObject.getSales_rejection_quantity());
//                    productRmStockSummaryModel.setSales_rejection_weight(summObject.getSales_rejection_weight());
//                    productRmStockSummaryModel.setCustomer_receipt_quantity(summObject.getCustomer_receipt_quantity());
//                    productRmStockSummaryModel.setCustomer_receipt_weight(summObject.getCustomer_receipt_weight());
//                    productRmStockSummaryModel.setCustomer_return_quantity(summObject.getCustomer_return_quantity());
//                    productRmStockSummaryModel.setCustomer_return_weight(summObject.getCustomer_return_weight());
//                    productRmStockSummaryModel.setCustomer_rejection_weight(summObject.getCustomer_rejection_weight());
//                    productRmStockSummaryModel.setCustomer_rejection_quantity(summObject.getCustomer_rejection_quantity());
//                    productRmStockSummaryModel.setTransfer_issue_quantity(summObject.getTransfer_issue_quantity());
//                    productRmStockSummaryModel.setTransfer_issue_weight(summObject.getTransfer_issue_weight());
//                    productRmStockSummaryModel.setTransfer_receipt_quantity(summObject.getTransfer_receipt_quantity());
//                    productRmStockSummaryModel.setTransfer_receipt_weight(summObject.getTransfer_receipt_weight());
//                    productRmStockSummaryModel.setOutsources_out_quantity(summObject.getOutsources_out_quantity());
//                    productRmStockSummaryModel.setOutsources_out_weight(summObject.getOutsources_out_weight());
//                    productRmStockSummaryModel.setOutsources_in_quantity(summObject.getOutsources_in_quantity());
//                    productRmStockSummaryModel.setOutsources_in_weight(summObject.getOutsources_in_weight());
//                    productRmStockSummaryModel.setOutsources_rejection_quantity(summObject.getOutsources_rejection_quantity());
//                    productRmStockSummaryModel.setOutsources_rejection_weight(summObject.getOutsources_rejection_weight());
//                    productRmStockSummaryModel.setLoan_receipt_quantity(summObject.getLoan_receipt_quantity());
//                    productRmStockSummaryModel.setLoan_receipt_weight(summObject.getLoan_receipt_weight());
//                    productRmStockSummaryModel.setLoan_issue_quantity(summObject.getLoan_issue_quantity());
//                    productRmStockSummaryModel.setLoan_issue_weight(summObject.getLoan_issue_weight());
//                    productRmStockSummaryModel.setCancel_quantity(summObject.getCancel_quantity());
//                    productRmStockSummaryModel.setCancel_weight(summObject.getCancel_weight());
//                    productRmStockSummaryModel.setDifference_quantity(summObject.getDifference_quantity());
//                    productRmStockSummaryModel.setDifference_weight(summObject.getDifference_weight());
//                    productRmStockSummaryModel.setClosing_balance_quantity(summObject.getClosing_balance_quantity());
//                    productRmStockSummaryModel.setClosing_balance_weight(summObject.getClosing_balance_weight());
//                    productRmStockSummaryModel.setOpening_quantity(summObject.getOpening_quantity());
//                    productRmStockSummaryModel.setOpening_weight(summObject.getOpening_weight());
//                    productRmStockSummaryModel.setCreated_by(summObject.getCreated_by());
//                    productRmStockSummaryModel.setGodown_id(godown_id);
//                    productRmStockSummaryModel.setGodown_section_id(godown_section_id);
//                    productRmStockSummaryModel.setGodown_section_beans_id(godown_section_beans_id);
//                    productRmStockSummaryModel.setTotal_box_weight(summObject.getTotal_box_weight());
//                    productRmStockSummaryModel.setTotal_quantity_in_box(summObject.getTotal_quantity_in_box());
//                    productRmStockSummaryModel.setWeight_per_box_item(summObject.getWeight_per_box_item());
//
//
//                    addProductRmStockSummaryList.add(productRmStockSummaryModel);
//                }
//            });
//        }
//
////          Check incoming stock is available in our stock then if available the update it or add it (For Raw material stock details table)
//        if (getRmStockDetails != null && getRmStockDetails.size() != 0) {
//            getRmStockDetails.forEach(detailsObject -> {
//                CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();
//                String material_id = detailsObject.getProduct_rm_id();
//                String good_receipt_no = detailsObject.getGoods_receipt_no();
//
//
////				    Find the object in productRmStockDetailsList based on material_id & good receipt no
//                Optional<CSmProductRmStockDetailsModel> matchingObject = productRmStockDetailsList.stream()
//                        .filter(item -> material_id.equals(item.getProduct_rm_id()) && good_receipt_no.equals(item.getGoods_receipt_no()))
//                        .findFirst();
//
////                  Matching object present using material Id then update it otherwise insert it
//                if (matchingObject.isPresent()) {
//                    CSmProductRmStockDetailsModel existingSummMaterislStock = matchingObject.get();
//
//                    BeanUtils.copyProperties(existingSummMaterislStock, productRmStockDetailsModel);
//
////						Concat existing no & new no
//                    String existingBatchNo = existingSummMaterislStock.getBatch_no();
//                    String newBatchNo = detailsObject.getBatch_no();
//                    String existingBatchExpiryDate = existingSummMaterislStock.getBatch_expiry_date();
//                    String existingGoodsReceiptNo = existingSummMaterislStock.getGoods_receipt_no();
//                    String existingSalesOrderNo = existingSummMaterislStock.getSales_order_no();
//                    String existingCustomerOrderNo = existingSummMaterislStock.getCustomer_order_no();
//                    String existingCustomerGoodsReceiptNo = existingSummMaterislStock.getCustomer_goods_receipt_no();
//                    Integer existingGodownId = existingSummMaterislStock.getGodown_id();
//                    Integer existingGodownSectionId = existingSummMaterislStock.getGodown_section_id();
//                    Integer existingGodownSectionBeansId = existingSummMaterislStock.getGodown_section_beans_id();
//
//                    productRmStockDetailsModel.setBatch_no((newBatchNo != null && !newBatchNo.isEmpty()) ? newBatchNo : existingBatchNo);
//                    productRmStockDetailsModel.setBatch_expiry_date((detailsObject.getBatch_expiry_date() != null && !detailsObject.getBatch_expiry_date().isEmpty()) ? detailsObject.getBatch_expiry_date() : existingBatchExpiryDate);
//                    productRmStockDetailsModel.setGoods_receipt_no((detailsObject.getGoods_receipt_no() != null && !detailsObject.getGoods_receipt_no().isEmpty()) ? detailsObject.getGoods_receipt_no() : existingGoodsReceiptNo);
//                    productRmStockDetailsModel.setSales_order_no((detailsObject.getSales_order_no() != null && !detailsObject.getSales_order_no().isEmpty()) ? detailsObject.getSales_order_no() : existingSalesOrderNo);
//                    productRmStockDetailsModel.setCustomer_order_no((detailsObject.getCustomer_order_no() != null && !detailsObject.getCustomer_order_no().isEmpty()) ? detailsObject.getCustomer_order_no() : existingCustomerOrderNo);
//                    productRmStockDetailsModel.setCustomer_goods_receipt_no((detailsObject.getCustomer_goods_receipt_no() != null && !detailsObject.getCustomer_goods_receipt_no().isEmpty()) ? detailsObject.getCustomer_goods_receipt_no() : existingCustomerGoodsReceiptNo);
//                    productRmStockDetailsModel.setGodown_id((detailsObject.getGodown_id() != null && detailsObject.getGodown_id() != 0) ? detailsObject.getGodown_id() : existingGodownId);
//                    productRmStockDetailsModel.setGodown_section_id((detailsObject.getGodown_section_id() != null && detailsObject.getGodown_section_id() != 0) ? detailsObject.getGodown_section_id() : existingGodownSectionId);
//                    productRmStockDetailsModel.setGodown_section_beans_id((detailsObject.getGodown_section_beans_id() != null && detailsObject.getGodown_section_beans_id() != 0) ? detailsObject.getGodown_section_beans_id() : existingGodownSectionBeansId);
//                    productRmStockDetailsModel.setTotal_box_weight(detailsObject.getTotal_box_weight());
//                    productRmStockDetailsModel.setTotal_quantity_in_box(detailsObject.getTotal_quantity_in_box());
//                    productRmStockDetailsModel.setWeight_per_box_item(detailsObject.getWeight_per_box_item());
//
//                    productRmStockDetailsModel.setSupplier_id(detailsObject.getSupplier_id());
//                    productRmStockDetailsModel.setCustomer_id(detailsObject.getCustomer_id());
//                    productRmStockDetailsModel.setOrder_quantity(existingSummMaterislStock.getOrder_quantity() + (detailsObject.getOrder_quantity()));
//                    productRmStockDetailsModel.setOrder_weight(existingSummMaterislStock.getOrder_weight() + (detailsObject.getOrder_weight()));
//                    productRmStockDetailsModel.setPending_quantity(existingSummMaterislStock.getPending_quantity() + (detailsObject.getPending_quantity()));
//                    productRmStockDetailsModel.setPending_weight(existingSummMaterislStock.getPending_weight() + (detailsObject.getPending_weight()));
//                    productRmStockDetailsModel.setReserve_quantity(existingSummMaterislStock.getReserve_quantity() + (detailsObject.getReserve_quantity()));
//                    productRmStockDetailsModel.setReserve_weight(existingSummMaterislStock.getReserve_weight() + (detailsObject.getReserve_weight()));
//                    productRmStockDetailsModel.setExcess_quantity(existingSummMaterislStock.getExcess_quantity() + (detailsObject.getExcess_quantity()));
//                    productRmStockDetailsModel.setExcess_weight(existingSummMaterislStock.getExcess_weight() + (detailsObject.getExcess_weight()));
//                    productRmStockDetailsModel.setPree_closed_quantity(existingSummMaterislStock.getPree_closed_quantity() + (detailsObject.getPree_closed_quantity()));
//                    productRmStockDetailsModel.setPree_closed_weight(existingSummMaterislStock.getPree_closed_weight() + (detailsObject.getPree_closed_weight()));
//                    productRmStockDetailsModel.setPurchase_quantity(existingSummMaterislStock.getPurchase_quantity() + (detailsObject.getPurchase_quantity()));
//                    productRmStockDetailsModel.setPurchase_weight(existingSummMaterislStock.getPurchase_weight() + (detailsObject.getPurchase_weight()));
//                    productRmStockDetailsModel.setPurchase_return_quantity(existingSummMaterislStock.getPurchase_return_quantity() + (detailsObject.getPurchase_return_quantity()));
//                    productRmStockDetailsModel.setPurchase_return_weight(existingSummMaterislStock.getPurchase_return_weight() + (detailsObject.getPurchase_return_weight()));
//                    productRmStockDetailsModel.setPurchase_rejection_quantity(existingSummMaterislStock.getPurchase_rejection_quantity() + (detailsObject.getPurchase_rejection_quantity()));
//                    productRmStockDetailsModel.setPurchase_rejection_weight(existingSummMaterislStock.getPurchase_rejection_weight() + (detailsObject.getPurchase_rejection_weight()));
//                    productRmStockDetailsModel.setJobcard_quantity(existingSummMaterislStock.getJobcard_quantity() + (detailsObject.getJobcard_quantity()));
//                    productRmStockDetailsModel.setJobcard_weight(existingSummMaterislStock.getJobcard_weight() + (detailsObject.getJobcard_weight()));
//                    productRmStockDetailsModel.setProduction_issue_quantity(existingSummMaterislStock.getProduction_issue_quantity() + (detailsObject.getProduction_issue_quantity()));
//                    productRmStockDetailsModel.setProduction_issue_weight(existingSummMaterislStock.getProduction_issue_weight() + (detailsObject.getProduction_issue_weight()));
//                    productRmStockDetailsModel.setProduction_issue_return_quantity(existingSummMaterislStock.getProduction_issue_return_quantity() + (detailsObject.getProduction_issue_return_quantity()));
//                    productRmStockDetailsModel.setProduction_issue_return_weight(existingSummMaterislStock.getProduction_issue_return_weight() + (detailsObject.getProduction_issue_return_weight()));
//                    productRmStockDetailsModel.setProduction_issue_rejection_quantity(existingSummMaterislStock.getProduction_issue_rejection_quantity() + (detailsObject.getProduction_issue_rejection_quantity()));
//                    productRmStockDetailsModel.setProduction_issue_rejection_weight(existingSummMaterislStock.getProduction_issue_rejection_weight() + (detailsObject.getProduction_issue_rejection_weight()));
//                    productRmStockDetailsModel.setProduction_quantity(existingSummMaterislStock.getProduction_quantity() + (detailsObject.getProduction_quantity()));
//                    productRmStockDetailsModel.setProduction_weight(existingSummMaterislStock.getProduction_weight() + (detailsObject.getProduction_weight()));
//                    productRmStockDetailsModel.setProduction_return_quantity(existingSummMaterislStock.getProduction_return_quantity() + (detailsObject.getProduction_return_quantity()));
//                    productRmStockDetailsModel.setProduction_return_weight(existingSummMaterislStock.getProduction_return_weight() + (detailsObject.getProduction_return_weight()));
//                    productRmStockDetailsModel.setProduction_rejection_quantity(existingSummMaterislStock.getProduction_rejection_quantity() + (detailsObject.getProduction_rejection_quantity()));
//                    productRmStockDetailsModel.setProduction_rejection_weight(existingSummMaterislStock.getProduction_rejection_weight() + (detailsObject.getProduction_rejection_quantity()));
//                    productRmStockDetailsModel.setAssembly_production_issue_quantity(existingSummMaterislStock.getAssembly_production_issue_quantity() + (detailsObject.getAssembly_production_issue_quantity()));
//                    productRmStockDetailsModel.setAssembly_production_issue_weight(existingSummMaterislStock.getAssembly_production_issue_weight() + (detailsObject.getAssembly_production_issue_weight()));
//                    productRmStockDetailsModel.setSales_quantity(existingSummMaterislStock.getSales_quantity() + (detailsObject.getSales_quantity()));
//                    productRmStockDetailsModel.setSales_weight(existingSummMaterislStock.getSales_weight() + (detailsObject.getSales_weight()));
//                    productRmStockDetailsModel.setSales_return_quantity(existingSummMaterislStock.getSales_return_quantity() + (detailsObject.getSales_return_quantity()));
//                    productRmStockDetailsModel.setSales_return_weight(existingSummMaterislStock.getSales_return_weight() + (detailsObject.getSales_return_weight()));
//                    productRmStockDetailsModel.setSales_rejection_quantity(existingSummMaterislStock.getSales_rejection_quantity() + (detailsObject.getSales_rejection_quantity()));
//                    productRmStockDetailsModel.setSales_rejection_weight(existingSummMaterislStock.getSales_rejection_weight() + (detailsObject.getSales_rejection_weight()));
//                    productRmStockDetailsModel.setCustomer_receipt_quantity(existingSummMaterislStock.getCustomer_receipt_quantity() + (detailsObject.getCustomer_receipt_quantity()));
//                    productRmStockDetailsModel.setCustomer_receipt_weight(existingSummMaterislStock.getCustomer_receipt_weight() + (detailsObject.getCustomer_receipt_weight()));
//                    productRmStockDetailsModel.setCustomer_return_quantity(existingSummMaterislStock.getCustomer_return_quantity() + (detailsObject.getCustomer_return_quantity()));
//                    productRmStockDetailsModel.setCustomer_return_weight(existingSummMaterislStock.getCustomer_return_weight() + (detailsObject.getCustomer_return_weight()));
//                    productRmStockDetailsModel.setCustomer_rejection_weight(existingSummMaterislStock.getCustomer_rejection_weight() + (detailsObject.getCustomer_rejection_weight()));
//                    productRmStockDetailsModel.setCustomer_rejection_quantity(existingSummMaterislStock.getCustomer_rejection_quantity() + (detailsObject.getCustomer_rejection_quantity()));
//                    productRmStockDetailsModel.setTransfer_issue_quantity(existingSummMaterislStock.getTransfer_issue_quantity() + (detailsObject.getTransfer_issue_quantity()));
//                    productRmStockDetailsModel.setTransfer_issue_weight(existingSummMaterislStock.getTransfer_issue_weight() + (detailsObject.getTransfer_issue_weight()));
//                    productRmStockDetailsModel.setTransfer_receipt_quantity(existingSummMaterislStock.getTransfer_receipt_quantity() + (detailsObject.getTransfer_receipt_quantity()));
//                    productRmStockDetailsModel.setTransfer_receipt_weight(existingSummMaterislStock.getTransfer_receipt_weight() + (detailsObject.getTransfer_receipt_weight()));
//                    productRmStockDetailsModel.setOutsources_out_quantity(existingSummMaterislStock.getOutsources_out_quantity() + (detailsObject.getOutsources_out_quantity()));
//                    productRmStockDetailsModel.setOutsources_out_weight(existingSummMaterislStock.getOutsources_out_weight() + (detailsObject.getOutsources_out_weight()));
//                    productRmStockDetailsModel.setOutsources_in_quantity(existingSummMaterislStock.getOutsources_in_quantity() + (detailsObject.getOutsources_in_quantity()));
//                    productRmStockDetailsModel.setOutsources_in_weight(existingSummMaterislStock.getOutsources_in_weight() + (detailsObject.getOutsources_in_weight()));
//                    productRmStockDetailsModel.setOutsources_rejection_quantity(existingSummMaterislStock.getOutsources_rejection_quantity() + (detailsObject.getOutsources_rejection_quantity()));
//                    productRmStockDetailsModel.setOutsources_rejection_weight(existingSummMaterislStock.getOutsources_rejection_weight() + (detailsObject.getOutsources_rejection_weight()));
//                    productRmStockDetailsModel.setLoan_receipt_quantity(existingSummMaterislStock.getLoan_receipt_quantity() + (detailsObject.getLoan_receipt_quantity()));
//                    productRmStockDetailsModel.setLoan_receipt_weight(existingSummMaterislStock.getLoan_receipt_weight() + (detailsObject.getLoan_receipt_weight()));
//                    productRmStockDetailsModel.setLoan_issue_quantity(existingSummMaterislStock.getLoan_issue_quantity() + (detailsObject.getLoan_issue_quantity()));
//                    productRmStockDetailsModel.setLoan_issue_weight(existingSummMaterislStock.getLoan_issue_weight() + (detailsObject.getLoan_issue_weight()));
//                    productRmStockDetailsModel.setCancel_quantity(existingSummMaterislStock.getCancel_quantity() + (detailsObject.getCancel_quantity()));
//                    productRmStockDetailsModel.setCancel_weight(existingSummMaterislStock.getCancel_weight() + (detailsObject.getCancel_weight()));
//                    productRmStockDetailsModel.setDifference_quantity(existingSummMaterislStock.getDifference_quantity() + (detailsObject.getDifference_quantity()));
//                    productRmStockDetailsModel.setDifference_weight(existingSummMaterislStock.getDifference_weight() + (detailsObject.getDifference_weight()));
//                    productRmStockDetailsModel.setClosing_balance_quantity(existingSummMaterislStock.getClosing_balance_quantity() + (detailsObject.getClosing_balance_quantity()));
//                    productRmStockDetailsModel.setClosing_balance_weight(existingSummMaterislStock.getClosing_balance_weight() + (detailsObject.getClosing_balance_weight()));
//
//
//                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
//
//                } else {
//                    productRmStockDetailsModel.setCompany_id(detailsObject.getCompany_id());
//                    productRmStockDetailsModel.setCompany_branch_id(detailsObject.getCompany_branch_id());
//                    productRmStockDetailsModel.setFinancial_year(detailsObject.getFinancial_year());
//                    productRmStockDetailsModel.setSupplier_id(detailsObject.getSupplier_id());
//                    productRmStockDetailsModel.setCustomer_id(detailsObject.getCustomer_id());
//                    productRmStockDetailsModel.setProduct_type_group(detailsObject.getProduct_type_group());
//                    productRmStockDetailsModel.setProduct_type_id(detailsObject.getProduct_type_id());
//                    productRmStockDetailsModel.setProduct_rm_id(detailsObject.getProduct_rm_id());
//                    productRmStockDetailsModel.setProduct_material_unit_id(detailsObject.getProduct_material_unit_id());
//                    productRmStockDetailsModel.setProduct_material_packing_id(detailsObject.getProduct_material_packing_id());
//
//                    productRmStockDetailsModel.setBatch_rate(detailsObject.getBatch_rate());
//                    productRmStockDetailsModel.setBatch_no(detailsObject.getBatch_no());
//                    productRmStockDetailsModel.setBatch_expiry_date(detailsObject.getBatch_expiry_date());
//                    productRmStockDetailsModel.setGoods_receipt_no(detailsObject.getGoods_receipt_no());
//                    productRmStockDetailsModel.setSales_order_no(detailsObject.getSales_order_no());
//                    productRmStockDetailsModel.setCustomer_order_no(detailsObject.getCustomer_order_no());
//                    productRmStockDetailsModel.setCustomer_goods_receipt_no(detailsObject.getCustomer_goods_receipt_no());
//
//                    productRmStockDetailsModel.setStock_date(detailsObject.getStock_date());
//                    productRmStockDetailsModel.setCustomer_id(detailsObject.getCustomer_id());
//                    productRmStockDetailsModel.setOrder_quantity(detailsObject.getOrder_quantity());
//                    productRmStockDetailsModel.setOrder_weight(detailsObject.getOrder_weight());
//                    productRmStockDetailsModel.setPending_quantity(detailsObject.getPending_quantity());
//                    productRmStockDetailsModel.setPending_weight(detailsObject.getPending_weight());
//                    productRmStockDetailsModel.setReserve_quantity(detailsObject.getReserve_quantity());
//                    productRmStockDetailsModel.setReserve_weight(detailsObject.getReserve_weight());
//                    productRmStockDetailsModel.setExcess_quantity(detailsObject.getExcess_quantity());
//                    productRmStockDetailsModel.setExcess_weight(detailsObject.getExcess_weight());
//                    productRmStockDetailsModel.setPree_closed_quantity(detailsObject.getPree_closed_quantity());
//                    productRmStockDetailsModel.setPree_closed_weight(detailsObject.getPree_closed_weight());
//                    productRmStockDetailsModel.setPurchase_quantity(detailsObject.getPurchase_quantity());
//                    productRmStockDetailsModel.setPurchase_weight(detailsObject.getPurchase_weight());
//                    productRmStockDetailsModel.setPurchase_return_quantity(detailsObject.getPurchase_return_quantity());
//                    productRmStockDetailsModel.setPurchase_return_weight(detailsObject.getPurchase_return_weight());
//                    productRmStockDetailsModel.setPurchase_rejection_quantity(detailsObject.getPurchase_rejection_quantity());
//                    productRmStockDetailsModel.setPurchase_rejection_weight(detailsObject.getPurchase_rejection_weight());
//                    productRmStockDetailsModel.setJobcard_quantity(detailsObject.getJobcard_quantity());
//                    productRmStockDetailsModel.setJobcard_weight(detailsObject.getJobcard_weight());
//                    productRmStockDetailsModel.setProduction_issue_quantity(detailsObject.getProduction_issue_quantity());
//                    productRmStockDetailsModel.setProduction_issue_weight(detailsObject.getProduction_issue_weight());
//                    productRmStockDetailsModel.setProduction_issue_return_quantity(detailsObject.getProduction_issue_return_quantity());
//                    productRmStockDetailsModel.setProduction_issue_return_weight(detailsObject.getProduction_issue_return_weight());
//                    productRmStockDetailsModel.setProduction_issue_rejection_quantity(detailsObject.getProduction_issue_rejection_quantity());
//                    productRmStockDetailsModel.setProduction_issue_rejection_weight(detailsObject.getProduction_issue_rejection_weight());
//                    productRmStockDetailsModel.setProduction_quantity(detailsObject.getProduction_quantity());
//                    productRmStockDetailsModel.setProduction_weight(detailsObject.getProduction_weight());
//                    productRmStockDetailsModel.setProduction_return_quantity(detailsObject.getProduction_return_quantity());
//                    productRmStockDetailsModel.setProduction_return_weight(detailsObject.getProduction_return_weight());
//                    productRmStockDetailsModel.setProduction_rejection_quantity(detailsObject.getProduction_rejection_quantity());
//                    productRmStockDetailsModel.setProduction_rejection_weight(detailsObject.getProduction_rejection_quantity());
//                    productRmStockDetailsModel.setAssembly_production_issue_quantity(detailsObject.getAssembly_production_issue_quantity());
//                    productRmStockDetailsModel.setAssembly_production_issue_weight(detailsObject.getAssembly_production_issue_weight());
//                    productRmStockDetailsModel.setSales_quantity(detailsObject.getSales_quantity());
//                    productRmStockDetailsModel.setSales_weight(detailsObject.getSales_weight());
//                    productRmStockDetailsModel.setSales_return_quantity(detailsObject.getSales_return_quantity());
//                    productRmStockDetailsModel.setSales_return_weight(detailsObject.getSales_return_weight());
//                    productRmStockDetailsModel.setSales_rejection_quantity(detailsObject.getSales_rejection_quantity());
//                    productRmStockDetailsModel.setSales_rejection_weight(detailsObject.getSales_rejection_weight());
//                    productRmStockDetailsModel.setCustomer_receipt_quantity(detailsObject.getCustomer_receipt_quantity());
//                    productRmStockDetailsModel.setCustomer_receipt_weight(detailsObject.getCustomer_receipt_weight());
//                    productRmStockDetailsModel.setCustomer_return_quantity(detailsObject.getCustomer_return_quantity());
//                    productRmStockDetailsModel.setCustomer_return_weight(detailsObject.getCustomer_return_weight());
//                    productRmStockDetailsModel.setCustomer_rejection_weight(detailsObject.getCustomer_rejection_weight());
//                    productRmStockDetailsModel.setCustomer_rejection_quantity(detailsObject.getCustomer_rejection_quantity());
//                    productRmStockDetailsModel.setTransfer_issue_quantity(detailsObject.getTransfer_issue_quantity());
//                    productRmStockDetailsModel.setTransfer_issue_weight(detailsObject.getTransfer_issue_weight());
//                    productRmStockDetailsModel.setTransfer_receipt_quantity(detailsObject.getTransfer_receipt_quantity());
//                    productRmStockDetailsModel.setTransfer_receipt_weight(detailsObject.getTransfer_receipt_weight());
//                    productRmStockDetailsModel.setOutsources_out_quantity(detailsObject.getOutsources_out_quantity());
//                    productRmStockDetailsModel.setOutsources_out_weight(detailsObject.getOutsources_out_weight());
//                    productRmStockDetailsModel.setOutsources_in_quantity(detailsObject.getOutsources_in_quantity());
//                    productRmStockDetailsModel.setOutsources_in_weight(detailsObject.getOutsources_in_weight());
//                    productRmStockDetailsModel.setOutsources_rejection_quantity(detailsObject.getOutsources_rejection_quantity());
//                    productRmStockDetailsModel.setOutsources_rejection_weight(detailsObject.getOutsources_rejection_weight());
//                    productRmStockDetailsModel.setLoan_receipt_quantity(detailsObject.getLoan_receipt_quantity());
//                    productRmStockDetailsModel.setLoan_receipt_weight(detailsObject.getLoan_receipt_weight());
//                    productRmStockDetailsModel.setLoan_issue_quantity(detailsObject.getLoan_issue_quantity());
//                    productRmStockDetailsModel.setLoan_issue_weight(detailsObject.getLoan_issue_weight());
//                    productRmStockDetailsModel.setCancel_quantity(detailsObject.getCancel_quantity());
//                    productRmStockDetailsModel.setCancel_weight(detailsObject.getCancel_weight());
//                    productRmStockDetailsModel.setDifference_quantity(detailsObject.getDifference_quantity());
//                    productRmStockDetailsModel.setDifference_weight(detailsObject.getDifference_weight());
//                    productRmStockDetailsModel.setClosing_balance_quantity(detailsObject.getClosing_balance_quantity());
//                    productRmStockDetailsModel.setClosing_balance_weight(detailsObject.getClosing_balance_weight());
//                    productRmStockDetailsModel.setOpening_quantity(detailsObject.getOpening_quantity());
//                    productRmStockDetailsModel.setOpening_weight(detailsObject.getOpening_weight());
//                    productRmStockDetailsModel.setGodown_id(detailsObject.getGodown_id());
//                    productRmStockDetailsModel.setGodown_section_id(detailsObject.getGodown_section_id());
//                    productRmStockDetailsModel.setGodown_section_beans_id(detailsObject.getGodown_section_beans_id());
//                    productRmStockDetailsModel.setTotal_box_weight(detailsObject.getTotal_box_weight());
//                    productRmStockDetailsModel.setTotal_quantity_in_box(detailsObject.getTotal_quantity_in_box());
//                    productRmStockDetailsModel.setWeight_per_box_item(detailsObject.getWeight_per_box_item());
//
//                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
//                }
//            });
//        }
//
//        if (getStockTrackingDetails != null && getStockTrackingDetails.size() != 0) {
//            AtomicReference<List<CSmProductStockTracking>> productRmStockTrackingDetailsListCopy = new AtomicReference<>();
//
//            if (productRmStockTrackingDetailsList != null) {
//                List<CSmProductStockTracking> trackingList = productRmStockTrackingDetailsList.stream()
//                        .map(details -> {
//                            CSmProductStockTracking copy = new CSmProductStockTracking();
//                            BeanUtils.copyProperties(details, copy);
//                            return copy;
//                        })
//                        .collect(Collectors.toList());
//
//                productRmStockTrackingDetailsListCopy.set(trackingList);
//            }
//
//            getStockTrackingDetails.forEach(trackingObject -> {
//                CSmProductStockTracking smProductStockTracking = new CSmProductStockTracking();
//
//                String material_id = trackingObject.getProduct_material_id();
//                String good_receipt_no = trackingObject.getGoods_receipt_no();
//
////				Find the object in productRmStockDetailsList based on material_id & good receipt no
//                Optional<CSmProductStockTracking> matchingObject = null;
//                matchingObject = productRmStockTrackingDetailsList.stream()
//                        .filter(item -> material_id.equals(item.getProduct_material_id()) && good_receipt_no.equals(item.getGoods_receipt_no()) && !item.is_stock_consumed())
//                        .findFirst();
//
//                if (!matchingObject.isPresent()) {
////                  from list added to save we need to find object after consumption
//                    matchingObject = addProductStockTracking.stream()
//                            .filter(item -> material_id.equals(item.getProduct_material_id()) && good_receipt_no.equals(item.getGoods_receipt_no()) && !item.is_stock_consumed())
//                            .findFirst();
//
//                }
//
//                if (matchingObject.isPresent()) {
//                    smProductStockTracking = matchingObject.get();
//
//                    if (!trackingObject.isStockAddOrConsume()) {    // if it is stock consume then isStockAddOrConsume status will be false
//                        smProductStockTracking.setConsumption_no(trackingObject.getConsumption_no());
//                        smProductStockTracking.setConsumption_detail_no(trackingObject.getConsumption_detail_no());
//                        smProductStockTracking.setConsumption_date(trackingObject.getConsumption_date());
//                        smProductStockTracking.setConsumption_location(trackingObject.getConsumption_location());
//                        smProductStockTracking.setConsumption_quantity(trackingObject.getConsumption_quantity());
//                        smProductStockTracking.setCreated_by(trackingObject.getCreated_by());
//                        smProductStockTracking.setModified_by(trackingObject.getCreated_by());
//                        smProductStockTracking.set_stock_consumed(true);
//                    } else {
//                        smProductStockTracking.setStock_quantity(smProductStockTracking.getStock_quantity() + trackingObject.getStock_quantity());
//                    }
//
//
////					Add one new entry after consumption
//                    if (matchingObject.get().getStock_quantity() - smProductStockTracking.getConsumption_quantity() > 0 && !trackingObject.isStockAddOrConsume()) {
//
//                        CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();
//                        cSmProductStockTracking.setCompany_id(matchingObject.get().getCompany_id());
//                        cSmProductStockTracking.setCompany_branch_id(matchingObject.get().getCompany_branch_id());
//                        cSmProductStockTracking.setFinancial_year(matchingObject.get().getFinancial_year());
//                        cSmProductStockTracking.setGoods_receipt_no(matchingObject.get().getGoods_receipt_no());
//                        cSmProductStockTracking.setProduct_material_id(matchingObject.get().getProduct_material_id());
//                        cSmProductStockTracking.setStock_date(matchingObject.get().getStock_date());
//                        cSmProductStockTracking.setStock_quantity(matchingObject.get().getStock_quantity() - smProductStockTracking.getConsumption_quantity());
//                        cSmProductStockTracking.setProduct_material_unit_id(matchingObject.get().getProduct_material_unit_id());
//                        cSmProductStockTracking.setStock_type(matchingObject.get().getStock_type());
//                        cSmProductStockTracking.setCreated_by(matchingObject.get().getCreated_by());
//
//                        addProductStockTracking.add(cSmProductStockTracking);
//
//                    }
//
//                } else {
//                    smProductStockTracking.setCompany_id(trackingObject.getCompany_id());
//                    smProductStockTracking.setCompany_branch_id(trackingObject.getCompany_branch_id());
//                    smProductStockTracking.setFinancial_year(trackingObject.getFinancial_year());
//                    smProductStockTracking.setGoods_receipt_no(trackingObject.getGoods_receipt_no());
//                    smProductStockTracking.setProduct_material_id(trackingObject.getProduct_material_id());
//                    smProductStockTracking.setStock_date(trackingObject.getStock_date());
//                    smProductStockTracking.setStock_quantity(trackingObject.getStock_quantity());
//                    smProductStockTracking.setProduct_material_unit_id(trackingObject.getProduct_material_unit_id());
//                    smProductStockTracking.setStock_type(trackingObject.getStock_type());
//                    smProductStockTracking.setCreated_by(trackingObject.getCreated_by());
//                }
//
//                addProductStockTracking.add(smProductStockTracking);
//            });
//
//        }
//        if (addProductRmStockSummaryList.size() != 0 && addProductRmStockSummaryList != null) {
//            iSmProductRmStockSummaryRepository.saveAll(addProductRmStockSummaryList);                // STore details in sm_product_rm_stock_summary
//        }
//        if (addProductRmStockDetailsList.size() != 0 && addProductRmStockDetailsList != null) {
//            iSmProductRmStockDetailsRepository.saveAll(addProductRmStockDetailsList);                    // STore details in sm_product_rm_stock_details
//        }
//        if (addProductStockTracking != null && addProductStockTracking.size() != 0) {
//            iSmProductStockTrackingRepository.saveAll(addProductStockTracking);                      // STore details in sm_product_stock_tracking
//        }
//
//        response.put("Success", 1);
//        response.put("message", "Stock updated successfully!...");
//
//        return response;
    }

    @Override
    public Map<String, Object> FnAddUpdateRmStockRawMaterials(Map<String, Object> stockDetails, String iuFlag, int company_id) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> batchList = new ArrayList<>();


//  ----------------------------------------------------------  material Stock Updation--------------------------------------------------------------------------------------
//			Create list to add object to save stock details & Summary
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addProductStockTracking = new ArrayList<>();

//			Get Rm & Fg Stock
        List<CSmProductRmStockSummaryModel> getRmStockSummary = (List<CSmProductRmStockSummaryModel>) stockDetails.get("RmStockSummary");
        List<CSmProductRmStockDetailsModel> getRmStockDetails = (List<CSmProductRmStockDetailsModel>) stockDetails.get("RmStockDetails");
        List<CSmProductStockTracking> getStockTrackingDetails = (List<CSmProductStockTracking>) stockDetails.get("StockTracking");

//        boolean is_product_type_raw_material = false;
        AtomicReference<List<CSmProductRmStockSummaryModel>> productRmStockSummaryList = new AtomicReference<>();
        AtomicReference<List<CSmProductRmStockDetailsModel>> productRmStockDetailsList = new AtomicReference<>();
        AtomicReference<List<CSmProductStockTracking>> productRmStockTrackingDetailsList = new AtomicReference<>();

        // Define a date formatter matching your date string format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern as needed
        // Get today's date
        LocalDate todayDate = LocalDate.now();
        Optional<LocalDate> issue_date = Optional.empty();

//          Get distinct material Ids
        List<String> distinctMaterialIds = null;
        if (getRmStockDetails != null && !getRmStockDetails.isEmpty()) {
            distinctMaterialIds = getRmStockDetails.stream().map(CSmProductRmStockDetailsModel::getProduct_rm_id).distinct().collect(Collectors.toList());
            issue_date = getRmStockDetails.stream()
                    .map(CSmProductRmStockDetailsModel::getStock_date) // map to stock_date as String
                    .filter(Objects::nonNull) // filter out any null values to avoid NullPointerException
                    .map(date -> LocalDate.parse(date, dateFormatter)) // parse directly in stream
                    .findFirst(); // get the first element if available

        }
        productRmStockSummaryList.set(distinctMaterialIds != null ? iSmProductRmStockSummaryRepository.FnGetAllProductRmStockSummary(distinctMaterialIds, company_id) : null);

        // Get data from stock table if issue date is small then current
        if (iuFlag.equalsIgnoreCase("Decrease") && issue_date.isPresent() && issue_date.get().isBefore(todayDate)) {
            // Using map and orElse to handle the empty case
            String issueDate = issue_date.map(LocalDate::toString).orElse("");
            productRmStockDetailsList.set(distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetailsByIssueDate(distinctMaterialIds, company_id, issueDate) : null);
        } else {
            productRmStockDetailsList.set(distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetails(distinctMaterialIds, company_id) : null);
        }
        productRmStockTrackingDetailsList.set(distinctMaterialIds != null ? iSmProductStockTrackingRepository.FnGetAllProductTrackingDetails(distinctMaterialIds, company_id) : null);

//  Check incoming stock is available in our stock then if available the update it or add it (For Raw material stock summary table)
        if (getRmStockSummary != null && getRmStockSummary.size() != 0) {
            getRmStockSummary.forEach(summObject -> {
                CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
                String material_id = summObject.getProduct_rm_id();
                Integer godown_id = summObject.getGodown_id();
                Integer godown_section_id = summObject.getPrev_godown_section_id() != null ? summObject.getPrev_godown_section_id() : summObject.getGodown_section_id();
                Integer godown_section_beans_id = summObject.getPrev_godown_section_id() != null ? summObject.getPrev_godown_section_beans_id() : summObject.getGodown_section_beans_id();
                Double weight_per_cone = summObject.getWeight_per_box_item();


//				Find the object in productRmStockDetailsList based on material_id
                Optional<CSmProductRmStockSummaryModel> matchingObject = productRmStockSummaryList.get().stream()
                        .filter(item ->
                                material_id.equals(item.getProduct_rm_id()) &&
//                                        (item.getProduct_type_id() != '2' || Objects.equals(company_id, item.getCompany_id())) &&
                                        (Objects.equals(company_id, item.getCompany_id())) &&
                                        Objects.equals(godown_id, item.getGodown_id()) &&
                                        Objects.equals(godown_section_id, item.getGodown_section_id()) &&
                                        Objects.equals(godown_section_beans_id, item.getGodown_section_beans_id()) &&
                                        (weight_per_cone == null || weight_per_cone == 0.0 || Objects.equals(weight_per_cone, item.getWeight_per_box_item())))

                        .findFirst();

//              Matching object present using material Id then update it otherwise insert it
                if (matchingObject.isPresent()) {
                    CSmProductRmStockSummaryModel existingSummMaterislStock = matchingObject.get();

                    BeanUtils.copyProperties(existingSummMaterislStock, productRmStockSummaryModel);

                    productRmStockSummaryModel.setPending_quantity(existingSummMaterislStock.getPending_quantity() + (summObject.getPending_quantity()));
                    productRmStockSummaryModel.setPending_weight(existingSummMaterislStock.getPending_weight() + (summObject.getPending_weight()));
                    productRmStockSummaryModel.setReserve_quantity(existingSummMaterislStock.getReserve_quantity() + (summObject.getReserve_quantity()));
                    productRmStockSummaryModel.setReserve_weight(existingSummMaterislStock.getReserve_weight() + (summObject.getReserve_weight()));
                    productRmStockSummaryModel.setExcess_quantity(existingSummMaterislStock.getExcess_quantity() + (summObject.getExcess_quantity()));
                    productRmStockSummaryModel.setExcess_weight(existingSummMaterislStock.getExcess_weight() + (summObject.getExcess_weight()));
                    productRmStockSummaryModel.setPree_closed_quantity(existingSummMaterislStock.getPree_closed_quantity() + (summObject.getPree_closed_quantity()));
                    productRmStockSummaryModel.setPree_closed_weight(existingSummMaterislStock.getPree_closed_weight() + (summObject.getPree_closed_weight()));
                    productRmStockSummaryModel.setPurchase_quantity(existingSummMaterislStock.getPurchase_quantity() + (summObject.getPurchase_quantity()));
                    productRmStockSummaryModel.setPurchase_weight(existingSummMaterislStock.getPurchase_weight() + (summObject.getPurchase_weight()));
                    productRmStockSummaryModel.setPurchase_return_quantity(existingSummMaterislStock.getPurchase_return_quantity() + (summObject.getPurchase_return_quantity()));
                    productRmStockSummaryModel.setPurchase_return_weight(existingSummMaterislStock.getPurchase_return_weight() + (summObject.getPurchase_return_weight()));
                    productRmStockSummaryModel.setPurchase_rejection_quantity(existingSummMaterislStock.getPurchase_rejection_quantity() + (summObject.getPurchase_rejection_quantity()));
                    productRmStockSummaryModel.setPurchase_rejection_weight(existingSummMaterislStock.getPurchase_rejection_weight() + (summObject.getPurchase_rejection_weight()));
                    productRmStockSummaryModel.setJobcard_quantity(existingSummMaterislStock.getJobcard_quantity() + (summObject.getJobcard_quantity()));
                    productRmStockSummaryModel.setJobcard_weight(existingSummMaterislStock.getJobcard_weight() + (summObject.getJobcard_weight()));
                    productRmStockSummaryModel.setProduction_issue_quantity(existingSummMaterislStock.getProduction_issue_quantity() + (summObject.getProduction_issue_quantity()));
                    productRmStockSummaryModel.setProduction_issue_weight(existingSummMaterislStock.getProduction_issue_weight() + (summObject.getProduction_issue_weight()));
                    productRmStockSummaryModel.setProduction_issue_return_quantity(existingSummMaterislStock.getProduction_issue_return_quantity() + (summObject.getProduction_issue_return_quantity()));
                    productRmStockSummaryModel.setProduction_issue_return_weight(existingSummMaterislStock.getProduction_issue_return_weight() + (summObject.getProduction_issue_return_weight()));
                    productRmStockSummaryModel.setProduction_issue_rejection_quantity(existingSummMaterislStock.getProduction_issue_rejection_quantity() + (summObject.getProduction_issue_rejection_quantity()));
                    productRmStockSummaryModel.setProduction_issue_rejection_weight(existingSummMaterislStock.getProduction_issue_rejection_weight() + (summObject.getProduction_issue_rejection_weight()));
                    productRmStockSummaryModel.setProduction_no_of_boxes(existingSummMaterislStock.getProduction_no_of_boxes() + (summObject.getProduction_no_of_boxes()));
                    productRmStockSummaryModel.setProduction_quantity(existingSummMaterislStock.getProduction_quantity() + (summObject.getProduction_quantity()));
                    productRmStockSummaryModel.setProduction_weight(existingSummMaterislStock.getProduction_weight() + (summObject.getProduction_weight()));
                    productRmStockSummaryModel.setProduction_return_quantity(existingSummMaterislStock.getProduction_return_quantity() + (summObject.getProduction_return_quantity()));
                    productRmStockSummaryModel.setProduction_return_weight(existingSummMaterislStock.getProduction_return_weight() + (summObject.getProduction_return_weight()));
                    productRmStockSummaryModel.setProduction_rejection_quantity(existingSummMaterislStock.getProduction_rejection_quantity() + (summObject.getProduction_rejection_quantity()));
                    productRmStockSummaryModel.setProduction_rejection_weight(existingSummMaterislStock.getProduction_rejection_weight() + (summObject.getProduction_rejection_quantity()));
                    productRmStockSummaryModel.setAssembly_production_issue_quantity(existingSummMaterislStock.getAssembly_production_issue_quantity() + (summObject.getAssembly_production_issue_quantity()));
                    productRmStockSummaryModel.setAssembly_production_issue_weight(existingSummMaterislStock.getAssembly_production_issue_weight() + (summObject.getAssembly_production_issue_weight()));
                    productRmStockSummaryModel.setSales_no_of_boxes(existingSummMaterislStock.getSales_no_of_boxes() + (summObject.getSales_no_of_boxes()));
                    productRmStockSummaryModel.setSales_quantity(existingSummMaterislStock.getSales_quantity() + (summObject.getSales_quantity()));
                    productRmStockSummaryModel.setSales_weight(existingSummMaterislStock.getSales_weight() + (summObject.getSales_weight()));
                    productRmStockSummaryModel.setSales_return_quantity(existingSummMaterislStock.getSales_return_quantity() + (summObject.getSales_return_quantity()));
                    productRmStockSummaryModel.setSales_return_weight(existingSummMaterislStock.getSales_return_weight() + (summObject.getSales_return_weight()));
                    productRmStockSummaryModel.setSales_rejection_quantity(existingSummMaterislStock.getSales_rejection_quantity() + (summObject.getSales_rejection_quantity()));
                    productRmStockSummaryModel.setSales_rejection_weight(existingSummMaterislStock.getSales_rejection_weight() + (summObject.getSales_rejection_weight()));
                    productRmStockSummaryModel.setSales_rejection_no_of_boxes(existingSummMaterislStock.getSales_rejection_no_of_boxes() + (summObject.getSales_rejection_no_of_boxes()));
                    productRmStockSummaryModel.setCustomer_receipt_quantity(existingSummMaterislStock.getCustomer_receipt_quantity() + (summObject.getCustomer_receipt_quantity()));
                    productRmStockSummaryModel.setCustomer_receipt_weight(existingSummMaterislStock.getCustomer_receipt_weight() + (summObject.getCustomer_receipt_weight()));
                    productRmStockSummaryModel.setCustomer_return_quantity(existingSummMaterislStock.getCustomer_return_quantity() + (summObject.getCustomer_return_quantity()));
                    productRmStockSummaryModel.setCustomer_return_weight(existingSummMaterislStock.getCustomer_return_weight() + (summObject.getCustomer_return_weight()));
                    productRmStockSummaryModel.setCustomer_rejection_weight(existingSummMaterislStock.getCustomer_rejection_weight() + (summObject.getCustomer_rejection_weight()));
                    productRmStockSummaryModel.setCustomer_rejection_quantity(existingSummMaterislStock.getCustomer_rejection_quantity() + (summObject.getCustomer_rejection_quantity()));
                    productRmStockSummaryModel.setTransfer_issue_quantity(existingSummMaterislStock.getTransfer_issue_quantity() + (summObject.getTransfer_issue_quantity()));
                    productRmStockSummaryModel.setTransfer_issue_weight(existingSummMaterislStock.getTransfer_issue_weight() + (summObject.getTransfer_issue_weight()));
                    productRmStockSummaryModel.setTransfer_receipt_quantity(existingSummMaterislStock.getTransfer_receipt_quantity() + (summObject.getTransfer_receipt_quantity()));
                    productRmStockSummaryModel.setTransfer_receipt_weight(existingSummMaterislStock.getTransfer_receipt_weight() + (summObject.getTransfer_receipt_weight()));
                    productRmStockSummaryModel.setOutsources_out_quantity(existingSummMaterislStock.getOutsources_out_quantity() + (summObject.getOutsources_out_quantity()));
                    productRmStockSummaryModel.setOutsources_out_weight(existingSummMaterislStock.getOutsources_out_weight() + (summObject.getOutsources_out_weight()));
                    productRmStockSummaryModel.setOutsources_in_quantity(existingSummMaterislStock.getOutsources_in_quantity() + (summObject.getOutsources_in_quantity()));
                    productRmStockSummaryModel.setOutsources_in_weight(existingSummMaterislStock.getOutsources_in_weight() + (summObject.getOutsources_in_weight()));
                    productRmStockSummaryModel.setOutsources_rejection_quantity(existingSummMaterislStock.getOutsources_rejection_quantity() + (summObject.getOutsources_rejection_quantity()));
                    productRmStockSummaryModel.setOutsources_rejection_weight(existingSummMaterislStock.getOutsources_rejection_weight() + (summObject.getOutsources_rejection_weight()));
                    productRmStockSummaryModel.setLoan_receipt_quantity(existingSummMaterislStock.getLoan_receipt_quantity() + (summObject.getLoan_receipt_quantity()));
                    productRmStockSummaryModel.setLoan_receipt_weight(existingSummMaterislStock.getLoan_receipt_weight() + (summObject.getLoan_receipt_weight()));
                    productRmStockSummaryModel.setLoan_receipt_boxes(existingSummMaterislStock.getLoan_receipt_boxes() + (summObject.getLoan_receipt_boxes()));
                    productRmStockSummaryModel.setLoan_issue_quantity(existingSummMaterislStock.getLoan_issue_quantity() + (summObject.getLoan_issue_quantity()));
                    productRmStockSummaryModel.setLoan_issue_weight(existingSummMaterislStock.getLoan_issue_weight() + (summObject.getLoan_issue_weight()));
                    productRmStockSummaryModel.setLoan_issue_boxes(existingSummMaterislStock.getLoan_issue_boxes() + (summObject.getLoan_issue_boxes()));
                    productRmStockSummaryModel.setCancel_quantity(existingSummMaterislStock.getCancel_quantity() + (summObject.getCancel_quantity()));
                    productRmStockSummaryModel.setCancel_weight(existingSummMaterislStock.getCancel_weight() + (summObject.getCancel_weight()));
                    productRmStockSummaryModel.setDifference_quantity(existingSummMaterislStock.getDifference_quantity() + (summObject.getDifference_quantity()));
                    productRmStockSummaryModel.setDifference_weight(existingSummMaterislStock.getDifference_weight() + (summObject.getDifference_weight()));
                    productRmStockSummaryModel.setClosing_balance_quantity(existingSummMaterislStock.getClosing_balance_quantity() + (summObject.getClosing_balance_quantity()));
                    productRmStockSummaryModel.setClosing_balance_weight(existingSummMaterislStock.getClosing_balance_weight() + (summObject.getClosing_balance_weight()));
                    productRmStockSummaryModel.setModified_by(summObject.getCreated_by());
                    productRmStockSummaryModel.setGodown_id(summObject.getGodown_id());
                    productRmStockSummaryModel.setGodown_section_id(summObject.getGodown_section_id());
                    productRmStockSummaryModel.setGodown_section_beans_id(summObject.getGodown_section_beans_id());
                    productRmStockSummaryModel.setOpening_no_of_boxes(existingSummMaterislStock.getOpening_no_of_boxes() + (summObject.getOpening_no_of_boxes()));
                    productRmStockSummaryModel.setPurchase_no_of_boxes(existingSummMaterislStock.getPurchase_no_of_boxes() + (summObject.getPurchase_no_of_boxes()));
                    productRmStockSummaryModel.setReserve_no_of_boxes(existingSummMaterislStock.getReserve_no_of_boxes() + (summObject.getReserve_no_of_boxes()));
                    productRmStockSummaryModel.setIssue_no_of_boxes(existingSummMaterislStock.getIssue_no_of_boxes() + (summObject.getIssue_no_of_boxes()));
                    productRmStockSummaryModel.setClosing_no_of_boxes(existingSummMaterislStock.getClosing_no_of_boxes() + (summObject.getClosing_no_of_boxes()));

                    productRmStockSummaryModel.setSupplier_return_quantity(existingSummMaterislStock.getSupplier_return_quantity() + (summObject.getSupplier_return_quantity()));
                    productRmStockSummaryModel.setSupplier_return_weight(existingSummMaterislStock.getSupplier_return_weight() + (summObject.getSupplier_return_weight()));
                    productRmStockSummaryModel.setSupplier_return_boxes(existingSummMaterislStock.getSupplier_return_boxes() + (summObject.getSupplier_return_boxes()));
                    if (iuFlag.equalsIgnoreCase("Increase")) {
                        productRmStockSummaryModel.setMaterial_rate(summObject.getMaterial_rate());
                    }

                    addProductRmStockSummaryList.add(productRmStockSummaryModel);
                } else {
                    productRmStockSummaryModel.setCompany_id(summObject.getCompany_id());
                    productRmStockSummaryModel.setCompany_branch_id(summObject.getCompany_branch_id());
                    productRmStockSummaryModel.setFinancial_year(summObject.getFinancial_year());
                    productRmStockSummaryModel.setCustomer_id(summObject.getCustomer_id());
                    productRmStockSummaryModel.setProduct_type_group(summObject.getProduct_type_group());
                    productRmStockSummaryModel.setProduct_type_id(summObject.getProduct_type_id());
                    productRmStockSummaryModel.setProduct_rm_id(summObject.getProduct_rm_id());
                    productRmStockSummaryModel.setProduct_material_unit_id(summObject.getProduct_material_unit_id());
                    productRmStockSummaryModel.setProduct_material_packing_id(summObject.getProduct_material_packing_id());
                    productRmStockSummaryModel.setCustomer_id(summObject.getCustomer_id());
                    productRmStockSummaryModel.setPending_quantity(summObject.getPending_quantity());
                    productRmStockSummaryModel.setPending_weight(summObject.getPending_weight());
                    productRmStockSummaryModel.setReserve_quantity(summObject.getReserve_quantity());
                    productRmStockSummaryModel.setReserve_weight(summObject.getReserve_weight());
                    productRmStockSummaryModel.setExcess_quantity(summObject.getExcess_quantity());
                    productRmStockSummaryModel.setExcess_weight(summObject.getExcess_weight());
                    productRmStockSummaryModel.setPree_closed_quantity(summObject.getPree_closed_quantity());
                    productRmStockSummaryModel.setPree_closed_weight(summObject.getPree_closed_weight());
                    productRmStockSummaryModel.setPurchase_quantity(summObject.getPurchase_quantity());
                    productRmStockSummaryModel.setPurchase_weight(summObject.getPurchase_weight());
                    productRmStockSummaryModel.setPurchase_return_quantity(summObject.getPurchase_return_quantity());
                    productRmStockSummaryModel.setPurchase_return_weight(summObject.getPurchase_return_weight());
                    productRmStockSummaryModel.setPurchase_rejection_quantity(summObject.getPurchase_rejection_quantity());
                    productRmStockSummaryModel.setPurchase_rejection_weight(summObject.getPurchase_rejection_weight());
                    productRmStockSummaryModel.setJobcard_quantity(summObject.getJobcard_quantity());
                    productRmStockSummaryModel.setJobcard_weight(summObject.getJobcard_weight());
                    productRmStockSummaryModel.setProduction_issue_quantity(summObject.getProduction_issue_quantity());
                    productRmStockSummaryModel.setProduction_issue_weight(summObject.getProduction_issue_weight());
                    productRmStockSummaryModel.setProduction_issue_return_quantity(summObject.getProduction_issue_return_quantity());
                    productRmStockSummaryModel.setProduction_issue_return_weight(summObject.getProduction_issue_return_weight());
                    productRmStockSummaryModel.setProduction_issue_rejection_quantity(summObject.getProduction_issue_rejection_quantity());
                    productRmStockSummaryModel.setProduction_issue_rejection_weight(summObject.getProduction_issue_rejection_weight());
                    productRmStockSummaryModel.setProduction_no_of_boxes(summObject.getProduction_no_of_boxes());
                    productRmStockSummaryModel.setProduction_quantity(summObject.getProduction_quantity());
                    productRmStockSummaryModel.setProduction_weight(summObject.getProduction_weight());
                    productRmStockSummaryModel.setProduction_return_quantity(summObject.getProduction_return_quantity());
                    productRmStockSummaryModel.setProduction_return_weight(summObject.getProduction_return_weight());
                    productRmStockSummaryModel.setProduction_rejection_quantity(summObject.getProduction_rejection_quantity());
                    productRmStockSummaryModel.setProduction_rejection_weight(summObject.getProduction_rejection_quantity());
                    productRmStockSummaryModel.setAssembly_production_issue_quantity(summObject.getAssembly_production_issue_quantity());
                    productRmStockSummaryModel.setAssembly_production_issue_weight(summObject.getAssembly_production_issue_weight());
                    productRmStockSummaryModel.setSales_no_of_boxes(summObject.getSales_no_of_boxes());
                    productRmStockSummaryModel.setSales_quantity(summObject.getSales_quantity());
                    productRmStockSummaryModel.setSales_weight(summObject.getSales_weight());
                    productRmStockSummaryModel.setSales_return_quantity(summObject.getSales_return_quantity());
                    productRmStockSummaryModel.setSales_return_weight(summObject.getSales_return_weight());
                    productRmStockSummaryModel.setSales_rejection_quantity(summObject.getSales_rejection_quantity());
                    productRmStockSummaryModel.setSales_rejection_weight(summObject.getSales_rejection_weight());
                    productRmStockSummaryModel.setSales_rejection_no_of_boxes(summObject.getSales_rejection_no_of_boxes());
                    productRmStockSummaryModel.setCustomer_receipt_quantity(summObject.getCustomer_receipt_quantity());
                    productRmStockSummaryModel.setCustomer_receipt_weight(summObject.getCustomer_receipt_weight());
                    productRmStockSummaryModel.setCustomer_return_quantity(summObject.getCustomer_return_quantity());
                    productRmStockSummaryModel.setCustomer_return_weight(summObject.getCustomer_return_weight());
                    productRmStockSummaryModel.setCustomer_rejection_weight(summObject.getCustomer_rejection_weight());
                    productRmStockSummaryModel.setCustomer_rejection_quantity(summObject.getCustomer_rejection_quantity());
                    productRmStockSummaryModel.setTransfer_issue_quantity(summObject.getTransfer_issue_quantity());
                    productRmStockSummaryModel.setTransfer_issue_weight(summObject.getTransfer_issue_weight());
                    productRmStockSummaryModel.setTransfer_receipt_quantity(summObject.getTransfer_receipt_quantity());
                    productRmStockSummaryModel.setTransfer_receipt_weight(summObject.getTransfer_receipt_weight());
                    productRmStockSummaryModel.setOutsources_out_quantity(summObject.getOutsources_out_quantity());
                    productRmStockSummaryModel.setOutsources_out_weight(summObject.getOutsources_out_weight());
                    productRmStockSummaryModel.setOutsources_in_quantity(summObject.getOutsources_in_quantity());
                    productRmStockSummaryModel.setOutsources_in_weight(summObject.getOutsources_in_weight());
                    productRmStockSummaryModel.setOutsources_rejection_quantity(summObject.getOutsources_rejection_quantity());
                    productRmStockSummaryModel.setOutsources_rejection_weight(summObject.getOutsources_rejection_weight());
                    productRmStockSummaryModel.setLoan_receipt_quantity(summObject.getLoan_receipt_quantity());
                    productRmStockSummaryModel.setLoan_receipt_weight(summObject.getLoan_receipt_weight());
                    productRmStockSummaryModel.setLoan_receipt_boxes(summObject.getLoan_receipt_boxes());
                    productRmStockSummaryModel.setLoan_issue_quantity(summObject.getLoan_issue_quantity());
                    productRmStockSummaryModel.setLoan_issue_weight(summObject.getLoan_issue_weight());
                    productRmStockSummaryModel.setLoan_issue_boxes(summObject.getLoan_issue_boxes());
                    productRmStockSummaryModel.setCancel_quantity(summObject.getCancel_quantity());
                    productRmStockSummaryModel.setCancel_weight(summObject.getCancel_weight());
                    productRmStockSummaryModel.setDifference_quantity(summObject.getDifference_quantity());
                    productRmStockSummaryModel.setDifference_weight(summObject.getDifference_weight());
                    productRmStockSummaryModel.setClosing_balance_quantity(summObject.getClosing_balance_quantity());
                    productRmStockSummaryModel.setClosing_balance_weight(summObject.getClosing_balance_weight());
                    productRmStockSummaryModel.setOpening_quantity(summObject.getOpening_quantity());
                    productRmStockSummaryModel.setOpening_weight(summObject.getOpening_weight());
                    productRmStockSummaryModel.setCreated_by(summObject.getCreated_by());
                    productRmStockSummaryModel.setGodown_id(godown_id);
                    productRmStockSummaryModel.setGodown_section_id(godown_section_id);
                    productRmStockSummaryModel.setGodown_section_beans_id(godown_section_beans_id);
                    productRmStockSummaryModel.setTotal_box_weight(summObject.getTotal_box_weight());
                    productRmStockSummaryModel.setTotal_quantity_in_box(summObject.getTotal_quantity_in_box());
                    productRmStockSummaryModel.setWeight_per_box_item(summObject.getWeight_per_box_item());
                    productRmStockSummaryModel.setOpening_no_of_boxes(summObject.getOpening_no_of_boxes());
                    productRmStockSummaryModel.setPurchase_no_of_boxes(summObject.getPurchase_no_of_boxes());
                    productRmStockSummaryModel.setReserve_no_of_boxes(summObject.getReserve_no_of_boxes());
                    productRmStockSummaryModel.setIssue_no_of_boxes(summObject.getIssue_no_of_boxes());
                    productRmStockSummaryModel.setClosing_no_of_boxes(summObject.getClosing_no_of_boxes());
                    productRmStockSummaryModel.setMaterial_rate(summObject.getMaterial_rate());

                    addProductRmStockSummaryList.add(productRmStockSummaryModel);
                }
            });
        }

//          Check incoming stock is available in our stock then if available the update it or add it (For Raw material stock details table)
        if (getRmStockDetails != null && getRmStockDetails.size() != 0) {
            getRmStockDetails.forEach(detailsObject -> {
                Map<String, Object> batchObject = new HashMap<>();
                CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();
                String material_id = detailsObject.getProduct_rm_id();
                String good_receipt_no = detailsObject.getGoods_receipt_no();
                Integer godown_id = detailsObject.getGodown_id();
                Integer godown_section_id = detailsObject.getGodown_section_id();
                Integer godown_section_beans_id = detailsObject.getGodown_section_beans_id();
                Double weight_per_cone = detailsObject.getWeight_per_box_item();


//				    Find the object in productRmStockDetailsList based on material_id & good receipt no
                Optional<CSmProductRmStockDetailsModel> matchingObject = productRmStockDetailsList.get().stream()
                        .filter(item -> material_id.equals(item.getProduct_rm_id())
                                && good_receipt_no.equals(item.getGoods_receipt_no()) &&
                                Objects.equals(godown_id, item.getGodown_id()) &&
                                Objects.equals(godown_section_id, item.getGodown_section_id()) &&
                                Objects.equals(godown_section_beans_id, item.getGodown_section_beans_id()) &&
                                (weight_per_cone == null || weight_per_cone == 0.0 || Objects.equals(weight_per_cone, item.getWeight_per_box_item()))
                        )
                        .findFirst();

//                  Matching object present using material Id then update it otherwise insert it
                if (matchingObject.isPresent()) {
                    CSmProductRmStockDetailsModel existingSummMaterislStock = matchingObject.get();

                    BeanUtils.copyProperties(existingSummMaterislStock, productRmStockDetailsModel);

//						Concat existing no & new no
                    String existingBatchNo = existingSummMaterislStock.getBatch_no();
                    String newBatchNo = detailsObject.getBatch_no();
                    String existingBatchExpiryDate = existingSummMaterislStock.getBatch_expiry_date();
                    String existingGoodsReceiptNo = existingSummMaterislStock.getGoods_receipt_no();
                    String existingSalesOrderNo = existingSummMaterislStock.getSales_order_no();
                    String existingCustomerOrderNo = existingSummMaterislStock.getCustomer_order_no();
                    String existingCustomerGoodsReceiptNo = existingSummMaterislStock.getCustomer_goods_receipt_no();
                    Integer existingGodownId = existingSummMaterislStock.getGodown_id();
                    Integer existingGodownSectionId = existingSummMaterislStock.getGodown_section_id();
                    Integer existingGodownSectionBeansId = existingSummMaterislStock.getGodown_section_beans_id();

                    if (iuFlag.equalsIgnoreCase("stockAdjustment")) {
                        productRmStockDetailsModel.setBatch_rate(detailsObject.getBatch_rate());
                    }
                    productRmStockDetailsModel.setBatch_no((newBatchNo != null && !newBatchNo.isEmpty()) ? newBatchNo : existingBatchNo);
                    productRmStockDetailsModel.setBatch_expiry_date((detailsObject.getBatch_expiry_date() != null && !detailsObject.getBatch_expiry_date().isEmpty()) ? detailsObject.getBatch_expiry_date() : existingBatchExpiryDate);
                    productRmStockDetailsModel.setGoods_receipt_no((detailsObject.getGoods_receipt_no() != null && !detailsObject.getGoods_receipt_no().isEmpty()) ? detailsObject.getGoods_receipt_no() : existingGoodsReceiptNo);
                    productRmStockDetailsModel.setSales_order_no((detailsObject.getSales_order_no() != null && !detailsObject.getSales_order_no().isEmpty()) ? detailsObject.getSales_order_no() : existingSalesOrderNo);
                    productRmStockDetailsModel.setCustomer_order_no((detailsObject.getCustomer_order_no() != null && !detailsObject.getCustomer_order_no().isEmpty()) ? detailsObject.getCustomer_order_no() : existingCustomerOrderNo);
                    productRmStockDetailsModel.setCustomer_goods_receipt_no((detailsObject.getCustomer_goods_receipt_no() != null && !detailsObject.getCustomer_goods_receipt_no().isEmpty()) ? detailsObject.getCustomer_goods_receipt_no() : existingCustomerGoodsReceiptNo);
                    productRmStockDetailsModel.setGodown_id((detailsObject.getGodown_id() != null && detailsObject.getGodown_id() != 0) ? detailsObject.getGodown_id() : existingGodownId);
                    productRmStockDetailsModel.setGodown_section_id((detailsObject.getGodown_section_id() != null && detailsObject.getGodown_section_id() != 0) ? detailsObject.getGodown_section_id() : existingGodownSectionId);
                    productRmStockDetailsModel.setGodown_section_beans_id((detailsObject.getGodown_section_beans_id() != null && detailsObject.getGodown_section_beans_id() != 0) ? detailsObject.getGodown_section_beans_id() : existingGodownSectionBeansId);
//                    productRmStockDetailsModel.setOrder_quantity(existingSummMaterislStock.getOrder_quantity() + (detailsObject.getOrder_quantity()));
//                    productRmStockDetailsModel.setOrder_weight(existingSummMaterislStock.getOrder_weight() + (detailsObject.getOrder_weight()));
                    productRmStockDetailsModel.setPending_quantity(existingSummMaterislStock.getPending_quantity() + (detailsObject.getPending_quantity()));
                    productRmStockDetailsModel.setPending_weight(existingSummMaterislStock.getPending_weight() + (detailsObject.getPending_weight()));
                    productRmStockDetailsModel.setReserve_quantity(existingSummMaterislStock.getReserve_quantity() + (detailsObject.getReserve_quantity()));
                    productRmStockDetailsModel.setReserve_weight(existingSummMaterislStock.getReserve_weight() + (detailsObject.getReserve_weight()));
                    productRmStockDetailsModel.setPree_closed_quantity(existingSummMaterislStock.getPree_closed_quantity() + (detailsObject.getPree_closed_quantity()));
                    productRmStockDetailsModel.setPree_closed_weight(existingSummMaterislStock.getPree_closed_weight() + (detailsObject.getPree_closed_weight()));
                    productRmStockDetailsModel.setPurchase_return_quantity(existingSummMaterislStock.getPurchase_return_quantity() + (detailsObject.getPurchase_return_quantity()));
                    productRmStockDetailsModel.setPurchase_return_weight(existingSummMaterislStock.getPurchase_return_weight() + (detailsObject.getPurchase_return_weight()));
                    productRmStockDetailsModel.setPurchase_rejection_quantity(existingSummMaterislStock.getPurchase_rejection_quantity() + (detailsObject.getPurchase_rejection_quantity()));
                    productRmStockDetailsModel.setPurchase_rejection_weight(existingSummMaterislStock.getPurchase_rejection_weight() + (detailsObject.getPurchase_rejection_weight()));
                    productRmStockDetailsModel.setJobcard_quantity(existingSummMaterislStock.getJobcard_quantity() + (detailsObject.getJobcard_quantity()));
                    productRmStockDetailsModel.setJobcard_weight(existingSummMaterislStock.getJobcard_weight() + (detailsObject.getJobcard_weight()));
                    productRmStockDetailsModel.setProduction_issue_quantity(existingSummMaterislStock.getProduction_issue_quantity() + (detailsObject.getProduction_issue_quantity()));
                    productRmStockDetailsModel.setProduction_issue_weight(existingSummMaterislStock.getProduction_issue_weight() + (detailsObject.getProduction_issue_weight()));
                    productRmStockDetailsModel.setProduction_issue_return_quantity(existingSummMaterislStock.getProduction_issue_return_quantity() + (detailsObject.getProduction_issue_return_quantity()));
                    productRmStockDetailsModel.setProduction_issue_return_weight(existingSummMaterislStock.getProduction_issue_return_weight() + (detailsObject.getProduction_issue_return_weight()));
                    productRmStockDetailsModel.setProduction_issue_rejection_quantity(existingSummMaterislStock.getProduction_issue_rejection_quantity() + (detailsObject.getProduction_issue_rejection_quantity()));
                    productRmStockDetailsModel.setProduction_issue_rejection_weight(existingSummMaterislStock.getProduction_issue_rejection_weight() + (detailsObject.getProduction_issue_rejection_weight()));
                    productRmStockDetailsModel.setProduction_no_of_boxes(existingSummMaterislStock.getProduction_no_of_boxes() + (detailsObject.getProduction_no_of_boxes()));
                    productRmStockDetailsModel.setProduction_quantity(existingSummMaterislStock.getProduction_quantity() + (detailsObject.getProduction_quantity()));
                    productRmStockDetailsModel.setProduction_weight(existingSummMaterislStock.getProduction_weight() + (detailsObject.getProduction_weight()));
                    productRmStockDetailsModel.setProduction_return_quantity(existingSummMaterislStock.getProduction_return_quantity() + (detailsObject.getProduction_return_quantity()));
                    productRmStockDetailsModel.setProduction_return_weight(existingSummMaterislStock.getProduction_return_weight() + (detailsObject.getProduction_return_weight()));
                    productRmStockDetailsModel.setProduction_rejection_quantity(existingSummMaterislStock.getProduction_rejection_quantity() + (detailsObject.getProduction_rejection_quantity()));
                    productRmStockDetailsModel.setProduction_rejection_weight(existingSummMaterislStock.getProduction_rejection_weight() + (detailsObject.getProduction_rejection_quantity()));
                    productRmStockDetailsModel.setAssembly_production_issue_quantity(existingSummMaterislStock.getAssembly_production_issue_quantity() + (detailsObject.getAssembly_production_issue_quantity()));
                    productRmStockDetailsModel.setAssembly_production_issue_weight(existingSummMaterislStock.getAssembly_production_issue_weight() + (detailsObject.getAssembly_production_issue_weight()));
                    productRmStockDetailsModel.setSales_no_of_boxes(existingSummMaterislStock.getSales_no_of_boxes() + (detailsObject.getSales_no_of_boxes()));
                    productRmStockDetailsModel.setSales_quantity(existingSummMaterislStock.getSales_quantity() + (detailsObject.getSales_quantity()));
                    productRmStockDetailsModel.setSales_weight(existingSummMaterislStock.getSales_weight() + (detailsObject.getSales_weight()));
                    productRmStockDetailsModel.setSales_return_quantity(existingSummMaterislStock.getSales_return_quantity() + (detailsObject.getSales_return_quantity()));
                    productRmStockDetailsModel.setSales_return_weight(existingSummMaterislStock.getSales_return_weight() + (detailsObject.getSales_return_weight()));
                    productRmStockDetailsModel.setSales_rejection_quantity(existingSummMaterislStock.getSales_rejection_quantity() + (detailsObject.getSales_rejection_quantity()));
                    productRmStockDetailsModel.setSales_rejection_weight(existingSummMaterislStock.getSales_rejection_weight() + (detailsObject.getSales_rejection_weight()));
                    productRmStockDetailsModel.setSales_rejection_no_of_boxes(existingSummMaterislStock.getSales_rejection_no_of_boxes() + (detailsObject.getSales_rejection_no_of_boxes()));
                    productRmStockDetailsModel.setCustomer_receipt_quantity(existingSummMaterislStock.getCustomer_receipt_quantity() + (detailsObject.getCustomer_receipt_quantity()));
                    productRmStockDetailsModel.setCustomer_receipt_weight(existingSummMaterislStock.getCustomer_receipt_weight() + (detailsObject.getCustomer_receipt_weight()));
                    productRmStockDetailsModel.setCustomer_return_quantity(existingSummMaterislStock.getCustomer_return_quantity() + (detailsObject.getCustomer_return_quantity()));
                    productRmStockDetailsModel.setCustomer_return_weight(existingSummMaterislStock.getCustomer_return_weight() + (detailsObject.getCustomer_return_weight()));
                    productRmStockDetailsModel.setCustomer_rejection_weight(existingSummMaterislStock.getCustomer_rejection_weight() + (detailsObject.getCustomer_rejection_weight()));
                    productRmStockDetailsModel.setCustomer_rejection_quantity(existingSummMaterislStock.getCustomer_rejection_quantity() + (detailsObject.getCustomer_rejection_quantity()));
                    productRmStockDetailsModel.setTransfer_issue_quantity(existingSummMaterislStock.getTransfer_issue_quantity() + (detailsObject.getTransfer_issue_quantity()));
                    productRmStockDetailsModel.setTransfer_issue_weight(existingSummMaterislStock.getTransfer_issue_weight() + (detailsObject.getTransfer_issue_weight()));
                    if (iuFlag.equalsIgnoreCase("Increase")) {
                        productRmStockDetailsModel.setExcess_quantity(existingSummMaterislStock.getExcess_quantity() + (detailsObject.getExcess_quantity()));
                        productRmStockDetailsModel.setExcess_weight(existingSummMaterislStock.getExcess_weight() + (detailsObject.getExcess_weight()));
                        productRmStockDetailsModel.setTransfer_receipt_quantity(existingSummMaterislStock.getTransfer_receipt_quantity() + (detailsObject.getTransfer_receipt_quantity()));
                        productRmStockDetailsModel.setTransfer_receipt_weight(existingSummMaterislStock.getTransfer_receipt_weight() + (detailsObject.getTransfer_receipt_weight()));
                    }
                    productRmStockDetailsModel.setOutsources_out_quantity(existingSummMaterislStock.getOutsources_out_quantity() + (detailsObject.getOutsources_out_quantity()));
                    productRmStockDetailsModel.setOutsources_out_weight(existingSummMaterislStock.getOutsources_out_weight() + (detailsObject.getOutsources_out_weight()));
                    productRmStockDetailsModel.setOutsources_in_quantity(existingSummMaterislStock.getOutsources_in_quantity() + (detailsObject.getOutsources_in_quantity()));
                    productRmStockDetailsModel.setOutsources_in_weight(existingSummMaterislStock.getOutsources_in_weight() + (detailsObject.getOutsources_in_weight()));
                    productRmStockDetailsModel.setOutsources_rejection_quantity(existingSummMaterislStock.getOutsources_rejection_quantity() + (detailsObject.getOutsources_rejection_quantity()));
                    productRmStockDetailsModel.setOutsources_rejection_weight(existingSummMaterislStock.getOutsources_rejection_weight() + (detailsObject.getOutsources_rejection_weight()));
                    productRmStockDetailsModel.setLoan_receipt_quantity(existingSummMaterislStock.getLoan_receipt_quantity() + (detailsObject.getLoan_receipt_quantity()));
                    productRmStockDetailsModel.setLoan_receipt_weight(existingSummMaterislStock.getLoan_receipt_weight() + (detailsObject.getLoan_receipt_weight()));
                    productRmStockDetailsModel.setLoan_receipt_boxes(existingSummMaterislStock.getLoan_receipt_boxes() + (detailsObject.getLoan_receipt_boxes()));
                    productRmStockDetailsModel.setLoan_issue_quantity(existingSummMaterislStock.getLoan_issue_quantity() + (detailsObject.getLoan_issue_quantity()));
                    productRmStockDetailsModel.setLoan_issue_weight(existingSummMaterislStock.getLoan_issue_weight() + (detailsObject.getLoan_issue_weight()));
                    productRmStockDetailsModel.setLoan_issue_boxes(existingSummMaterislStock.getLoan_issue_boxes() + (detailsObject.getLoan_issue_boxes()));
                    productRmStockDetailsModel.setCancel_quantity(existingSummMaterislStock.getCancel_quantity() + (detailsObject.getCancel_quantity()));
                    productRmStockDetailsModel.setCancel_weight(existingSummMaterislStock.getCancel_weight() + (detailsObject.getCancel_weight()));
                    productRmStockDetailsModel.setDifference_quantity(existingSummMaterislStock.getDifference_quantity() + (detailsObject.getDifference_quantity()));
                    productRmStockDetailsModel.setDifference_weight(existingSummMaterislStock.getDifference_weight() + (detailsObject.getDifference_weight()));
                    productRmStockDetailsModel.setClosing_balance_quantity(existingSummMaterislStock.getClosing_balance_quantity() + (detailsObject.getClosing_balance_quantity()));
                    productRmStockDetailsModel.setClosing_balance_weight(existingSummMaterislStock.getClosing_balance_weight() + (detailsObject.getClosing_balance_weight()));
                    productRmStockDetailsModel.setOpening_no_of_boxes(existingSummMaterislStock.getOpening_no_of_boxes() + (detailsObject.getOpening_no_of_boxes()));
                    productRmStockDetailsModel.setPurchase_no_of_boxes(existingSummMaterislStock.getPurchase_no_of_boxes() + (detailsObject.getPurchase_no_of_boxes()));
                    productRmStockDetailsModel.setReserve_no_of_boxes(existingSummMaterislStock.getReserve_no_of_boxes() + (detailsObject.getReserve_no_of_boxes()));
                    productRmStockDetailsModel.setIssue_no_of_boxes(existingSummMaterislStock.getIssue_no_of_boxes() + (detailsObject.getIssue_no_of_boxes()));
                    productRmStockDetailsModel.setClosing_no_of_boxes(existingSummMaterislStock.getClosing_no_of_boxes() + (detailsObject.getClosing_no_of_boxes()));

                    productRmStockDetailsModel.setSupplier_return_quantity(existingSummMaterislStock.getSupplier_return_quantity() + (detailsObject.getSupplier_return_quantity()));
                    productRmStockDetailsModel.setSupplier_return_weight(existingSummMaterislStock.getSupplier_return_weight() + (detailsObject.getSupplier_return_weight()));
                    productRmStockDetailsModel.setSupplier_return_boxes(existingSummMaterislStock.getSupplier_return_boxes() + (detailsObject.getSupplier_return_boxes()));

                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
                    batchObject.put("goods_receipt_no", existingSummMaterislStock.getGoods_receipt_no());
                    batchObject.put("issue_date", existingSummMaterislStock.getStock_date());
                    batchObject.put("product_material_id", existingSummMaterislStock.getProduct_rm_id());
                    batchList.add(batchObject);

                } else {
                    productRmStockDetailsModel.setCompany_id(detailsObject.getCompany_id());
                    productRmStockDetailsModel.setCompany_branch_id(detailsObject.getCompany_branch_id());
                    productRmStockDetailsModel.setFinancial_year(detailsObject.getFinancial_year());
                    productRmStockDetailsModel.setSupplier_id(detailsObject.getSupplier_id());
                    productRmStockDetailsModel.setCustomer_id(detailsObject.getCustomer_id());
                    productRmStockDetailsModel.setProduct_type_group(detailsObject.getProduct_type_group());
                    productRmStockDetailsModel.setProduct_type_id(detailsObject.getProduct_type_id());
                    productRmStockDetailsModel.setProduct_rm_id(detailsObject.getProduct_rm_id());
                    productRmStockDetailsModel.setProduct_material_unit_id(detailsObject.getProduct_material_unit_id());
                    productRmStockDetailsModel.setProduct_material_packing_id(detailsObject.getProduct_material_packing_id());

                    productRmStockDetailsModel.setBatch_rate(detailsObject.getBatch_rate());
                    productRmStockDetailsModel.setBatch_no(detailsObject.getBatch_no());
                    productRmStockDetailsModel.setBatch_expiry_date(detailsObject.getBatch_expiry_date());
                    productRmStockDetailsModel.setGoods_receipt_no(detailsObject.getGoods_receipt_no());
                    productRmStockDetailsModel.setSales_order_no(detailsObject.getSales_order_no());
                    productRmStockDetailsModel.setCustomer_order_no(detailsObject.getCustomer_order_no());
                    productRmStockDetailsModel.setCustomer_goods_receipt_no(detailsObject.getCustomer_goods_receipt_no());

                    productRmStockDetailsModel.setStock_date(detailsObject.getStock_date());
                    productRmStockDetailsModel.setCustomer_id(detailsObject.getCustomer_id());
                    productRmStockDetailsModel.setPending_quantity(detailsObject.getPending_quantity());
                    productRmStockDetailsModel.setPending_weight(detailsObject.getPending_weight());
                    productRmStockDetailsModel.setReserve_quantity(detailsObject.getReserve_quantity());
                    productRmStockDetailsModel.setReserve_weight(detailsObject.getReserve_weight());
                    productRmStockDetailsModel.setExcess_quantity(detailsObject.getExcess_quantity());
                    productRmStockDetailsModel.setExcess_weight(detailsObject.getExcess_weight());
                    productRmStockDetailsModel.setPree_closed_quantity(detailsObject.getPree_closed_quantity());
                    productRmStockDetailsModel.setPree_closed_weight(detailsObject.getPree_closed_weight());
                    productRmStockDetailsModel.setPurchase_quantity(detailsObject.getPurchase_quantity());
                    productRmStockDetailsModel.setPurchase_weight(detailsObject.getPurchase_weight());
                    productRmStockDetailsModel.setPurchase_return_quantity(detailsObject.getPurchase_return_quantity());
                    productRmStockDetailsModel.setPurchase_return_weight(detailsObject.getPurchase_return_weight());
                    productRmStockDetailsModel.setPurchase_rejection_quantity(detailsObject.getPurchase_rejection_quantity());
                    productRmStockDetailsModel.setPurchase_rejection_weight(detailsObject.getPurchase_rejection_weight());
                    productRmStockDetailsModel.setJobcard_quantity(detailsObject.getJobcard_quantity());
                    productRmStockDetailsModel.setJobcard_weight(detailsObject.getJobcard_weight());
                    productRmStockDetailsModel.setProduction_issue_quantity(detailsObject.getProduction_issue_quantity());
                    productRmStockDetailsModel.setProduction_issue_weight(detailsObject.getProduction_issue_weight());
                    productRmStockDetailsModel.setProduction_issue_return_quantity(detailsObject.getProduction_issue_return_quantity());
                    productRmStockDetailsModel.setProduction_issue_return_weight(detailsObject.getProduction_issue_return_weight());
                    productRmStockDetailsModel.setProduction_issue_rejection_quantity(detailsObject.getProduction_issue_rejection_quantity());
                    productRmStockDetailsModel.setProduction_issue_rejection_weight(detailsObject.getProduction_issue_rejection_weight());
                    productRmStockDetailsModel.setProduction_no_of_boxes(detailsObject.getProduction_no_of_boxes());
                    productRmStockDetailsModel.setProduction_quantity(detailsObject.getProduction_quantity());
                    productRmStockDetailsModel.setProduction_weight(detailsObject.getProduction_weight());
                    productRmStockDetailsModel.setProduction_return_quantity(detailsObject.getProduction_return_quantity());
                    productRmStockDetailsModel.setProduction_return_weight(detailsObject.getProduction_return_weight());
                    productRmStockDetailsModel.setProduction_rejection_quantity(detailsObject.getProduction_rejection_quantity());
                    productRmStockDetailsModel.setProduction_rejection_weight(detailsObject.getProduction_rejection_quantity());
                    productRmStockDetailsModel.setAssembly_production_issue_quantity(detailsObject.getAssembly_production_issue_quantity());
                    productRmStockDetailsModel.setAssembly_production_issue_weight(detailsObject.getAssembly_production_issue_weight());
                    productRmStockDetailsModel.setSales_no_of_boxes(detailsObject.getSales_no_of_boxes());
                    productRmStockDetailsModel.setSales_quantity(detailsObject.getSales_quantity());
                    productRmStockDetailsModel.setSales_weight(detailsObject.getSales_weight());
                    productRmStockDetailsModel.setSales_return_quantity(detailsObject.getSales_return_quantity());
                    productRmStockDetailsModel.setSales_return_weight(detailsObject.getSales_return_weight());
                    productRmStockDetailsModel.setSales_rejection_quantity(detailsObject.getSales_rejection_quantity());
                    productRmStockDetailsModel.setSales_rejection_weight(detailsObject.getSales_rejection_weight());
                    productRmStockDetailsModel.setSales_rejection_no_of_boxes(detailsObject.getSales_rejection_no_of_boxes());
                    productRmStockDetailsModel.setCustomer_receipt_quantity(detailsObject.getCustomer_receipt_quantity());
                    productRmStockDetailsModel.setCustomer_receipt_weight(detailsObject.getCustomer_receipt_weight());
                    productRmStockDetailsModel.setCustomer_return_quantity(detailsObject.getCustomer_return_quantity());
                    productRmStockDetailsModel.setCustomer_return_weight(detailsObject.getCustomer_return_weight());
                    productRmStockDetailsModel.setCustomer_rejection_weight(detailsObject.getCustomer_rejection_weight());
                    productRmStockDetailsModel.setCustomer_rejection_quantity(detailsObject.getCustomer_rejection_quantity());
                    productRmStockDetailsModel.setTransfer_issue_quantity(detailsObject.getTransfer_issue_quantity());
                    productRmStockDetailsModel.setTransfer_issue_weight(detailsObject.getTransfer_issue_weight());
                    productRmStockDetailsModel.setTransfer_receipt_quantity(detailsObject.getTransfer_receipt_quantity());
                    productRmStockDetailsModel.setTransfer_receipt_weight(detailsObject.getTransfer_receipt_weight());
                    productRmStockDetailsModel.setOutsources_out_quantity(detailsObject.getOutsources_out_quantity());
                    productRmStockDetailsModel.setOutsources_out_weight(detailsObject.getOutsources_out_weight());
                    productRmStockDetailsModel.setOutsources_in_quantity(detailsObject.getOutsources_in_quantity());
                    productRmStockDetailsModel.setOutsources_in_weight(detailsObject.getOutsources_in_weight());
                    productRmStockDetailsModel.setOutsources_rejection_quantity(detailsObject.getOutsources_rejection_quantity());
                    productRmStockDetailsModel.setOutsources_rejection_weight(detailsObject.getOutsources_rejection_weight());
                    productRmStockDetailsModel.setLoan_receipt_quantity(detailsObject.getLoan_receipt_quantity());
                    productRmStockDetailsModel.setLoan_receipt_weight(detailsObject.getLoan_receipt_weight());
                    productRmStockDetailsModel.setLoan_receipt_boxes(detailsObject.getLoan_receipt_boxes());
                    productRmStockDetailsModel.setLoan_issue_quantity(detailsObject.getLoan_issue_quantity());
                    productRmStockDetailsModel.setLoan_issue_weight(detailsObject.getLoan_issue_weight());
                    productRmStockDetailsModel.setLoan_issue_boxes(detailsObject.getLoan_issue_boxes());
                    productRmStockDetailsModel.setCancel_quantity(detailsObject.getCancel_quantity());
                    productRmStockDetailsModel.setCancel_weight(detailsObject.getCancel_weight());
                    productRmStockDetailsModel.setDifference_quantity(detailsObject.getDifference_quantity());
                    productRmStockDetailsModel.setDifference_weight(detailsObject.getDifference_weight());
                    productRmStockDetailsModel.setClosing_balance_quantity(detailsObject.getClosing_balance_quantity());
                    productRmStockDetailsModel.setClosing_balance_weight(detailsObject.getClosing_balance_weight());
                    productRmStockDetailsModel.setOpening_quantity(detailsObject.getOpening_quantity());
                    productRmStockDetailsModel.setOpening_weight(detailsObject.getOpening_weight());
                    productRmStockDetailsModel.setGodown_id(detailsObject.getGodown_id());
                    productRmStockDetailsModel.setGodown_section_id(detailsObject.getGodown_section_id());
                    productRmStockDetailsModel.setGodown_section_beans_id(detailsObject.getGodown_section_beans_id());
                    productRmStockDetailsModel.setTotal_box_weight(detailsObject.getTotal_box_weight());
                    productRmStockDetailsModel.setTotal_quantity_in_box(detailsObject.getTotal_quantity_in_box());
                    productRmStockDetailsModel.setWeight_per_box_item(detailsObject.getWeight_per_box_item());
                    productRmStockDetailsModel.setOpening_no_of_boxes(detailsObject.getOpening_no_of_boxes());
                    productRmStockDetailsModel.setPurchase_no_of_boxes(detailsObject.getPurchase_no_of_boxes());
                    productRmStockDetailsModel.setReserve_no_of_boxes(detailsObject.getReserve_no_of_boxes());
                    productRmStockDetailsModel.setIssue_no_of_boxes(detailsObject.getIssue_no_of_boxes());
                    productRmStockDetailsModel.setClosing_no_of_boxes(detailsObject.getClosing_no_of_boxes());

                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
                }
            });
        }

        if (getStockTrackingDetails != null && getStockTrackingDetails.size() != 0) {
            AtomicReference<List<CSmProductStockTracking>> productRmStockTrackingDetailsListCopy = new AtomicReference<>();

            if (productRmStockTrackingDetailsList != null) {
                List<CSmProductStockTracking> trackingList = productRmStockTrackingDetailsList.get().stream()
                        .map(details -> {
                            CSmProductStockTracking copy = new CSmProductStockTracking();
                            BeanUtils.copyProperties(details, copy);
                            return copy;
                        })
                        .collect(Collectors.toList());

                productRmStockTrackingDetailsListCopy.set(trackingList);
            }

            getStockTrackingDetails.forEach(trackingObject -> {
                CSmProductStockTracking smProductStockTracking = new CSmProductStockTracking();

                String material_id = trackingObject.getProduct_material_id();
                String good_receipt_no = trackingObject.getGoods_receipt_no();

//				Find the object in productRmStockDetailsList based on material_id & good receipt no
                Optional<CSmProductStockTracking> matchingObject = null;
                matchingObject = productRmStockTrackingDetailsList.get().stream()
                        .filter(item -> material_id.equals(item.getProduct_material_id())
                                && good_receipt_no.equals(item.getGoods_receipt_no())
                                && !item.is_stock_consumed())
                        .findFirst();

                if (!matchingObject.isPresent()) {
//                  from list added to save we need to find object after consumption
                    matchingObject = addProductStockTracking.stream()
                            .filter(item -> material_id.equals(item.getProduct_material_id())
                                    && good_receipt_no.equals(item.getGoods_receipt_no())
                                    && !item.is_stock_consumed())
                            .findFirst();

                }

                if (matchingObject.isPresent()) {
                    smProductStockTracking = matchingObject.get();

                    if (!trackingObject.isStockAddOrConsume()) {    // if it is stock consume then isStockAddOrConsume status will be false
                        smProductStockTracking.setConsumption_no(trackingObject.getConsumption_no());
                        smProductStockTracking.setConsumption_detail_no(trackingObject.getConsumption_detail_no());
                        smProductStockTracking.setConsumption_date(trackingObject.getConsumption_date());
                        smProductStockTracking.setConsumption_location(trackingObject.getConsumption_location());
                        smProductStockTracking.setConsumption_quantity(trackingObject.getConsumption_quantity());
                        smProductStockTracking.setCreated_by(trackingObject.getCreated_by());
                        smProductStockTracking.setModified_by(trackingObject.getCreated_by());
                        smProductStockTracking.set_stock_consumed(true);
                    } else {
                        smProductStockTracking.setStock_quantity(smProductStockTracking.getStock_quantity() + trackingObject.getStock_quantity());
                    }


//					Add one new entry after consumption
                    if (matchingObject.get().getStock_quantity() - smProductStockTracking.getConsumption_quantity() > 0 && !trackingObject.isStockAddOrConsume()) {

                        CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();
                        cSmProductStockTracking.setCompany_id(matchingObject.get().getCompany_id());
                        cSmProductStockTracking.setCompany_branch_id(matchingObject.get().getCompany_branch_id());
                        cSmProductStockTracking.setFinancial_year(matchingObject.get().getFinancial_year());
                        cSmProductStockTracking.setGoods_receipt_no(matchingObject.get().getGoods_receipt_no());
                        cSmProductStockTracking.setProduct_material_id(matchingObject.get().getProduct_material_id());
                        cSmProductStockTracking.setStock_date(matchingObject.get().getStock_date());
                        cSmProductStockTracking.setStock_quantity(matchingObject.get().getStock_quantity() - smProductStockTracking.getConsumption_quantity());
                        cSmProductStockTracking.setProduct_material_unit_id(matchingObject.get().getProduct_material_unit_id());
                        cSmProductStockTracking.setStock_type(matchingObject.get().getStock_type());
                        cSmProductStockTracking.setCreated_by(matchingObject.get().getCreated_by());

                        addProductStockTracking.add(cSmProductStockTracking);

                    }

                } else {
                    smProductStockTracking.setCompany_id(trackingObject.getCompany_id());
                    smProductStockTracking.setCompany_branch_id(trackingObject.getCompany_branch_id());
                    smProductStockTracking.setFinancial_year(trackingObject.getFinancial_year());
                    smProductStockTracking.setGoods_receipt_no(trackingObject.getGoods_receipt_no());
                    smProductStockTracking.setProduct_material_id(trackingObject.getProduct_material_id());
                    smProductStockTracking.setStock_date(trackingObject.getStock_date());
                    smProductStockTracking.setStock_quantity(trackingObject.getStock_quantity());
                    smProductStockTracking.setProduct_material_unit_id(trackingObject.getProduct_material_unit_id());
                    smProductStockTracking.setStock_type(trackingObject.getStock_type());
                    smProductStockTracking.setCreated_by(trackingObject.getCreated_by());
                }

                addProductStockTracking.add(smProductStockTracking);
            });
        }
        if (!addProductRmStockSummaryList.isEmpty() && addProductRmStockSummaryList != null) {
            iSmProductRmStockSummaryRepository.saveAll(addProductRmStockSummaryList);                // STore details in sm_product_rm_stock_summary
        }
        if (!addProductRmStockDetailsList.isEmpty() && addProductRmStockDetailsList != null) {
            iSmProductRmStockDetailsRepository.saveAll(addProductRmStockDetailsList);                    // STore details in sm_product_rm_stock_details

        }
        if (addProductStockTracking != null && !addProductStockTracking.isEmpty()) {
            iSmProductStockTrackingRepository.saveAll(addProductStockTracking);                      // STore details in sm_product_stock_tracking
        }
        response.put("issuedBatchList", batchList);
        response.put("Success", 1);
        response.put("message", "Stock " + iuFlag + " updated successfully!...");
        return response;
    }
}



