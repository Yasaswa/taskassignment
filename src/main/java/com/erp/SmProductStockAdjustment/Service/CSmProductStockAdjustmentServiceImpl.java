package com.erp.SmProductStockAdjustment.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.PtGoodsReceiptDetails.Service.CPtGoodsReceiptDetailsServiceImpl;
import com.erp.RawMaterial.Product_Rm.Repository.IProductRmRepository;
import com.erp.RawMaterial.Product_Rm_Commercial.Repository.IProductRmCommercialRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockSummaryRepository;
import com.erp.SmProductStockAdjustment.Model.CSmProductStockAdjustmentDetailsModel;
import com.erp.SmProductStockAdjustment.Model.CSmProductStockAdjustmentDetailsViewModel;
import com.erp.SmProductStockAdjustment.Model.CSmProductStockAdjustmentModel;
import com.erp.SmProductStockAdjustment.Repository.ISmProductStockAdjustmentDetailsModelRepository;
import com.erp.SmProductStockAdjustment.Repository.ISmProductStockAdjustmentDetailsViewModelRepository;
import com.erp.SmProductStockAdjustment.Repository.ISmProductStockAdjustmentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
public class CSmProductStockAdjustmentServiceImpl implements ISmProductStockAdjustmentService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    ISmProductStockAdjustmentRepository iSmProductStockAdjustmentRepository;

    @Autowired
    ISmProductStockAdjustmentDetailsModelRepository iSmProductStockAdjustmentDetailsModelRepository;

    @Autowired
    ISmProductStockAdjustmentDetailsViewModelRepository iSmProductStockAdjustmentDetailsViewModelRepository;

    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    IProductRmRepository iProductRmRepository;

    @Autowired
    IProductRmCommercialRepository iProductRmCommercialRepository;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    CPtGoodsReceiptDetailsServiceImpl cPtGoodsReceiptDetailsService;

    @Autowired
    ISmProductRmStockSummaryRepository iSmProductRmStockSummaryRepository;

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject stockAdjustmentJson) {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> response = new HashMap<>();

        JSONObject getStockAdjustmentMaster = (JSONObject) stockAdjustmentJson.get("StockAdjustmentMaster");
        JSONArray getStockAdjustmentDetails = (JSONArray) stockAdjustmentJson.get("StockAdjustmentDetails");
        Boolean KeyForAddUpdateStock = (Boolean) stockAdjustmentJson.get("KeyForAddUpdateStock");
        JSONObject commonIdsObj = (JSONObject) stockAdjustmentJson.get("commonIds");
        String product_type_group = commonIdsObj.getString("product_type_group");


        int company_id = getStockAdjustmentMaster.getInt("company_id");
        AtomicInteger stock_adjustment_transaction_id = new AtomicInteger((int) getStockAdjustmentMaster.get("stock_adjustment_transaction_id"));

        try {

            CSmProductStockAdjustmentModel smProductStockAdjustmentModel =
                    objectMapper.readValue(getStockAdjustmentMaster.toString(), CSmProductStockAdjustmentModel.class);

            CSmProductStockAdjustmentModel respProductStockAdjustmentModel = iSmProductStockAdjustmentRepository.save(smProductStockAdjustmentModel);

            if (!getStockAdjustmentDetails.isEmpty()) {

                // Parse stock adjustment details from the retrieved data
                List<CSmProductStockAdjustmentDetailsModel> stockAdjustmentDetailsModel = objectMapper.readValue(getStockAdjustmentDetails.toString(),
                        new TypeReference<List<CSmProductStockAdjustmentDetailsModel>>() {
                        });

                stockAdjustmentDetailsModel.forEach(detailItems -> {
                    detailItems.setStock_adjustment_transaction_id(respProductStockAdjustmentModel.getStock_adjustment_transaction_id());
                });

                iSmProductStockAdjustmentDetailsModelRepository.saveAll(stockAdjustmentDetailsModel);

                FnAddUpdateStock(stockAdjustmentDetailsModel, company_id, commonIdsObj);
            }

            response.put("data", respProductStockAdjustmentModel);
            response.put("success", 1);
            response.put("error", "");
            response.put("message", stock_adjustment_transaction_id.get() == 0 ? "Record added successfully!..." : "Record updated successfully!...");


        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductStockAdjustment/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", "0");
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductStockAdjustment/FnAddUpdateRecord", 0,
                    e.getMessage());
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());

        }

        return response;

    }

    private void FnAddUpdateStock(List<CSmProductStockAdjustmentDetailsModel> stockAdjustmentDetailsModel, int company_id, JSONObject commonIdsObj) {

        Double product_rm_std_weight;
        int product_category2_id ;
        String product_type_group = commonIdsObj.getString("product_type_group");
        if (product_type_group.equals("PRM")) {
            product_rm_std_weight = commonIdsObj.getDouble("product_rm_std_weight");
            product_category2_id = commonIdsObj.getInt("product_category2_id");
        } else {
            product_rm_std_weight = 0.0;
            product_category2_id = 0;
        }
//		 Get the current date
        Date currentDate = new Date();

//		 Formatting the date to display only the date portion
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);

//		Create list to add object to save stock details & Summary
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addProductStockTrackingList = new ArrayList<>();

        Map<String, Object> stockDetails = new HashMap<>();

        stockAdjustmentDetailsModel.forEach(detailItems -> {
            CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();
            CSmProductRmStockSummaryModel stockSummaryModel = new CSmProductRmStockSummaryModel();
            CSmProductStockTracking smProductStockTracking = new CSmProductStockTracking();


//			Add update stock
            stockSummaryModel.setCompany_id(detailItems.getCompany_id());
            stockSummaryModel.setCompany_branch_id(detailItems.getCompany_branch_id());
            stockSummaryModel.setFinancial_year(detailItems.getFinancial_year());
            stockSummaryModel.setProduct_rm_id(detailItems.getProduct_material_id());
            stockSummaryModel.setClosing_balance_quantity(detailItems.getStock_adjustment_type().equals("Add") ? detailItems.getStock_adjustment_quantity() : -detailItems.getStock_adjustment_quantity());
            stockSummaryModel.setClosing_balance_weight(detailItems.getStock_adjustment_type().equals("Add") ? detailItems.getStock_adjustment_weight() : -detailItems.getStock_adjustment_weight());
            stockSummaryModel.setGodown_id(detailItems.getGodown_id());
            stockSummaryModel.setGodown_section_id(detailItems.getGodown_section_id());
            stockSummaryModel.setGodown_section_beans_id(detailItems.getGodown_section_beans_id());
            stockSummaryModel.setCreated_by(detailItems.getCreated_by());
            stockSummaryModel.setModified_by(detailItems.getCreated_by());
            stockSummaryModel.setPrev_godown_section_id(detailItems.getPrev_godown_section_id());
            stockSummaryModel.setPrev_godown_section_beans_id(detailItems.getPrev_godown_section_beans_id());
            stockSummaryModel.setProduct_type_id(detailItems.getProduct_type_id());
            if (product_type_group.equals("PRM") && product_category2_id == 5) {
                stockSummaryModel.setTotal_box_weight(detailItems.getStock_adjustment_weight());
                stockSummaryModel.setTotal_quantity_in_box(detailItems.getStock_adjustment_quantity() );
                stockSummaryModel.setWeight_per_box_item(product_rm_std_weight);
            }

            addProductRmStockSummaryList.add(stockSummaryModel);

            productRmStockDetailsModel.setCompany_id(detailItems.getCompany_id());
            productRmStockDetailsModel.setCompany_branch_id(detailItems.getCompany_branch_id());
            productRmStockDetailsModel.setFinancial_year(detailItems.getFinancial_year());
            productRmStockDetailsModel.setProduct_rm_id(detailItems.getProduct_material_id());
            productRmStockDetailsModel.setGoods_receipt_no(detailItems.getGoods_receipt_no() == null || detailItems.getGoods_receipt_no().isEmpty() ? "Opening Balance" : detailItems.getGoods_receipt_no());
            productRmStockDetailsModel.setClosing_balance_quantity(detailItems.getStock_adjustment_type().equals("Add") ? detailItems.getStock_adjustment_quantity() : -detailItems.getStock_adjustment_quantity());
            productRmStockDetailsModel.setClosing_balance_weight(detailItems.getStock_adjustment_type().equals("Add") ? detailItems.getStock_adjustment_weight() : -detailItems.getStock_adjustment_weight());
            productRmStockDetailsModel.setGodown_id(detailItems.getGodown_id());
            productRmStockDetailsModel.setGodown_section_id(detailItems.getGodown_section_id());
            productRmStockDetailsModel.setGodown_section_beans_id(detailItems.getGodown_section_beans_id());
            productRmStockDetailsModel.setCreated_by(detailItems.getCreated_by());
            productRmStockDetailsModel.setModified_by(detailItems.getCreated_by());
            productRmStockDetailsModel.setPrev_godown_section_id(detailItems.getPrev_godown_section_id());
            productRmStockDetailsModel.setPrev_godown_section_beans_id(detailItems.getPrev_godown_section_beans_id());
            productRmStockDetailsModel.setProduct_type_id(detailItems.getProduct_type_id());
            productRmStockDetailsModel.setBatch_rate(detailItems.getMaterial_rate());
        if (product_type_group.equals("PRM") && product_category2_id == 5) {
            productRmStockDetailsModel.setTotal_box_weight(detailItems.getStock_adjustment_weight());
            productRmStockDetailsModel.setTotal_quantity_in_box(detailItems.getStock_adjustment_quantity() );
            productRmStockDetailsModel.setWeight_per_box_item(product_rm_std_weight);
        }
            addProductRmStockDetailsList.add(productRmStockDetailsModel);

//			Stock tracking details
//			smProductStockTracking.setGoods_receipt_no(detailItems.getGoods_receipt_no() == null || detailItems.getGoods_receipt_no().equals("") || detailItems.getGoods_receipt_no().isEmpty() ? "Stock Adjustment" : detailItems.getGoods_receipt_no());
            smProductStockTracking.setGoods_receipt_no("Stock Adjustment");
            smProductStockTracking.setCompany_id(detailItems.getCompany_id());
            smProductStockTracking.setCompany_branch_id(detailItems.getCompany_branch_id());
            smProductStockTracking.setFinancial_year(detailItems.getFinancial_year());
            smProductStockTracking.setProduct_material_id(detailItems.getProduct_material_id());
            smProductStockTracking.setStock_date(todayDate);
            smProductStockTracking.setStock_quantity(detailItems.getStock_adjustment_type().equals("Add") ? detailItems.getStock_adjustment_quantity() : -detailItems.getStock_adjustment_quantity());
            smProductStockTracking.setCreated_by(detailItems.getCreated_by());
            smProductStockTracking.setModified_by(detailItems.getCreated_by());

            addProductStockTrackingList.add(smProductStockTracking);
        });

        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        stockDetails.put("StockTracking", addProductStockTrackingList);

        cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "stockAdjustment", company_id);

    }

    @Override
    public Map<String, Object> FnDeleteRecord(int stock_adjustment_transaction_id, int company_id, String deleted_by) {
        Map<String, Object> response = new HashMap<>();

        try {

            //Delete Product Stock Adjustment Record
            iSmProductStockAdjustmentRepository.FnDeleteStockAdjustmentRecord(stock_adjustment_transaction_id, company_id, deleted_by);

            //Delete Product Stock Adjustment  Details Record
            iSmProductStockAdjustmentDetailsModelRepository.FnDeleteStockAdjustmentDetailsRecord(stock_adjustment_transaction_id, company_id, deleted_by);

            response.put("success", 1);

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductStockAdjustment/FnDeleteRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", "0");
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductStockAdjustment/FnDeleteRecord", 0,
                    e.getMessage());
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());

        }
        return response;
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int stock_adjustment_transaction_id, int company_id) {
        Map<String, Object> response = new HashMap<>();

        try {

            //Fetch Product Stock Adjustment Record For update
            CSmProductStockAdjustmentModel productStockAdjustmentRecord =
                    iSmProductStockAdjustmentRepository.FnShowProductStockAdjustmentRecord(stock_adjustment_transaction_id, company_id);

            //Fetch Product Stock Adjustment details Record For update
            List<CSmProductStockAdjustmentDetailsViewModel> productStockAdjustmentDetailsRecord =
                    iSmProductStockAdjustmentDetailsViewModelRepository.FnShowProductStockAdjustmentDetailsRecord(stock_adjustment_transaction_id, company_id);

            response.put("ProductStockAdjustmentRecord", productStockAdjustmentRecord);
            response.put("productStockAdjustmentDetailsRecord", productStockAdjustmentDetailsRecord);
            response.put("success", 1);


        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductStockAdjustment/FnShowParticularRecordForUpdate",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductStockAdjustment/FnShowParticularRecordForUpdate", 0,
                    e.getMessage());
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());

        }
        return response;
    }


}
