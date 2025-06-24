package com.erp.SortTransfer.Service;


import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.ExceptionHandling.ExceptionHandlingClass;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockSummaryRepository;
import com.erp.SortTransfer.Model.CSortTransferDetailsModel;
import com.erp.SortTransfer.Model.CSortTransferDetailsViewModel;
import com.erp.SortTransfer.Model.CSortTransferMasterModel;
import com.erp.SortTransfer.Model.CSortTransferMasterViewModel;
import com.erp.SortTransfer.Repository.SortTransferDetailsDataRepository;
import com.erp.SortTransfer.Repository.SortTransferDetailsDataViewRepository;
import com.erp.SortTransfer.Repository.SortTransferMasterDataRepository;
import com.erp.SortTransfer.Repository.SortTransferMasterDataViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class STSortTransferServiceImpl implements STSortTransferService {

    private final ExceptionHandlingClass exceptionHandlingClass;
    @Autowired
    ISmProductRmStockSummaryRepository iSmProductRmStockSummaryRepository;
    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;
    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;
    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
    @Autowired
    private SortTransferMasterDataRepository sortTransferMasterDataRepository;
    @Autowired
    private SortTransferDetailsDataRepository sortTransferDetailsDataRepository;
    @Autowired
    private SortTransferDetailsDataViewRepository sortTransferDetailsDataViewRepository;
    @Autowired
    private SortTransferMasterDataViewRepository sortTransferMasterDataViewRepository;
    @Autowired
    private JdbcTemplate executeQuery;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> stockResponse = new HashMap<>();
        // get CommonId's
        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        JSONObject masterjson = jsonObject.getJSONObject("TransMasterData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        try {

            // Convert transfer MASTER DATA FROM JSON
            CSortTransferMasterModel cSortTransferMasterModel = objectMapper.readValue(masterjson.toString(), CSortTransferMasterModel.class);
            // transfer MASTER SAVE
            CSortTransferMasterModel responseSortTransferMaster = sortTransferMasterDataRepository.save(cSortTransferMasterModel);

            List<CSortTransferDetailsModel> cSortTransferDetailsModel = objectMapper.readValue(detailsArray.toString(),
                    new TypeReference<List<CSortTransferDetailsModel>>() {
                    });

            cSortTransferDetailsModel.forEach(items -> {
                items.setSort_transfer_master_id(responseSortTransferMaster.getSort_transfer_master_id());
            });

            List<CSortTransferDetailsModel> sortTransferSavedDetails = sortTransferDetailsDataRepository.saveAll(cSortTransferDetailsModel);

            if (!cSortTransferDetailsModel.isEmpty()) {
                updatePoRates(sortTransferSavedDetails);
                stockResponse = FnAddMaterialStock(cSortTransferDetailsModel, company_id);
                System.out.println("FnAddMaterialStock" + stockResponse);
            }
            response = exceptionHandlingClass.createSuccessResponse("Record processed successfully");
            response.put("responseSortTransferMaster", responseSortTransferMaster);
            response.put("stockResponse", stockResponse);

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtWeavingProductionInspectionMaster/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtWeavingProductionInspectionMaster/FnAddUpdateRecord", 0,
                    e.getMessage());
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());

        }
        return response;
    }

    public void updatePoRates(List<CSortTransferDetailsModel> savedPurchaseOrderDetailsModel) {
        String sql = "UPDATE xt_weaving_production_inspection_details SET sort_no = ?,inspection_production_set_no = ?, sizing_beam_no = ?, machine_id = ?,style = ? " +
                ", product_material_id = ?, product_material_name = ?  WHERE roll_no = ? AND is_delete = 0";

        executeQuery.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CSortTransferDetailsModel detail = savedPurchaseOrderDetailsModel.get(i);
                ps.setString(1, detail.getTo_sort_no());
                ps.setString(2, detail.getTo_set_no());
                ps.setString(3, detail.getSizing_beam_no());
                ps.setInt(4, detail.getMachine_id());
                ps.setString(5, detail.getTo_style());
                ps.setString(6, detail.getTo_product_material_id());
                ps.setString(7, detail.getTo_product_material_name());
                ps.setInt(8, detail.getRoll_no());
            }

            @Override
            public int getBatchSize() {
                return savedPurchaseOrderDetailsModel.size();
            }
        });
    }

    private Map<String, Object> FnAddMaterialStock(List<CSortTransferDetailsModel> cSortTransferDetailsModel, int companyId) {

        Map<String, Object> bothBatchresponse = new HashMap<>();
        Map<String, Object> batchresponse = new HashMap<>();
        String dateStr = "2025-05-19";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate stockImportDate = LocalDate.parse(dateStr, formatter);


        //		Create list to add object to save stock details & Summary
        List<CSmProductRmStockDetailsModel> addFgSortStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addFgSortStockSummaryList = new ArrayList<>();
        List<CSmProductRmStockDetailsModel> reduceProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> reduceProductRmStockSummaryList = new ArrayList<>();


        for (CSortTransferDetailsModel sortTransferDetails : cSortTransferDetailsModel) {
            Integer noOfRolls = 1;
            double mtrDifference = sortTransferDetails.getInspection_mtr();
            double weightDifference = sortTransferDetails.getWeight();

            CSmProductRmStockSummaryModel addproductRmStockSummaryModel = new CSmProductRmStockSummaryModel();
            CSmProductRmStockSummaryModel reduceproductRmStockSummaryModel = new CSmProductRmStockSummaryModel();

            Optional<CSmProductRmStockSummaryModel> checkTheMaterialAlreadyExist = addFgSortStockSummaryList.stream()
                    .filter(item -> sortTransferDetails.getTo_product_material_id().equals(item.getProduct_rm_id()) &&
                            sortTransferDetails.getGodown_id().equals(item.getGodown_id()))
                    .findFirst();

            if (checkTheMaterialAlreadyExist.isPresent()) {
                addproductRmStockSummaryModel = checkTheMaterialAlreadyExist.get();

                addproductRmStockSummaryModel.setClosing_balance_quantity(addproductRmStockSummaryModel.getClosing_balance_quantity() + mtrDifference);
                addproductRmStockSummaryModel.setClosing_balance_weight(addproductRmStockSummaryModel.getClosing_balance_weight() + weightDifference);
                addproductRmStockSummaryModel.setClosing_no_of_boxes(addproductRmStockSummaryModel.getClosing_no_of_boxes() + noOfRolls);
                addproductRmStockSummaryModel.setProduction_no_of_boxes(addproductRmStockSummaryModel.getProduction_no_of_boxes() + noOfRolls);
                addproductRmStockSummaryModel.setProduction_quantity(addproductRmStockSummaryModel.getProduction_quantity() + mtrDifference);
                addproductRmStockSummaryModel.setProduction_weight(addproductRmStockSummaryModel.getProduction_weight() + weightDifference);
            } else {
                addproductRmStockSummaryModel.setCompany_id(sortTransferDetails.getCompany_id());
                addproductRmStockSummaryModel.setCompany_branch_id(sortTransferDetails.getCompany_branch_id());
                addproductRmStockSummaryModel.setFinancial_year(sortTransferDetails.getFinancial_year());
                addproductRmStockSummaryModel.setProduct_type_group("GF");
                addproductRmStockSummaryModel.setProduct_type_id(sortTransferDetails.getProduct_type_id());
                addproductRmStockSummaryModel.setProduct_rm_id(sortTransferDetails.getTo_product_material_id());
                addproductRmStockSummaryModel.setProduct_material_unit_id(sortTransferDetails.getProduct_material_stock_unit_id());
                addproductRmStockSummaryModel.setProduct_material_packing_id(sortTransferDetails.getProduct_material_packing_id());

                addproductRmStockSummaryModel.setClosing_no_of_boxes(noOfRolls);
                addproductRmStockSummaryModel.setClosing_balance_quantity(mtrDifference);
                addproductRmStockSummaryModel.setClosing_balance_weight(weightDifference);
                addproductRmStockSummaryModel.setProduction_no_of_boxes(noOfRolls);
                addproductRmStockSummaryModel.setProduction_quantity(mtrDifference);
                addproductRmStockSummaryModel.setProduction_weight(weightDifference);

                addproductRmStockSummaryModel.setGodown_id(sortTransferDetails.getGodown_id());
                addproductRmStockSummaryModel.setGodown_section_id(sortTransferDetails.getGodown_section_id());
                addproductRmStockSummaryModel.setGodown_section_beans_id(sortTransferDetails.getGodown_section_beans_id());
                addproductRmStockSummaryModel.setCreated_by(sortTransferDetails.getCreated_by());
                addproductRmStockSummaryModel.setModified_by(sortTransferDetails.getCreated_by());
                addproductRmStockSummaryModel.setModified_on(sortTransferDetails.getModified_on());

                addFgSortStockSummaryList.add(addproductRmStockSummaryModel);
            }

            Optional<CSmProductRmStockSummaryModel> checkReduceMaterialAlreadyExist = reduceProductRmStockSummaryList.stream()
                    .filter(item -> sortTransferDetails.getFrom_product_material_id().equals(item.getProduct_rm_id()) &&
                            sortTransferDetails.getGodown_id().equals(item.getGodown_id()))
                    .findFirst();


            if (checkReduceMaterialAlreadyExist.isPresent()) {
                reduceproductRmStockSummaryModel = checkReduceMaterialAlreadyExist.get();

                reduceproductRmStockSummaryModel.setClosing_balance_quantity(reduceproductRmStockSummaryModel.getClosing_balance_quantity() + mtrDifference);
                reduceproductRmStockSummaryModel.setClosing_balance_weight(reduceproductRmStockSummaryModel.getClosing_balance_weight() + weightDifference);
                reduceproductRmStockSummaryModel.setClosing_no_of_boxes(reduceproductRmStockSummaryModel.getClosing_no_of_boxes() + noOfRolls);
                reduceproductRmStockSummaryModel.setProduction_no_of_boxes(reduceproductRmStockSummaryModel.getProduction_no_of_boxes() + noOfRolls);
                reduceproductRmStockSummaryModel.setProduction_quantity(reduceproductRmStockSummaryModel.getProduction_quantity() + mtrDifference);
                reduceproductRmStockSummaryModel.setProduction_weight(reduceproductRmStockSummaryModel.getProduction_weight() + weightDifference);
            } else {
                reduceproductRmStockSummaryModel.setCompany_id(sortTransferDetails.getCompany_id());
                reduceproductRmStockSummaryModel.setCompany_branch_id(sortTransferDetails.getCompany_branch_id());
                reduceproductRmStockSummaryModel.setFinancial_year(sortTransferDetails.getFinancial_year());
                addproductRmStockSummaryModel.setProduct_type_group("GF");
                reduceproductRmStockSummaryModel.setProduct_type_id(sortTransferDetails.getProduct_type_id());
                reduceproductRmStockSummaryModel.setProduct_rm_id(sortTransferDetails.getFrom_product_material_id());
              addproductRmStockSummaryModel.setProduct_material_unit_id(sortTransferDetails.getProduct_material_stock_unit_id());
              addproductRmStockSummaryModel.setProduct_material_packing_id(sortTransferDetails.getProduct_material_packing_id());

                reduceproductRmStockSummaryModel.setClosing_no_of_boxes(-noOfRolls);
                reduceproductRmStockSummaryModel.setClosing_balance_quantity(-mtrDifference);
                reduceproductRmStockSummaryModel.setClosing_balance_weight(-weightDifference);
                reduceproductRmStockSummaryModel.setProduction_no_of_boxes(-noOfRolls);
                reduceproductRmStockSummaryModel.setProduction_quantity(-mtrDifference);
                reduceproductRmStockSummaryModel.setProduction_weight(-weightDifference);

                reduceproductRmStockSummaryModel.setGodown_id(sortTransferDetails.getGodown_id());
                reduceproductRmStockSummaryModel.setGodown_section_id(sortTransferDetails.getGodown_section_id());
                reduceproductRmStockSummaryModel.setGodown_section_beans_id(sortTransferDetails.getGodown_section_beans_id());
                reduceproductRmStockSummaryModel.setCreated_by(sortTransferDetails.getCreated_by());
                reduceproductRmStockSummaryModel.setModified_by(sortTransferDetails.getCreated_by());
                reduceproductRmStockSummaryModel.setModified_on(sortTransferDetails.getModified_on());

                reduceProductRmStockSummaryList.add(reduceproductRmStockSummaryModel);
            }
            String transfer_date = sortTransferDetails.getInspection_production_date();
            LocalDate roll_date = LocalDate.parse(sortTransferDetails.getInspection_production_date(), formatter);
            if (roll_date.isBefore(stockImportDate)) {
                transfer_date = dateStr;
            }

//			smv_product_rm_stock_details
            CSmProductRmStockDetailsModel addProductRmStockDetailsModel = new CSmProductRmStockDetailsModel();
            CSmProductRmStockDetailsModel reduceProductRmStockDetailsModel = new CSmProductRmStockDetailsModel();

            String toBatchNo = (sortTransferDetails.getTo_style() == null || sortTransferDetails.getTo_style().isEmpty())
                    ? sortTransferDetails.getTo_sort_no()
                    : sortTransferDetails.getTo_sort_no() + "-" + sortTransferDetails.getTo_style();
            final String finalTransferDate = transfer_date;

            Optional<CSmProductRmStockDetailsModel> checkTheMaterialDertailAlreadyExist = addFgSortStockDetailsList.stream()
                    .filter(item -> sortTransferDetails.getTo_product_material_id().equals(item.getProduct_rm_id()) &&
                            sortTransferDetails.getGodown_id().equals(item.getGodown_id()) &&
                            toBatchNo.equals(item.getBatch_no()) &&
                            finalTransferDate.equals(item.getStock_date()) &&
                            sortTransferDetails.getTo_set_no().equals(item.getGoods_receipt_no()))
                    .findFirst();

            if (checkTheMaterialDertailAlreadyExist.isPresent()) {
                addProductRmStockDetailsModel = checkTheMaterialDertailAlreadyExist.get();
                addProductRmStockDetailsModel.setClosing_no_of_boxes(addProductRmStockDetailsModel.getClosing_no_of_boxes() + noOfRolls);
                addProductRmStockDetailsModel.setClosing_balance_quantity(addProductRmStockDetailsModel.getClosing_balance_quantity() + mtrDifference);
                addProductRmStockDetailsModel.setClosing_balance_weight(addProductRmStockDetailsModel.getClosing_balance_weight() + weightDifference);
                addProductRmStockDetailsModel.setProduction_no_of_boxes(addProductRmStockDetailsModel.getProduction_no_of_boxes() + noOfRolls);
                addProductRmStockDetailsModel.setProduction_quantity(addProductRmStockDetailsModel.getProduction_quantity() + mtrDifference);
                addProductRmStockDetailsModel.setProduction_weight(addProductRmStockDetailsModel.getProduction_weight() + weightDifference);
            } else {
                addProductRmStockDetailsModel.setCompany_id(sortTransferDetails.getCompany_id());
                addProductRmStockDetailsModel.setCompany_branch_id(sortTransferDetails.getCompany_branch_id());
                addProductRmStockDetailsModel.setFinancial_year(sortTransferDetails.getFinancial_year());
                addProductRmStockDetailsModel.setProduct_type_group("GF");
                addProductRmStockDetailsModel.setProduct_type_id(sortTransferDetails.getProduct_type_id());
                addProductRmStockDetailsModel.setProduct_rm_id(sortTransferDetails.getTo_product_material_id());
                addProductRmStockDetailsModel.setProduct_material_unit_id(sortTransferDetails.getProduct_material_stock_unit_id());
                addProductRmStockDetailsModel.setProduct_material_packing_id(sortTransferDetails.getProduct_material_packing_id());
                addProductRmStockDetailsModel.setStock_date(transfer_date);

                addProductRmStockDetailsModel.setGoods_receipt_no(sortTransferDetails.getTo_set_no());
                addProductRmStockDetailsModel.setBatch_no(toBatchNo);
                addProductRmStockDetailsModel.setProduction_no_of_boxes(noOfRolls);
                addProductRmStockDetailsModel.setProduction_quantity(mtrDifference);
                addProductRmStockDetailsModel.setProduction_weight(weightDifference);
                addProductRmStockDetailsModel.setClosing_no_of_boxes(noOfRolls);
                addProductRmStockDetailsModel.setClosing_balance_quantity(mtrDifference);
                addProductRmStockDetailsModel.setClosing_balance_weight(weightDifference);
                addProductRmStockDetailsModel.setGodown_id(sortTransferDetails.getGodown_id());
                addProductRmStockDetailsModel.setCreated_by(sortTransferDetails.getCreated_by());
                addProductRmStockDetailsModel.setModified_by(sortTransferDetails.getCreated_by());

                addFgSortStockDetailsList.add(addProductRmStockDetailsModel);
            }

            String fromBatchNo = (sortTransferDetails.getFrom_style() == null || sortTransferDetails.getFrom_style().isEmpty())
                    ? sortTransferDetails.getFrom_sort_no()
                    : sortTransferDetails.getFrom_sort_no() + "-" + sortTransferDetails.getFrom_style();

            Optional<CSmProductRmStockDetailsModel> checkReduceMaterialDertailAlreadyExist = reduceProductRmStockDetailsList.stream()
                    .filter(item -> sortTransferDetails.getFrom_product_material_id().equals(item.getProduct_rm_id()) &&
                            sortTransferDetails.getGodown_id().equals(item.getGodown_id()) &&
                            fromBatchNo.equals(item.getBatch_no()) &&
                            finalTransferDate.equals(item.getStock_date()) &&
                            sortTransferDetails.getFrom_set_no().equals(item.getGoods_receipt_no()))
                    .findFirst();


            if (checkReduceMaterialDertailAlreadyExist.isPresent()) {
                reduceProductRmStockDetailsModel = checkReduceMaterialDertailAlreadyExist.get();
                reduceProductRmStockDetailsModel.setClosing_no_of_boxes(reduceProductRmStockDetailsModel.getClosing_no_of_boxes() + noOfRolls);
                reduceProductRmStockDetailsModel.setClosing_balance_quantity(reduceProductRmStockDetailsModel.getClosing_balance_quantity() + mtrDifference);
                reduceProductRmStockDetailsModel.setClosing_balance_weight(reduceProductRmStockDetailsModel.getClosing_balance_weight() + weightDifference);
                reduceProductRmStockDetailsModel.setProduction_no_of_boxes(reduceProductRmStockDetailsModel.getProduction_no_of_boxes() + noOfRolls);
                reduceProductRmStockDetailsModel.setProduction_quantity(reduceProductRmStockDetailsModel.getProduction_quantity() + mtrDifference);
                reduceProductRmStockDetailsModel.setProduction_weight(reduceProductRmStockDetailsModel.getProduction_weight() + weightDifference);
            } else {
                reduceProductRmStockDetailsModel.setCompany_id(sortTransferDetails.getCompany_id());
                reduceProductRmStockDetailsModel.setCompany_branch_id(sortTransferDetails.getCompany_branch_id());
                reduceProductRmStockDetailsModel.setFinancial_year(sortTransferDetails.getFinancial_year());
                reduceProductRmStockDetailsModel.setProduct_type_group("GF");
                reduceProductRmStockDetailsModel.setProduct_type_id(sortTransferDetails.getProduct_type_id());
                reduceProductRmStockDetailsModel.setProduct_rm_id(sortTransferDetails.getFrom_product_material_id());
                reduceProductRmStockDetailsModel.setProduct_material_unit_id(sortTransferDetails.getProduct_material_stock_unit_id());
                reduceProductRmStockDetailsModel.setProduct_material_packing_id(sortTransferDetails.getProduct_material_packing_id());
                reduceProductRmStockDetailsModel.setStock_date(transfer_date);

                reduceProductRmStockDetailsModel.setGoods_receipt_no(sortTransferDetails.getFrom_set_no());
                reduceProductRmStockDetailsModel.setBatch_no(fromBatchNo);
                reduceProductRmStockDetailsModel.setProduction_no_of_boxes(noOfRolls);
                reduceProductRmStockDetailsModel.setProduction_quantity(mtrDifference);
                reduceProductRmStockDetailsModel.setProduction_weight(weightDifference);
                reduceProductRmStockDetailsModel.setClosing_no_of_boxes(noOfRolls);
                reduceProductRmStockDetailsModel.setClosing_balance_quantity(mtrDifference);
                reduceProductRmStockDetailsModel.setClosing_balance_weight(weightDifference);
                reduceProductRmStockDetailsModel.setGodown_id(sortTransferDetails.getGodown_id());
                reduceProductRmStockDetailsModel.setCreated_by(sortTransferDetails.getCreated_by());
                reduceProductRmStockDetailsModel.setModified_by(sortTransferDetails.getCreated_by());

                reduceProductRmStockDetailsList.add(reduceProductRmStockDetailsModel);
            }
        }

        // Prepare Map objects for increase and decrease
        Map<String, Object> increaseStockDetails = new HashMap<>();
        Map<String, Object> decreaseStockDetails = new HashMap<>();

        if (!addFgSortStockDetailsList.isEmpty()) {
            increaseStockDetails.put("RmStockSummary", addFgSortStockSummaryList);
            increaseStockDetails.put("RmStockDetails", addFgSortStockDetailsList);
            batchresponse = cSmProductRmStockDetailsController.FnAddUpdateFGStock(increaseStockDetails, "Increase", companyId);
            bothBatchresponse.put("FGStockAddDetails", batchresponse);
        }

        if (!reduceProductRmStockDetailsList.isEmpty()) {

            for (CSmProductRmStockDetailsModel item : reduceProductRmStockDetailsList) {
                item.setClosing_balance_quantity(-item.getClosing_balance_quantity());
                item.setClosing_balance_weight(-item.getClosing_balance_weight());
                item.setClosing_no_of_boxes(-item.getClosing_no_of_boxes());
                item.setProduction_quantity(-item.getProduction_quantity());
                item.setProduction_weight(-item.getProduction_weight());
                item.setProduction_no_of_boxes(-item.getProduction_no_of_boxes());
            }


            decreaseStockDetails.put("RmStockSummary", reduceProductRmStockSummaryList);
            decreaseStockDetails.put("RmStockDetails", reduceProductRmStockDetailsList);
            batchresponse = cSmProductRmStockDetailsController.FnAddUpdateFGStock(decreaseStockDetails, "Decrease", companyId);
            bothBatchresponse.put("FGStockReducedDetails", batchresponse);
        }

        System.out.println("bothBatchresponse" + bothBatchresponse);
        return bothBatchresponse;
    }


    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(Integer companyId, Integer sort_transfer_master_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            CSortTransferMasterViewModel sortTransferMasterModel = sortTransferMasterDataViewRepository.FnGetSortTransferMasterModel(sort_transfer_master_id, companyId);
            List<CSortTransferDetailsViewModel> sortTransferDetailsModels = sortTransferDetailsDataViewRepository.FnShowParticularRecordForUpdate(sort_transfer_master_id, companyId);

            response.put("sortTransferMasterData", sortTransferMasterModel);
            response.put("sortTransferDetailsData", sortTransferDetailsModels);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
        }

        return response;
    }


}
